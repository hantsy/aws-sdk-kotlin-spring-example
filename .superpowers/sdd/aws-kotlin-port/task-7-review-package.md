# Task 7 review package

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
?? .kotlin/
?? .superpowers/

## diff stat
 gradle/libs.versions.toml                          |  10 +
 sqs/build.gradle.kts                               |  60 +++
 sqs/gradle.properties                              |   5 +
 .../sqs/DefaultSqsDestinationOperations.kt         | 125 ++++++
 .../awskotlin/sqs/JacksonSqsMessageConverter.kt    |  42 ++
 .../hantsy/awskotlin/sqs/SqsAcknowledgementMode.kt |  10 +
 .../awskotlin/sqs/SqsDestinationOperations.kt      |  47 +++
 .../io/github/hantsy/awskotlin/sqs/SqsMessage.kt   |  18 +
 .../hantsy/awskotlin/sqs/SqsMessageConverter.kt    |  10 +
 .../hantsy/awskotlin/sqs/SqsQueueResolver.kt       |  40 ++
 .../io/github/hantsy/awskotlin/sqs/SqsTemplate.kt  |  19 +
 .../hantsy/awskotlin/sqs/SqsTemplateException.kt   |  19 +
 .../hantsy/awskotlin/sqs/listener/Acknowledge.kt   |  31 ++
 .../hantsy/awskotlin/sqs/listener/SqsListener.kt   |  19 +
 .../sqs/listener/SqsListenerAnnotationRegistrar.kt |  91 +++++
 .../awskotlin/sqs/listener/SqsListenerConfig.kt    |  19 +
 .../awskotlin/sqs/listener/SqsListenerContainer.kt | 154 +++++++
 .../awskotlin/sqs/listener/SqsListenerEndpoint.kt  |   9 +
 .../awskotlin/sqs/listener/SqsListenerMethod.kt    |  96 +++++
 .../sqs/JacksonSqsMessageConverterTest.kt          |  50 +++
 .../hantsy/awskotlin/sqs/SqsQueueResolverTest.kt   |  83 ++++
 .../github/hantsy/awskotlin/sqs/SqsTemplateTest.kt | 324 +++++++++++++++
 .../listener/SqsListenerAnnotationRegistrarTest.kt | 152 +++++++
 .../sqs/listener/SqsListenerContainerTest.kt       | 451 +++++++++++++++++++++
 24 files changed, 1884 insertions(+)

