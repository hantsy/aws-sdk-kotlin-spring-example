# Task 5 — SQS fluent template core

## Context
Worktree: D:/hantsylabs/aws-sdk-kotlin-spring-example/.worktrees/awskotlin-spring-port
Branch: awskotlin-spring-port. No commits authorized. Do not modify examples/list/utilities/aws-build-tool. Do not dispatch subagents.
This task creates the pure Kotlin `sqs` module only. Starter auto-configuration is Task 6; listener runtime is Task 7.

## Global constraints
- AWS SDK for Kotlin 1.9.45; Spring Boot 4.2.0 BOM; Kotlin 2.4.10; Java 25.
- Package `io.github.hantsy.awskotlin.sqs`; artifact ID `awskotlin-spring-sqs`.
- Use AWS SDK Kotlin APIs, not Java SDK. Authoritative local SDK source: D:/hantsylabs/aws-sdk-kotlin; generated clients verified from published jars.
- Standard queues only; FIFO fails fast; no implicit queue creation.
- Auto acknowledgement by default; manual opt-in. Reject sets visibility timeout 0. Errors/no decision leave message undeleted.
- SDK default chains are core/starter concern; this module accepts an injected SqsClient.

## Required build setup
Create `sqs/build.gradle.kts` and `sqs/gradle.properties`. Add `include("sqs")` to settings.gradle.kts.
Replicate core module build workarounds exactly:
- plugins: kotlinJvm, dependencyManagement, kotlinter, versionless `id("org.jetbrains.kotlin.kapt")` only if configuration processor needed; for pure Kotlin module kapt is not required unless you add metadata. Prefer no kapt in sqs module.
- `java-library`; group io.github.hantsy; version 0.1.0-SNAPSHOT; archivesName awskotlin-spring-sqs.
- dependencyManagement imports Spring Boot BOM using libs.versions.springboot.
- dependencies: api(libs.awsSqs), implementation(libs.kotlinStdlib), implementation(libs.kotlinCoroutinesCore), implementation(libs.jacksonDatabind), implementation(libs.jacksonKotlin). Tests: springBootStarterTest, bundles.kotest, bundles.mockk, bundles.kotlintest, kotlinCoroutinesTest, junitPlatformLauncher.
- kotlin jvmToolchain 25; tasks.withType<Test> useJUnitPlatform().
- ktlint classpath compiler pin resolution rule from core/build.gradle.kts (useVersion 2.2.21 for kotlin-compiler-embeddable, kotlin-daemon-embeddable, kotlin-script-runtime, kotlin-build-tools-api).
- gradle.properties: kotlin.compiler.runViaBuildToolsApi=false with same workaround comment as core.
- End every new text file with a trailing newline.

## Public API (use these names/signatures)
```kotlin
data class SqsMessage<T>(
    val payload: T,
    val messageId: String,
    val receiptHandle: String,
    val body: String,
    val attributes: Map<String, String>,
    val messageAttributes: Map<String, MessageAttributeValue>,
)

interface SqsMessageConverter {
    fun toBody(payload: Any): String
    fun <T : Any> fromBody(body: String, targetType: Class<T>): T
}

class JacksonSqsMessageConverter : SqsMessageConverter

enum class SqsAcknowledgementMode { AUTO, MANUAL }

class SqsQueueResolver(private val client: SqsClient) {
    suspend fun resolve(queueNameOrUrl: String): String
}

class SqsTemplate(
    private val client: SqsClient,
    private val converter: SqsMessageConverter = JacksonSqsMessageConverter(),
    private val queueResolver: SqsQueueResolver = SqsQueueResolver(client),
) {
    fun destination(queueNameOrUrl: String): SqsDestinationOperations
}

interface SqsDestinationOperations {
    suspend fun send(payload: Any, delay: Duration? = null): SendMessageResponse
    suspend fun <T : Any> receive(
        targetType: Class<T>,
        timeout: Duration = 10.seconds,
        acknowledgement: SqsAcknowledgementMode = SqsAcknowledgementMode.AUTO,
    ): SqsMessage<T>?
    suspend fun acknowledge(message: SqsMessage<*>): DeleteMessageResponse
    suspend fun reject(message: SqsMessage<*>): ChangeMessageVisibilityResponse
}
```
Add Kotlin reified convenience overloads if useful: `receive<T>(timeout, acknowledgement)` and `send` remains Any. Do not add Spring annotations in this module.

