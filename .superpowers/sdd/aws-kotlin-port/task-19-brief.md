# Task 19 — SNS notification envelope support for SQS fan-out

## Context
Worktree: D:/hantsylabs/aws-sdk-kotlin-spring-example/.worktrees/awskotlin-spring-port
No commits authorized. Do not modify examples/list/utilities/aws-build-tool. Do not dispatch subagents.
Research ruling: AWS SDK Kotlin SNS has no receive/poll operation and no Notification/NotificationStatus shapes. SNS is push-only. The SDK-grounded listener pattern is SNS->SQS fan-out reusing the SQS listener. This task adds envelope objects/parser and an SQS subscription convenience; it does NOT add an SNS pull listener.

## Global constraints
- AWS SDK Kotlin 1.9.45; Spring Boot 4.2.0 BOM; Kotlin 2.4.10; Java 25.
- Package io.github.hantsy.awskotlin.sns; artifact awskotlin-spring-sns.
- Jackson 3 tools.jackson.* only.
- LocalStack tests skip without Docker/token; localstack/localstack:4.
- JUnit 6 tests return Unit.

## Required API
Add to sns module:
```kotlin
enum class SnsNotificationType { Notification, SubscriptionConfirmation, UnsubscribeConfirmation }

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
    val token: String? = null,
)

object SnsNotificationParser {
    fun parse(body: String): SnsNotification
}

class SnsNotificationParseException(message: String, cause: Throwable? = null) : RuntimeException(message, cause)
```
Parser must read the SNS JSON envelope fields: Type, MessageId, TopicArn, Message, Subject, Timestamp, SignatureVersion, Signature, SigningCertURL, UnsubscribeURL, Token. Unknown fields ignored. Missing/invalid Type or missing required MessageId/TopicArn/Message throws SnsNotificationParseException. Do not invent a NotificationStatus type.

Add TopicOperations convenience:
```kotlin
suspend fun subscribeSqs(queueArn: String, rawMessageDelivery: Boolean = false): SubscribeResponse
```
Implementation: call existing subscribe(protocol="sqs", endpoint=queueArn), then client.setSubscriptionAttributes { subscriptionArn = response.subscriptionArn; attributes = mapOf("RawMessageDelivery" to rawMessageDelivery.toString()) }. Return the SubscribeResponse. Verify SDK extension names from jar/source. If subscriptionArn is null, throw IllegalStateException/IllegalArgumentException with clear message.

Document in KDoc that SQS listeners can receive the envelope as String and call SnsNotificationParser.parse; rawMessageDelivery=true bypasses the envelope.

## Build changes
- sns/build.gradle.kts: add implementation(libs.jacksonDatabind). Add testImplementation(libs.awsSqs), testImplementation(libs.testcontainersJunitJupiter), testImplementation(libs.testcontainersLocalstack) for fan-out integration test. Keep existing workarounds.
- Do not add Spring dependencies to sns module.

## Tests required
Unit tests:
- parse Notification envelope with all fields; parse SubscriptionConfirmation with Token; parse UnsubscribeConfirmation.
- unknown fields ignored; missing Type/MessageId/TopicArn/Message throws typed error; invalid JSON throws typed error; unknown Type throws typed error.
- subscribeSqs calls subscribe with protocol sqs and endpoint queueArn, then setSubscriptionAttributes RawMessageDelivery true/false; null subscriptionArn fails.
Use MockK/runTest; tests return Unit.

Integration test (skip-safe, follow S3TemplateLocalStackTest pattern):
- LocalStack services sns,sqs; token/Docker assumptions; localstack/localstack:4.
- Create SNS topic and SQS queue; subscribeSqs(queueArn, rawMessageDelivery=false); publish message; receive from SQS; parse envelope; assert message/topicArn.
- Optionally rawMessageDelivery=true receives original body without envelope.
- Best-effort cleanup. If LocalStack SNS->SQS fan-out is unreliable in container, document and keep test skip-safe; do not fake assertions.

## Verification
Run from worktree root:
- `./gradlew :sns:test`
- `./gradlew :sns:lintKotlin`
- `./gradlew :starters:starter-sns:test` (ensure no regression)
Report exact summaries and skipped LocalStack count/reason.

## Report contract
Write full report to `.superpowers/sdd/aws-kotlin-port/task-19-report.md`.
Return only status, one-line test summary, concerns.