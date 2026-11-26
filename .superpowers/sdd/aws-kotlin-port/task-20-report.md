# Task 20 report — build cleanup, artifact names, kotlinx-serialization, kapt removal

Worktree: `D:/hantsylabs/aws-sdk-kotlin-spring-example/.worktrees/awskotlin-spring-port`
Branch: `awskotlin-spring-port`. Working-tree changes only; **no commits made**. No subagents dispatched.

## Status: COMPLETE — all verification green

## 1. Removed unused template modules and convention build
- Deleted directories `list/`, `utilities/`, `aws-build-tool/` (user-authorized).
- `settings.gradle.kts`: removed the `pluginManagement { includeBuild("aws-build-tool") }` block and
  dropped `list`, `utilities` from `include(...)`. Kept `examples` and all port modules.
- Verified no remaining build script references `buildlogic.*` plugins (grep across
  `core/s3/secrets-manager/sns/sqs/starters/examples/gradle` returned no matches). Examples and port
  modules use catalog plugin aliases only.
- `examples` untouched and still builds.

## 2. Renamed artifacts and starter folders
Module `base.archivesName` (word `spring` dropped):
- `core` -> `awskotlin-core`
- `s3` -> `awskotlin-s3`
- `secrets-manager` -> `awskotlin-secrets-manager`
- `sns` -> `awskotlin-sns`
- `sqs` -> `awskotlin-sqs`

Starter folders renamed under `starters/` (via `git mv`) and artifact IDs set per the Spring Boot
third-party starter guide:
- `starters/starter-s3` -> `starters/s3-starter` -> `awskotlin-s3-spring-boot-starter`
- `starters/starter-secrets-manager` -> `starters/secrets-manager-starter` -> `awskotlin-secrets-manager-spring-boot-starter`
- `starters/starter-sns` -> `starters/sns-starter` -> `awskotlin-sns-spring-boot-starter`
- `starters/starter-sqs` -> `starters/sqs-starter` -> `awskotlin-sqs-spring-boot-starter`

`settings.gradle.kts` includes updated to `starters:s3-starter`, etc. Starter `project(":s3")` /
`project(":core")` dependencies unchanged; package names unchanged. Confirmed produced jars:
`awskotlin-core-0.1.0-SNAPSHOT.jar`, `awskotlin-s3-spring-boot-starter-0.1.0-SNAPSHOT.jar`, etc.
(Old `awskotlin-spring-*` jars still present under `build/libs` are stale, gitignored outputs from
prior builds — not tracked, not regenerated.)

## 3. Replaced Jackson with kotlinx-serialization-json in port modules
Catalog (`gradle/libs.versions.toml`):
- Added `kotlinxSerialization = "1.9.0"` — **resolved successfully, no fallback needed**.
- Added library `kotlinxSerializationJson` (`org.jetbrains.kotlinx:kotlinx-serialization-json`).
- Added plugin `kotlinPluginSerialization` (`org.jetbrains.kotlin.plugin.serialization`, version.ref `kotlin`).
- Removed `jacksonDatabind` library (no longer referenced).
- **Kept `jacksonKotlin`** — still used by `examples` via `bundles.kotlinSupport` for WebFlux HTTP.

SQS:
- Replaced `JacksonSqsMessageConverter` with `KotlinxSqsMessageConverter` (file renamed via `git mv`),
  implementing the existing `SqsMessageConverter`. String payload passthrough unchanged in both
  directions. Non-String payloads use runtime serializers
  `serializer(payload::class.starProjectedType)` / `serializer(targetType.kotlin.starProjectedType)`
  with a configured `Json { ignoreUnknownKeys = true }` (injectable via constructor).
- Failures wrapped in the existing `SqsMessageConversionException`; conversion failure still does not
  delete messages (behavior covered by `SqsTemplateTest`).
