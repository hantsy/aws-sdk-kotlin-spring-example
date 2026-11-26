package io.github.hantsy.awskotlin.sqs

/**
 * Base error for SQS template operations.
 *
 * Named `SqsTemplateException` (not `SqsException`) to avoid collision with the SDK's
 * [aws.sdk.kotlin.services.sqs.model.SqsException].
 */
open class SqsTemplateException(message: String, cause: Throwable? = null) : RuntimeException(message, cause)

/** The queue name or URL could not be resolved to an existing queue. */
class SqsQueueNotFoundException(queueNameOrUrl: String, cause: Throwable? = null) :
    SqsTemplateException("Queue not found: $queueNameOrUrl", cause)

/** A message payload could not be serialized or deserialized. */
class SqsMessageConversionException(message: String, cause: Throwable? = null) : SqsTemplateException(message, cause)

/** An SQS operation failed validation or the SDK reported an error. The original cause is preserved. */
class SqsOperationException(message: String, cause: Throwable? = null) : SqsTemplateException(message, cause)
