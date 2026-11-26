package io.github.hantsy.awskotlin.sqs

import aws.sdk.kotlin.services.sqs.SqsClient
import aws.sdk.kotlin.services.sqs.model.ChangeMessageVisibilityRequest
import aws.sdk.kotlin.services.sqs.model.ChangeMessageVisibilityResponse
import aws.sdk.kotlin.services.sqs.model.DeleteMessageRequest
import aws.sdk.kotlin.services.sqs.model.DeleteMessageResponse
import aws.sdk.kotlin.services.sqs.model.GetQueueUrlRequest
import aws.sdk.kotlin.services.sqs.model.GetQueueUrlResponse
import aws.sdk.kotlin.services.sqs.model.Message
import aws.sdk.kotlin.services.sqs.model.MessageAttributeValue
import aws.sdk.kotlin.services.sqs.model.MessageNotInflight
import aws.sdk.kotlin.services.sqs.model.MessageSystemAttributeName
import aws.sdk.kotlin.services.sqs.model.QueueDoesNotExist
import aws.sdk.kotlin.services.sqs.model.ReceiptHandleIsInvalid
import aws.sdk.kotlin.services.sqs.model.ReceiveMessageRequest
import aws.sdk.kotlin.services.sqs.model.ReceiveMessageResponse
import aws.sdk.kotlin.services.sqs.model.SendMessageRequest
import aws.sdk.kotlin.services.sqs.model.SendMessageResponse
import aws.sdk.kotlin.services.sqs.model.SqsException
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import io.mockk.slot
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import kotlin.time.Duration.Companion.seconds

class SqsTemplateTest {
    private val client = mockk<SqsClient>()
    private val template = SqsTemplate(client)
    private val queueName = "orders"
    private val queueUrl = "https://sqs.us-east-1.amazonaws.com/123456789012/orders"
    private val orderJson = """{"id":"o-1","quantity":2}"""

    private fun stubQueueUrl() {
        val url = queueUrl
        coEvery { client.getQueueUrl(any<GetQueueUrlRequest>()) } returns GetQueueUrlResponse { queueUrl = url }
    }

    private fun sdkMessage(body: String) = Message {
        messageId = "m-1"
        receiptHandle = "rh-1"
        this.body = body
        attributes = mapOf(MessageSystemAttributeName.SentTimestamp to "1700000000000")
        messageAttributes = mapOf(
            "trace" to MessageAttributeValue {
                dataType = "String";
                stringValue = "t-1"
            }
        )
    }

    private fun receivedMessage() = SqsMessage(
        payload = "p",
        messageId = "m-1",
        receiptHandle = "rh-1",
        body = "p",
        attributes = emptyMap(),
        messageAttributes = emptyMap(),
    )

    @Test
    fun `send serializes payload as JSON and resolves queue by name`() = runTest {
        val queueRequest = slot<GetQueueUrlRequest>()
        val url = queueUrl
        coEvery { client.getQueueUrl(capture(queueRequest)) } returns GetQueueUrlResponse { queueUrl = url }
        val request = slot<SendMessageRequest>()
        val response = SendMessageResponse { messageId = "sent-1" }
        coEvery { client.sendMessage(capture(request)) } returns response

        val result = template.destination(queueName).send(Order("o-1", 2))

        result shouldBe response
        queueRequest.captured.queueName shouldBe queueName
        request.captured.queueUrl shouldBe queueUrl
        request.captured.messageBody shouldBe orderJson
        request.captured.delaySeconds.shouldBeNull()
    }

    @Test
    fun `send passes String payload through unchanged`() = runTest {
        stubQueueUrl()
        val request = slot<SendMessageRequest>()
        coEvery { client.sendMessage(capture(request)) } returns SendMessageResponse { }

        template.destination(queueName).send("plain text")

        request.captured.messageBody shouldBe "plain text"
    }

    @Test
    fun `send accepts queue URL without resolution`() = runTest {
        val request = slot<SendMessageRequest>()
        coEvery { client.sendMessage(capture(request)) } returns SendMessageResponse { }

        template.destination(queueUrl).send("hello")

        request.captured.queueUrl shouldBe queueUrl
        coVerify(exactly = 0) { client.getQueueUrl(any<GetQueueUrlRequest>()) }
    }

    @Test
    fun `send sets delaySeconds from delay`() = runTest {
        stubQueueUrl()
        val request = slot<SendMessageRequest>()
        coEvery { client.sendMessage(capture(request)) } returns SendMessageResponse { }

        template.destination(queueName).send("hello", delay = 5.seconds)

        request.captured.delaySeconds shouldBe 5
    }

