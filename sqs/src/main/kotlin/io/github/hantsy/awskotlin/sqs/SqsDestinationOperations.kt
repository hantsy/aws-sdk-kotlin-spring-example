package io.github.hantsy.awskotlin.sqs

import aws.sdk.kotlin.services.sqs.model.ChangeMessageVisibilityResponse
import aws.sdk.kotlin.services.sqs.model.DeleteMessageResponse
import aws.sdk.kotlin.services.sqs.model.SendMessageResponse
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

/**
 * Queue-scoped SQS operations. Created via [SqsTemplate.destination].
 *
 * Standard queues only; no implicit queue creation, no batch receive.
 */
interface SqsDestinationOperations {
    /**
     * Sends [payload] to the queue, optionally delaying delivery by [delay]
     * (0..900 seconds; invalid values throw [SqsOperationException]).
     */
    suspend fun send(payload: Any, delay: Duration? = null): SendMessageResponse

    /**
     * Receives a single message and converts its body to [targetType], or returns
     * `null` when the queue is empty. [timeout] is clamped to 0..20 seconds for
     * long polling; negative values throw [SqsOperationException].
     *
     * [SqsAcknowledgementMode.AUTO] deletes the message after successful conversion;
     * [SqsAcknowledgementMode.MANUAL] leaves it in flight. Conversion failures never
     * delete the message.
     */
    suspend fun <T : Any> receive(
        targetType: Class<T>,
        timeout: Duration = 10.seconds,
        acknowledgement: SqsAcknowledgementMode = SqsAcknowledgementMode.AUTO,
    ): SqsMessage<T>?

    /** Deletes the message from the queue using its receipt handle. */
    suspend fun acknowledge(message: SqsMessage<*>): DeleteMessageResponse

    /** Makes the message immediately visible again by setting its visibility timeout to 0. */
    suspend fun reject(message: SqsMessage<*>): ChangeMessageVisibilityResponse
}

/** Reified convenience overload of [SqsDestinationOperations.receive]. */
suspend inline fun <reified T : Any> SqsDestinationOperations.receive(
    timeout: Duration = 10.seconds,
    acknowledgement: SqsAcknowledgementMode = SqsAcknowledgementMode.AUTO,
): SqsMessage<T>? = receive(T::class.java, timeout, acknowledgement)
