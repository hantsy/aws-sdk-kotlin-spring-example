# Task 6 review package

## git status
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
 A s3/build.gradle.kts
 A s3/gradle.properties
 A s3/src/main/kotlin/io/github/hantsy/awskotlin/s3/S3BucketOperations.kt
 A s3/src/main/kotlin/io/github/hantsy/awskotlin/s3/S3ObjectOperations.kt
 A s3/src/main/kotlin/io/github/hantsy/awskotlin/s3/S3Template.kt
 A s3/src/test/kotlin/io/github/hantsy/awskotlin/s3/S3TemplateLocalStackTest.kt
 A s3/src/test/kotlin/io/github/hantsy/awskotlin/s3/S3TemplateTest.kt
 A secrets-manager/build.gradle.kts
 A secrets-manager/gradle.properties
 A secrets-manager/src/main/kotlin/io/github/hantsy/awskotlin/secretsmanager/SecretOperations.kt
 A secrets-manager/src/main/kotlin/io/github/hantsy/awskotlin/secretsmanager/SecretsManagerTemplate.kt
 A secrets-manager/src/test/kotlin/io/github/hantsy/awskotlin/secretsmanager/SecretsManagerTemplateTest.kt
 M settings.gradle.kts
 A sns/build.gradle.kts
 A sns/gradle.properties
 A sns/src/main/kotlin/io/github/hantsy/awskotlin/sns/SnsTemplate.kt
 A sns/src/main/kotlin/io/github/hantsy/awskotlin/sns/TopicOperations.kt
 A sns/src/test/kotlin/io/github/hantsy/awskotlin/sns/SnsTemplateTest.kt
 A sqs/build.gradle.kts
 A sqs/gradle.properties
 A sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/DefaultSqsDestinationOperations.kt
 A sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/JacksonSqsMessageConverter.kt
 A sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/SqsAcknowledgementMode.kt
 A sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/SqsDestinationOperations.kt
 A sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/SqsMessage.kt
 A sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/SqsMessageConverter.kt
 A sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/SqsQueueResolver.kt
 A sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/SqsTemplate.kt
 A sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/SqsTemplateException.kt
 A sqs/src/test/kotlin/io/github/hantsy/awskotlin/sqs/JacksonSqsMessageConverterTest.kt
 A sqs/src/test/kotlin/io/github/hantsy/awskotlin/sqs/SqsQueueResolverTest.kt
 A sqs/src/test/kotlin/io/github/hantsy/awskotlin/sqs/SqsTemplateTest.kt
 A starters/starter-s3/build.gradle.kts
 A starters/starter-s3/gradle.properties
 A starters/starter-s3/src/main/kotlin/io/github/hantsy/awskotlin/s3/autoconfigure/S3AutoConfiguration.kt
 A starters/starter-s3/src/main/kotlin/io/github/hantsy/awskotlin/s3/autoconfigure/S3Properties.kt
 A starters/starter-s3/src/main/resources/META-INF/additional-spring-configuration-metadata.json
 A starters/starter-s3/src/main/resources/META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports
 A starters/starter-s3/src/test/kotlin/io/github/hantsy/awskotlin/s3/autoconfigure/S3AutoConfigurationTest.kt
 A starters/starter-secrets-manager/build.gradle.kts
 A starters/starter-secrets-manager/gradle.properties
 A starters/starter-secrets-manager/src/main/kotlin/io/github/hantsy/awskotlin/secretsmanager/autoconfigure/SecretsManagerAutoConfiguration.kt
 A starters/starter-secrets-manager/src/main/kotlin/io/github/hantsy/awskotlin/secretsmanager/autoconfigure/SecretsManagerProperties.kt
 A starters/starter-secrets-manager/src/main/resources/META-INF/additional-spring-configuration-metadata.json
 A starters/starter-secrets-manager/src/main/resources/META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports
 A starters/starter-secrets-manager/src/test/kotlin/io/github/hantsy/awskotlin/secretsmanager/autoconfigure/SecretsManagerAutoConfigurationTest.kt
 A starters/starter-sns/build.gradle.kts
 A starters/starter-sns/gradle.properties
 A starters/starter-sns/src/main/kotlin/io/github/hantsy/awskotlin/sns/autoconfigure/SnsAutoConfiguration.kt
 A starters/starter-sns/src/main/kotlin/io/github/hantsy/awskotlin/sns/autoconfigure/SnsProperties.kt
 A starters/starter-sns/src/main/resources/META-INF/additional-spring-configuration-metadata.json
 A starters/starter-sns/src/main/resources/META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports
 A starters/starter-sns/src/test/kotlin/io/github/hantsy/awskotlin/sns/autoconfigure/SnsAutoConfigurationTest.kt
 A starters/starter-sqs/build.gradle.kts
 A starters/starter-sqs/gradle.properties
 A starters/starter-sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/autoconfigure/KotlinDurationConverter.kt
 A starters/starter-sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/autoconfigure/SqsAutoConfiguration.kt
 A starters/starter-sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/autoconfigure/SqsProperties.kt
 A starters/starter-sqs/src/main/resources/META-INF/additional-spring-configuration-metadata.json
 A starters/starter-sqs/src/main/resources/META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports
 A starters/starter-sqs/src/test/kotlin/io/github/hantsy/awskotlin/sqs/autoconfigure/SqsAutoConfigurationTest.kt
