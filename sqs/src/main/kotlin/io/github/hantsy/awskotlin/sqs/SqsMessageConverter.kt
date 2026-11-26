package io.github.hantsy.awskotlin.sqs

/** Converts message payloads to and from SQS message bodies. */
interface SqsMessageConverter {
    /** Serializes [payload] into a message body. */
    fun toBody(payload: Any): String

    /** Deserializes [body] into an instance of [targetType]. */
    fun <T : Any> fromBody(body: String, targetType: Class<T>): T
}
