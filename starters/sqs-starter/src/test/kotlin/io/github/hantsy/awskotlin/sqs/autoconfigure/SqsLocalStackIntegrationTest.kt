package io.github.hantsy.awskotlin.sqs.autoconfigure

import aws.sdk.kotlin.runtime.auth.credentials.StaticCredentialsProvider
import aws.sdk.kotlin.services.sqs.SqsClient
import aws.sdk.kotlin.services.sqs.createQueue
import aws.smithy.kotlin.runtime.net.url.Url
import io.github.hantsy.awskotlin.core.autoconfigure.AwsKotlinAutoConfiguration
import io.github.hantsy.awskotlin.sqs.SqsAcknowledgementMode
import io.github.hantsy.awskotlin.sqs.SqsQueueNotFoundException
import io.github.hantsy.awskotlin.sqs.SqsTemplate
import io.github.hantsy.awskotlin.sqs.listener.Acknowledge
import io.github.hantsy.awskotlin.sqs.listener.SqsListener
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.runBlocking
import org.awaitility.kotlin.await
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assumptions.assumeTrue
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.boot.autoconfigure.AutoConfigurations
import org.springframework.boot.test.context.runner.ApplicationContextRunner
import org.testcontainers.DockerClientFactory
import org.testcontainers.localstack.LocalStackContainer
import org.testcontainers.utility.DockerImageName
import java.time.Duration
import java.util.UUID
import java.util.concurrent.CopyOnWriteArrayList
import kotlin.time.Duration.Companion.seconds

// @SqsListener queue names must be compile-time constants, so listener tests use fixed
// per-test names (unique within the freshly started container); template tests use UUIDs.
private const val AUTO_ACK_QUEUE = "awskotlin-sqs-it-auto-ack"
private const val MANUAL_ACK_QUEUE = "awskotlin-sqs-it-manual-ack"
private const val MISSING_QUEUE = "awskotlin-sqs-it-missing"