?? .kotlin/
?? .superpowers/

## diff stat
 settings.gradle.kts                                |   9 +
 starters/starter-sqs/build.gradle.kts              |  74 +++++++
 starters/starter-sqs/gradle.properties             |   5 +
 .../sqs/autoconfigure/KotlinDurationConverter.kt   |  43 ++++
 .../sqs/autoconfigure/SqsAutoConfiguration.kt      |  71 +++++++
 .../awskotlin/sqs/autoconfigure/SqsProperties.kt   |  27 +++
 .../additional-spring-configuration-metadata.json  |  57 ++++++
 ...rk.boot.autoconfigure.AutoConfiguration.imports |   1 +
 .../sqs/autoconfigure/SqsAutoConfigurationTest.kt  | 218 +++++++++++++++++++++
 9 files changed, 505 insertions(+)

## diff
diff --git a/settings.gradle.kts b/settings.gradle.kts
index a2d0cf9..83c1ca3 100644
--- a/settings.gradle.kts
+++ b/settings.gradle.kts
@@ -11,10 +11,19 @@ pluginManagement {
     includeBuild("aws-build-tool")
 }
 
 plugins {
     // Apply the foojay-resolver plugin to allow automatic download of JDKs
     id("org.gradle.toolchains.foojay-resolver-convention") version "0.10.0"
 }
 
 rootProject.name = "aws-sdk-kotlin-spring"
 include( "list", "utilities", "examples")
