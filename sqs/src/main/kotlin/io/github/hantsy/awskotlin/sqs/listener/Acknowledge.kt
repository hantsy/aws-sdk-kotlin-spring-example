package io.github.hantsy.awskotlin.sqs.listener

import io.github.hantsy.awskotlin.sqs.SqsMessage
import io.github.hantsy.awskotlin.sqs.SqsTemplate

/**
 * Manual acknowledgement handle passed to listener methods that declare an
 * [Acknowledge] parameter. Bound to the received message and its queue.
 */
interface Acknowledge {
    /** Deletes the message from the queue. */
    suspend fun accept()

    /** Makes the message immediately visible again (visibility timeout 0). */
    suspend fun reject()
}

/** Default [Acknowledge] backed by the fluent template's queue-scoped operations. */
internal class DefaultAcknowledge(
    private val template: SqsTemplate,
    private val queueUrl: String,
    private val message: SqsMessage<*>
) : Acknowledge {
    override suspend fun accept() {
        template.destination(queueUrl).acknowledge(message)
    }

    override suspend fun reject() {
        template.destination(queueUrl).reject(message)
    }
}