/**
 * End-to-end SQS template and listener integration against LocalStack.
 *
 * Skipped unless Docker is available and LOCALSTACK_AUTH_TOKEN is set
 * (LocalStack 4 requires a license token).
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SqsLocalStackIntegrationTest {
    private lateinit var container: LocalStackContainer
    private lateinit var client: SqsClient
    private lateinit var template: SqsTemplate

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
                .withServices("sqs")
                .withEnv("LOCALSTACK_AUTH_TOKEN", authToken)
        container.start()

        client =
            SqsClient {
                region = "us-east-1"
                credentialsProvider =
                    StaticCredentialsProvider {
                        accessKeyId = "test"
                        secretAccessKey = "test"
                    }
                endpointUrl = Url.parse("http://${container.host}:${container.firstMappedPort}")
            }
        template = SqsTemplate(client)
    }

    @AfterAll
    fun stopLocalStack() {
        if (::client.isInitialized) {
            runCatching { client.close() }
        }
        if (::container.isInitialized) {
            runCatching { container.stop() }
        }
    }

    @Test
    fun `template send and AUTO receive round trip deletes the message`() = runBlocking<Unit> {
        val queue = newQueue()
        val operations = template.destination(queue)
        operations.send("hello-auto")

        val received = operations.receive(String::class.java, 10.seconds)
        received.shouldNotBeNull().payload shouldBe "hello-auto"

        // AUTO acknowledgement already deleted the message; a short poll finds nothing.
        operations.receive(String::class.java, 2.seconds) shouldBe null
    }

    @Test
    fun `manual acknowledge deletes and reject makes the message visible again`() = runBlocking<Unit> {
        val queue = newQueue()
        val operations = template.destination(queue)
        operations.send("hello-manual")

        val first = operations.receive(String::class.java, 10.seconds, SqsAcknowledgementMode.MANUAL)
        first.shouldNotBeNull().payload shouldBe "hello-manual"

        // reject sets visibility timeout 0, so the same message can be received again.
        operations.reject(first)
        val redelivered = operations.receive(String::class.java, 10.seconds, SqsAcknowledgementMode.MANUAL)
        redelivered.shouldNotBeNull().payload shouldBe "hello-manual"
        redelivered.messageId shouldBe first.messageId

        operations.acknowledge(redelivered)
        operations.receive(String::class.java, 2.seconds) shouldBe null
    }

    @Test
    fun `listener auto-acks messages through the Spring context`() {
        createQueue(AUTO_ACK_QUEUE)
        contextRunner().withBean(AutoAckListener::class.java).run { context ->
            context.startupFailure shouldBe null
            val listener = context.getBean(AutoAckListener::class.java)
            val springTemplate = context.getBean(SqsTemplate::class.java)

            runBlocking { springTemplate.destination(AUTO_ACK_QUEUE).send("hello-listener") }
            await.atMost(Duration.ofSeconds(30)).until { listener.received.contains("hello-listener") }

            // No Acknowledge parameter: the container deleted the message after invocation.
            runBlocking {
                springTemplate.destination(AUTO_ACK_QUEUE).receive(String::class.java, 2.seconds)
            } shouldBe null
        }
    }

    @Test
    fun `listener manual Acknowledge accept deletes the message`() {
        createQueue(MANUAL_ACK_QUEUE)
        contextRunner().withBean(ManualAckListener::class.java).run { context ->
            context.startupFailure shouldBe null
            val listener = context.getBean(ManualAckListener::class.java)
            val springTemplate = context.getBean(SqsTemplate::class.java)

            runBlocking { springTemplate.destination(MANUAL_ACK_QUEUE).send("hello-manual-listener") }
            await.atMost(Duration.ofSeconds(30)).until { listener.received.contains("hello-manual-listener") }

            // Acknowledge.accept() deleted the message; nothing is redelivered.
            runBlocking {
                springTemplate.destination(MANUAL_ACK_QUEUE).receive(String::class.java, 2.seconds)
            } shouldBe null
        }
    }

    @Test
    fun `missing queue fails context startup without implicit creation`() {
        contextRunner().withBean(MissingQueueListener::class.java).run { context ->
            val failure = context.startupFailure
            failure.shouldNotBeNull()
            generateSequence(failure) { it.cause }
                .any { it is SqsQueueNotFoundException } shouldBe true
        }
    }

    private fun contextRunner(): ApplicationContextRunner = ApplicationContextRunner()
        .withConfiguration(
            AutoConfigurations.of(AwsKotlinAutoConfiguration::class.java, SqsAutoConfiguration::class.java),
        )
        .withPropertyValues(
            "awskotlin.region=us-east-1",
            "awskotlin.access-key-id=test",
            "awskotlin.secret-access-key=test",
            "awskotlin.sqs.endpoint=http://${container.host}:${container.firstMappedPort}",
            "awskotlin.sqs.listener.poll-time=1s",
            "awskotlin.sqs.listener.shutdown-timeout=5s",
        )

    private fun newQueue(): String {
        val name = "awskotlin-sqs-it-${UUID.randomUUID().toString().substring(0, 8)}"
        createQueue(name)
        return name
    }

    private fun createQueue(name: String) {
        runBlocking { client.createQueue { queueName = name } }
    }
}

class AutoAckListener {
    val received = CopyOnWriteArrayList<String>()

    @SqsListener(AUTO_ACK_QUEUE)
    fun onMessage(payload: String) {
        received += payload
    }
}

class ManualAckListener {
    val received = CopyOnWriteArrayList<String>()

    @SqsListener(MANUAL_ACK_QUEUE)
    suspend fun onMessage(payload: String, acknowledge: Acknowledge) {
        received += payload
        acknowledge.accept()
    }
}

class MissingQueueListener {
    @SqsListener(MISSING_QUEUE)
    fun onMessage(payload: String) {
        error("Must never be invoked: the queue does not exist")
    }
}
