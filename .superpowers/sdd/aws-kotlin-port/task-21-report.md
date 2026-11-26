# Task 21 report — examples consume awskotlin starters; official Testcontainers LocalStack

Worktree: `D:/hantsylabs/aws-sdk-kotlin-spring-example/.worktrees/awskotlin-spring-port`
Working-tree changes only; **no commits made**. No subagents dispatched.
All changes scoped to `examples/` — port modules (`core`, `s3`, `secrets-manager`, `sns`, `sqs`, `starters/*`) untouched
(`git diff --stat -- core s3 secrets-manager sns sqs starters` is empty).

## Status: DONE — compile/lint green, tests green (LocalStack tests token-gated, skipped without LOCALSTACK_AUTH_TOKEN)

## Files changed

Modified:
- `examples/build.gradle.kts`
- `examples/src/main/resources/application.properties`
- `examples/src/main/kotlin/com/example/demo/internal/S3StorageService.kt`
- `examples/src/main/kotlin/com/example/demo/internal/AwsSecretsManagementService.kt`
- `examples/src/main/kotlin/com/example/demo/internal/SqsMessagingService.kt`
- `examples/src/main/kotlin/com/example/demo/internal/AutomaticMessageReceiver.kt`
- `examples/src/test/kotlin/com/example/demo/StorageServiceTest.kt`
- `examples/src/test/kotlin/com/example/demo/AwsSecretsManagementServiceTest.kt`
- `examples/src/test/kotlin/com/example/demo/MessagingServiceTest.kt`

Deleted:
- `examples/src/main/kotlin/com/example/demo/internal/AwsConfig.kt`
- `examples/src/main/kotlin/com/example/demo/internal/AwsProperties.kt`
- `examples/src/main/kotlin/com/example/demo/internal/S3ClientExtension.kt`
- `examples/src/test/kotlin/com/example/demo/LocalstackTestContainer.kt`

Untouched (per brief): `Extensions.kt`, `DemoApplication.kt`, `TestDemoApplication.kt`, the public interfaces
`StorageService` / `MessagingService` / `SecretsManagementService` (and their exception types), `test.png`.

## Design notes

### Build (`examples/build.gradle.kts`)
- Added `alias(libs.plugins.kotlinPluginSerialization)`.
- Replaced `implementation(libs.bundles.awsSdkKotlin)` with
  `implementation(project(":starters:s3-starter"))`,
  `implementation(project(":starters:secrets-manager-starter"))`,
  `implementation(project(":starters:sqs-starter"))`.
- Added `implementation(libs.kotlinxSerializationJson)` (kotlinx converter runtime + `@Serializable` test payload).
- Kept Spring/WebFlux (`bundles.core`), `bundles.kotlinSupport` (jacksonKotlin still used by WebFlux HTTP),
  okhttp, and all test bundles. No SNS starter (no SNS use case in examples). No catalog changes needed.
- The starters expose the port modules and AWS SDK via `api`, so `S3Template`, `S3Client`, `ByteStream`, etc.
  are on the examples compile classpath transitively. No ktlint compiler-pin workaround was needed in examples
  (lint passes as-is, unlike the port modules).

### Configuration
- `application.properties` now exactly:
  `awskotlin.access-key-id=test-access-key`, `awskotlin.secret-access-key=test-secret-key`,
  `awskotlin.region=us-east-1`, `example.s3.bucket-name=uploaded-files`.
  No `awskotlin.endpoint` in main properties; tests register it dynamically.
- All hand-rolled `aws.*` configuration (`AwsConfig`, `AwsProperties`, `S3ClientExtension` + `S3ClientException`)
  deleted. Credentials/clients/templates now come from `AwsKotlinAutoConfiguration`, `S3AutoConfiguration`,
  `SecretsManagerAutoConfiguration`, `SqsAutoConfiguration`.

### S3StorageService
- Injects `S3Template`, starter-provided `S3Client`, and
  `@Value("\${example.s3.bucket-name:uploaded-files}") bucketName`.
