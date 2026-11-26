# Task 5 review package

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
 A sqs/src/test/kotlin/io/github/hantsy/awskotlin/sqs/JacksonSqsMessageConverterTest.kt
 A sqs/src/test/kotlin/io/github/hantsy/awskotlin/sqs/SqsQueueResolverTest.kt
 A sqs/src/test/kotlin/io/github/hantsy/awskotlin/sqs/SqsTemplateTest.kt
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
?? .kotlin/
?? .superpowers/

## diff stat
 settings.gradle.kts                                |   8 +
 sqs/build.gradle.kts                               |  58 ++++
 sqs/gradle.properties                              |   5 +
 .../sqs/DefaultSqsDestinationOperations.kt         | 125 ++++++++
 .../awskotlin/sqs/JacksonSqsMessageConverter.kt    |  42 +++
 .../hantsy/awskotlin/sqs/SqsAcknowledgementMode.kt |  10 +
 .../awskotlin/sqs/SqsDestinationOperations.kt      |  47 +++
 .../io/github/hantsy/awskotlin/sqs/SqsMessage.kt   |  18 ++
 .../hantsy/awskotlin/sqs/SqsMessageConverter.kt    |  10 +
 .../hantsy/awskotlin/sqs/SqsQueueResolver.kt       |  40 +++
 .../io/github/hantsy/awskotlin/sqs/SqsTemplate.kt  |  19 ++
 .../hantsy/awskotlin/sqs/SqsTemplateException.kt   |  19 ++
 .../sqs/JacksonSqsMessageConverterTest.kt          |  50 ++++
 .../hantsy/awskotlin/sqs/SqsQueueResolverTest.kt   |  83 ++++++
 .../github/hantsy/awskotlin/sqs/SqsTemplateTest.kt | 324 +++++++++++++++++++++
 15 files changed, 858 insertions(+)

## diff
diff --git a/settings.gradle.kts b/settings.gradle.kts
index a2d0cf9..84180dc 100644
--- a/settings.gradle.kts
+++ b/settings.gradle.kts
@@ -11,10 +11,18 @@ pluginManagement {
     includeBuild("aws-build-tool")
 }
 
 plugins {
     // Apply the foojay-resolver plugin to allow automatic download of JDKs
     id("org.gradle.toolchains.foojay-resolver-convention") version "0.10.0"
 }
 
 rootProject.name = "aws-sdk-kotlin-spring"
 include( "list", "utilities", "examples")
