# Task 2: S3 Template and Starter

Worktree: D:/hantsylabs/aws-sdk-kotlin-spring-example/.worktrees/awskotlin-spring-port
Do not commit. Do not modify examples/list/utilities/aws-build-tool. Do not dispatch subagents.

## Context
Task 1 created core module with AwsKotlinProperties and CredentialsProvider auto-configuration. You add the S3 service module and fine-grained S3 starter. Authoritative AWS SDK Kotlin API source: D:/hantsylabs/aws-sdk-kotlin. Generated service sources are not vendored; verify operations from codegen/sdk/aws-models/s3.json and services/s3 usage. Published dependency: aws.sdk.kotlin:s3:1.9.45.

## Global constraints
- Prefix awskotlin; package io.github.hantsy.awskotlin; artifact IDs awskotlin-spring-s3 and awskotlin-spring-starter-s3.
- No Java AWS SDK, Spring Cloud, Spring Integration.
- User S3Client/S3Template beans win. Global awskotlin.enabled=false and awskotlin.s3.enabled=false disable beans.
- S3 path-style defaults true when endpoint override exists unless explicitly configured.
- S3 existence returns false only for NotFound; other SDK errors propagate.
- LocalStack tests skip cleanly without Docker or LOCALSTACK_AUTH_TOKEN.

## Build workarounds required (verified in Task 1)
For each new module with kapt/kotlinter:
- Add module gradle.properties with `kotlin.compiler.runViaBuildToolsApi=false`.
- Apply kapt as `id("org.jetbrains.kotlin.kapt")` without version.
- Copy the ktlint configuration resolutionStrategy pin from core/build.gradle.kts (kotlin-compiler-embeddable, kotlin-daemon-embeddable, kotlin-script-runtime, kotlin-build-tools-api -> 2.2.21).
- Copy kapt additionalMetadataLocations argument if using configuration processor.
- Kotlin constructor-bound properties need hand-written META-INF/additional-spring-configuration-metadata.json mirroring generated metadata.
- Boot 4.2 test assertions: ApplicationContextRunner AssertableApplicationContext no longer has hasSingleBean; use getBean / BeanFactory assertions.

## Files
Modify:
- settings.gradle.kts: add include("s3") and include("starters:starter-s3")
Create:
- s3/build.gradle.kts
- s3/gradle.properties
- s3/src/main/kotlin/io/github/hantsy/awskotlin/s3/S3Template.kt
- s3/src/main/kotlin/io/github/hantsy/awskotlin/s3/S3BucketOperations.kt
- s3/src/main/kotlin/io/github/hantsy/awskotlin/s3/S3ObjectOperations.kt
- starters/starter-s3/build.gradle.kts
- starters/starter-s3/gradle.properties
- starters/starter-s3/src/main/kotlin/io/github/hantsy/awskotlin/s3/autoconfigure/S3Properties.kt
- starters/starter-s3/src/main/kotlin/io/github/hantsy/awskotlin/s3/autoconfigure/S3AutoConfiguration.kt
- starters/starter-s3/src/main/resources/META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports
- starters/starter-s3/src/main/resources/META-INF/additional-spring-configuration-metadata.json
Test:
- s3/src/test/kotlin/io/github/hantsy/awskotlin/s3/S3TemplateTest.kt
- starters/starter-s3/src/test/kotlin/io/github/hantsy/awskotlin/s3/autoconfigure/S3AutoConfigurationTest.kt
- s3/src/test/kotlin/io/github/hantsy/awskotlin/s3/S3TemplateLocalStackTest.kt

## Public API
```kotlin
class S3Template(private val client: S3Client) {
    fun bucket(bucket: String): S3BucketOperations
}
class S3BucketOperations internal constructor(private val client: S3Client, private val bucket: String) {
    suspend fun exists(): Boolean
    suspend fun listObjects(prefix: String? = null, maxKeys: Int? = null): ListObjectsV2Response
    fun object(key: String): S3ObjectOperations
}
class S3ObjectOperations internal constructor(private val client: S3Client, private val bucket: String, private val key: String) {
    suspend fun put(body: ByteStream, contentType: String? = null): PutObjectResponse
    suspend fun get(): ByteArray?
    suspend fun delete(): DeleteObjectResponse
}
```
Use aws.smithy.kotlin.runtime.content.ByteStream. get must consume body inside client.getObject(request) { it.body?.toByteArray() }. exists uses headBucket and catches only aws.sdk.kotlin.services.s3.model.NotFoundException. listObjects returns one SDK page, no pagination. put/delete map directly to SDK requests. Do not catch other SDK exceptions.

## Properties and auto-config
```kotlin
@ConfigurationProperties(prefix = "awskotlin.s3")
data class S3Properties(
    val enabled: Boolean = true,
    val endpoint: String? = null,
    val region: String? = null,
    val pathStyleAccessEnabled: Boolean? = null
)
```
S3AutoConfiguration:
- @AutoConfiguration(after = [AwsKotlinAutoConfiguration::class])
- @ConditionalOnClass(S3Client::class)
- global and service enabled conditions (matchIfMissing true)
- @EnableConfigurationProperties(S3Properties::class)
- @Bean @ConditionalOnMissingBean S3Client: region = s3.region ?: global.region; credentialsProvider injected; endpointUrl = (s3.endpoint ?: global.endpoint)?.let { Url.parse(it) }; forcePathStyle = s3.pathStyleAccessEnabled ?: (endpoint != null)
- @Bean @ConditionalOnMissingBean S3Template(client)
Use aws.smithy.kotlin.runtime.net.url.Url.

## Dependencies
s3 module: api(libs.awsSdkKotlinS3), implementation kotlinStdlib/coroutinesCore; test mockk/kotest/kotlin-test/coroutines-test/springBootStarterTest? For LocalStack: testImplementation bundles.testcontainers, awaitility if needed, junitPlatformLauncher.
starter-s3: api(project(":s3")), api(project(":core")), implementation springBootAutoconfigure; kapt configurationProcessor; test springBootStarterTest, mockk, kotest.

## Tests required
Unit:
- bucket exists true on successful headBucket
- bucket exists false on NotFoundException
- other headBucket exception propagates
- listObjects returns SDK response and passes prefix/maxKeys
- put builds PutObjectRequest with bucket/key/body/contentType
- delete builds DeleteObjectRequest
- get returns bytes via response-transform (mock transform if practical; otherwise cover in LocalStack test and record)
Context:
- default S3Client/S3Template beans with static credentials and region
- endpoint override enables forcePathStyle (inspect client config if accessible; otherwise assert bean creation and record limitation)
- explicit path-style false respected
- service disabled removes beans
- global disabled removes beans
- user S3Client bean wins
LocalStack:
- skip unless Docker available and LOCALSTACK_AUTH_TOKEN present
- create bucket, put/get/delete/list/exists round trip

## Verification
JAVA_HOME="D:/jdks/jdk25" ./gradlew :s3:test :starters:starter-s3:test :s3:lintKotlin :starters:starter-s3:lintKotlin --console=plain
Expected PASS; LocalStack skipped if unavailable.

## Report
Write full report to .superpowers/sdd/aws-kotlin-port/task-2-report.md. Return only status, one-line test summary, concerns.
