package io.github.hantsy.awskotlin.sqs.listener

import aws.sdk.kotlin.services.sqs.SqsClient
import aws.sdk.kotlin.services.sqs.model.ChangeMessageVisibilityResponse
import aws.sdk.kotlin.services.sqs.model.DeleteMessageResponse
import io.github.hantsy.awskotlin.sqs.SqsAcknowledgementMode
import io.github.hantsy.awskotlin.sqs.SqsDestinationOperations
import io.github.hantsy.awskotlin.sqs.SqsMessage
import io.github.hantsy.awskotlin.sqs.SqsOperationException
import io.github.hantsy.awskotlin.sqs.SqsQueueNotFoundException
import io.github.hantsy.awskotlin.sqs.SqsQueueResolver
import io.github.hantsy.awskotlin.sqs.SqsTemplate
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.awaitCancellation
import kotlinx.coroutines.isActive
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import kotlin.concurrent.thread
import kotlin.reflect.KFunction
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.memberFunctions
import kotlin.time.Duration.Companion.seconds

@OptIn(ExperimentalCoroutinesApi::class)
class SqsListenerContainerTest {
    private val template = mockk<SqsTemplate>()
    private val operations = mockk<SqsDestinationOperations>()
    private val resolver = mockk<SqsQueueResolver>()
    private val queueUrl = "https://sqs.us-east-1.amazonaws.com/123456789012/orders"
    private val bean = ListenerBean()
    private val message = SqsMessage(
        payload = "p",
        messageId = "m-1",
        receiptHandle = "rh-1",
        body = "p",
        attributes = mapOf("SentTimestamp" to "1700000000000"),
        messageAttributes = emptyMap()
    )

    private fun stubQueue() {
        coEvery { resolver.resolve("orders") } returns queueUrl
        every { template.destination(queueUrl) } returns operations
    }

    // Deliver one message, then suspend forever so the poll loop parks without busy-spinning.
    private fun stubReceiveOnce() {
        var delivered = false
        coEvery {
            operations.receive(String::class.java, any(), SqsAcknowledgementMode.MANUAL)
        } coAnswers {
            if (delivered) {
                awaitCancellation()
            } else {
                delivered = true
                message
            }
        }
    }

    private fun endpoint(method: KFunction<*>, queues: List<String> = listOf("orders")) =
        SqsListenerEndpoint(bean, method, queues)

    @Test
    fun `SqsListener annotation is runtime retained and captures queue names`() {
        val annotation = ListenerBean::plain.findAnnotation<SqsListener>()
        annotation!!.queues.toList() shouldBe listOf("orders")
        SqsListener::class.findAnnotation<Target>()!!.allowedTargets.toList() shouldContain AnnotationTarget.FUNCTION
        SqsListener::class.java.getAnnotation(Retention::class.java).value shouldBe RetentionPolicy.RUNTIME
    }

    @Test
    fun `endpoint with empty queues is rejected`() {
        shouldThrow<IllegalArgumentException> {
            SqsListenerContainer(endpoint(ListenerBean::plain, emptyList()), template, resolver)
        }
    }

    @Test
    fun `method without payload parameter is rejected`() {
        shouldThrow<IllegalArgumentException> {
            SqsListenerContainer(endpoint(ListenerBean::ackOnly), template, resolver)
        }
    }

    @Test
    fun `method with two payload parameters is rejected`() {
        shouldThrow<IllegalArgumentException> {
            SqsListenerContainer(endpoint(ListenerBean::twoPayloads), template, resolver)
        }
    }

    @Test
    fun `method with unsupported generic parameter is rejected`() {
        val generic = ListenerBean::class.memberFunctions.first { it.name == "generic" }
        shouldThrow<IllegalArgumentException> {
            SqsListenerContainer(endpoint(generic), template, resolver)
        }
    }

    @Test
    fun `SqsMessage star projection is rejected`() {
        shouldThrow<IllegalArgumentException> {
            SqsListenerContainer(endpoint(ListenerBean::star), template, resolver)
        }
    }

    @Test
    fun `method with two Acknowledge parameters is rejected`() {
        shouldThrow<IllegalArgumentException> {
            SqsListenerContainer(endpoint(ListenerBean::twoAcks), template, resolver)
        }
    }

    @Test
    fun `start resolves every queue before polling`() = runTest {
        val urlA = "https://sqs.us-east-1.amazonaws.com/123456789012/a"
        val urlB = "https://sqs.us-east-1.amazonaws.com/123456789012/b"
        val opsA = mockk<SqsDestinationOperations>()
        val opsB = mockk<SqsDestinationOperations>()
        coEvery { resolver.resolve("a") } returns urlA
        coEvery { resolver.resolve("b") } returns urlB
        every { template.destination(urlA) } returns opsA
        every { template.destination(urlB) } returns opsB
        coEvery { opsA.receive(String::class.java, any(), any()) } coAnswers { awaitCancellation() }
        coEvery { opsB.receive(String::class.java, any(), any()) } coAnswers { awaitCancellation() }
        val container = SqsListenerContainer(
            endpoint(ListenerBean::plain, listOf("a", "b")),
            template,
            resolver,
            scope = this
        )

        container.start()
        container.isRunning shouldBe true
        advanceUntilIdle()

        coVerify(exactly = 1) { resolver.resolve("a") }
        coVerify(exactly = 1) { resolver.resolve("b") }
        coVerify(exactly = 1) { opsA.receive(String::class.java, 10.seconds, SqsAcknowledgementMode.MANUAL) }
        coVerify(exactly = 1) { opsB.receive(String::class.java, 10.seconds, SqsAcknowledgementMode.MANUAL) }
        container.stop()
    }