- `SqsTemplate` default converter and `SqsAutoConfiguration.sqsMessageConverter()` bean now use
  `KotlinxSqsMessageConverter`; `SqsAutoConfigurationTest` assertion updated.
- Applied the serialization plugin to the `sqs` module; test payloads use `@Serializable data class Order`.
  Removed `jacksonDatabind`/`jacksonKotlin` deps from `sqs`; added `kotlinxSerializationJson`
  (kept `kotlinReflect`, required for `starProjectedType`/runtime serializer lookup).
- Converter test renamed to `KotlinxSqsMessageConverterTest`; Jackson imports/assertions replaced with
  kotlinx expectations. Kept all prior behavior coverage and added two cases (unknown-fields-ignored,
  non-serializable-payload).

SNS:
- Rewrote `SnsNotificationParser` using the kotlinx.serialization.json tree API
  (`Json.parseToJsonElement`, `JsonObject`, `JsonPrimitive.isString`, `content`/`contentOrNull`).
  Public API and `SnsNotificationParseException` semantics preserved: invalid JSON / non-object /
  missing required fields / unknown `Type` -> typed error; unknown fields ignored; optional non-string
  fields -> null. Updated the exception KDoc to reference kotlinx.serialization instead of Jackson.
- Removed `jacksonDatabind` from `sns`; added `kotlinxSerializationJson`. No serialization compiler
  plugin needed (parser/tests use `JsonElement` trees only).
- Parser tests updated to kotlinx expectations (`SerializationException` cause); coverage kept.

`examples` Jackson usage unchanged.

## 4. Removed kapt / configuration processor
- Removed `id("org.jetbrains.kotlin.kapt")`, `kapt(libs.springBootConfigurationProcessor)`, and the
  `kapt { arguments { ... } }` blocks from `core` and all four starters.
- Removed catalog entries `kotlinKapt` plugin and `springBootConfigurationProcessor` library (unused).
- Replaced hand-written `META-INF/additional-spring-configuration-metadata.json` with standard
  `META-INF/spring-configuration-metadata.json` in `core` and each starter (renamed via `git mv`).
  Added `groups` entries and retained `properties` documenting **all** constructor-bound properties,
  including the nested `sqs` listener fields (`auto-startup`, `max-concurrent-messages`, `poll-time`,
  `shutdown-timeout`). Kebab-case names, types, defaults and `sourceType` preserved. JSON validated:
  core 1 group/6 props; s3 1/4; secrets-manager 1/3; sns 1/3; sqs 2 groups/8 props.
- Kept the ktlint compiler-pin resolution rules in every module.

### Deviation — `kotlin.compiler.runViaBuildToolsApi=false` still required (brief assumption failed)
Removing the per-module `gradle.properties` workaround broke **regular `compileKotlin`**, not just kapt:
```
Execution failed for task ':core:compileKotlin' ...
  BuildToolsApiCompilationWork
  > 'java.util.List org.jetbrains.kotlin.compilerRunner.ArgumentsToStrings.toArgumentStrings$default(...)'
```
Root cause: Gradle 9.9.2 ships Kotlin 2.4.21 compiler classes in its parent classloader, which breaks
KGP 2.4.10's Build Tools API compilation path (the same `NoSuchMethodError` previously seen with kapt
stub generation). The legacy compiler runner avoids that path.

Smallest correct fix: restored the property **once** in the root `gradle.properties` (applies to every
subproject) instead of recreating 9 per-module files, with an updated comment explaining it is needed
for the general BTA compilation path after kapt removal. Deleted all 9 per-module `gradle.properties`
files (they contained only this property). Deprecated in KGP 2.4.x, removed in 2.5.0.

