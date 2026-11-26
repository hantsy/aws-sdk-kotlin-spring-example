# Task 1 review package

## Base
5e56ebb730a4e340125aec61632f9994ee7126bd

## Status
 A core/build.gradle.kts
 A core/gradle.properties
 A core/src/main/kotlin/io/github/hantsy/awskotlin/core/AwsKotlinCredentialsProviderFactory.kt
 A core/src/main/kotlin/io/github/hantsy/awskotlin/core/AwsKotlinProperties.kt
 A core/src/main/kotlin/io/github/hantsy/awskotlin/core/autoconfigure/AwsKotlinAutoConfiguration.kt
 A core/src/main/resources/META-INF/additional-spring-configuration-metadata.json
 A core/src/main/resources/META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports
 A core/src/test/kotlin/io/github/hantsy/awskotlin/core/AwsKotlinCredentialsProviderFactoryTest.kt
 A core/src/test/kotlin/io/github/hantsy/awskotlin/core/autoconfigure/AwsKotlinAutoConfigurationTest.kt
 M gradle/libs.versions.toml
 M settings.gradle.kts
?? .kotlin/
?? .superpowers/

## Diff stat
 core/build.gradle.kts                              | 76 ++++++++++++++++++++++
 core/gradle.properties                             |  5 ++
 .../core/AwsKotlinCredentialsProviderFactory.kt    | 35 ++++++++++
 .../hantsy/awskotlin/core/AwsKotlinProperties.kt   | 18 +++++
 .../autoconfigure/AwsKotlinAutoConfiguration.kt    | 28 ++++++++
 .../additional-spring-configuration-metadata.json  | 41 ++++++++++++
 ...rk.boot.autoconfigure.AutoConfiguration.imports |  1 +
 .../AwsKotlinCredentialsProviderFactoryTest.kt     | 61 +++++++++++++++++
 .../AwsKotlinAutoConfigurationTest.kt              | 72 ++++++++++++++++++++
 gradle/libs.versions.toml                          |  9 +++
 settings.gradle.kts                                |  1 +
 11 files changed, 347 insertions(+)

