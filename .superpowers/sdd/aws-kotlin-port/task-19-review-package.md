# Task 19 review package

## diff stat
 sns/build.gradle.kts                               |  59 +++++++
 sns/gradle.properties                              |   5 +
 .../github/hantsy/awskotlin/sns/SnsNotification.kt |  35 ++++
 .../awskotlin/sns/SnsNotificationParseException.kt |   8 +
 .../hantsy/awskotlin/sns/SnsNotificationParser.kt  |  68 ++++++++
 .../hantsy/awskotlin/sns/SnsNotificationType.kt    |  15 ++
 .../io/github/hantsy/awskotlin/sns/SnsTemplate.kt  |  21 +++
 .../github/hantsy/awskotlin/sns/TopicOperations.kt |  67 ++++++++
 .../awskotlin/sns/SnsNotificationParserTest.kt     | 184 +++++++++++++++++++++
 .../awskotlin/sns/SnsSqsFanOutLocalStackTest.kt    | 182 ++++++++++++++++++++
 .../github/hantsy/awskotlin/sns/SnsTemplateTest.kt | 110 ++++++++++++
 .../sns/TopicOperationsSubscribeSqsTest.kt         |  68 ++++++++
 12 files changed, 822 insertions(+)

## diff
diff --git a/sns/build.gradle.kts b/sns/build.gradle.kts
new file mode 100644
index 0000000..30ebe4f
--- /dev/null
+++ b/sns/build.gradle.kts
@@ -0,0 +1,59 @@
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
+base { archivesName.set("awskotlin-spring-sns") }
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
+    api(libs.awsSdkKotlinSns)
+    implementation(libs.kotlinStdlib)
+    implementation(libs.kotlinCoroutinesCore)
+    implementation(libs.jacksonDatabind)
+    testImplementation(libs.awsSqs)
+    testImplementation(libs.testcontainersJunitJupiter)
+    testImplementation(libs.testcontainersLocalstack)
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
\ No newline at end of file
diff --git a/sns/gradle.properties b/sns/gradle.properties
new file mode 100644
index 0000000..92b73fb
--- /dev/null
+++ b/sns/gradle.properties
@@ -0,0 +1,5 @@
+# Workaround for KGP 2.4.10 + Gradle 9.9.2: Gradle ships Kotlin 2.4.21 compiler classes
+# in its parent classloader, which breaks the Build Tools API path used by kapt stub
+# generation (NoSuchMethodError: ArgumentsToStrings.toArgumentStrings$default).
+# The legacy compiler runner avoids that path. Deprecated in KGP 2.4.x, removed in 2.5.0.
+kotlin.compiler.runViaBuildToolsApi=false
\ No newline at end of file
diff --git a/sns/src/main/kotlin/io/github/hantsy/awskotlin/sns/SnsNotification.kt b/sns/src/main/kotlin/io/github/hantsy/awskotlin/sns/SnsNotification.kt
new file mode 100644
index 0000000..fb54778
--- /dev/null
+++ b/sns/src/main/kotlin/io/github/hantsy/awskotlin/sns/SnsNotification.kt
@@ -0,0 +1,35 @@
+package io.github.hantsy.awskotlin.sns
+
+/**
+ * An SNS JSON notification envelope as delivered to subscribers (for example an SQS queue
+ * subscribed to a topic without raw message delivery).
+ *
+ * Field names follow the SNS envelope keys: `Type`, `MessageId`, `TopicArn`, `Message`,
+ * `Subject`, `Timestamp`, `SignatureVersion`, `Signature`, `SigningCertURL`,
+ * `UnsubscribeURL` and `Token`. Obtain instances with [SnsNotificationParser.parse].
+ *
+ * @property type the envelope `Type`.
+ * @property messageId unique identifier of the notification.
+ * @property topicArn ARN of the topic the message was published to.
+ * @property message the message body as published (still a String; decode it yourself if it is JSON).
+ * @property subject optional subject supplied at publish time.
+ * @property timestamp ISO-8601 publish time, kept as the raw string.
+ * @property signatureVersion version of the signature algorithm.
+ * @property signature base64-encoded signature of the envelope.
+ * @property signingCertUrl URL of the certificate used to sign the envelope.
+ * @property unsubscribeUrl URL for unsubscribing the endpoint.
+ * @property token confirmation token present on subscription/unsubscribe confirmation messages.
+ */
+data class SnsNotification(
+    val type: SnsNotificationType,
+    val messageId: String,
+    val topicArn: String,
+    val message: String,
+    val subject: String? = null,
+    val timestamp: String? = null,
+    val signatureVersion: String? = null,
+    val signature: String? = null,
+    val signingCertUrl: String? = null,
+    val unsubscribeUrl: String? = null,
+    val token: String? = null
+)
diff --git a/sns/src/main/kotlin/io/github/hantsy/awskotlin/sns/SnsNotificationParseException.kt b/sns/src/main/kotlin/io/github/hantsy/awskotlin/sns/SnsNotificationParseException.kt
new file mode 100644
index 0000000..b4bf44c
--- /dev/null
+++ b/sns/src/main/kotlin/io/github/hantsy/awskotlin/sns/SnsNotificationParseException.kt
@@ -0,0 +1,8 @@
+package io.github.hantsy.awskotlin.sns
+
+/**
+ * Thrown when a message body cannot be parsed as an SNS JSON notification envelope:
+ * invalid JSON, a missing/unknown `Type`, or a missing required `MessageId`, `TopicArn`
+ * or `Message` field. The underlying Jackson error, when present, is preserved as the cause.
+ */
+class SnsNotificationParseException(message: String, cause: Throwable? = null) : RuntimeException(message, cause)
diff --git a/sns/src/main/kotlin/io/github/hantsy/awskotlin/sns/SnsNotificationParser.kt b/sns/src/main/kotlin/io/github/hantsy/awskotlin/sns/SnsNotificationParser.kt
new file mode 100644
index 0000000..f94ad0c
--- /dev/null
+++ b/sns/src/main/kotlin/io/github/hantsy/awskotlin/sns/SnsNotificationParser.kt
@@ -0,0 +1,68 @@
+package io.github.hantsy.awskotlin.sns
+
+import tools.jackson.core.JacksonException
+import tools.jackson.databind.JsonNode
+import tools.jackson.databind.ObjectMapper
+import tools.jackson.databind.json.JsonMapper
+
+/**
+ * Parses the SNS JSON notification envelope delivered to subscribers.
+ *
+ * Typical use is SNS -> SQS fan-out: subscribe a queue with
+ * [TopicOperations.subscribeSqs] (`rawMessageDelivery = false`, the default), receive the
+ * envelope as a String in an SQS listener, and call [parse] to obtain an [SnsNotification].
+ *
+ * Unknown envelope fields are ignored. A missing or unknown `Type`, or a missing required
+ * `MessageId`, `TopicArn` or `Message` field, raises [SnsNotificationParseException].
+ */
+object SnsNotificationParser {
+    private val mapper: ObjectMapper = JsonMapper.builder().build()
+
+    /**
+     * Parses [body] as an SNS JSON notification envelope.
+     *
+     * @throws SnsNotificationParseException if the body is not valid JSON, is not a JSON
+     * object, has a missing/unknown `Type`, or lacks a required string field.
+     */
+    fun parse(body: String): SnsNotification {
+        val root =
+            try {
+                mapper.readTree(body)
+            } catch (e: JacksonException) {
+                throw SnsNotificationParseException("SNS notification envelope is not valid JSON", e)
+            }
+        if (root == null || !root.isObject) {
+            throw SnsNotificationParseException("SNS notification envelope must be a JSON object")
+        }
+        val rawType = requiredText(root, "Type")
+        val type =
+            SnsNotificationType.entries.firstOrNull { it.name == rawType }
+                ?: throw SnsNotificationParseException("Unknown SNS notification Type '$rawType'")
+        return SnsNotification(
+            type = type,
+            messageId = requiredText(root, "MessageId"),
+            topicArn = requiredText(root, "TopicArn"),
+            message = requiredText(root, "Message"),
+            subject = optionalText(root, "Subject"),
+            timestamp = optionalText(root, "Timestamp"),
+            signatureVersion = optionalText(root, "SignatureVersion"),
+            signature = optionalText(root, "Signature"),
+            signingCertUrl = optionalText(root, "SigningCertURL"),
+            unsubscribeUrl = optionalText(root, "UnsubscribeURL"),
+            token = optionalText(root, "Token")
+        )
+    }
+
+    private fun requiredText(root: JsonNode, field: String): String {
+        val node = root.get(field)
+        if (node == null || !node.isString) {
+            throw SnsNotificationParseException(
+                "SNS notification envelope is missing required string field '$field'"
+            )
+        }
+        return node.asString()
+    }
+
+    private fun optionalText(root: JsonNode, field: String): String? =
+        root.get(field)?.takeIf { it.isString }?.asString()
+}
diff --git a/sns/src/main/kotlin/io/github/hantsy/awskotlin/sns/SnsNotificationType.kt b/sns/src/main/kotlin/io/github/hantsy/awskotlin/sns/SnsNotificationType.kt
new file mode 100644
index 0000000..4609c0d
--- /dev/null
+++ b/sns/src/main/kotlin/io/github/hantsy/awskotlin/sns/SnsNotificationType.kt
@@ -0,0 +1,15 @@
+package io.github.hantsy.awskotlin.sns
+
+/**
+ * The kind of message carried by an SNS JSON envelope, taken from its `Type` field.
+ */
+enum class SnsNotificationType {
+    /** A published message delivered to subscribers. */
+    Notification,
+
+    /** Sent to an endpoint when a subscription is created and must be confirmed. */
+    SubscriptionConfirmation,
+
+    /** Sent to an endpoint when its subscription is removed. */
+    UnsubscribeConfirmation,
+}
diff --git a/sns/src/main/kotlin/io/github/hantsy/awskotlin/sns/SnsTemplate.kt b/sns/src/main/kotlin/io/github/hantsy/awskotlin/sns/SnsTemplate.kt
new file mode 100644
index 0000000..6651be9
--- /dev/null
+++ b/sns/src/main/kotlin/io/github/hantsy/awskotlin/sns/SnsTemplate.kt
@@ -0,0 +1,21 @@
+package io.github.hantsy.awskotlin.sns
+
+import aws.sdk.kotlin.services.sns.SnsClient
+import aws.sdk.kotlin.services.sns.model.UnsubscribeResponse
+import aws.sdk.kotlin.services.sns.unsubscribe
+
+/**
+ * Entry point for fluent SNS operations backed by the AWS SDK for Kotlin [SnsClient].
+ *
+ * Obtain topic-scoped operations with [topic]. The selector accepts a topic ARN only;
+ * no implicit topic creation or name resolution is performed.
+ */
+class SnsTemplate(private val client: SnsClient) {
+    /** Returns operations scoped to the topic identified by [topicArn]. No request is made. */
+    fun topic(topicArn: String): TopicOperations = TopicOperations(client, topicArn)
+
+    /** Cancels the subscription identified by [subscriptionArn]. */
+    suspend fun unsubscribe(subscriptionArn: String): UnsubscribeResponse = client.unsubscribe {
+        this.subscriptionArn = subscriptionArn
+    }
+}
diff --git a/sns/src/main/kotlin/io/github/hantsy/awskotlin/sns/TopicOperations.kt b/sns/src/main/kotlin/io/github/hantsy/awskotlin/sns/TopicOperations.kt
new file mode 100644
index 0000000..9b59fae
--- /dev/null
+++ b/sns/src/main/kotlin/io/github/hantsy/awskotlin/sns/TopicOperations.kt
@@ -0,0 +1,67 @@
+package io.github.hantsy.awskotlin.sns
+
+import aws.sdk.kotlin.services.sns.SnsClient
+import aws.sdk.kotlin.services.sns.deleteTopic
+import aws.sdk.kotlin.services.sns.model.DeleteTopicResponse
+import aws.sdk.kotlin.services.sns.model.PublishResponse
+import aws.sdk.kotlin.services.sns.model.SubscribeResponse
+import aws.sdk.kotlin.services.sns.publish
+import aws.sdk.kotlin.services.sns.setSubscriptionAttributes
+import aws.sdk.kotlin.services.sns.subscribe
+
+/**
+ * Topic-scoped SNS operations. Created via [SnsTemplate.topic].
+ *
+ * Each method maps directly to the matching SDK request and propagates SDK exceptions
+ * unchanged.
+ */
+class TopicOperations internal constructor(private val client: SnsClient, private val topicArn: String,) {
+    /** Publishes [message] to the topic, optionally setting a [subject]. */
+    suspend fun publish(message: String, subject: String? = null): PublishResponse = client.publish {
+        topicArn = this@TopicOperations.topicArn
+        this.message = message
+        this.subject = subject
+    }
+
+    /** Subscribes the given [endpoint] using [protocol] (for example `email`, `sqs`, `https`). */
+    suspend fun subscribe(protocol: String, endpoint: String): SubscribeResponse = client.subscribe {
+        topicArn = this@TopicOperations.topicArn
+        this.protocol = protocol
+        this.endpoint = endpoint
+    }
+
+    /**
+     * Subscribes the SQS queue identified by [queueArn] to this topic for SNS -> SQS fan-out,
+     * then sets the subscription's `RawMessageDelivery` attribute to [rawMessageDelivery].
+     *
+     * With the default `rawMessageDelivery = false`, SQS listeners receive the SNS JSON
+     * envelope as a String body and can call [SnsNotificationParser.parse] to obtain an
+     * [SnsNotification]. With `rawMessageDelivery = true`, the envelope is bypassed and the
+     * queue receives the published message body unchanged.
+     *
+     * The queue must exist and its policy must allow the topic to send messages to it
+     * (`sqs:SendMessage` with an `aws:SourceArn` condition on this topic).
+     *
+     * @throws IllegalStateException if the subscribe response carries no subscription ARN.
+     */
+    suspend fun subscribeSqs(queueArn: String, rawMessageDelivery: Boolean = false): SubscribeResponse {
+        val response = subscribe(protocol = "sqs", endpoint = queueArn)
+        val subArn =
+            response.subscriptionArn
+                ?: throw IllegalStateException(
+                    "SNS subscribe for queue $queueArn returned no subscriptionArn; " +
+                        "cannot set RawMessageDelivery"
+                )
+        client.setSubscriptionAttributes {
+            subscriptionArn = subArn
+            attributeName = "RawMessageDelivery"
+            attributeValue = rawMessageDelivery.toString()
+        }
+        return response
+    }
+
+    /** Deletes the topic. */
+    suspend fun delete(): DeleteTopicResponse = client.deleteTopic {
+        topicArn = this@TopicOperations.topicArn
+    }
+}
diff --git a/sns/src/test/kotlin/io/github/hantsy/awskotlin/sns/SnsNotificationParserTest.kt b/sns/src/test/kotlin/io/github/hantsy/awskotlin/sns/SnsNotificationParserTest.kt
new file mode 100644
index 0000000..4e2b460
--- /dev/null
+++ b/sns/src/test/kotlin/io/github/hantsy/awskotlin/sns/SnsNotificationParserTest.kt
@@ -0,0 +1,184 @@
+package io.github.hantsy.awskotlin.sns
+
+import io.kotest.assertions.throwables.shouldThrow
+import io.kotest.matchers.nulls.shouldBeNull
+import io.kotest.matchers.nulls.shouldNotBeNull
+import io.kotest.matchers.shouldBe
+import io.kotest.matchers.string.shouldContain
+import io.kotest.matchers.types.shouldBeInstanceOf
+import org.junit.jupiter.api.Test
+import tools.jackson.core.JacksonException
+
+class SnsNotificationParserTest {
+    private val topicArn = "arn:aws:sns:us-east-1:123456789012:my-topic"
+
+    @Test
+    fun `parse Notification envelope with all fields`() {
+        val body =
+            """
+            {
+              "Type": "Notification",
+              "MessageId": "msg-1",
+              "TopicArn": "$topicArn",
+              "Message": "hello fan-out",
+              "Subject": "greeting",
+              "Timestamp": "2026-01-01T00:00:00.000Z",
+              "SignatureVersion": "1",
+              "Signature": "c2lnbmF0dXJl",
+              "SigningCertURL": "https://sns.us-east-1.amazonaws.com/cert.pem",
+              "UnsubscribeURL": "https://sns.us-east-1.amazonaws.com/?Action=Unsubscribe"
+            }
+            """.trimIndent()
+
+        val notification = SnsNotificationParser.parse(body)
+
+        notification shouldBe
+            SnsNotification(
+                type = SnsNotificationType.Notification,
+                messageId = "msg-1",
+                topicArn = topicArn,
+                message = "hello fan-out",
+                subject = "greeting",
+                timestamp = "2026-01-01T00:00:00.000Z",
+                signatureVersion = "1",
+                signature = "c2lnbmF0dXJl",
+                signingCertUrl = "https://sns.us-east-1.amazonaws.com/cert.pem",
+                unsubscribeUrl = "https://sns.us-east-1.amazonaws.com/?Action=Unsubscribe"
+            )
+        notification.token.shouldBeNull()
+    }
+
+    @Test
+    fun `parse SubscriptionConfirmation envelope with Token`() {
+        val body =
+            """
+            {
+              "Type": "SubscriptionConfirmation",
+              "MessageId": "msg-2",
+              "TopicArn": "$topicArn",
+              "Message": "You have chosen to subscribe to the topic",
+              "Token": "confirm-token",
+              "SubscribeURL": "https://sns.us-east-1.amazonaws.com/?Action=ConfirmSubscription",
+              "Timestamp": "2026-01-01T00:00:00.000Z"
+            }
+            """.trimIndent()
+
+        val notification = SnsNotificationParser.parse(body)
+
+        notification.type shouldBe SnsNotificationType.SubscriptionConfirmation
+        notification.messageId shouldBe "msg-2"
+        notification.topicArn shouldBe topicArn
+        notification.token shouldBe "confirm-token"
+        notification.subject.shouldBeNull()
+    }
+
+    @Test
+    fun `parse UnsubscribeConfirmation envelope`() {
+        val body =
+            """
+            {
+              "Type": "UnsubscribeConfirmation",
+              "MessageId": "msg-3",
+              "TopicArn": "$topicArn",
+              "Message": "You have chosen to deactivate subscription",
+              "Token": "unsubscribe-token"
+            }
+            """.trimIndent()
+
+        val notification = SnsNotificationParser.parse(body)
+
+        notification.type shouldBe SnsNotificationType.UnsubscribeConfirmation
+        notification.token shouldBe "unsubscribe-token"
+    }
+
+    @Test
+    fun `unknown fields are ignored`() {
+        val body =
+            """
+            {
+              "Type": "Notification",
+              "MessageId": "msg-4",
+              "TopicArn": "$topicArn",
+              "Message": "payload",
+              "SomethingNew": {"nested": true},
+              "MessageAttributes": {"key": {"Type": "String", "Value": "v"}}
+            }
+            """.trimIndent()
+
+        val notification = SnsNotificationParser.parse(body)
+
+        notification.message shouldBe "payload"
+        notification.subject.shouldBeNull()
+    }
+
+    @Test
+    fun `minimal Notification leaves optional fields null`() {
+        val body =
+            """
+            {"Type":"Notification","MessageId":"msg-5","TopicArn":"$topicArn","Message":"only-required"}
+            """.trimIndent()
+
+        val notification = SnsNotificationParser.parse(body)
+
+        notification.type shouldBe SnsNotificationType.Notification
+        notification.subject.shouldBeNull()
+        notification.timestamp.shouldBeNull()
+        notification.signatureVersion.shouldBeNull()
+        notification.signature.shouldBeNull()
+        notification.signingCertUrl.shouldBeNull()
+        notification.unsubscribeUrl.shouldBeNull()
+        notification.token.shouldBeNull()
+    }
+
+    @Test
+    fun `missing Type throws typed error`() {
+        val body = """{"MessageId":"m","TopicArn":"$topicArn","Message":"x"}"""
+
+        val thrown = shouldThrow<SnsNotificationParseException> { SnsNotificationParser.parse(body) }
+        thrown.message.shouldNotBeNull() shouldContain "Type"
+    }
+
+    @Test
+    fun `unknown Type throws typed error`() {
+        val body = """{"Type":"Bogus","MessageId":"m","TopicArn":"$topicArn","Message":"x"}"""
+
+        val thrown = shouldThrow<SnsNotificationParseException> { SnsNotificationParser.parse(body) }
+        thrown.message.shouldNotBeNull() shouldContain "Bogus"
+    }
+
+    @Test
+    fun `missing required fields throw typed error`() {
+        val fields =
+            mapOf(
+                "MessageId" to """{"Type":"Notification","TopicArn":"$topicArn","Message":"x"}""",
+                "TopicArn" to """{"Type":"Notification","MessageId":"m","Message":"x"}""",
+                "Message" to """{"Type":"Notification","MessageId":"m","TopicArn":"$topicArn"}"""
+            )
+
+        fields.forEach { (field, body) ->
+            val thrown = shouldThrow<SnsNotificationParseException> { SnsNotificationParser.parse(body) }
+            thrown.message.shouldNotBeNull() shouldContain field
+        }
+    }
+
+    @Test
+    fun `non-string required field throws typed error`() {
+        val body = """{"Type":"Notification","MessageId":42,"TopicArn":"$topicArn","Message":"x"}"""
+
+        val thrown = shouldThrow<SnsNotificationParseException> { SnsNotificationParser.parse(body) }
+        thrown.message.shouldNotBeNull() shouldContain "MessageId"
+    }
+
+    @Test
+    fun `invalid JSON throws typed error with Jackson cause`() {
+        val thrown = shouldThrow<SnsNotificationParseException> { SnsNotificationParser.parse("{not json") }
+        thrown.cause.shouldNotBeNull().shouldBeInstanceOf<JacksonException>()
+    }
+
+    @Test
+    fun `non-object JSON throws typed error`() {
+        shouldThrow<SnsNotificationParseException> { SnsNotificationParser.parse("[1,2,3]") }
+        shouldThrow<SnsNotificationParseException> { SnsNotificationParser.parse("\"just a string\"") }
+        shouldThrow<SnsNotificationParseException> { SnsNotificationParser.parse("null") }
+    }
+}
diff --git a/sns/src/test/kotlin/io/github/hantsy/awskotlin/sns/SnsSqsFanOutLocalStackTest.kt b/sns/src/test/kotlin/io/github/hantsy/awskotlin/sns/SnsSqsFanOutLocalStackTest.kt
new file mode 100644
index 0000000..a520c5e
--- /dev/null
+++ b/sns/src/test/kotlin/io/github/hantsy/awskotlin/sns/SnsSqsFanOutLocalStackTest.kt
@@ -0,0 +1,182 @@
+package io.github.hantsy.awskotlin.sns
+
+import aws.sdk.kotlin.runtime.auth.credentials.StaticCredentialsProvider
+import aws.sdk.kotlin.services.sns.SnsClient
+import aws.sdk.kotlin.services.sns.createTopic
+import aws.sdk.kotlin.services.sns.deleteTopic
+import aws.sdk.kotlin.services.sqs.SqsClient
+import aws.sdk.kotlin.services.sqs.createQueue
+import aws.sdk.kotlin.services.sqs.deleteQueue
+import aws.sdk.kotlin.services.sqs.getQueueAttributes
+import aws.sdk.kotlin.services.sqs.model.QueueAttributeName
+import aws.sdk.kotlin.services.sqs.receiveMessage
+import aws.sdk.kotlin.services.sqs.setQueueAttributes
+import aws.smithy.kotlin.runtime.net.url.Url
+import io.kotest.matchers.nulls.shouldNotBeNull
+import io.kotest.matchers.shouldBe
+import kotlinx.coroutines.runBlocking
+import org.junit.jupiter.api.AfterAll
+import org.junit.jupiter.api.Assumptions.assumeTrue
+import org.junit.jupiter.api.BeforeAll
+import org.junit.jupiter.api.Test
+import org.junit.jupiter.api.TestInstance
+import org.testcontainers.DockerClientFactory
+import org.testcontainers.localstack.LocalStackContainer
+import org.testcontainers.utility.DockerImageName
+import java.util.UUID
+
+/**
+ * End-to-end SNS -> SQS fan-out against LocalStack: [TopicOperations.subscribeSqs],
+ * publish, receive from SQS and parse the envelope with [SnsNotificationParser].
+ *
+ * Skipped unless Docker is available and LOCALSTACK_AUTH_TOKEN is set
+ * (LocalStack 4 requires a license token).
+ */
+@TestInstance(TestInstance.Lifecycle.PER_CLASS)
+class SnsSqsFanOutLocalStackTest {
+    private lateinit var container: LocalStackContainer
+    private lateinit var snsClient: SnsClient
+    private lateinit var sqsClient: SqsClient
+    private lateinit var template: SnsTemplate
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
+                .withServices("sns", "sqs")
+                .withEnv("LOCALSTACK_AUTH_TOKEN", authToken)
+        container.start()
+
+        val endpoint = Url.parse("http://${container.host}:${container.firstMappedPort}")
+        snsClient =
+            SnsClient {
+                region = "us-east-1"
+                credentialsProvider =
+                    StaticCredentialsProvider {
+                        accessKeyId = "test"
+                        secretAccessKey = "test"
+                    }
+                endpointUrl = endpoint
+            }
+        sqsClient =
+            SqsClient {
+                region = "us-east-1"
+                credentialsProvider =
+                    StaticCredentialsProvider {
+                        accessKeyId = "test"
+                        secretAccessKey = "test"
+                    }
+                endpointUrl = endpoint
+            }
+        template = SnsTemplate(snsClient)
+    }
+
+    @AfterAll
+    fun stopLocalStack() {
+        if (::snsClient.isInitialized) {
+            runCatching { snsClient.close() }
+        }
+        if (::sqsClient.isInitialized) {
+            runCatching { sqsClient.close() }
+        }
+        if (::container.isInitialized) {
+            runCatching { container.stop() }
+        }
+    }
+
+    @Test
+    fun `subscribeSqs fan-out delivers the SNS envelope to SQS`() = runBlocking<Unit> {
+        val fanOut = newFanOut()
+        try {
+            template.topic(fanOut.topicArn).subscribeSqs(fanOut.queueArn, rawMessageDelivery = false)
+            template.topic(fanOut.topicArn).publish("hello fan-out", subject = "greeting")
+
+            val body = receiveBody(fanOut.queueUrl)
+            val notification = SnsNotificationParser.parse(body)
+
+            notification.type shouldBe SnsNotificationType.Notification
+            notification.message shouldBe "hello fan-out"
+            notification.topicArn shouldBe fanOut.topicArn
+            notification.subject shouldBe "greeting"
+        } finally {
+            fanOut.cleanup()
+        }
+    }
+
+    @Test
+    fun `subscribeSqs with rawMessageDelivery delivers the original body without envelope`() = runBlocking<Unit> {
+        val fanOut = newFanOut()
+        try {
+            template.topic(fanOut.topicArn).subscribeSqs(fanOut.queueArn, rawMessageDelivery = true)
+            template.topic(fanOut.topicArn).publish("raw-body")
+
+            receiveBody(fanOut.queueUrl) shouldBe "raw-body"
+        } finally {
+            fanOut.cleanup()
+        }
+    }
+
+    private suspend fun newFanOut(): FanOut {
+        val suffix = UUID.randomUUID().toString().substring(0, 8)
+        val topicArn = snsClient.createTopic { name = "awskotlin-fanout-$suffix" }.topicArn.shouldNotBeNull()
+        val queueUrl =
+            sqsClient.createQueue { queueName = "awskotlin-fanout-$suffix" }.queueUrl.shouldNotBeNull()
+        val queueArn =
+            sqsClient.getQueueAttributes {
+                this.queueUrl = queueUrl
+                attributeNames = listOf(QueueAttributeName.QueueArn)
+            }.attributes.shouldNotBeNull()[QueueAttributeName.QueueArn].shouldNotBeNull()
+
+        // Real AWS (and policy-enforcing emulators) require the queue to allow the topic to send to it.
+        sqsClient.setQueueAttributes {
+            this.queueUrl = queueUrl
+            attributes = mapOf(QueueAttributeName.Policy to sendPolicy(topicArn, queueArn))
+        }
+        return FanOut(topicArn, queueUrl, queueArn)
+    }
+
+    private fun sendPolicy(topicArn: String, queueArn: String): String =
+        """
+        {
+          "Version": "2012-10-17",
+          "Statement": [{
+            "Effect": "Allow",
+            "Principal": {"Service": "sns.amazonaws.com"},
+            "Action": "sqs:SendMessage",
+            "Resource": "$queueArn",
+            "Condition": {"ArnEquals": {"aws:SourceArn": "$topicArn"}}
+          }]
+        }
+        """.trimIndent()
+
+    /** Long-polls the queue for up to ~60 seconds; fan-out delivery is asynchronous. */
+    private suspend fun receiveBody(queueUrl: String): String {
+        repeat(12) {
+            val message =
+                sqsClient.receiveMessage {
+                    this.queueUrl = queueUrl
+                    waitTimeSeconds = 5
+                    maxNumberOfMessages = 1
+                }.messages?.firstOrNull()
+            if (message != null) {
+                return message.body.shouldNotBeNull()
+            }
+        }
+        error("No SQS message received from $queueUrl within 60 seconds of SNS publish")
+    }
+
+    private inner class FanOut(val topicArn: String, val queueUrl: String, val queueArn: String) {
+        /** Best-effort cleanup so a failed assertion does not leak resources into other tests. */
+        suspend fun cleanup() {
+            runCatching { snsClient.deleteTopic { this.topicArn = this@FanOut.topicArn } }
+            runCatching { sqsClient.deleteQueue { this.queueUrl = this@FanOut.queueUrl } }
+        }
+    }
+}
diff --git a/sns/src/test/kotlin/io/github/hantsy/awskotlin/sns/SnsTemplateTest.kt b/sns/src/test/kotlin/io/github/hantsy/awskotlin/sns/SnsTemplateTest.kt
new file mode 100644
index 0000000..23de4f0
--- /dev/null
+++ b/sns/src/test/kotlin/io/github/hantsy/awskotlin/sns/SnsTemplateTest.kt
@@ -0,0 +1,110 @@
+package io.github.hantsy.awskotlin.sns
+
+import aws.sdk.kotlin.services.sns.SnsClient
+import aws.sdk.kotlin.services.sns.model.DeleteTopicRequest
+import aws.sdk.kotlin.services.sns.model.DeleteTopicResponse
+import aws.sdk.kotlin.services.sns.model.NotFoundException
+import aws.sdk.kotlin.services.sns.model.PublishRequest
+import aws.sdk.kotlin.services.sns.model.PublishResponse
+import aws.sdk.kotlin.services.sns.model.SnsException
+import aws.sdk.kotlin.services.sns.model.SubscribeRequest
+import aws.sdk.kotlin.services.sns.model.SubscribeResponse
+import aws.sdk.kotlin.services.sns.model.UnsubscribeRequest
+import aws.sdk.kotlin.services.sns.model.UnsubscribeResponse
+import io.kotest.assertions.throwables.shouldThrow
+import io.kotest.matchers.nulls.shouldBeNull
+import io.kotest.matchers.shouldBe
+import io.mockk.coEvery
+import io.mockk.mockk
+import io.mockk.slot
+import kotlinx.coroutines.test.runTest
+import org.junit.jupiter.api.Test
+
+class SnsTemplateTest {
+    private val client = mockk<SnsClient>()
+    private val template = SnsTemplate(client)
+    private val topicArn = "arn:aws:sns:us-east-1:123456789012:my-topic"
+
+    @Test
+    fun `publish builds PublishRequest with topicArn message and subject`() = runTest {
+        val request = slot<PublishRequest>()
+        val response = PublishResponse { messageId = "msg-1" }
+        coEvery { client.publish(capture(request)) } returns response
+
+        val result = template.topic(topicArn).publish("hello", subject = "greeting")
+
+        result shouldBe response
+        request.captured.topicArn shouldBe topicArn
+        request.captured.message shouldBe "hello"
+        request.captured.subject shouldBe "greeting"
+    }
+
+    @Test
+    fun `publish without subject leaves it unset`() = runTest {
+        val request = slot<PublishRequest>()
+        coEvery { client.publish(capture(request)) } returns PublishResponse { }
+
+        template.topic(topicArn).publish("hello")
+
+        request.captured.topicArn shouldBe topicArn
+        request.captured.message shouldBe "hello"
+        request.captured.subject.shouldBeNull()
+    }
+
+    @Test
+    fun `subscribe builds SubscribeRequest with topicArn protocol and endpoint`() = runTest {
+        val request = slot<SubscribeRequest>()
+        val response = SubscribeResponse { subscriptionArn = "arn:aws:sns:us-east-1:123456789012:my-topic:sub-1" }
+        coEvery { client.subscribe(capture(request)) } returns response
+
+        val result = template.topic(topicArn).subscribe("email", "user@example.com")
+
+        result shouldBe response
+        request.captured.topicArn shouldBe topicArn
+        request.captured.protocol shouldBe "email"
+        request.captured.endpoint shouldBe "user@example.com"
+    }
+
+    @Test
+    fun `delete builds DeleteTopicRequest with topicArn`() = runTest {
+        val request = slot<DeleteTopicRequest>()
+        val response = DeleteTopicResponse { }
+        coEvery { client.deleteTopic(capture(request)) } returns response
+
+        val result = template.topic(topicArn).delete()
+
+        result shouldBe response
+        request.captured.topicArn shouldBe topicArn
+    }
+
+    @Test
+    fun `unsubscribe builds UnsubscribeRequest with subscriptionArn`() = runTest {
+        val request = slot<UnsubscribeRequest>()
+        val response = UnsubscribeResponse { }
+        coEvery { client.unsubscribe(capture(request)) } returns response
+        val subscriptionArn = "arn:aws:sns:us-east-1:123456789012:my-topic:sub-1"
+
+        val result = template.unsubscribe(subscriptionArn)
+
+        result shouldBe response
+        request.captured.subscriptionArn shouldBe subscriptionArn
+    }
+
+    @Test
+    fun `publish propagates NotFoundException unchanged`() = runTest {
+        val failure = NotFoundException { message = "topic not found" }
+        coEvery { client.publish(any<PublishRequest>()) } throws failure
+
+        val thrown = shouldThrow<NotFoundException> { template.topic(topicArn).publish("hello") }
+        thrown shouldBe failure
+    }
+
+    @Test
+    fun `subscribe propagates other SDK exceptions unchanged`() = runTest {
+        val failure = SnsException("access denied")
+        coEvery { client.subscribe(any<SubscribeRequest>()) } throws failure
+
+        val thrown = shouldThrow<SnsException> { template.topic(topicArn).subscribe("email", "user@example.com") }
+        thrown shouldBe failure
+    }
+}
diff --git a/sns/src/test/kotlin/io/github/hantsy/awskotlin/sns/TopicOperationsSubscribeSqsTest.kt b/sns/src/test/kotlin/io/github/hantsy/awskotlin/sns/TopicOperationsSubscribeSqsTest.kt
new file mode 100644
index 0000000..43f19c6
--- /dev/null
+++ b/sns/src/test/kotlin/io/github/hantsy/awskotlin/sns/TopicOperationsSubscribeSqsTest.kt
@@ -0,0 +1,68 @@
+package io.github.hantsy.awskotlin.sns
+
+import aws.sdk.kotlin.services.sns.SnsClient
+import aws.sdk.kotlin.services.sns.model.SetSubscriptionAttributesRequest
+import aws.sdk.kotlin.services.sns.model.SetSubscriptionAttributesResponse
+import aws.sdk.kotlin.services.sns.model.SubscribeRequest
+import aws.sdk.kotlin.services.sns.model.SubscribeResponse
+import io.kotest.assertions.throwables.shouldThrow
+import io.kotest.matchers.shouldBe
+import io.kotest.matchers.string.shouldContain
+import io.mockk.coEvery
+import io.mockk.coVerify
+import io.mockk.mockk
+import io.mockk.slot
+import kotlinx.coroutines.test.runTest
+import org.junit.jupiter.api.Test
+
+class TopicOperationsSubscribeSqsTest {
+    private val client = mockk<SnsClient>()
+    private val template = SnsTemplate(client)
+    private val topicArn = "arn:aws:sns:us-east-1:123456789012:my-topic"
+    private val queueArn = "arn:aws:sqs:us-east-1:123456789012:my-queue"
+    private val subArn = "arn:aws:sns:us-east-1:123456789012:my-topic:sub-1"
+
+    @Test
+    fun `subscribeSqs subscribes with sqs protocol and defaults RawMessageDelivery to false`() = runTest {
+        val subscribeRequest = slot<SubscribeRequest>()
+        val attributesRequest = slot<SetSubscriptionAttributesRequest>()
+        val response = SubscribeResponse { subscriptionArn = subArn }
+        coEvery { client.subscribe(capture(subscribeRequest)) } returns response
+        coEvery { client.setSubscriptionAttributes(capture(attributesRequest)) } returns
+            SetSubscriptionAttributesResponse { }
+
+        val result = template.topic(topicArn).subscribeSqs(queueArn)
+
+        result shouldBe response
+        subscribeRequest.captured.topicArn shouldBe topicArn
+        subscribeRequest.captured.protocol shouldBe "sqs"
+        subscribeRequest.captured.endpoint shouldBe queueArn
+        attributesRequest.captured.subscriptionArn shouldBe subArn
+        attributesRequest.captured.attributeName shouldBe "RawMessageDelivery"
+        attributesRequest.captured.attributeValue shouldBe "false"
+    }
+
+    @Test
+    fun `subscribeSqs with rawMessageDelivery true sets the attribute to true`() = runTest {
+        val attributesRequest = slot<SetSubscriptionAttributesRequest>()
+        coEvery { client.subscribe(any<SubscribeRequest>()) } returns SubscribeResponse { subscriptionArn = subArn }
+        coEvery { client.setSubscriptionAttributes(capture(attributesRequest)) } returns
+            SetSubscriptionAttributesResponse { }
+
+        template.topic(topicArn).subscribeSqs(queueArn, rawMessageDelivery = true)
+
+        attributesRequest.captured.subscriptionArn shouldBe subArn
+        attributesRequest.captured.attributeName shouldBe "RawMessageDelivery"
+        attributesRequest.captured.attributeValue shouldBe "true"
+    }
+
+    @Test
+    fun `subscribeSqs fails when the subscribe response has no subscriptionArn`() = runTest {
+        coEvery { client.subscribe(any<SubscribeRequest>()) } returns SubscribeResponse { }
+
+        val thrown = shouldThrow<IllegalStateException> { template.topic(topicArn).subscribeSqs(queueArn) }
+
+        thrown.message.shouldContain(queueArn)
+        coVerify(exactly = 0) { client.setSubscriptionAttributes(any<SetSubscriptionAttributesRequest>()) }
+    }
+}
