# Task 3+4 Batch Report: Secrets Manager and SNS Templates + Starters

Status: DONE
Worktree: D:/hantsylabs/aws-sdk-kotlin-spring-example/.worktrees/awskotlin-spring-port
Verification: BUILD SUCCESSFUL — 28 tests pass (7 per suite x 4 suites), 0 skipped, 0 failures, 0 errors; all lintKotlin tasks pass.

## Files changed

Modified (existing):
- settings.gradle.kts — added include("secrets-manager"), include("sns"), include("starters:starter-secrets-manager"), include("starters:starter-sns").

New — Secrets Manager service module (artifact awskotlin-spring-secrets-manager):
- secrets-manager/build.gradle.kts
- secrets-manager/gradle.properties
- secrets-manager/src/main/kotlin/io/github/hantsy/awskotlin/secretsmanager/SecretsManagerTemplate.kt
- secrets-manager/src/main/kotlin/io/github/hantsy/awskotlin/secretsmanager/SecretOperations.kt
- secrets-manager/src/test/kotlin/io/github/hantsy/awskotlin/secretsmanager/SecretsManagerTemplateTest.kt

New — Secrets Manager starter (artifact awskotlin-spring-starter-secrets-manager):
- starters/starter-secrets-manager/build.gradle.kts
- starters/starter-secrets-manager/gradle.properties
- starters/starter-secrets-manager/src/main/kotlin/io/github/hantsy/awskotlin/secretsmanager/autoconfigure/SecretsManagerProperties.kt
- starters/starter-secrets-manager/src/main/kotlin/io/github/hantsy/awskotlin/secretsmanager/autoconfigure/SecretsManagerAutoConfiguration.kt
- starters/starter-secrets-manager/src/main/resources/META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports
- starters/starter-secrets-manager/src/main/resources/META-INF/additional-spring-configuration-metadata.json
- starters/starter-secrets-manager/src/test/kotlin/io/github/hantsy/awskotlin/secretsmanager/autoconfigure/SecretsManagerAutoConfigurationTest.kt

New — SNS service module (artifact awskotlin-spring-sns):
- sns/build.gradle.kts
- sns/gradle.properties
- sns/src/main/kotlin/io/github/hantsy/awskotlin/sns/SnsTemplate.kt
- sns/src/main/kotlin/io/github/hantsy/awskotlin/sns/TopicOperations.kt
- sns/src/test/kotlin/io/github/hantsy/awskotlin/sns/SnsTemplateTest.kt

New — SNS starter (artifact awskotlin-spring-starter-sns):
- starters/starter-sns/build.gradle.kts
- starters/starter-sns/gradle.properties
- starters/starter-sns/src/main/kotlin/io/github/hantsy/awskotlin/sns/autoconfigure/SnsProperties.kt
- starters/starter-sns/src/main/kotlin/io/github/hantsy/awskotlin/sns/autoconfigure/SnsAutoConfiguration.kt
- starters/starter-sns/src/main/resources/META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports
- starters/starter-sns/src/main/resources/META-INF/additional-spring-configuration-metadata.json
- starters/starter-sns/src/test/kotlin/io/github/hantsy/awskotlin/sns/autoconfigure/SnsAutoConfigurationTest.kt

Not modified: examples/, list/, utilities/, aws-build-tool/, core/, s3/, starters/starter-s3/, gradle/libs.versions.toml (the catalog already contained awsSdkKotlinSecretManager and awsSdkKotlinSns entries from a prior task). No commit was made.

## API verification (authoritative source)

Verified against Smithy models in D:/hantsylabs/aws-sdk-kotlin/codegen/sdk/aws-models (secrets-manager.json, sns.json) AND the published 1.9.45 jars (secretsmanager-jvm from the Gradle cache; sns-jvm downloaded from Maven Central and inspected with javap, then removed). Did not assume Java SDK names.

Secrets Manager (package aws.sdk.kotlin.services.secretsmanager):
- Client SecretsManagerClient; DSL extensions getSecretValue/createSecret/updateSecret/deleteSecret.
- Models GetSecretValueRequest/Response, CreateSecretRequest/Response, UpdateSecretRequest/Response, DeleteSecretRequest/Response.
- Base exception SecretsManagerException (has a String-message constructor); modeled not-found exception ResourceNotFoundException (DSL builder, message field).
- Request field mapping: get -> secretId; create -> name (= secretId) + secretString; update -> secretId + secretString; delete -> secretId + forceDeleteWithoutRecovery.

SNS (package aws.sdk.kotlin.services.sns):
- Client SnsClient; DSL extensions publish/subscribe/deleteTopic/unsubscribe.
- Smithy input shapes are PublishInput/SubscribeInput/DeleteTopicInput/UnsubscribeInput but the generated Kotlin classes are PublishRequest/Response, SubscribeRequest/Response, DeleteTopicRequest/Response, UnsubscribeRequest/Response (confirmed in jar).
- Base exception SnsException (String-message constructor); modeled not-found exception NotFoundException (DSL builder, message field). Note: SNS uses NotFoundException, distinct from Secrets Manager's ResourceNotFoundException.
- Request field mapping: publish -> topicArn + message + subject; subscribe -> topicArn + protocol + endpoint; deleteTopic -> topicArn; unsubscribe -> subscriptionArn.

## Decisions

