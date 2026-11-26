package io.github.hantsy.awskotlin.sqs.listener

import aws.sdk.kotlin.services.sqs.model.DeleteMessageResponse
import io.github.hantsy.awskotlin.sqs.SqsDestinationOperations
import io.github.hantsy.awskotlin.sqs.SqsMessage
import io.github.hantsy.awskotlin.sqs.SqsQueueNotFoundException
import io.github.hantsy.awskotlin.sqs.SqsQueueResolver
import io.github.hantsy.awskotlin.sqs.SqsTemplate
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeSameInstanceAs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.awaitCancellation
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.springframework.aop.framework.ProxyFactory

@OptIn(ExperimentalCoroutinesApi::class)
class SqsListenerAnnotationRegistrarTest {
    private val template = mockk<SqsTemplate>()
    private val resolver = mockk<SqsQueueResolver>()
    private val operations = mockk<SqsDestinationOperations>()
    private val urlA = "https://sqs.us-east-1.amazonaws.com/123456789012/a"
    private val urlB = "https://sqs.us-east-1.amazonaws.com/123456789012/b"
    private val urlC = "https://sqs.us-east-1.amazonaws.com/123456789012/c"

    private fun stubQueue(name: String, url: String) {
        coEvery { resolver.resolve(name) } returns url
        every { template.destination(url) } returns operations
        coEvery { operations.receive(String::class.java, any(), any()) } coAnswers { awaitCancellation() }
    }

    @Test
    fun `postProcess collects annotated methods and start resolves their queues`() = runTest {
        stubQueue("a", urlA)
        stubQueue("b", urlB)
        stubQueue("c", urlC)
        val registrar = SqsListenerAnnotationRegistrar(template, resolver, scope = this)
        val bean = AnnotatedBean()

        registrar.postProcessAfterInitialization(bean, "listener") shouldBeSameInstanceAs bean
        registrar.start()
        registrar.isRunning shouldBe true
        advanceUntilIdle()

        coVerify(exactly = 1) { resolver.resolve("a") }
        coVerify(exactly = 1) { resolver.resolve("b") }
        coVerify(exactly = 1) { resolver.resolve("c") }
        coVerify(exactly = 3) { operations.receive(String::class.java, any(), any()) }
        registrar.stop()
        registrar.isRunning shouldBe false
    }

    @Test
    fun `unannotated beans are ignored`() = runTest {
        val registrar = SqsListenerAnnotationRegistrar(template, resolver, scope = this)
        val bean = PlainBean()

        registrar.postProcessAfterInitialization(bean, "plain") shouldBeSameInstanceAs bean
        registrar.start()
        registrar.isRunning shouldBe true
        advanceUntilIdle()

        confirmVerified(template, resolver)
        registrar.stop()
    }

    @Test
    fun `postProcessing the same bean twice does not duplicate endpoints`() = runTest {
        stubQueue("a", urlA)
        stubQueue("b", urlB)
        stubQueue("c", urlC)
        val registrar = SqsListenerAnnotationRegistrar(template, resolver, scope = this)
        val bean = AnnotatedBean()

        registrar.postProcessAfterInitialization(bean, "listener")
        registrar.postProcessAfterInitialization(bean, "listener")
        registrar.start()
        advanceUntilIdle()

        coVerify(exactly = 1) { resolver.resolve("a") }
        coVerify(exactly = 1) { resolver.resolve("b") }
        coVerify(exactly = 1) { resolver.resolve("c") }
        registrar.stop()
    }

    @Test
    fun `missing queue fails registrar startup`() = runTest {
        stubQueue("a", urlA)
        stubQueue("c", urlC)
        coEvery { resolver.resolve("b") } throws SqsQueueNotFoundException("b")
        val registrar = SqsListenerAnnotationRegistrar(template, resolver, scope = this)
        registrar.postProcessAfterInitialization(AnnotatedBean(), "listener")

        shouldThrow<SqsQueueNotFoundException> { registrar.start() }

        registrar.isRunning shouldBe false
        registrar.stop()
    }

