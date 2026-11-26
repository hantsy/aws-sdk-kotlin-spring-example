package io.github.hantsy.awskotlin.sqs

/** Controls whether received messages are deleted automatically. */
enum class SqsAcknowledgementMode {
    /** Delete the message after successful payload conversion (default). */
    AUTO,

    /** Leave the message in the queue; the caller must acknowledge or reject it. */
    MANUAL,
}
