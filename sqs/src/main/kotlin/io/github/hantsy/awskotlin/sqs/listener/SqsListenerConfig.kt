package io.github.hantsy.awskotlin.sqs.listener

import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

/**
 * Runtime settings shared by listener containers.
 *
 * [maxConcurrentMessages] caps in-flight messages per container across all its
 * queues; [pollTime] is the long-polling wait per receive (clamped to 0..20 seconds
 * by the template); [shutdownTimeout] bounds how long stop waits for in-flight
 * message processing before cancelling it.
 */
data class SqsListenerConfig(
    val autoStartup: Boolean = true,
    val maxConcurrentMessages: Int = 10,
    val pollTime: Duration = 10.seconds,
    val shutdownTimeout: Duration = 20.seconds
)
