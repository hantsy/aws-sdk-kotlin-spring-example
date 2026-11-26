package io.github.hantsy.awskotlin.sns

import aws.sdk.kotlin.services.sns.SnsClient
import aws.sdk.kotlin.services.sns.model.DeleteTopicRequest
import aws.sdk.kotlin.services.sns.model.DeleteTopicResponse
import aws.sdk.kotlin.services.sns.model.NotFoundException
import aws.sdk.kotlin.services.sns.model.PublishRequest
import aws.sdk.kotlin.services.sns.model.PublishResponse
import aws.sdk.kotlin.services.sns.model.SnsException
import aws.sdk.kotlin.services.sns.model.SubscribeRequest
import aws.sdk.kotlin.services.sns.model.SubscribeResponse
import aws.sdk.kotlin.services.sns.model.UnsubscribeRequest
import aws.sdk.kotlin.services.sns.model.UnsubscribeResponse
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.slot
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test

class SnsTemplateTest {
    private val client = mockk<SnsClient>()
    private val template = SnsTemplate(client)
    private val topicArn = "arn:aws:sns:us-east-1:123456789012:my-topic"

    @Test
    fun `publish builds PublishRequest with topicArn message and subject`() = runTest {
        val request = slot<PublishRequest>()
        val response = PublishResponse { messageId = "msg-1" }
        coEvery { client.publish(capture(request)) } returns response

        val result = template.topic(topicArn).publish("hello", subject = "greeting")

        result shouldBe response
        request.captured.topicArn shouldBe topicArn
        request.captured.message shouldBe "hello"
        request.captured.subject shouldBe "greeting"
    }

    @Test
    fun `publish without subject leaves it unset`() = runTest {
        val request = slot<PublishRequest>()
        coEvery { client.publish(capture(request)) } returns PublishResponse { }

        template.topic(topicArn).publish("hello")

        request.captured.topicArn shouldBe topicArn
        request.captured.message shouldBe "hello"
        request.captured.subject.shouldBeNull()
    }

    @Test
    fun `subscribe builds SubscribeRequest with topicArn protocol and endpoint`() = runTest {
        val request = slot<SubscribeRequest>()
        val response = SubscribeResponse { subscriptionArn = "arn:aws:sns:us-east-1:123456789012:my-topic:sub-1" }
        coEvery { client.subscribe(capture(request)) } returns response

        val result = template.topic(topicArn).subscribe("email", "user@example.com")

        result shouldBe response
        request.captured.topicArn shouldBe topicArn
        request.captured.protocol shouldBe "email"
        request.captured.endpoint shouldBe "user@example.com"
    }

    @Test
    fun `delete builds DeleteTopicRequest with topicArn`() = runTest {
        val request = slot<DeleteTopicRequest>()
        val response = DeleteTopicResponse { }
        coEvery { client.deleteTopic(capture(request)) } returns response

        val result = template.topic(topicArn).delete()

        result shouldBe response
        request.captured.topicArn shouldBe topicArn
    }

    @Test
    fun `unsubscribe builds UnsubscribeRequest with subscriptionArn`() = runTest {
        val request = slot<UnsubscribeRequest>()
        val response = UnsubscribeResponse { }
        coEvery { client.unsubscribe(capture(request)) } returns response
        val subscriptionArn = "arn:aws:sns:us-east-1:123456789012:my-topic:sub-1"

        val result = template.unsubscribe(subscriptionArn)

        result shouldBe response
        request.captured.subscriptionArn shouldBe subscriptionArn
    }

    @Test
    fun `publish propagates NotFoundException unchanged`() = runTest {
        val failure = NotFoundException { message = "topic not found" }
        coEvery { client.publish(any<PublishRequest>()) } throws failure

        val thrown = shouldThrow<NotFoundException> { template.topic(topicArn).publish("hello") }
        thrown shouldBe failure
    }

    @Test
    fun `subscribe propagates other SDK exceptions unchanged`() = runTest {
        val failure = SnsException("access denied")
        coEvery { client.subscribe(any<SubscribeRequest>()) } throws failure

        val thrown = shouldThrow<SnsException> { template.topic(topicArn).subscribe("email", "user@example.com") }
        thrown shouldBe failure
    }
}
