# Task 21 — Update examples to use the awskotlin starters

## Goal
Refactor the `examples` module so it consumes the new `awskotlin-*-spring-boot-starter` modules and their auto-configuration/templates instead of the hand-rolled `aws.*` SDK configuration. Keep the public example interfaces (`StorageService`, `MessagingService`, `SecretsManagementService`) and the existing LocalStack test assertions working.

## Workspace / constraints
- Work only in `D:/hantsylabs/aws-sdk-kotlin-spring-example/.worktrees/awskotlin-spring-port`.
- Do NOT create git commits. Leave changes in the working tree.
- Do NOT dispatch subagents.
- AWS SDK for Kotlin API authority: `D:/hantsylabs/aws-sdk-kotlin` (and published jars if needed). Do not infer APIs from Java SDK sources.
- Port modules/starters are already implemented and reviewed. Do not change port module behavior unless a compile blocker is discovered; if so, report it rather than silently redesigning.
- Keep ktlint clean. Examples uses Kotlin 2.4.10 / Spring Boot 4.2.0 / Java 25.

## Starter APIs to use (verified)
Core:
- `io.github.hantsy.awskotlin.core.autoconfigure.AwsKotlinAutoConfiguration` provides a `CredentialsProvider` bean.
- `io.github.hantsy.awskotlin.core.AwsKotlinProperties` prefix `awskotlin`: `enabled`, `region`, `endpoint`, `accessKeyId`, `secretAccessKey`, `profileName`.

S3 starter:
- `io.github.hantsy.awskotlin.s3.autoconfigure.S3AutoConfiguration` provides `S3Client` and `S3Template` beans.
- `io.github.hantsy.awskotlin.s3.autoconfigure.S3Properties` prefix `awskotlin.s3`: `enabled`, `endpoint`, `region`, `pathStyleAccessEnabled`.
- `S3Template.bucket(bucket: String): S3BucketOperations`
- `S3BucketOperations.exists(): Boolean` (only SDK `NotFound` maps to false), `.object(key): S3ObjectOperations`
- `S3ObjectOperations.put(body: ByteStream, contentType: String? = null): PutObjectResponse`, `.get(): ByteArray?`, `.delete(): DeleteObjectResponse`
- S3 auto-config sets `forcePathStyle = pathStyleAccessEnabled ?: (endpoint != null)`.

Secrets Manager starter:
- `io.github.hantsy.awskotlin.secretsmanager.autoconfigure.SecretsManagerAutoConfiguration` provides `SecretsManagerClient` and `SecretsManagerTemplate`.
- `SecretsManagerTemplate.secret(secretId): SecretOperations`
- `SecretOperations.get(): GetSecretValueResponse`, `.create(secretValue)`, `.update(secretValue)`, `.delete(forceDeleteWithoutRecovery=false)`.
- The template does NOT expose `description` or `forceOverwriteReplicaSecret`.

SQS starter:
- `io.github.hantsy.awskotlin.sqs.autoconfigure.SqsAutoConfiguration` provides `SqsClient`, `SqsMessageConverter` (`KotlinxSqsMessageConverter`), `SqsQueueResolver`, `SqsTemplate`, and `SqsListenerAnnotationRegistrar`.
- `SqsTemplate.destination(queueNameOrUrl): SqsDestinationOperations`
- `SqsDestinationOperations.send(payload: Any, delay: Duration? = null): SendMessageResponse`
- `SqsDestinationOperations.receive(targetType: Class<T>, timeout: Duration = 10.seconds, acknowledgement: SqsAcknowledgementMode = AUTO): SqsMessage<T>?`
- `SqsMessageConverter.toBody(payload: Any): String`, `.fromBody(body: String, targetType: Class<T>): T`
- `io.github.hantsy.awskotlin.sqs.listener.SqsListener(vararg queues: String)`; listener method must have exactly one payload parameter (`T` or `SqsMessage<T>`), optional `Acknowledge`; suspend supported. Without `Acknowledge`, successful return auto-deletes.
- SQS template has NO queue creation and NO batch receive. Use the starter-provided `SqsClient` for those example operations.

## Required changes

### 1. examples/build.gradle.kts
- Add plugin `alias(libs.plugins.kotlinPluginSerialization)`.
- Replace `implementation(libs.bundles.awsSdkKotlin)` with:
  - `implementation(project(":starters:s3-starter"))`
  - `implementation(project(":starters:secrets-manager-starter"))`
  - `implementation(project(":starters:sqs-starter"))`
