# Task 2 Report: S3 Template and Starter

Status: DONE_WITH_CONCERNS
Worktree: `D:/hantsylabs/aws-sdk-kotlin-spring-example/.worktrees/awskotlin-spring-port`
No commits were made. `examples/`, `list/`, `utilities/`, `aws-build-tool/` were not modified. SNS/SQS/Secrets Manager were not implemented.

## Files changed

Modified:
- `settings.gradle.kts` — added `include("s3")` and `include("starters:starter-s3")` (the `include("core")` line in the diff is Task 1's uncommitted change).

Created:
- `s3/build.gradle.kts`
- `s3/gradle.properties` (KGP/Gradle compiler workaround, copied from core)
- `s3/src/main/kotlin/io/github/hantsy/awskotlin/s3/S3Template.kt`
- `s3/src/main/kotlin/io/github/hantsy/awskotlin/s3/S3BucketOperations.kt`
- `s3/src/main/kotlin/io/github/hantsy/awskotlin/s3/S3ObjectOperations.kt`
- `s3/src/test/kotlin/io/github/hantsy/awskotlin/s3/S3TemplateTest.kt`
- `s3/src/test/kotlin/io/github/hantsy/awskotlin/s3/S3TemplateLocalStackTest.kt`
- `starters/starter-s3/build.gradle.kts`
- `starters/starter-s3/gradle.properties` (same workaround)
- `starters/starter-s3/src/main/kotlin/io/github/hantsy/awskotlin/s3/autoconfigure/S3Properties.kt`
- `starters/starter-s3/src/main/kotlin/io/github/hantsy/awskotlin/s3/autoconfigure/S3AutoConfiguration.kt`
- `starters/starter-s3/src/main/resources/META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports`
- `starters/starter-s3/src/main/resources/META-INF/additional-spring-configuration-metadata.json`
- `starters/starter-s3/src/test/kotlin/io/github/hantsy/awskotlin/s3/autoconfigure/S3AutoConfigurationTest.kt`

## API verification (authoritative source + published jars)

All APIs were verified against `D:/hantsylabs/aws-sdk-kotlin` (codegen model `codegen/sdk/aws-models/s3.json`, `services/s3` e2e tests, `ClientConfigIntegration.kt`) AND the published `aws.sdk.kotlin:s3-jvm:1.9.45` / `aws.smithy.kotlin:runtime-core-jvm:1.7.17` jars via `javap`:

- `S3Client` interface: `headBucket(HeadBucketRequest)`, `listObjectsV2(ListObjectsV2Request)`, `putObject(PutObjectRequest)`, `deleteObject(DeleteObjectRequest)`, and the streaming transform overload `getObject(GetObjectRequest, suspend (GetObjectResponse) -> T)`. DSL block extensions (`headBucket {}`, `putObject {}`, ...) are top-level functions in `aws.sdk.kotlin.services.s3` and must be imported explicitly. There is NO `getObject {}` DSL extension and no `getObject(block, transform)` extension — only the interface method with an explicit `GetObjectRequest`.
- `S3Client.Config` exposes `region`, `credentialsProvider`, `endpointUrl: Url?`, `forcePathStyle: Boolean` — used directly in the auto-configuration tests (no limitation needed).
- `aws.smithy.kotlin.runtime.net.url.Url.Companion.parse(String)` and `aws.smithy.kotlin.runtime.content.ByteStream` / suspend `toByteArray()` confirmed.
- `ListObjectsV2Request.Builder.maxKeys` is `Int?` (`java.lang.Integer`), `prefix` `String?`.

## Implementation decisions

1. **`NotFound` instead of `NotFoundException` (deviation from brief, verified)**: The brief says `exists` catches `aws.sdk.kotlin.services.s3.model.NotFoundException`. That class does not exist in `aws.sdk.kotlin:s3:1.9.45`. The Smithy model shape `com.amazonaws.s3#NotFound` (the only error of `HeadBucket`) generates the class `aws.sdk.kotlin.services.s3.model.NotFound` (verified in the jar: `NotFound extends S3Exception`; zero `NotFoundException` entries in the jar). `exists()` catches only `NotFound`; all other SDK exceptions propagate (unit-tested with `S3Exception`). Semantics are exactly as the brief requires.

2. **Public API matches the brief**, including `fun \`object\`(key: String)` — `object` is a Kotlin hard keyword, so the declaration and all call sites use backticks. `S3BucketOperations`/`S3ObjectOperations` have `internal constructor`s; only `S3Template.bucket()` creates them. `get()` consumes the body inside `client.getObject(request) { it.body?.toByteArray() }` and returns `ByteArray?`. `listObjects` returns one SDK page (no pagination). `put`/`delete` map directly to SDK requests.

3. **Enabled conditions in one annotation**: `@ConditionalOnProperty` is not repeatable, so `S3AutoConfiguration` uses `@ConditionalOnProperty(prefix = "awskotlin", name = ["enabled", "s3.enabled"], havingValue = "true", matchIfMissing = true)` — names are relative to the prefix, so this checks both `awskotlin.enabled` and `awskotlin.s3.enabled` at class level. Both disable paths are unit-tested.

4. **Client bean per brief**: `region = s3.region ?: global.region`; injected `CredentialsProvider` (from core auto-config, `@AutoConfiguration(after = [AwsKotlinAutoConfiguration::class])`); `endpointUrl = (s3.endpoint ?: global.endpoint)?.let { Url.parse(it) }`; `forcePathStyle = s3.pathStyleAccessEnabled ?: (endpoint != null)`. `@ConditionalOnMissingBean` on both `S3Client` and `S3Template` beans so user beans win. Spring's inferred destroy method closes the SDK client on context shutdown.

5. **`get` unit-tested with a mocked transform (no LocalStack needed)**: MockK `coEvery { client.getObject(capture(slot), any<suspend (GetObjectResponse) -> ByteArray?>()) } coAnswers { secondArg<...>()(GetObjectResponse { body = ByteStream.fromBytes(bytes) }) }` invokes the real transform with a real SDK response, so `toByteArray()` consumption is exercised in the unit test. A second test covers the null-body case. The brief's fallback ("cover in LocalStack and record") was not needed.

6. **JUnit 6 return-value rule (discovered during testing)**: JUnit Jupiter 6 refuses to execute `@Test` methods that return a value ("must not return a value. It will not be executed" — observed as a warning, tests silently not run). `runBlocking { ... }` returns the last expression, so all LocalStack `@Test` bodies use `runBlocking<Unit>`. `runTest` (coroutines-test) returns `TestResult` = `Unit` on JVM, so the unit tests were unaffected. Later tasks must use `runBlocking<Unit>` (or `runTest`) for expression-body tests.

7. **LocalStack test gating**: `@BeforeAll` uses JUnit `Assumptions.assumeTrue` for (a) non-blank `LOCALSTACK_AUTH_TOKEN` env var and (b) `DockerClientFactory.instance().isDockerAvailable`. With the token unset (this machine has Docker but no token), all 3 tests are reported as skipped cleanly (`tests="3" skipped="3" failures="0" errors="0"`). Image pinned to `localstack/localstack:4` matching the repo convention (`docker-compose.yaml`, examples). Testcontainers 2.x API verified via javap: `org.testcontainers.localstack.LocalStackContainer(DockerImageName)`, `withServices(vararg String)` (string service names, not the old `Service` enum).

8. **Build workarounds copied from core**: both modules get `gradle.properties` with `kotlin.compiler.runViaBuildToolsApi=false` and the ktlint-config `resolutionStrategy` pin (kotlin-compiler-embeddable / kotlin-daemon-embeddable / kotlin-script-runtime / kotlin-build-tools-api -> 2.2.21). starter-s3 applies kapt as `id("org.jetbrains.kotlin.kapt")` without version and passes `additionalMetadataLocations`; the s3 module has no kapt (no `@ConfigurationProperties` there). Hand-written `additional-spring-configuration-metadata.json` documents all four `awskotlin.s3.*` properties; verified the built starter jar's merged `spring-configuration-metadata.json` contains the group + all four properties, and the `AutoConfiguration.imports` file is packaged.

9. **Dependencies**: s3 — `api(libs.awsSdkKotlinS3)`, `implementation` kotlinStdlib/coroutinesCore; tests: springBootStarterTest (brings the JUnit engine), kotest/mockk/kotlintest bundles, kotlinCoroutinesTest, `bundles.testcontainers`, junitPlatformLauncher. starter-s3 — `api(project(":s3"))`, `api(project(":core"))`, `implementation(libs.springBootAutoconfigure)`, kapt configurationProcessor; tests: springBootStarterTest, kotest/mockk/kotlintest bundles, junitPlatformLauncher. Awaitility was not needed (Testcontainers' start wait suffices; no polling required). Boot 4.2 context assertions use BeanFactory APIs (`getBeanNamesForType`/`getBean`), not the removed `hasSingleBean`.

## TDD sequence and exact commands run

All commands from the worktree root in Git Bash:

1. Red: `JAVA_HOME="D:/jdks/jdk25" ./gradlew :s3:test :starters:starter-s3:test --console=plain`
   - Failed at `:s3:compileTestKotlin` with unresolved references only to the not-yet-written `S3Template` (and cascading type-inference errors) — the intended red state.
2. Green attempt 1: same command after writing main sources — failed: DSL block extensions (`headBucket {}` etc.) are top-level functions needing explicit imports. Added imports.
3. Green: same command — **BUILD SUCCESSFUL** (1m 30s). But inspection of results showed the LocalStack class produced no XML; a filtered run
   `JAVA_HOME="D:/jdks/jdk25" ./gradlew :s3:test --tests "io.github.hantsy.awskotlin.s3.S3TemplateLocalStackTest" --console=plain -i`
   exposed the JUnit 6 "must not return a value" warnings → fixed with `runBlocking<Unit>`.
4. Formatting: `JAVA_HOME="D:/jdks/jdk25" ./gradlew :s3:formatKotlin :starters:starter-s3:formatKotlin --console=plain` — fixed `standard:indent` violations in test files (BUILD SUCCESSFUL).
5. Final verification (exact command from the brief):
   `JAVA_HOME="D:/jdks/jdk25" ./gradlew :s3:test :starters:starter-s3:test :s3:lintKotlin :starters:starter-s3:lintKotlin --console=plain`
   - **BUILD SUCCESSFUL** (1m 47s). Test results:
     - `S3TemplateTest`: tests=10, failures=0, errors=0, skipped=0
     - `S3TemplateLocalStackTest`: tests=3, skipped=3 (LOCALSTACK_AUTH_TOKEN unset), failures=0, errors=0
     - `S3AutoConfigurationTest`: tests=8, failures=0, errors=0, skipped=0
   - Lint: `:s3:lintKotlinMain/Test`, `:starters:starter-s3:lintKotlinMain/Test` all passed.
6. Baseline sanity: `JAVA_HOME="D:/jdks/jdk25" ./gradlew :core:test :list:compileKotlin :utilities:compileKotlin --console=plain` — BUILD SUCCESSFUL (core's 10 tests still pass).
7. Jar inspection: `unzip -p starters/starter-s3/build/libs/awskotlin-spring-starter-s3-0.1.0-SNAPSHOT.jar META-INF/spring-configuration-metadata.json` shows group `awskotlin.s3` + `enabled`, `endpoint`, `path-style-access-enabled`, `region`; `AutoConfiguration.imports` contains `io.github.hantsy.awskotlin.s3.autoconfigure.S3AutoConfiguration`.

Test coverage vs brief:
- Unit (10): exists true / exists false on NotFound / other SDK exception propagates (same instance); listObjects passes bucket/prefix/maxKeys and returns the SDK response, plus unset-args case; put maps bucket/key/body/contentType, plus unset-contentType case; delete maps bucket/key; get returns bytes via the response transform; get returns null for a bodyless response.
- Context (8): default beans with static credentials + region (config inspected: region, StaticCredentialsProvider, forcePathStyle=false); s3.region overrides global; s3.endpoint enables forcePathStyle and sets endpointUrl; global endpoint fallback; explicit path-style=false respected; `awskotlin.s3.enabled=false` removes beans; `awskotlin.enabled=false` removes beans; user S3Client bean wins (same-instance check, template still created).
- LocalStack (3, skipped here): full create-bucket/put/get/delete/list/exists round trip; exists=false for a missing bucket; binary byte-exact get.

## Concerns

1. **Brief's exception name is wrong for SDK 1.9.45**: `NotFoundException` does not exist; the modeled HeadBucket 404 error class is `aws.sdk.kotlin.services.s3.model.NotFound`. Implemented and tested against `NotFound`. If the design docs reference `NotFoundException`, they should be corrected for later tasks.
2. **LocalStack round trip not executed on this machine**: Docker is available but `LOCALSTACK_AUTH_TOKEN` is unset, so the 3 integration tests were skipped (cleanly). The happy path (container start + round trip) is unverified until run with a token; the Testcontainers 2.x API usage was verified via javap only.
3. **JUnit 6 silently skips value-returning @Test methods** (warning only). Later tasks using `runBlocking` in expression-body tests must write `runBlocking<Unit>`; worth a note in the shared plan.
4. **Inherited workarounds** (same as Task 1): deprecated `kotlin.compiler.runViaBuildToolsApi=false` (removal in Kotlin 2.5.0), ktlint compiler pin 2.2.21, and hand-maintained `additional-spring-configuration-metadata.json` that must be kept in sync with `S3Properties`.
5. `fun \`object\`` requires backticks at every call site (Kotlin hard keyword). This matches the brief's API but is slightly awkward for consumers; renaming would be an API decision for a later task.
6. `bundles.testcontainers` pulls `spring-boot-testcontainers` into the s3 test classpath even though the s3 module tests do not use Spring service connections; harmless, kept because the brief specifies the bundle. `springmockk` (in the mockk bundle) remains unused as in Task 1.