## diff
diff --git a/gradle/libs.versions.toml b/gradle/libs.versions.toml
index 3400e81..9e20afc 100644
--- a/gradle/libs.versions.toml
+++ b/gradle/libs.versions.toml
@@ -28,23 +28,32 @@ kotlinStdlib = { module = "org.jetbrains.kotlin:kotlin-stdlib" }
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
@@ -75,13 +84,14 @@ testcore = ["webfluxTest", "reactorTest", "kotlinCoroutinesTest"]
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
index 0000000..01fff34
--- /dev/null
+++ b/sqs/build.gradle.kts
@@ -0,0 +1,60 @@
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
index 0000000..bfdfeb5
--- /dev/null
+++ b/sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/listener/SqsListenerAnnotationRegistrar.kt
@@ -0,0 +1,91 @@
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
+import kotlin.reflect.full.memberFunctions
+import kotlin.reflect.jvm.jvmErasure
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
+        val annotated = bean.javaClass.methods.filter { it.isAnnotationPresent(SqsListener::class.java) }
+        if (annotated.isEmpty()) return bean
+        val functions = bean::class.memberFunctions
+        annotated.forEach { method ->
+            val function = functions.firstOrNull { it.matches(method) }
+                ?: error("No Kotlin member function for annotated method ${method.name} on ${bean.javaClass.name}")
+            val queues = method.getAnnotation(SqsListener::class.java).queues.toList()
+            endpoints += SqsListenerEndpoint(bean, function, queues)
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
diff --git a/sqs/src/test/kotlin/io/github/hantsy/awskotlin/sqs/listener/SqsListenerAnnotationRegistrarTest.kt b/sqs/src/test/kotlin/io/github/hantsy/awskotlin/sqs/listener/SqsListenerAnnotationRegistrarTest.kt
new file mode 100644
index 0000000..65dee46
--- /dev/null
+++ b/sqs/src/test/kotlin/io/github/hantsy/awskotlin/sqs/listener/SqsListenerAnnotationRegistrarTest.kt
@@ -0,0 +1,152 @@
+package io.github.hantsy.awskotlin.sqs.listener
+
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
+}
diff --git a/sqs/src/test/kotlin/io/github/hantsy/awskotlin/sqs/listener/SqsListenerContainerTest.kt b/sqs/src/test/kotlin/io/github/hantsy/awskotlin/sqs/listener/SqsListenerContainerTest.kt
new file mode 100644
index 0000000..b8ddd40
--- /dev/null
+++ b/sqs/src/test/kotlin/io/github/hantsy/awskotlin/sqs/listener/SqsListenerContainerTest.kt
@@ -0,0 +1,451 @@
+package io.github.hantsy.awskotlin.sqs.listener
+
+import aws.sdk.kotlin.services.sqs.SqsClient
+import aws.sdk.kotlin.services.sqs.model.ChangeMessageVisibilityResponse
+import aws.sdk.kotlin.services.sqs.model.DeleteMessageResponse
+import io.github.hantsy.awskotlin.sqs.SqsAcknowledgementMode
+import io.github.hantsy.awskotlin.sqs.SqsDestinationOperations
+import io.github.hantsy.awskotlin.sqs.SqsMessage
+import io.github.hantsy.awskotlin.sqs.SqsOperationException
+import io.github.hantsy.awskotlin.sqs.SqsQueueNotFoundException
+import io.github.hantsy.awskotlin.sqs.SqsQueueResolver
+import io.github.hantsy.awskotlin.sqs.SqsTemplate
+import io.kotest.assertions.throwables.shouldThrow
+import io.kotest.matchers.collections.shouldContain
+import io.kotest.matchers.shouldBe
+import io.mockk.coEvery
+import io.mockk.coVerify
+import io.mockk.confirmVerified
+import io.mockk.every
+import io.mockk.mockk
+import kotlinx.coroutines.CompletableDeferred
+import kotlinx.coroutines.ExperimentalCoroutinesApi
+import kotlinx.coroutines.awaitCancellation
+import kotlinx.coroutines.isActive
+import kotlinx.coroutines.test.advanceTimeBy
+import kotlinx.coroutines.test.advanceUntilIdle
+import kotlinx.coroutines.test.runCurrent
+import kotlinx.coroutines.test.runTest
+import org.junit.jupiter.api.Test
+import java.lang.annotation.Retention
+import java.lang.annotation.RetentionPolicy
+import kotlin.concurrent.thread
+import kotlin.reflect.KFunction
+import kotlin.reflect.full.findAnnotation
+import kotlin.reflect.full.memberFunctions
+import kotlin.time.Duration.Companion.seconds
+
+@OptIn(ExperimentalCoroutinesApi::class)
+class SqsListenerContainerTest {
+    private val template = mockk<SqsTemplate>()
+    private val operations = mockk<SqsDestinationOperations>()
+    private val resolver = mockk<SqsQueueResolver>()
+    private val queueUrl = "https://sqs.us-east-1.amazonaws.com/123456789012/orders"
+    private val bean = ListenerBean()
+    private val message = SqsMessage(
+        payload = "p",
+        messageId = "m-1",
+        receiptHandle = "rh-1",
+        body = "p",
+        attributes = mapOf("SentTimestamp" to "1700000000000"),
+        messageAttributes = emptyMap()
+    )
+
+    private fun stubQueue() {
+        coEvery { resolver.resolve("orders") } returns queueUrl
+        every { template.destination(queueUrl) } returns operations
+    }
+
+    // Deliver one message, then suspend forever so the poll loop parks without busy-spinning.
+    private fun stubReceiveOnce() {
+        var delivered = false
+        coEvery {
+            operations.receive(String::class.java, any(), SqsAcknowledgementMode.MANUAL)
+        } coAnswers {
+            if (delivered) {
+                awaitCancellation()
+            } else {
+                delivered = true
+                message
+            }
+        }
+    }
+
+    private fun endpoint(method: KFunction<*>, queues: List<String> = listOf("orders")) =
+        SqsListenerEndpoint(bean, method, queues)
+
+    @Test
+    fun `SqsListener annotation is runtime retained and captures queue names`() {
+        val annotation = ListenerBean::plain.findAnnotation<SqsListener>()
+        annotation!!.queues.toList() shouldBe listOf("orders")
+        SqsListener::class.findAnnotation<Target>()!!.allowedTargets.toList() shouldContain AnnotationTarget.FUNCTION
+        SqsListener::class.java.getAnnotation(Retention::class.java).value shouldBe RetentionPolicy.RUNTIME
+    }
+
+    @Test
+    fun `endpoint with empty queues is rejected`() {
+        shouldThrow<IllegalArgumentException> {
+            SqsListenerContainer(endpoint(ListenerBean::plain, emptyList()), template, resolver)
+        }
+    }
+
+    @Test
+    fun `method without payload parameter is rejected`() {
+        shouldThrow<IllegalArgumentException> {
+            SqsListenerContainer(endpoint(ListenerBean::ackOnly), template, resolver)
+        }
+    }
+
+    @Test
+    fun `method with two payload parameters is rejected`() {
+        shouldThrow<IllegalArgumentException> {
+            SqsListenerContainer(endpoint(ListenerBean::twoPayloads), template, resolver)
+        }
+    }
+
+    @Test
+    fun `method with unsupported generic parameter is rejected`() {
+        val generic = ListenerBean::class.memberFunctions.first { it.name == "generic" }
+        shouldThrow<IllegalArgumentException> {
+            SqsListenerContainer(endpoint(generic), template, resolver)
+        }
+    }
+
+    @Test
+    fun `SqsMessage star projection is rejected`() {
+        shouldThrow<IllegalArgumentException> {
+            SqsListenerContainer(endpoint(ListenerBean::star), template, resolver)
+        }
+    }
+
+    @Test
+    fun `method with two Acknowledge parameters is rejected`() {
+        shouldThrow<IllegalArgumentException> {
+            SqsListenerContainer(endpoint(ListenerBean::twoAcks), template, resolver)
+        }
+    }
+
+    @Test
+    fun `start resolves every queue before polling`() = runTest {
+        val urlA = "https://sqs.us-east-1.amazonaws.com/123456789012/a"
+        val urlB = "https://sqs.us-east-1.amazonaws.com/123456789012/b"
+        val opsA = mockk<SqsDestinationOperations>()
+        val opsB = mockk<SqsDestinationOperations>()
+        coEvery { resolver.resolve("a") } returns urlA
+        coEvery { resolver.resolve("b") } returns urlB
+        every { template.destination(urlA) } returns opsA
+        every { template.destination(urlB) } returns opsB
+        coEvery { opsA.receive(String::class.java, any(), any()) } coAnswers { awaitCancellation() }
+        coEvery { opsB.receive(String::class.java, any(), any()) } coAnswers { awaitCancellation() }
+        val container = SqsListenerContainer(
+            endpoint(ListenerBean::plain, listOf("a", "b")),
+            template,
+            resolver,
+            scope = this
+        )
+
+        container.start()
+        container.isRunning shouldBe true
+        advanceUntilIdle()
+
+        coVerify(exactly = 1) { resolver.resolve("a") }
+        coVerify(exactly = 1) { resolver.resolve("b") }
+        coVerify(exactly = 1) { opsA.receive(String::class.java, 10.seconds, SqsAcknowledgementMode.MANUAL) }
+        coVerify(exactly = 1) { opsB.receive(String::class.java, 10.seconds, SqsAcknowledgementMode.MANUAL) }
+        container.stop()
+    }
+
+    @Test
+    fun `missing queue fails startup without polling`() = runTest {
+        coEvery { resolver.resolve("missing") } throws SqsQueueNotFoundException("missing")
+        val container =
+            SqsListenerContainer(endpoint(ListenerBean::plain, listOf("missing")), template, resolver, scope = this)
+
+        shouldThrow<SqsQueueNotFoundException> { container.start() }
+
+        container.isRunning shouldBe false
+        coVerify(exactly = 0) { template.destination(any()) }
+    }
+
+    @Test
+    fun `FIFO queue fails fast without SDK calls`() = runTest {
+        val client = mockk<SqsClient>()
+        val container = SqsListenerContainer(
+            endpoint(ListenerBean::plain, listOf("orders.fifo")),
+            template,
+            SqsQueueResolver(client),
+            scope = this
+        )
+
+        shouldThrow<SqsOperationException> { container.start() }
+
+        container.isRunning shouldBe false
+        confirmVerified(client)
+    }
+
+    @Test
+    fun `plain payload listener is invoked and message auto-deleted in MANUAL mode`() = runTest {
+        stubQueue()
+        stubReceiveOnce()
+        coEvery { operations.acknowledge(message) } returns DeleteMessageResponse { }
+        val container = SqsListenerContainer(endpoint(ListenerBean::plain), template, resolver, scope = this)
+
+        container.start()
+        advanceUntilIdle()
+
+        bean.plainCalls shouldBe listOf("p")
+        coVerify(exactly = 2) { operations.receive(String::class.java, 10.seconds, SqsAcknowledgementMode.MANUAL) }
+        coVerify(exactly = 1) { operations.acknowledge(message) }
+        container.stop()
+    }
+
+    @Test
+    fun `SqsMessage listener receives full message and auto-deletes`() = runTest {
+        stubQueue()
+        stubReceiveOnce()
+        coEvery { operations.acknowledge(message) } returns DeleteMessageResponse { }
+        val container = SqsListenerContainer(endpoint(ListenerBean::full), template, resolver, scope = this)
+
+        container.start()
+        advanceUntilIdle()
+
+        bean.messageCalls shouldBe listOf(message)
+        coVerify(exactly = 1) { operations.acknowledge(message) }
+        container.stop()
+    }
+
+    @Test
+    fun `Acknowledge accept deletes the message`() = runTest {
+        stubQueue()
+        stubReceiveOnce()
+        coEvery { operations.acknowledge(message) } returns DeleteMessageResponse { }
+        bean.manualAction = { it.accept() }
+        val container = SqsListenerContainer(endpoint(ListenerBean::manual), template, resolver, scope = this)
+
+        container.start()
+        advanceUntilIdle()
+
+        bean.messageCalls shouldBe listOf(message)
+        coVerify(exactly = 1) { operations.acknowledge(message) }
+        coVerify(exactly = 0) { operations.reject(any()) }
+        container.stop()
+    }
+
+    @Test
+    fun `Acknowledge reject makes the message visible again without deleting`() = runTest {
+        stubQueue()
+        stubReceiveOnce()
+        coEvery { operations.reject(message) } returns ChangeMessageVisibilityResponse { }
+        bean.manualAction = { it.reject() }
+        val container = SqsListenerContainer(endpoint(ListenerBean::manual), template, resolver, scope = this)
+
+        container.start()
+        advanceUntilIdle()
+
+        coVerify(exactly = 1) { operations.reject(message) }
+        coVerify(exactly = 0) { operations.acknowledge(any()) }
+        container.stop()
+    }
+
+    @Test
+    fun `listener exception does not delete and poll loop survives`() = runTest {
+        stubQueue()
+        stubReceiveOnce()
+        val container = SqsListenerContainer(endpoint(ListenerBean::boom), template, resolver, scope = this)
+
+        container.start()
+        advanceUntilIdle()
+
+        coVerify(exactly = 0) { operations.acknowledge(any()) }
+        coVerify(exactly = 2) { operations.receive(String::class.java, any(), SqsAcknowledgementMode.MANUAL) }
+        container.isRunning shouldBe true
+        container.stop()
+    }
+
+    @Test
+    fun `unused Acknowledge parameter leaves the message undeleted`() = runTest {
+        stubQueue()
+        stubReceiveOnce()
+        bean.manualAction = { }
+        val container = SqsListenerContainer(endpoint(ListenerBean::manual), template, resolver, scope = this)
+
+        container.start()
+        advanceUntilIdle()
+
+        bean.messageCalls shouldBe listOf(message)
+        coVerify(exactly = 0) { operations.acknowledge(any()) }
+        coVerify(exactly = 0) { operations.reject(any()) }
+        container.stop()
+    }
+
+    @Test
+    fun `empty receive continues polling without processing`() = runTest {
+        stubQueue()
+        var delivered = false
+        coEvery {
+            operations.receive(String::class.java, any(), SqsAcknowledgementMode.MANUAL)
+        } coAnswers {
+            if (delivered) {
+                awaitCancellation()
+            } else {
+                delivered = true
+                null
+            }
+        }
+        val container = SqsListenerContainer(endpoint(ListenerBean::plain), template, resolver, scope = this)
+
+        container.start()
+        advanceUntilIdle()
+
+        bean.plainCalls shouldBe emptyList()
+        coVerify(exactly = 2) { operations.receive(String::class.java, any(), SqsAcknowledgementMode.MANUAL) }
+        coVerify(exactly = 0) { operations.acknowledge(any()) }
+        container.stop()
+    }
+
+    @Test
+    fun `receive errors back off exponentially and reset on success`() = runTest {
+        stubQueue()
+        var calls = 0
+        coEvery {
+            operations.receive(String::class.java, any(), SqsAcknowledgementMode.MANUAL)
+        } coAnswers {
+            calls++
+            if (calls <= 2) throw SqsOperationException("down") else awaitCancellation()
+        }
+        val container = SqsListenerContainer(endpoint(ListenerBean::plain), template, resolver, scope = this)
+
+        container.start()
+        runCurrent()
+        coVerify(exactly = 1) { operations.receive(String::class.java, any(), SqsAcknowledgementMode.MANUAL) }
+
+        advanceTimeBy(1.seconds)
+        runCurrent()
+        coVerify(exactly = 2) { operations.receive(String::class.java, any(), SqsAcknowledgementMode.MANUAL) }
+
+        advanceTimeBy(1.seconds)
+        runCurrent()
+        coVerify(exactly = 2) { operations.receive(String::class.java, any(), SqsAcknowledgementMode.MANUAL) }
+
+        advanceTimeBy(1.seconds)
+        runCurrent()
+        coVerify(exactly = 3) { operations.receive(String::class.java, any(), SqsAcknowledgementMode.MANUAL) }
+        container.stop()
+    }
+
+    @Test
+    fun `stop is idempotent, cancels polling and keeps the external scope alive`() = runTest {
+        stubQueue()
+        stubReceiveOnce()
+        coEvery { operations.acknowledge(message) } returns DeleteMessageResponse { }
+        val container = SqsListenerContainer(endpoint(ListenerBean::plain), template, resolver, scope = this)
+
+        container.start()
+        advanceUntilIdle()
+        container.isRunning shouldBe true
+
+        container.stop()
+        container.stop()
+        container.isRunning shouldBe false
+        advanceUntilIdle()
+
+        coVerify(exactly = 2) { operations.receive(String::class.java, any(), SqsAcknowledgementMode.MANUAL) }
+        isActive shouldBe true
+    }
+
+    @Test
+    fun `stop waits for in-flight processing before cancelling`() = runTest {
+        stubQueue()
+        stubReceiveOnce()
+        coEvery { operations.acknowledge(message) } returns DeleteMessageResponse { }
+        val gate = CompletableDeferred<Unit>()
+        bean.gate = { gate.await() }
+        val container = SqsListenerContainer(
+            endpoint(ListenerBean::gated),
+            template,
+            resolver,
+            SqsListenerConfig(shutdownTimeout = 10.seconds),
+            scope = this
+        )
+
+        container.start()
+        advanceUntilIdle()
+        bean.gatedCalls shouldBe emptyList()
+
+        val stopper = thread { container.stop() }
+        gate.complete(Unit)
+        advanceUntilIdle()
+        stopper.join(10_000)
+
+        stopper.isAlive shouldBe false
+        bean.gatedCalls shouldBe listOf("p")
+        coVerify(exactly = 1) { operations.acknowledge(message) }
+        container.isRunning shouldBe false
+    }
+
+    @Test
+    fun `isAutoStartup follows config`() = runTest {
+        val container = SqsListenerContainer(
+            endpoint(ListenerBean::plain),
+            template,
+            resolver,
+            SqsListenerConfig(autoStartup = false),
+            scope = this
+        )
+        container.isAutoStartup shouldBe false
+        container.isRunning shouldBe false
+    }
+
+    private class ListenerBean {
+        val plainCalls = mutableListOf<String>()
+        val messageCalls = mutableListOf<SqsMessage<String>>()
+        val gatedCalls = mutableListOf<String>()
+        var manualAction: suspend (Acknowledge) -> Unit = { }
+        var gate: suspend () -> Unit = { }
+
+        @SqsListener("orders")
+        fun plain(payload: String) {
+            plainCalls += payload
+        }
+
+        @SqsListener("orders")
+        suspend fun full(message: SqsMessage<String>) {
+            messageCalls += message
+        }
+
+        @SqsListener("orders")
+        suspend fun manual(message: SqsMessage<String>, ack: Acknowledge) {
+            messageCalls += message
+            manualAction(ack)
+        }
+
+        @SqsListener("orders")
+        fun boom(payload: String): Unit = throw IllegalStateException("boom")
+
+        @SqsListener("orders")
+        suspend fun gated(payload: String) {
+            gate()
+            gatedCalls += payload
+        }
+
+        @SqsListener("orders")
+        fun ackOnly(ack: Acknowledge) {
+        }
+
+        @SqsListener("orders")
+        fun twoPayloads(a: String, b: String) {
+        }
+
+        @SqsListener("orders")
+        fun <T> generic(payload: T) {
+        }
+
+        @SqsListener("orders")
+        fun star(message: SqsMessage<*>) {
+        }
+
+        @SqsListener("orders")
+        fun twoAcks(payload: String, a: Acknowledge, b: Acknowledge) {
+        }
+    }
+}