- SNS topic selector accepts topicArn only; no implicit topic creation or name resolution (per ruling). SnsTemplate.topic(topicArn) and TopicOperations are ARN-scoped.
- create(secretValue) maps secretId to CreateSecretRequest.name (the required identifier for a new secret); secretString carries the value. This is the direct SDK mapping for the brief's create signature.
- delete(forceDeleteWithoutRecovery=false) always sets the flag explicitly on the request (false by default), so the default is asserted in tests.
- Combined enabled condition copied verbatim from the S3 starter: @ConditionalOnProperty(prefix="awskotlin", name=["enabled","<service>.enabled"], havingValue="true", matchIfMissing=true). Service prefixes: awskotlin.secrets-manager and awskotlin.sns.
- Region/endpoint fallback: service property ?: global AwsKotlinProperty; injected CredentialsProvider from core; endpointUrl = Url.parse(endpoint) when present. No forcePathStyle (S3-only concept).
- User client/template beans back off via @ConditionalOnMissingBean. User-client backoff tests use mockk(relaxed = true) so the inferred close() destroy method is stubbed (avoids destroy-method noise).
- JUnit 6 suspend tests use expression-body `= runTest { ... }`; runTest returns TestResult (Unit on JVM) and the lambda is declared () -> Unit, so every @Test returns Unit. Confirmed 0 skipped across all suites.
- Boot 4.2 context assertions use getBeanNamesForType/getBean (no removed hasSingleBean helpers).
- Build workarounds copied from core/s3: module gradle.properties kotlin.compiler.runViaBuildToolsApi=false; kapt applied without version in starters; ktlint compiler pin to 2.2.21 on the lint classpath; kapt additionalMetadataLocations argument; hand-written additional-spring-configuration-metadata.json for constructor-bound properties.
- Service modules use only unit-test deps (kotest/mockk/kotlin-test/coroutines-test); no testcontainers/LocalStack (none required by the brief). Starters add springBootStarterTest.
- Formatting: ran kotlinter formatKotlin to satisfy the intellij_idea ktlint style. ktlint collapses constructor/data-class signatures that fit within max_line_length=120 to a single line (with the codebase's customary trailing comma). SnsProperties (111 chars) and the 2-param SecretOperations/TopicOperations signatures became single-line; SecretsManagerProperties (122 chars single-line) correctly stays multi-line. This is a lint-driven formatting normalization, not a semantic change from the brief.

## Exact commands run (from worktree root)

1) First verification attempt (failed on lint formatting only; main compileKotlin succeeded):
JAVA_HOME="D:/jdks/jdk25" ./gradlew :secrets-manager:test :sns:test :starters:starter-secrets-manager:test :starters:starter-sns:test :secrets-manager:lintKotlin :sns:lintKotlin :starters:starter-secrets-manager:lintKotlin :starters:starter-sns:lintKotlin --console=plain

2) Auto-format to fix ktlint class-signature / final-newline findings:
JAVA_HOME="D:/jdks/jdk25" ./gradlew :secrets-manager:formatKotlin :sns:formatKotlin :starters:starter-secrets-manager:formatKotlin :starters:starter-sns:formatKotlin --console=plain

3) Final verification (PASS):
JAVA_HOME="D:/jdks/jdk25" ./gradlew :secrets-manager:test :sns:test :starters:starter-secrets-manager:test :starters:starter-sns:test :secrets-manager:lintKotlin :sns:lintKotlin :starters:starter-secrets-manager:lintKotlin :starters:starter-sns:lintKotlin --console=plain

## Output summary

- Command 1: BUILD FAILED in 1m 32s — only :secrets-manager:lintKotlinMain and :secrets-manager:lintKotlinTest failed (standard:class-signature single-line expectation + standard:final-newline). :secrets-manager:compileKotlin and :sns:compileKotlin succeeded, confirming the SDK API usage compiles.
- Command 2: BUILD SUCCESSFUL in 19s — formatKotlin fixed class-signature and final-newline across all four modules.
- Command 3: BUILD SUCCESSFUL in 1m 44s — 49 actionable tasks (33 executed, 16 up-to-date). All four test tasks and all four lintKotlin tasks passed.
- Test results (build/test-results/test): SecretsManagerTemplateTest 7/0/0/0, SnsTemplateTest 7/0/0/0, SecretsManagerAutoConfigurationTest 7/0/0/0, SnsAutoConfigurationTest 7/0/0/0 (tests/skipped/failures/errors). Total 28 tests, 0 skipped.
- Test coverage: SM unit (get/create/update/delete request fields, default + forced delete flag, ResourceNotFoundException and SecretsManagerException propagation); SNS unit (publish with/without subject, subscribe, delete, unsubscribe request fields, NotFoundException and SnsException propagation); each starter context (default beans + static credentials/region, service region override, service endpoint override, global endpoint fallback, service disabled, global disabled, user client bean wins).

## Concerns

- None blocking. The only deviation from the brief's literal text is formatting: ktlint's max_line_length=120 forces SecretsManagerProperties to remain multi-line and collapses the shorter signatures to single line; this is required for lint to pass and matches the established S3 style.
- The deprecated kotlin.compiler.runViaBuildToolsApi=false workaround emits a KGP deprecation warning (same as core/s3); it is intentional and required for kapt under KGP 2.4.10 + Gradle 9.9.2, and will need revisiting before Kotlin 2.5.0.
- SNS jar was not pre-cached; it resolves from Maven Central during the build (confirmed available at 1.9.45). A transient .kotlin/ session directory is generated by the build (untracked, harmless).