package io.github.hantsy.awskotlin.sns

/**
 * Thrown when a message body cannot be parsed as an SNS JSON notification envelope:
 * invalid JSON, a missing/unknown `Type`, or a missing required `MessageId`, `TopicArn`
 * or `Message` field. The underlying kotlinx.serialization error, when present, is preserved as the cause.
 */
class SnsNotificationParseException(message: String, cause: Throwable? = null) : RuntimeException(message, cause)
