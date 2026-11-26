# Cleanup task — build modules, artifact names, kotlinx-serialization, kapt removal

Worktree: D:/hantsylabs/aws-sdk-kotlin-spring-example/.worktrees/awskotlin-spring-port
No commits authorized. Do not dispatch subagents. User explicitly authorized removing list/utilities/aws-build-tool.

## 1. Remove unused template modules and convention build
- Delete directories `list/`, `utilities/`, `aws-build-tool/`.
- settings.gradle.kts: remove `includeBuild("aws-build-tool")` and `include("list", "utilities", ...)`. Keep `examples` and all port modules.
- Verify no remaining build script references `buildlogic.*` plugins. Examples and port modules use catalog plugin aliases only.
- Do NOT delete examples.

## 2. Rename artifacts and starter folders
Module artifact IDs (base.archivesName) drop the word spring:
- core -> awskotlin-core
- s3 -> awskotlin-s3
- secrets-manager -> awskotlin-secrets-manager
- sns -> awskotlin-sns
- sqs -> awskotlin-sqs

Starter folders rename under starters/:
- starters/starter-s3 -> starters/s3-starter
- starters/starter-secrets-manager -> starters/secrets-manager-starter
- starters/starter-sns -> starters/sns-starter
- starters/starter-sqs -> starters/sqs-starter

Starter artifact IDs follow Spring Boot third-party starter guide:
- awskotlin-s3-spring-boot-starter
- awskotlin-secrets-manager-spring-boot-starter
- awskotlin-sns-spring-boot-starter
- awskotlin-sqs-spring-boot-starter

Update settings.gradle.kts includes to `starters:s3-starter`, etc. Update any project(":...") references if needed (starters depend on :s3/:core etc, unchanged). Keep package names unchanged.

## 3. Replace Jackson with kotlinx-serialization-json in port modules
Catalog:
- Add version `kotlinxSerialization = "1.9.0"` (if resolution fails, pick a compatible published version and document).
- Add library `kotlinxSerializationJson = { module = "org.jetbrains.kotlinx:kotlinx-serialization-json", version.ref = "kotlinxSerialization" }`.
- Add plugin `kotlinPluginSerialization = { id = "org.jetbrains.kotlin.plugin.serialization", version.ref = "kotlin" }`.
- Remove `jacksonDatabind` library if no longer referenced. Keep `jacksonKotlin` because examples/bundles.kotlinSupport still uses it for WebFlux HTTP.

SQS:
- Replace `JacksonSqsMessageConverter` with `KotlinxSqsMessageConverter` implementing existing `SqsMessageConverter`.
- String payload passthrough unchanged.
- For non-String payloads use kotlinx.serialization runtime serializers, e.g. `serializer(payload::class.starProjectedType)` / `serializer(targetType.kotlin.starProjectedType)` with a configured `Json { ignoreUnknownKeys = true }`.
- Wrap serialization failures in existing `SqsMessageConversionException`; conversion failure must still not delete messages.
- Update `SqsTemplate` default converter and starter bean to KotlinxSqsMessageConverter.
- Apply serialization plugin to sqs module so test payloads can be @Serializable. Update tests: replace Jackson-specific assertions/imports; use @Serializable data classes. Keep all existing behavior coverage.
- Remove jacksonDatabind/jacksonKotlin deps from sqs.

SNS:
- Rewrite `SnsNotificationParser` using kotlinx.serialization.json tree API (`Json.parseToJsonElement`, jsonObject/jsonPrimitive/contentOrNull). Keep public API and `SnsNotificationParseException` semantics: invalid JSON/non-object/missing required fields/unknown Type -> typed error; unknown fields ignored; optional non-string fields -> null.
- Remove jacksonDatabind dep from sns. Add kotlinxSerializationJson. No serialization compiler plugin needed if parser/tests use JsonElement trees only.
- Update parser tests to kotlinx expectations; keep coverage.

Do not change examples' Jackson usage.

## 4. Remove kapt / configuration processor
- Remove `id("org.jetbrains.kotlin.kapt")`, `kapt(libs.springBootConfigurationProcessor)`, and kapt argument blocks from core and all starters.
- Remove catalog entries `kotlinKapt` plugin and `springBootConfigurationProcessor` library if unused.
- Replace hand-written `META-INF/additional-spring-configuration-metadata.json` with standard `META-INF/spring-configuration-metadata.json` in core and each starter, documenting ALL constructor-bound properties (including nested sqs listener fields). Without the processor, standard metadata is the IDE source of truth. Keep kebab-case names, correct types/defaults/sourceType.
- Try removing per-module `gradle.properties` `kotlin.compiler.runViaBuildToolsApi=false` workaround (it existed for kapt). If compilation/lint fails without it, restore the minimal property and document why. Delete empty gradle.properties files if no properties remain.
- Keep ktlint compiler-pin resolution rules.

## Verification
Run from worktree root:
- `./gradlew :core:test :s3:test :secrets-manager:test :sns:test :sqs:test :starters:s3-starter:test :starters:secrets-manager-starter:test :starters:sns-starter:test :starters:sqs-starter:test`
- `./gradlew :core:lintKotlin :s3:lintKotlin :secrets-manager:lintKotlin :sns:lintKotlin :sqs:lintKotlin :starters:s3-starter:lintKotlin :starters:secrets-manager-starter:lintKotlin :starters:sns-starter:lintKotlin :starters:sqs-starter:lintKotlin`
- `./gradlew :examples:compileKotlin :examples:compileTestKotlin` (ensure examples still builds)
Report exact summaries, skipped LocalStack counts, and any deviations.

## Report contract
Write full report to `.superpowers/sdd/aws-kotlin-port/task-20-report.md`.
Return only status, one-line test summary, concerns.