- Add `implementation(libs.kotlinxSerializationJson)` (needed for kotlinx converter runtime and `@Serializable` test payloads).
- Keep existing Spring/WebFlux, kotlinSupport, okhttp, and test bundles. Do not add SNS starter; examples have no SNS use case.

### 2. Remove hand-rolled AWS config
- Delete `examples/src/main/kotlin/com/example/demo/internal/AwsConfig.kt`.
- Delete `examples/src/main/kotlin/com/example/demo/internal/AwsProperties.kt`.
- Delete `examples/src/main/kotlin/com/example/demo/internal/S3ClientExtension.kt` (raw SDK helpers replaced by `S3Template`; bucket creation moves into the storage service).
- Keep `examples/src/main/kotlin/com/example/demo/Extensions.kt` (Flux/DataBuffer helpers).

### 3. application.properties
Replace `aws.*` with:
```properties
awskotlin.access-key-id=test-access-key
awskotlin.secret-access-key=test-secret-key
awskotlin.region=us-east-1
example.s3.bucket-name=uploaded-files
```
Do not set `awskotlin.endpoint` in main properties; tests supply it dynamically.

### 4. S3StorageService
Rewrite `internal/S3StorageService.kt` to implement `StorageService` using `S3Template` plus starter-provided `S3Client` only for bucket creation:
- Constructor injects `S3Template`, `S3Client`, and bucket name via `@Value("\${example.s3.bucket-name:uploaded-files}")`.
- `store`: ensure bucket exists (`template.bucket(bucketName).exists()`; if false, `client.createBucket { bucket = bucketName }`), then `template.bucket(bucketName).object(resourceKey).put(ByteStream.fromBytes(data.toByteArray()), contentType = ...)`. Preserve the old content-type lookup: `MediaTypeFactory.getMediaType(resourceKey).orElseGet { MediaType.APPLICATION_OCTET_STREAM }.toString()`.
- `retrieve`: `template.bucket(bucketName).object(resourceKey).get()?.toFluxDataBuffer() ?: Flux.empty()`.
- `delete`: `template.bucket(bucketName).object(resourceKey).delete()`.
- Wrap SDK failures in `StorageServiceException(e.message ?: fallback)`. IMPORTANT: preserve the SDK message for missing keys; `StorageServiceTest` asserts the exception message starts with `The specified key does not exist`.

### 5. AwsSecretsManagementService
Rewrite `internal/AwsSecretsManagementService.kt`:
- Inject `SecretsManagerTemplate` and starter-provided `SecretsManagerClient`.
- `read`: use `template.secret(secretName).get().secretString`; wrap failures in `SecretsManagementServiceException`.
- `write`: keep the existing raw-client `createSecret` request with `description = secretDescription` and `forceOverwriteReplicaSecret = true`, because the template does not support those fields. Return `response.arn`; wrap failures in `SecretsManagementServiceException`.

### 6. SqsMessagingService
Rewrite `internal/SqsMessagingService.kt`:
- Inject `SqsTemplate`, starter-provided `SqsClient`, and `SqsMessageConverter`.
- Keep `companion object { const val DEFAULT_SQS_QUEUE_NAME = "default" }` (used by the listener).
- Keep `@PostConstruct fun init() = runBlocking { createQueue(DEFAULT_SQS_QUEUE_NAME) }`.
- `createQueue` / `getQueue`: keep current `SqsClient` behavior (template has no queue creation).
- `send`: use `template.destination(queueNameVal).send(message, delay = 1.seconds)`; wrap failures in `MessagingServiceException`.
- `receive`: keep batch behavior using `SqsClient.receiveMessage` with `maxNumberOfMessages = 5`, but replace Jackson with `converter.fromBody(message.body, clazz)` inside `runCatching { ... }.getOrNull()`. Do not delete/ack messages in this method (preserve existing example semantics).
- Remove Jackson `ObjectMapper` dependency from this class.

### 7. Replace scheduled receiver with @SqsListener
Rewrite `internal/AutomaticMessageReceiver.kt`:
- Remove `@Scheduled` and the `MessagingService` polling dependency.
- Use `@SqsListener(SqsMessagingService.DEFAULT_SQS_QUEUE_NAME)` on a suspend method with a single `String` payload parameter, logging the received message.
- The starter registrar auto-config handles polling/ack; no manual scheduling.

