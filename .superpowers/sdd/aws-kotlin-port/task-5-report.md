# Task 5 Report — SQS fluent template core

Status: DONE
Branch: awskotlin-spring-port (worktree). No commits made; all changes left in working tree.

## Files changed

New module `sqs/` (all untracked, new):
- `sqs/build.gradle.kts` — mirrors sns/core build: kotlinJvm + dependencyManagement + kotlinter + `java-library`, no kapt; group `io.github.hantsy`, version `0.1.0-SNAPSHOT`, archivesName `awskotlin-spring-sqs`; Spring Boot BOM via `libs.versions.springboot` (4.2.0); deps `api(libs.awsSqs)`, `implementation` kotlinStdlib/kotlinCoroutinesCore/jacksonDatabind/jacksonKotlin; tests springBootStarterTest, bundles.kotest/mockk/kotlintest, kotlinCoroutinesTest, junitPlatformLauncher; jvmToolchain 25; ktlint compiler-pin resolution rule (useVersion 2.2.21) copied verbatim from core; `useJUnitPlatform()`.
- `sqs/gradle.properties` — `kotlin.compiler.runViaBuildToolsApi=false` with the same workaround comment as core.
- `sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/SqsTemplateException.kt` — `SqsTemplateException` (open, RuntimeException), `SqsQueueNotFoundException(queueNameOrUrl, cause)`, `SqsMessageConversionException(message, cause)`, `SqsOperationException(message, cause)`.
- `.../SqsMessage.kt` — data class exactly per brief (payload, messageId, receiptHandle, body, attributes: Map<String,String>, messageAttributes: Map<String, MessageAttributeValue>).
- `.../SqsMessageConverter.kt` — interface `toBody(Any): String`, `fromBody(String, Class<T>): T`.
- `.../JacksonSqsMessageConverter.kt` — Jackson 3 (`tools.jackson.databind.json.JsonMapper` + `tools.jackson.module.kotlin.KotlinModule.Builder().build()`); String passthrough both directions; failures wrapped in `SqsMessageConversionException` preserving the `tools.jackson.core.JacksonException` cause; null deserialization result also raises `SqsMessageConversionException`.
- `.../SqsAcknowledgementMode.kt` — enum AUTO, MANUAL.
- `.../SqsQueueResolver.kt` — URL passthrough (`https://`/`http://`), FIFO `.fifo` rejection before any SDK call (checked before URL passthrough so FIFO URLs are rejected too), `getQueueUrl` for names, Mutex + mutableMap cache (`withLock { cache.getOrPut(...) }`), `QueueDoesNotExist` → `SqsQueueNotFoundException` with cause; null `queueUrl` in response → `SqsQueueNotFoundException`.
- `.../SqsTemplate.kt` — constructor `(client, converter = JacksonSqsMessageConverter(), queueResolver = SqsQueueResolver(client))`; `destination(queueNameOrUrl)` returns `SqsDestinationOperations`, makes no request. No Spring annotations anywhere in the module.
- `.../SqsDestinationOperations.kt` — interface with exact brief signatures (send/receive/acknowledge/reject, defaults `timeout = 10.seconds`, `acknowledgement = AUTO`) plus reified `suspend inline fun <reified T : Any> SqsDestinationOperations.receive(timeout, acknowledgement)` extension.
- `.../DefaultSqsDestinationOperations.kt` — internal implementation (see behavior below).

Modified:
- `settings.gradle.kts` — added `include("sqs")` after `include("sns")` (one line; other includes were pre-existing from Tasks 1–4).

Not touched: examples/, list/, utilities/, aws-build-tool/, core/, s3/, secrets-manager/, sns/, starters/, gradle/libs.versions.toml (catalog already had `awsSqs`, `jacksonDatabind`, `jacksonKotlin`).

## Behavior implementation

