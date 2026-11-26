# Task 1: Build Foundation and Core Auto-Configuration

You are implementing the first task of a Kotlin port of Spring Cloud AWS service support using AWS SDK for Kotlin and Spring Boot. Work only in this worktree. Do not commit. Do not modify examples/list/utilities/aws-build-tool. Do not dispatch subagents.

## Global constraints
- AWS SDK for Kotlin 1.9.45; Spring Boot 4.2.0; Kotlin 2.4.10; Java 25; Gradle wrapper 9.9.2.
- Prefix awskotlin; package io.github.hantsy.awskotlin.
- Maven artifact ID for core: awskotlin-spring-core.
- No Java AWS SDK, Spring Cloud, Spring Integration dependencies.
- SDK default credential/region chains with optional overrides.
- Static credentials require both access-key-id and secret-access-key.
- User-provided CredentialsProvider beans win.
- awskotlin.enabled=false disables core auto-configuration.
- Git Bash commands: JAVA_HOME="D:/jdks/jdk25" ./gradlew ...

## Files
Modify:
- settings.gradle.kts
- gradle/libs.versions.toml
Create:
- core/build.gradle.kts
- core/src/main/kotlin/io/github/hantsy/awskotlin/core/AwsKotlinProperties.kt
- core/src/main/kotlin/io/github/hantsy/awskotlin/core/AwsKotlinCredentialsProviderFactory.kt
- core/src/main/kotlin/io/github/hantsy/awskotlin/core/autoconfigure/AwsKotlinAutoConfiguration.kt
- core/src/main/resources/META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports
Test:
- core/src/test/kotlin/io/github/hantsy/awskotlin/core/AwsKotlinCredentialsProviderFactoryTest.kt
- core/src/test/kotlin/io/github/hantsy/awskotlin/core/autoconfigure/AwsKotlinAutoConfigurationTest.kt

## Required changes
1. settings.gradle.kts: keep existing includes and add:
```kotlin
include("core", "s3", "secrets-manager", "sqs", "sns")
include(
    "starters:starter-s3",
    "starters:starter-secrets-manager",
    "starters:starter-sqs",
    "starters:starter-sns"
)
```
Only core has build files in this task; other included directories may be created later. If Gradle fails because included projects lack build files, create minimal placeholder build.gradle.kts files for the other modules only if necessary, but prefer adding only core include now and leave other includes for later tasks if that avoids broken baseline. Ruling: you may add only `include("core")` in this task and add remaining includes in their tasks; record what you did.

2. gradle/libs.versions.toml add libraries:
```toml
awsSdkKotlinSns = { module = "aws.sdk.kotlin:sns", version.ref = "awsSdkKotlin" }
springBootAutoconfigure = { module = "org.springframework.boot:spring-boot-autoconfigure" }
springBootConfigurationProcessor = { module = "org.springframework.boot:spring-boot-configuration-processor" }
springBootStarterTest = { module = "org.springframework.boot:spring-boot-starter-test" }
jacksonDatabind = { module = "tools.jackson.core:jackson-databind" }
```
Add plugin:
```toml
kotlinKapt = { id = "org.jetbrains.kotlin.kapt", version.ref = "kotlin" }
```

3. core/build.gradle.kts:
```kotlin
plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.dependencyManagement)
    alias(libs.plugins.kotlinter)
    alias(libs.plugins.kotlinKapt)
    `java-library`
}
group = "io.github.hantsy"
version = "0.1.0-SNAPSHOT"
base { archivesName.set("awskotlin-spring-core") }
repositories { mavenCentral() }
dependencyManagement {
    imports {
        mavenBom("org.springframework.boot:spring-boot-dependencies:${libs.versions.springboot.get()}")
    }
}
dependencies {
    api(libs.awsSdkKotlinHttp)
    implementation(libs.springBootAutoconfigure)
    implementation(libs.kotlinStdlib)
    implementation(libs.kotlinCoroutinesCore)
    kapt(libs.springBootConfigurationProcessor)
    testImplementation(libs.springBootStarterTest)
    testImplementation(libs.bundles.kotest)
    testImplementation(libs.bundles.mockk)
    testImplementation(libs.bundles.kotlintest)
    testRuntimeOnly(libs.junitPlatformLauncher)
}
kotlin { jvmToolchain(libs.versions.java.get().toInt()) }
tasks.withType<Test> { useJUnitPlatform() }
```
Adjust only if required by Gradle/Kotlin compatibility; record adjustments.

4. AwsKotlinProperties:
```kotlin
@ConfigurationProperties(prefix = "awskotlin")
data class AwsKotlinProperties(
    val enabled: Boolean = true,
    val region: String? = null,
    val endpoint: String? = null,
    val accessKeyId: String? = null,
    val secretAccessKey: String? = null,
    val profileName: String? = null
)
```

5. AwsKotlinCredentialsProviderFactory:
- If either static key is present, require both and return StaticCredentialsProvider DSL.
- Else if profileName present, return ProfileCredentialsProvider(profileName, region).
- Else return DefaultChainCredentialsProvider(region = properties.region).
Use AWS SDK Kotlin types from aws.sdk.kotlin.runtime.auth.credentials and aws.smithy.kotlin.runtime.auth.awscredentials. Do not use Java SDK.

6. AwsKotlinAutoConfiguration:
```kotlin
@AutoConfiguration
@ConditionalOnClass(CredentialsProvider::class)
@ConditionalOnProperty(prefix = "awskotlin", name = ["enabled"], havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties(AwsKotlinProperties::class)
class AwsKotlinAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean(CredentialsProvider::class)
    fun awsKotlinCredentialsProvider(properties: AwsKotlinProperties): CredentialsProvider =
        AwsKotlinCredentialsProviderFactory.create(properties)
}
```
Register in AutoConfiguration.imports.

## Tests required
- static credentials require both keys (missing secret throws IllegalArgumentException)
- static credentials produce StaticCredentialsProvider when both present
- profile override produces ProfileCredentialsProvider
- no overrides produce DefaultChainCredentialsProvider
- context has single CredentialsProvider bean by default
- user CredentialsProvider bean wins
- awskotlin.enabled=false removes auto-configured provider
Use ApplicationContextRunner, MockK, Kotest assertions, JUnit 6. Suspend provider resolution tests may use runBlocking/runTest where needed.

## Verification
Run:
```bash
JAVA_HOME="D:/jdks/jdk25" ./gradlew :core:test :core:lintKotlin
```
Expected PASS. If kapt/configuration processor causes issues, fix minimally and record.

## Report
Write full report to .superpowers/sdd/aws-kotlin-port/task-1-report.md including files changed, decisions, test command/output summary, concerns. Return only status (DONE/DONE_WITH_CONCERNS/NEEDS_CONTEXT/BLOCKED), one-line test summary, concerns. No commits.
