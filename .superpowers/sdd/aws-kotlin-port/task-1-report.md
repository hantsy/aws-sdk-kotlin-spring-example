# Task 1 Report: Build Foundation and Core Auto-Configuration

Status: DONE_WITH_CONCERNS
Worktree: `D:/hantsylabs/aws-sdk-kotlin-spring-example/.worktrees/awskotlin-spring-port`
No commits were made. `examples/`, `list/`, `utilities/`, `aws-build-tool/` were not modified.

## Files changed

Modified:
- `settings.gradle.kts` — added `include("core")` only (see decision below).
- `gradle/libs.versions.toml` — added libraries `awsSdkKotlinSns`, `springBootAutoconfigure`, `springBootConfigurationProcessor`, `springBootStarterTest`, `jacksonDatabind` (verbatim from brief), plus `awsSdkKotlinConfig` (required adjustment, see below); added plugin `kotlinKapt = { id = "org.jetbrains.kotlin.kapt", version.ref = "kotlin" }`.

Created:
- `core/build.gradle.kts`
- `core/gradle.properties` (workaround, see below)
- `core/src/main/kotlin/io/github/hantsy/awskotlin/core/AwsKotlinProperties.kt`
- `core/src/main/kotlin/io/github/hantsy/awskotlin/core/AwsKotlinCredentialsProviderFactory.kt`
- `core/src/main/kotlin/io/github/hantsy/awskotlin/core/autoconfigure/AwsKotlinAutoConfiguration.kt`
- `core/src/main/resources/META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports`
- `core/src/main/resources/META-INF/additional-spring-configuration-metadata.json` (see metadata decision)
- `core/src/test/kotlin/io/github/hantsy/awskotlin/core/AwsKotlinCredentialsProviderFactoryTest.kt`
- `core/src/test/kotlin/io/github/hantsy/awskotlin/core/autoconfigure/AwsKotlinAutoConfigurationTest.kt`

## Implementation decisions

1. **Settings includes**: Added only `include("core")` per the ruling. The other module directories (`s3`, `secrets-manager`, `sqs`, `sns`, `starters:*`) have no build files yet; including them now would create empty projects and risk a broken baseline. Later tasks add their own includes.

2. **`aws-config` dependency added (adjustment to brief)**: The brief's build file lists only `api(libs.awsSdkKotlinHttp)`. Verified against the published `aws.sdk.kotlin:aws-http:1.9.45` POM: it does NOT transitively expose `aws-config`, which contains `StaticCredentialsProvider`, `ProfileCredentialsProvider`, `DefaultChainCredentialsProvider`. Added catalog entry `awsSdkKotlinConfig = { module = "aws.sdk.kotlin:aws-config", version.ref = "awsSdkKotlin" }` and `api(libs.awsSdkKotlinConfig)` to core. APIs were verified against both the authoritative source at `D:/hantsylabs/aws-sdk-kotlin` and the published 1.9.45 jars (javap): `StaticCredentialsProvider { accessKeyId = ...; secretAccessKey = ... }` DSL, `ProfileCredentialsProvider(profileName, region)`, `DefaultChainCredentialsProvider(region = ...)`.

3. **kapt applied without version (adjustment)**: `alias(libs.plugins.kotlinKapt)` fails with "plugin already on the classpath with an unknown version" because the kapt plugin implementation ships inside `kotlin-gradle-plugin`, which the root build already loads. Core applies `id("org.jetbrains.kotlin.kapt")` (no version). The catalog entry `kotlinKapt` was still added as the brief requires (currently unused by core).

4. **Factory semantics**: If either static key is non-null, `require(both non-null)` throws `IllegalArgumentException`; otherwise profile override, otherwise `DefaultChainCredentialsProvider(region = properties.region)`. Exactly per brief. `AwsKotlinProperties` and `AwsKotlinAutoConfiguration` match the brief verbatim (plus KDoc and trailing commas required by ktlint). User beans win via `@ConditionalOnMissingBean(CredentialsProvider::class)`; `awskotlin.enabled=false` disables via `@ConditionalOnProperty(matchIfMissing = true)`.

