# Task 7 fix round 1 review package

## diff stat
 .../sqs/listener/SqsListenerAnnotationRegistrar.kt | 103 +++++++++
 .../listener/SqsListenerAnnotationRegistrarTest.kt | 251 +++++++++++++++++++++
 2 files changed, 354 insertions(+)

## diff
diff --git a/sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/listener/SqsListenerAnnotationRegistrar.kt b/sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/listener/SqsListenerAnnotationRegistrar.kt
new file mode 100644
index 0000000..a8dcd32
--- /dev/null
+++ b/sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/listener/SqsListenerAnnotationRegistrar.kt
@@ -0,0 +1,103 @@
+package io.github.hantsy.awskotlin.sqs.listener
+
+import io.github.hantsy.awskotlin.sqs.SqsQueueResolver
+import io.github.hantsy.awskotlin.sqs.SqsTemplate
+import kotlinx.coroutines.CoroutineScope
+import kotlinx.coroutines.Dispatchers
+import kotlinx.coroutines.SupervisorJob
+import org.springframework.beans.factory.config.BeanPostProcessor
+import org.springframework.context.SmartLifecycle
+import java.lang.reflect.Method
+import java.util.concurrent.CopyOnWriteArrayList
+import kotlin.reflect.KFunction
+import kotlin.reflect.KParameter
+import kotlin.reflect.full.findAnnotation
+import kotlin.reflect.full.memberFunctions
+import kotlin.reflect.jvm.jvmErasure
+import kotlin.reflect.jvm.kotlinFunction
+
+/**
+ * Collects [SqsListener]-annotated methods from beans and runs them.
+ *
+ * [postProcessAfterInitialization] only records endpoints; no polling starts before
+ * the context refresh. [start] creates and starts one [SqsListenerContainer] per
+ * endpoint — a missing queue fails startup and stops any containers already started.
+ * [stop] stops all containers. Testable without an ApplicationContext.
+ */
+class SqsListenerAnnotationRegistrar(
+    private val template: SqsTemplate,
+    private val queueResolver: SqsQueueResolver,
+    private val config: SqsListenerConfig = SqsListenerConfig(),
+    private val scope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
+) : BeanPostProcessor,
+    SmartLifecycle {
+    private val endpoints = CopyOnWriteArrayList<SqsListenerEndpoint>()
+    private val containers = CopyOnWriteArrayList<SqsListenerContainer>()
+
+    @Volatile
+    private var running = false
+
+    override fun postProcessAfterInitialization(bean: Any, beanName: String): Any {
+        if (endpoints.any { it.bean === bean }) return bean
+        // Pre-filter with Java reflection: safe and cheap for any bean (proxies, lambdas, Java classes).
+        // declaredMethods is included so private annotated Kotlin listeners are not silently skipped.
+        val annotated = (bean.javaClass.methods.asSequence() + bean.javaClass.declaredMethods.asSequence())
+            .filter { !it.isBridge && !it.isSynthetic && it.isAnnotationPresent(SqsListener::class.java) }
+            .distinct()
+            .toList()
+        if (annotated.isEmpty()) return bean
+        val functions = bean::class.memberFunctions
+        annotated.forEach { method ->
+            // Kotlin discovery is authoritative: kotlinFunction resolves internal name mangling
+            // (foo$module) and private members that plain name matching cannot map back.
+            val function = method.kotlinFunction
+                ?: functions.firstOrNull { it.matches(method) }
+                ?: functions.firstOrNull {
+                    it.name == method.name.substringBefore('$') && it.findAnnotation<SqsListener>() != null
+                }
+                ?: error("No Kotlin member function for annotated method ${method.name} on ${bean.javaClass.name}")
+            val annotation = function.findAnnotation<SqsListener>() ?: method.getAnnotation(SqsListener::class.java)
+            endpoints += SqsListenerEndpoint(bean, function, annotation.queues.toList())
+        }
+        return bean
+    }
+
+    override fun isAutoStartup(): Boolean = config.autoStartup
+
+    override fun isRunning(): Boolean = running
+
+    @Synchronized
+    override fun start() {
+        if (running) return
+        val started = mutableListOf<SqsListenerContainer>()
+        try {
+            endpoints.forEach { endpoint ->
+                val container = SqsListenerContainer(endpoint, template, queueResolver, config, scope)
+                container.start()
+                started += container
+            }
+        } catch (e: Exception) {
+            started.forEach { runCatching { it.stop() } }
+            throw e
+        }
+        containers += started
+        running = true
+    }
+
+    @Synchronized
+    override fun stop() {
+        if (!running) return
+        running = false
+        containers.forEach { it.stop() }
+        containers.clear()
+    }
+
+    // Maps a Java method back to its Kotlin function; suspend methods carry a trailing Continuation parameter.
+    private fun KFunction<*>.matches(method: Method): Boolean {
+        if (name != method.name) return false
+        val valueParameters = parameters.filter { it.kind == KParameter.Kind.VALUE }
+        val parameterTypes = method.parameterTypes.let { if (isSuspend) it.dropLast(1) else it.asList() }
+        return valueParameters.size == parameterTypes.size &&
+            valueParameters.zip(parameterTypes).all { (parameter, type) -> parameter.type.jvmErasure.java == type }
+    }
+}
diff --git a/sqs/src/test/kotlin/io/github/hantsy/awskotlin/sqs/listener/SqsListenerAnnotationRegistrarTest.kt b/sqs/src/test/kotlin/io/github/hantsy/awskotlin/sqs/listener/SqsListenerAnnotationRegistrarTest.kt
new file mode 100644
index 0000000..0740974
--- /dev/null
+++ b/sqs/src/test/kotlin/io/github/hantsy/awskotlin/sqs/listener/SqsListenerAnnotationRegistrarTest.kt
@@ -0,0 +1,251 @@
+package io.github.hantsy.awskotlin.sqs.listener
+
+import aws.sdk.kotlin.services.sqs.model.DeleteMessageResponse
+import io.github.hantsy.awskotlin.sqs.SqsDestinationOperations
+import io.github.hantsy.awskotlin.sqs.SqsMessage
+import io.github.hantsy.awskotlin.sqs.SqsQueueNotFoundException
+import io.github.hantsy.awskotlin.sqs.SqsQueueResolver
+import io.github.hantsy.awskotlin.sqs.SqsTemplate
+import io.kotest.assertions.throwables.shouldThrow
+import io.kotest.matchers.shouldBe
+import io.kotest.matchers.types.shouldBeSameInstanceAs
+import io.mockk.coEvery
+import io.mockk.coVerify
+import io.mockk.confirmVerified
+import io.mockk.every
+import io.mockk.mockk
+import kotlinx.coroutines.ExperimentalCoroutinesApi
+import kotlinx.coroutines.awaitCancellation
+import kotlinx.coroutines.test.advanceUntilIdle
+import kotlinx.coroutines.test.runTest
+import org.junit.jupiter.api.Test
+
+@OptIn(ExperimentalCoroutinesApi::class)
+class SqsListenerAnnotationRegistrarTest {
+    private val template = mockk<SqsTemplate>()
+    private val resolver = mockk<SqsQueueResolver>()
+    private val operations = mockk<SqsDestinationOperations>()
+    private val urlA = "https://sqs.us-east-1.amazonaws.com/123456789012/a"
+    private val urlB = "https://sqs.us-east-1.amazonaws.com/123456789012/b"
+    private val urlC = "https://sqs.us-east-1.amazonaws.com/123456789012/c"
+
+    private fun stubQueue(name: String, url: String) {
+        coEvery { resolver.resolve(name) } returns url
+        every { template.destination(url) } returns operations
+        coEvery { operations.receive(String::class.java, any(), any()) } coAnswers { awaitCancellation() }
+    }
+
+    @Test
+    fun `postProcess collects annotated methods and start resolves their queues`() = runTest {
+        stubQueue("a", urlA)
+        stubQueue("b", urlB)
+        stubQueue("c", urlC)
+        val registrar = SqsListenerAnnotationRegistrar(template, resolver, scope = this)
+        val bean = AnnotatedBean()
+
+        registrar.postProcessAfterInitialization(bean, "listener") shouldBeSameInstanceAs bean
+        registrar.start()
+        registrar.isRunning shouldBe true
+        advanceUntilIdle()
+
+        coVerify(exactly = 1) { resolver.resolve("a") }
+        coVerify(exactly = 1) { resolver.resolve("b") }
+        coVerify(exactly = 1) { resolver.resolve("c") }
+        coVerify(exactly = 3) { operations.receive(String::class.java, any(), any()) }
+        registrar.stop()
+        registrar.isRunning shouldBe false
+    }
+
+    @Test
+    fun `unannotated beans are ignored`() = runTest {
+        val registrar = SqsListenerAnnotationRegistrar(template, resolver, scope = this)
+        val bean = PlainBean()
+
+        registrar.postProcessAfterInitialization(bean, "plain") shouldBeSameInstanceAs bean
+        registrar.start()
+        registrar.isRunning shouldBe true
+        advanceUntilIdle()
+
+        confirmVerified(template, resolver)
+        registrar.stop()
+    }
+
+    @Test
+    fun `postProcessing the same bean twice does not duplicate endpoints`() = runTest {
+        stubQueue("a", urlA)
+        stubQueue("b", urlB)
+        stubQueue("c", urlC)
+        val registrar = SqsListenerAnnotationRegistrar(template, resolver, scope = this)
+        val bean = AnnotatedBean()
+
+        registrar.postProcessAfterInitialization(bean, "listener")
+        registrar.postProcessAfterInitialization(bean, "listener")
+        registrar.start()
+        advanceUntilIdle()
+
+        coVerify(exactly = 1) { resolver.resolve("a") }
+        coVerify(exactly = 1) { resolver.resolve("b") }
+        coVerify(exactly = 1) { resolver.resolve("c") }
+        registrar.stop()
+    }
+
+    @Test
+    fun `missing queue fails registrar startup`() = runTest {
+        stubQueue("a", urlA)
+        stubQueue("c", urlC)
+        coEvery { resolver.resolve("b") } throws SqsQueueNotFoundException("b")
+        val registrar = SqsListenerAnnotationRegistrar(template, resolver, scope = this)
+        registrar.postProcessAfterInitialization(AnnotatedBean(), "listener")
+
+        shouldThrow<SqsQueueNotFoundException> { registrar.start() }
+
+        registrar.isRunning shouldBe false
+        registrar.stop()
+    }
+
+    @Test
+    fun `stop is idempotent and cancels polling`() = runTest {
+        stubQueue("a", urlA)
+        stubQueue("b", urlB)
+        stubQueue("c", urlC)
+        val registrar = SqsListenerAnnotationRegistrar(template, resolver, scope = this)
+        registrar.postProcessAfterInitialization(AnnotatedBean(), "listener")
+
+        registrar.start()
+        advanceUntilIdle()
+        registrar.stop()
+        registrar.stop()
+        advanceUntilIdle()
+
+        registrar.isRunning shouldBe false
+        coVerify(exactly = 3) { operations.receive(String::class.java, any(), any()) }
+    }
+
+    @Test
+    fun `isAutoStartup follows config`() = runTest {
+        val registrar = SqsListenerAnnotationRegistrar(
+            template,
+            resolver,
+            SqsListenerConfig(autoStartup = false),
+            scope = this
+        )
+        registrar.isAutoStartup shouldBe false
+        registrar.isRunning shouldBe false
+    }
+
+    @Test
+    fun `internal annotated listener methods are discovered and invoked`() = runTest {
+        val messageA = message("internal-plain")
+        val messageB = message("internal-suspend")
+        val opsA = stubQueueOnce("a", urlA, messageA)
+        val opsB = stubQueueOnce("b", urlB, messageB)
+        val registrar = SqsListenerAnnotationRegistrar(template, resolver, scope = this)
+        val bean = InternalVisibilityBean()
+
+        registrar.postProcessAfterInitialization(bean, "internalVisibility")
+        registrar.start()
+        advanceUntilIdle()
+
+        bean.internalCalls shouldBe listOf("internal-plain")
+        bean.internalSuspendCalls shouldBe listOf(messageB)
+        coVerify(exactly = 1) { opsA.acknowledge(messageA) }
+        coVerify(exactly = 1) { opsB.acknowledge(messageB) }
+        registrar.stop()
+    }
+
+    @Test
+    fun `private annotated listener methods are discovered and invoked`() = runTest {
+        val messageA = message("private-plain")
+        val messageB = message("private-suspend")
+        val opsA = stubQueueOnce("a", urlA, messageA)
+        val opsB = stubQueueOnce("b", urlB, messageB)
+        val registrar = SqsListenerAnnotationRegistrar(template, resolver, scope = this)
+        val bean = PrivateVisibilityBean()
+
+        registrar.postProcessAfterInitialization(bean, "privateVisibility")
+        registrar.start()
+        advanceUntilIdle()
+
+        bean.privateCalls shouldBe listOf("private-plain")
+        bean.privateSuspendCalls shouldBe listOf(messageB)
+        coVerify(exactly = 1) { opsA.acknowledge(messageA) }
+        coVerify(exactly = 1) { opsB.acknowledge(messageB) }
+        registrar.stop()
+    }
+
+    private fun message(payload: String) = SqsMessage(
+        payload = payload,
+        messageId = "m-$payload",
+        receiptHandle = "rh-$payload",
+        body = payload,
+        attributes = emptyMap(),
+        messageAttributes = emptyMap()
+    )
+
+    // Deliver one message on the queue, then park so the poll loop does not busy-spin.
+    private fun stubQueueOnce(name: String, url: String, message: SqsMessage<String>): SqsDestinationOperations {
+        val queueOperations = mockk<SqsDestinationOperations>()
+        coEvery { resolver.resolve(name) } returns url
+        every { template.destination(url) } returns queueOperations
+        var delivered = false
+        coEvery { queueOperations.receive(String::class.java, any(), any()) } coAnswers {
+            if (delivered) {
+                awaitCancellation()
+            } else {
+                delivered = true
+                message
+            }
+        }
+        coEvery { queueOperations.acknowledge(message) } returns DeleteMessageResponse { }
+        return queueOperations
+    }
+
+    private class AnnotatedBean {
+        @SqsListener("a")
+        fun first(payload: String) {
+        }
+
+        @SqsListener("b", "c")
+        suspend fun second(message: SqsMessage<String>) {
+        }
+
+        fun notAListener(payload: String) {
+        }
+    }
+
+    private class PlainBean {
+        fun handle(payload: String) {
+        }
+    }
+
+    // Not private so internal members compile to mangled public bytecode (foo$module).
+    class InternalVisibilityBean {
+        val internalCalls = mutableListOf<String>()
+        val internalSuspendCalls = mutableListOf<SqsMessage<String>>()
+
+        @SqsListener("a")
+        internal fun handleInternal(payload: String) {
+            internalCalls += payload
+        }
+
+        @SqsListener("b")
+        internal suspend fun handleInternalSuspend(message: SqsMessage<String>) {
+            internalSuspendCalls += message
+        }
+    }
+
+    class PrivateVisibilityBean {
+        val privateCalls = mutableListOf<String>()
+        val privateSuspendCalls = mutableListOf<SqsMessage<String>>()
+
+        @SqsListener("a")
+        private fun handlePrivate(payload: String) {
+            privateCalls += payload
+        }
+
+        @SqsListener("b")
+        private suspend fun handlePrivateSuspend(message: SqsMessage<String>) {
+            privateSuspendCalls += message
+        }
+    }
+}