## Other deviations / notes
- **Converter exception handling:** `KotlinxSqsMessageConverter` catches `IllegalArgumentException`
  (the superclass of `kotlinx.serialization.SerializationException`). Serializer lookup for
  star-projected generic payloads (e.g. a cyclic `MutableList`) throws a plain
  `IllegalArgumentException`, not `SerializationException`; catching the superclass ensures every
  serialization failure is wrapped in `SqsMessageConversionException`. The cyclic-payload test asserts
  `IllegalArgumentException`; parse/encode failures assert `SerializationException`.
- **ktlint formatting:** the `standard:class-signature` rule (max_line_length 120, intellij_idea style)
  requires the single-parameter converter constructor on one line, and `standard:final-newline`
  requires trailing newlines. Ran `:sns:formatKotlin :sqs:formatKotlin` to auto-fix (cosmetic only;
  trailing comma retained per repo style). Re-ran `:sns:test :sqs:test` afterwards — green.
- **README:** updated the Modules section and stack table to remove references to the deleted
  `list`/`utilities`/`aws-build-tool` modules and reflect the new module/artifact layout and the
  kotlinx-serialization (port) vs Jackson (examples) split. Minimal doc correction; no behavior change.

## Verification (run from worktree root, `JAVA_HOME=D:\jdks\jdk25`)
1. `./gradlew :core:test :s3:test :secrets-manager:test :sns:test :sqs:test :starters:s3-starter:test
   :starters:secrets-manager-starter:test :starters:sns-starter:test :starters:sqs-starter:test`
   -> **BUILD SUCCESSFUL** (3m 21s).
2. `./gradlew :core:lintKotlin :s3:lintKotlin :secrets-manager:lintKotlin :sns:lintKotlin :sqs:lintKotlin
   :starters:s3-starter:lintKotlin :starters:secrets-manager-starter:lintKotlin :starters:sns-starter:lintKotlin
   :starters:sqs-starter:lintKotlin :examples:compileKotlin :examples:compileTestKotlin`
   -> **BUILD SUCCESSFUL** (1m 12s). All lint tasks pass; examples main + test compile.
3. `./gradlew :sns:test :sqs:test` (post-format re-check) -> **BUILD SUCCESSFUL** (31s).

### Test summary (from JUnit XML results)
| Module | tests | failures | errors | skipped |
| --- | --- | --- | --- | --- |
| core | 10 | 0 | 0 | 0 |
| s3 | 13 | 0 | 0 | 3 |
| secrets-manager | 7 | 0 | 0 | 0 |
| sns | 23 | 0 | 0 | 2 |
| sqs | 71 | 0 | 0 | 0 |
| starters/s3-starter | 8 | 0 | 0 | 0 |
| starters/secrets-manager-starter | 7 | 0 | 0 | 0 |
| starters/sns-starter | 7 | 0 | 0 | 0 |
| starters/sqs-starter | 20 | 0 | 0 | 5 |
| **TOTAL** | **166** | **0** | **0** | **10** |

**Skipped LocalStack counts (10 total):** `S3TemplateLocalStackTest` (3), `SnsSqsFanOutLocalStackTest` (2),
`SqsLocalStackIntegrationTest` (5). All skipped via `assumeTrue(LOCALSTACK_AUTH_TOKEN != null)` — the
license token is not set in this environment, so the LocalStack containers are not started.

## Concerns
- The `runViaBuildToolsApi=false` workaround is still required for compilation under KGP 2.4.10 +
  Gradle 9.9.2 and is now centralized in the root `gradle.properties`. It is deprecated in KGP 2.4.x
  and removed in 2.5.0; a future Kotlin/Gradle alignment should remove it.
- LocalStack integration tests (10) were skipped, not executed, because `LOCALSTACK_AUTH_TOKEN` is
  unset here; end-to-end SQS/S3/SNS behavior against a real container was not exercised in this run.
- `KotlinxSqsMessageConverter` resolves serializers from the runtime class via star-projected types;
  generic collection payloads without a concrete `@Serializable` type fail lookup and are wrapped as
  conversion errors (documented in the converter KDoc). This matches the brief's prescribed approach.