    @Test
    fun `stop is idempotent and cancels polling`() = runTest {
        stubQueue("a", urlA)
        stubQueue("b", urlB)
        stubQueue("c", urlC)
        val registrar = SqsListenerAnnotationRegistrar(template, resolver, scope = this)
        registrar.postProcessAfterInitialization(AnnotatedBean(), "listener")

        registrar.start()
        advanceUntilIdle()
        registrar.stop()
        registrar.stop()
        advanceUntilIdle()

        registrar.isRunning shouldBe false
        coVerify(exactly = 3) { operations.receive(String::class.java, any(), any()) }
    }

    @Test
    fun `isAutoStartup follows config`() = runTest {
        val registrar = SqsListenerAnnotationRegistrar(
            template,
            resolver,
            SqsListenerConfig(autoStartup = false),
            scope = this
        )
        registrar.isAutoStartup shouldBe false
        registrar.isRunning shouldBe false
    }

    @Test
    fun `internal annotated listener methods are discovered and invoked`() = runTest {
        val messageA = message("internal-plain")
        val messageB = message("internal-suspend")
        val opsA = stubQueueOnce("a", urlA, messageA)
        val opsB = stubQueueOnce("b", urlB, messageB)
        val registrar = SqsListenerAnnotationRegistrar(template, resolver, scope = this)
        val bean = InternalVisibilityBean()

        registrar.postProcessAfterInitialization(bean, "internalVisibility")
        registrar.start()
        advanceUntilIdle()

        bean.internalCalls shouldBe listOf("internal-plain")
        bean.internalSuspendCalls shouldBe listOf(messageB)
        coVerify(exactly = 1) { opsA.acknowledge(messageA) }
        coVerify(exactly = 1) { opsB.acknowledge(messageB) }
        registrar.stop()
    }

    @Test
    fun `private annotated listener methods are discovered and invoked`() = runTest {
        val messageA = message("private-plain")
        val messageB = message("private-suspend")
        val opsA = stubQueueOnce("a", urlA, messageA)
        val opsB = stubQueueOnce("b", urlB, messageB)
        val registrar = SqsListenerAnnotationRegistrar(template, resolver, scope = this)
        val bean = PrivateVisibilityBean()

        registrar.postProcessAfterInitialization(bean, "privateVisibility")
        registrar.start()
        advanceUntilIdle()

        bean.privateCalls shouldBe listOf("private-plain")
        bean.privateSuspendCalls shouldBe listOf(messageB)
        coVerify(exactly = 1) { opsA.acknowledge(messageA) }
        coVerify(exactly = 1) { opsB.acknowledge(messageB) }
        registrar.stop()
    }

    @Test
    fun `CGLIB proxied listener beans are discovered and invoked on the target`() = runTest {
        val message = message("cglib")
        val ops = stubQueueOnce("a", urlA, message)
        val registrar = SqsListenerAnnotationRegistrar(template, resolver, scope = this)
        val target = CglibListenerBean()
        val proxy = ProxyFactory(target).apply { isProxyTargetClass = true }.proxy

        registrar.postProcessAfterInitialization(proxy, "cglibProxied") shouldBeSameInstanceAs proxy
        registrar.start()
        advanceUntilIdle()

        target.calls shouldBe listOf("cglib")
        coVerify(exactly = 1) { ops.acknowledge(message) }
        registrar.stop()
    }

    @Test
    fun `CGLIB proxied internal listener methods are discovered and invoked`() = runTest {
        val message = message("cglib-internal")
        val ops = stubQueueOnce("a", urlA, message)
        val registrar = SqsListenerAnnotationRegistrar(template, resolver, scope = this)
        val target = InternalCglibListenerBean()
        val proxy = ProxyFactory(target).apply { isProxyTargetClass = true }.proxy

        registrar.postProcessAfterInitialization(proxy, "internalCglibProxied")
        registrar.start()
        advanceUntilIdle()

        target.calls shouldBe listOf("cglib-internal")
        coVerify(exactly = 1) { ops.acknowledge(message) }
        registrar.stop()
    }