    @Test
    fun `missing queue fails startup without polling`() = runTest {
        coEvery { resolver.resolve("missing") } throws SqsQueueNotFoundException("missing")
        val container =
            SqsListenerContainer(endpoint(ListenerBean::plain, listOf("missing")), template, resolver, scope = this)

        shouldThrow<SqsQueueNotFoundException> { container.start() }

        container.isRunning shouldBe false
        coVerify(exactly = 0) { template.destination(any()) }
    }

    @Test
    fun `FIFO queue fails fast without SDK calls`() = runTest {
        val client = mockk<SqsClient>()
        val container = SqsListenerContainer(
            endpoint(ListenerBean::plain, listOf("orders.fifo")),
            template,
            SqsQueueResolver(client),
            scope = this
        )

        shouldThrow<SqsOperationException> { container.start() }

        container.isRunning shouldBe false
        confirmVerified(client)
    }

    @Test
    fun `plain payload listener is invoked and message auto-deleted in MANUAL mode`() = runTest {
        stubQueue()
        stubReceiveOnce()
        coEvery { operations.acknowledge(message) } returns DeleteMessageResponse { }
        val container = SqsListenerContainer(endpoint(ListenerBean::plain), template, resolver, scope = this)

        container.start()
        advanceUntilIdle()

        bean.plainCalls shouldBe listOf("p")
        coVerify(exactly = 2) { operations.receive(String::class.java, 10.seconds, SqsAcknowledgementMode.MANUAL) }
        coVerify(exactly = 1) { operations.acknowledge(message) }
        container.stop()
    }

    @Test
    fun `SqsMessage listener receives full message and auto-deletes`() = runTest {
        stubQueue()
        stubReceiveOnce()
        coEvery { operations.acknowledge(message) } returns DeleteMessageResponse { }
        val container = SqsListenerContainer(endpoint(ListenerBean::full), template, resolver, scope = this)

        container.start()
        advanceUntilIdle()

        bean.messageCalls shouldBe listOf(message)
        coVerify(exactly = 1) { operations.acknowledge(message) }
        container.stop()
    }

    @Test
    fun `Acknowledge accept deletes the message`() = runTest {
        stubQueue()
        stubReceiveOnce()
        coEvery { operations.acknowledge(message) } returns DeleteMessageResponse { }
        bean.manualAction = { it.accept() }
        val container = SqsListenerContainer(endpoint(ListenerBean::manual), template, resolver, scope = this)

        container.start()
        advanceUntilIdle()

        bean.messageCalls shouldBe listOf(message)
        coVerify(exactly = 1) { operations.acknowledge(message) }
        coVerify(exactly = 0) { operations.reject(any()) }
        container.stop()
    }

    @Test
    fun `Acknowledge reject makes the message visible again without deleting`() = runTest {
        stubQueue()
        stubReceiveOnce()
        coEvery { operations.reject(message) } returns ChangeMessageVisibilityResponse { }
        bean.manualAction = { it.reject() }
        val container = SqsListenerContainer(endpoint(ListenerBean::manual), template, resolver, scope = this)

        container.start()
        advanceUntilIdle()

        coVerify(exactly = 1) { operations.reject(message) }
        coVerify(exactly = 0) { operations.acknowledge(any()) }
        container.stop()
    }

    @Test
    fun `listener exception does not delete and poll loop survives`() = runTest {
        stubQueue()
        stubReceiveOnce()
        val container = SqsListenerContainer(endpoint(ListenerBean::boom), template, resolver, scope = this)

        container.start()
        advanceUntilIdle()

        coVerify(exactly = 0) { operations.acknowledge(any()) }
        coVerify(exactly = 2) { operations.receive(String::class.java, any(), SqsAcknowledgementMode.MANUAL) }
        container.isRunning shouldBe true
        container.stop()
    }

    @Test
    fun `unused Acknowledge parameter leaves the message undeleted`() = runTest {
        stubQueue()
        stubReceiveOnce()
        bean.manualAction = { }
        val container = SqsListenerContainer(endpoint(ListenerBean::manual), template, resolver, scope = this)

        container.start()
        advanceUntilIdle()

        bean.messageCalls shouldBe listOf(message)
        coVerify(exactly = 0) { operations.acknowledge(any()) }
        coVerify(exactly = 0) { operations.reject(any()) }
        container.stop()
    }