+include("core")
+include("s3")
+include("secrets-manager")
+include("sns")
+include("sqs")
+include("starters:starter-s3")
+include("starters:starter-secrets-manager")
+include("starters:starter-sns")
+include("starters:starter-sqs")
diff --git a/starters/starter-sqs/build.gradle.kts b/starters/starter-sqs/build.gradle.kts
new file mode 100644
index 0000000..3b358ac
--- /dev/null
+++ b/starters/starter-sqs/build.gradle.kts
@@ -0,0 +1,74 @@
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
+base { archivesName.set("awskotlin-spring-starter-sqs") }
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
+    api(project(":sqs"))
+    api(project(":core"))
+    implementation(libs.springBootAutoconfigure)
+    implementation(libs.kotlinStdlib)
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
diff --git a/starters/starter-sqs/gradle.properties b/starters/starter-sqs/gradle.properties
new file mode 100644
index 0000000..40754e7
--- /dev/null
+++ b/starters/starter-sqs/gradle.properties
@@ -0,0 +1,5 @@
+# Workaround for KGP 2.4.10 + Gradle 9.9.2: Gradle ships Kotlin 2.4.21 compiler classes
+# in its parent classloader, which breaks the Build Tools API path used by kapt stub
+# generation (NoSuchMethodError: ArgumentsToStrings.toArgumentStrings$default).
+# The legacy compiler runner avoids that path. Deprecated in KGP 2.4.x, removed in 2.5.0.
+kotlin.compiler.runViaBuildToolsApi=false
diff --git a/starters/starter-sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/autoconfigure/KotlinDurationConverter.kt b/starters/starter-sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/autoconfigure/KotlinDurationConverter.kt
new file mode 100644
index 0000000..8b1e5ea
--- /dev/null
+++ b/starters/starter-sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/autoconfigure/KotlinDurationConverter.kt
@@ -0,0 +1,43 @@
+package io.github.hantsy.awskotlin.sqs.autoconfigure
+
+import org.springframework.core.convert.TypeDescriptor
+import org.springframework.core.convert.converter.ConditionalGenericConverter
+import org.springframework.core.convert.converter.GenericConverter
+import kotlin.time.Duration
+
+/**
+ * Marks a [Duration] constructor parameter for configuration property binding.
+ *
+ * Spring Boot's value-object binder erases non-null Kotlin value class parameters to their
+ * underlying JVM type (`long` for [Duration]), so a plain `Converter<String, Duration>` is
+ * never consulted for them. [KotlinDurationConverter] matches on this annotation instead
+ * and supplies the boxed [Duration] that Kotlin constructor invocation expects.
+ */
+@Target(AnnotationTarget.VALUE_PARAMETER)
+@Retention(AnnotationRetention.RUNTIME)
+annotation class KotlinDuration
+
+/**
+ * Converts duration strings such as `10s`, `500ms` or ISO-8601 `PT15S` to [Duration]
+ * for constructor-bound configuration properties annotated with [KotlinDuration].
+ */
+class KotlinDurationConverter : ConditionalGenericConverter {
+    override fun getConvertibleTypes(): Set<GenericConverter.ConvertiblePair> = setOf(
+        // The binder targets the erased primitive `long`; converter lookup resolves
+        // primitives to wrappers, so the pairs must use java.lang.Long (javaObjectType).
+        GenericConverter.ConvertiblePair(String::class.java, Long::class.javaObjectType),
+        // Binder.handleBindResult re-converts the bound value to the declared target
+        // type, so the boxed Duration must also be accepted for the erased long target.
+        GenericConverter.ConvertiblePair(Duration::class.java, Long::class.javaObjectType),
+        GenericConverter.ConvertiblePair(String::class.java, Duration::class.java),
+    )
+
+    override fun matches(sourceType: TypeDescriptor, targetType: TypeDescriptor,): Boolean =
+        targetType.hasAnnotation(KotlinDuration::class.java) || targetType.type == Duration::class.java
+
+    override fun convert(source: Any?, sourceType: TypeDescriptor, targetType: TypeDescriptor,): Any? = when (source) {
+        null -> null
+        is Duration -> source
+        else -> Duration.parse(source.toString().trim())
+    }
+}
diff --git a/starters/starter-sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/autoconfigure/SqsAutoConfiguration.kt b/starters/starter-sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/autoconfigure/SqsAutoConfiguration.kt
new file mode 100644
index 0000000..c58ec8a
--- /dev/null
+++ b/starters/starter-sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/autoconfigure/SqsAutoConfiguration.kt
@@ -0,0 +1,71 @@
+package io.github.hantsy.awskotlin.sqs.autoconfigure
+
+import aws.sdk.kotlin.services.sqs.SqsClient
+import aws.smithy.kotlin.runtime.auth.awscredentials.CredentialsProvider
+import aws.smithy.kotlin.runtime.net.url.Url
+import io.github.hantsy.awskotlin.core.AwsKotlinProperties
+import io.github.hantsy.awskotlin.core.autoconfigure.AwsKotlinAutoConfiguration
+import io.github.hantsy.awskotlin.sqs.JacksonSqsMessageConverter
+import io.github.hantsy.awskotlin.sqs.SqsMessageConverter
+import io.github.hantsy.awskotlin.sqs.SqsQueueResolver
+import io.github.hantsy.awskotlin.sqs.SqsTemplate
+import org.springframework.boot.autoconfigure.AutoConfiguration
+import org.springframework.boot.autoconfigure.condition.ConditionalOnClass
+import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
+import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
+import org.springframework.boot.context.properties.ConfigurationPropertiesBinding
+import org.springframework.boot.context.properties.EnableConfigurationProperties
+import org.springframework.context.annotation.Bean
+
+/**
+ * Auto-configuration for the AWS SDK for Kotlin SQS client and [SqsTemplate].
+ *
+ * Disabled by either `awskotlin.enabled=false` or `awskotlin.sqs.enabled=false`
+ * (both checked by the single property condition below). User-provided
+ * [SqsClient]/[SqsMessageConverter]/[SqsQueueResolver]/[SqsTemplate] beans always win.
+ */
+@AutoConfiguration(after = [AwsKotlinAutoConfiguration::class])
+@ConditionalOnClass(SqsClient::class)
+@ConditionalOnProperty(
+    prefix = "awskotlin",
+    name = ["enabled", "sqs.enabled"],
+    havingValue = "true",
+    matchIfMissing = true,
+)
+@EnableConfigurationProperties(SqsProperties::class)
+class SqsAutoConfiguration {
+    // Boot's binder erases non-null kotlin.time.Duration (a value class) constructor
+    // parameters to long, so binding needs this annotation-scoped converter rather than
+    // a plain Converter<String, Duration>. See KotlinDurationConverter.
+    @Bean
+    @ConfigurationPropertiesBinding
+    fun kotlinDurationConverter(): KotlinDurationConverter = KotlinDurationConverter()
+
+    @Bean
+    @ConditionalOnMissingBean
+    fun sqsClient(
+        properties: AwsKotlinProperties,
+        sqsProperties: SqsProperties,
+        credentialsProvider: CredentialsProvider,
+    ): SqsClient {
+        val endpoint = sqsProperties.endpoint ?: properties.endpoint
+        return SqsClient {
+            region = sqsProperties.region ?: properties.region
+            this.credentialsProvider = credentialsProvider
+            endpoint?.let { endpointUrl = Url.parse(it) }
+        }
+    }
+
+    @Bean
+    @ConditionalOnMissingBean
+    fun sqsMessageConverter(): SqsMessageConverter = JacksonSqsMessageConverter()
+
+    @Bean
+    @ConditionalOnMissingBean
+    fun sqsQueueResolver(client: SqsClient): SqsQueueResolver = SqsQueueResolver(client)
+
+    @Bean
+    @ConditionalOnMissingBean
+    fun sqsTemplate(client: SqsClient, converter: SqsMessageConverter, queueResolver: SqsQueueResolver,): SqsTemplate =
+        SqsTemplate(client, converter, queueResolver)
+}
diff --git a/starters/starter-sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/autoconfigure/SqsProperties.kt b/starters/starter-sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/autoconfigure/SqsProperties.kt
new file mode 100644
index 0000000..c83e2c4
--- /dev/null
+++ b/starters/starter-sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/autoconfigure/SqsProperties.kt
@@ -0,0 +1,27 @@
+package io.github.hantsy.awskotlin.sqs.autoconfigure
+
+import org.springframework.boot.context.properties.ConfigurationProperties
+import kotlin.time.Duration
+import kotlin.time.Duration.Companion.seconds
+
+/**
+ * Configuration properties for the SQS module.
+ *
+ * Unset values fall back to the shared `awskotlin.*` properties and then to the
+ * SDK default chains. Listener settings are consumed by the listener runtime;
+ * values are not validated here.
+ */
+@ConfigurationProperties(prefix = "awskotlin.sqs")
+data class SqsProperties(
+    val enabled: Boolean = true,
+    val endpoint: String? = null,
+    val region: String? = null,
+    val listener: Listener = Listener(),
+) {
+    data class Listener(
+        val autoStartup: Boolean = true,
+        val maxConcurrentMessages: Int = 10,
+        @KotlinDuration val pollTime: Duration = 10.seconds,
+        @KotlinDuration val shutdownTimeout: Duration = 20.seconds,
+    )
+}
diff --git a/starters/starter-sqs/src/main/resources/META-INF/additional-spring-configuration-metadata.json b/starters/starter-sqs/src/main/resources/META-INF/additional-spring-configuration-metadata.json
new file mode 100644
index 0000000..26cabfb
--- /dev/null
+++ b/starters/starter-sqs/src/main/resources/META-INF/additional-spring-configuration-metadata.json
@@ -0,0 +1,57 @@
+{
+  "properties": [
+    {
+      "name": "awskotlin.sqs.enabled",
+      "type": "java.lang.Boolean",
+      "description": "Whether SQS auto-configuration is enabled.",
+      "sourceType": "io.github.hantsy.awskotlin.sqs.autoconfigure.SqsProperties",
+      "defaultValue": true
+    },
+    {
+      "name": "awskotlin.sqs.endpoint",
+      "type": "java.lang.String",
+      "description": "Override the SQS endpoint URL (for example, a LocalStack endpoint). Falls back to awskotlin.endpoint.",
+      "sourceType": "io.github.hantsy.awskotlin.sqs.autoconfigure.SqsProperties"
+    },
+    {
+      "name": "awskotlin.sqs.region",
+      "type": "java.lang.String",
+      "description": "Override the SQS region. Falls back to awskotlin.region, then the SDK default region chain.",
+      "sourceType": "io.github.hantsy.awskotlin.sqs.autoconfigure.SqsProperties"
+    },
+    {
+      "name": "awskotlin.sqs.listener",
+      "type": "io.github.hantsy.awskotlin.sqs.autoconfigure.SqsProperties$Listener",
+      "description": "Listener container settings.",
+      "sourceType": "io.github.hantsy.awskotlin.sqs.autoconfigure.SqsProperties"
+    },
+    {
+      "name": "awskotlin.sqs.listener.auto-startup",
+      "type": "java.lang.Boolean",
+      "description": "Whether listener containers start automatically with the application context.",
+      "sourceType": "io.github.hantsy.awskotlin.sqs.autoconfigure.SqsProperties$Listener",
+      "defaultValue": true
+    },
+    {
+      "name": "awskotlin.sqs.listener.max-concurrent-messages",
+      "type": "java.lang.Integer",
+      "description": "Maximum number of messages processed concurrently per listener.",
+      "sourceType": "io.github.hantsy.awskotlin.sqs.autoconfigure.SqsProperties$Listener",
+      "defaultValue": 10
+    },
+    {
+      "name": "awskotlin.sqs.listener.poll-time",
+      "type": "kotlin.time.Duration",
+      "description": "Long-poll wait time used when receiving messages. Accepts Kotlin duration format (10s, 500ms) or ISO-8601 (PT10S).",
+      "sourceType": "io.github.hantsy.awskotlin.sqs.autoconfigure.SqsProperties$Listener",
+      "defaultValue": "10s"
+    },
+    {
+      "name": "awskotlin.sqs.listener.shutdown-timeout",
+      "type": "kotlin.time.Duration",
+      "description": "Time to wait for in-flight messages to complete on shutdown. Accepts Kotlin duration format (20s, 500ms) or ISO-8601 (PT20S).",
+      "sourceType": "io.github.hantsy.awskotlin.sqs.autoconfigure.SqsProperties$Listener",
+      "defaultValue": "20s"
+    }
+  ]
+}
diff --git a/starters/starter-sqs/src/main/resources/META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports b/starters/starter-sqs/src/main/resources/META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports
new file mode 100644
index 0000000..36e1d95
--- /dev/null
+++ b/starters/starter-sqs/src/main/resources/META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports
@@ -0,0 +1 @@
+io.github.hantsy.awskotlin.sqs.autoconfigure.SqsAutoConfiguration
diff --git a/starters/starter-sqs/src/test/kotlin/io/github/hantsy/awskotlin/sqs/autoconfigure/SqsAutoConfigurationTest.kt b/starters/starter-sqs/src/test/kotlin/io/github/hantsy/awskotlin/sqs/autoconfigure/SqsAutoConfigurationTest.kt
new file mode 100644
index 0000000..bd3b71b
--- /dev/null
+++ b/starters/starter-sqs/src/test/kotlin/io/github/hantsy/awskotlin/sqs/autoconfigure/SqsAutoConfigurationTest.kt
@@ -0,0 +1,218 @@
+package io.github.hantsy.awskotlin.sqs.autoconfigure
+
+import aws.sdk.kotlin.runtime.auth.credentials.StaticCredentialsProvider
+import aws.sdk.kotlin.services.sqs.SqsClient
+import io.github.hantsy.awskotlin.core.autoconfigure.AwsKotlinAutoConfiguration
+import io.github.hantsy.awskotlin.sqs.JacksonSqsMessageConverter
+import io.github.hantsy.awskotlin.sqs.SqsMessageConverter
+import io.github.hantsy.awskotlin.sqs.SqsQueueResolver
+import io.github.hantsy.awskotlin.sqs.SqsTemplate
+import io.kotest.matchers.shouldBe
+import io.kotest.matchers.types.shouldBeInstanceOf
+import io.kotest.matchers.types.shouldBeSameInstanceAs
+import io.mockk.mockk
+import org.junit.jupiter.api.Test
+import org.springframework.boot.autoconfigure.AutoConfigurations
+import org.springframework.boot.test.context.runner.ApplicationContextRunner
+import org.springframework.context.annotation.Bean
+import org.springframework.context.annotation.Configuration
+import kotlin.time.Duration.Companion.seconds
+
+class SqsAutoConfigurationTest {
+    private val runner =
+        ApplicationContextRunner().withConfiguration(
+            AutoConfigurations.of(AwsKotlinAutoConfiguration::class.java, SqsAutoConfiguration::class.java),
+        )
+
+    @Test
+    fun `default context provides SQS beans with static credentials and region`() {
+        runner.withPropertyValues(
+            "awskotlin.region=us-east-1",
+            "awskotlin.access-key-id=test-access-key",
+            "awskotlin.secret-access-key=test-secret-key",
+        ).run { context ->
+            context.getBeanNamesForType(SqsClient::class.java).size shouldBe 1
+            context.getBeanNamesForType(SqsMessageConverter::class.java).size shouldBe 1
+            context.getBeanNamesForType(SqsQueueResolver::class.java).size shouldBe 1
+            context.getBeanNamesForType(SqsTemplate::class.java).size shouldBe 1
+            context.getBeanNamesForType(SqsProperties::class.java).size shouldBe 1
+            val client = context.getBean(SqsClient::class.java)
+            client.config.region shouldBe "us-east-1"
+            client.config.credentialsProvider.shouldBeInstanceOf<StaticCredentialsProvider>()
+            context.getBean(SqsMessageConverter::class.java).shouldBeInstanceOf<JacksonSqsMessageConverter>()
+        }
+    }
+
+    @Test
+    fun `awskotlin enabled=false removes the beans`() {
+        runner.withPropertyValues(
+            "awskotlin.region=us-east-1",
+            "awskotlin.enabled=false",
+        ).run { context ->
+            context.getBeanNamesForType(SqsClient::class.java).isEmpty() shouldBe true
+            context.getBeanNamesForType(SqsMessageConverter::class.java).isEmpty() shouldBe true
+            context.getBeanNamesForType(SqsQueueResolver::class.java).isEmpty() shouldBe true
+            context.getBeanNamesForType(SqsTemplate::class.java).isEmpty() shouldBe true
+            context.getBeanNamesForType(SqsProperties::class.java).isEmpty() shouldBe true
+        }
+    }
+
+    @Test
+    fun `awskotlin sqs enabled=false removes the beans`() {
+        runner.withPropertyValues(
+            "awskotlin.region=us-east-1",
+            "awskotlin.sqs.enabled=false",
+        ).run { context ->
+            context.getBeanNamesForType(SqsClient::class.java).isEmpty() shouldBe true
+            context.getBeanNamesForType(SqsMessageConverter::class.java).isEmpty() shouldBe true
+            context.getBeanNamesForType(SqsQueueResolver::class.java).isEmpty() shouldBe true
+            context.getBeanNamesForType(SqsTemplate::class.java).isEmpty() shouldBe true
+            context.getBeanNamesForType(SqsProperties::class.java).isEmpty() shouldBe true
+        }
+    }
+
+    @Test
+    fun `user SqsClient bean wins`() {
+        runner.withPropertyValues(
+            "awskotlin.region=us-east-1",
+        ).withUserConfiguration(UserSqsClientConfiguration::class.java).run { context ->
+            context.getBeanNamesForType(SqsClient::class.java).size shouldBe 1
+            val userBean = context.getBean("userSqsClient")
+            context.getBean(SqsClient::class.java) shouldBeSameInstanceAs userBean
+            context.getBeanNamesForType(SqsTemplate::class.java).size shouldBe 1
+        }
+    }
+
+    @Test
+    fun `user SqsMessageConverter bean wins`() {
+        runner.withPropertyValues(
+            "awskotlin.region=us-east-1",
+        ).withUserConfiguration(UserSqsMessageConverterConfiguration::class.java).run { context ->
+            context.getBeanNamesForType(SqsMessageConverter::class.java).size shouldBe 1
+            val userBean = context.getBean("userSqsMessageConverter")
+            context.getBean(SqsMessageConverter::class.java) shouldBeSameInstanceAs userBean
+            context.getBeanNamesForType(SqsTemplate::class.java).size shouldBe 1
+        }
+    }
+
+    @Test
+    fun `user SqsQueueResolver bean wins`() {
+        runner.withPropertyValues(
+            "awskotlin.region=us-east-1",
+        ).withUserConfiguration(UserSqsQueueResolverConfiguration::class.java).run { context ->
+            context.getBeanNamesForType(SqsQueueResolver::class.java).size shouldBe 1
+            val userBean = context.getBean("userSqsQueueResolver")
+            context.getBean(SqsQueueResolver::class.java) shouldBeSameInstanceAs userBean
+            context.getBeanNamesForType(SqsTemplate::class.java).size shouldBe 1
+        }
+    }
+
+    @Test
+    fun `user SqsTemplate bean wins`() {
+        runner.withPropertyValues(
+            "awskotlin.region=us-east-1",
+        ).withUserConfiguration(UserSqsTemplateConfiguration::class.java).run { context ->
+            context.getBeanNamesForType(SqsTemplate::class.java).size shouldBe 1
+            val userBean = context.getBean("userSqsTemplate")
+            context.getBean(SqsTemplate::class.java) shouldBeSameInstanceAs userBean
+        }
+    }
+
+    @Test
+    fun `sqs region and endpoint override the global values`() {
+        runner.withPropertyValues(
+            "awskotlin.region=us-east-1",
+            "awskotlin.endpoint=http://global:4566",
+            "awskotlin.sqs.region=eu-west-1",
+            "awskotlin.sqs.endpoint=http://localhost:4566",
+            "awskotlin.access-key-id=test-access-key",
+            "awskotlin.secret-access-key=test-secret-key",
+        ).run { context ->
+            val config = context.getBean(SqsClient::class.java).config
+            config.region shouldBe "eu-west-1"
+            config.endpointUrl?.toString() shouldBe "http://localhost:4566"
+            val properties = context.getBean(SqsProperties::class.java)
+            properties.region shouldBe "eu-west-1"
+            properties.endpoint shouldBe "http://localhost:4566"
+        }
+    }
+
+    @Test
+    fun `global endpoint override is used when sqs endpoint is unset`() {
+        runner.withPropertyValues(
+            "awskotlin.region=us-east-1",
+            "awskotlin.endpoint=http://localhost:4566",
+            "awskotlin.access-key-id=test-access-key",
+            "awskotlin.secret-access-key=test-secret-key",
+        ).run { context ->
+            context.getBean(SqsClient::class.java).config.endpointUrl?.toString() shouldBe "http://localhost:4566"
+        }
+    }
+
+    @Test
+    fun `listener properties have sensible defaults`() {
+        runner.withPropertyValues(
+            "awskotlin.region=us-east-1",
+        ).run { context ->
+            val listener = context.getBean(SqsProperties::class.java).listener
+            listener.autoStartup shouldBe true
+            listener.maxConcurrentMessages shouldBe 10
+            listener.pollTime shouldBe 10.seconds
+            listener.shutdownTimeout shouldBe 20.seconds
+        }
+    }
+
+    @Test
+    fun `custom listener values bind`() {
+        runner.withPropertyValues(
+            "awskotlin.region=us-east-1",
+            "awskotlin.sqs.listener.auto-startup=false",
+            "awskotlin.sqs.listener.max-concurrent-messages=5",
+            "awskotlin.sqs.listener.poll-time=5s",
+            "awskotlin.sqs.listener.shutdown-timeout=30s",
+        ).run { context ->
+            val listener = context.getBean(SqsProperties::class.java).listener
+            listener.autoStartup shouldBe false
+            listener.maxConcurrentMessages shouldBe 5
+            listener.pollTime shouldBe 5.seconds
+            listener.shutdownTimeout shouldBe 30.seconds
+        }
+    }
+
+    @Test
+    fun `listener durations also accept ISO-8601 format`() {
+        runner.withPropertyValues(
+            "awskotlin.region=us-east-1",
+            "awskotlin.sqs.listener.poll-time=PT2S",
+            "awskotlin.sqs.listener.shutdown-timeout=PT1M",
+        ).run { context ->
+            val listener = context.getBean(SqsProperties::class.java).listener
+            listener.pollTime shouldBe 2.seconds
+            listener.shutdownTimeout shouldBe 60.seconds
+        }
+    }
+}
+
+@Configuration(proxyBeanMethods = false)
+class UserSqsClientConfiguration {
+    @Bean
+    fun userSqsClient(): SqsClient = mockk(relaxed = true)
+}
+
+@Configuration(proxyBeanMethods = false)
+class UserSqsMessageConverterConfiguration {
+    @Bean
+    fun userSqsMessageConverter(): SqsMessageConverter = mockk(relaxed = true)
+}
+
+@Configuration(proxyBeanMethods = false)
+class UserSqsQueueResolverConfiguration {
+    @Bean
+    fun userSqsQueueResolver(): SqsQueueResolver = SqsQueueResolver(mockk(relaxed = true))
+}
+
+@Configuration(proxyBeanMethods = false)
+class UserSqsTemplateConfiguration {
+    @Bean
+    fun userSqsTemplate(): SqsTemplate = SqsTemplate(mockk(relaxed = true))
+}