- `store`: `template.bucket(bucketName).exists()`; if false, `client.createBucket { bucket = bucketName }`
  (raw client only for bucket creation, per ruling); then
  ``template.bucket(bucketName).`object`(resourceKey).put(ByteStream.fromBytes(data.toByteArray()), contentType = ...)``
  with the preserved `MediaTypeFactory.getMediaType(resourceKey).orElseGet { MediaType.APPLICATION_OCTET_STREAM }.toString()`
  lookup. (`object` is a Kotlin hard keyword, so call sites use backticks — same as the port module's own tests.)
- `retrieve`: ``...`object`(resourceKey).get()?.toFluxDataBuffer() ?: Flux.empty()``.
- `delete`: ``...`object`(resourceKey).delete()``.
- All SDK failures wrapped in `StorageServiceException(e.message ?: fallback)`; the SDK message is preserved,
  so the missing-key assertion (`shouldStartWith "The specified key does not exist"`) still holds — the template
  propagates the SDK `NoSuchKey` unchanged.

### AwsSecretsManagementService
- Injects `SecretsManagerTemplate` + starter-provided `SecretsManagerClient`.
- `read`: `template.secret(secretName).get().secretString`, failures wrapped in `SecretsManagementServiceException`.
- `write`: kept the raw-client `CreateSecretRequest` with `description = secretDescription` and
  `forceOverwriteReplicaSecret = true` (template does not expose those fields, per brief); returns `response.arn`;
  failures wrapped. Logging preserved.

### SqsMessagingService
- Injects `SqsTemplate`, starter-provided `SqsClient`, `SqsMessageConverter`. Jackson `ObjectMapper` removed.
- Kept `companion object { const val DEFAULT_SQS_QUEUE_NAME = "default" }` and
  `@PostConstruct fun init() = runBlocking { createQueue(DEFAULT_SQS_QUEUE_NAME) }`.
- `createQueue` / `getQueue`: unchanged raw `SqsClient` behavior (template has no queue creation).
- `send`: `template.destination(queueNameVal).send(message, delay = 1.seconds)`; failures wrapped in
  `MessagingServiceException`. Removed the now-unused `DELAY_IN_SECONDS` constant.
- `receive`: kept batch `SqsClient.receiveMessage` with `maxNumberOfMessages = 5`; conversion is now
  `runCatching { converter.fromBody(message.body ?: "", clazz) }.getOrNull()`. No delete/ack in this method
  (example semantics preserved). Two minimal adaptations vs the brief's literal snippet, both compile/semantics
  necessities: SDK `Message.body` is `String?` while `fromBody` takes non-null `String`, hence `?: ""`
  (a null/empty body fails conversion and is filtered out, exactly like the old Jackson NPE path);
  `clazz: Class<out T>` passes to `fromBody(body, clazz)` as-is (compiles cleanly).

### AutomaticMessageReceiver
- `@Scheduled` polling and the `MessagingService` dependency removed.
- Now `@SqsListener(SqsMessagingService.DEFAULT_SQS_QUEUE_NAME)` on
  `suspend fun onMessageReceived(message: String)` logging the payload; the starter's
  `SqsListenerAnnotationRegistrar` handles polling and auto-delete on successful return.

### Tests — official Testcontainers LocalStack lifecycle
- Deleted the custom `LocalstackTestContainer` wrapper (manual start/shutdown-hook/health polling).
- Each of the three test classes now has, verbatim per brief:
  - `@Testcontainers` and `@EnabledIfEnvironmentVariable(named = "LOCALSTACK_AUTH_TOKEN", matches = ".+")` on the class;
  - companion `@JvmField @Container val localstack: LocalStackContainer =
    LocalStackContainer(DockerImageName.parse("localstack/localstack:4")).withServices("s3", "sqs", "secretsmanager")
    .apply { System.getenv("LOCALSTACK_AUTH_TOKEN")?.takeIf { it.isNotBlank() }?.let { withEnv("LOCALSTACK_AUTH_TOKEN", it) } }`;
  - `@JvmStatic @DynamicPropertySource` registering `awskotlin.endpoint` from `localstack.endpoint.toString()`.
  Container start/stop/wait is entirely the official container wait strategy + JUnit Jupiter lifecycle.
- Auto-configuration imports:
  - `StorageServiceTest`: `@Import(S3StorageService::class)` + `@ImportAutoConfiguration(AwsKotlinAutoConfiguration::class, S3AutoConfiguration::class)`.
  - `AwsSecretsManagementServiceTest`: `@Import(AwsSecretsManagementService::class)` + `@ImportAutoConfiguration(AwsKotlinAutoConfiguration::class, SecretsManagerAutoConfiguration::class)`.
  - `MessagingServiceTest`: `@Import(SqsMessagingService::class)` + `@ImportAutoConfiguration(AwsKotlinAutoConfiguration::class, SqsAutoConfiguration::class)`;
    `JacksonAutoConfiguration` import/auto-config removed; `@Serializable` added to `data class MyMessage(val text: String)`.
  - All `AwsConfig`/`AwsProperties` imports and `@EnableConfigurationProperties(AwsProperties::class)` removed.
- All existing assertions and `eventually` blocks kept unchanged (including the
  `The specified key does not exist` message assertion). Removed one pre-existing unused import
  (`Duration.Companion.milliseconds` in `StorageServiceTest`). `TestDemoApplication` untouched.

## Verification (exact commands, run from the worktree root)

1. `./gradlew :examples:compileKotlin :examples:compileTestKotlin :examples:lintKotlin`
   - First run: compile OK; lint failed with `standard:final-newline` on the 7 rewritten files (missing trailing
     newline). Fixed by appending a final newline; no other lint findings.
   - Re-run: **BUILD SUCCESSFUL in 34s** — `:examples:compileKotlin`, `:examples:compileTestKotlin`,
     `:examples:lintKotlinMain`, `:examples:lintKotlinTest`, `:examples:lintKotlin` all pass.
2. `./gradlew :examples:test`
   - **BUILD SUCCESSFUL in 13s** — `SUCCESS: Executed 4 tests in 4.1s (4 skipped)`.
   - Environment: Docker available; `LOCALSTACK_AUTH_TOKEN` **not set**, so all three LocalStack test classes are
     disabled by `@EnabledIfEnvironmentVariable` and skip cleanly (test XML shows `<skipped/>`, 0 failures/errors,
     ~0.001s per test — no container start attempted). No assertions were disabled or deleted.
3. `./gradlew test` (full suite; port modules untouched)
   - **BUILD SUCCESSFUL in 32s** — all module tests pass; examples contributes the same 4 token-gated skips.

## Concerns

- The LocalStack integration tests could not be executed end-to-end in this environment because
  `LOCALSTACK_AUTH_TOKEN` is not set (LocalStack 4 license requirement). They skip cleanly by design, matching the
  port modules' gating. Compile/lint and the full suite are green; the token-gated path is the only untested
  runtime behavior. With a token set, the tests start the official container via `@Testcontainers`/`@Container`
  and register `awskotlin.endpoint` dynamically.
- Minor, semantics-preserving deviations from the brief's literal snippets (documented above):
  `message.body ?: ""` for the nullable SDK body, and backticked `` `object`(key) `` call sites (Kotlin keyword).
- No port-module changes were required; no compile blockers discovered.