package io.github.hantsy.awskotlin.sns

import aws.sdk.kotlin.services.sns.SnsClient
import aws.sdk.kotlin.services.sns.deleteTopic
import aws.sdk.kotlin.services.sns.model.DeleteTopicResponse
import aws.sdk.kotlin.services.sns.model.PublishResponse
import aws.sdk.kotlin.services.sns.model.SubscribeResponse
import aws.sdk.kotlin.services.sns.publish
import aws.sdk.kotlin.services.sns.setSubscriptionAttributes
import aws.sdk.kotlin.services.sns.subscribe

/**
 * Topic-scoped SNS operations. Created via [SnsTemplate.topic].
 *
 * Each method maps directly to the matching SDK request and propagates SDK exceptions
 * unchanged.
 */
class TopicOperations internal constructor(private val client: SnsClient, private val topicArn: String,) {
    /** Publishes [message] to the topic, optionally setting a [subject]. */
    suspend fun publish(message: String, subject: String? = null): PublishResponse = client.publish {
        topicArn = this@TopicOperations.topicArn
        this.message = message
        this.subject = subject
    }

    /** Subscribes the given [endpoint] using [protocol] (for example `email`, `sqs`, `https`). */
    suspend fun subscribe(protocol: String, endpoint: String): SubscribeResponse = client.subscribe {
        topicArn = this@TopicOperations.topicArn
        this.protocol = protocol
        this.endpoint = endpoint
    }

    /**
     * Subscribes the SQS queue identified by [queueArn] to this topic for SNS -> SQS fan-out,
     * then sets the subscription's `RawMessageDelivery` attribute to [rawMessageDelivery].
     *
     * With the default `rawMessageDelivery = false`, SQS listeners receive the SNS JSON
     * envelope as a String body and can call [SnsNotificationParser.parse] to obtain an
     * [SnsNotification]. With `rawMessageDelivery = true`, the envelope is bypassed and the
     * queue receives the published message body unchanged.
     *
     * The queue must exist and its policy must allow the topic to send messages to it
     * (`sqs:SendMessage` with an `aws:SourceArn` condition on this topic).
     *
     * @throws IllegalStateException if the subscribe response carries no subscription ARN.
     */
    suspend fun subscribeSqs(queueArn: String, rawMessageDelivery: Boolean = false): SubscribeResponse {
        val response = subscribe(protocol = "sqs", endpoint = queueArn)
        val subArn =
            response.subscriptionArn
                ?: throw IllegalStateException(
                    "SNS subscribe for queue $queueArn returned no subscriptionArn; " +
                        "cannot set RawMessageDelivery"
                )
        client.setSubscriptionAttributes {
            subscriptionArn = subArn
            attributeName = "RawMessageDelivery"
            attributeValue = rawMessageDelivery.toString()
        }
        return response
    }

    /** Deletes the topic. */
    suspend fun delete(): DeleteTopicResponse = client.deleteTopic {
        topicArn = this@TopicOperations.topicArn
    }
}