## Full diff
diff --git a/core/build.gradle.kts b/core/build.gradle.kts
new file mode 100644
index 0000000..73c36cc
--- /dev/null
+++ b/core/build.gradle.kts
@@ -0,0 +1,76 @@
+plugins {
+    alias(libs.plugins.kotlinJvm)
+    alias(libs.plugins.dependencyManagement)
+    alias(libs.plugins.kotlinter)
+    // kapt ships inside kotlin-gradle-plugin (already on the classpath via the root build),
+    // so it must be applied without an explicit version.
+    id("org.jetbrains.kotlin.kapt")
+    `java-library`
+}
+
+group = "io.github.hantsy"
+version = "0.1.0-SNAPSHOT"
+
+base { archivesName.set("awskotlin-spring-core") }
+
+repositories { mavenCentral() }
+
+dependencyManagement {
+    imports {
+        mavenBom("org.springframework.boot:spring-boot-dependencies:${libs.versions.springboot.get()}")
+    }
+}
+
+dependencies {
+    api(libs.awsSdkKotlinHttp)
+    // aws-http does not transitively expose aws-config, which contains the credentials providers.
+    api(libs.awsSdkKotlinConfig)
+    implementation(libs.springBootAutoconfigure)
+    implementation(libs.kotlinStdlib)
+    implementation(libs.kotlinCoroutinesCore)
+    kapt(libs.springBootConfigurationProcessor)
+    testImplementation(libs.springBootStarterTest)
+    testImplementation(libs.bundles.kotest)
+    testImplementation(libs.bundles.mockk)
+    testImplementation(libs.bundles.kotlintest)
+    testRuntimeOnly(libs.junitPlatformLauncher)
+}
+
+kotlin { jvmToolchain(libs.versions.java.get().toInt()) }
+
+// kapt's CLASS_OUTPUT layout defeats the processor's Gradle resources-directory heuristic,
+// so point it at the source resources for additional-spring-configuration-metadata.json.
+// The additional file documents the constructor-bound properties that the processor cannot
+// infer from Kotlin stubs (val properties have no setters; the synthetic no-arg constructor
+// generated for all-default parameters breaks single-constructor deduction).
+kapt {
+    arguments {
+        arg(
+            "org.springframework.boot.configurationprocessor.additionalMetadataLocations",
+            "${projectDir}/src/main/resources",
+        )
+    }
+}
+
+// ktlint 1.8.0 (applied by kotlinter) requires kotlin-compiler-embeddable 2.2.x.
+// A resolution rule in this build aligns Kotlin artifacts to Gradle's embedded 2.4.21,
+// which breaks ktlint ("Extensions storage is not registered"). Pin the compiler
+// artifacts for the lint classpath only; compilation itself still uses Kotlin 2.4.10.
+configurations.named("ktlint") {
+    resolutionStrategy.eachDependency {
+        if (requested.group == "org.jetbrains.kotlin" &&
+            requested.name in
+            setOf(
+                "kotlin-compiler-embeddable",
+                "kotlin-daemon-embeddable",
+                "kotlin-script-runtime",
+                "kotlin-build-tools-api",
+            )
+        ) {
+            useVersion("2.2.21")
+            because("ktlint 1.8.0 is not compatible with kotlin-compiler-embeddable 2.4.x")
+        }
+    }
+}
+
+tasks.withType<Test> { useJUnitPlatform() }
\ No newline at end of file
diff --git a/core/gradle.properties b/core/gradle.properties
new file mode 100644
index 0000000..92b73fb
--- /dev/null
+++ b/core/gradle.properties
@@ -0,0 +1,5 @@
+# Workaround for KGP 2.4.10 + Gradle 9.9.2: Gradle ships Kotlin 2.4.21 compiler classes
+# in its parent classloader, which breaks the Build Tools API path used by kapt stub
+# generation (NoSuchMethodError: ArgumentsToStrings.toArgumentStrings$default).
+# The legacy compiler runner avoids that path. Deprecated in KGP 2.4.x, removed in 2.5.0.
+kotlin.compiler.runViaBuildToolsApi=false
\ No newline at end of file
diff --git a/core/src/main/kotlin/io/github/hantsy/awskotlin/core/AwsKotlinCredentialsProviderFactory.kt b/core/src/main/kotlin/io/github/hantsy/awskotlin/core/AwsKotlinCredentialsProviderFactory.kt
new file mode 100644
index 0000000..cb0c185
--- /dev/null
+++ b/core/src/main/kotlin/io/github/hantsy/awskotlin/core/AwsKotlinCredentialsProviderFactory.kt
@@ -0,0 +1,35 @@
+package io.github.hantsy.awskotlin.core
+
+import aws.sdk.kotlin.runtime.auth.credentials.DefaultChainCredentialsProvider
+import aws.sdk.kotlin.runtime.auth.credentials.ProfileCredentialsProvider
+import aws.sdk.kotlin.runtime.auth.credentials.StaticCredentialsProvider
+import aws.smithy.kotlin.runtime.auth.awscredentials.CredentialsProvider
+
+/**
+ * Creates an AWS SDK for Kotlin [CredentialsProvider] from [AwsKotlinProperties].
+ *
+ * Resolution order:
+ * 1. Static credentials when either key is configured (both keys are then required).
+ * 2. Profile credentials when [AwsKotlinProperties.profileName] is configured.
+ * 3. The SDK [DefaultChainCredentialsProvider] otherwise.
+ */
+object AwsKotlinCredentialsProviderFactory {
+    fun create(properties: AwsKotlinProperties): CredentialsProvider {
+        val accessKeyId = properties.accessKeyId
+        val secretAccessKey = properties.secretAccessKey
+        if (accessKeyId != null || secretAccessKey != null) {
+            require(accessKeyId != null && secretAccessKey != null) {
+                "awskotlin.access-key-id and awskotlin.secret-access-key must both be configured for static credentials"
+            }
+            return StaticCredentialsProvider {
+                this.accessKeyId = accessKeyId
+                this.secretAccessKey = secretAccessKey
+            }
+        }
+        val profileName = properties.profileName
+        if (profileName != null) {
+            return ProfileCredentialsProvider(profileName = profileName, region = properties.region)
+        }
+        return DefaultChainCredentialsProvider(region = properties.region)
+    }
+}
diff --git a/core/src/main/kotlin/io/github/hantsy/awskotlin/core/AwsKotlinProperties.kt b/core/src/main/kotlin/io/github/hantsy/awskotlin/core/AwsKotlinProperties.kt
new file mode 100644
index 0000000..8700bd4
--- /dev/null
+++ b/core/src/main/kotlin/io/github/hantsy/awskotlin/core/AwsKotlinProperties.kt
@@ -0,0 +1,18 @@
+package io.github.hantsy.awskotlin.core
+
+import org.springframework.boot.context.properties.ConfigurationProperties
+
+/**
+ * Shared configuration properties for AWS SDK for Kotlin based Spring modules.
+ *
+ * When no overrides are configured, the SDK default credential and region chains are used.
+ */
+@ConfigurationProperties(prefix = "awskotlin")
+data class AwsKotlinProperties(
+    val enabled: Boolean = true,
+    val region: String? = null,
+    val endpoint: String? = null,
+    val accessKeyId: String? = null,
+    val secretAccessKey: String? = null,
+    val profileName: String? = null,
+)
diff --git a/core/src/main/kotlin/io/github/hantsy/awskotlin/core/autoconfigure/AwsKotlinAutoConfiguration.kt b/core/src/main/kotlin/io/github/hantsy/awskotlin/core/autoconfigure/AwsKotlinAutoConfiguration.kt
new file mode 100644
index 0000000..2d7773a
--- /dev/null
+++ b/core/src/main/kotlin/io/github/hantsy/awskotlin/core/autoconfigure/AwsKotlinAutoConfiguration.kt
@@ -0,0 +1,28 @@
+package io.github.hantsy.awskotlin.core.autoconfigure
+
+import aws.smithy.kotlin.runtime.auth.awscredentials.CredentialsProvider
+import io.github.hantsy.awskotlin.core.AwsKotlinCredentialsProviderFactory
+import io.github.hantsy.awskotlin.core.AwsKotlinProperties
+import org.springframework.boot.autoconfigure.AutoConfiguration
+import org.springframework.boot.autoconfigure.condition.ConditionalOnClass
+import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
+import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
+import org.springframework.boot.context.properties.EnableConfigurationProperties
+import org.springframework.context.annotation.Bean
+
+/**
+ * Auto-configuration shared by all AWS SDK for Kotlin Spring modules.
+ *
+ * Provides a [CredentialsProvider] backed by the SDK default chain unless overrides are
+ * configured via [AwsKotlinProperties]. A user-provided [CredentialsProvider] bean always wins.
+ */
+@AutoConfiguration
+@ConditionalOnClass(CredentialsProvider::class)
+@ConditionalOnProperty(prefix = "awskotlin", name = ["enabled"], havingValue = "true", matchIfMissing = true)
+@EnableConfigurationProperties(AwsKotlinProperties::class)
+class AwsKotlinAutoConfiguration {
+    @Bean
+    @ConditionalOnMissingBean(CredentialsProvider::class)
+    fun awsKotlinCredentialsProvider(properties: AwsKotlinProperties): CredentialsProvider =
+        AwsKotlinCredentialsProviderFactory.create(properties)
+}
diff --git a/core/src/main/resources/META-INF/additional-spring-configuration-metadata.json b/core/src/main/resources/META-INF/additional-spring-configuration-metadata.json
new file mode 100644
index 0000000..9ec5b49
--- /dev/null
+++ b/core/src/main/resources/META-INF/additional-spring-configuration-metadata.json
@@ -0,0 +1,41 @@
+{
+  "properties": [
+    {
+      "name": "awskotlin.enabled",
+      "type": "java.lang.Boolean",
+      "description": "Whether AWS SDK for Kotlin auto-configuration is enabled.",
+      "sourceType": "io.github.hantsy.awskotlin.core.AwsKotlinProperties",
+      "defaultValue": true
+    },
+    {
+      "name": "awskotlin.region",
+      "type": "java.lang.String",
+      "description": "Override the AWS region. When unset, the SDK default region chain is used.",
+      "sourceType": "io.github.hantsy.awskotlin.core.AwsKotlinProperties"
+    },
+    {
+      "name": "awskotlin.endpoint",
+      "type": "java.lang.String",
+      "description": "Override the AWS service endpoint URL (for example, a LocalStack endpoint).",
+      "sourceType": "io.github.hantsy.awskotlin.core.AwsKotlinProperties"
+    },
+    {
+      "name": "awskotlin.access-key-id",
+      "type": "java.lang.String",
+      "description": "Static AWS access key ID. Must be configured together with awskotlin.secret-access-key.",
+      "sourceType": "io.github.hantsy.awskotlin.core.AwsKotlinProperties"
+    },
+    {
+      "name": "awskotlin.secret-access-key",
+      "type": "java.lang.String",
+      "description": "Static AWS secret access key. Must be configured together with awskotlin.access-key-id.",
+      "sourceType": "io.github.hantsy.awskotlin.core.AwsKotlinProperties"
+    },
+    {
+      "name": "awskotlin.profile-name",
+      "type": "java.lang.String",
+      "description": "AWS shared config profile name used to resolve credentials.",
+      "sourceType": "io.github.hantsy.awskotlin.core.AwsKotlinProperties"
+    }
+  ]
+}
\ No newline at end of file
diff --git a/core/src/main/resources/META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports b/core/src/main/resources/META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports
new file mode 100644
index 0000000..f8b281e
--- /dev/null
+++ b/core/src/main/resources/META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports
@@ -0,0 +1 @@
+io.github.hantsy.awskotlin.core.autoconfigure.AwsKotlinAutoConfiguration
\ No newline at end of file
diff --git a/core/src/test/kotlin/io/github/hantsy/awskotlin/core/AwsKotlinCredentialsProviderFactoryTest.kt b/core/src/test/kotlin/io/github/hantsy/awskotlin/core/AwsKotlinCredentialsProviderFactoryTest.kt
new file mode 100644
index 0000000..dfcbb84
--- /dev/null
+++ b/core/src/test/kotlin/io/github/hantsy/awskotlin/core/AwsKotlinCredentialsProviderFactoryTest.kt
@@ -0,0 +1,61 @@
+package io.github.hantsy.awskotlin.core
+
+import aws.sdk.kotlin.runtime.auth.credentials.DefaultChainCredentialsProvider
+import aws.sdk.kotlin.runtime.auth.credentials.ProfileCredentialsProvider
+import aws.sdk.kotlin.runtime.auth.credentials.StaticCredentialsProvider
+import io.kotest.assertions.throwables.shouldThrow
+import io.kotest.matchers.shouldBe
+import io.kotest.matchers.types.shouldBeInstanceOf
+import kotlinx.coroutines.runBlocking
+import org.junit.jupiter.api.Test
+
+class AwsKotlinCredentialsProviderFactoryTest {
+    @Test
+    fun `static credentials require both access key id and secret access key`() {
+        shouldThrow<IllegalArgumentException> {
+            AwsKotlinCredentialsProviderFactory.create(AwsKotlinProperties(accessKeyId = "test-access-key"))
+        }
+        shouldThrow<IllegalArgumentException> {
+            AwsKotlinCredentialsProviderFactory.create(AwsKotlinProperties(secretAccessKey = "test-secret-key"))
+        }
+    }
+
+    @Test
+    fun `static credentials produce StaticCredentialsProvider when both keys are present`() {
+        val provider =
+            AwsKotlinCredentialsProviderFactory.create(
+                AwsKotlinProperties(accessKeyId = "test-access-key", secretAccessKey = "test-secret-key"),
+            )
+        provider.shouldBeInstanceOf<StaticCredentialsProvider>()
+        val credentials = runBlocking { provider.resolve() }
+        credentials.accessKeyId shouldBe "test-access-key"
+        credentials.secretAccessKey shouldBe "test-secret-key"
+    }
+
+    @Test
+    fun `profile override produces ProfileCredentialsProvider`() {
+        val provider =
+            AwsKotlinCredentialsProviderFactory.create(
+                AwsKotlinProperties(profileName = "test-profile", region = "us-east-1"),
+            )
+        val profileProvider = provider.shouldBeInstanceOf<ProfileCredentialsProvider>()
+        profileProvider.profileName shouldBe "test-profile"
+        profileProvider.region shouldBe "us-east-1"
+        profileProvider.close()
+    }
+
+    @Test
+    fun `no overrides produce DefaultChainCredentialsProvider`() {
+        val provider = AwsKotlinCredentialsProviderFactory.create(AwsKotlinProperties())
+        val chain = provider.shouldBeInstanceOf<DefaultChainCredentialsProvider>()
+        chain.close()
+    }
+
+    @Test
+    fun `region override is passed to DefaultChainCredentialsProvider`() {
+        val provider = AwsKotlinCredentialsProviderFactory.create(AwsKotlinProperties(region = "eu-west-1"))
+        val chain = provider.shouldBeInstanceOf<DefaultChainCredentialsProvider>()
+        chain.region shouldBe "eu-west-1"
+        chain.close()
+    }
+}
diff --git a/core/src/test/kotlin/io/github/hantsy/awskotlin/core/autoconfigure/AwsKotlinAutoConfigurationTest.kt b/core/src/test/kotlin/io/github/hantsy/awskotlin/core/autoconfigure/AwsKotlinAutoConfigurationTest.kt
new file mode 100644
index 0000000..0461140
--- /dev/null
+++ b/core/src/test/kotlin/io/github/hantsy/awskotlin/core/autoconfigure/AwsKotlinAutoConfigurationTest.kt
@@ -0,0 +1,72 @@
+package io.github.hantsy.awskotlin.core.autoconfigure
+
+import aws.sdk.kotlin.runtime.auth.credentials.DefaultChainCredentialsProvider
+import aws.sdk.kotlin.runtime.auth.credentials.StaticCredentialsProvider
+import aws.smithy.kotlin.runtime.auth.awscredentials.CredentialsProvider
+import io.kotest.matchers.nulls.shouldNotBeNull
+import io.kotest.matchers.shouldBe
+import io.kotest.matchers.types.shouldBeInstanceOf
+import io.kotest.matchers.types.shouldBeSameInstanceAs
+import io.mockk.mockk
+import kotlinx.coroutines.runBlocking
+import org.junit.jupiter.api.Test
+import org.springframework.boot.autoconfigure.AutoConfigurations
+import org.springframework.boot.test.context.runner.ApplicationContextRunner
+import org.springframework.context.annotation.Bean
+import org.springframework.context.annotation.Configuration
+
+class AwsKotlinAutoConfigurationTest {
+    private val runner =
+        ApplicationContextRunner().withConfiguration(AutoConfigurations.of(AwsKotlinAutoConfiguration::class.java))
+
+    @Test
+    fun `context has single CredentialsProvider bean by default`() {
+        runner.run { context ->
+            context.getBeanNamesForType(CredentialsProvider::class.java).size shouldBe 1
+            context.getBean(CredentialsProvider::class.java).shouldBeInstanceOf<DefaultChainCredentialsProvider>()
+        }
+    }
+
+    @Test
+    fun `user CredentialsProvider bean wins`() {
+        runner.withUserConfiguration(UserCredentialsConfiguration::class.java).run { context ->
+            context.getBeanNamesForType(CredentialsProvider::class.java).size shouldBe 1
+            val userBean = context.getBean("userCredentialsProvider")
+            context.getBean(CredentialsProvider::class.java) shouldBeSameInstanceAs userBean
+        }
+    }
+
+    @Test
+    fun `awskotlin enabled=false removes auto-configured provider`() {
+        runner.withPropertyValues("awskotlin.enabled=false").run { context ->
+            context.getBeanNamesForType(CredentialsProvider::class.java).isEmpty() shouldBe true
+        }
+    }
+
+    @Test
+    fun `static properties produce StaticCredentialsProvider bean`() {
+        runner.withPropertyValues(
+            "awskotlin.access-key-id=test-access-key",
+            "awskotlin.secret-access-key=test-secret-key",
+        ).run { context ->
+            val provider = context.getBean(CredentialsProvider::class.java)
+            provider.shouldBeInstanceOf<StaticCredentialsProvider>()
+            val credentials = runBlocking { provider.resolve() }
+            credentials.accessKeyId shouldBe "test-access-key"
+            credentials.secretAccessKey shouldBe "test-secret-key"
+        }
+    }
+
+    @Test
+    fun `static properties missing secret access key fail context startup`() {
+        runner.withPropertyValues("awskotlin.access-key-id=test-access-key").run { context ->
+            context.startupFailure.shouldNotBeNull()
+        }
+    }
+}
+
+@Configuration(proxyBeanMethods = false)
+class UserCredentialsConfiguration {
+    @Bean
+    fun userCredentialsProvider(): CredentialsProvider = mockk()
+}
diff --git a/gradle/libs.versions.toml b/gradle/libs.versions.toml
index 3400e81..f63ccf4 100644
--- a/gradle/libs.versions.toml
+++ b/gradle/libs.versions.toml
@@ -28,23 +28,31 @@ kotlinStdlib = { module = "org.jetbrains.kotlin:kotlin-stdlib" }
 kotlinCoroutinesCore = { module = "org.jetbrains.kotlinx:kotlinx-coroutines-core", version.ref = "kotlinCoroutines" }
 kotlinCoroutinesReactor = { module = "org.jetbrains.kotlinx:kotlinx-coroutines-reactor", version.ref = "kotlinCoroutines" }
 
 # okhttp
 okhttp = { module = "com.squareup.okhttp3:okhttp", version.ref = "okhttp" }
 okhttpCoroutines = { module = "com.squareup.okhttp3:okhttp-coroutines", version.ref = "okhttp" }
 okhttpLoggingInterceptor = { module = "com.squareup.okhttp3:logging-interceptor", version.ref = "okhttp" }
 
 # AWS SDK for Kotlin
 awsSdkKotlinHttp = { module = "aws.sdk.kotlin:aws-http", version.ref = "awsSdkKotlin" }
