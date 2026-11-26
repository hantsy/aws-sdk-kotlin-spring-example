# Task 8 review package

## git status
 A core/build.gradle.kts
 A core/gradle.properties
 A core/src/main/kotlin/io/github/hantsy/awskotlin/core/AwsKotlinCredentialsProviderFactory.kt
 A core/src/main/kotlin/io/github/hantsy/awskotlin/core/AwsKotlinProperties.kt
 A core/src/main/kotlin/io/github/hantsy/awskotlin/core/autoconfigure/AwsKotlinAutoConfiguration.kt
 A core/src/main/resources/META-INF/additional-spring-configuration-metadata.json
 A core/src/main/resources/META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports
 A core/src/test/kotlin/io/github/hantsy/awskotlin/core/AwsKotlinCredentialsProviderFactoryTest.kt
 A core/src/test/kotlin/io/github/hantsy/awskotlin/core/autoconfigure/AwsKotlinAutoConfigurationTest.kt
 M gradle/libs.versions.toml
 A s3/build.gradle.kts
 A s3/gradle.properties
 A s3/src/main/kotlin/io/github/hantsy/awskotlin/s3/S3BucketOperations.kt
 A s3/src/main/kotlin/io/github/hantsy/awskotlin/s3/S3ObjectOperations.kt
 A s3/src/main/kotlin/io/github/hantsy/awskotlin/s3/S3Template.kt
 A s3/src/test/kotlin/io/github/hantsy/awskotlin/s3/S3TemplateLocalStackTest.kt
 A s3/src/test/kotlin/io/github/hantsy/awskotlin/s3/S3TemplateTest.kt
 A secrets-manager/build.gradle.kts
 A secrets-manager/gradle.properties
 A secrets-manager/src/main/kotlin/io/github/hantsy/awskotlin/secretsmanager/SecretOperations.kt
 A secrets-manager/src/main/kotlin/io/github/hantsy/awskotlin/secretsmanager/SecretsManagerTemplate.kt
 A secrets-manager/src/test/kotlin/io/github/hantsy/awskotlin/secretsmanager/SecretsManagerTemplateTest.kt
 M settings.gradle.kts
 A sns/build.gradle.kts
 A sns/gradle.properties
 A sns/src/main/kotlin/io/github/hantsy/awskotlin/sns/SnsTemplate.kt
 A sns/src/main/kotlin/io/github/hantsy/awskotlin/sns/TopicOperations.kt
 A sns/src/test/kotlin/io/github/hantsy/awskotlin/sns/SnsTemplateTest.kt
 A sqs/build.gradle.kts
 A sqs/gradle.properties
 A sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/DefaultSqsDestinationOperations.kt
 A sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/JacksonSqsMessageConverter.kt
 A sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/SqsAcknowledgementMode.kt
 A sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/SqsDestinationOperations.kt
 A sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/SqsMessage.kt
 A sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/SqsMessageConverter.kt
 A sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/SqsQueueResolver.kt
 A sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/SqsTemplate.kt
 A sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/SqsTemplateException.kt
 A sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/listener/Acknowledge.kt
 A sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/listener/SqsListener.kt
 A sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/listener/SqsListenerAnnotationRegistrar.kt
 A sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/listener/SqsListenerConfig.kt
 A sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/listener/SqsListenerContainer.kt
 A sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/listener/SqsListenerEndpoint.kt
 A sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/listener/SqsListenerMethod.kt
 A sqs/src/test/kotlin/io/github/hantsy/awskotlin/sqs/JacksonSqsMessageConverterTest.kt
 A sqs/src/test/kotlin/io/github/hantsy/awskotlin/sqs/SqsQueueResolverTest.kt
 A sqs/src/test/kotlin/io/github/hantsy/awskotlin/sqs/SqsTemplateTest.kt
 A sqs/src/test/kotlin/io/github/hantsy/awskotlin/sqs/listener/SqsListenerAnnotationRegistrarTest.kt
 A sqs/src/test/kotlin/io/github/hantsy/awskotlin/sqs/listener/SqsListenerContainerTest.kt
 A starters/starter-s3/build.gradle.kts
 A starters/starter-s3/gradle.properties
 A starters/starter-s3/src/main/kotlin/io/github/hantsy/awskotlin/s3/autoconfigure/S3AutoConfiguration.kt
 A starters/starter-s3/src/main/kotlin/io/github/hantsy/awskotlin/s3/autoconfigure/S3Properties.kt
 A starters/starter-s3/src/main/resources/META-INF/additional-spring-configuration-metadata.json
 A starters/starter-s3/src/main/resources/META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports
 A starters/starter-s3/src/test/kotlin/io/github/hantsy/awskotlin/s3/autoconfigure/S3AutoConfigurationTest.kt
 A starters/starter-secrets-manager/build.gradle.kts
 A starters/starter-secrets-manager/gradle.properties
 A starters/starter-secrets-manager/src/main/kotlin/io/github/hantsy/awskotlin/secretsmanager/autoconfigure/SecretsManagerAutoConfiguration.kt
 A starters/starter-secrets-manager/src/main/kotlin/io/github/hantsy/awskotlin/secretsmanager/autoconfigure/SecretsManagerProperties.kt
 A starters/starter-secrets-manager/src/main/resources/META-INF/additional-spring-configuration-metadata.json
 A starters/starter-secrets-manager/src/main/resources/META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports
 A starters/starter-secrets-manager/src/test/kotlin/io/github/hantsy/awskotlin/secretsmanager/autoconfigure/SecretsManagerAutoConfigurationTest.kt
 A starters/starter-sns/build.gradle.kts
 A starters/starter-sns/gradle.properties
 A starters/starter-sns/src/main/kotlin/io/github/hantsy/awskotlin/sns/autoconfigure/SnsAutoConfiguration.kt
 A starters/starter-sns/src/main/kotlin/io/github/hantsy/awskotlin/sns/autoconfigure/SnsProperties.kt
 A starters/starter-sns/src/main/resources/META-INF/additional-spring-configuration-metadata.json
 A starters/starter-sns/src/main/resources/META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports
 A starters/starter-sns/src/test/kotlin/io/github/hantsy/awskotlin/sns/autoconfigure/SnsAutoConfigurationTest.kt
 A starters/starter-sqs/build.gradle.kts
 A starters/starter-sqs/gradle.properties
 A starters/starter-sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/autoconfigure/KotlinDurationConverter.kt
 A starters/starter-sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/autoconfigure/SqsAutoConfiguration.kt
 A starters/starter-sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/autoconfigure/SqsProperties.kt
 A starters/starter-sqs/src/main/resources/META-INF/additional-spring-configuration-metadata.json
 A starters/starter-sqs/src/main/resources/META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports
 A starters/starter-sqs/src/test/kotlin/io/github/hantsy/awskotlin/sqs/autoconfigure/SqsAutoConfigurationTest.kt
 A starters/starter-sqs/src/test/kotlin/io/github/hantsy/awskotlin/sqs/autoconfigure/SqsLocalStackIntegrationTest.kt
?? .kotlin/
?? .superpowers/

## diff stat
 gradle/libs.versions.toml                          |  11 +
 sqs/build.gradle.kts                               |  61 +++++
 .../hantsy/awskotlin/sqs/listener/Acknowledge.kt   |  31 +++
 .../hantsy/awskotlin/sqs/listener/SqsListener.kt   |  19 ++
 .../sqs/listener/SqsListenerAnnotationRegistrar.kt | 111 ++++++++
 .../awskotlin/sqs/listener/SqsListenerConfig.kt    |  19 ++
 .../awskotlin/sqs/listener/SqsListenerContainer.kt | 154 +++++++++++
 .../awskotlin/sqs/listener/SqsListenerEndpoint.kt  |   9 +
 .../awskotlin/sqs/listener/SqsListenerMethod.kt    |  96 +++++++
 starters/starter-sqs/build.gradle.kts              |  77 ++++++
 starters/starter-sqs/gradle.properties             |   5 +
 .../sqs/autoconfigure/KotlinDurationConverter.kt   |  43 ++++
 .../sqs/autoconfigure/SqsAutoConfiguration.kt      |  98 +++++++
 .../awskotlin/sqs/autoconfigure/SqsProperties.kt   |  27 ++
 .../additional-spring-configuration-metadata.json  |  57 ++++
 ...rk.boot.autoconfigure.AutoConfiguration.imports |   1 +
 .../sqs/autoconfigure/SqsAutoConfigurationTest.kt  | 286 +++++++++++++++++++++
 .../autoconfigure/SqsLocalStackIntegrationTest.kt  | 214 +++++++++++++++
 18 files changed, 1319 insertions(+)

