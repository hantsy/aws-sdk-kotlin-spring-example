package io.github.hantsy.awskotlin.sqs

import aws.sdk.kotlin.services.sqs.model.MessageAttributeValue

/**
 * A received SQS message with its converted [payload] and the raw SDK metadata.
 *
 * [receiptHandle] is required to [SqsDestinationOperations.acknowledge] or
 * [SqsDestinationOperations.reject] the message in manual acknowledgement mode.
 */
data class SqsMessage<T>(
    val payload: T,
    val messageId: String,
    val receiptHandle: String,
    val body: String,
    val attributes: Map<String, String>,
    val messageAttributes: Map<String, MessageAttributeValue>,
)
