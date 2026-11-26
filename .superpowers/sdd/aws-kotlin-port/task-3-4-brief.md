# Tasks 3+4 Batch: Secrets Manager and SNS Templates + Starters

Worktree: D:/hantsylabs/aws-sdk-kotlin-spring-example/.worktrees/awskotlin-spring-port
Do not commit. Do not modify examples/list/utilities/aws-build-tool. Do not dispatch subagents.

## Context
Core and S3 modules exist. You add two independent service modules and starters in one batch. Authoritative AWS SDK Kotlin API source: D:/hantsylabs/aws-sdk-kotlin. Verify exact generated exception class names from published jars/Smithy models; do not assume Java SDK names. S3 task proved HeadBucket not-found class is `NotFound`, not `NotFoundException`.

## Global constraints
- Prefix awskotlin; package io.github.hantsy.awskotlin.
- Artifact IDs: awskotlin-spring-secrets-manager, awskotlin-spring-starter-secrets-manager, awskotlin-spring-sns, awskotlin-spring-starter-sns.
- No Java AWS SDK, Spring Cloud, Spring Integration.
- User client/template beans win; global awskotlin.enabled=false and service enabled=false disable beans.
- Use combined enabled condition pattern from S3 starter: @ConditionalOnProperty(prefix="awskotlin", name=["enabled","<service>.enabled"], havingValue="true", matchIfMissing=true) if that is the established pattern; otherwise use equivalent AND semantics.
- JUnit 6: expression-body suspend tests must return Unit (runBlocking<Unit> or runTest returning Unit); avoid silently skipped value-returning @Test methods.

## Build workarounds required
Copy from core/s3:
- module gradle.properties: kotlin.compiler.runViaBuildToolsApi=false
- kapt applied without version where configuration processor used
- ktlint configuration compiler pin to 2.2.21
- kapt additionalMetadataLocations argument
- hand-written META-INF/additional-spring-configuration-metadata.json for constructor-bound properties
- Boot 4.2 context assertions use getBean/BeanFactory APIs, not removed hasSingleBean helpers.

## Files
Modify settings.gradle.kts: add include("secrets-manager"), include("sns"), include("starters:starter-secrets-manager"), include("starters:starter-sns").

Secrets Manager:
- secrets-manager/build.gradle.kts, gradle.properties
- secrets-manager/src/main/kotlin/io/github/hantsy/awskotlin/secretsmanager/SecretsManagerTemplate.kt
- secrets-manager/src/main/kotlin/io/github/hantsy/awskotlin/secretsmanager/SecretOperations.kt
- starters/starter-secrets-manager/build.gradle.kts, gradle.properties
- starters/starter-secrets-manager/src/main/kotlin/io/github/hantsy/awskotlin/secretsmanager/autoconfigure/SecretsManagerProperties.kt
- starters/starter-secrets-manager/src/main/kotlin/io/github/hantsy/awskotlin/secretsmanager/autoconfigure/SecretsManagerAutoConfiguration.kt
- starters/starter-secrets-manager/src/main/resources/META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports
- starters/starter-secrets-manager/src/main/resources/META-INF/additional-spring-configuration-metadata.json
Tests:
- secrets-manager/src/test/kotlin/io/github/hantsy/awskotlin/secretsmanager/SecretsManagerTemplateTest.kt
- starters/starter-secrets-manager/src/test/kotlin/io/github/hantsy/awskotlin/secretsmanager/autoconfigure/SecretsManagerAutoConfigurationTest.kt

SNS:
- sns/build.gradle.kts, gradle.properties
- sns/src/main/kotlin/io/github/hantsy/awskotlin/sns/SnsTemplate.kt
- sns/src/main/kotlin/io/github/hantsy/awskotlin/sns/TopicOperations.kt
- starters/starter-sns/build.gradle.kts, gradle.properties
- starters/starter-sns/src/main/kotlin/io/github/hantsy/awskotlin/sns/autoconfigure/SnsProperties.kt
- starters/starter-sns/src/main/kotlin/io/github/hantsy/awskotlin/sns/autoconfigure/SnsAutoConfiguration.kt
- starters/starter-sns/src/main/resources/META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports
- starters/starter-sns/src/main/resources/META-INF/additional-spring-configuration-metadata.json
Tests:
- sns/src/test/kotlin/io/github/hantsy/awskotlin/sns/SnsTemplateTest.kt
- starters/starter-sns/src/test/kotlin/io/github/hantsy/awskotlin/sns/autoconfigure/SnsAutoConfigurationTest.kt

## Secrets Manager API
```kotlin
class SecretsManagerTemplate(private val client: SecretsManagerClient) {
    fun secret(secretId: String): SecretOperations
}
class SecretOperations internal constructor(private val client: SecretsManagerClient, private val secretId: String) {
    suspend fun get(): GetSecretValueResponse
    suspend fun create(secretValue: String): CreateSecretResponse
    suspend fun update(secretValue: String): UpdateSecretResponse
    suspend fun delete(forceDeleteWithoutRecovery: Boolean = false): DeleteSecretResponse
}
```
Map directly to SDK requests. Propagate SDK exceptions unchanged. Properties:
```kotlin
@ConfigurationProperties(prefix = "awskotlin.secrets-manager")
data class SecretsManagerProperties(val enabled: Boolean = true, val endpoint: String? = null, val region: String? = null)
```
Auto-config creates SecretsManagerClient with region/endpoint fallback and injected CredentialsProvider, then SecretsManagerTemplate. User beans back off.

## SNS API
```kotlin
class SnsTemplate(private val client: SnsClient) {
    fun topic(topicArn: String): TopicOperations
    suspend fun unsubscribe(subscriptionArn: String): UnsubscribeResponse
}
class TopicOperations internal constructor(private val client: SnsClient, private val topicArn: String) {
    suspend fun publish(message: String, subject: String? = null): PublishResponse
    suspend fun subscribe(protocol: String, endpoint: String): SubscribeResponse
    suspend fun delete(): DeleteTopicResponse
}
```
Ruling: topic selector accepts topicArn only; no implicit topic creation/name resolution. Properties:
```kotlin
@ConfigurationProperties(prefix = "awskotlin.sns")
data class SnsProperties(val enabled: Boolean = true, val endpoint: String? = null, val region: String? = null)
```
Auto-config creates SnsClient and SnsTemplate with same fallback/backoff rules.

## Dependencies
Service modules: api(libs.awsSdkKotlinSecretManager) or api(libs.awsSdkKotlinSns), kotlinStdlib/coroutinesCore; tests mockk/kotest/kotlin-test/coroutines-test.
Starters: api(project(":<service>")), api(project(":core")), implementation springBootAutoconfigure; kapt configurationProcessor; tests springBootStarterTest/mockk/kotest.

## Tests required
Secrets Manager unit: get/create/update/delete request fields; SDK exception propagation (use verified exception class).
SNS unit: publish/subscribe/delete/unsubscribe request fields; SDK exception propagation.
Context for each: default beans; endpoint/region override; service disabled; global disabled; user client bean wins (use relaxed mock or stub close to avoid destroy-method noise).

## Verification
JAVA_HOME="D:/jdks/jdk25" ./gradlew :secrets-manager:test :sns:test :starters:starter-secrets-manager:test :starters:starter-sns:test :secrets-manager:lintKotlin :sns:lintKotlin :starters:starter-secrets-manager:lintKotlin :starters:starter-sns:lintKotlin --console=plain
Expected PASS.

## Report
Write full report to .superpowers/sdd/aws-kotlin-port/task-3-4-report.md. Return only status, one-line test summary, concerns.