## Error types
Ruling: base error is `SqsTemplateException`, NOT `SqsException`, to avoid collision with SDK `aws.sdk.kotlin.services.sqs.model.SqsException`.
```kotlin
open class SqsTemplateException(message: String, cause: Throwable? = null) : RuntimeException(message, cause)
class SqsQueueNotFoundException(queueNameOrUrl: String, cause: Throwable? = null) : SqsTemplateException(...)
class SqsMessageConversionException(message: String, cause: Throwable? = null) : SqsTemplateException(...)
class SqsOperationException(message: String, cause: Throwable? = null) : SqsTemplateException(...)
```

## Verified SDK facts (1.9.45 jar)
- Client: `aws.sdk.kotlin.services.sqs.SqsClient`.
- Not-found modeled error: `aws.sdk.kotlin.services.sqs.model.QueueDoesNotExist` (NO Exception suffix).
- Receipt handle error: `ReceiptHandleIsInvalid`; visibility error: `MessageNotInflight`; base SDK error: `SqsException`.
- `receiveMessage` request supports queueUrl, maxNumberOfMessages, waitTimeSeconds Int, messageAttributeNames, attributeNames. Use maxNumberOfMessages=1. Request all message attributes with `messageAttributeNames = listOf("All")`; request system attributes with `attributeNames = listOf(MessageSystemAttributeName.All)` if that enum value exists in jar; verify before use.
- `deleteMessage` requires queueUrl and receiptHandle.
- `changeMessageVisibility` requires queueUrl, receiptHandle, visibilityTimeout Int seconds.
- `sendMessage` supports queueUrl, messageBody, delaySeconds Int?; FIFO-only fields must not be exposed.

## Behavior requirements
1. Queue resolver:
   - If input starts with `https://` or `http://`, treat as URL and return unchanged.
   - Otherwise call getQueueUrl and cache successful name->URL mappings.
   - Coroutine-safe cache: use Mutex + mutableMap, not ConcurrentHashMap.computeIfAbsent with runBlocking.
   - Reject FIFO names/URLs ending `.fifo` with SqsOperationException before SDK call.
   - Map QueueDoesNotExist to SqsQueueNotFoundException; preserve cause.
2. Jackson converter:
   - Use Jackson 3 (`tools.jackson.databind`), not com.fasterxml.jackson.
   - Register Kotlin module (`tools.jackson.module.kotlin.KotlinModule`) verified from jar.
   - String payload: toBody returns the string unchanged; fromBody(String::class.java) returns body unchanged.
   - Other payloads serialize/deserialize JSON. Conversion failures wrap in SqsMessageConversionException.
3. send:
   - Resolve queue, convert payload, call sendMessage. delay?.inWholeSeconds?.toInt() validated 0..900; invalid delay throws SqsOperationException.
   - Do not delete or receive implicitly.
4. receive:
   - Resolve queue; clamp timeout to 0..20 seconds for waitTimeSeconds; negative timeout throws SqsOperationException.
   - Empty response returns null.
   - Convert body to targetType. Conversion failure must NOT delete message; throw SqsMessageConversionException.
   - AUTO: delete message after successful conversion and return SqsMessage. Delete failure wraps SqsOperationException; message may remain visible.
   - MANUAL: return SqsMessage without deleting.
   - Populate messageId/receiptHandle/body/attributes/messageAttributes from SDK message; missing nullable SDK fields become empty string/map as appropriate.
5. acknowledge/reject:
   - Use message.receiptHandle and resolved queue URL. reject visibilityTimeout=0.
   - Map ReceiptHandleIsInvalid/MessageNotInflight/QueueDoesNotExist to typed errors with cause.
6. No implicit queue creation, no FIFO support, no batch receive in this task.

## Tests required
Unit tests with MockK and runTest/runBlocking<Unit>. JUnit 6 silently skips value-returning @Test methods; all test methods must return Unit.
Cover at least:
- resolver URL passthrough, name resolution, cache hit (getQueueUrl called once), QueueDoesNotExist mapping, FIFO rejection.
- converter String passthrough and data class JSON roundtrip; conversion failure typed error.
- send payload serialization and delay validation.
- receive empty -> null; AUTO deletes; MANUAL does not delete; conversion failure does not delete; timeout clamping; negative timeout error.
- acknowledge delete call; reject visibility 0; SDK error mapping.
Use `relaxed = true` only where necessary; verify exact SDK request fields where practical.

## Verification commands
Run from worktree root:
- `./gradlew :sqs:test`
- `./gradlew :sqs:lintKotlin` (or formatKotlin then lintKotlin if needed)
Report exact output summary. Do not run full examples tests.

## Report contract
Write full report to `.superpowers/sdd/aws-kotlin-port/task-5-report.md`: files changed, API decisions, SDK/Jackson verification evidence, tests run and output, self-review findings, concerns.
Return only: status (DONE/DONE_WITH_CONCERNS/NEEDS_CONTEXT/BLOCKED), one-line test summary, concerns.