package io.github.hantsy.awskotlin.sqs

import aws.sdk.kotlin.services.sqs.SqsClient

/**
 * Entry point for fluent SQS operations backed by the AWS SDK for Kotlin [SqsClient].
 *
 * Obtain queue-scoped operations with [destination]; the queue name or URL is
 * resolved lazily on the first operation.
 */
class SqsTemplate(
    private val client: SqsClient,
    private val converter: SqsMessageConverter = KotlinxSqsMessageConverter(),
    private val queueResolver: SqsQueueResolver = SqsQueueResolver(client),
) {
    /** Returns operations scoped to [queueNameOrUrl]. No request is made. */
    fun destination(queueNameOrUrl: String): SqsDestinationOperations =
        DefaultSqsDestinationOperations(client, converter, queueResolver, queueNameOrUrl)
}
