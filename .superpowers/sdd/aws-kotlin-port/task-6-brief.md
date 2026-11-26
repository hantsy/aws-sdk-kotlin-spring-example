# Task 6 — SQS starter auto-configuration

## Context
Worktree: D:/hantsylabs/aws-sdk-kotlin-spring-example/.worktrees/awskotlin-spring-port
No commits authorized. Do not modify examples/list/utilities/aws-build-tool. Do not dispatch subagents.
Task 5 created the pure Kotlin `sqs` module. This task creates `starters/starter-sqs` auto-configuration only. Listener runtime is Task 7; listener auto-config/integration tests are Task 8.

## Global constraints
- Prefix awskotlin; package io.github.hantsy.awskotlin.sqs.autoconfigure; artifact ID awskotlin-spring-starter-sqs.
- Spring Boot 4.2.0; AWS SDK Kotlin 1.9.45; Kotlin 2.4.10; Java 25.
- SDK default credential/region chains with optional overrides; user beans win.
- Combined enabled condition: `@ConditionalOnProperty(prefix="awskotlin", name=["enabled","sqs.enabled"], havingValue="true", matchIfMissing=true)`.
- No Spring Cloud/Spring Integration. No listener runtime in this task.

## Required files
- settings.gradle.kts: add `include("starters:starter-sqs")`.
- starters/starter-sqs/build.gradle.kts and gradle.properties.
- src/main/kotlin/io/github/hantsy/awskotlin/sqs/autoconfigure/SqsProperties.kt
- src/main/kotlin/io/github/hantsy/awskotlin/sqs/autoconfigure/SqsAutoConfiguration.kt
- src/main/resources/META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports
- src/main/resources/META-INF/additional-spring-configuration-metadata.json
- tests under src/test/kotlin/io/github/hantsy/awskotlin/sqs/autoconfigure/

Follow existing starter style: read starters/starter-s3/build.gradle.kts, S3AutoConfiguration.kt, S3Properties.kt, its metadata JSON and tests. Replicate core kapt/ktlint workarounds and gradle.properties exactly. End all text files with newline.

## Build dependencies
- api(project(":sqs"))
- api(project(":core"))
- implementation(libs.springBootAutoconfigure)
- kapt(libs.springBootConfigurationProcessor)
- testImplementation(libs.springBootStarterTest), bundles.kotest, bundles.mockk, bundles.kotlintest, testRuntimeOnly(libs.junitPlatformLauncher)
- kapt additionalMetadataLocations argument pointing at src/main/resources (same as core).
- ktlint compiler pin block and kotlin.compiler.runViaBuildToolsApi=false.

## Properties
```kotlin
@ConfigurationProperties(prefix = "awskotlin.sqs")
data class SqsProperties(
    val enabled: Boolean = true,
    val endpoint: String? = null,
    val region: String? = null,
    val listener: Listener = Listener(),
) {
    data class Listener(
        val autoStartup: Boolean = true,
        val maxConcurrentMessages: Int = 10,
        val pollTime: Duration = 10.seconds,
        val shutdownTimeout: Duration = 20.seconds,
    )
}
```
Use kotlin.time.Duration. Validate nothing in properties; runtime validates later. Metadata JSON must document all constructor-bound properties including nested listener fields, with correct types and defaults. Use kebab-case property names in metadata (awskotlin.sqs.listener.max-concurrent-messages etc.).

## Auto-configuration
```kotlin
@AutoConfiguration(after = [AwsKotlinAutoConfiguration::class])
@ConditionalOnClass(SqsClient::class)
@ConditionalOnProperty(prefix = "awskotlin", name = ["enabled", "sqs.enabled"], havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties(SqsProperties::class)
class SqsAutoConfiguration {
    @Bean @ConditionalOnMissingBean
    fun sqsClient(properties: AwsKotlinProperties, sqsProperties: SqsProperties, credentialsProvider: CredentialsProvider): SqsClient

    @Bean @ConditionalOnMissingBean
    fun sqsMessageConverter(): SqsMessageConverter = JacksonSqsMessageConverter()

    @Bean @ConditionalOnMissingBean
    fun sqsQueueResolver(client: SqsClient): SqsQueueResolver = SqsQueueResolver(client)

    @Bean @ConditionalOnMissingBean
    fun sqsTemplate(client: SqsClient, converter: SqsMessageConverter, queueResolver: SqsQueueResolver): SqsTemplate =
        SqsTemplate(client, converter, queueResolver)
}
```
Client builder:
- region = sqsProperties.region ?: properties.region
- credentialsProvider = injected core bean
- endpoint = sqsProperties.endpoint ?: properties.endpoint; if non-null, endpointUrl = Url.parse(it)
- Do not set S3-specific forcePathStyle.
- Use AWS SDK Kotlin SqsClient DSL, not Java SDK.

## Tests required
Use ApplicationContextRunner with AutoConfigurations.of(AwsKotlinAutoConfiguration::class.java, SqsAutoConfiguration::class.java). JUnit 6 test methods must return Unit.
Cover:
- default context has SqsClient, SqsMessageConverter (Jackson), SqsQueueResolver, SqsTemplate, SqsProperties.
- awskotlin.enabled=false disables all SQS beans.
- awskotlin.sqs.enabled=false disables all SQS beans.
- user SqsClient bean wins (mock SqsClient; close noise acceptable if relaxed).
- user SqsMessageConverter bean wins.
- user SqsQueueResolver bean wins.
- user SqsTemplate bean wins.
- properties bind endpoint/region and listener defaults; custom listener values bind (max-concurrent-messages, poll-time, shutdown-timeout, auto-startup).
- Boot 4.2 AssertableApplicationContext lacks hasSingleBean; use getBean/getBeanNamesForType/BeanFactory assertions.

## Verification
Run from worktree root:
- `./gradlew :starters:starter-sqs:test`
- `./gradlew :starters:starter-sqs:lintKotlin`
Report exact summary. Do not run full examples tests.

## Report contract
Write full report to `.superpowers/sdd/aws-kotlin-port/task-6-report.md`.
Return only status, one-line test summary, concerns.