package io.github.hantsy.awskotlin.sqs

import aws.sdk.kotlin.services.sqs.SqsClient
import aws.sdk.kotlin.services.sqs.getQueueUrl
import aws.sdk.kotlin.services.sqs.model.QueueDoesNotExist
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

/**
 * Resolves a queue name or URL to a queue URL, caching successful name lookups.
 *
 * URLs (`https://` or `http://`) pass through unchanged. Names are resolved with
 * `getQueueUrl`; the cache is guarded by a [Mutex] so concurrent coroutines never
 * trigger duplicate lookups for the same name. FIFO queues (`.fifo` suffix) are
 * rejected with [SqsOperationException] before any SDK call.
 */
class SqsQueueResolver(private val client: SqsClient) {
    private val mutex = Mutex()
    private val cache = mutableMapOf<String, String>()

    /** Returns the queue URL for [queueNameOrUrl]. */
    suspend fun resolve(queueNameOrUrl: String): String {
        if (queueNameOrUrl.endsWith(".fifo")) {
            throw SqsOperationException("FIFO queues are not supported: $queueNameOrUrl")
        }
        if (queueNameOrUrl.startsWith("https://") || queueNameOrUrl.startsWith("http://")) {
            return queueNameOrUrl
        }
        return mutex.withLock {
            cache.getOrPut(queueNameOrUrl) {
                val response = try {
                    client.getQueueUrl { queueName = queueNameOrUrl }
                } catch (e: QueueDoesNotExist) {
                    throw SqsQueueNotFoundException(queueNameOrUrl, e)
                }
                response.queueUrl ?: throw SqsQueueNotFoundException(queueNameOrUrl)
            }
        }
    }
}
