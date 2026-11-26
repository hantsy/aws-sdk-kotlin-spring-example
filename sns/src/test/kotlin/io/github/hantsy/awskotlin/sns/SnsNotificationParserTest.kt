package io.github.hantsy.awskotlin.sns

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import io.kotest.matchers.types.shouldBeInstanceOf
import kotlinx.serialization.SerializationException
import org.junit.jupiter.api.Test

class SnsNotificationParserTest {
    private val topicArn = "arn:aws:sns:us-east-1:123456789012:my-topic"

    @Test
    fun `parse Notification envelope with all fields`() {
        val body =
            """
            {
              "Type": "Notification",
              "MessageId": "msg-1",
              "TopicArn": "$topicArn",
              "Message": "hello fan-out",
              "Subject": "greeting",
              "Timestamp": "2026-01-01T00:00:00.000Z",
              "SignatureVersion": "1",
              "Signature": "c2lnbmF0dXJl",
              "SigningCertURL": "https://sns.us-east-1.amazonaws.com/cert.pem",
              "UnsubscribeURL": "https://sns.us-east-1.amazonaws.com/?Action=Unsubscribe"
            }
            """.trimIndent()

        val notification = SnsNotificationParser.parse(body)

        notification shouldBe
            SnsNotification(
                type = SnsNotificationType.Notification,
                messageId = "msg-1",
                topicArn = topicArn,
                message = "hello fan-out",
                subject = "greeting",
                timestamp = "2026-01-01T00:00:00.000Z",
                signatureVersion = "1",
                signature = "c2lnbmF0dXJl",
                signingCertUrl = "https://sns.us-east-1.amazonaws.com/cert.pem",
                unsubscribeUrl = "https://sns.us-east-1.amazonaws.com/?Action=Unsubscribe"
            )
        notification.token.shouldBeNull()
    }

    @Test
    fun `parse SubscriptionConfirmation envelope with Token`() {
        val body =
            """
            {
              "Type": "SubscriptionConfirmation",
              "MessageId": "msg-2",
              "TopicArn": "$topicArn",
              "Message": "You have chosen to subscribe to the topic",
              "Token": "confirm-token",
              "SubscribeURL": "https://sns.us-east-1.amazonaws.com/?Action=ConfirmSubscription",
              "Timestamp": "2026-01-01T00:00:00.000Z"
            }
            """.trimIndent()

        val notification = SnsNotificationParser.parse(body)

        notification.type shouldBe SnsNotificationType.SubscriptionConfirmation
        notification.messageId shouldBe "msg-2"
        notification.topicArn shouldBe topicArn
        notification.token shouldBe "confirm-token"
        notification.subject.shouldBeNull()
    }

    @Test
    fun `parse UnsubscribeConfirmation envelope`() {
        val body =
            """
            {
              "Type": "UnsubscribeConfirmation",
              "MessageId": "msg-3",
              "TopicArn": "$topicArn",
              "Message": "You have chosen to deactivate subscription",
              "Token": "unsubscribe-token"
            }
            """.trimIndent()

        val notification = SnsNotificationParser.parse(body)

        notification.type shouldBe SnsNotificationType.UnsubscribeConfirmation
        notification.token shouldBe "unsubscribe-token"
    }

    @Test
    fun `unknown fields are ignored`() {
        val body =
            """
            {
              "Type": "Notification",
              "MessageId": "msg-4",
              "TopicArn": "$topicArn",
              "Message": "payload",
              "SomethingNew": {"nested": true},
              "MessageAttributes": {"key": {"Type": "String", "Value": "v"}}
            }
            """.trimIndent()

        val notification = SnsNotificationParser.parse(body)

        notification.message shouldBe "payload"
        notification.subject.shouldBeNull()
    }

    @Test
    fun `minimal Notification leaves optional fields null`() {
        val body =
            """
            {"Type":"Notification","MessageId":"msg-5","TopicArn":"$topicArn","Message":"only-required"}
            """.trimIndent()

        val notification = SnsNotificationParser.parse(body)

        notification.type shouldBe SnsNotificationType.Notification
        notification.subject.shouldBeNull()
        notification.timestamp.shouldBeNull()
        notification.signatureVersion.shouldBeNull()
        notification.signature.shouldBeNull()
        notification.signingCertUrl.shouldBeNull()
        notification.unsubscribeUrl.shouldBeNull()
        notification.token.shouldBeNull()
    }

    @Test
    fun `missing Type throws typed error`() {
        val body = """{"MessageId":"m","TopicArn":"$topicArn","Message":"x"}"""

        val thrown = shouldThrow<SnsNotificationParseException> { SnsNotificationParser.parse(body) }
        thrown.message.shouldNotBeNull() shouldContain "Type"
    }

    @Test
    fun `unknown Type throws typed error`() {
        val body = """{"Type":"Bogus","MessageId":"m","TopicArn":"$topicArn","Message":"x"}"""

        val thrown = shouldThrow<SnsNotificationParseException> { SnsNotificationParser.parse(body) }
        thrown.message.shouldNotBeNull() shouldContain "Bogus"
    }

    @Test
    fun `missing required fields throw typed error`() {
        val fields =
            mapOf(
                "MessageId" to """{"Type":"Notification","TopicArn":"$topicArn","Message":"x"}""",
                "TopicArn" to """{"Type":"Notification","MessageId":"m","Message":"x"}""",
                "Message" to """{"Type":"Notification","MessageId":"m","TopicArn":"$topicArn"}"""
            )

        fields.forEach { (field, body) ->
            val thrown = shouldThrow<SnsNotificationParseException> { SnsNotificationParser.parse(body) }
            thrown.message.shouldNotBeNull() shouldContain field
        }
    }

    @Test
    fun `non-string required field throws typed error`() {
        val body = """{"Type":"Notification","MessageId":42,"TopicArn":"$topicArn","Message":"x"}"""

        val thrown = shouldThrow<SnsNotificationParseException> { SnsNotificationParser.parse(body) }
        thrown.message.shouldNotBeNull() shouldContain "MessageId"
    }

    @Test
    fun `invalid JSON throws typed error with serialization cause`() {
        val thrown = shouldThrow<SnsNotificationParseException> { SnsNotificationParser.parse("{not json") }
        thrown.cause.shouldNotBeNull().shouldBeInstanceOf<SerializationException>()
    }

    @Test
    fun `non-object JSON throws typed error`() {
        shouldThrow<SnsNotificationParseException> { SnsNotificationParser.parse("[1,2,3]") }
        shouldThrow<SnsNotificationParseException> { SnsNotificationParser.parse("\"just a string\"") }
        shouldThrow<SnsNotificationParseException> { SnsNotificationParser.parse("null") }
    }
}
