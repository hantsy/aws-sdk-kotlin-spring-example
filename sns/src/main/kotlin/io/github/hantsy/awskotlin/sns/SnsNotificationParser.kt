package io.github.hantsy.awskotlin.sns

import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.contentOrNull

/**
 * Parses the SNS JSON notification envelope delivered to subscribers.
 *
 * Typical use is SNS -> SQS fan-out: subscribe a queue with
 * [TopicOperations.subscribeSqs] (`rawMessageDelivery = false`, the default), receive the
 * envelope as a String in an SQS listener, and call [parse] to obtain an [SnsNotification].
 *
 * Unknown envelope fields are ignored. A missing or unknown `Type`, or a missing required
 * `MessageId`, `TopicArn` or `Message` field, raises [SnsNotificationParseException].
 */
object SnsNotificationParser {
    /**
     * Parses [body] as an SNS JSON notification envelope.
     *
     * @throws SnsNotificationParseException if the body is not valid JSON, is not a JSON
     * object, has a missing/unknown `Type`, or lacks a required string field.
     */
    fun parse(body: String): SnsNotification {
        val root =
            try {
                Json.parseToJsonElement(body)
            } catch (e: SerializationException) {
                throw SnsNotificationParseException("SNS notification envelope is not valid JSON", e)
            }
        if (root !is JsonObject) {
            throw SnsNotificationParseException("SNS notification envelope must be a JSON object")
        }
        val rawType = requiredText(root, "Type")
        val type =
            SnsNotificationType.entries.firstOrNull { it.name == rawType }
                ?: throw SnsNotificationParseException("Unknown SNS notification Type '$rawType'")
        return SnsNotification(
            type = type,
            messageId = requiredText(root, "MessageId"),
            topicArn = requiredText(root, "TopicArn"),
            message = requiredText(root, "Message"),
            subject = optionalText(root, "Subject"),
            timestamp = optionalText(root, "Timestamp"),
            signatureVersion = optionalText(root, "SignatureVersion"),
            signature = optionalText(root, "Signature"),
            signingCertUrl = optionalText(root, "SigningCertURL"),
            unsubscribeUrl = optionalText(root, "UnsubscribeURL"),
            token = optionalText(root, "Token")
        )
    }

    private fun requiredText(root: JsonObject, field: String): String {
        val element = root[field]
        if (element !is JsonPrimitive || !element.isString) {
            throw SnsNotificationParseException(
                "SNS notification envelope is missing required string field '$field'"
            )
        }
        return element.content
    }

    private fun optionalText(root: JsonObject, field: String): String? =
        (root[field] as? JsonPrimitive)?.takeIf { it.isString }?.contentOrNull
}