+awsSdkKotlinConfig = { module = "aws.sdk.kotlin:aws-config", version.ref = "awsSdkKotlin" }
 awsSdkKotlinS3 = { module = "aws.sdk.kotlin:s3", version.ref = "awsSdkKotlin" }
 awsSdkKotlinSecretManager = { module = "aws.sdk.kotlin:secretsmanager", version.ref = "awsSdkKotlin" }
 awsSqs = { module = "aws.sdk.kotlin:sqs", version.ref = "awsSdkKotlin" }
+awsSdkKotlinSns = { module = "aws.sdk.kotlin:sns", version.ref = "awsSdkKotlin" }
+
+# Spring Boot auto-configuration support
+springBootAutoconfigure = { module = "org.springframework.boot:spring-boot-autoconfigure" }
+springBootConfigurationProcessor = { module = "org.springframework.boot:spring-boot-configuration-processor" }
+springBootStarterTest = { module = "org.springframework.boot:spring-boot-starter-test" }
+jacksonDatabind = { module = "tools.jackson.core:jackson-databind" }
 
 # Test dependencies
 webfluxTest = { module = "org.springframework.boot:spring-boot-starter-webflux-test" }
 reactorTest = { module = "io.projectreactor:reactor-test" }
 testcontainersJunitJupiter = { module = "org.testcontainers:testcontainers-junit-jupiter" }
 testcontainersLocalstack = { module = "org.testcontainers:testcontainers-localstack" }
 springBootTestcontainers = { module = "org.springframework.boot:spring-boot-testcontainers" }
 kotlinTestJunit5 = { module = "org.jetbrains.kotlin:kotlin-test-junit5" }
 kotlinCoroutinesTest = { module = "org.jetbrains.kotlinx:kotlinx-coroutines-test", version.ref = "kotlinCoroutines" }
 kotestAssertionsCoreJvm = { module = "io.kotest:kotest-assertions-core-jvm", version.ref = "kotest" }