- send: delay validated first (`delay?.inWholeSeconds` checked against 0..900 as Long before `toInt()`, avoiding Int overflow on huge durations); invalid delay throws `SqsOperationException` before any SDK call. Then resolve queue, convert payload, `sendMessage` with queueUrl/messageBody/delaySeconds only. No FIFO-only fields exposed.
- receive: negative timeout throws `SqsOperationException` before any SDK call; `waitTimeSeconds = timeout.inWholeSeconds.coerceIn(0, 20).toInt()`; request uses `maxNumberOfMessages = 1`, `messageAttributeNames = listOf("All")`, `messageSystemAttributeNames = listOf(MessageSystemAttributeName.All)` (see SDK verification note below). Empty/null messages → null. Body converted before any delete; conversion failure propagates `SqsMessageConversionException` with the message left in the queue. AUTO deletes after successful conversion (delete failure → `SqsOperationException` with cause); MANUAL never deletes. Nullable SDK fields default to ""/emptyMap; system attribute keys mapped via `MessageSystemAttributeName.value`.
- acknowledge: `deleteMessage` with resolved queueUrl + message.receiptHandle.
- reject: `changeMessageVisibility` with `visibilityTimeout = 0`.
- Error mapping (shared inline helper): `QueueDoesNotExist` → `SqsQueueNotFoundException`; `ReceiptHandleIsInvalid` and `MessageNotInflight` → `SqsOperationException` with specific messages; any other SDK `SqsException` → `SqsOperationException`. Cause always preserved. Applied uniformly to send/receive/acknowledge/reject (brief mandated it for acknowledge/reject; extending it to send/receive is a consistency decision, documented here).
- No implicit queue creation, no FIFO support, no batch receive.

## SDK / Jackson verification evidence (javap/unzip on published jars)

Jar: `C:/Users/hantsy/.gradle/caches/modules-2/files-2.1/aws.sdk.kotlin/sqs-jvm/1.9.45/.../sqs-jvm-1.9.45.jar`
- `aws.sdk.kotlin.services.sqs.SqsClient` exposes suspend getQueueUrl/sendMessage/receiveMessage/deleteMessage/changeMessageVisibility; DSL extensions confirmed in `SqsClientKt` (Function1<Builder, Unit> overloads).
- Modeled errors confirmed with NO Exception suffix: `QueueDoesNotExist`, `ReceiptHandleIsInvalid`, `MessageNotInflight`, all extending `aws.sdk.kotlin.services.sqs.model.SqsException` (hence base template error named `SqsTemplateException`).
- `Message` getters: attributes is `Map<MessageSystemAttributeName, String>` (mapped to `Map<String,String>` via `.value`); messageAttributes is `Map<String, MessageAttributeValue>`; messageId/receiptHandle/body nullable Strings.
- `ReceiveMessageRequest.Builder`: `setAttributeNames(List<? extends QueueAttributeName>)` (legacy) vs `setMessageSystemAttributeNames(List<? extends MessageSystemAttributeName>)`. **Deviation from brief's conditional instruction:** `attributeNames` cannot take `MessageSystemAttributeName.All` in 1.9.45 (it is typed for `QueueAttributeName`), so system attributes are requested via `messageSystemAttributeNames = listOf(MessageSystemAttributeName.All)`. `MessageSystemAttributeName$All` confirmed as an object (INSTANCE) in the jar. The brief said "verify before use"; verification drove this choice.
- `SendMessageRequest.Builder` has queueUrl/messageBody/delaySeconds(Integer); `ChangeMessageVisibilityRequest.Builder` has queueUrl/receiptHandle/visibilityTimeout(Integer); `DeleteMessageRequest.Builder` has queueUrl/receiptHandle; `GetQueueUrlResponse.getQueueUrl()` nullable.
- `MessageNotInflight$Builder` has NO `message` setter (empty error shape); test constructs it with `MessageNotInflight { }`. `QueueDoesNotExist`/`ReceiptHandleIsInvalid` builders do have `message`.

Jackson (Boot 4.2.0 BOM manages `jackson-bom.version = 3.1.8`, verified in spring-boot-dependencies-4.2.0.pom):
- `tools.jackson.module.kotlin.KotlinModule$Builder` confirmed in jackson-module-kotlin-3.1.8.jar (public no-arg ctor, `build()`).
- `tools.jackson.core.JacksonException extends RuntimeException` confirmed in jackson-core jar; used as the catch type for conversion failures.
- Behavioral finding: Jackson 3 serializes an empty bean (`Any()`) as `{}` without error (FAIL_ON_EMPTY_BEANS no longer fails by default), so the serialization-failure test uses a self-referencing list, which reliably raises a JacksonException.

