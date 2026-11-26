package io.github.hantsy.awskotlin.sqs

import aws.sdk.kotlin.services.sqs.SqsClient
import aws.sdk.kotlin.services.sqs.model.GetQueueUrlRequest
import aws.sdk.kotlin.services.sqs.model.GetQueueUrlResponse
import aws.sdk.kotlin.services.sqs.model.QueueDoesNotExist
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import io.mockk.slot
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test

class SqsQueueResolverTest {
    private val client = mockk<SqsClient>()
    private val resolver = SqsQueueResolver(client)
    private val queueUrl = "https://sqs.us-east-1.amazonaws.com/123456789012/orders"

    @Test
    fun `https URL passes through unchanged without SDK call`() = runTest {
        resolver.resolve(queueUrl) shouldBe queueUrl
        confirmVerified(client)
    }

    @Test
    fun `http URL passes through unchanged without SDK call`() = runTest {
        val url = "http://localhost:4566/000000000000/orders"
        resolver.resolve(url) shouldBe url
        confirmVerified(client)
    }

    @Test
    fun `queue name is resolved with getQueueUrl`() = runTest {
        val url = queueUrl
        val request = slot<GetQueueUrlRequest>()
        coEvery { client.getQueueUrl(capture(request)) } returns GetQueueUrlResponse { queueUrl = url }

        resolver.resolve("orders") shouldBe queueUrl
        request.captured.queueName shouldBe "orders"
    }

    @Test
    fun `successful name resolution is cached`() = runTest {
        val url = queueUrl
        coEvery { client.getQueueUrl(any<GetQueueUrlRequest>()) } returns GetQueueUrlResponse { queueUrl = url }

        resolver.resolve("orders") shouldBe queueUrl
        resolver.resolve("orders") shouldBe queueUrl

        coVerify(exactly = 1) { client.getQueueUrl(any<GetQueueUrlRequest>()) }
    }

    @Test
    fun `QueueDoesNotExist maps to SqsQueueNotFoundException with cause`() = runTest {
        val failure = QueueDoesNotExist { message = "queue does not exist" }
        coEvery { client.getQueueUrl(any<GetQueueUrlRequest>()) } throws failure

        val thrown = shouldThrow<SqsQueueNotFoundException> { resolver.resolve("missing") }
        thrown.cause shouldBe failure
    }

    @Test
    fun `missing queueUrl in response maps to SqsQueueNotFoundException`() = runTest {
        coEvery { client.getQueueUrl(any<GetQueueUrlRequest>()) } returns GetQueueUrlResponse { }

        shouldThrow<SqsQueueNotFoundException> { resolver.resolve("missing") }
    }

    @Test
    fun `FIFO queue name is rejected before any SDK call`() = runTest {
        shouldThrow<SqsOperationException> { resolver.resolve("orders.fifo") }
        confirmVerified(client)
    }

    @Test
    fun `FIFO queue URL is rejected before any SDK call`() = runTest {
        shouldThrow<SqsOperationException> { resolver.resolve("$queueUrl.fifo") }
        confirmVerified(client)
    }
}
