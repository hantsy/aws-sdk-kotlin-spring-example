package io.github.hantsy.awskotlin.sns

/**
 * The kind of message carried by an SNS JSON envelope, taken from its `Type` field.
 */
enum class SnsNotificationType {
    /** A published message delivered to subscribers. */
    Notification,

    /** Sent to an endpoint when a subscription is created and must be confirmed. */
    SubscriptionConfirmation,

    /** Sent to an endpoint when its subscription is removed. */
    UnsubscribeConfirmation,
}