@@ -75,13 +83,14 @@ testcore = ["webfluxTest", "reactorTest", "kotlinCoroutinesTest"]
 testcontainers = ["testcontainersJunitJupiter", "testcontainersLocalstack", "springBootTestcontainers"]
 kotest = ["kotestAssertionsCoreJvm"]
 mockk = ["mockk", "springmockk"]
 awaitility = ["awaitility", "awaitilityKotlin"]
 kotlintest = ["kotlinTestJunit5"]
 
 [plugins]
 springBoot = { id = "org.springframework.boot", version.ref = "springboot" }
 dependencyManagement = { id = "io.spring.dependency-management", version.ref = "springDependencyManagement" }
 kotlinJvm = { id = "org.jetbrains.kotlin.jvm", version.ref = "kotlin" }
+kotlinKapt = { id = "org.jetbrains.kotlin.kapt", version.ref = "kotlin" }
 kotlinPluginSpring = { id = "org.jetbrains.kotlin.plugin.spring", version.ref = "kotlin" }
 testLogger = { id = "com.adarshr.test-logger", version.ref = "testLogger" }
 kotlinter = { id = "org.jmailen.kotlinter", version.ref = "kotlinter" }
diff --git a/settings.gradle.kts b/settings.gradle.kts
index a2d0cf9..c53b6ef 100644
--- a/settings.gradle.kts
+++ b/settings.gradle.kts
@@ -11,10 +11,11 @@ pluginManagement {
     includeBuild("aws-build-tool")
 }
 
 plugins {
     // Apply the foojay-resolver plugin to allow automatic download of JDKs
     id("org.gradle.toolchains.foojay-resolver-convention") version "0.10.0"
 }
 
 rootProject.name = "aws-sdk-kotlin-spring"
 include( "list", "utilities", "examples")
+include("core")