5. **Boot 4.2 test API adaptation**: Spring Boot 4.2 removed the convenience methods `hasSingleBean`/`doesNotHaveBean`/`hasFailed` from `AssertableApplicationContext` (verified via javap on `spring-boot-test-4.2.0.jar`). Tests use `ApplicationContextRunner` with BeanFactory APIs (`getBeanNamesForType`, `getBean`, `startupFailure`) asserted with Kotest, plus MockK for the user bean and `runBlocking` for suspend `resolve()`. JUnit Jupiter 6 via `spring-boot-starter-test`.

6. **kapt + Gradle 9.9.2 / KGP 2.4.10 incompatibility (workaround)**: `kaptGenerateStubsTestKotlin` failed with `NoSuchMethodError: ArgumentsToStrings.toArgumentStrings$default(CommonToolArguments, boolean, boolean, int, Object)`. Root cause (verified with javap): Gradle 9.9.2 ships `kotlin-compiler-embeddable-2.4.21-min.jar` / `kotlin-compiler-runner-2.4.21.jar` in its distribution `lib/` (parent classloader); the 2.4.21 `ArgumentsToStrings` only has the 3-boolean `$default` overload, while KGP 2.4.10's build-tools-impl calls the 2-boolean one. Fixed by `core/gradle.properties` with `kotlin.compiler.runViaBuildToolsApi=false` (legacy compiler runner, scoped to core only; root `gradle.properties` untouched). KGP marks this property deprecated (removal in Kotlin 2.5.0) and emits a warning.

7. **ktlint compiler pin (workaround)**: `lintKotlin` failed with `IllegalStateException: Extensions storage is not registered` because a resolution rule in this build ("selected by rule" per `dependencyInsight`) aligns Kotlin artifacts to Gradle's embedded 2.4.21, while ktlint 1.8.0 (kotlinter 5.7.0) requires `kotlin-compiler-embeddable` 2.2.x. Fixed in `core/build.gradle.kts` by pinning `kotlin-compiler-embeddable`, `kotlin-daemon-embeddable`, `kotlin-script-runtime`, `kotlin-build-tools-api` to 2.2.21 in the `ktlint` configuration only. Compilation still uses Kotlin 2.4.10. Note: existing modules do not apply kotlinter (convention plugins don't include it), so there was no working lint baseline.

8. **Configuration metadata (adjustment)**: kapt ran and produced the `awskotlin` group, but `properties` was empty. Root cause established by decompiling `spring-boot-configuration-processor-4.2.0` and experimenting:
   - Kotlin generates a synthetic no-arg constructor when ALL constructor parameters have defaults, so the kapt stub has 2 constructors; the processor's single-constructor deduction fails.
   - Adding `@ConstructorBinding` did not help: kapt copies it onto BOTH stub constructors, and the processor returns empty when >1 bound constructor (experiment reverted).
   - The JavaBean fallback requires a setter (`JavaBeanPropertyDescriptor.isProperty` = `!excluded && hasGetter && (hasSetter || isCollectionOrMap)`); Kotlin `val` properties have none.
   - A control experiment with a temporary Java `@ConfigurationProperties` class generated properties correctly (file removed afterwards), confirming the issue is Kotlin-stub-specific.
   Solution: kept the brief's data class unchanged and added `core/src/main/resources/META-INF/additional-spring-configuration-metadata.json` documenting all six properties (types, descriptions, `defaultValue: true` for `enabled`). The processor's Gradle resources-directory heuristic fails for kapt's `build/tmp/kapt3/classes/main` layout, so core passes the documented processor argument `org.springframework.boot.configurationprocessor.additionalMetadataLocations=<projectDir>/src/main/resources` via `kapt { arguments { ... } }`. Verified: the generated `spring-configuration-metadata.json` (and the jar) now contains the group plus all six `awskotlin.*` properties.

9. **Formatting**: `:core:formatKotlin` was run once to fix `standard:final-newline` violations (files created without trailing newlines). No other style changes were needed.

