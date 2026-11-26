# Task 21 review package

## git status (examples)
 M examples/build.gradle.kts
 M examples/src/main/kotlin/com/example/demo/internal/AutomaticMessageReceiver.kt
 D examples/src/main/kotlin/com/example/demo/internal/AwsConfig.kt
 D examples/src/main/kotlin/com/example/demo/internal/AwsProperties.kt
 M examples/src/main/kotlin/com/example/demo/internal/AwsSecretsManagementService.kt
 D examples/src/main/kotlin/com/example/demo/internal/S3ClientExtension.kt
 M examples/src/main/kotlin/com/example/demo/internal/S3StorageService.kt
 M examples/src/main/kotlin/com/example/demo/internal/SqsMessagingService.kt
 M examples/src/main/resources/application.properties
 M examples/src/test/kotlin/com/example/demo/AwsSecretsManagementServiceTest.kt
 D examples/src/test/kotlin/com/example/demo/LocalstackTestContainer.kt
 M examples/src/test/kotlin/com/example/demo/MessagingServiceTest.kt
 M examples/src/test/kotlin/com/example/demo/StorageServiceTest.kt

## diffstat
 examples/build.gradle.kts                          |  10 +-
 .../demo/internal/AutomaticMessageReceiver.kt      |  15 ++-
 .../kotlin/com/example/demo/internal/AwsConfig.kt  |  74 ---------------
 .../com/example/demo/internal/AwsProperties.kt     |  24 -----
 .../demo/internal/AwsSecretsManagementService.kt   |  18 ++--
 .../com/example/demo/internal/S3ClientExtension.kt | 101 ---------------------
 .../com/example/demo/internal/S3StorageService.kt  |  37 ++++++--
 .../example/demo/internal/SqsMessagingService.kt   |  37 +++-----
 examples/src/main/resources/application.properties |   9 +-
 .../demo/AwsSecretsManagementServiceTest.kt        |  29 ++++--
 .../com/example/demo/LocalstackTestContainer.kt    |  73 ---------------
 .../com/example/demo/MessagingServiceTest.kt       |  32 +++++--
 .../kotlin/com/example/demo/StorageServiceTest.kt  |  30 ++++--
 13 files changed, 140 insertions(+), 349 deletions(-)