    @Test
    fun `send rejects negative delay without SDK send call`() = runTest {
        shouldThrow<SqsOperationException> { template.destination(queueName).send("hello", delay = (-1).seconds) }
        coVerify(exactly = 0) { client.sendMessage(any<SendMessageRequest>()) }
    }

    @Test
    fun `send rejects delay above 900 seconds without SDK send call`() = runTest {
        shouldThrow<SqsOperationException> { template.destination(queueName).send("hello", delay = 901.seconds) }
        coVerify(exactly = 0) { client.sendMessage(any<SendMessageRequest>()) }
    }

    @Test
    fun `send maps QueueDoesNotExist from sendMessage with cause`() = runTest {
        stubQueueUrl()
        val failure = QueueDoesNotExist { message = "queue vanished" }
        coEvery { client.sendMessage(any<SendMessageRequest>()) } throws failure

        val thrown = shouldThrow<SqsQueueNotFoundException> { template.destination(queueName).send("hello") }
        thrown.cause shouldBe failure
    }

    @Test
    fun `receive returns null for empty response and builds expected request`() = runTest {
        stubQueueUrl()
        val request = slot<ReceiveMessageRequest>()
        coEvery { client.receiveMessage(capture(request)) } returns ReceiveMessageResponse { messages = emptyList() }

        template.destination(queueName).receive(String::class.java).shouldBeNull()

        request.captured.queueUrl shouldBe queueUrl
        request.captured.maxNumberOfMessages shouldBe 1
        request.captured.waitTimeSeconds shouldBe 10
        request.captured.messageAttributeNames shouldBe listOf("All")
        request.captured.messageSystemAttributeNames shouldBe listOf(MessageSystemAttributeName.All)
        coVerify(exactly = 0) { client.deleteMessage(any<DeleteMessageRequest>()) }
    }

    @Test
    fun `receive AUTO converts payload and deletes message`() = runTest {
        stubQueueUrl()
        val message = sdkMessage(orderJson)
        coEvery { client.receiveMessage(any<ReceiveMessageRequest>()) } returns
            ReceiveMessageResponse { messages = listOf(message) }
        val deleteRequest = slot<DeleteMessageRequest>()
        val deleteResponse = DeleteMessageResponse { }
        coEvery { client.deleteMessage(capture(deleteRequest)) } returns deleteResponse

        val result = template.destination(queueName).receive(Order::class.java)

        result shouldBe SqsMessage(
            payload = Order("o-1", 2),
            messageId = "m-1",
            receiptHandle = "rh-1",
            body = orderJson,
            attributes = mapOf("SentTimestamp" to "1700000000000"),
            messageAttributes = message.messageAttributes!!,
        )
        deleteRequest.captured.queueUrl shouldBe queueUrl
        deleteRequest.captured.receiptHandle shouldBe "rh-1"
    }

    @Test
    fun `receive MANUAL does not delete message`() = runTest {
        stubQueueUrl()
        coEvery { client.receiveMessage(any<ReceiveMessageRequest>()) } returns
            ReceiveMessageResponse { messages = listOf(sdkMessage(orderJson)) }

        val result = template.destination(queueName)
            .receive(Order::class.java, acknowledgement = SqsAcknowledgementMode.MANUAL)

        result!!.payload shouldBe Order("o-1", 2)
        coVerify(exactly = 0) { client.deleteMessage(any<DeleteMessageRequest>()) }
    }

    @Test
    fun `receive conversion failure does not delete message`() = runTest {
        stubQueueUrl()
        coEvery { client.receiveMessage(any<ReceiveMessageRequest>()) } returns
            ReceiveMessageResponse { messages = listOf(sdkMessage("{not json")) }

        shouldThrow<SqsMessageConversionException> { template.destination(queueName).receive(Order::class.java) }
        coVerify(exactly = 0) { client.deleteMessage(any<DeleteMessageRequest>()) }
    }

    @Test
    fun `receive clamps timeout to 20 seconds`() = runTest {
        stubQueueUrl()
        val request = slot<ReceiveMessageRequest>()
        coEvery { client.receiveMessage(capture(request)) } returns ReceiveMessageResponse { }

        template.destination(queueName).receive(String::class.java, timeout = 60.seconds)

        request.captured.waitTimeSeconds shouldBe 20
    }