## TDD sequence and exact commands run

All commands from the worktree root in Git Bash:

1. Red: `JAVA_HOME="D:/jdks/jdk25" ./gradlew :core:test --console=plain`
   - First attempts failed on infrastructure (kapt plugin version conflict, then the BT API `NoSuchMethodError`); after workarounds, failed at `:core:compileTestKotlin` with unresolved references ONLY to the not-yet-written `AwsKotlinProperties`, `AwsKotlinCredentialsProviderFactory`, `AwsKotlinAutoConfiguration` — the intended red state.
2. Green: implemented the three main classes + imports file, then
   `JAVA_HOME="D:/jdks/jdk25" ./gradlew :core:test :core:lintKotlin --console=plain`
   - Failed on ktlint worker error (compiler 2.4.21 clash) → pinned ktlint classpath; then failed on `final-newline` violations → `JAVA_HOME="D:/jdks/jdk25" ./gradlew :core:formatKotlin --console=plain`.
3. Final verification (exact command from the brief):
   `JAVA_HOME="D:/jdks/jdk25" ./gradlew :core:test :core:lintKotlin --console=plain`
   - **BUILD SUCCESSFUL** (25s). Test results:
     - `AwsKotlinCredentialsProviderFactoryTest`: tests=5, failures=0, errors=0, skipped=0
     - `AwsKotlinAutoConfigurationTest`: tests=5, failures=0, errors=0, skipped=0
   - Lint: `:core:lintKotlinMain`, `:core:lintKotlinTest`, `:core:lintKotlin` all passed.
4. Baseline sanity: `JAVA_HOME="D:/jdks/jdk25" ./gradlew :list:compileKotlin :utilities:compileKotlin --console=plain` — BUILD SUCCESSFUL.
5. Jar inspection: `awskotlin-spring-core-0.1.0-SNAPSHOT.jar` contains `META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports`, merged `META-INF/spring-configuration-metadata.json` (group + 6 properties), and the additional metadata file.

Tests cover all seven required scenarios: missing-secret and missing-access-key throw `IllegalArgumentException`; both keys produce `StaticCredentialsProvider` (resolved credentials verified); profile override produces `ProfileCredentialsProvider` (profileName/region verified); no overrides produce `DefaultChainCredentialsProvider` (region pass-through verified); single provider bean by default; user bean wins (same-instance check); `awskotlin.enabled=false` removes the provider; plus static property binding through the context and context startup failure when the secret is missing.

## Concerns

1. **`kotlin.compiler.runViaBuildToolsApi=false` is deprecated** (KGP says removal in Kotlin 2.5.0). It is scoped to `core/gradle.properties`. Later modules that use kapt will need the same property (or a Kotlin/Gradle version alignment where KGP matches Gradle's embedded Kotlin). The underlying clash is Gradle 9.9.2 bundling Kotlin 2.4.21 vs the mandated Kotlin 2.4.10.
2. **ktlint compiler pin (2.2.21) is a workaround** for the same Gradle-embedded-Kotlin alignment rule; if kotlinter/ktlint upgrades to a version compatible with compiler 2.4.x, the pin in `core/build.gradle.kts` should be removed. Later modules applying kotlinter will need the same pin.
3. **Configuration metadata for constructor-bound Kotlin properties is maintained manually** in `additional-spring-configuration-metadata.json`. If `AwsKotlinProperties` gains/changes properties, this file must be updated in sync (the processor cannot infer them from kapt stubs). Later modules with their own `@ConfigurationProperties` will face the same limitation.
4. **Deprecation warning noise**: every core build prints the KGP warning about `kotlin.compiler.runViaBuildToolsApi`. Cosmetic only.
5. `springmockk` (in the `mockk` bundle, per brief) targets Boot 3.x; it is on the test classpath but unused. If later tasks use `@MockkBean`, compatibility with Boot 4.2 should be verified.
6. The `endpoint` property is stored but not yet consumed (no client beans in Task 1); service modules will apply it.