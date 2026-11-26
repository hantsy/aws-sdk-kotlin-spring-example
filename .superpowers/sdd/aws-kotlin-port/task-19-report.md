# Task 19 Report — SNS notification envelope support for SQS fan-out

Status: COMPLETE (working-tree changes only; no commits made)

## Scope delivered

SNS envelope model/parser plus an SQS subscription convenience in the `sns` module
(`awskotlin-spring-sns`, package `io.github.hantsy.awskotlin.sns`). Per the research ruling,
no SNS pull listener and no `NotificationStatus` type were added; SNS remains push-only and
the SDK-grounded pattern is SNS -> SQS fan-out reusing the existing SQS listener runtime.

## Files added (main)

- `sns/src/main/kotlin/io/github/hantsy/awskotlin/sns/SnsNotificationType.kt`
  — enum `Notification`, `SubscriptionConfirmation`, `UnsubscribeConfirmation`.
- `sns/src/main/kotlin/io/github/hantsy/awskotlin/sns/SnsNotification.kt`
  — data class exactly per the brief: `type`, `messageId`, `topicArn`, `message` required;
  `subject`, `timestamp`, `signatureVersion`, `signature`, `signingCertUrl`, `unsubscribeUrl`,
  `token` optional (default null). Timestamp kept as raw String.
- `sns/src/main/kotlin/io/github/hantsy/awskotlin/sns/SnsNotificationParseException.kt`
  — `class SnsNotificationParseException(message: String, cause: Throwable? = null) : RuntimeException(message, cause)`.
- `sns/src/main/kotlin/io/github/hantsy/awskotlin/sns/SnsNotificationParser.kt`
  — `object SnsNotificationParser { fun parse(body: String): SnsNotification }`.
  Jackson 3 (`tools.jackson.databind.json.JsonMapper`) `readTree`; reads `Type`, `MessageId`,
  `TopicArn`, `Message`, `Subject`, `Timestamp`, `SignatureVersion`, `Signature`,
  `SigningCertURL`, `UnsubscribeURL`, `Token`. Unknown fields ignored. Typed exception for:
  invalid JSON (Jackson cause preserved), non-object JSON (array/string/null), missing or
  non-string `Type`, unknown `Type` value, missing/non-string `MessageId`/`TopicArn`/`Message`.
  Optional fields that are present but non-string are treated as absent (null).

## Files modified (main)

- `sns/src/main/kotlin/io/github/hantsy/awskotlin/sns/TopicOperations.kt`
  — added:
  ```kotlin
  suspend fun subscribeSqs(queueArn: String, rawMessageDelivery: Boolean = false): SubscribeResponse
  ```
  Calls existing `subscribe(protocol = "sqs", endpoint = queueArn)`, then
  `client.setSubscriptionAttributes { subscriptionArn = ...; attributeName = "RawMessageDelivery";
  attributeValue = rawMessageDelivery.toString() }`, returns the `SubscribeResponse`.
  Null `subscriptionArn` throws `IllegalStateException` naming the queue ARN, before any
  attribute call. KDoc documents the fan-out pattern: SQS listeners receive the envelope as
  a String and call `SnsNotificationParser.parse`; `rawMessageDelivery = true` bypasses the
  envelope; the queue policy must allow the topic `sqs:SendMessage`.
- `sns/build.gradle.kts`
  — added `implementation(libs.jacksonDatabind)` (Jackson 3, version via Spring Boot 4.2.0 BOM),
  `testImplementation(libs.awsSqs)`, `testImplementation(libs.testcontainersJunitJupiter)`,
  `testImplementation(libs.testcontainersLocalstack)`. No Spring dependencies added.
  Existing ktlint compiler-pin workaround untouched.

## SDK verification (deviation from brief, grounded in jar)

Verified with `javap` against the cached `sns-jvm-1.9.45.jar`:

- Extension `aws.sdk.kotlin.services.sns.setSubscriptionAttributes(SnsClient, SetSubscriptionAttributesRequest.Builder.() -> Unit)`
  exists in `SnsClientKt` (alongside `subscribe`, `publish`, `unsubscribe`).
- **Brief deviation:** the brief suggested `attributes = mapOf("RawMessageDelivery" to ...)`,
  but SNS `SetSubscriptionAttributesRequest` has NO `attributes` map. Its builder exposes
  `subscriptionArn`, `attributeName`, `attributeValue` (single attribute per call — unlike
  SQS `SetQueueAttributes`). Implementation therefore uses
  `attributeName = "RawMessageDelivery"; attributeValue = rawMessageDelivery.toString()`,
  which is semantically identical to the brief's intent.
- SQS test-side APIs verified from `sqs-jvm-1.9.45.jar`: `createQueue`, `getQueueAttributes`,
  `setQueueAttributes`, `receiveMessage`, `deleteQueue` extensions;
  `GetQueueAttributesResponse.attributes` and `SetQueueAttributesRequest.attributes` are
  `Map<QueueAttributeName, String>` (used `QueueAttributeName.QueueArn` / `.Policy`).