    @Test
    fun `receive rejects negative timeout without SDK receive call`() = runTest {
        shouldThrow<SqsOperationException> {
            template.destination(queueName).receive(String::class.java, timeout = (-1).seconds)
        }
        coVerify(exactly = 0) { client.receiveMessage(any<ReceiveMessageRequest>()) }
    }

    @Test
    fun `receive AUTO delete failure wraps SqsOperationException with cause`() = runTest {
        stubQueueUrl()
        coEvery { client.receiveMessage(any<ReceiveMessageRequest>()) } returns
            ReceiveMessageResponse { messages = listOf(sdkMessage("hello")) }
        val failure = SqsException("delete denied")
        coEvery { client.deleteMessage(any<DeleteMessageRequest>()) } throws failure

        val thrown = shouldThrow<SqsOperationException> { template.destination(queueName).receive(String::class.java) }
        thrown.cause shouldBe failure
    }

    @Test
    fun `reified receive infers target type`() = runTest {
        stubQueueUrl()
        coEvery { client.receiveMessage(any<ReceiveMessageRequest>()) } returns
            ReceiveMessageResponse { messages = listOf(sdkMessage(orderJson)) }

        val result = template.destination(queueName).receive<Order>(acknowledgement = SqsAcknowledgementMode.MANUAL)

        result!!.payload shouldBe Order("o-1", 2)
        coVerify(exactly = 0) { client.deleteMessage(any<DeleteMessageRequest>()) }
    }

    @Test
    fun `receive maps QueueDoesNotExist from receiveMessage with cause`() = runTest {
        stubQueueUrl()
        val failure = QueueDoesNotExist { message = "queue vanished" }
        coEvery { client.receiveMessage(any<ReceiveMessageRequest>()) } throws failure

        val thrown =
            shouldThrow<SqsQueueNotFoundException> { template.destination(queueName).receive(String::class.java) }
        thrown.cause shouldBe failure
    }

    @Test
    fun `acknowledge deletes with receipt handle and resolved URL`() = runTest {
        stubQueueUrl()
        val request = slot<DeleteMessageRequest>()
        val response = DeleteMessageResponse { }
        coEvery { client.deleteMessage(capture(request)) } returns response

        val result = template.destination(queueName).acknowledge(receivedMessage())

        result shouldBe response
        request.captured.queueUrl shouldBe queueUrl
        request.captured.receiptHandle shouldBe "rh-1"
    }

    @Test
    fun `reject sets visibility timeout to zero`() = runTest {
        stubQueueUrl()
        val request = slot<ChangeMessageVisibilityRequest>()
        val response = ChangeMessageVisibilityResponse { }
        coEvery { client.changeMessageVisibility(capture(request)) } returns response

        val result = template.destination(queueName).reject(receivedMessage())

        result shouldBe response
        request.captured.queueUrl shouldBe queueUrl
        request.captured.receiptHandle shouldBe "rh-1"
        request.captured.visibilityTimeout shouldBe 0
    }

    @Test
    fun `acknowledge maps ReceiptHandleIsInvalid with cause`() = runTest {
        stubQueueUrl()
        val failure = ReceiptHandleIsInvalid { message = "bad handle" }
        coEvery { client.deleteMessage(any<DeleteMessageRequest>()) } throws failure

        val thrown =
            shouldThrow<SqsOperationException> { template.destination(queueName).acknowledge(receivedMessage()) }
        thrown.cause shouldBe failure
    }

    @Test
    fun `reject maps MessageNotInflight with cause`() = runTest {
        stubQueueUrl()
        val failure = MessageNotInflight { }
        coEvery { client.changeMessageVisibility(any<ChangeMessageVisibilityRequest>()) } throws failure

        val thrown = shouldThrow<SqsOperationException> { template.destination(queueName).reject(receivedMessage()) }
        thrown.cause shouldBe failure
    }

    @Test
    fun `acknowledge maps QueueDoesNotExist with cause`() = runTest {
        stubQueueUrl()
        val failure = QueueDoesNotExist { message = "queue vanished" }
        coEvery { client.deleteMessage(any<DeleteMessageRequest>()) } throws failure

        val thrown = shouldThrow<SqsQueueNotFoundException> {
            template.destination(queueName).acknowledge(receivedMessage())
        }
        thrown.cause shouldBe failure
    }

    @Test
    fun `destination makes no request`() = runTest {
        template.destination(queueName)
        confirmVerified(client)
    }
}
