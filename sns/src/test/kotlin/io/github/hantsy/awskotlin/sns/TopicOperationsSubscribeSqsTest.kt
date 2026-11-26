package io.github.hantsy.awskotlin.sns

import aws.sdk.kotlin.services.sns.SnsClient
import aws.sdk.kotlin.services.sns.model.SetSubscriptionAttributesRequest
import aws.sdk.kotlin.services.sns.model.SetSubscriptionAttributesResponse
import aws.sdk.kotlin.services.sns.model.SubscribeRequest
import aws.sdk.kotlin.services.sns.model.SubscribeResponse
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.slot
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test

class TopicOperationsSubscribeSqsTest {
    private val client = mockk<SnsClient>()
    private val template = SnsTemplate(client)
    private val topicArn = "arn:aws:sns:us-east-1:123456789012:my-topic"
    private val queueArn = "arn:aws:sqs:us-east-1:123456789012:my-queue"
    private val subArn = "arn:aws:sns:us-east-1:123456789012:my-topic:sub-1"

    @Test
    fun `subscribeSqs subscribes with sqs protocol and defaults RawMessageDelivery to false`() = runTest {
        val subscribeRequest = slot<SubscribeRequest>()
        val attributesRequest = slot<SetSubscriptionAttributesRequest>()
        val response = SubscribeResponse { subscriptionArn = subArn }
        coEvery { client.subscribe(capture(subscribeRequest)) } returns response
        coEvery { client.setSubscriptionAttributes(capture(attributesRequest)) } returns
            SetSubscriptionAttributesResponse { }

        val result = template.topic(topicArn).subscribeSqs(queueArn)

        result shouldBe response
        subscribeRequest.captured.topicArn shouldBe topicArn
        subscribeRequest.captured.protocol shouldBe "sqs"
        subscribeRequest.captured.endpoint shouldBe queueArn
        attributesRequest.captured.subscriptionArn shouldBe subArn
        attributesRequest.captured.attributeName shouldBe "RawMessageDelivery"
        attributesRequest.captured.attributeValue shouldBe "false"
    }

    @Test
    fun `subscribeSqs with rawMessageDelivery true sets the attribute to true`() = runTest {
        val attributesRequest = slot<SetSubscriptionAttributesRequest>()
        coEvery { client.subscribe(any<SubscribeRequest>()) } returns SubscribeResponse { subscriptionArn = subArn }
        coEvery { client.setSubscriptionAttributes(capture(attributesRequest)) } returns
            SetSubscriptionAttributesResponse { }

        template.topic(topicArn).subscribeSqs(queueArn, rawMessageDelivery = true)

        attributesRequest.captured.subscriptionArn shouldBe subArn
        attributesRequest.captured.attributeName shouldBe "RawMessageDelivery"
        attributesRequest.captured.attributeValue shouldBe "true"
    }

    @Test
    fun `subscribeSqs fails when the subscribe response has no subscriptionArn`() = runTest {
        coEvery { client.subscribe(any<SubscribeRequest>()) } returns SubscribeResponse { }

        val thrown = shouldThrow<IllegalStateException> { template.topic(topicArn).subscribeSqs(queueArn) }

        thrown.message.shouldContain(queueArn)
        coVerify(exactly = 0) { client.setSubscriptionAttributes(any<SetSubscriptionAttributesRequest>()) }
    }
}