    @Test
    fun `JDK proxied listener beans with a singleton target are discovered and invoked`() = runTest {
        val message = message("jdk")
        val ops = stubQueueOnce("a", urlA, message)
        val registrar = SqsListenerAnnotationRegistrar(template, resolver, scope = this)
        val target = JdkListenerBean()
        val proxy = ProxyFactory().apply {
            setTarget(target)
            addInterface(JdkListenerContract::class.java)
        }.proxy

        registrar.postProcessAfterInitialization(proxy, "jdkProxied")
        registrar.start()
        advanceUntilIdle()

        target.calls shouldBe listOf("jdk")
        coVerify(exactly = 1) { ops.acknowledge(message) }
        registrar.stop()
    }

    @Test
    fun `postProcessing the same proxy twice does not duplicate endpoints`() = runTest {
        val message = message("cglib-once")
        val ops = stubQueueOnce("a", urlA, message)
        val registrar = SqsListenerAnnotationRegistrar(template, resolver, scope = this)
        val target = CglibListenerBean()
        val proxy = ProxyFactory(target).apply { isProxyTargetClass = true }.proxy

        registrar.postProcessAfterInitialization(proxy, "cglibProxied")
        registrar.postProcessAfterInitialization(proxy, "cglibProxied")
        registrar.start()
        advanceUntilIdle()

        target.calls shouldBe listOf("cglib-once")
        coVerify(exactly = 1) { resolver.resolve("a") }
        coVerify(exactly = 1) { ops.acknowledge(message) }
        registrar.stop()
    }

    private fun message(payload: String) = SqsMessage(
        payload = payload,
        messageId = "m-$payload",
        receiptHandle = "rh-$payload",
        body = payload,
        attributes = emptyMap(),
        messageAttributes = emptyMap()
    )

    // Deliver one message on the queue, then park so the poll loop does not busy-spin.
    private fun stubQueueOnce(name: String, url: String, message: SqsMessage<String>): SqsDestinationOperations {
        val queueOperations = mockk<SqsDestinationOperations>()
        coEvery { resolver.resolve(name) } returns url
        every { template.destination(url) } returns queueOperations
        var delivered = false
        coEvery { queueOperations.receive(String::class.java, any(), any()) } coAnswers {
            if (delivered) {
                awaitCancellation()
            } else {
                delivered = true
                message
            }
        }
        coEvery { queueOperations.acknowledge(message) } returns DeleteMessageResponse { }
        return queueOperations
    }

    private class AnnotatedBean {
        @SqsListener("a")
        fun first(payload: String) {
        }

        @SqsListener("b", "c")
        suspend fun second(message: SqsMessage<String>) {
        }

        fun notAListener(payload: String) {
        }
    }

    private class PlainBean {
        fun handle(payload: String) {
        }
    }

    // Not private so internal members compile to mangled public bytecode (foo$module).
    class InternalVisibilityBean {
        val internalCalls = mutableListOf<String>()
        val internalSuspendCalls = mutableListOf<SqsMessage<String>>()

        @SqsListener("a")
        internal fun handleInternal(payload: String) {
            internalCalls += payload
        }

        @SqsListener("b")
        internal suspend fun handleInternalSuspend(message: SqsMessage<String>) {
            internalSuspendCalls += message
        }
    }

    class PrivateVisibilityBean {
        val privateCalls = mutableListOf<String>()
        val privateSuspendCalls = mutableListOf<SqsMessage<String>>()

        @SqsListener("a")
        private fun handlePrivate(payload: String) {
            privateCalls += payload
        }

        @SqsListener("b")
        private suspend fun handlePrivateSuspend(message: SqsMessage<String>) {
            privateSuspendCalls += message
        }
    }

    // Open class/method so CGLIB can subclass it; the override carries no annotations.
    open class CglibListenerBean {
        val calls = mutableListOf<String>()

        @SqsListener("a")
        open fun handle(payload: String) {
            calls += payload
        }
    }

    open class InternalCglibListenerBean {
        val calls = mutableListOf<String>()

        @SqsListener("a")
        internal open fun handleInternal(payload: String) {
            calls += payload
        }
    }

    interface JdkListenerContract {
        fun handle(payload: String)
    }

    // The annotation lives on the implementation, not on the proxied interface.
    class JdkListenerBean : JdkListenerContract {
        val calls = mutableListOf<String>()

        @SqsListener("a")
        override fun handle(payload: String) {
            calls += payload
        }
    }
}
