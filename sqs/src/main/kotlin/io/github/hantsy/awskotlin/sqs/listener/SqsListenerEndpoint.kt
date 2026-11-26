package io.github.hantsy.awskotlin.sqs.listener

import kotlin.reflect.KFunction

/**
 * A discovered listener: the target [bean], its annotated [method] and the
 * standard [queues] (names or URLs) it consumes.
 */
data class SqsListenerEndpoint(val bean: Any, val method: KFunction<*>, val queues: List<String>)