## diff
diff --git a/gradle/libs.versions.toml b/gradle/libs.versions.toml
index 3400e81..bcf7b75 100644
--- a/gradle/libs.versions.toml
+++ b/gradle/libs.versions.toml
@@ -28,23 +28,33 @@ kotlinStdlib = { module = "org.jetbrains.kotlin:kotlin-stdlib" }
 kotlinCoroutinesCore = { module = "org.jetbrains.kotlinx:kotlinx-coroutines-core", version.ref = "kotlinCoroutines" }
 kotlinCoroutinesReactor = { module = "org.jetbrains.kotlinx:kotlinx-coroutines-reactor", version.ref = "kotlinCoroutines" }
 
 # okhttp
 okhttp = { module = "com.squareup.okhttp3:okhttp", version.ref = "okhttp" }
 okhttpCoroutines = { module = "com.squareup.okhttp3:okhttp-coroutines", version.ref = "okhttp" }
 okhttpLoggingInterceptor = { module = "com.squareup.okhttp3:logging-interceptor", version.ref = "okhttp" }
 
 # AWS SDK for Kotlin
 awsSdkKotlinHttp = { module = "aws.sdk.kotlin:aws-http", version.ref = "awsSdkKotlin" }
+awsSdkKotlinConfig = { module = "aws.sdk.kotlin:aws-config", version.ref = "awsSdkKotlin" }
 awsSdkKotlinS3 = { module = "aws.sdk.kotlin:s3", version.ref = "awsSdkKotlin" }
 awsSdkKotlinSecretManager = { module = "aws.sdk.kotlin:secretsmanager", version.ref = "awsSdkKotlin" }
 awsSqs = { module = "aws.sdk.kotlin:sqs", version.ref = "awsSdkKotlin" }
+awsSdkKotlinSns = { module = "aws.sdk.kotlin:sns", version.ref = "awsSdkKotlin" }
+
+# Spring Boot auto-configuration support
+springContext = { module = "org.springframework:spring-context" }
+springAop = { module = "org.springframework:spring-aop" }
+springBootAutoconfigure = { module = "org.springframework.boot:spring-boot-autoconfigure" }
+springBootConfigurationProcessor = { module = "org.springframework.boot:spring-boot-configuration-processor" }
+springBootStarterTest = { module = "org.springframework.boot:spring-boot-starter-test" }
+jacksonDatabind = { module = "tools.jackson.core:jackson-databind" }
 
 # Test dependencies
 webfluxTest = { module = "org.springframework.boot:spring-boot-starter-webflux-test" }
 reactorTest = { module = "io.projectreactor:reactor-test" }
 testcontainersJunitJupiter = { module = "org.testcontainers:testcontainers-junit-jupiter" }
 testcontainersLocalstack = { module = "org.testcontainers:testcontainers-localstack" }
 springBootTestcontainers = { module = "org.springframework.boot:spring-boot-testcontainers" }
 kotlinTestJunit5 = { module = "org.jetbrains.kotlin:kotlin-test-junit5" }
 kotlinCoroutinesTest = { module = "org.jetbrains.kotlinx:kotlinx-coroutines-test", version.ref = "kotlinCoroutines" }
 kotestAssertionsCoreJvm = { module = "io.kotest:kotest-assertions-core-jvm", version.ref = "kotest" }
@@ -75,13 +85,14 @@ testcore = ["webfluxTest", "reactorTest", "kotlinCoroutinesTest"]
 testcontainers = ["testcontainersJunitJupiter", "testcontainersLocalstack", "springBootTestcontainers"]
 kotest = ["kotestAssertionsCoreJvm"]
 mockk = ["mockk", "springmockk"]
 awaitility = ["awaitility", "awaitilityKotlin"]
 kotlintest = ["kotlinTestJunit5"]
 
 [plugins]
 springBoot = { id = "org.springframework.boot", version.ref = "springboot" }
 dependencyManagement = { id = "io.spring.dependency-management", version.ref = "springDependencyManagement" }
 kotlinJvm = { id = "org.jetbrains.kotlin.jvm", version.ref = "kotlin" }
+kotlinKapt = { id = "org.jetbrains.kotlin.kapt", version.ref = "kotlin" }
 kotlinPluginSpring = { id = "org.jetbrains.kotlin.plugin.spring", version.ref = "kotlin" }
 testLogger = { id = "com.adarshr.test-logger", version.ref = "testLogger" }
 kotlinter = { id = "org.jmailen.kotlinter", version.ref = "kotlinter" }
