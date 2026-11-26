package io.github.hantsy.awskotlin.sns

import aws.sdk.kotlin.runtime.auth.credentials.StaticCredentialsProvider
import aws.sdk.kotlin.services.sns.SnsClient
import aws.sdk.kotlin.services.sns.createTopic
import aws.sdk.kotlin.services.sns.deleteTopic
import aws.sdk.kotlin.services.sqs.SqsClient
import aws.sdk.kotlin.services.sqs.createQueue
import aws.sdk.kotlin.services.sqs.deleteQueue
import aws.sdk.kotlin.services.sqs.getQueueAttributes
import aws.sdk.kotlin.services.sqs.model.QueueAttributeName
import aws.sdk.kotlin.services.sqs.receiveMessage
import aws.sdk.kotlin.services.sqs.setQueueAttributes
import aws.smithy.kotlin.runtime.net.url.Url
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assumptions.assumeTrue
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.testcontainers.DockerClientFactory
import org.testcontainers.localstack.LocalStackContainer
import org.testcontainers.utility.DockerImageName
import java.util.UUID

/**
 * End-to-end SNS -> SQS fan-out against LocalStack: [TopicOperations.subscribeSqs],
 * publish, receive from SQS and parse the envelope with [SnsNotificationParser].
 *
 * Skipped unless Docker is available and LOCALSTACK_AUTH_TOKEN is set
 * (LocalStack 4 requires a license token).
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SnsSqsFanOutLocalStackTest {
    private lateinit var container: LocalStackContainer
    private lateinit var snsClient: SnsClient
    private lateinit var sqsClient: SqsClient
    private lateinit var template: SnsTemplate

    @BeforeAll
    fun startLocalStack() {
        val authToken = System.getenv("LOCALSTACK_AUTH_TOKEN")?.takeIf { it.isNotBlank() }
        assumeTrue(authToken != null, "LOCALSTACK_AUTH_TOKEN is not set; skipping LocalStack tests")
        assumeTrue(
            DockerClientFactory.instance().isDockerAvailable,
            "Docker is not available; skipping LocalStack tests"
        )

        container =
            LocalStackContainer(DockerImageName.parse("localstack/localstack:4"))
                .withServices("sns", "sqs")
                .withEnv("LOCALSTACK_AUTH_TOKEN", authToken)
        container.start()

        val endpoint = Url.parse("http://${container.host}:${container.firstMappedPort}")
        snsClient =
            SnsClient {
                region = "us-east-1"
                credentialsProvider =
                    StaticCredentialsProvider {
                        accessKeyId = "test"
                        secretAccessKey = "test"
                    }
                endpointUrl = endpoint
            }
        sqsClient =
            SqsClient {
                region = "us-east-1"
                credentialsProvider =
                    StaticCredentialsProvider {
                        accessKeyId = "test"
                        secretAccessKey = "test"
                    }
                endpointUrl = endpoint
            }
        template = SnsTemplate(snsClient)
    }

    @AfterAll
    fun stopLocalStack() {
        if (::snsClient.isInitialized) {
            runCatching { snsClient.close() }
        }
        if (::sqsClient.isInitialized) {
            runCatching { sqsClient.close() }
        }
        if (::container.isInitialized) {
            runCatching { container.stop() }
        }
    }

    @Test
    fun `subscribeSqs fan-out delivers the SNS envelope to SQS`() = runBlocking<Unit> {
        val fanOut = newFanOut()
        try {
            template.topic(fanOut.topicArn).subscribeSqs(fanOut.queueArn, rawMessageDelivery = false)
            template.topic(fanOut.topicArn).publish("hello fan-out", subject = "greeting")

            val body = receiveBody(fanOut.queueUrl)
            val notification = SnsNotificationParser.parse(body)

            notification.type shouldBe SnsNotificationType.Notification
            notification.message shouldBe "hello fan-out"
            notification.topicArn shouldBe fanOut.topicArn
            notification.subject shouldBe "greeting"
        } finally {
            fanOut.cleanup()
        }
    }

    @Test
    fun `subscribeSqs with rawMessageDelivery delivers the original body without envelope`() = runBlocking<Unit> {
        val fanOut = newFanOut()
        try {
            template.topic(fanOut.topicArn).subscribeSqs(fanOut.queueArn, rawMessageDelivery = true)
            template.topic(fanOut.topicArn).publish("raw-body")

            receiveBody(fanOut.queueUrl) shouldBe "raw-body"
        } finally {
            fanOut.cleanup()
        }
    }

    private suspend fun newFanOut(): FanOut {
        val suffix = UUID.randomUUID().toString().substring(0, 8)
        val topicArn = snsClient.createTopic { name = "awskotlin-fanout-$suffix" }.topicArn.shouldNotBeNull()
        val queueUrl =
            sqsClient.createQueue { queueName = "awskotlin-fanout-$suffix" }.queueUrl.shouldNotBeNull()
        val queueArn =
            sqsClient.getQueueAttributes {
                this.queueUrl = queueUrl
                attributeNames = listOf(QueueAttributeName.QueueArn)
            }.attributes.shouldNotBeNull()[QueueAttributeName.QueueArn].shouldNotBeNull()

        // Real AWS (and policy-enforcing emulators) require the queue to allow the topic to send to it.
        sqsClient.setQueueAttributes {
            this.queueUrl = queueUrl
            attributes = mapOf(QueueAttributeName.Policy to sendPolicy(topicArn, queueArn))
        }
        return FanOut(topicArn, queueUrl, queueArn)
    }

    private fun sendPolicy(topicArn: String, queueArn: String): String =
        """
        {
          "Version": "2012-10-17",
          "Statement": [{
            "Effect": "Allow",
            "Principal": {"Service": "sns.amazonaws.com"},
            "Action": "sqs:SendMessage",
            "Resource": "$queueArn",
            "Condition": {"ArnEquals": {"aws:SourceArn": "$topicArn"}}
          }]
        }
        """.trimIndent()

    /** Long-polls the queue for up to ~60 seconds; fan-out delivery is asynchronous. */
    private suspend fun receiveBody(queueUrl: String): String {
        repeat(12) {
            val message =
                sqsClient.receiveMessage {
                    this.queueUrl = queueUrl
                    waitTimeSeconds = 5
                    maxNumberOfMessages = 1
                }.messages?.firstOrNull()
            if (message != null) {
                return message.body.shouldNotBeNull()
            }
        }
        error("No SQS message received from $queueUrl within 60 seconds of SNS publish")
    }

    private inner class FanOut(val topicArn: String, val queueUrl: String, val queueArn: String) {
        /** Best-effort cleanup so a failed assertion does not leak resources into other tests. */
        suspend fun cleanup() {
            runCatching { snsClient.deleteTopic { this.topicArn = this@FanOut.topicArn } }
            runCatching { sqsClient.deleteQueue { this.queueUrl = this@FanOut.queueUrl } }
        }
    }
}