- Jackson 3 API verified from `jackson-databind-3.1.8.jar`: `JsonNode.get(String)` (null when
  missing), `isString`, `asString`, `isObject`, `ObjectMapper.readTree(String)`; parser uses
  these (not the Jackson 2 `asText`/`isTextual` names). No Kotlin module needed since the
  parser maps fields manually from the tree.

## Tests added

Unit (JUnit 6, all return Unit; kotest assertions; MockK + runTest for the suspend API):

- `SnsNotificationParserTest` (11 tests): full Notification envelope (all fields);
  SubscriptionConfirmation with Token (SubscribeURL ignored as unknown); UnsubscribeConfirmation;
  unknown fields ignored (incl. MessageAttributes); minimal Notification leaves optionals null;
  missing Type; unknown Type; missing MessageId/TopicArn/Message (each asserted to name the field);
  non-string required field; invalid JSON (cause is `tools.jackson.core.JacksonException`);
  non-object JSON (array, string, null).
- `TopicOperationsSubscribeSqsTest` (3 tests): default call captures `SubscribeRequest`
  (topicArn/protocol=sqs/endpoint=queueArn) and `SetSubscriptionAttributesRequest`
  (subscriptionArn, RawMessageDelivery="false"), returns the SubscribeResponse;
  `rawMessageDelivery = true` sets "true"; null subscriptionArn throws IllegalStateException
  mentioning the queue ARN and `setSubscriptionAttributes` is never called (coVerify exactly 0).

Integration (skip-safe, follows `S3TemplateLocalStackTest` pattern):

- `SnsSqsFanOutLocalStackTest` (2 tests): `localstack/localstack:4` pinned in code,
  services `sns,sqs`; `assumeTrue` on `LOCALSTACK_AUTH_TOKEN` then Docker availability;
  StaticCredentialsProvider test/test, endpoint from container. Each test creates a unique
  topic + queue, resolves QueueArn via getQueueAttributes, applies an SQS queue policy
  allowing the topic `sqs:SendMessage` (ArnEquals aws:SourceArn condition), then:
  1. `subscribeSqs(queueArn, rawMessageDelivery = false)` -> publish with subject ->
     long-poll receive (12 x 5s waitTimeSeconds, ~60s budget) -> `SnsNotificationParser.parse`
     -> assert type/message/topicArn/subject.
  2. `subscribeSqs(queueArn, rawMessageDelivery = true)` -> publish -> received body equals
     the original string, no envelope.
  Best-effort cleanup (deleteTopic/deleteQueue in `finally` with runCatching); clients and
  container closed in `@AfterAll`. No faked assertions: if no message arrives the test fails
  with a clear error.

## Verification runs (from worktree root, JAVA_HOME=D:\jdks\jdk25)

| Command | Result |
| --- | --- |
| `./gradlew :sns:test` | BUILD SUCCESSFUL — 23 tests: SnsNotificationParserTest 11/11 pass, TopicOperationsSubscribeSqsTest 3/3 pass, SnsTemplateTest 7/7 pass (pre-existing, no regression), SnsSqsFanOutLocalStackTest 2 skipped, 0 failures/errors |
| `./gradlew :sns:lintKotlin` | BUILD SUCCESSFUL (lintKotlinMain + lintKotlinTest clean) |
| `./gradlew :starters:starter-sns:test` | BUILD SUCCESSFUL — SnsAutoConfigurationTest 7/7 pass, 0 skipped, no regression |

LocalStack skips: 2 (both `SnsSqsFanOutLocalStackTest` tests). Reason: `LOCALSTACK_AUTH_TOKEN`
is not set in this environment (first assumption triggers). Docker IS available on this
machine, so with a token set the fan-out tests will execute against `localstack/localstack:4`.

Note: an initial lint run failed only on `standard:final-newline` for the 7 newly created
files; fixed by appending final newlines, no other lint issues.

## Constraints honored

- No commits; working-tree changes only (7 new files + edits to TopicOperations.kt and sns/build.gradle.kts).
- examples/list/utilities/aws-build-tool untouched; no subagents dispatched.
- Jackson 3 `tools.jackson.*` only; AWS SDK Kotlin only (no Spring deps in sns module).
- No SNS pull listener, no NotificationStatus type.
- JUnit 6 tests return Unit (`runTest`/`runBlocking<Unit>` or plain blocks).

## Concerns / follow-ups

1. The brief's `attributes = mapOf(...)` shape does not exist on SNS `SetSubscriptionAttributesRequest`;
   implemented with `attributeName`/`attributeValue` as verified from the 1.9.45 jar. Reviewers
   should accept this deviation (single-attribute API is the actual SNS contract).
2. LocalStack fan-out path (both envelope and raw delivery) is implemented but was not executed
   here (no LOCALSTACK_AUTH_TOKEN). LocalStack 4 supports RawMessageDelivery; if the container
   proves unreliable, the tests fail loudly rather than fake assertions and remain skip-safe.
3. `subscribeSqs` does not manage the SQS queue policy (out of scope per brief); KDoc states the
   requirement and the integration test demonstrates the policy. Real-AWS users must attach it
   themselves (a future convenience could automate it).
4. Signature fields are parsed but not cryptographically verified — envelope signature validation
   is deliberately out of scope for this task.