### 8. Tests — use official Testcontainers LocalStack lifecycle
The custom `examples/src/test/kotlin/com/example/demo/LocalstackTestContainer.kt` wrapper is NOT required. Delete it and use the official Testcontainers LocalStack module directly with the JUnit Jupiter lifecycle.

Verified official API (testcontainers-localstack 2.0.5 jar):
- `org.testcontainers.localstack.LocalStackContainer` extends `GenericContainer<LocalStackContainer>`.
- Constructors: `LocalStackContainer(String image)` / `LocalStackContainer(DockerImageName)`.
- `withServices(vararg services: String): LocalStackContainer`
- `getEndpoint(): java.net.URI`, `getAccessKey()`, `getSecretKey()`, `getRegion()`.

For each of the three LocalStack tests:
- Add `@Testcontainers` to the test class.
- In the companion object, declare a static container field using `@JvmField @Container`:
  ```kotlin
  @JvmField
  @Container
  val localstack: LocalStackContainer =
      LocalStackContainer(DockerImageName.parse("localstack/localstack:4"))
          .withServices("s3", "sqs", "secretsmanager")
          .apply {
              System.getenv("LOCALSTACK_AUTH_TOKEN")?.takeIf { it.isNotBlank() }
                  ?.let { withEnv("LOCALSTACK_AUTH_TOKEN", it) }
          }
  ```
- Keep `@JvmStatic @DynamicPropertySource` but register `awskotlin.endpoint` from `localstack.endpoint.toString()`.
- Gate the tests on the license token so they skip cleanly without it (matching the port modules): add `@EnabledIfEnvironmentVariable(named = "LOCALSTACK_AUTH_TOKEN", matches = ".+")` to each test class.
- Do NOT hand-roll container start/stop/health polling; rely on the official container wait strategy and JUnit lifecycle.

Starter auto-configuration imports:
- `StorageServiceTest`: `@Import(S3StorageService::class)` and `@ImportAutoConfiguration(AwsKotlinAutoConfiguration::class, S3AutoConfiguration::class)`. Remove `AwsConfig`/`AwsProperties` imports and `@EnableConfigurationProperties(AwsProperties::class)`.
- `AwsSecretsManagementServiceTest`: `@Import(AwsSecretsManagementService::class)` and `@ImportAutoConfiguration(AwsKotlinAutoConfiguration::class, SecretsManagerAutoConfiguration::class)`.
- `MessagingServiceTest`: `@Import(SqsMessagingService::class)` and `@ImportAutoConfiguration(AwsKotlinAutoConfiguration::class, SqsAutoConfiguration::class)`. Remove `JacksonAutoConfiguration` import/auto-config. Add `@Serializable` to `data class MyMessage(val text: String)` so `KotlinxSqsMessageConverter` can serialize it.
- Keep all existing assertions and `eventually` blocks unchanged.
- Do not modify `TestDemoApplication` unless compilation requires it.

## Rulings (already decided; do not reopen)
- Use S3/Secrets Manager/SQS starters only; SNS starter is not demonstrated because examples have no SNS flow.
- Raw starter-provided SDK clients are acceptable where templates lack operations: S3 bucket creation, SQS queue creation/batch receive, Secrets Manager write with description/forceOverwrite.
- Keep public example interfaces and test assertions; do not weaken tests to make them pass.
- Delete the custom `LocalstackTestContainer` wrapper; use official Testcontainers `LocalStackContainer` with `@Testcontainers`/`@Container` and token-gated execution.
- No commits.

## Verification
Run from the worktree root:
1. `./gradlew :examples:compileKotlin :examples:compileTestKotlin :examples:lintKotlin`
2. `./gradlew :examples:test`
3. If port modules were untouched, a full `./gradlew test` is optional but preferred if time allows.

If LocalStack/Docker is unavailable and examples integration tests cannot start, report that explicitly with the failure output; do not delete or disable the tests. Compile/lint must still pass.

## Report
Write the full report to `.superpowers/sdd/aws-kotlin-port/task-21-report.md` including: files changed, design notes, exact verification commands and results, any concerns. Return only status (DONE / DONE_WITH_CONCERNS / NEEDS_CONTEXT / BLOCKED), a one-line test summary, and concerns.