    @Test
    fun `empty receive continues polling without processing`() = runTest {
        stubQueue()
        var delivered = false
        coEvery {
            operations.receive(String::class.java, any(), SqsAcknowledgementMode.MANUAL)
        } coAnswers {
            if (delivered) {
                awaitCancellation()
            } else {
                delivered = true
                null
            }
        }
        val container = SqsListenerContainer(endpoint(ListenerBean::plain), template, resolver, scope = this)

        container.start()
        advanceUntilIdle()

        bean.plainCalls shouldBe emptyList()
        coVerify(exactly = 2) { operations.receive(String::class.java, any(), SqsAcknowledgementMode.MANUAL) }
        coVerify(exactly = 0) { operations.acknowledge(any()) }
        container.stop()
    }

    @Test
    fun `receive errors back off exponentially and reset on success`() = runTest {
        stubQueue()
        var calls = 0
        coEvery {
            operations.receive(String::class.java, any(), SqsAcknowledgementMode.MANUAL)
        } coAnswers {
            calls++
            if (calls <= 2) throw SqsOperationException("down") else awaitCancellation()
        }
        val container = SqsListenerContainer(endpoint(ListenerBean::plain), template, resolver, scope = this)

        container.start()
        runCurrent()
        coVerify(exactly = 1) { operations.receive(String::class.java, any(), SqsAcknowledgementMode.MANUAL) }

        advanceTimeBy(1.seconds)
        runCurrent()
        coVerify(exactly = 2) { operations.receive(String::class.java, any(), SqsAcknowledgementMode.MANUAL) }

        advanceTimeBy(1.seconds)
        runCurrent()
        coVerify(exactly = 2) { operations.receive(String::class.java, any(), SqsAcknowledgementMode.MANUAL) }

        advanceTimeBy(1.seconds)
        runCurrent()
        coVerify(exactly = 3) { operations.receive(String::class.java, any(), SqsAcknowledgementMode.MANUAL) }
        container.stop()
    }

    @Test
    fun `stop is idempotent, cancels polling and keeps the external scope alive`() = runTest {
        stubQueue()
        stubReceiveOnce()
        coEvery { operations.acknowledge(message) } returns DeleteMessageResponse { }
        val container = SqsListenerContainer(endpoint(ListenerBean::plain), template, resolver, scope = this)

        container.start()
        advanceUntilIdle()
        container.isRunning shouldBe true

        container.stop()
        container.stop()
        container.isRunning shouldBe false
        advanceUntilIdle()

        coVerify(exactly = 2) { operations.receive(String::class.java, any(), SqsAcknowledgementMode.MANUAL) }
        isActive shouldBe true
    }

    @Test
    fun `stop waits for in-flight processing before cancelling`() = runTest {
        stubQueue()
        stubReceiveOnce()
        coEvery { operations.acknowledge(message) } returns DeleteMessageResponse { }
        val gate = CompletableDeferred<Unit>()
        bean.gate = { gate.await() }
        val container = SqsListenerContainer(
            endpoint(ListenerBean::gated),
            template,
            resolver,
            SqsListenerConfig(shutdownTimeout = 10.seconds),
            scope = this
        )

        container.start()
        advanceUntilIdle()
        bean.gatedCalls shouldBe emptyList()

        val stopper = thread { container.stop() }
        gate.complete(Unit)
        advanceUntilIdle()
        stopper.join(10_000)

        stopper.isAlive shouldBe false
        bean.gatedCalls shouldBe listOf("p")
        coVerify(exactly = 1) { operations.acknowledge(message) }
        container.isRunning shouldBe false
    }

    @Test
    fun `isAutoStartup follows config`() = runTest {
        val container = SqsListenerContainer(
            endpoint(ListenerBean::plain),
            template,
            resolver,
            SqsListenerConfig(autoStartup = false),
            scope = this
        )
        container.isAutoStartup shouldBe false
        container.isRunning shouldBe false
    }

    private class ListenerBean {
        val plainCalls = mutableListOf<String>()
        val messageCalls = mutableListOf<SqsMessage<String>>()
        val gatedCalls = mutableListOf<String>()
        var manualAction: suspend (Acknowledge) -> Unit = { }
        var gate: suspend () -> Unit = { }

        @SqsListener("orders")
        fun plain(payload: String) {
            plainCalls += payload
        }

        @SqsListener("orders")
        suspend fun full(message: SqsMessage<String>) {
            messageCalls += message
        }

        @SqsListener("orders")
        suspend fun manual(message: SqsMessage<String>, ack: Acknowledge) {
            messageCalls += message
            manualAction(ack)
        }

        @SqsListener("orders")
        fun boom(payload: String): Unit = throw IllegalStateException("boom")

        @SqsListener("orders")
        suspend fun gated(payload: String) {
            gate()
            gatedCalls += payload
        }

        @SqsListener("orders")
        fun ackOnly(ack: Acknowledge) {
        }

        @SqsListener("orders")
        fun twoPayloads(a: String, b: String) {
        }

        @SqsListener("orders")
        fun <T> generic(payload: T) {
        }

        @SqsListener("orders")
        fun star(message: SqsMessage<*>) {
        }

        @SqsListener("orders")
        fun twoAcks(payload: String, a: Acknowledge, b: Acknowledge) {
        }
    }
}
