package io.github.hantsy.awskotlin.sns

/**
 * An SNS JSON notification envelope as delivered to subscribers (for example an SQS queue
 * subscribed to a topic without raw message delivery).
 *
 * Field names follow the SNS envelope keys: `Type`, `MessageId`, `TopicArn`, `Message`,
 * `Subject`, `Timestamp`, `SignatureVersion`, `Signature`, `SigningCertURL`,
 * `UnsubscribeURL` and `Token`. Obtain instances with [SnsNotificationParser.parse].
 *
 * @property type the envelope `Type`.
 * @property messageId unique identifier of the notification.
 * @property topicArn ARN of the topic the message was published to.
 * @property message the message body as published (still a String; decode it yourself if it is JSON).
 * @property subject optional subject supplied at publish time.
 * @property timestamp ISO-8601 publish time, kept as the raw string.
 * @property signatureVersion version of the signature algorithm.
 * @property signature base64-encoded signature of the envelope.
 * @property signingCertUrl URL of the certificate used to sign the envelope.
 * @property unsubscribeUrl URL for unsubscribing the endpoint.
 * @property token confirmation token present on subscription/unsubscribe confirmation messages.
 */
data class SnsNotification(
    val type: SnsNotificationType,
    val messageId: String,
    val topicArn: String,
    val message: String,
    val subject: String? = null,
    val timestamp: String? = null,
    val signatureVersion: String? = null,
    val signature: String? = null,
    val signingCertUrl: String? = null,
    val unsubscribeUrl: String? = null,
    val token: String? = null
)
