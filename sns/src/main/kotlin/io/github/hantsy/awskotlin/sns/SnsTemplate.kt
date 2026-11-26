package io.github.hantsy.awskotlin.sns

import aws.sdk.kotlin.services.sns.SnsClient
import aws.sdk.kotlin.services.sns.model.UnsubscribeResponse
import aws.sdk.kotlin.services.sns.unsubscribe

/**
 * Entry point for fluent SNS operations backed by the AWS SDK for Kotlin [SnsClient].
 *
 * Obtain topic-scoped operations with [topic]. The selector accepts a topic ARN only;
 * no implicit topic creation or name resolution is performed.
 */
class SnsTemplate(private val client: SnsClient) {
    /** Returns operations scoped to the topic identified by [topicArn]. No request is made. */
    fun topic(topicArn: String): TopicOperations = TopicOperations(client, topicArn)

    /** Cancels the subscription identified by [subscriptionArn]. */
    suspend fun unsubscribe(subscriptionArn: String): UnsubscribeResponse = client.unsubscribe {
        this.subscriptionArn = subscriptionArn
    }
}
