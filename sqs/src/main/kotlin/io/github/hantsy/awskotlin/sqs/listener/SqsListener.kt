package io.github.hantsy.awskotlin.sqs.listener

/**
 * Marks a member function as an SQS message listener for the given standard [queues]
 * (names or URLs).
 *
 * The method must declare exactly one payload parameter — either a plain payload
 * type `T` or [io.github.hantsy.awskotlin.sqs.SqsMessage]`<T>` with a concrete `T` —
 * and may optionally declare an [Acknowledge] parameter. Suspend and non-suspend
 * methods are both supported.
 *
 * Without an [Acknowledge] parameter the message is deleted automatically after the
 * method returns successfully; if the method throws, the message is left in flight.
 * With an [Acknowledge] parameter the caller decides: [Acknowledge.accept] deletes,
 * [Acknowledge.reject] makes the message visible again, and no decision leaves it.
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class SqsListener(vararg val queues: String)