+include("core")
+include("s3")
+include("secrets-manager")
+include("sns")
+include("sqs")
+include("starters:starter-s3")
+include("starters:starter-secrets-manager")
+include("starters:starter-sns")
diff --git a/sqs/build.gradle.kts b/sqs/build.gradle.kts
new file mode 100644
index 0000000..ebef45d
--- /dev/null
+++ b/sqs/build.gradle.kts
@@ -0,0 +1,58 @@
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
diff --git a/sqs/gradle.properties b/sqs/gradle.properties
new file mode 100644
index 0000000..40754e7
--- /dev/null
+++ b/sqs/gradle.properties
@@ -0,0 +1,5 @@
+# Workaround for KGP 2.4.10 + Gradle 9.9.2: Gradle ships Kotlin 2.4.21 compiler classes
+# in its parent classloader, which breaks the Build Tools API path used by kapt stub
+# generation (NoSuchMethodError: ArgumentsToStrings.toArgumentStrings$default).
+# The legacy compiler runner avoids that path. Deprecated in KGP 2.4.x, removed in 2.5.0.
+kotlin.compiler.runViaBuildToolsApi=false
diff --git a/sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/DefaultSqsDestinationOperations.kt b/sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/DefaultSqsDestinationOperations.kt
new file mode 100644
index 0000000..f72bd30
--- /dev/null
+++ b/sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/DefaultSqsDestinationOperations.kt
@@ -0,0 +1,125 @@
+package io.github.hantsy.awskotlin.sqs
+
+import aws.sdk.kotlin.services.sqs.SqsClient
+import aws.sdk.kotlin.services.sqs.changeMessageVisibility
+import aws.sdk.kotlin.services.sqs.deleteMessage
+import aws.sdk.kotlin.services.sqs.model.ChangeMessageVisibilityResponse
+import aws.sdk.kotlin.services.sqs.model.DeleteMessageResponse
+import aws.sdk.kotlin.services.sqs.model.MessageNotInflight
+import aws.sdk.kotlin.services.sqs.model.MessageSystemAttributeName
+import aws.sdk.kotlin.services.sqs.model.QueueDoesNotExist
+import aws.sdk.kotlin.services.sqs.model.ReceiptHandleIsInvalid
+import aws.sdk.kotlin.services.sqs.model.SendMessageResponse
+import aws.sdk.kotlin.services.sqs.model.SqsException
+import aws.sdk.kotlin.services.sqs.receiveMessage
+import aws.sdk.kotlin.services.sqs.sendMessage
+import kotlin.time.Duration
+
+/**
+ * Default [SqsDestinationOperations] implementation.
+ *
+ * Modeled SDK errors are mapped to typed template errors with the original cause
+ * preserved: `QueueDoesNotExist` becomes [SqsQueueNotFoundException];
+ * `ReceiptHandleIsInvalid`, `MessageNotInflight` and other [SqsException]s become
+ * [SqsOperationException].
+ */
+internal class DefaultSqsDestinationOperations(
+    private val client: SqsClient,
+    private val converter: SqsMessageConverter,
+    private val queueResolver: SqsQueueResolver,
+    private val queueNameOrUrl: String,
+) : SqsDestinationOperations {
+    override suspend fun send(payload: Any, delay: Duration?): SendMessageResponse {
+        val delaySeconds = delay?.inWholeSeconds
+        if (delaySeconds != null && delaySeconds !in 0..MAX_DELAY_SECONDS) {
+            throw SqsOperationException("delay must be between 0 and $MAX_DELAY_SECONDS seconds, got $delay")
+        }
+        val queueUrl = queueResolver.resolve(queueNameOrUrl)
+        val body = converter.toBody(payload)
+        return mapSdkErrors("send message") {
+            client.sendMessage {
+                this.queueUrl = queueUrl
+                messageBody = body
+                this.delaySeconds = delaySeconds?.toInt()
+            }
+        }
+    }
+
+    override suspend fun <T : Any> receive(
+        targetType: Class<T>,
+        timeout: Duration,
+        acknowledgement: SqsAcknowledgementMode,
+    ): SqsMessage<T>? {
+        if (timeout.isNegative()) {
+            throw SqsOperationException("timeout must not be negative, got $timeout")
+        }
+        val waitTimeSeconds = timeout.inWholeSeconds.coerceIn(0, MAX_WAIT_TIME_SECONDS).toInt()
+        val queueUrl = queueResolver.resolve(queueNameOrUrl)
+        val response = mapSdkErrors("receive message") {
+            client.receiveMessage {
+                this.queueUrl = queueUrl
+                maxNumberOfMessages = 1
+                this.waitTimeSeconds = waitTimeSeconds
+                messageAttributeNames = listOf("All")
+                messageSystemAttributeNames = listOf(MessageSystemAttributeName.All)
+            }
+        }
+        val message = response.messages?.firstOrNull() ?: return null
+        val body = message.body ?: ""
+        // Conversion failures propagate before any delete: the message stays in the queue.
+        val payload = converter.fromBody(body, targetType)
+        val result = SqsMessage(
+            payload = payload,
+            messageId = message.messageId ?: "",
+            receiptHandle = message.receiptHandle ?: "",
+            body = body,
+            attributes = message.attributes?.mapKeys { it.key.value } ?: emptyMap(),
+            messageAttributes = message.messageAttributes ?: emptyMap(),
+        )
+        if (acknowledgement == SqsAcknowledgementMode.AUTO) {
+            delete(queueUrl, result.receiptHandle)
+        }
+        return result
+    }
+
+    override suspend fun acknowledge(message: SqsMessage<*>): DeleteMessageResponse {
+        val queueUrl = queueResolver.resolve(queueNameOrUrl)
+        return delete(queueUrl, message.receiptHandle)
+    }
+
+    override suspend fun reject(message: SqsMessage<*>): ChangeMessageVisibilityResponse {
+        val queueUrl = queueResolver.resolve(queueNameOrUrl)
+        return mapSdkErrors("reject message") {
+            client.changeMessageVisibility {
+                this.queueUrl = queueUrl
+                receiptHandle = message.receiptHandle
+                visibilityTimeout = 0
+            }
+        }
+    }
+
+    private suspend fun delete(queueUrl: String, receiptHandle: String): DeleteMessageResponse =
+        mapSdkErrors("delete message") {
+            client.deleteMessage {
+                this.queueUrl = queueUrl
+                this.receiptHandle = receiptHandle
+            }
+        }
+
+    private inline fun <R> mapSdkErrors(operation: String, block: () -> R): R = try {
+        block()
+    } catch (e: QueueDoesNotExist) {
+        throw SqsQueueNotFoundException(queueNameOrUrl, e)
+    } catch (e: ReceiptHandleIsInvalid) {
+        throw SqsOperationException("Cannot $operation: receipt handle is invalid or expired", e)
+    } catch (e: MessageNotInflight) {
+        throw SqsOperationException("Cannot $operation: message is not in flight", e)
+    } catch (e: SqsException) {
+        throw SqsOperationException("Failed to $operation on $queueNameOrUrl", e)
+    }
+
+    private companion object {
+        const val MAX_DELAY_SECONDS = 900L
+        const val MAX_WAIT_TIME_SECONDS = 20L
+    }
+}
diff --git a/sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/JacksonSqsMessageConverter.kt b/sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/JacksonSqsMessageConverter.kt
new file mode 100644
index 0000000..7364c8e
--- /dev/null
+++ b/sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/JacksonSqsMessageConverter.kt
@@ -0,0 +1,42 @@
+package io.github.hantsy.awskotlin.sqs
+
+import tools.jackson.core.JacksonException
+import tools.jackson.databind.ObjectMapper
+import tools.jackson.databind.json.JsonMapper
+import tools.jackson.module.kotlin.KotlinModule
+
+/**
+ * Jackson 3 (`tools.jackson`) based converter with the Kotlin module registered.
+ *
+ * String payloads pass through unchanged in both directions; all other payloads
+ * are serialized/deserialized as JSON. Conversion failures are wrapped in
+ * [SqsMessageConversionException] with the Jackson error preserved as the cause.
+ */
+class JacksonSqsMessageConverter : SqsMessageConverter {
+    private val mapper: ObjectMapper = JsonMapper.builder().addModule(KotlinModule.Builder().build()).build()
+
+    override fun toBody(payload: Any): String {
+        if (payload is String) return payload
+        return try {
+            mapper.writeValueAsString(payload)
+        } catch (e: JacksonException) {
+            throw SqsMessageConversionException(
+                "Failed to serialize payload of type ${payload.javaClass.name} to a message body",
+                e,
+            )
+        }
+    }
+
+    override fun <T : Any> fromBody(body: String, targetType: Class<T>): T {
+        if (targetType == String::class.java) {
+            @Suppress("UNCHECKED_CAST")
+            return body as T
+        }
+        return try {
+            mapper.readValue(body, targetType)
+                ?: throw SqsMessageConversionException("Message body converted to null for type ${targetType.name}")
+        } catch (e: JacksonException) {
+            throw SqsMessageConversionException("Failed to deserialize message body to ${targetType.name}", e)
+        }
+    }
+}
diff --git a/sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/SqsAcknowledgementMode.kt b/sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/SqsAcknowledgementMode.kt
new file mode 100644
index 0000000..1833c55
--- /dev/null
+++ b/sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/SqsAcknowledgementMode.kt
@@ -0,0 +1,10 @@
+package io.github.hantsy.awskotlin.sqs
+
+/** Controls whether received messages are deleted automatically. */
+enum class SqsAcknowledgementMode {
+    /** Delete the message after successful payload conversion (default). */
+    AUTO,
+
+    /** Leave the message in the queue; the caller must acknowledge or reject it. */
+    MANUAL,
+}
diff --git a/sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/SqsDestinationOperations.kt b/sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/SqsDestinationOperations.kt
new file mode 100644
index 0000000..aa664f4
--- /dev/null
+++ b/sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/SqsDestinationOperations.kt
@@ -0,0 +1,47 @@
+package io.github.hantsy.awskotlin.sqs
+
+import aws.sdk.kotlin.services.sqs.model.ChangeMessageVisibilityResponse
+import aws.sdk.kotlin.services.sqs.model.DeleteMessageResponse
+import aws.sdk.kotlin.services.sqs.model.SendMessageResponse
+import kotlin.time.Duration
+import kotlin.time.Duration.Companion.seconds
+
+/**
+ * Queue-scoped SQS operations. Created via [SqsTemplate.destination].
+ *
+ * Standard queues only; no implicit queue creation, no batch receive.
+ */
+interface SqsDestinationOperations {
+    /**
+     * Sends [payload] to the queue, optionally delaying delivery by [delay]
+     * (0..900 seconds; invalid values throw [SqsOperationException]).
+     */
+    suspend fun send(payload: Any, delay: Duration? = null): SendMessageResponse
+
+    /**
+     * Receives a single message and converts its body to [targetType], or returns
+     * `null` when the queue is empty. [timeout] is clamped to 0..20 seconds for
+     * long polling; negative values throw [SqsOperationException].
+     *
+     * [SqsAcknowledgementMode.AUTO] deletes the message after successful conversion;
+     * [SqsAcknowledgementMode.MANUAL] leaves it in flight. Conversion failures never
+     * delete the message.
+     */
+    suspend fun <T : Any> receive(
+        targetType: Class<T>,
+        timeout: Duration = 10.seconds,
+        acknowledgement: SqsAcknowledgementMode = SqsAcknowledgementMode.AUTO,
+    ): SqsMessage<T>?
+
+    /** Deletes the message from the queue using its receipt handle. */
+    suspend fun acknowledge(message: SqsMessage<*>): DeleteMessageResponse
+
+    /** Makes the message immediately visible again by setting its visibility timeout to 0. */
+    suspend fun reject(message: SqsMessage<*>): ChangeMessageVisibilityResponse
+}
+
+/** Reified convenience overload of [SqsDestinationOperations.receive]. */
+suspend inline fun <reified T : Any> SqsDestinationOperations.receive(
+    timeout: Duration = 10.seconds,
+    acknowledgement: SqsAcknowledgementMode = SqsAcknowledgementMode.AUTO,
+): SqsMessage<T>? = receive(T::class.java, timeout, acknowledgement)
diff --git a/sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/SqsMessage.kt b/sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/SqsMessage.kt
new file mode 100644
index 0000000..2222b14
--- /dev/null
+++ b/sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/SqsMessage.kt
@@ -0,0 +1,18 @@
+package io.github.hantsy.awskotlin.sqs
+
+import aws.sdk.kotlin.services.sqs.model.MessageAttributeValue
+
+/**
+ * A received SQS message with its converted [payload] and the raw SDK metadata.
+ *
+ * [receiptHandle] is required to [SqsDestinationOperations.acknowledge] or
+ * [SqsDestinationOperations.reject] the message in manual acknowledgement mode.
+ */
+data class SqsMessage<T>(
+    val payload: T,
+    val messageId: String,
+    val receiptHandle: String,
+    val body: String,
+    val attributes: Map<String, String>,
+    val messageAttributes: Map<String, MessageAttributeValue>,
+)
diff --git a/sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/SqsMessageConverter.kt b/sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/SqsMessageConverter.kt
new file mode 100644
index 0000000..ddea8de
--- /dev/null
+++ b/sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/SqsMessageConverter.kt
@@ -0,0 +1,10 @@
+package io.github.hantsy.awskotlin.sqs
+
+/** Converts message payloads to and from SQS message bodies. */
+interface SqsMessageConverter {
+    /** Serializes [payload] into a message body. */
+    fun toBody(payload: Any): String
+
+    /** Deserializes [body] into an instance of [targetType]. */
+    fun <T : Any> fromBody(body: String, targetType: Class<T>): T
+}
diff --git a/sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/SqsQueueResolver.kt b/sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/SqsQueueResolver.kt
new file mode 100644
index 0000000..aba7ac0
--- /dev/null
+++ b/sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/SqsQueueResolver.kt
@@ -0,0 +1,40 @@
+package io.github.hantsy.awskotlin.sqs
+
+import aws.sdk.kotlin.services.sqs.SqsClient
+import aws.sdk.kotlin.services.sqs.getQueueUrl
+import aws.sdk.kotlin.services.sqs.model.QueueDoesNotExist
+import kotlinx.coroutines.sync.Mutex
+import kotlinx.coroutines.sync.withLock
+
+/**
+ * Resolves a queue name or URL to a queue URL, caching successful name lookups.
+ *
+ * URLs (`https://` or `http://`) pass through unchanged. Names are resolved with
+ * `getQueueUrl`; the cache is guarded by a [Mutex] so concurrent coroutines never
+ * trigger duplicate lookups for the same name. FIFO queues (`.fifo` suffix) are
+ * rejected with [SqsOperationException] before any SDK call.
+ */
+class SqsQueueResolver(private val client: SqsClient) {
+    private val mutex = Mutex()
+    private val cache = mutableMapOf<String, String>()
+
+    /** Returns the queue URL for [queueNameOrUrl]. */
+    suspend fun resolve(queueNameOrUrl: String): String {
+        if (queueNameOrUrl.endsWith(".fifo")) {
+            throw SqsOperationException("FIFO queues are not supported: $queueNameOrUrl")
+        }
+        if (queueNameOrUrl.startsWith("https://") || queueNameOrUrl.startsWith("http://")) {
+            return queueNameOrUrl
+        }
+        return mutex.withLock {
+            cache.getOrPut(queueNameOrUrl) {
+                val response = try {
+                    client.getQueueUrl { queueName = queueNameOrUrl }
+                } catch (e: QueueDoesNotExist) {
+                    throw SqsQueueNotFoundException(queueNameOrUrl, e)
+                }
+                response.queueUrl ?: throw SqsQueueNotFoundException(queueNameOrUrl)
+            }
+        }
+    }
+}
diff --git a/sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/SqsTemplate.kt b/sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/SqsTemplate.kt
new file mode 100644
index 0000000..da67e45
--- /dev/null
+++ b/sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/SqsTemplate.kt
@@ -0,0 +1,19 @@
+package io.github.hantsy.awskotlin.sqs
+
+import aws.sdk.kotlin.services.sqs.SqsClient
+
+/**
+ * Entry point for fluent SQS operations backed by the AWS SDK for Kotlin [SqsClient].
+ *
+ * Obtain queue-scoped operations with [destination]; the queue name or URL is
+ * resolved lazily on the first operation.
+ */
+class SqsTemplate(
+    private val client: SqsClient,
+    private val converter: SqsMessageConverter = JacksonSqsMessageConverter(),
+    private val queueResolver: SqsQueueResolver = SqsQueueResolver(client),
+) {
+    /** Returns operations scoped to [queueNameOrUrl]. No request is made. */
+    fun destination(queueNameOrUrl: String): SqsDestinationOperations =
+        DefaultSqsDestinationOperations(client, converter, queueResolver, queueNameOrUrl)
+}
diff --git a/sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/SqsTemplateException.kt b/sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/SqsTemplateException.kt
new file mode 100644
index 0000000..5e120fd
--- /dev/null
+++ b/sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/SqsTemplateException.kt
@@ -0,0 +1,19 @@
+package io.github.hantsy.awskotlin.sqs
+
+/**
+ * Base error for SQS template operations.
+ *
+ * Named `SqsTemplateException` (not `SqsException`) to avoid collision with the SDK's
+ * [aws.sdk.kotlin.services.sqs.model.SqsException].
+ */
+open class SqsTemplateException(message: String, cause: Throwable? = null) : RuntimeException(message, cause)
+
+/** The queue name or URL could not be resolved to an existing queue. */
+class SqsQueueNotFoundException(queueNameOrUrl: String, cause: Throwable? = null) :
+    SqsTemplateException("Queue not found: $queueNameOrUrl", cause)
+
+/** A message payload could not be serialized or deserialized. */
+class SqsMessageConversionException(message: String, cause: Throwable? = null) : SqsTemplateException(message, cause)
+
+/** An SQS operation failed validation or the SDK reported an error. The original cause is preserved. */
+class SqsOperationException(message: String, cause: Throwable? = null) : SqsTemplateException(message, cause)
diff --git a/sqs/src/test/kotlin/io/github/hantsy/awskotlin/sqs/JacksonSqsMessageConverterTest.kt b/sqs/src/test/kotlin/io/github/hantsy/awskotlin/sqs/JacksonSqsMessageConverterTest.kt
new file mode 100644
index 0000000..8bd7dd3
--- /dev/null
+++ b/sqs/src/test/kotlin/io/github/hantsy/awskotlin/sqs/JacksonSqsMessageConverterTest.kt
@@ -0,0 +1,50 @@
+package io.github.hantsy.awskotlin.sqs
+
+import io.kotest.assertions.throwables.shouldThrow
+import io.kotest.matchers.shouldBe
+import io.kotest.matchers.types.shouldBeInstanceOf
+import org.junit.jupiter.api.Test
+import tools.jackson.core.JacksonException
+
+data class Order(val id: String, val quantity: Int)
+
+class JacksonSqsMessageConverterTest {
+    private val converter = JacksonSqsMessageConverter()
+
+    @Test
+    fun `toBody returns String payload unchanged`() {
+        converter.toBody("plain text, not JSON") shouldBe "plain text, not JSON"
+    }
+
+    @Test
+    fun `fromBody returns body unchanged for String target`() {
+        converter.fromBody("plain text, not JSON", String::class.java) shouldBe "plain text, not JSON"
+    }
+
+    @Test
+    fun `data class roundtrips through JSON`() {
+        val order = Order("o-1", 2)
+        val body = converter.toBody(order)
+        body shouldBe """{"id":"o-1","quantity":2}"""
+        converter.fromBody(body, Order::class.java) shouldBe order
+    }
+
+    @Test
+    fun `serialization failure throws SqsMessageConversionException`() {
+        val cyclic = mutableListOf<Any>()
+        cyclic.add(cyclic)
+        val thrown = shouldThrow<SqsMessageConversionException> { converter.toBody(cyclic) }
+        thrown.cause.shouldBeInstanceOf<JacksonException>()
+    }
+
+    @Test
+    fun `deserialization failure throws SqsMessageConversionException`() {
+        val thrown = shouldThrow<SqsMessageConversionException> { converter.fromBody("{not json", Order::class.java) }
+        thrown.cause.shouldBeInstanceOf<JacksonException>()
+    }
+
+    @Test
+    fun `null JSON body throws SqsMessageConversionException`() {
+        shouldThrow<SqsMessageConversionException> { converter.fromBody("null", Order::class.java) }
+    }
+}
diff --git a/sqs/src/test/kotlin/io/github/hantsy/awskotlin/sqs/SqsQueueResolverTest.kt b/sqs/src/test/kotlin/io/github/hantsy/awskotlin/sqs/SqsQueueResolverTest.kt
new file mode 100644
index 0000000..ba7cd82
--- /dev/null
+++ b/sqs/src/test/kotlin/io/github/hantsy/awskotlin/sqs/SqsQueueResolverTest.kt
@@ -0,0 +1,83 @@
+package io.github.hantsy.awskotlin.sqs
+
+import aws.sdk.kotlin.services.sqs.SqsClient
+import aws.sdk.kotlin.services.sqs.model.GetQueueUrlRequest
+import aws.sdk.kotlin.services.sqs.model.GetQueueUrlResponse
+import aws.sdk.kotlin.services.sqs.model.QueueDoesNotExist
+import io.kotest.assertions.throwables.shouldThrow
+import io.kotest.matchers.shouldBe
+import io.mockk.coEvery
+import io.mockk.coVerify
+import io.mockk.confirmVerified
+import io.mockk.mockk
+import io.mockk.slot
+import kotlinx.coroutines.test.runTest
+import org.junit.jupiter.api.Test
+
+class SqsQueueResolverTest {
+    private val client = mockk<SqsClient>()
+    private val resolver = SqsQueueResolver(client)
+    private val queueUrl = "https://sqs.us-east-1.amazonaws.com/123456789012/orders"
+
+    @Test
+    fun `https URL passes through unchanged without SDK call`() = runTest {
+        resolver.resolve(queueUrl) shouldBe queueUrl
+        confirmVerified(client)
+    }
+
+    @Test
+    fun `http URL passes through unchanged without SDK call`() = runTest {
+        val url = "http://localhost:4566/000000000000/orders"
+        resolver.resolve(url) shouldBe url
+        confirmVerified(client)
+    }
+
+    @Test
+    fun `queue name is resolved with getQueueUrl`() = runTest {
+        val url = queueUrl
+        val request = slot<GetQueueUrlRequest>()
+        coEvery { client.getQueueUrl(capture(request)) } returns GetQueueUrlResponse { queueUrl = url }
+
+        resolver.resolve("orders") shouldBe queueUrl
+        request.captured.queueName shouldBe "orders"
+    }
+
+    @Test
+    fun `successful name resolution is cached`() = runTest {
+        val url = queueUrl
+        coEvery { client.getQueueUrl(any<GetQueueUrlRequest>()) } returns GetQueueUrlResponse { queueUrl = url }
+
+        resolver.resolve("orders") shouldBe queueUrl
+        resolver.resolve("orders") shouldBe queueUrl
+
+        coVerify(exactly = 1) { client.getQueueUrl(any<GetQueueUrlRequest>()) }
+    }
+
+    @Test
+    fun `QueueDoesNotExist maps to SqsQueueNotFoundException with cause`() = runTest {
+        val failure = QueueDoesNotExist { message = "queue does not exist" }
+        coEvery { client.getQueueUrl(any<GetQueueUrlRequest>()) } throws failure
+
+        val thrown = shouldThrow<SqsQueueNotFoundException> { resolver.resolve("missing") }
+        thrown.cause shouldBe failure
+    }
+
+    @Test
+    fun `missing queueUrl in response maps to SqsQueueNotFoundException`() = runTest {
+        coEvery { client.getQueueUrl(any<GetQueueUrlRequest>()) } returns GetQueueUrlResponse { }
+
+        shouldThrow<SqsQueueNotFoundException> { resolver.resolve("missing") }
+    }
+
+    @Test
+    fun `FIFO queue name is rejected before any SDK call`() = runTest {
+        shouldThrow<SqsOperationException> { resolver.resolve("orders.fifo") }
+        confirmVerified(client)
+    }
+
+    @Test
+    fun `FIFO queue URL is rejected before any SDK call`() = runTest {
+        shouldThrow<SqsOperationException> { resolver.resolve("$queueUrl.fifo") }
+        confirmVerified(client)
+    }
+}
diff --git a/sqs/src/test/kotlin/io/github/hantsy/awskotlin/sqs/SqsTemplateTest.kt b/sqs/src/test/kotlin/io/github/hantsy/awskotlin/sqs/SqsTemplateTest.kt
new file mode 100644
index 0000000..4f2bef9
--- /dev/null
+++ b/sqs/src/test/kotlin/io/github/hantsy/awskotlin/sqs/SqsTemplateTest.kt
@@ -0,0 +1,324 @@
+package io.github.hantsy.awskotlin.sqs
+
+import aws.sdk.kotlin.services.sqs.SqsClient
+import aws.sdk.kotlin.services.sqs.model.ChangeMessageVisibilityRequest
+import aws.sdk.kotlin.services.sqs.model.ChangeMessageVisibilityResponse
+import aws.sdk.kotlin.services.sqs.model.DeleteMessageRequest
+import aws.sdk.kotlin.services.sqs.model.DeleteMessageResponse
+import aws.sdk.kotlin.services.sqs.model.GetQueueUrlRequest
+import aws.sdk.kotlin.services.sqs.model.GetQueueUrlResponse
+import aws.sdk.kotlin.services.sqs.model.Message
+import aws.sdk.kotlin.services.sqs.model.MessageAttributeValue
+import aws.sdk.kotlin.services.sqs.model.MessageNotInflight
+import aws.sdk.kotlin.services.sqs.model.MessageSystemAttributeName
+import aws.sdk.kotlin.services.sqs.model.QueueDoesNotExist
+import aws.sdk.kotlin.services.sqs.model.ReceiptHandleIsInvalid
+import aws.sdk.kotlin.services.sqs.model.ReceiveMessageRequest
+import aws.sdk.kotlin.services.sqs.model.ReceiveMessageResponse
+import aws.sdk.kotlin.services.sqs.model.SendMessageRequest
+import aws.sdk.kotlin.services.sqs.model.SendMessageResponse
+import aws.sdk.kotlin.services.sqs.model.SqsException
+import io.kotest.assertions.throwables.shouldThrow
+import io.kotest.matchers.nulls.shouldBeNull
+import io.kotest.matchers.shouldBe
+import io.mockk.coEvery
+import io.mockk.coVerify
+import io.mockk.confirmVerified
+import io.mockk.mockk
+import io.mockk.slot
+import kotlinx.coroutines.test.runTest
+import org.junit.jupiter.api.Test
+import kotlin.time.Duration.Companion.seconds
+
+class SqsTemplateTest {
+    private val client = mockk<SqsClient>()
+    private val template = SqsTemplate(client)
+    private val queueName = "orders"
+    private val queueUrl = "https://sqs.us-east-1.amazonaws.com/123456789012/orders"
+    private val orderJson = """{"id":"o-1","quantity":2}"""
+
+    private fun stubQueueUrl() {
+        val url = queueUrl
+        coEvery { client.getQueueUrl(any<GetQueueUrlRequest>()) } returns GetQueueUrlResponse { queueUrl = url }
+    }
+
+    private fun sdkMessage(body: String) = Message {
+        messageId = "m-1"
+        receiptHandle = "rh-1"
+        this.body = body
+        attributes = mapOf(MessageSystemAttributeName.SentTimestamp to "1700000000000")
+        messageAttributes = mapOf(
+            "trace" to MessageAttributeValue {
+                dataType = "String";
+                stringValue = "t-1"
+            }
+        )
+    }
+
+    private fun receivedMessage() = SqsMessage(
+        payload = "p",
+        messageId = "m-1",
+        receiptHandle = "rh-1",
+        body = "p",
+        attributes = emptyMap(),
+        messageAttributes = emptyMap(),
+    )
+
+    @Test
+    fun `send serializes payload as JSON and resolves queue by name`() = runTest {
+        val queueRequest = slot<GetQueueUrlRequest>()
+        val url = queueUrl
+        coEvery { client.getQueueUrl(capture(queueRequest)) } returns GetQueueUrlResponse { queueUrl = url }
+        val request = slot<SendMessageRequest>()
+        val response = SendMessageResponse { messageId = "sent-1" }
+        coEvery { client.sendMessage(capture(request)) } returns response
+
+        val result = template.destination(queueName).send(Order("o-1", 2))
+
+        result shouldBe response
+        queueRequest.captured.queueName shouldBe queueName
+        request.captured.queueUrl shouldBe queueUrl
+        request.captured.messageBody shouldBe orderJson
+        request.captured.delaySeconds.shouldBeNull()
+    }
+
+    @Test
+    fun `send passes String payload through unchanged`() = runTest {
+        stubQueueUrl()
+        val request = slot<SendMessageRequest>()
+        coEvery { client.sendMessage(capture(request)) } returns SendMessageResponse { }
+
+        template.destination(queueName).send("plain text")
+
+        request.captured.messageBody shouldBe "plain text"
+    }
+
+    @Test
+    fun `send accepts queue URL without resolution`() = runTest {
+        val request = slot<SendMessageRequest>()
+        coEvery { client.sendMessage(capture(request)) } returns SendMessageResponse { }
+
+        template.destination(queueUrl).send("hello")
+
+        request.captured.queueUrl shouldBe queueUrl
+        coVerify(exactly = 0) { client.getQueueUrl(any<GetQueueUrlRequest>()) }
+    }
+
+    @Test
+    fun `send sets delaySeconds from delay`() = runTest {
+        stubQueueUrl()
+        val request = slot<SendMessageRequest>()
+        coEvery { client.sendMessage(capture(request)) } returns SendMessageResponse { }
+
+        template.destination(queueName).send("hello", delay = 5.seconds)
+
+        request.captured.delaySeconds shouldBe 5
+    }
+
+    @Test
+    fun `send rejects negative delay without SDK send call`() = runTest {
+        shouldThrow<SqsOperationException> { template.destination(queueName).send("hello", delay = (-1).seconds) }
+        coVerify(exactly = 0) { client.sendMessage(any<SendMessageRequest>()) }
+    }
+
+    @Test
+    fun `send rejects delay above 900 seconds without SDK send call`() = runTest {
+        shouldThrow<SqsOperationException> { template.destination(queueName).send("hello", delay = 901.seconds) }
+        coVerify(exactly = 0) { client.sendMessage(any<SendMessageRequest>()) }
+    }
+
+    @Test
+    fun `send maps QueueDoesNotExist from sendMessage with cause`() = runTest {
+        stubQueueUrl()
+        val failure = QueueDoesNotExist { message = "queue vanished" }
+        coEvery { client.sendMessage(any<SendMessageRequest>()) } throws failure
+
+        val thrown = shouldThrow<SqsQueueNotFoundException> { template.destination(queueName).send("hello") }
+        thrown.cause shouldBe failure
+    }
+
+    @Test
+    fun `receive returns null for empty response and builds expected request`() = runTest {
+        stubQueueUrl()
+        val request = slot<ReceiveMessageRequest>()
+        coEvery { client.receiveMessage(capture(request)) } returns ReceiveMessageResponse { messages = emptyList() }
+
+        template.destination(queueName).receive(String::class.java).shouldBeNull()
+
+        request.captured.queueUrl shouldBe queueUrl
+        request.captured.maxNumberOfMessages shouldBe 1
+        request.captured.waitTimeSeconds shouldBe 10
+        request.captured.messageAttributeNames shouldBe listOf("All")
+        request.captured.messageSystemAttributeNames shouldBe listOf(MessageSystemAttributeName.All)
+        coVerify(exactly = 0) { client.deleteMessage(any<DeleteMessageRequest>()) }
+    }
+
+    @Test
+    fun `receive AUTO converts payload and deletes message`() = runTest {
+        stubQueueUrl()
+        val message = sdkMessage(orderJson)
+        coEvery { client.receiveMessage(any<ReceiveMessageRequest>()) } returns
+            ReceiveMessageResponse { messages = listOf(message) }
+        val deleteRequest = slot<DeleteMessageRequest>()
+        val deleteResponse = DeleteMessageResponse { }
+        coEvery { client.deleteMessage(capture(deleteRequest)) } returns deleteResponse
+
+        val result = template.destination(queueName).receive(Order::class.java)
+
+        result shouldBe SqsMessage(
+            payload = Order("o-1", 2),
+            messageId = "m-1",
+            receiptHandle = "rh-1",
+            body = orderJson,
+            attributes = mapOf("SentTimestamp" to "1700000000000"),
+            messageAttributes = message.messageAttributes!!,
+        )
+        deleteRequest.captured.queueUrl shouldBe queueUrl
+        deleteRequest.captured.receiptHandle shouldBe "rh-1"
+    }
+
+    @Test
+    fun `receive MANUAL does not delete message`() = runTest {
+        stubQueueUrl()
+        coEvery { client.receiveMessage(any<ReceiveMessageRequest>()) } returns
+            ReceiveMessageResponse { messages = listOf(sdkMessage(orderJson)) }
+
+        val result = template.destination(queueName)
+            .receive(Order::class.java, acknowledgement = SqsAcknowledgementMode.MANUAL)
+
+        result!!.payload shouldBe Order("o-1", 2)
+        coVerify(exactly = 0) { client.deleteMessage(any<DeleteMessageRequest>()) }
+    }
+
+    @Test
+    fun `receive conversion failure does not delete message`() = runTest {
+        stubQueueUrl()
+        coEvery { client.receiveMessage(any<ReceiveMessageRequest>()) } returns
+            ReceiveMessageResponse { messages = listOf(sdkMessage("{not json")) }
+
+        shouldThrow<SqsMessageConversionException> { template.destination(queueName).receive(Order::class.java) }
+        coVerify(exactly = 0) { client.deleteMessage(any<DeleteMessageRequest>()) }
+    }
+
+    @Test
+    fun `receive clamps timeout to 20 seconds`() = runTest {
+        stubQueueUrl()
+        val request = slot<ReceiveMessageRequest>()
+        coEvery { client.receiveMessage(capture(request)) } returns ReceiveMessageResponse { }
+
+        template.destination(queueName).receive(String::class.java, timeout = 60.seconds)
+
+        request.captured.waitTimeSeconds shouldBe 20
+    }
+
+    @Test
+    fun `receive rejects negative timeout without SDK receive call`() = runTest {
+        shouldThrow<SqsOperationException> {
+            template.destination(queueName).receive(String::class.java, timeout = (-1).seconds)
+        }
+        coVerify(exactly = 0) { client.receiveMessage(any<ReceiveMessageRequest>()) }
+    }
+
+    @Test
+    fun `receive AUTO delete failure wraps SqsOperationException with cause`() = runTest {
+        stubQueueUrl()
+        coEvery { client.receiveMessage(any<ReceiveMessageRequest>()) } returns
+            ReceiveMessageResponse { messages = listOf(sdkMessage("hello")) }
+        val failure = SqsException("delete denied")
+        coEvery { client.deleteMessage(any<DeleteMessageRequest>()) } throws failure
+
+        val thrown = shouldThrow<SqsOperationException> { template.destination(queueName).receive(String::class.java) }
+        thrown.cause shouldBe failure
+    }
+
+    @Test
+    fun `reified receive infers target type`() = runTest {
+        stubQueueUrl()
+        coEvery { client.receiveMessage(any<ReceiveMessageRequest>()) } returns
+            ReceiveMessageResponse { messages = listOf(sdkMessage(orderJson)) }
+
+        val result = template.destination(queueName).receive<Order>(acknowledgement = SqsAcknowledgementMode.MANUAL)
+
+        result!!.payload shouldBe Order("o-1", 2)
+        coVerify(exactly = 0) { client.deleteMessage(any<DeleteMessageRequest>()) }
+    }
+
+    @Test
+    fun `receive maps QueueDoesNotExist from receiveMessage with cause`() = runTest {
+        stubQueueUrl()
+        val failure = QueueDoesNotExist { message = "queue vanished" }
+        coEvery { client.receiveMessage(any<ReceiveMessageRequest>()) } throws failure
+
+        val thrown =
+            shouldThrow<SqsQueueNotFoundException> { template.destination(queueName).receive(String::class.java) }
+        thrown.cause shouldBe failure
+    }
+
+    @Test
+    fun `acknowledge deletes with receipt handle and resolved URL`() = runTest {
+        stubQueueUrl()
+        val request = slot<DeleteMessageRequest>()
+        val response = DeleteMessageResponse { }
+        coEvery { client.deleteMessage(capture(request)) } returns response
+
+        val result = template.destination(queueName).acknowledge(receivedMessage())
+
+        result shouldBe response
+        request.captured.queueUrl shouldBe queueUrl
+        request.captured.receiptHandle shouldBe "rh-1"
+    }
+
+    @Test
+    fun `reject sets visibility timeout to zero`() = runTest {
+        stubQueueUrl()
+        val request = slot<ChangeMessageVisibilityRequest>()
+        val response = ChangeMessageVisibilityResponse { }
+        coEvery { client.changeMessageVisibility(capture(request)) } returns response
+
+        val result = template.destination(queueName).reject(receivedMessage())
+
+        result shouldBe response
+        request.captured.queueUrl shouldBe queueUrl
+        request.captured.receiptHandle shouldBe "rh-1"
+        request.captured.visibilityTimeout shouldBe 0
+    }
+
+    @Test
+    fun `acknowledge maps ReceiptHandleIsInvalid with cause`() = runTest {
+        stubQueueUrl()
+        val failure = ReceiptHandleIsInvalid { message = "bad handle" }
+        coEvery { client.deleteMessage(any<DeleteMessageRequest>()) } throws failure
+
+        val thrown =
+            shouldThrow<SqsOperationException> { template.destination(queueName).acknowledge(receivedMessage()) }
+        thrown.cause shouldBe failure
+    }
+
+    @Test
+    fun `reject maps MessageNotInflight with cause`() = runTest {
+        stubQueueUrl()
+        val failure = MessageNotInflight { }
+        coEvery { client.changeMessageVisibility(any<ChangeMessageVisibilityRequest>()) } throws failure
+
+        val thrown = shouldThrow<SqsOperationException> { template.destination(queueName).reject(receivedMessage()) }
+        thrown.cause shouldBe failure
+    }
+
+    @Test
+    fun `acknowledge maps QueueDoesNotExist with cause`() = runTest {
+        stubQueueUrl()
+        val failure = QueueDoesNotExist { message = "queue vanished" }
+        coEvery { client.deleteMessage(any<DeleteMessageRequest>()) } throws failure
+
+        val thrown = shouldThrow<SqsQueueNotFoundException> {
+            template.destination(queueName).acknowledge(receivedMessage())
+        }
+        thrown.cause shouldBe failure
+    }
+
+    @Test
+    fun `destination makes no request`() = runTest {
+        template.destination(queueName)
+        confirmVerified(client)
+    }
+}