## full diff
diff --git a/examples/build.gradle.kts b/examples/build.gradle.kts
index 968bd90..14a2634 100644
--- a/examples/build.gradle.kts
+++ b/examples/build.gradle.kts
@@ -1,40 +1,46 @@
 import com.adarshr.gradle.testlogger.theme.ThemeType.MOCHA
 import org.jetbrains.kotlin.gradle.dsl.JvmDefaultMode
 import org.jmailen.gradle.kotlinter.tasks.FormatTask
 import org.jmailen.gradle.kotlinter.tasks.LintTask
 
 plugins {
     alias(libs.plugins.springBoot)
     alias(libs.plugins.dependencyManagement)
     alias(libs.plugins.kotlinJvm)
     alias(libs.plugins.kotlinPluginSpring)
+    alias(libs.plugins.kotlinPluginSerialization)
     alias(libs.plugins.kotlinter)
     alias(libs.plugins.testLogger)
 }
 
 group = "com.example"
 version = "0.0.1-SNAPSHOT"
 
 repositories {
     mavenCentral()
 }
 
 dependencies {
     // spring libs
     implementation(libs.bundles.core)
 
     // kotlin support
     implementation(libs.bundles.kotlinSupport)
 
-    // aws sdk kotlin
-    implementation(libs.bundles.awsSdkKotlin)
+    // awskotlin spring boot starters (S3, Secrets Manager, SQS)
+    implementation(project(":starters:s3-starter"))
+    implementation(project(":starters:secrets-manager-starter"))
+    implementation(project(":starters:sqs-starter"))
+
+    // kotlinx converter runtime and @Serializable example payloads
+    implementation(libs.kotlinxSerializationJson)
 
     // okhttp
     implementation(libs.bundles.okhttp)
 
     // test libs
     testImplementation(libs.bundles.testcore)
     testImplementation(libs.bundles.testcontainers)
     testImplementation(libs.bundles.kotest)
     testImplementation(libs.bundles.mockk)
     testImplementation(libs.bundles.awaitility)
diff --git a/examples/src/main/kotlin/com/example/demo/internal/AutomaticMessageReceiver.kt b/examples/src/main/kotlin/com/example/demo/internal/AutomaticMessageReceiver.kt
index 27a7184..9a72948 100644
--- a/examples/src/main/kotlin/com/example/demo/internal/AutomaticMessageReceiver.kt
+++ b/examples/src/main/kotlin/com/example/demo/internal/AutomaticMessageReceiver.kt
@@ -1,22 +1,19 @@
 package com.example.demo.internal
 
-import com.example.demo.MessagingService
+import io.github.hantsy.awskotlin.sqs.listener.SqsListener
 import org.slf4j.LoggerFactory
-import org.springframework.scheduling.annotation.Scheduled
 import org.springframework.stereotype.Component
 
 @Component
-class AutomaticMessageReceiver(val messagingService: MessagingService) {
+class AutomaticMessageReceiver {
     companion object {
         private val log = LoggerFactory.getLogger(AutomaticMessageReceiver::class.java)
     }
 
-    @Scheduled(initialDelay = 500, fixedRate = 100)
-    suspend fun onMessageReceived() {
-        messagingService.receive(DEFAULT_SQS_QUEUE_NAME, String::class.java)
-            .forEach {
-                log.debug("received messaging service: $it")
-            }
+    // The starter registrar polls the queue and auto-deletes on successful return.
+    @SqsListener(SqsMessagingService.DEFAULT_SQS_QUEUE_NAME)
+    suspend fun onMessageReceived(message: String) {
+        log.debug("received messaging service: $message")
         log.debug("like an event listener, do side effect...")
     }
 }
diff --git a/examples/src/main/kotlin/com/example/demo/internal/AwsConfig.kt b/examples/src/main/kotlin/com/example/demo/internal/AwsConfig.kt
deleted file mode 100644
index 7d5554b..0000000
--- a/examples/src/main/kotlin/com/example/demo/internal/AwsConfig.kt
+++ /dev/null
@@ -1,74 +0,0 @@
-package com.example.demo.internal
-
-import aws.sdk.kotlin.runtime.auth.credentials.EnvironmentCredentialsProvider
-import aws.sdk.kotlin.runtime.auth.credentials.StaticCredentialsProvider
-import aws.sdk.kotlin.services.s3.S3Client
-import aws.sdk.kotlin.services.secretsmanager.SecretsManagerClient
-import aws.sdk.kotlin.services.sqs.SqsClient
-import aws.smithy.kotlin.runtime.auth.awscredentials.CachedCredentialsProvider
-import aws.smithy.kotlin.runtime.auth.awscredentials.Credentials
-import aws.smithy.kotlin.runtime.auth.awscredentials.CredentialsProvider
-import aws.smithy.kotlin.runtime.auth.awscredentials.CredentialsProviderChain
-import aws.smithy.kotlin.runtime.client.LogMode
-import aws.smithy.kotlin.runtime.net.url.Url
-import org.springframework.context.annotation.Bean
-import org.springframework.context.annotation.Configuration
-
-@Configuration
-class AwsConfig {
-    @Bean
-    fun awsCredentialsProvider(properties: AwsProperties): CredentialsProvider {
-        val chain = CredentialsProviderChain(
-            EnvironmentCredentialsProvider(),
-            StaticCredentialsProvider(
-                Credentials(
-                    secretAccessKey = properties.secretAccessKey,
-                    accessKeyId = properties.accessKeyId,
-                )
-            )
-        )
-
-        return CachedCredentialsProvider(chain)
-    }
-
-    // see: https://github.com/awslabs/aws-sdk-kotlin/issues/842
-    @Bean
-    fun awsS3Client(awsCredentialsProvider: CredentialsProvider, properties: AwsProperties): S3Client {
-        val endpoint = properties.s3?.endpoint ?: properties.endpoint
-        return S3Client {
-            credentialsProvider = awsCredentialsProvider
-            region = properties.region
-//            endpointProvider = properties.endpoint?.let { url ->
-//                S3EndpointProvider { Endpoint(url) }
-//            } ?: DefaultS3EndpointProvider()
-            endpointUrl = endpoint?.let { Url.parse(it) }
-            forcePathStyle = !endpoint.isNullOrBlank()
-            logMode = LogMode.LogRequestWithBody
-        }
-    }
-
-    @Bean
-    fun secretsManagerClient(
-        awsCredentialsProvider: CredentialsProvider,
-        properties: AwsProperties
-    ): SecretsManagerClient = SecretsManagerClient {
-        credentialsProvider = awsCredentialsProvider
-        region = properties.region
-//        endpointProvider = properties.endpoint?.let { url ->
-//            SecretsManagerEndpointProvider { Endpoint(url) }
-//        } ?: DefaultSecretsManagerEndpointProvider()
-        endpointUrl = (properties.secretsManager?.endpoint ?: properties.endpoint)?.let { Url.parse(it) }
-        // there is no forcePathStyle for SecretsManagerClient
-        // forcePathStyle = !properties.endpoint.isNullOrBlank()
-        logMode = LogMode.LogRequestWithBody
-    }
-
-    @Bean
-    fun sqsClient(awsCredentialsProvider: CredentialsProvider, properties: AwsProperties): SqsClient = SqsClient {
-        credentialsProvider = awsCredentialsProvider
-        region = properties.region
-        endpointUrl = (properties.sqs?.endpoint ?: properties.endpoint)?.let { Url.parse(it) }
-        // there is no forcePathStyle for SqsClient
-        logMode = LogMode.LogRequestWithBody
-    }
-}
diff --git a/examples/src/main/kotlin/com/example/demo/internal/AwsProperties.kt b/examples/src/main/kotlin/com/example/demo/internal/AwsProperties.kt
deleted file mode 100644
index 272a822..0000000
--- a/examples/src/main/kotlin/com/example/demo/internal/AwsProperties.kt
+++ /dev/null
@@ -1,24 +0,0 @@
-package com.example.demo.internal
-
-import org.springframework.boot.context.properties.ConfigurationProperties
-
-const val DEFAULT_BUCKET_NAME = "default"
-const val DEFAULT_SQS_QUEUE_NAME = "default"
-const val DEFAULT_REGION = "us-east-1"
-
-@ConfigurationProperties(prefix = "aws")
-data class AwsProperties(
-    val accessKeyId: String,
-    val secretAccessKey: String,
-    val region: String = DEFAULT_REGION,
-    val endpoint: String? = null,
-    val s3: S3Properties? = null,
-    val secretsManager: SecretsManagerProperties? = null,
-    val sqs: SqsProperties? = null,
-) {
-    data class S3Properties(val bucketName: String = DEFAULT_BUCKET_NAME, val endpoint: String? = null,)
-
-    data class SecretsManagerProperties(val endpoint: String? = null,)
-
-    data class SqsProperties(val queueName: String = DEFAULT_SQS_QUEUE_NAME, val endpoint: String? = null,)
-}
diff --git a/examples/src/main/kotlin/com/example/demo/internal/AwsSecretsManagementService.kt b/examples/src/main/kotlin/com/example/demo/internal/AwsSecretsManagementService.kt
index e11060e..cfa4946 100644
--- a/examples/src/main/kotlin/com/example/demo/internal/AwsSecretsManagementService.kt
+++ b/examples/src/main/kotlin/com/example/demo/internal/AwsSecretsManagementService.kt
@@ -1,49 +1,49 @@
 package com.example.demo.internal
 
 import aws.sdk.kotlin.services.secretsmanager.SecretsManagerClient
 import aws.sdk.kotlin.services.secretsmanager.model.CreateSecretRequest
-import aws.sdk.kotlin.services.secretsmanager.model.GetSecretValueRequest
 import com.example.demo.SecretsManagementService
 import com.example.demo.SecretsManagementServiceException
+import io.github.hantsy.awskotlin.secretsmanager.SecretsManagerTemplate
 import org.slf4j.LoggerFactory
 import org.springframework.stereotype.Service
 
 @Service
-class AwsSecretsManagementService(private val client: SecretsManagerClient) : SecretsManagementService {
+class AwsSecretsManagementService(
+    private val template: SecretsManagerTemplate,
+    private val client: SecretsManagerClient,
+) : SecretsManagementService {
     companion object {
         private val log = LoggerFactory.getLogger(SecretsManagementService::class.java)
     }
 
     override suspend fun write(secretName: String, secretValue: String, secretDescription: String?): String? {
+        // The template does not expose description/forceOverwriteReplicaSecret,
+        // so keep the raw starter-provided client request for writes.
         val request = CreateSecretRequest {
             name = secretName
             description = secretDescription
             secretString = secretValue
             forceOverwriteReplicaSecret = true // override the duplicated secret name
         }
 
         log.debug("writing secrets: $secretName, $secretValue, $secretDescription")
         val response = try {
             client.createSecret(request)
         } catch (e: Exception) {
             log.error("error occurred when writing secrets: ${e.message}")
             throw SecretsManagementServiceException(e.message ?: "Failed to write secrets:")
         }
         return response.arn
     }
 
     override suspend fun read(secretName: String): String? {
-        val valueRequest = GetSecretValueRequest {
-            secretId = secretName
-        }
-
         log.debug("read secrets by name: $secretName")
-        val response = try {
-            client.getSecretValue(valueRequest)
+        return try {
+            template.secret(secretName).get().secretString
         } catch (e: Exception) {
             log.error("error occurred when reading secrets: ${e.message}")
             throw SecretsManagementServiceException(e.message ?: "Failed to read secrets")
         }
-        return response.secretString
     }
 }
diff --git a/examples/src/main/kotlin/com/example/demo/internal/S3ClientExtension.kt b/examples/src/main/kotlin/com/example/demo/internal/S3ClientExtension.kt
deleted file mode 100644
index f262156..0000000
--- a/examples/src/main/kotlin/com/example/demo/internal/S3ClientExtension.kt
+++ /dev/null
@@ -1,101 +0,0 @@
-package com.example.demo.internal
-
-import aws.sdk.kotlin.services.s3.S3Client
-import aws.sdk.kotlin.services.s3.model.CreateBucketRequest
-import aws.sdk.kotlin.services.s3.model.DeleteObjectRequest
-import aws.sdk.kotlin.services.s3.model.GetObjectRequest
-import aws.sdk.kotlin.services.s3.model.HeadBucketRequest
-import aws.sdk.kotlin.services.s3.model.PutObjectRequest
-import aws.smithy.kotlin.runtime.content.ByteStream
-import aws.smithy.kotlin.runtime.content.toByteArray
-import com.example.demo.toByteArray
-import com.example.demo.toFluxDataBuffer
-import org.springframework.core.io.buffer.DataBuffer
-import org.springframework.http.MediaType
-import org.springframework.http.MediaTypeFactory
-import reactor.core.publisher.Flux
-
-class S3ClientException(message: String) : RuntimeException(message)
-
-suspend fun S3Client.delete(bucketName: String, resourceKey: String) {
-    val request = DeleteObjectRequest {
-        bucket = bucketName
-        key = resourceKey
-    }
-
-    try {
-        this.deleteObject(request)
-    } catch (e: Exception) {
-        throw S3ClientException(e.message ?: "Failed to delete object $resourceKey")
-    }
-}
-
-suspend fun S3Client.bucketExists(s3bucket: String) = try {
-    headBucket(HeadBucketRequest { bucket = s3bucket })
-    true
-} catch (e: Exception) {
-    e.printStackTrace()
-    false
-}
-
-suspend fun S3Client.createBucketIfNotExists(bucketName: String) {
-    if (!this.bucketExists(bucketName)) {
-        val request =
-            CreateBucketRequest {
-                bucket = bucketName
-            }
-        try {
-            this.createBucket(request)
-        } catch (e: Exception) {
-            e.printStackTrace()
-            throw S3ClientException(e.message ?: "Failed to create bucket $bucketName")
-        }
-    }
-}
-
-suspend fun S3Client.store(bucketName: String, resourceKey: String, data: ByteArray) {
-    this.createBucketIfNotExists(bucketName)
-    val mediaType = MediaTypeFactory.getMediaType(resourceKey)
-        .orElseGet { MediaType.APPLICATION_OCTET_STREAM }
-
-    val request = PutObjectRequest {
-        bucket = bucketName
-        body = ByteStream.fromBytes(data)
-        key = resourceKey
-        contentType = mediaType.toString()
-    }
-    val result = try {
-        this.putObject(request)
-    } catch (e: Exception) {
-        throw S3ClientException(e.message ?: "Failed to store object $resourceKey")
-    }
-    println("store object to $bucketName: ${result.eTag}")
-}
-
-suspend fun S3Client.store(bucketName: String, resourceKey: String, data: Flux<DataBuffer>) {
-    this.store(bucketName, resourceKey, data.toByteArray())
-}
-
-suspend fun S3Client.retrieve(bucketName: String, resourceKey: String): ByteArray? {
-    val request = GetObjectRequest {
-        bucket = bucketName
-        key = resourceKey
-    }
-
-    return try {
-        this.getObject(request) { result ->
-            val body = result.body ?: return@getObject null
-            val bytes = body.toByteArray()
-
-            bytes
-        }
-    } catch (e: Exception) {
-        throw S3ClientException(e.message ?: "Failed to retrieve object $resourceKey")
-    }
-}
-
-suspend fun S3Client.retrieveAsFluxDataBuffer(bucketName: String, resourceKey: String): Flux<DataBuffer> {
-    return this.retrieve(bucketName, resourceKey)?.let {
-        return it.toFluxDataBuffer()
-    } ?: Flux.empty()
-}
diff --git a/examples/src/main/kotlin/com/example/demo/internal/S3StorageService.kt b/examples/src/main/kotlin/com/example/demo/internal/S3StorageService.kt
index 0ec4078..21a7297 100644
--- a/examples/src/main/kotlin/com/example/demo/internal/S3StorageService.kt
+++ b/examples/src/main/kotlin/com/example/demo/internal/S3StorageService.kt
@@ -1,35 +1,54 @@
 package com.example.demo.internal
 
 import aws.sdk.kotlin.services.s3.S3Client
+import aws.sdk.kotlin.services.s3.createBucket
+import aws.smithy.kotlin.runtime.content.ByteStream
 import com.example.demo.StorageService
 import com.example.demo.StorageServiceException
+import com.example.demo.toByteArray
+import com.example.demo.toFluxDataBuffer
+import io.github.hantsy.awskotlin.s3.S3Template
+import org.springframework.beans.factory.annotation.Value
 import org.springframework.core.io.buffer.DataBuffer
+import org.springframework.http.MediaType
+import org.springframework.http.MediaTypeFactory
 import org.springframework.stereotype.Service
 import reactor.core.publisher.Flux
 
 @Service
-class S3StorageService(private val properties: AwsProperties, private val client: S3Client,) : StorageService {
-    val bucketName = properties.s3?.bucketName ?: DEFAULT_BUCKET_NAME
-
+class S3StorageService(
+    private val template: S3Template,
+    private val client: S3Client,
+    @Value("\${example.s3.bucket-name:uploaded-files}") private val bucketName: String,
+) : StorageService {
     override suspend fun store(resourceKey: String, data: Flux<DataBuffer>) {
         try {
-            client.store(bucketName, resourceKey, data)
-        } catch (e: S3ClientException) {
+            // The template has no bucket creation, so use the starter-provided client for it.
+            if (!template.bucket(bucketName).exists()) {
+                client.createBucket { bucket = bucketName }
+            }
+            val contentType = MediaTypeFactory.getMediaType(resourceKey)
+                .orElseGet { MediaType.APPLICATION_OCTET_STREAM }
+                .toString()
+            template.bucket(bucketName).`object`(resourceKey)
+                .put(ByteStream.fromBytes(data.toByteArray()), contentType = contentType)
+        } catch (e: Exception) {
             throw StorageServiceException(e.message ?: "Failed to upload object $resourceKey")
         }
     }
 
     override suspend fun retrieve(resourceKey: String): Flux<DataBuffer>? = try {
-        client.retrieveAsFluxDataBuffer(bucketName, resourceKey)
-    } catch (e: S3ClientException) {
+        template.bucket(bucketName).`object`(resourceKey).get()?.toFluxDataBuffer() ?: Flux.empty()
+    } catch (e: Exception) {
+        // Keep the SDK message (for example "The specified key does not exist") intact.
         throw StorageServiceException(e.message ?: "Failed to download object $resourceKey")
     }
 
     override suspend fun delete(resourceKey: String) {
         try {
-            client.delete(bucketName, resourceKey)
-        } catch (e: S3ClientException) {
+            template.bucket(bucketName).`object`(resourceKey).delete()
+        } catch (e: Exception) {
             throw StorageServiceException(e.message ?: "Failed to delete object $resourceKey")
         }
     }
 }
diff --git a/examples/src/main/kotlin/com/example/demo/internal/SqsMessagingService.kt b/examples/src/main/kotlin/com/example/demo/internal/SqsMessagingService.kt
index 5ebf6f6..f8f4a51 100644
--- a/examples/src/main/kotlin/com/example/demo/internal/SqsMessagingService.kt
+++ b/examples/src/main/kotlin/com/example/demo/internal/SqsMessagingService.kt
@@ -1,46 +1,48 @@
 package com.example.demo.internal
 
 import aws.sdk.kotlin.services.sqs.SqsClient
 import aws.sdk.kotlin.services.sqs.model.CreateQueueRequest
 import aws.sdk.kotlin.services.sqs.model.GetQueueUrlRequest
 import aws.sdk.kotlin.services.sqs.model.ReceiveMessageRequest
-import aws.sdk.kotlin.services.sqs.model.SendMessageRequest
 import com.example.demo.MessagingService
 import com.example.demo.MessagingServiceException
+import io.github.hantsy.awskotlin.sqs.SqsMessageConverter
+import io.github.hantsy.awskotlin.sqs.SqsTemplate
 import jakarta.annotation.PostConstruct
 import kotlinx.coroutines.Dispatchers
 import kotlinx.coroutines.runBlocking
 import kotlinx.coroutines.withContext
 import org.slf4j.LoggerFactory
 import org.springframework.stereotype.Service
-import tools.jackson.databind.ObjectMapper
+import kotlin.time.Duration.Companion.seconds
 
 @Service
 class SqsMessagingService(
-    private val properties: AwsProperties,
+    private val template: SqsTemplate,
     private val sqsClient: SqsClient,
-    private val objectMapper: ObjectMapper,
+    private val converter: SqsMessageConverter,
 ) : MessagingService {
     companion object {
         private val log = LoggerFactory.getLogger(SqsMessagingService::class.java)
+        const val DEFAULT_SQS_QUEUE_NAME = "default"
         const val MAX_NUMBER_OF_RECEIVED_MESSAGES = 5
-        const val DELAY_IN_SECONDS = 1
     }
 
     @PostConstruct
     fun init() = runBlocking {
         log.debug("initializing SqsMessagingService...")
-        val queueNameVal = createQueue(properties.sqs?.queueName ?: DEFAULT_SQS_QUEUE_NAME)
+        val queueNameVal = createQueue(DEFAULT_SQS_QUEUE_NAME)
         log.debug("created queue: $queueNameVal")
     }
 
+    // The template has no queue creation, so keep the raw starter-provided client here.
     override suspend fun createQueue(queueNameVal: String): String {
         return getQueue(queueNameVal) ?: run {
             log.debug("queue $queueNameVal does not exist, create one")
             val createQueueRequest =
                 CreateQueueRequest {
                     queueName = queueNameVal
                 }
 
             val createQueueResponse =
                 withContext(Dispatchers.IO) {
@@ -67,40 +69,31 @@ class SqsMessagingService(
                 try {
                     sqsClient.getQueueUrl(getQueueUrlRequest)
                 } catch (_: Exception) {
                     null
                 }
             }
         return getQueueUrlResponse?.queueUrl
     }
 
     override suspend fun send(queueNameVal: String, message: Any) {
-        val getQueueUrl = getQueue(queueNameVal)
-
-        val sendMessageRequest =
-            SendMessageRequest {
-                queueUrl = getQueueUrl
-                delaySeconds = DELAY_IN_SECONDS
-                messageBody = objectMapper.writeValueAsString(message)
-            }
-
         val sendMessageResponse =
-            withContext(Dispatchers.IO) {
-                try {
-                    sqsClient.sendMessage(sendMessageRequest)
-                } catch (e: Exception) {
-                    throw MessagingServiceException(e.message ?: "Failed to send message")
-                }
+            try {
+                template.destination(queueNameVal).send(message, delay = 1.seconds)
+            } catch (e: Exception) {
+                throw MessagingServiceException(e.message ?: "Failed to send message")
             }
         log.debug("message ${sendMessageResponse.messageId} is sent out")
     }
 
+    // The template has no batch receive, so keep the raw client request and convert
+    // each body with the starter-provided converter. Messages are not deleted here.
     override suspend fun <T : Any> receive(queueNameVal: String, clazz: Class<out T>): List<T> {
         val getQueueUrl = getQueue(queueNameVal)
 
         val receiveMessageRequest =
             ReceiveMessageRequest {
                 queueUrl = getQueueUrl
                 maxNumberOfMessages = MAX_NUMBER_OF_RECEIVED_MESSAGES
             }
 
         val receiveMessageResponse =
@@ -108,14 +101,14 @@ class SqsMessagingService(
                 try {
                     sqsClient.receiveMessage(receiveMessageRequest)
                 } catch (e: Exception) {
                     throw MessagingServiceException(e.message ?: "Failed to receive message")
                 }
             }
         return receiveMessageResponse
             .messages
             ?.mapNotNull { message ->
                 log.debug("received message: $message")
-                runCatching { objectMapper.readValue(message.body, clazz) }.getOrNull()
+                runCatching { converter.fromBody(message.body ?: "", clazz) }.getOrNull()
             } ?: emptyList()
     }
 }
diff --git a/examples/src/main/resources/application.properties b/examples/src/main/resources/application.properties
index 2c7eeb6..38730dd 100644
--- a/examples/src/main/resources/application.properties
+++ b/examples/src/main/resources/application.properties
@@ -1,5 +1,4 @@
-# Aws S3 Properties
-aws.accessKeyId=test-access-key
-aws.secretAccessKey=test-secret-key
-aws.region=us-east-1
-aws.s3.bucketName=uploaded-files
+awskotlin.access-key-id=test-access-key
+awskotlin.secret-access-key=test-secret-key
+awskotlin.region=us-east-1
+example.s3.bucket-name=uploaded-files
\ No newline at end of file
diff --git a/examples/src/test/kotlin/com/example/demo/AwsSecretsManagementServiceTest.kt b/examples/src/test/kotlin/com/example/demo/AwsSecretsManagementServiceTest.kt
index 73988eb..fc2d54d 100644
--- a/examples/src/test/kotlin/com/example/demo/AwsSecretsManagementServiceTest.kt
+++ b/examples/src/test/kotlin/com/example/demo/AwsSecretsManagementServiceTest.kt
@@ -1,43 +1,60 @@
 package com.example.demo
 
 import com.example.demo.AwsSecretsManagementServiceTest.AwsSecretsManagementServiceTestConfig
-import com.example.demo.internal.AwsConfig
-import com.example.demo.internal.AwsProperties
 import com.example.demo.internal.AwsSecretsManagementService
+import io.github.hantsy.awskotlin.core.autoconfigure.AwsKotlinAutoConfiguration
+import io.github.hantsy.awskotlin.secretsmanager.autoconfigure.SecretsManagerAutoConfiguration
 import io.kotest.matchers.shouldBe
 import io.kotest.matchers.shouldNotBe
 import org.junit.jupiter.api.Test
+import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable
 import org.slf4j.LoggerFactory
 import org.springframework.beans.factory.annotation.Autowired
-import org.springframework.boot.context.properties.EnableConfigurationProperties
+import org.springframework.boot.autoconfigure.ImportAutoConfiguration
 import org.springframework.boot.test.context.SpringBootTest
 import org.springframework.context.annotation.Configuration
 import org.springframework.context.annotation.Import
 import org.springframework.test.context.DynamicPropertyRegistry
 import org.springframework.test.context.DynamicPropertySource
+import org.testcontainers.junit.jupiter.Container
+import org.testcontainers.junit.jupiter.Testcontainers
+import org.testcontainers.localstack.LocalStackContainer
+import org.testcontainers.utility.DockerImageName
 
 @SpringBootTest(classes = [AwsSecretsManagementServiceTestConfig::class])
+@Testcontainers
+@EnabledIfEnvironmentVariable(named = "LOCALSTACK_AUTH_TOKEN", matches = ".+")
 class AwsSecretsManagementServiceTest {
     companion object {
         private val log = LoggerFactory.getLogger(AwsSecretsManagementServiceTest::class.java)
 
+        @JvmField
+        @Container
+        val localstack: LocalStackContainer =
+            LocalStackContainer(DockerImageName.parse("localstack/localstack:4"))
+                .withServices("s3", "sqs", "secretsmanager")
+                .apply {
+                    System.getenv("LOCALSTACK_AUTH_TOKEN")?.takeIf { it.isNotBlank() }
+                        ?.let { withEnv("LOCALSTACK_AUTH_TOKEN", it) }
+                }
+
         @JvmStatic
         @DynamicPropertySource
         fun localstackProperties(registry: DynamicPropertyRegistry) {
-            registry.add("aws.endpoint") { LocalstackTestContainer.endpoint }
+            registry.add("awskotlin.endpoint") { localstack.endpoint.toString() }
         }
     }
 
     @Configuration
-    @Import(AwsSecretsManagementService::class, AwsConfig::class)
-    @EnableConfigurationProperties(AwsProperties::class)
+    @Import(AwsSecretsManagementService::class)
+    @ImportAutoConfiguration(AwsKotlinAutoConfiguration::class, SecretsManagerAutoConfiguration::class)
     class AwsSecretsManagementServiceTestConfig
 
     @Autowired
     lateinit var client: SecretsManagementService
 
     // JUnit 6 supports suspend test functions natively.
     @Test
     suspend fun `read and write secrets`() {
         val arn = client.write("/mysecrests/test", "test", "")
         log.debug("writing secrets, return arn: $arn")
diff --git a/examples/src/test/kotlin/com/example/demo/LocalstackTestContainer.kt b/examples/src/test/kotlin/com/example/demo/LocalstackTestContainer.kt
deleted file mode 100644
index 3a492f8..0000000
--- a/examples/src/test/kotlin/com/example/demo/LocalstackTestContainer.kt
+++ /dev/null
@@ -1,73 +0,0 @@
-package com.example.demo
-
-import org.testcontainers.localstack.LocalStackContainer
-import org.testcontainers.utility.DockerImageName
-import java.net.URI
-
-/**
- * A single LocalStack container shared by all the integration tests of this module.
- *
- * LocalStack has no Spring Boot service connection, so the endpoint is injected with
- * [org.springframework.test.context.DynamicPropertySource] instead.
- *
- * The license token is taken from the environment, so that CI can run the tests
- * against a licensed LocalStack image.
- */
-object LocalstackTestContainer {
-    /**
-     * LocalStack 4 requires a license token, set LOCALSTACK_AUTH_TOKEN to run it.
-     */
-    private const val IMAGE = "localstack/localstack:4"
-
-    private val authToken = System.getenv("LOCALSTACK_AUTH_TOKEN")?.takeIf { it.isNotBlank() }
-
-    private val requiredServices = listOf("s3", "sqs", "secretsmanager")
-    private const val READY_TIMEOUT_MILLIS = 120_000L
-    private const val POLL_INTERVAL_MILLIS = 500L
-
-    private val container: LocalStackContainer =
-        LocalStackContainer(DockerImageName.parse(IMAGE)).also { localStack ->
-            authToken?.let { localStack.withEnv("LOCALSTACK_AUTH_TOKEN", it) }
-            localStack.start()
-        }
-
-    /**
-     * The endpoint of the running LocalStack instance, for example http://localhost:32768.
-     */
-    val endpoint: String
-        get() = "http://${container.host}:${container.firstMappedPort}"
-
-    init {
-        Runtime.getRuntime().addShutdownHook(Thread { container.stop() })
-        awaitServices()
-    }
-
-    /**
-     * Waits until the AWS services used by the tests are actually served by the container.
-     */
-    private fun awaitServices() {
-        val deadline = System.currentTimeMillis() + READY_TIMEOUT_MILLIS
-        while (System.currentTimeMillis() < deadline) {
-            if (servicesReady()) {
-                return
-            }
-            Thread.sleep(POLL_INTERVAL_MILLIS)
-        }
-        throw IllegalStateException("LocalStack did not become ready within $READY_TIMEOUT_MILLIS ms")
-    }
-
-    private fun servicesReady(): Boolean {
-        val body =
-            runCatching {
-                URI("$endpoint/_localstack/health")
-                    .toURL()
-                    .openStream()
-                    .bufferedReader()
-                    .use { it.readText() }
-            }.getOrNull() ?: return false
-
-        return requiredServices.all {
-            body.contains("\"$it\": \"available\"") || body.contains("\"$it\": \"running\"")
-        }
-    }
-}
diff --git a/examples/src/test/kotlin/com/example/demo/MessagingServiceTest.kt b/examples/src/test/kotlin/com/example/demo/MessagingServiceTest.kt
index e3a40cb..5677705 100644
--- a/examples/src/test/kotlin/com/example/demo/MessagingServiceTest.kt
+++ b/examples/src/test/kotlin/com/example/demo/MessagingServiceTest.kt
@@ -1,54 +1,70 @@
 package com.example.demo
 
-import com.example.demo.internal.AwsConfig
-import com.example.demo.internal.AwsProperties
 import com.example.demo.internal.SqsMessagingService
+import io.github.hantsy.awskotlin.core.autoconfigure.AwsKotlinAutoConfiguration
+import io.github.hantsy.awskotlin.sqs.autoconfigure.SqsAutoConfiguration
 import io.kotest.assertions.nondeterministic.eventually
 import io.kotest.inspectors.forAny
 import io.kotest.matchers.shouldBe
+import kotlinx.serialization.Serializable
 import org.junit.jupiter.api.Test
+import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable
 import org.springframework.beans.factory.annotation.Autowired
 import org.springframework.boot.autoconfigure.ImportAutoConfiguration
-import org.springframework.boot.context.properties.EnableConfigurationProperties
-import org.springframework.boot.jackson.autoconfigure.JacksonAutoConfiguration
 import org.springframework.boot.test.context.SpringBootTest
 import org.springframework.context.annotation.Configuration
 import org.springframework.context.annotation.Import
 import org.springframework.test.context.DynamicPropertyRegistry
 import org.springframework.test.context.DynamicPropertySource
+import org.testcontainers.junit.jupiter.Container
+import org.testcontainers.junit.jupiter.Testcontainers
+import org.testcontainers.localstack.LocalStackContainer
+import org.testcontainers.utility.DockerImageName
 import kotlin.time.Duration.Companion.seconds
 
 @SpringBootTest(classes = [MessagingServiceTest.TestConfig::class])
+@Testcontainers
+@EnabledIfEnvironmentVariable(named = "LOCALSTACK_AUTH_TOKEN", matches = ".+")
 class MessagingServiceTest @Autowired constructor(val messagingService: MessagingService) {
     companion object {
+        @JvmField
+        @Container
+        val localstack: LocalStackContainer =
+            LocalStackContainer(DockerImageName.parse("localstack/localstack:4"))
+                .withServices("s3", "sqs", "secretsmanager")
+                .apply {
+                    System.getenv("LOCALSTACK_AUTH_TOKEN")?.takeIf { it.isNotBlank() }
+                        ?.let { withEnv("LOCALSTACK_AUTH_TOKEN", it) }
+                }
+
         @JvmStatic
         @DynamicPropertySource
         fun localstackProperties(registry: DynamicPropertyRegistry) {
-            registry.add("aws.endpoint") { LocalstackTestContainer.endpoint }
+            registry.add("awskotlin.endpoint") { localstack.endpoint.toString() }
         }
     }
 
     @Configuration
-    @Import(SqsMessagingService::class, AwsConfig::class)
-    @EnableConfigurationProperties(AwsProperties::class)
-    @ImportAutoConfiguration(JacksonAutoConfiguration::class)
+    @Import(SqsMessagingService::class)
+    @ImportAutoConfiguration(AwsKotlinAutoConfiguration::class, SqsAutoConfiguration::class)
     internal class TestConfig
 
     // JUnit 6 supports suspend test functions natively.
     @Test
     suspend fun `send and receive message`() {
         messagingService.createQueue("test")
 
         eventually(5.seconds) {
             messagingService.send("test", MyMessage("Hello, SQS!"))
         }
 
         eventually(5.seconds) {
             messagingService.receive("test", MyMessage::class.java).forAny {
                 it.text shouldBe "Hello, SQS!"
             }
         }
     }
 
+    @Serializable
     data class MyMessage(val text: String)
 }
diff --git a/examples/src/test/kotlin/com/example/demo/StorageServiceTest.kt b/examples/src/test/kotlin/com/example/demo/StorageServiceTest.kt
index 19a316e..d5ce05c 100644
--- a/examples/src/test/kotlin/com/example/demo/StorageServiceTest.kt
+++ b/examples/src/test/kotlin/com/example/demo/StorageServiceTest.kt
@@ -1,50 +1,66 @@
 package com.example.demo
 
 import com.example.demo.StorageServiceTest.StorageServiceTestConfig
-import com.example.demo.internal.AwsConfig
-import com.example.demo.internal.AwsProperties
 import com.example.demo.internal.S3StorageService
+import io.github.hantsy.awskotlin.core.autoconfigure.AwsKotlinAutoConfiguration
+import io.github.hantsy.awskotlin.s3.autoconfigure.S3AutoConfiguration
 import io.kotest.assertions.nondeterministic.eventually
 import io.kotest.matchers.shouldBe
 import io.kotest.matchers.shouldNotBe
 import io.kotest.matchers.string.shouldStartWith
 import kotlinx.coroutines.Dispatchers
 import kotlinx.coroutines.withContext
 import org.junit.jupiter.api.Test
+import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable
 import org.springframework.beans.factory.annotation.Autowired
-import org.springframework.boot.context.properties.EnableConfigurationProperties
+import org.springframework.boot.autoconfigure.ImportAutoConfiguration
 import org.springframework.boot.test.context.SpringBootTest
 import org.springframework.context.annotation.Configuration
 import org.springframework.context.annotation.Import
 import org.springframework.core.io.ClassPathResource
 import org.springframework.test.context.DynamicPropertyRegistry
 import org.springframework.test.context.DynamicPropertySource
 import org.springframework.util.FileCopyUtils
+import org.testcontainers.junit.jupiter.Container
+import org.testcontainers.junit.jupiter.Testcontainers
+import org.testcontainers.localstack.LocalStackContainer
+import org.testcontainers.utility.DockerImageName
 import java.nio.file.Files
 import java.util.*
-import kotlin.time.Duration.Companion.milliseconds
 import kotlin.time.Duration.Companion.seconds
 
 @SpringBootTest(classes = [StorageServiceTestConfig::class])
+@Testcontainers
+@EnabledIfEnvironmentVariable(named = "LOCALSTACK_AUTH_TOKEN", matches = ".+")
 class StorageServiceTest @Autowired constructor(val storageService: StorageService) {
     companion object {
+        @JvmField
+        @Container
+        val localstack: LocalStackContainer =
+            LocalStackContainer(DockerImageName.parse("localstack/localstack:4"))
+                .withServices("s3", "sqs", "secretsmanager")
+                .apply {
+                    System.getenv("LOCALSTACK_AUTH_TOKEN")?.takeIf { it.isNotBlank() }
+                        ?.let { withEnv("LOCALSTACK_AUTH_TOKEN", it) }
+                }
+
         @JvmStatic
         @DynamicPropertySource
         fun localstackProperties(registry: DynamicPropertyRegistry) {
-            registry.add("aws.endpoint") { LocalstackTestContainer.endpoint }
+            registry.add("awskotlin.endpoint") { localstack.endpoint.toString() }
         }
     }
 
     @Configuration
-    @Import(S3StorageService::class, AwsConfig::class)
-    @EnableConfigurationProperties(AwsProperties::class)
+    @Import(S3StorageService::class)
+    @ImportAutoConfiguration(AwsKotlinAutoConfiguration::class, S3AutoConfiguration::class)
     class StorageServiceTestConfig
 
     // JUnit 6 supports suspend test functions natively.
     @Test
     suspend fun `store text files`() {
         val id = UUID.randomUUID().toString()
 
         eventually(10.seconds) {
             storageService.store(id, "Hello World".toByteArray().toFluxDataBuffer())
         }