diff --git a/sqs/build.gradle.kts b/sqs/build.gradle.kts
new file mode 100644
index 0000000..f946e31
--- /dev/null
+++ b/sqs/build.gradle.kts
@@ -0,0 +1,61 @@
+plugins {
+    alias(libs.plugins.kotlinJvm)
+    alias(libs.plugins.dependencyManagement)
+    alias(libs.plugins.kotlinter)
+    `java-library`
+}
+
+group = "io.github.hantsy"
+version = "0.1.0-SNAPSHOT"
+
+base { archivesName.set("awskotlin-spring-sqs") }
+
+repositories { mavenCentral() }
+
+dependencyManagement {
+    imports {
+        mavenBom("org.springframework.boot:spring-boot-dependencies:${libs.versions.springboot.get()}")
+    }
+}
+
+dependencies {
+    api(libs.awsSqs)
+    implementation(libs.kotlinStdlib)
+    implementation(libs.kotlinCoroutinesCore)
+    implementation(libs.springContext)
+    implementation(libs.springAop)
+    implementation(libs.kotlinReflect)
+    implementation(libs.jacksonDatabind)
+    implementation(libs.jacksonKotlin)
+    testImplementation(libs.springBootStarterTest)
+    testImplementation(libs.bundles.kotest)
+    testImplementation(libs.bundles.mockk)
+    testImplementation(libs.bundles.kotlintest)
+    testImplementation(libs.kotlinCoroutinesTest)
+    testRuntimeOnly(libs.junitPlatformLauncher)
+}
+
+kotlin { jvmToolchain(libs.versions.java.get().toInt()) }
+
+// ktlint 1.8.0 (applied by kotlinter) requires kotlin-compiler-embeddable 2.2.x.
+// A resolution rule in this build aligns Kotlin artifacts to Gradle's embedded 2.4.21,
+// which breaks ktlint ("Extensions storage is not registered"). Pin the compiler
+// artifacts for the lint classpath only; compilation itself still uses Kotlin 2.4.10.
+configurations.named("ktlint") {
+    resolutionStrategy.eachDependency {
+        if (requested.group == "org.jetbrains.kotlin" &&
+            requested.name in
+            setOf(
+                "kotlin-compiler-embeddable",
+                "kotlin-daemon-embeddable",
+                "kotlin-script-runtime",
+                "kotlin-build-tools-api",
+            )
+        ) {
+            useVersion("2.2.21")
+            because("ktlint 1.8.0 is not compatible with kotlin-compiler-embeddable 2.4.x")
+        }
+    }
+}
+
+tasks.withType<Test> { useJUnitPlatform() }
diff --git a/sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/listener/Acknowledge.kt b/sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/listener/Acknowledge.kt
new file mode 100644
index 0000000..658495b
--- /dev/null
+++ b/sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/listener/Acknowledge.kt
@@ -0,0 +1,31 @@
+package io.github.hantsy.awskotlin.sqs.listener
+
+import io.github.hantsy.awskotlin.sqs.SqsMessage
+import io.github.hantsy.awskotlin.sqs.SqsTemplate
+
+/**
+ * Manual acknowledgement handle passed to listener methods that declare an
+ * [Acknowledge] parameter. Bound to the received message and its queue.
+ */
+interface Acknowledge {
+    /** Deletes the message from the queue. */
+    suspend fun accept()
+
+    /** Makes the message immediately visible again (visibility timeout 0). */
+    suspend fun reject()
+}
+
+/** Default [Acknowledge] backed by the fluent template's queue-scoped operations. */
+internal class DefaultAcknowledge(
+    private val template: SqsTemplate,
+    private val queueUrl: String,
+    private val message: SqsMessage<*>
+) : Acknowledge {
+    override suspend fun accept() {
+        template.destination(queueUrl).acknowledge(message)
+    }
+
+    override suspend fun reject() {
+        template.destination(queueUrl).reject(message)
+    }
+}
diff --git a/sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/listener/SqsListener.kt b/sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/listener/SqsListener.kt
new file mode 100644
index 0000000..d4e47b6
--- /dev/null
+++ b/sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/listener/SqsListener.kt
@@ -0,0 +1,19 @@
+package io.github.hantsy.awskotlin.sqs.listener
+
+/**
+ * Marks a member function as an SQS message listener for the given standard [queues]
+ * (names or URLs).
+ *
+ * The method must declare exactly one payload parameter — either a plain payload
+ * type `T` or [io.github.hantsy.awskotlin.sqs.SqsMessage]`<T>` with a concrete `T` —
+ * and may optionally declare an [Acknowledge] parameter. Suspend and non-suspend
+ * methods are both supported.
+ *
+ * Without an [Acknowledge] parameter the message is deleted automatically after the
+ * method returns successfully; if the method throws, the message is left in flight.
+ * With an [Acknowledge] parameter the caller decides: [Acknowledge.accept] deletes,
+ * [Acknowledge.reject] makes the message visible again, and no decision leaves it.
+ */
+@Target(AnnotationTarget.FUNCTION)
+@Retention(AnnotationRetention.RUNTIME)
+annotation class SqsListener(vararg val queues: String)
diff --git a/sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/listener/SqsListenerAnnotationRegistrar.kt b/sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/listener/SqsListenerAnnotationRegistrar.kt
new file mode 100644
index 0000000..2fa81cf
--- /dev/null
+++ b/sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/listener/SqsListenerAnnotationRegistrar.kt
@@ -0,0 +1,111 @@
+package io.github.hantsy.awskotlin.sqs.listener
+
+import io.github.hantsy.awskotlin.sqs.SqsQueueResolver
+import io.github.hantsy.awskotlin.sqs.SqsTemplate
+import kotlinx.coroutines.CoroutineScope
+import kotlinx.coroutines.Dispatchers
+import kotlinx.coroutines.SupervisorJob
+import org.springframework.aop.framework.AopProxyUtils
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
+ * the context refresh. Annotations are discovered on the ultimate target class behind
+ * Spring AOP proxies, and singleton targets are invoked directly. [start] creates and
+ * starts one [SqsListenerContainer] per
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
+        // Proxy-aware discovery: CGLIB overrides drop method annotations and JDK proxies only
+        // expose interface methods, so scan the ultimate target class. When the proxy has a
+        // singleton target, invoke that target directly; otherwise fall back to the bean itself.
+        val endpointBean = AopProxyUtils.getSingletonTarget(bean) ?: bean
+        if (endpoints.any { it.bean === endpointBean }) return bean
+        val targetClass = AopProxyUtils.ultimateTargetClass(bean)
+        // Pre-filter with Java reflection: safe and cheap for any bean (proxies, lambdas, Java classes).
+        // declaredMethods is included so private annotated Kotlin listeners are not silently skipped.
+        val annotated = (targetClass.methods.asSequence() + targetClass.declaredMethods.asSequence())
+            .filter { !it.isBridge && !it.isSynthetic && it.isAnnotationPresent(SqsListener::class.java) }
+            .distinct()
+            .toList()
+        if (annotated.isEmpty()) return bean
+        val functions = targetClass.kotlin.memberFunctions
+        annotated.forEach { method ->
+            // Kotlin discovery is authoritative: kotlinFunction resolves internal name mangling
+            // (foo$module) and private members that plain name matching cannot map back.
+            val function = method.kotlinFunction
+                ?: functions.firstOrNull { it.matches(method) }
+                ?: functions.firstOrNull {
+                    it.name == method.name.substringBefore('$') && it.findAnnotation<SqsListener>() != null
+                }
+                ?: error("No Kotlin member function for annotated method ${method.name} on ${targetClass.name}")
+            val annotation = function.findAnnotation<SqsListener>() ?: method.getAnnotation(SqsListener::class.java)
+            endpoints += SqsListenerEndpoint(endpointBean, function, annotation.queues.toList())
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
diff --git a/sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/listener/SqsListenerConfig.kt b/sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/listener/SqsListenerConfig.kt
new file mode 100644
index 0000000..054013e
--- /dev/null
+++ b/sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/listener/SqsListenerConfig.kt
@@ -0,0 +1,19 @@
+package io.github.hantsy.awskotlin.sqs.listener
+
+import kotlin.time.Duration
+import kotlin.time.Duration.Companion.seconds
+
+/**
+ * Runtime settings shared by listener containers.
+ *
+ * [maxConcurrentMessages] caps in-flight messages per container across all its
+ * queues; [pollTime] is the long-polling wait per receive (clamped to 0..20 seconds
+ * by the template); [shutdownTimeout] bounds how long stop waits for in-flight
+ * message processing before cancelling it.
+ */
+data class SqsListenerConfig(
+    val autoStartup: Boolean = true,
+    val maxConcurrentMessages: Int = 10,
+    val pollTime: Duration = 10.seconds,
+    val shutdownTimeout: Duration = 20.seconds
+)
diff --git a/sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/listener/SqsListenerContainer.kt b/sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/listener/SqsListenerContainer.kt
new file mode 100644
index 0000000..39932ea
--- /dev/null
+++ b/sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/listener/SqsListenerContainer.kt
@@ -0,0 +1,154 @@
+package io.github.hantsy.awskotlin.sqs.listener
+
+import io.github.hantsy.awskotlin.sqs.SqsAcknowledgementMode
+import io.github.hantsy.awskotlin.sqs.SqsMessage
+import io.github.hantsy.awskotlin.sqs.SqsQueueResolver
+import io.github.hantsy.awskotlin.sqs.SqsTemplate
+import kotlinx.coroutines.CancellationException
+import kotlinx.coroutines.CoroutineScope
+import kotlinx.coroutines.Dispatchers
+import kotlinx.coroutines.Job
+import kotlinx.coroutines.SupervisorJob
+import kotlinx.coroutines.cancel
+import kotlinx.coroutines.delay
+import kotlinx.coroutines.launch
+import kotlinx.coroutines.runBlocking
+import kotlinx.coroutines.sync.Semaphore
+import kotlinx.coroutines.withTimeoutOrNull
+import kotlinx.coroutines.yield
+import org.apache.commons.logging.LogFactory
+import org.springframework.context.SmartLifecycle
+import kotlin.time.Duration.Companion.seconds
+
+/**
+ * Polls the endpoint's queues and invokes the listener method for each message.
+ *
+ * Standard queues only. [start] resolves every queue URL up front — missing queues
+ * ([io.github.hantsy.awskotlin.sqs.SqsQueueNotFoundException]) and FIFO queues
+ * ([io.github.hantsy.awskotlin.sqs.SqsOperationException]) fail startup; nothing is
+ * created implicitly. Each queue gets one long-polling loop in the provided [scope]
+ * under a child [SupervisorJob], so [stop] cancels container work without cancelling
+ * the external scope. A shared [Semaphore] caps in-flight messages at
+ * [SqsListenerConfig.maxConcurrentMessages] across all queues.
+ *
+ * Messages are received in [SqsAcknowledgementMode.MANUAL]; the container deletes
+ * after successful invocation only when the method has no [Acknowledge] parameter.
+ */
+class SqsListenerContainer(
+    private val endpoint: SqsListenerEndpoint,
+    private val template: SqsTemplate,
+    private val queueResolver: SqsQueueResolver,
+    private val config: SqsListenerConfig = SqsListenerConfig(),
+    private val scope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
+) : SmartLifecycle {
+    private val log = LogFactory.getLog(SqsListenerContainer::class.java)
+    private val method = SqsListenerMethod(endpoint.method)
+
+    @Volatile
+    private var running = false
+    private var containerJob: Job? = null
+    private var pollingJob: Job? = null
+    private var processingJob: Job? = null
+
+    init {
+        require(endpoint.queues.isNotEmpty()) { "SqsListenerEndpoint must declare at least one queue" }
+        require(config.maxConcurrentMessages > 0) {
+            "maxConcurrentMessages must be positive, got ${config.maxConcurrentMessages}"
+        }
+    }
+
+    override fun isAutoStartup(): Boolean = config.autoStartup
+
+    override fun isRunning(): Boolean = running
+
+    @Synchronized
+    override fun start() {
+        if (running) return
+        // Resolve every queue before polling; failures propagate and leave the container stopped.
+        val queueUrls = runBlocking { endpoint.queues.map { queueResolver.resolve(it) } }
+        val parent = SupervisorJob(scope.coroutineContext[Job])
+        val polling = SupervisorJob(parent)
+        val processing = SupervisorJob(parent)
+        containerJob = parent
+        pollingJob = polling
+        processingJob = processing
+        val semaphore = Semaphore(config.maxConcurrentMessages)
+        val pollingScope = CoroutineScope(scope.coroutineContext + polling)
+        val processingScope = CoroutineScope(scope.coroutineContext + processing)
+        queueUrls.forEach { queueUrl -> pollingScope.launch { pollLoop(queueUrl, semaphore, processingScope) } }
+        running = true
+    }
+
+    @Synchronized
+    override fun stop() {
+        if (!running) return
+        running = false
+        val parent = containerJob
+        val polling = pollingJob
+        val processing = processingJob
+        containerJob = null
+        pollingJob = null
+        processingJob = null
+        polling?.cancel()
+        if (processing != null) {
+            // Give in-flight processing a chance to finish before cancelling it.
+            runBlocking {
+                withTimeoutOrNull(config.shutdownTimeout) {
+                    processing.children.toList().forEach { it.join() }
+                }
+            }
+        }
+        parent?.cancel()
+    }
+
+    private suspend fun pollLoop(queueUrl: String, semaphore: Semaphore, processingScope: CoroutineScope) {
+        var backoff = INITIAL_BACKOFF
+        while (true) {
+            semaphore.acquire()
+            val message = try {
+                template.destination(queueUrl)
+                    .receive(method.payloadType, config.pollTime, SqsAcknowledgementMode.MANUAL)
+            } catch (e: CancellationException) {
+                semaphore.release()
+                throw e
+            } catch (e: Exception) {
+                semaphore.release()
+                log.warn("Receive failed on $queueUrl; retrying in $backoff", e)
+                delay(backoff)
+                backoff = (backoff * 2).coerceAtMost(MAX_BACKOFF)
+                continue
+            }
+            backoff = INITIAL_BACKOFF
+            if (message == null) {
+                semaphore.release()
+                yield()
+                continue
+            }
+            processingScope.launch {
+                try {
+                    process(queueUrl, message)
+                } finally {
+                    semaphore.release()
+                }
+            }
+        }
+    }
+
+    private suspend fun process(queueUrl: String, message: SqsMessage<Any>) {
+        try {
+            method.invoke(endpoint.bean, message, DefaultAcknowledge(template, queueUrl, message))
+            if (!method.hasAcknowledgeParameter) {
+                template.destination(queueUrl).acknowledge(message)
+            }
+        } catch (e: CancellationException) {
+            throw e
+        } catch (e: Exception) {
+            log.error("Listener ${endpoint.method.name} failed for message ${message.messageId} on $queueUrl", e)
+        }
+    }
+
+    private companion object {
+        val INITIAL_BACKOFF = 1.seconds
+        val MAX_BACKOFF = 30.seconds
+    }
+}
diff --git a/sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/listener/SqsListenerEndpoint.kt b/sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/listener/SqsListenerEndpoint.kt
new file mode 100644
index 0000000..e1eeb67
--- /dev/null
+++ b/sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/listener/SqsListenerEndpoint.kt
@@ -0,0 +1,9 @@
+package io.github.hantsy.awskotlin.sqs.listener
+
+import kotlin.reflect.KFunction
+
+/**
+ * A discovered listener: the target [bean], its annotated [method] and the
+ * standard [queues] (names or URLs) it consumes.
+ */
+data class SqsListenerEndpoint(val bean: Any, val method: KFunction<*>, val queues: List<String>)
diff --git a/sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/listener/SqsListenerMethod.kt b/sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/listener/SqsListenerMethod.kt
new file mode 100644
index 0000000..6649d0b
--- /dev/null
+++ b/sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/listener/SqsListenerMethod.kt
@@ -0,0 +1,96 @@
+package io.github.hantsy.awskotlin.sqs.listener
+
+import io.github.hantsy.awskotlin.sqs.SqsMessage
+import kotlin.reflect.KClass
+import kotlin.reflect.KFunction
+import kotlin.reflect.KParameter
+import kotlin.reflect.full.callSuspendBy
+import kotlin.reflect.jvm.isAccessible
+
+/**
+ * Validated reflective view of a listener method.
+ *
+ * Enforces the endpoint contract: exactly one payload-bearing parameter (plain `T`
+ * or `SqsMessage<T>` with a concrete `T`), at most one [Acknowledge] parameter and
+ * no unsupported parameters. Invalid methods fail with [IllegalArgumentException].
+ */
+internal class SqsListenerMethod(private val function: KFunction<*>) {
+    private enum class Role { INSTANCE, MESSAGE, PAYLOAD, ACKNOWLEDGE }
+
+    private val roles: Map<KParameter, Role>
+    val payloadType: Class<Any>
+    val hasAcknowledgeParameter: Boolean
+
+    init {
+        val roles = mutableMapOf<KParameter, Role>()
+        var payloadClass: KClass<*>? = null
+        var payloadCount = 0
+        var acknowledgeCount = 0
+        for (parameter in function.parameters) {
+            when (parameter.kind) {
+                KParameter.Kind.INSTANCE -> roles[parameter] = Role.INSTANCE
+
+                KParameter.Kind.VALUE -> when (val classifier = parameter.type.classifier) {
+                    Acknowledge::class -> {
+                        roles[parameter] = Role.ACKNOWLEDGE
+                        acknowledgeCount++
+                    }
+
+                    SqsMessage::class -> {
+                        val argument = parameter.type.arguments.singleOrNull()?.type?.classifier as? KClass<*>
+                        require(argument != null) {
+                            "SqsMessage parameter of ${function.name} must have a concrete payload type, " +
+                                "not a star projection"
+                        }
+                        roles[parameter] = Role.MESSAGE
+                        payloadClass = argument
+                        payloadCount++
+                    }
+
+                    is KClass<*> -> {
+                        roles[parameter] = Role.PAYLOAD
+                        payloadClass = classifier
+                        payloadCount++
+                    }
+
+                    else -> throw IllegalArgumentException(
+                        "Unsupported parameter ${parameter.name} of listener method ${function.name}"
+                    )
+                }
+
+                else -> throw IllegalArgumentException(
+                    "Unsupported parameter ${parameter.name} of listener method ${function.name}"
+                )
+            }
+        }
+        require(roles.values.count { it == Role.INSTANCE } == 1) {
+            "Listener method ${function.name} must be a member function"
+        }
+        require(payloadCount == 1) {
+            "Listener method ${function.name} must declare exactly one payload parameter, got $payloadCount"
+        }
+        require(acknowledgeCount <= 1) {
+            "Listener method ${function.name} must declare at most one Acknowledge parameter"
+        }
+        this.roles = roles
+
+        @Suppress("UNCHECKED_CAST")
+        payloadType = requireNotNull(payloadClass).java as Class<Any>
+        hasAcknowledgeParameter = acknowledgeCount == 1
+    }
+
+    /** Invokes the method, resolving each parameter by its validated role. */
+    suspend fun invoke(bean: Any, message: SqsMessage<*>, acknowledge: Acknowledge) {
+        val arguments = function.parameters.associateWith { parameter ->
+            when (roles[parameter]) {
+                Role.INSTANCE -> bean
+                Role.MESSAGE -> message
+                Role.PAYLOAD -> message.payload
+                Role.ACKNOWLEDGE -> acknowledge
+                null -> error("No role for parameter ${parameter.name}")
+            }
+        }
+        function.isAccessible = true
+        if (function.isSuspend) function.callSuspendBy(arguments) else function.callBy(arguments)
+    }
+}
diff --git a/starters/starter-sqs/build.gradle.kts b/starters/starter-sqs/build.gradle.kts
new file mode 100644
index 0000000..cd1c16f
--- /dev/null
+++ b/starters/starter-sqs/build.gradle.kts
@@ -0,0 +1,77 @@
+plugins {
+    alias(libs.plugins.kotlinJvm)
+    alias(libs.plugins.dependencyManagement)
+    alias(libs.plugins.kotlinter)
+    // kapt ships inside kotlin-gradle-plugin (already on the classpath via the root build),
+    // so it must be applied without an explicit version.
+    id("org.jetbrains.kotlin.kapt")
+    `java-library`
+}
+
+group = "io.github.hantsy"
+version = "0.1.0-SNAPSHOT"
+
+base { archivesName.set("awskotlin-spring-starter-sqs") }
+
+repositories { mavenCentral() }
+
+dependencyManagement {
+    imports {
+        mavenBom("org.springframework.boot:spring-boot-dependencies:${libs.versions.springboot.get()}")
+    }
+}
+
+dependencies {
+    api(project(":sqs"))
+    api(project(":core"))
+    implementation(libs.springBootAutoconfigure)
+    implementation(libs.kotlinStdlib)
+    kapt(libs.springBootConfigurationProcessor)
+    testImplementation(libs.springBootStarterTest)
+    testImplementation(libs.bundles.kotest)
+    testImplementation(libs.bundles.mockk)
+    testImplementation(libs.bundles.kotlintest)
+    testImplementation(libs.kotlinCoroutinesCore)
+    testImplementation(libs.bundles.testcontainers)
+    testImplementation(libs.bundles.awaitility)
+    testRuntimeOnly(libs.junitPlatformLauncher)
+}
+
+kotlin { jvmToolchain(libs.versions.java.get().toInt()) }
+
+// kapt's CLASS_OUTPUT layout defeats the processor's Gradle resources-directory heuristic,
+// so point it at the source resources for additional-spring-configuration-metadata.json.
+// The additional file documents the constructor-bound properties that the processor cannot
+// infer from Kotlin stubs (val properties have no setters; the synthetic no-arg constructor
+// generated for all-default parameters breaks single-constructor deduction).
+kapt {
+    arguments {
+        arg(
+            "org.springframework.boot.configurationprocessor.additionalMetadataLocations",
+            "${projectDir}/src/main/resources",
+        )
+    }
+}
+
+// ktlint 1.8.0 (applied by kotlinter) requires kotlin-compiler-embeddable 2.2.x.
+// A resolution rule in this build aligns Kotlin artifacts to Gradle's embedded 2.4.21,
+// which breaks ktlint ("Extensions storage is not registered"). Pin the compiler
+// artifacts for the lint classpath only; compilation itself still uses Kotlin 2.4.10.
+configurations.named("ktlint") {
+    resolutionStrategy.eachDependency {
+        if (requested.group == "org.jetbrains.kotlin" &&
+            requested.name in
+            setOf(
+                "kotlin-compiler-embeddable",
+                "kotlin-daemon-embeddable",
+                "kotlin-script-runtime",
+                "kotlin-build-tools-api",
+            )
+        ) {
+            useVersion("2.2.21")
+            because("ktlint 1.8.0 is not compatible with kotlin-compiler-embeddable 2.4.x")
+        }
+    }
+}
+
+tasks.withType<Test> { useJUnitPlatform() }
diff --git a/starters/starter-sqs/gradle.properties b/starters/starter-sqs/gradle.properties
new file mode 100644
index 0000000..40754e7
--- /dev/null
+++ b/starters/starter-sqs/gradle.properties
@@ -0,0 +1,5 @@
+# Workaround for KGP 2.4.10 + Gradle 9.9.2: Gradle ships Kotlin 2.4.21 compiler classes
+# in its parent classloader, which breaks the Build Tools API path used by kapt stub
+# generation (NoSuchMethodError: ArgumentsToStrings.toArgumentStrings$default).
+# The legacy compiler runner avoids that path. Deprecated in KGP 2.4.x, removed in 2.5.0.
+kotlin.compiler.runViaBuildToolsApi=false
diff --git a/starters/starter-sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/autoconfigure/KotlinDurationConverter.kt b/starters/starter-sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/autoconfigure/KotlinDurationConverter.kt
new file mode 100644
index 0000000..8b1e5ea
--- /dev/null
+++ b/starters/starter-sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/autoconfigure/KotlinDurationConverter.kt
@@ -0,0 +1,43 @@
+package io.github.hantsy.awskotlin.sqs.autoconfigure
+
+import org.springframework.core.convert.TypeDescriptor
+import org.springframework.core.convert.converter.ConditionalGenericConverter
+import org.springframework.core.convert.converter.GenericConverter
+import kotlin.time.Duration
+
+/**
+ * Marks a [Duration] constructor parameter for configuration property binding.
+ *
+ * Spring Boot's value-object binder erases non-null Kotlin value class parameters to their
+ * underlying JVM type (`long` for [Duration]), so a plain `Converter<String, Duration>` is
+ * never consulted for them. [KotlinDurationConverter] matches on this annotation instead
+ * and supplies the boxed [Duration] that Kotlin constructor invocation expects.
+ */
+@Target(AnnotationTarget.VALUE_PARAMETER)
+@Retention(AnnotationRetention.RUNTIME)
+annotation class KotlinDuration
+
+/**
+ * Converts duration strings such as `10s`, `500ms` or ISO-8601 `PT15S` to [Duration]
+ * for constructor-bound configuration properties annotated with [KotlinDuration].
+ */
+class KotlinDurationConverter : ConditionalGenericConverter {
+    override fun getConvertibleTypes(): Set<GenericConverter.ConvertiblePair> = setOf(
+        // The binder targets the erased primitive `long`; converter lookup resolves
+        // primitives to wrappers, so the pairs must use java.lang.Long (javaObjectType).
+        GenericConverter.ConvertiblePair(String::class.java, Long::class.javaObjectType),
+        // Binder.handleBindResult re-converts the bound value to the declared target
+        // type, so the boxed Duration must also be accepted for the erased long target.
+        GenericConverter.ConvertiblePair(Duration::class.java, Long::class.javaObjectType),
+        GenericConverter.ConvertiblePair(String::class.java, Duration::class.java),
+    )
+
+    override fun matches(sourceType: TypeDescriptor, targetType: TypeDescriptor,): Boolean =
+        targetType.hasAnnotation(KotlinDuration::class.java) || targetType.type == Duration::class.java
+
+    override fun convert(source: Any?, sourceType: TypeDescriptor, targetType: TypeDescriptor,): Any? = when (source) {
+        null -> null
+        is Duration -> source
+        else -> Duration.parse(source.toString().trim())
+    }
+}
diff --git a/starters/starter-sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/autoconfigure/SqsAutoConfiguration.kt b/starters/starter-sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/autoconfigure/SqsAutoConfiguration.kt
new file mode 100644
index 0000000..5b30f96
--- /dev/null
+++ b/starters/starter-sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/autoconfigure/SqsAutoConfiguration.kt
@@ -0,0 +1,98 @@
+package io.github.hantsy.awskotlin.sqs.autoconfigure
+
+import aws.sdk.kotlin.services.sqs.SqsClient
+import aws.smithy.kotlin.runtime.auth.awscredentials.CredentialsProvider
+import aws.smithy.kotlin.runtime.net.url.Url
+import io.github.hantsy.awskotlin.core.AwsKotlinProperties
+import io.github.hantsy.awskotlin.core.autoconfigure.AwsKotlinAutoConfiguration
+import io.github.hantsy.awskotlin.sqs.JacksonSqsMessageConverter
+import io.github.hantsy.awskotlin.sqs.SqsMessageConverter
+import io.github.hantsy.awskotlin.sqs.SqsQueueResolver
+import io.github.hantsy.awskotlin.sqs.SqsTemplate
+import io.github.hantsy.awskotlin.sqs.listener.SqsListenerAnnotationRegistrar
+import io.github.hantsy.awskotlin.sqs.listener.SqsListenerConfig
+import org.springframework.boot.autoconfigure.AutoConfiguration
+import org.springframework.boot.autoconfigure.condition.ConditionalOnClass
+import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
+import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
+import org.springframework.boot.context.properties.ConfigurationPropertiesBinding
+import org.springframework.boot.context.properties.EnableConfigurationProperties
+import org.springframework.context.annotation.Bean
+
+/**
+ * Auto-configuration for the AWS SDK for Kotlin SQS client, [SqsTemplate] and the
+ * [SqsListenerAnnotationRegistrar] that runs `@SqsListener` methods.
+ *
+ * Disabled by either `awskotlin.enabled=false` or `awskotlin.sqs.enabled=false`
+ * (both checked by the single property condition below). User-provided
+ * [SqsClient]/[SqsMessageConverter]/[SqsQueueResolver]/[SqsTemplate]/
+ * [SqsListenerAnnotationRegistrar] beans always win.
+ */
+@AutoConfiguration(after = [AwsKotlinAutoConfiguration::class])
+@ConditionalOnClass(SqsClient::class)
+@ConditionalOnProperty(
+    prefix = "awskotlin",
+    name = ["enabled", "sqs.enabled"],
+    havingValue = "true",
+    matchIfMissing = true,
+)
+@EnableConfigurationProperties(SqsProperties::class)
+class SqsAutoConfiguration {
+    // Boot's binder erases non-null kotlin.time.Duration (a value class) constructor
+    // parameters to long, so binding needs this annotation-scoped converter rather than
+    // a plain Converter<String, Duration>. See KotlinDurationConverter.
+    @Bean
+    @ConfigurationPropertiesBinding
+    fun kotlinDurationConverter(): KotlinDurationConverter = KotlinDurationConverter()
+
+    @Bean
+    @ConditionalOnMissingBean
+    fun sqsClient(
+        properties: AwsKotlinProperties,
+        sqsProperties: SqsProperties,
+        credentialsProvider: CredentialsProvider,
+    ): SqsClient {
+        val endpoint = sqsProperties.endpoint ?: properties.endpoint
+        return SqsClient {
+            region = sqsProperties.region ?: properties.region
+            this.credentialsProvider = credentialsProvider
+            endpoint?.let { endpointUrl = Url.parse(it) }
+        }
+    }
+
+    @Bean
+    @ConditionalOnMissingBean
+    fun sqsMessageConverter(): SqsMessageConverter = JacksonSqsMessageConverter()
+
+    @Bean
+    @ConditionalOnMissingBean
+    fun sqsQueueResolver(client: SqsClient): SqsQueueResolver = SqsQueueResolver(client)
+
+    @Bean
+    @ConditionalOnMissingBean
+    fun sqsTemplate(client: SqsClient, converter: SqsMessageConverter, queueResolver: SqsQueueResolver,): SqsTemplate =
+        SqsTemplate(client, converter, queueResolver)
+
+    companion object {
+        // Static (companion + @JvmStatic) so registering this BeanPostProcessor/SmartLifecycle
+        // does not force early instantiation of the auto-configuration class itself.
+        // autoStartup=false prevents automatic polling; the registrar can still be started manually.
+        @Bean
+        @ConditionalOnMissingBean
+        @JvmStatic
+        fun sqsListenerAnnotationRegistrar(
+            template: SqsTemplate,
+            queueResolver: SqsQueueResolver,
+            properties: SqsProperties,
+        ): SqsListenerAnnotationRegistrar = SqsListenerAnnotationRegistrar(
+            template = template,
+            queueResolver = queueResolver,
+            config = SqsListenerConfig(
+                autoStartup = properties.listener.autoStartup,
+                maxConcurrentMessages = properties.listener.maxConcurrentMessages,
+                pollTime = properties.listener.pollTime,
+                shutdownTimeout = properties.listener.shutdownTimeout,
+            ),
+        )
+    }
+}
diff --git a/starters/starter-sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/autoconfigure/SqsProperties.kt b/starters/starter-sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/autoconfigure/SqsProperties.kt
new file mode 100644
index 0000000..c83e2c4
--- /dev/null
+++ b/starters/starter-sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/autoconfigure/SqsProperties.kt
@@ -0,0 +1,27 @@
+package io.github.hantsy.awskotlin.sqs.autoconfigure
+
+import org.springframework.boot.context.properties.ConfigurationProperties
+import kotlin.time.Duration
+import kotlin.time.Duration.Companion.seconds
+
+/**
+ * Configuration properties for the SQS module.
+ *
+ * Unset values fall back to the shared `awskotlin.*` properties and then to the
+ * SDK default chains. Listener settings are consumed by the listener runtime;
+ * values are not validated here.
+ */
+@ConfigurationProperties(prefix = "awskotlin.sqs")
+data class SqsProperties(
+    val enabled: Boolean = true,
+    val endpoint: String? = null,
+    val region: String? = null,
+    val listener: Listener = Listener(),
+) {
+    data class Listener(
+        val autoStartup: Boolean = true,
+        val maxConcurrentMessages: Int = 10,
+        @KotlinDuration val pollTime: Duration = 10.seconds,
+        @KotlinDuration val shutdownTimeout: Duration = 20.seconds,
+    )
+}
diff --git a/starters/starter-sqs/src/main/resources/META-INF/additional-spring-configuration-metadata.json b/starters/starter-sqs/src/main/resources/META-INF/additional-spring-configuration-metadata.json
new file mode 100644
index 0000000..26cabfb
--- /dev/null
+++ b/starters/starter-sqs/src/main/resources/META-INF/additional-spring-configuration-metadata.json
@@ -0,0 +1,57 @@
+{
+  "properties": [
+    {
+      "name": "awskotlin.sqs.enabled",
+      "type": "java.lang.Boolean",
+      "description": "Whether SQS auto-configuration is enabled.",
+      "sourceType": "io.github.hantsy.awskotlin.sqs.autoconfigure.SqsProperties",
+      "defaultValue": true
+    },
+    {
+      "name": "awskotlin.sqs.endpoint",
+      "type": "java.lang.String",
+      "description": "Override the SQS endpoint URL (for example, a LocalStack endpoint). Falls back to awskotlin.endpoint.",
+      "sourceType": "io.github.hantsy.awskotlin.sqs.autoconfigure.SqsProperties"
+    },
+    {
+      "name": "awskotlin.sqs.region",
+      "type": "java.lang.String",
+      "description": "Override the SQS region. Falls back to awskotlin.region, then the SDK default region chain.",
+      "sourceType": "io.github.hantsy.awskotlin.sqs.autoconfigure.SqsProperties"
+    },
+    {
+      "name": "awskotlin.sqs.listener",
+      "type": "io.github.hantsy.awskotlin.sqs.autoconfigure.SqsProperties$Listener",
+      "description": "Listener container settings.",
+      "sourceType": "io.github.hantsy.awskotlin.sqs.autoconfigure.SqsProperties"
+    },
+    {
+      "name": "awskotlin.sqs.listener.auto-startup",
+      "type": "java.lang.Boolean",
+      "description": "Whether listener containers start automatically with the application context.",
+      "sourceType": "io.github.hantsy.awskotlin.sqs.autoconfigure.SqsProperties$Listener",
+      "defaultValue": true
+    },
+    {
+      "name": "awskotlin.sqs.listener.max-concurrent-messages",
+      "type": "java.lang.Integer",
+      "description": "Maximum number of messages processed concurrently per listener.",
+      "sourceType": "io.github.hantsy.awskotlin.sqs.autoconfigure.SqsProperties$Listener",
+      "defaultValue": 10
+    },
+    {
+      "name": "awskotlin.sqs.listener.poll-time",
+      "type": "kotlin.time.Duration",
+      "description": "Long-poll wait time used when receiving messages. Accepts Kotlin duration format (10s, 500ms) or ISO-8601 (PT10S).",
+      "sourceType": "io.github.hantsy.awskotlin.sqs.autoconfigure.SqsProperties$Listener",
+      "defaultValue": "10s"
+    },
+    {
+      "name": "awskotlin.sqs.listener.shutdown-timeout",
+      "type": "kotlin.time.Duration",
+      "description": "Time to wait for in-flight messages to complete on shutdown. Accepts Kotlin duration format (20s, 500ms) or ISO-8601 (PT20S).",
+      "sourceType": "io.github.hantsy.awskotlin.sqs.autoconfigure.SqsProperties$Listener",
+      "defaultValue": "20s"
+    }
+  ]
+}
diff --git a/starters/starter-sqs/src/main/resources/META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports b/starters/starter-sqs/src/main/resources/META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports
new file mode 100644
index 0000000..36e1d95
--- /dev/null
+++ b/starters/starter-sqs/src/main/resources/META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports
@@ -0,0 +1 @@
+io.github.hantsy.awskotlin.sqs.autoconfigure.SqsAutoConfiguration
diff --git a/starters/starter-sqs/src/test/kotlin/io/github/hantsy/awskotlin/sqs/autoconfigure/SqsAutoConfigurationTest.kt b/starters/starter-sqs/src/test/kotlin/io/github/hantsy/awskotlin/sqs/autoconfigure/SqsAutoConfigurationTest.kt
new file mode 100644
index 0000000..857e1e8
--- /dev/null
+++ b/starters/starter-sqs/src/test/kotlin/io/github/hantsy/awskotlin/sqs/autoconfigure/SqsAutoConfigurationTest.kt
@@ -0,0 +1,286 @@
+package io.github.hantsy.awskotlin.sqs.autoconfigure
+
+import aws.sdk.kotlin.runtime.auth.credentials.StaticCredentialsProvider
+import aws.sdk.kotlin.services.sqs.SqsClient
+import io.github.hantsy.awskotlin.core.autoconfigure.AwsKotlinAutoConfiguration
+import io.github.hantsy.awskotlin.sqs.JacksonSqsMessageConverter
+import io.github.hantsy.awskotlin.sqs.SqsMessageConverter
+import io.github.hantsy.awskotlin.sqs.SqsQueueResolver
+import io.github.hantsy.awskotlin.sqs.SqsTemplate
+import io.github.hantsy.awskotlin.sqs.listener.SqsListenerAnnotationRegistrar
+import io.github.hantsy.awskotlin.sqs.listener.SqsListenerConfig
+import io.kotest.matchers.shouldBe
+import io.kotest.matchers.types.shouldBeInstanceOf
+import io.kotest.matchers.types.shouldBeSameInstanceAs
+import io.mockk.mockk
+import org.junit.jupiter.api.Test
+import org.springframework.boot.autoconfigure.AutoConfigurations
+import org.springframework.boot.test.context.runner.ApplicationContextRunner
+import org.springframework.context.annotation.Bean
+import org.springframework.context.annotation.Configuration
+import kotlin.time.Duration.Companion.seconds
+
+class SqsAutoConfigurationTest {
+    private val runner =
+        ApplicationContextRunner().withConfiguration(
+            AutoConfigurations.of(AwsKotlinAutoConfiguration::class.java, SqsAutoConfiguration::class.java),
+        )
+
+    @Test
+    fun `default context provides SQS beans with static credentials and region`() {
+        runner.withPropertyValues(
+            "awskotlin.region=us-east-1",
+            "awskotlin.access-key-id=test-access-key",
+            "awskotlin.secret-access-key=test-secret-key",
+        ).run { context ->
+            context.getBeanNamesForType(SqsClient::class.java).size shouldBe 1
+            context.getBeanNamesForType(SqsMessageConverter::class.java).size shouldBe 1
+            context.getBeanNamesForType(SqsQueueResolver::class.java).size shouldBe 1
+            context.getBeanNamesForType(SqsTemplate::class.java).size shouldBe 1
+            context.getBeanNamesForType(SqsProperties::class.java).size shouldBe 1
+            context.getBeanNamesForType(SqsListenerAnnotationRegistrar::class.java).size shouldBe 1
+            val client = context.getBean(SqsClient::class.java)
+            client.config.region shouldBe "us-east-1"
+            client.config.credentialsProvider.shouldBeInstanceOf<StaticCredentialsProvider>()
+            context.getBean(SqsMessageConverter::class.java).shouldBeInstanceOf<JacksonSqsMessageConverter>()
+        }
+    }
+
+    @Test
+    fun `awskotlin enabled=false removes the beans`() {
+        runner.withPropertyValues(
+            "awskotlin.region=us-east-1",
+            "awskotlin.enabled=false",
+        ).run { context ->
+            context.getBeanNamesForType(SqsClient::class.java).isEmpty() shouldBe true
+            context.getBeanNamesForType(SqsMessageConverter::class.java).isEmpty() shouldBe true
+            context.getBeanNamesForType(SqsQueueResolver::class.java).isEmpty() shouldBe true
+            context.getBeanNamesForType(SqsTemplate::class.java).isEmpty() shouldBe true
+            context.getBeanNamesForType(SqsProperties::class.java).isEmpty() shouldBe true
+            context.getBeanNamesForType(SqsListenerAnnotationRegistrar::class.java).isEmpty() shouldBe true
+        }
+    }
+
+    @Test
+    fun `awskotlin sqs enabled=false removes the beans`() {
+        runner.withPropertyValues(
+            "awskotlin.region=us-east-1",
+            "awskotlin.sqs.enabled=false",
+        ).run { context ->
+            context.getBeanNamesForType(SqsClient::class.java).isEmpty() shouldBe true
+            context.getBeanNamesForType(SqsMessageConverter::class.java).isEmpty() shouldBe true
+            context.getBeanNamesForType(SqsQueueResolver::class.java).isEmpty() shouldBe true
+            context.getBeanNamesForType(SqsTemplate::class.java).isEmpty() shouldBe true
+            context.getBeanNamesForType(SqsProperties::class.java).isEmpty() shouldBe true
+            context.getBeanNamesForType(SqsListenerAnnotationRegistrar::class.java).isEmpty() shouldBe true
+        }
+    }
+
+    @Test
+    fun `user SqsClient bean wins`() {
+        runner.withPropertyValues(
+            "awskotlin.region=us-east-1",
+        ).withUserConfiguration(UserSqsClientConfiguration::class.java).run { context ->
+            context.getBeanNamesForType(SqsClient::class.java).size shouldBe 1
+            val userBean = context.getBean("userSqsClient")
+            context.getBean(SqsClient::class.java) shouldBeSameInstanceAs userBean
+            context.getBeanNamesForType(SqsTemplate::class.java).size shouldBe 1
+        }
+    }
+
+    @Test
+    fun `user SqsMessageConverter bean wins`() {
+        runner.withPropertyValues(
+            "awskotlin.region=us-east-1",
+        ).withUserConfiguration(UserSqsMessageConverterConfiguration::class.java).run { context ->
+            context.getBeanNamesForType(SqsMessageConverter::class.java).size shouldBe 1
+            val userBean = context.getBean("userSqsMessageConverter")
+            context.getBean(SqsMessageConverter::class.java) shouldBeSameInstanceAs userBean
+            context.getBeanNamesForType(SqsTemplate::class.java).size shouldBe 1
+        }
+    }
+
+    @Test
+    fun `user SqsQueueResolver bean wins`() {
+        runner.withPropertyValues(
+            "awskotlin.region=us-east-1",
+        ).withUserConfiguration(UserSqsQueueResolverConfiguration::class.java).run { context ->
+            context.getBeanNamesForType(SqsQueueResolver::class.java).size shouldBe 1
+            val userBean = context.getBean("userSqsQueueResolver")
+            context.getBean(SqsQueueResolver::class.java) shouldBeSameInstanceAs userBean
+            context.getBeanNamesForType(SqsTemplate::class.java).size shouldBe 1
+        }
+    }
+
+    @Test
+    fun `user SqsTemplate bean wins`() {
+        runner.withPropertyValues(
+            "awskotlin.region=us-east-1",
+        ).withUserConfiguration(UserSqsTemplateConfiguration::class.java).run { context ->
+            context.getBeanNamesForType(SqsTemplate::class.java).size shouldBe 1
+            val userBean = context.getBean("userSqsTemplate")
+            context.getBean(SqsTemplate::class.java) shouldBeSameInstanceAs userBean
+        }
+    }
+
+    @Test
+    fun `sqs region and endpoint override the global values`() {
+        runner.withPropertyValues(
+            "awskotlin.region=us-east-1",
+            "awskotlin.endpoint=http://global:4566",
+            "awskotlin.sqs.region=eu-west-1",
+            "awskotlin.sqs.endpoint=http://localhost:4566",
+            "awskotlin.access-key-id=test-access-key",
+            "awskotlin.secret-access-key=test-secret-key",
+        ).run { context ->
+            val config = context.getBean(SqsClient::class.java).config
+            config.region shouldBe "eu-west-1"
+            config.endpointUrl?.toString() shouldBe "http://localhost:4566"
+            val properties = context.getBean(SqsProperties::class.java)
+            properties.region shouldBe "eu-west-1"
+            properties.endpoint shouldBe "http://localhost:4566"
+        }
+    }
+
+    @Test
+    fun `global endpoint override is used when sqs endpoint is unset`() {
+        runner.withPropertyValues(
+            "awskotlin.region=us-east-1",
+            "awskotlin.endpoint=http://localhost:4566",
+            "awskotlin.access-key-id=test-access-key",
+            "awskotlin.secret-access-key=test-secret-key",
+        ).run { context ->
+            context.getBean(SqsClient::class.java).config.endpointUrl?.toString() shouldBe "http://localhost:4566"
+        }
+    }
+
+    @Test
+    fun `listener properties have sensible defaults`() {
+        runner.withPropertyValues(
+            "awskotlin.region=us-east-1",
+        ).run { context ->
+            val listener = context.getBean(SqsProperties::class.java).listener
+            listener.autoStartup shouldBe true
+            listener.maxConcurrentMessages shouldBe 10
+            listener.pollTime shouldBe 10.seconds
+            listener.shutdownTimeout shouldBe 20.seconds
+        }
+    }
+
+    @Test
+    fun `custom listener values bind`() {
+        runner.withPropertyValues(
+            "awskotlin.region=us-east-1",
+            "awskotlin.sqs.listener.auto-startup=false",
+            "awskotlin.sqs.listener.max-concurrent-messages=5",
+            "awskotlin.sqs.listener.poll-time=5s",
+            "awskotlin.sqs.listener.shutdown-timeout=30s",
+        ).run { context ->
+            val listener = context.getBean(SqsProperties::class.java).listener
+            listener.autoStartup shouldBe false
+            listener.maxConcurrentMessages shouldBe 5
+            listener.pollTime shouldBe 5.seconds
+            listener.shutdownTimeout shouldBe 30.seconds
+        }
+    }
+
+    @Test
+    fun `listener durations also accept ISO-8601 format`() {
+        runner.withPropertyValues(
+            "awskotlin.region=us-east-1",
+            "awskotlin.sqs.listener.poll-time=PT2S",
+            "awskotlin.sqs.listener.shutdown-timeout=PT1M",
+        ).run { context ->
+            val listener = context.getBean(SqsProperties::class.java).listener
+            listener.pollTime shouldBe 2.seconds
+            listener.shutdownTimeout shouldBe 60.seconds
+        }
+    }
+
+    @Test
+    fun `user SqsListenerAnnotationRegistrar bean wins`() {
+        runner.withPropertyValues(
+            "awskotlin.region=us-east-1",
+        ).withUserConfiguration(UserSqsListenerRegistrarConfiguration::class.java).run { context ->
+            context.getBeanNamesForType(SqsListenerAnnotationRegistrar::class.java).size shouldBe 1
+            val userBean = context.getBean("userSqsListenerRegistrar")
+            context.getBean(SqsListenerAnnotationRegistrar::class.java) shouldBeSameInstanceAs userBean
+        }
+    }
+
+    @Test
+    fun `default listener config maps into the registrar`() {
+        runner.withPropertyValues(
+            "awskotlin.region=us-east-1",
+        ).run { context ->
+            val registrar = context.getBean(SqsListenerAnnotationRegistrar::class.java)
+            registrarConfig(registrar) shouldBe SqsListenerConfig()
+            registrar.isAutoStartup shouldBe true
+        }
+    }
+
+    @Test
+    fun `custom listener properties map into the registrar and autoStartup=false prevents polling`() {
+        runner.withPropertyValues(
+            "awskotlin.region=us-east-1",
+            "awskotlin.sqs.listener.auto-startup=false",
+            "awskotlin.sqs.listener.max-concurrent-messages=5",
+            "awskotlin.sqs.listener.poll-time=5s",
+            "awskotlin.sqs.listener.shutdown-timeout=30s",
+        ).run { context ->
+            val registrar = context.getBean(SqsListenerAnnotationRegistrar::class.java)
+            registrarConfig(registrar) shouldBe SqsListenerConfig(
+                autoStartup = false,
+                maxConcurrentMessages = 5,
+                pollTime = 5.seconds,
+                shutdownTimeout = 30.seconds,
+            )
+            // autoStartup=false: the refreshed context did not start the registrar...
+            registrar.isAutoStartup shouldBe false
+            registrar.isRunning shouldBe false
+            // ...but manual start still works (no endpoints registered, so no AWS calls).
+            registrar.start()
+            registrar.isRunning shouldBe true
+            registrar.stop()
+            registrar.isRunning shouldBe false
+        }
+    }
+
+    // The registrar keeps its config private; read it reflectively to verify property mapping.
+    private fun registrarConfig(registrar: SqsListenerAnnotationRegistrar): SqsListenerConfig {
+        val field = SqsListenerAnnotationRegistrar::class.java.getDeclaredField("config")
+        field.isAccessible = true
+        return field.get(registrar) as SqsListenerConfig
+    }
+}
+
+@Configuration(proxyBeanMethods = false)
+class UserSqsClientConfiguration {
+    @Bean
+    fun userSqsClient(): SqsClient = mockk(relaxed = true)
+}
+
+@Configuration(proxyBeanMethods = false)
+class UserSqsMessageConverterConfiguration {
+    @Bean
+    fun userSqsMessageConverter(): SqsMessageConverter = mockk(relaxed = true)
+}
+
+@Configuration(proxyBeanMethods = false)
+class UserSqsQueueResolverConfiguration {
+    @Bean
+    fun userSqsQueueResolver(): SqsQueueResolver = SqsQueueResolver(mockk(relaxed = true))
+}
+
+@Configuration(proxyBeanMethods = false)
+class UserSqsTemplateConfiguration {
+    @Bean
+    fun userSqsTemplate(): SqsTemplate = SqsTemplate(mockk(relaxed = true))
+}
+
+@Configuration(proxyBeanMethods = false)
+class UserSqsListenerRegistrarConfiguration {
+    @Bean
+    fun userSqsListenerRegistrar(): SqsListenerAnnotationRegistrar =
+        SqsListenerAnnotationRegistrar(mockk(relaxed = true), mockk(relaxed = true))
+}
diff --git a/starters/starter-sqs/src/test/kotlin/io/github/hantsy/awskotlin/sqs/autoconfigure/SqsLocalStackIntegrationTest.kt b/starters/starter-sqs/src/test/kotlin/io/github/hantsy/awskotlin/sqs/autoconfigure/SqsLocalStackIntegrationTest.kt
new file mode 100644
index 0000000..c56e0b3
--- /dev/null
+++ b/starters/starter-sqs/src/test/kotlin/io/github/hantsy/awskotlin/sqs/autoconfigure/SqsLocalStackIntegrationTest.kt
@@ -0,0 +1,214 @@
+package io.github.hantsy.awskotlin.sqs.autoconfigure
+
+import aws.sdk.kotlin.runtime.auth.credentials.StaticCredentialsProvider
+import aws.sdk.kotlin.services.sqs.SqsClient
+import aws.sdk.kotlin.services.sqs.createQueue
+import aws.smithy.kotlin.runtime.net.url.Url
+import io.github.hantsy.awskotlin.core.autoconfigure.AwsKotlinAutoConfiguration
+import io.github.hantsy.awskotlin.sqs.SqsAcknowledgementMode
+import io.github.hantsy.awskotlin.sqs.SqsQueueNotFoundException
+import io.github.hantsy.awskotlin.sqs.SqsTemplate
+import io.github.hantsy.awskotlin.sqs.listener.Acknowledge
+import io.github.hantsy.awskotlin.sqs.listener.SqsListener
+import io.kotest.matchers.nulls.shouldNotBeNull
+import io.kotest.matchers.shouldBe
+import kotlinx.coroutines.runBlocking
+import org.awaitility.kotlin.await
+import org.junit.jupiter.api.AfterAll
+import org.junit.jupiter.api.Assumptions.assumeTrue
+import org.junit.jupiter.api.BeforeAll
+import org.junit.jupiter.api.Test
+import org.junit.jupiter.api.TestInstance
+import org.springframework.boot.autoconfigure.AutoConfigurations
+import org.springframework.boot.test.context.runner.ApplicationContextRunner
+import org.testcontainers.DockerClientFactory
+import org.testcontainers.localstack.LocalStackContainer
+import org.testcontainers.utility.DockerImageName
+import java.time.Duration
+import java.util.UUID
+import java.util.concurrent.CopyOnWriteArrayList
+import kotlin.time.Duration.Companion.seconds
+
+// @SqsListener queue names must be compile-time constants, so listener tests use fixed
+// per-test names (unique within the freshly started container); template tests use UUIDs.
+private const val AUTO_ACK_QUEUE = "awskotlin-sqs-it-auto-ack"
+private const val MANUAL_ACK_QUEUE = "awskotlin-sqs-it-manual-ack"
+private const val MISSING_QUEUE = "awskotlin-sqs-it-missing"
+
+/**
+ * End-to-end SQS template and listener integration against LocalStack.
+ *
+ * Skipped unless Docker is available and LOCALSTACK_AUTH_TOKEN is set
+ * (LocalStack 4 requires a license token).
+ */
+@TestInstance(TestInstance.Lifecycle.PER_CLASS)
+class SqsLocalStackIntegrationTest {
+    private lateinit var container: LocalStackContainer
+    private lateinit var client: SqsClient
+    private lateinit var template: SqsTemplate
+
+    @BeforeAll
+    fun startLocalStack() {
+        val authToken = System.getenv("LOCALSTACK_AUTH_TOKEN")?.takeIf { it.isNotBlank() }
+        assumeTrue(authToken != null, "LOCALSTACK_AUTH_TOKEN is not set; skipping LocalStack tests")
+        assumeTrue(
+            DockerClientFactory.instance().isDockerAvailable,
+            "Docker is not available; skipping LocalStack tests"
+        )
+
+        container =
+            LocalStackContainer(DockerImageName.parse("localstack/localstack:4"))
+                .withServices("sqs")
+                .withEnv("LOCALSTACK_AUTH_TOKEN", authToken)
+        container.start()
+
+        client =
+            SqsClient {
+                region = "us-east-1"
+                credentialsProvider =
+                    StaticCredentialsProvider {
+                        accessKeyId = "test"
+                        secretAccessKey = "test"
+                    }
+                endpointUrl = Url.parse("http://${container.host}:${container.firstMappedPort}")
+            }
+        template = SqsTemplate(client)
+    }
+
+    @AfterAll
+    fun stopLocalStack() {
+        if (::client.isInitialized) {
+            runCatching { client.close() }
+        }
+        if (::container.isInitialized) {
+            runCatching { container.stop() }
+        }
+    }
+
+    @Test
+    fun `template send and AUTO receive round trip deletes the message`() = runBlocking<Unit> {
+        val queue = newQueue()
+        val operations = template.destination(queue)
+        operations.send("hello-auto")
+
+        val received = operations.receive(String::class.java, 10.seconds)
+        received.shouldNotBeNull().payload shouldBe "hello-auto"
+
+        // AUTO acknowledgement already deleted the message; a short poll finds nothing.
+        operations.receive(String::class.java, 2.seconds) shouldBe null
+    }
+
+    @Test
+    fun `manual acknowledge deletes and reject makes the message visible again`() = runBlocking<Unit> {
+        val queue = newQueue()
+        val operations = template.destination(queue)
+        operations.send("hello-manual")
+
+        val first = operations.receive(String::class.java, 10.seconds, SqsAcknowledgementMode.MANUAL)
+        first.shouldNotBeNull().payload shouldBe "hello-manual"
+
+        // reject sets visibility timeout 0, so the same message can be received again.
+        operations.reject(first)
+        val redelivered = operations.receive(String::class.java, 10.seconds, SqsAcknowledgementMode.MANUAL)
+        redelivered.shouldNotBeNull().payload shouldBe "hello-manual"
+        redelivered.messageId shouldBe first.messageId
+
+        operations.acknowledge(redelivered)
+        operations.receive(String::class.java, 2.seconds) shouldBe null
+    }
+
+    @Test
+    fun `listener auto-acks messages through the Spring context`() {
+        createQueue(AUTO_ACK_QUEUE)
+        contextRunner().withBean(AutoAckListener::class.java).run { context ->
+            context.startupFailure shouldBe null
+            val listener = context.getBean(AutoAckListener::class.java)
+            val springTemplate = context.getBean(SqsTemplate::class.java)
+
+            runBlocking { springTemplate.destination(AUTO_ACK_QUEUE).send("hello-listener") }
+            await.atMost(Duration.ofSeconds(30)).until { listener.received.contains("hello-listener") }
+
+            // No Acknowledge parameter: the container deleted the message after invocation.
+            runBlocking {
+                springTemplate.destination(AUTO_ACK_QUEUE).receive(String::class.java, 2.seconds)
+            } shouldBe null
+        }
+    }
+
+    @Test
+    fun `listener manual Acknowledge accept deletes the message`() {
+        createQueue(MANUAL_ACK_QUEUE)
+        contextRunner().withBean(ManualAckListener::class.java).run { context ->
+            context.startupFailure shouldBe null
+            val listener = context.getBean(ManualAckListener::class.java)
+            val springTemplate = context.getBean(SqsTemplate::class.java)
+
+            runBlocking { springTemplate.destination(MANUAL_ACK_QUEUE).send("hello-manual-listener") }
+            await.atMost(Duration.ofSeconds(30)).until { listener.received.contains("hello-manual-listener") }
+
+            // Acknowledge.accept() deleted the message; nothing is redelivered.
+            runBlocking {
+                springTemplate.destination(MANUAL_ACK_QUEUE).receive(String::class.java, 2.seconds)
+            } shouldBe null
+        }
+    }
+
+    @Test
+    fun `missing queue fails context startup without implicit creation`() {
+        contextRunner().withBean(MissingQueueListener::class.java).run { context ->
+            val failure = context.startupFailure
+            failure.shouldNotBeNull()
+            generateSequence(failure) { it.cause }
+                .any { it is SqsQueueNotFoundException } shouldBe true
+        }
+    }
+
+    private fun contextRunner(): ApplicationContextRunner = ApplicationContextRunner()
+        .withConfiguration(
+            AutoConfigurations.of(AwsKotlinAutoConfiguration::class.java, SqsAutoConfiguration::class.java),
+        )
+        .withPropertyValues(
+            "awskotlin.region=us-east-1",
+            "awskotlin.access-key-id=test",
+            "awskotlin.secret-access-key=test",
+            "awskotlin.sqs.endpoint=http://${container.host}:${container.firstMappedPort}",
+            "awskotlin.sqs.listener.poll-time=1s",
+            "awskotlin.sqs.listener.shutdown-timeout=5s",
+        )
+
+    private fun newQueue(): String {
+        val name = "awskotlin-sqs-it-${UUID.randomUUID().toString().substring(0, 8)}"
+        createQueue(name)
+        return name
+    }
+
+    private fun createQueue(name: String) {
+        runBlocking { client.createQueue { queueName = name } }
+    }
+}
+
+class AutoAckListener {
+    val received = CopyOnWriteArrayList<String>()
+
+    @SqsListener(AUTO_ACK_QUEUE)
+    fun onMessage(payload: String) {
+        received += payload
+    }
+}
+
+class ManualAckListener {
+    val received = CopyOnWriteArrayList<String>()
+
+    @SqsListener(MANUAL_ACK_QUEUE)
+    suspend fun onMessage(payload: String, acknowledge: Acknowledge) {
+        received += payload
+        acknowledge.accept()
+    }
+}
+
+class MissingQueueListener {
+    @SqsListener(MISSING_QUEUE)
+    fun onMessage(payload: String) {
+        error("Must never be invoked: the queue does not exist")
+    }
+}