## Tests run and output

All test methods use `= runTest { ... }` (JVM `TestResult` is a typealias for `Unit`, so compiled methods return void — same pattern as the approved SNS tests; no value-returning @Test methods). MockK strict mocks; `relaxed = true` not used anywhere. Exact SDK request fields captured and asserted via slots.

- `./gradlew :sqs:test` → BUILD SUCCESSFUL. JUnit XML totals: SqsQueueResolverTest tests=8, JacksonSqsMessageConverterTest tests=6, SqsTemplateTest tests=22 → **36 tests, 0 failures, 0 errors, 0 skipped**.
- `./gradlew :sqs:lintKotlin` → initially failed (final-newline, import-ordering, wrapping rules); ran `./gradlew :sqs:formatKotlin` (allowed by brief) then `./gradlew :sqs:lintKotlin` → BUILD SUCCESSFUL (lintKotlinMain + lintKotlinTest).
- Final combined `./gradlew :sqs:test :sqs:lintKotlin` → BUILD SUCCESSFUL in 15s.
- No Gradle daemon issues encountered; configuration cache reused normally. No global config changed.

Coverage vs brief checklist: resolver URL passthrough (https + http), name resolution, cache hit (coVerify exactly 1 getQueueUrl), QueueDoesNotExist mapping with cause, FIFO name and URL rejection with confirmVerified (no SDK call), null queueUrl response; converter String passthrough both directions, data class JSON roundtrip, serialization/deserialization failure typed errors with JacksonException cause, null-body error; send JSON serialization + queueName capture, String passthrough, URL destination without resolution, delaySeconds set, negative/901s delay rejection without SDK call, QueueDoesNotExist mapping; receive empty→null + full request field assertions (maxNumberOfMessages=1, waitTimeSeconds=10 default, messageAttributeNames=["All"], messageSystemAttributeNames=[All]), AUTO converts+deletes with full SqsMessage field equality, MANUAL no delete, conversion failure no delete, 60s→20 clamping, negative timeout error without SDK call, AUTO delete failure wrapped with cause, reified receive, QueueDoesNotExist mapping; acknowledge delete request fields, reject visibilityTimeout=0, ReceiptHandleIsInvalid/MessageNotInflight/QueueDoesNotExist mapping with cause; destination() makes no request.

## Self-review findings

- Two compile/test iterations were needed: (1) `MessageNotInflight { message = ... }` does not compile (no message member) → empty builder; (2) `toBody(Any())` did not throw under Jackson 3 → cyclic-list payload. Both fixed and re-verified.
- The Write tool stripped trailing newlines on new files; formatKotlin restored them on .kt files and explicit appends fixed `sqs/build.gradle.kts` / `sqs/gradle.properties`. Verified every new text file ends with `\n` (od check).
- Formatter changes were cosmetic only (import order with kotlin imports last per intellij_idea style, line wrapping); tests re-run green after formatting.
- Resolver holds the Mutex across the `getQueueUrl` call (`withLock { cache.getOrPut }`). This guarantees at most one lookup per name even under concurrency, at the cost of serializing concurrent resolutions of different names. Acceptable for a template; per-key locking could be revisited if contention matters (Task 7 listener will mostly pass URLs, which bypass the lock entirely).
- `git status` confirms scope: only `settings.gradle.kts` modified (one added line) plus new untracked `sqs/`. examples/list/utilities/aws-build-tool untouched. Nothing committed.

## Concerns

- `messageSystemAttributeNames` used instead of the brief's suggested `attributeNames` (legacy field is typed `List<QueueAttributeName>` in 1.9.45); this is the verified-correct modern field and matches the brief's "verify before use" instruction. Task 7 should assert on this field name.
- SDK error mapping was extended to send/receive (not just acknowledge/reject) for consistency; callers see typed template errors, never raw SDK exceptions, from destination operations. If a later task wants raw propagation for specific operations, this is the place to adjust.
- Jackson 3's relaxed empty-bean behavior means `toBody` accepts payloads Jackson can represent as `{}`; only genuine Jackson failures are wrapped. No action needed.
