# Cleanup review package

## git status
 M README.md
D  aws-build-tool/build.gradle.kts
D  aws-build-tool/settings.gradle.kts
D  aws-build-tool/src/main/kotlin/buildlogic.kotlin-application-conventions.gradle.kts
D  aws-build-tool/src/main/kotlin/buildlogic.kotlin-common-conventions.gradle.kts
D  aws-build-tool/src/main/kotlin/buildlogic.kotlin-library-conventions.gradle.kts
A  core/build.gradle.kts
A  core/src/main/kotlin/io/github/hantsy/awskotlin/core/AwsKotlinCredentialsProviderFactory.kt
A  core/src/main/kotlin/io/github/hantsy/awskotlin/core/AwsKotlinProperties.kt
A  core/src/main/kotlin/io/github/hantsy/awskotlin/core/autoconfigure/AwsKotlinAutoConfiguration.kt
A  core/src/main/resources/META-INF/spring-configuration-metadata.json
A  core/src/main/resources/META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports
A  core/src/test/kotlin/io/github/hantsy/awskotlin/core/AwsKotlinCredentialsProviderFactoryTest.kt
A  core/src/test/kotlin/io/github/hantsy/awskotlin/core/autoconfigure/AwsKotlinAutoConfigurationTest.kt
M  gradle.properties
M  gradle/libs.versions.toml
D  list/build.gradle.kts
D  list/src/main/kotlin/org/example/list/LinkedList.kt
D  list/src/test/kotlin/org/example/list/LinkedListTest.kt
A  s3/build.gradle.kts
A  s3/src/main/kotlin/io/github/hantsy/awskotlin/s3/S3BucketOperations.kt
A  s3/src/main/kotlin/io/github/hantsy/awskotlin/s3/S3ObjectOperations.kt
A  s3/src/main/kotlin/io/github/hantsy/awskotlin/s3/S3Template.kt
A  s3/src/test/kotlin/io/github/hantsy/awskotlin/s3/S3TemplateLocalStackTest.kt
A  s3/src/test/kotlin/io/github/hantsy/awskotlin/s3/S3TemplateTest.kt
A  secrets-manager/build.gradle.kts
A  secrets-manager/src/main/kotlin/io/github/hantsy/awskotlin/secretsmanager/SecretOperations.kt
A  secrets-manager/src/main/kotlin/io/github/hantsy/awskotlin/secretsmanager/SecretsManagerTemplate.kt
A  secrets-manager/src/test/kotlin/io/github/hantsy/awskotlin/secretsmanager/SecretsManagerTemplateTest.kt
M  settings.gradle.kts
A  sns/build.gradle.kts
A  sns/src/main/kotlin/io/github/hantsy/awskotlin/sns/SnsNotification.kt
A  sns/src/main/kotlin/io/github/hantsy/awskotlin/sns/SnsNotificationParseException.kt
A  sns/src/main/kotlin/io/github/hantsy/awskotlin/sns/SnsNotificationParser.kt
A  sns/src/main/kotlin/io/github/hantsy/awskotlin/sns/SnsNotificationType.kt
A  sns/src/main/kotlin/io/github/hantsy/awskotlin/sns/SnsTemplate.kt
A  sns/src/main/kotlin/io/github/hantsy/awskotlin/sns/TopicOperations.kt
A  sns/src/test/kotlin/io/github/hantsy/awskotlin/sns/SnsNotificationParserTest.kt
A  sns/src/test/kotlin/io/github/hantsy/awskotlin/sns/SnsSqsFanOutLocalStackTest.kt
A  sns/src/test/kotlin/io/github/hantsy/awskotlin/sns/SnsTemplateTest.kt
A  sns/src/test/kotlin/io/github/hantsy/awskotlin/sns/TopicOperationsSubscribeSqsTest.kt
A  sqs/build.gradle.kts
A  sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/DefaultSqsDestinationOperations.kt
A  sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/KotlinxSqsMessageConverter.kt
A  sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/SqsAcknowledgementMode.kt
A  sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/SqsDestinationOperations.kt
A  sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/SqsMessage.kt
A  sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/SqsMessageConverter.kt
A  sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/SqsQueueResolver.kt
A  sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/SqsTemplate.kt
A  sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/SqsTemplateException.kt
A  sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/listener/Acknowledge.kt
A  sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/listener/SqsListener.kt
A  sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/listener/SqsListenerAnnotationRegistrar.kt
A  sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/listener/SqsListenerConfig.kt
A  sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/listener/SqsListenerContainer.kt
A  sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/listener/SqsListenerEndpoint.kt
A  sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/listener/SqsListenerMethod.kt
A  sqs/src/test/kotlin/io/github/hantsy/awskotlin/sqs/KotlinxSqsMessageConverterTest.kt
A  sqs/src/test/kotlin/io/github/hantsy/awskotlin/sqs/SqsQueueResolverTest.kt
A  sqs/src/test/kotlin/io/github/hantsy/awskotlin/sqs/SqsTemplateTest.kt
A  sqs/src/test/kotlin/io/github/hantsy/awskotlin/sqs/listener/SqsListenerAnnotationRegistrarTest.kt
A  sqs/src/test/kotlin/io/github/hantsy/awskotlin/sqs/listener/SqsListenerContainerTest.kt
A  starters/s3-starter/build.gradle.kts
A  starters/s3-starter/src/main/kotlin/io/github/hantsy/awskotlin/s3/autoconfigure/S3AutoConfiguration.kt
A  starters/s3-starter/src/main/kotlin/io/github/hantsy/awskotlin/s3/autoconfigure/S3Properties.kt
A  starters/s3-starter/src/main/resources/META-INF/spring-configuration-metadata.json
A  starters/s3-starter/src/main/resources/META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports
A  starters/s3-starter/src/test/kotlin/io/github/hantsy/awskotlin/s3/autoconfigure/S3AutoConfigurationTest.kt
A  starters/secrets-manager-starter/build.gradle.kts
A  starters/secrets-manager-starter/src/main/kotlin/io/github/hantsy/awskotlin/secretsmanager/autoconfigure/SecretsManagerAutoConfiguration.kt
A  starters/secrets-manager-starter/src/main/kotlin/io/github/hantsy/awskotlin/secretsmanager/autoconfigure/SecretsManagerProperties.kt
A  starters/secrets-manager-starter/src/main/resources/META-INF/spring-configuration-metadata.json
A  starters/secrets-manager-starter/src/main/resources/META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports
A  starters/secrets-manager-starter/src/test/kotlin/io/github/hantsy/awskotlin/secretsmanager/autoconfigure/SecretsManagerAutoConfigurationTest.kt
A  starters/sns-starter/build.gradle.kts
A  starters/sns-starter/src/main/kotlin/io/github/hantsy/awskotlin/sns/autoconfigure/SnsAutoConfiguration.kt
A  starters/sns-starter/src/main/kotlin/io/github/hantsy/awskotlin/sns/autoconfigure/SnsProperties.kt
A  starters/sns-starter/src/main/resources/META-INF/spring-configuration-metadata.json
A  starters/sns-starter/src/main/resources/META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports
A  starters/sns-starter/src/test/kotlin/io/github/hantsy/awskotlin/sns/autoconfigure/SnsAutoConfigurationTest.kt
A  starters/sqs-starter/build.gradle.kts
A  starters/sqs-starter/src/main/kotlin/io/github/hantsy/awskotlin/sqs/autoconfigure/KotlinDurationConverter.kt
A  starters/sqs-starter/src/main/kotlin/io/github/hantsy/awskotlin/sqs/autoconfigure/SqsAutoConfiguration.kt
A  starters/sqs-starter/src/main/kotlin/io/github/hantsy/awskotlin/sqs/autoconfigure/SqsProperties.kt
A  starters/sqs-starter/src/main/resources/META-INF/spring-configuration-metadata.json
A  starters/sqs-starter/src/main/resources/META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports
A  starters/sqs-starter/src/test/kotlin/io/github/hantsy/awskotlin/sqs/autoconfigure/SqsAutoConfigurationTest.kt
A  starters/sqs-starter/src/test/kotlin/io/github/hantsy/awskotlin/sqs/autoconfigure/SqsLocalStackIntegrationTest.kt
D  utilities/build.gradle.kts
D  utilities/src/main/kotlin/org/example/utilities/JoinUtils.kt
D  utilities/src/main/kotlin/org/example/utilities/SplitUtils.kt
D  utilities/src/main/kotlin/org/example/utilities/StringUtils.kt
?? .kotlin/
?? .superpowers/

## diff stat
 README.md                                          |  13 +-
 aws-build-tool/build.gradle.kts                    |  19 -
 aws-build-tool/settings.gradle.kts                 |  15 -
 ...logic.kotlin-application-conventions.gradle.kts |  13 -
 ...buildlogic.kotlin-common-conventions.gradle.kts |  58 ---
 ...uildlogic.kotlin-library-conventions.gradle.kts |  13 -
 core/build.gradle.kts                              |  58 +++
 .../core/AwsKotlinCredentialsProviderFactory.kt    |  35 ++
 .../hantsy/awskotlin/core/AwsKotlinProperties.kt   |  18 +
 .../autoconfigure/AwsKotlinAutoConfiguration.kt    |  28 ++
 .../META-INF/spring-configuration-metadata.json    |  48 +++
 ...rk.boot.autoconfigure.AutoConfiguration.imports |   1 +
 .../AwsKotlinCredentialsProviderFactoryTest.kt     |  61 +++
 .../AwsKotlinAutoConfigurationTest.kt              |  72 ++++
 gradle.properties                                  |   8 +
 gradle/libs.versions.toml                          |  11 +
 list/build.gradle.kts                              |   9 -
 .../src/main/kotlin/org/example/list/LinkedList.kt |  86 ----
 .../test/kotlin/org/example/list/LinkedListTest.kt |  52 ---
 s3/build.gradle.kts                                |  57 +++
 .../hantsy/awskotlin/s3/S3BucketOperations.kt      |  38 ++
 .../hantsy/awskotlin/s3/S3ObjectOperations.kt      |  46 +++
 .../io/github/hantsy/awskotlin/s3/S3Template.kt    |  14 +
 .../awskotlin/s3/S3TemplateLocalStackTest.kt       | 116 ++++++
 .../github/hantsy/awskotlin/s3/S3TemplateTest.kt   | 153 +++++++
 secrets-manager/build.gradle.kts                   |  55 +++
 .../awskotlin/secretsmanager/SecretOperations.kt   |  45 ++
 .../secretsmanager/SecretsManagerTemplate.kt       |  14 +
 .../secretsmanager/SecretsManagerTemplateTest.kt   | 105 +++++
 settings.gradle.kts                                |  16 +-
 sns/build.gradle.kts                               |  59 +++
 .../github/hantsy/awskotlin/sns/SnsNotification.kt |  35 ++
 .../awskotlin/sns/SnsNotificationParseException.kt |   8 +
 .../hantsy/awskotlin/sns/SnsNotificationParser.kt  |  67 +++
 .../hantsy/awskotlin/sns/SnsNotificationType.kt    |  15 +
 .../io/github/hantsy/awskotlin/sns/SnsTemplate.kt  |  21 +
 .../github/hantsy/awskotlin/sns/TopicOperations.kt |  67 +++
 .../awskotlin/sns/SnsNotificationParserTest.kt     | 184 +++++++++
 .../awskotlin/sns/SnsSqsFanOutLocalStackTest.kt    | 182 +++++++++
 .../github/hantsy/awskotlin/sns/SnsTemplateTest.kt | 110 +++++
 .../sns/TopicOperationsSubscribeSqsTest.kt         |  68 ++++
 sqs/build.gradle.kts                               |  61 +++
 .../sqs/DefaultSqsDestinationOperations.kt         | 125 ++++++
 .../awskotlin/sqs/KotlinxSqsMessageConverter.kt    |  47 +++
 .../hantsy/awskotlin/sqs/SqsAcknowledgementMode.kt |  10 +
 .../awskotlin/sqs/SqsDestinationOperations.kt      |  47 +++
 .../io/github/hantsy/awskotlin/sqs/SqsMessage.kt   |  18 +
 .../hantsy/awskotlin/sqs/SqsMessageConverter.kt    |  10 +
 .../hantsy/awskotlin/sqs/SqsQueueResolver.kt       |  40 ++
 .../io/github/hantsy/awskotlin/sqs/SqsTemplate.kt  |  19 +
 .../hantsy/awskotlin/sqs/SqsTemplateException.kt   |  19 +
 .../hantsy/awskotlin/sqs/listener/Acknowledge.kt   |  31 ++
 .../hantsy/awskotlin/sqs/listener/SqsListener.kt   |  19 +
 .../sqs/listener/SqsListenerAnnotationRegistrar.kt | 111 +++++
 .../awskotlin/sqs/listener/SqsListenerConfig.kt    |  19 +
 .../awskotlin/sqs/listener/SqsListenerContainer.kt | 154 +++++++
 .../awskotlin/sqs/listener/SqsListenerEndpoint.kt  |   9 +
 .../awskotlin/sqs/listener/SqsListenerMethod.kt    |  96 +++++
 .../sqs/KotlinxSqsMessageConverterTest.kt          |  68 ++++
 .../hantsy/awskotlin/sqs/SqsQueueResolverTest.kt   |  83 ++++
 .../github/hantsy/awskotlin/sqs/SqsTemplateTest.kt | 324 +++++++++++++++
 .../listener/SqsListenerAnnotationRegistrarTest.kt | 358 ++++++++++++++++
 .../sqs/listener/SqsListenerContainerTest.kt       | 451 +++++++++++++++++++++
 starters/s3-starter/build.gradle.kts               |  56 +++
 .../s3/autoconfigure/S3AutoConfiguration.kt        |  54 +++
 .../awskotlin/s3/autoconfigure/S3Properties.kt     |  17 +
 .../META-INF/spring-configuration-metadata.json    |  36 ++
 ...rk.boot.autoconfigure.AutoConfiguration.imports |   1 +
 .../s3/autoconfigure/S3AutoConfigurationTest.kt    | 131 ++++++
 starters/secrets-manager-starter/build.gradle.kts  |  56 +++
 .../SecretsManagerAutoConfiguration.kt             |  51 +++
 .../autoconfigure/SecretsManagerProperties.kt      |  16 +
 .../META-INF/spring-configuration-metadata.json    |  30 ++
 ...rk.boot.autoconfigure.AutoConfiguration.imports |   1 +
 .../SecretsManagerAutoConfigurationTest.kt         | 115 ++++++
 starters/sns-starter/build.gradle.kts              |  56 +++
 .../sns/autoconfigure/SnsAutoConfiguration.kt      |  51 +++
 .../awskotlin/sns/autoconfigure/SnsProperties.kt   |  12 +
 .../META-INF/spring-configuration-metadata.json    |  30 ++
 ...rk.boot.autoconfigure.AutoConfiguration.imports |   1 +
 .../sns/autoconfigure/SnsAutoConfigurationTest.kt  | 113 ++++++
 starters/sqs-starter/build.gradle.kts              |  59 +++
 .../sqs/autoconfigure/KotlinDurationConverter.kt   |  43 ++
 .../sqs/autoconfigure/SqsAutoConfiguration.kt      |  98 +++++
 .../awskotlin/sqs/autoconfigure/SqsProperties.kt   |  27 ++
 .../META-INF/spring-configuration-metadata.json    |  69 ++++
 ...rk.boot.autoconfigure.AutoConfiguration.imports |   1 +
 .../sqs/autoconfigure/SqsAutoConfigurationTest.kt  | 286 +++++++++++++
 .../autoconfigure/SqsLocalStackIntegrationTest.kt  | 214 ++++++++++
 utilities/build.gradle.kts                         |  13 -
 .../main/kotlin/org/example/utilities/JoinUtils.kt |  22 -
 .../kotlin/org/example/utilities/SplitUtils.kt     |  42 --
 .../kotlin/org/example/utilities/StringUtils.kt    |  18 -
 93 files changed, 5559 insertions(+), 372 deletions(-)

## diff
diff --git a/README.md b/README.md
index 0b1bd28..ec7de4d 100644
--- a/README.md
+++ b/README.md
@@ -6,31 +6,32 @@
 | Component | Version |
 | --- | --- |
 | Spring Boot | 4.2.0 |
 | Kotlin | 2.4.10 |
 | Java | 25 |
 | Gradle | 9.9.2 |
 | JUnit Jupiter | 6.1.3 (JUnit 6) |
 | Kotest assertions | 6.2.7 |
 | AWS SDK for Kotlin | 1.9.45 |
 | OkHttp | 5.5.1 |
-| Jackson | 3.x (via `tools.jackson.module:jackson-module-kotlin`) |
+| Jackson | 3.x (examples, via `tools.jackson.module:jackson-module-kotlin`) |
+| kotlinx-serialization | 1.9.0 (port modules) |
 | Testcontainers | 2.0.5 |
 
 ## Modules
 
-* `list` – a small plain Kotlin library (LinkedList) with JUnit 6 style tests.
-* `utilities` – plain Kotlin string helpers.
+* `core`, `s3`, `secrets-manager`, `sns`, `sqs` – AWS SDK for Kotlin support libraries
+  (artifacts `awskotlin-core`, `awskotlin-s3`, ...). JSON conversion uses kotlinx-serialization.
+* `starters/<service>-starter` – Spring Boot auto-configuration starters
+  (artifacts `awskotlin-<service>-spring-boot-starter`).
 * `examples` – the Spring Boot 4 application: S3, SQS and Secrets Manager services
-  built on the AWS SDK for Kotlin, plus the integration tests.
-
-Shared build conventions live in the `aws-build-tool` included build.
+  built on the AWS SDK for Kotlin, plus the integration tests. Keeps Jackson for WebFlux HTTP.
 
 ## Testing
 
 The integration tests run against a LocalStack container that is started by the test
 suite itself; the endpoint is injected with `@DynamicPropertySource`, because
 LocalStack has no Spring Boot service connection.
 
 The tests run against `localstack/localstack:4`, which needs a license token that is
 taken from the environment:
 
diff --git a/aws-build-tool/build.gradle.kts b/aws-build-tool/build.gradle.kts
deleted file mode 100644
index 3ad4a34..0000000
--- a/aws-build-tool/build.gradle.kts
+++ /dev/null
@@ -1,19 +0,0 @@
-/*
- * This file was generated by the Gradle 'init' task.
- *
- * This project uses @Incubating APIs which are subject to change.
- */
-
-plugins {
-    // Support convention plugins written in Kotlin. Convention plugins are build scripts in 'src/main' that automatically become available as plugins in the main build.
-    `kotlin-dsl`
-}
-
-repositories {
-    // Use the plugin portal to apply community plugins in convention plugins.
-    gradlePluginPortal()
-}
-
-dependencies {
-    implementation(libs.kotlin.gradle.plugin)
-}
diff --git a/aws-build-tool/settings.gradle.kts b/aws-build-tool/settings.gradle.kts
deleted file mode 100644
index 6ab6b69..0000000
--- a/aws-build-tool/settings.gradle.kts
+++ /dev/null
@@ -1,15 +0,0 @@
-/*
- * This file was generated by the Gradle 'init' task.
- *
- * This settings file is used to specify which projects to include in your build-logic build.
- * This project uses @Incubating APIs which are subject to change.
- */
-
-dependencyResolutionManagement {
-    // Reuse version catalog from the main build.
-    versionCatalogs {
-        create("libs", { from(files("../gradle/libs.versions.toml")) })
-    }
-}
-
-rootProject.name = "aws-build-tool"
diff --git a/aws-build-tool/src/main/kotlin/buildlogic.kotlin-application-conventions.gradle.kts b/aws-build-tool/src/main/kotlin/buildlogic.kotlin-application-conventions.gradle.kts
deleted file mode 100644
index 9cfe5f2..0000000
--- a/aws-build-tool/src/main/kotlin/buildlogic.kotlin-application-conventions.gradle.kts
+++ /dev/null
@@ -1,13 +0,0 @@
-/*
- * This file was generated by the Gradle 'init' task.
- *
- * This project uses @Incubating APIs which are subject to change.
- */
-
-plugins {
-    // Apply the common convention plugin for shared build configuration between library and application projects.
-    id("buildlogic.kotlin-common-conventions")
-
-    // Apply the application plugin to add support for building a CLI application in Java.
-    application
-}
diff --git a/aws-build-tool/src/main/kotlin/buildlogic.kotlin-common-conventions.gradle.kts b/aws-build-tool/src/main/kotlin/buildlogic.kotlin-common-conventions.gradle.kts
deleted file mode 100644
index 180b001..0000000
--- a/aws-build-tool/src/main/kotlin/buildlogic.kotlin-common-conventions.gradle.kts
+++ /dev/null
@@ -1,58 +0,0 @@
-import org.gradle.api.artifacts.VersionCatalogsExtension
-import org.jetbrains.kotlin.gradle.dsl.JvmDefaultMode
-
-/*
- * Shared build configuration for the library modules of this build.
- */
-plugins {
-    // Apply the org.jetbrains.kotlin.jvm Plugin to add support for Kotlin.
-    id("org.jetbrains.kotlin.jvm")
-}
-
-repositories {
-    // Use Maven Central for resolving dependencies.
-    mavenCentral()
-}
-
-// Precompiled script plugins have no generated "libs" accessor, so the version catalog
-// is looked up explicitly from the consuming build.
-val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
-
-dependencies {
-    constraints {
-        // Define dependency versions as constraints
-        implementation("org.apache.commons:commons-text:1.14.0")
-    }
-
-    testImplementation(libs.findBundle("kotest").get())
-    testImplementation(libs.findBundle("kotlintest").get())
-    testRuntimeOnly(libs.findLibrary("junitPlatformLauncher").get())
-}
-
-testing {
-    suites {
-        // Configure the built-in test suite
-        val test = getByName<JvmTestSuite>("test")
-        test.useJUnitJupiter(libs.findVersion("junit").get().requiredVersion)
-    }
-}
-
-kotlin {
-    compilerOptions {
-        jvmDefault = JvmDefaultMode.NO_COMPATIBILITY
-        progressiveMode.set(true)
-        freeCompilerArgs.addAll(
-            "-Xjsr305=strict",
-        )
-        optIn.addAll(
-            "kotlin.RequiresOptIn",
-        )
-    }
-}
-
-// Apply a specific Java toolchain to ease working on different environments.
-java {
-    toolchain {
-        languageVersion = JavaLanguageVersion.of(libs.findVersion("java").get().requiredVersion.toInt())
-    }
-}
diff --git a/aws-build-tool/src/main/kotlin/buildlogic.kotlin-library-conventions.gradle.kts b/aws-build-tool/src/main/kotlin/buildlogic.kotlin-library-conventions.gradle.kts
deleted file mode 100644
index 0e4a5c4..0000000
--- a/aws-build-tool/src/main/kotlin/buildlogic.kotlin-library-conventions.gradle.kts
+++ /dev/null
@@ -1,13 +0,0 @@
-/*
- * This file was generated by the Gradle 'init' task.
- *
- * This project uses @Incubating APIs which are subject to change.
- */
-
-plugins {
-    // Apply the common convention plugin for shared build configuration between library and application projects.
-    id("buildlogic.kotlin-common-conventions")
-
-    // Apply the java-library plugin for API and implementation separation.
-    `java-library`
-}
diff --git a/core/build.gradle.kts b/core/build.gradle.kts
new file mode 100644
index 0000000..094dc30
--- /dev/null
+++ b/core/build.gradle.kts
@@ -0,0 +1,58 @@
+plugins {
+    alias(libs.plugins.kotlinJvm)
+    alias(libs.plugins.dependencyManagement)
+    alias(libs.plugins.kotlinter)
+    `java-library`
+}
+
+group = "io.github.hantsy"
+version = "0.1.0-SNAPSHOT"
+
+base { archivesName.set("awskotlin-core") }
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
+    testImplementation(libs.springBootStarterTest)
+    testImplementation(libs.bundles.kotest)
+    testImplementation(libs.bundles.mockk)
+    testImplementation(libs.bundles.kotlintest)
+    testRuntimeOnly(libs.junitPlatformLauncher)
+}
+
+kotlin { jvmToolchain(libs.versions.java.get().toInt()) }
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
diff --git a/core/src/main/resources/META-INF/spring-configuration-metadata.json b/core/src/main/resources/META-INF/spring-configuration-metadata.json
new file mode 100644
index 0000000..d22573a
--- /dev/null
+++ b/core/src/main/resources/META-INF/spring-configuration-metadata.json
@@ -0,0 +1,48 @@
+{
+  "groups": [
+    {
+      "name": "awskotlin",
+      "type": "io.github.hantsy.awskotlin.core.AwsKotlinProperties",
+      "sourceType": "io.github.hantsy.awskotlin.core.AwsKotlinProperties"
+    }
+  ],
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
diff --git a/gradle.properties b/gradle.properties
index 5154008..f1c6011 100644
--- a/gradle.properties
+++ b/gradle.properties
@@ -1,7 +1,15 @@
 # This file was generated by the Gradle 'init' task.
 # https://docs.gradle.org/current/userguide/build_environment.html#sec:gradle_configuration_properties
 
 org.gradle.configuration-cache=true
 org.gradle.parallel=true
 org.gradle.caching=true
 
+# Workaround for KGP 2.4.10 + Gradle 9.9.2: Gradle ships Kotlin 2.4.21 compiler classes
+# in its parent classloader, which breaks the Build Tools API compilation path
+# (NoSuchMethodError: ArgumentsToStrings.toArgumentStrings$default). This affects regular
+# compileKotlin, not only kapt, so it is still required after kapt removal. The legacy
+# compiler runner avoids that path. Deprecated in KGP 2.4.x, removed in 2.5.0.
+# Set once here so it applies to every subproject.
+kotlin.compiler.runViaBuildToolsApi=false
+
diff --git a/gradle/libs.versions.toml b/gradle/libs.versions.toml
index 3400e81..f8806b0 100644
--- a/gradle/libs.versions.toml
+++ b/gradle/libs.versions.toml
@@ -1,16 +1,17 @@
 [versions]
 java = "25"
 springboot = "4.2.0"
 springDependencyManagement = "1.1.7"
 kotlin = "2.4.10"
 kotlinCoroutines = "1.11.0"
+kotlinxSerialization = "1.9.0"
 kotlinter = "5.7.0"
 testLogger = "4.0.0"
 kotest = "6.2.7"
 mockk = "1.14.12"
 springmockk = "5.0.1"
 awaitility = "4.3.0"
 okhttp = "5.5.1"
 awsSdkKotlin = "1.9.45"
 junit = "6.1.3"
 
@@ -20,31 +21,40 @@ kotlin-gradle-plugin = { module = "org.jetbrains.kotlin:kotlin-gradle-plugin", v
 starterWebflux = { module = "org.springframework.boot:spring-boot-starter-webflux" }
 starterWebclient = { module = "org.springframework.boot:spring-boot-starter-webclient" }
 
 # Kotlin support
 jacksonKotlin = { module = "tools.jackson.module:jackson-module-kotlin" }
 reactorKotlinExtensions = { module = "io.projectreactor.kotlin:reactor-kotlin-extensions" }
 kotlinReflect = { module = "org.jetbrains.kotlin:kotlin-reflect" }
 kotlinStdlib = { module = "org.jetbrains.kotlin:kotlin-stdlib" }
 kotlinCoroutinesCore = { module = "org.jetbrains.kotlinx:kotlinx-coroutines-core", version.ref = "kotlinCoroutines" }
 kotlinCoroutinesReactor = { module = "org.jetbrains.kotlinx:kotlinx-coroutines-reactor", version.ref = "kotlinCoroutines" }
+kotlinxSerializationJson = { module = "org.jetbrains.kotlinx:kotlinx-serialization-json", version.ref = "kotlinxSerialization" }
 
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
+springContext = { module = "org.springframework:spring-context" }
+springAop = { module = "org.springframework:spring-aop" }
+springBootAutoconfigure = { module = "org.springframework.boot:spring-boot-autoconfigure" }
+springBootStarterTest = { module = "org.springframework.boot:spring-boot-starter-test" }
 
 # Test dependencies
 webfluxTest = { module = "org.springframework.boot:spring-boot-starter-webflux-test" }
 reactorTest = { module = "io.projectreactor:reactor-test" }
 testcontainersJunitJupiter = { module = "org.testcontainers:testcontainers-junit-jupiter" }
 testcontainersLocalstack = { module = "org.testcontainers:testcontainers-localstack" }
 springBootTestcontainers = { module = "org.springframework.boot:spring-boot-testcontainers" }
 kotlinTestJunit5 = { module = "org.jetbrains.kotlin:kotlin-test-junit5" }
 kotlinCoroutinesTest = { module = "org.jetbrains.kotlinx:kotlinx-coroutines-test", version.ref = "kotlinCoroutines" }
 kotestAssertionsCoreJvm = { module = "io.kotest:kotest-assertions-core-jvm", version.ref = "kotest" }
@@ -76,12 +86,13 @@ testcontainers = ["testcontainersJunitJupiter", "testcontainersLocalstack", "spr
 kotest = ["kotestAssertionsCoreJvm"]
 mockk = ["mockk", "springmockk"]
 awaitility = ["awaitility", "awaitilityKotlin"]
 kotlintest = ["kotlinTestJunit5"]
 
 [plugins]
 springBoot = { id = "org.springframework.boot", version.ref = "springboot" }
 dependencyManagement = { id = "io.spring.dependency-management", version.ref = "springDependencyManagement" }
 kotlinJvm = { id = "org.jetbrains.kotlin.jvm", version.ref = "kotlin" }
 kotlinPluginSpring = { id = "org.jetbrains.kotlin.plugin.spring", version.ref = "kotlin" }
+kotlinPluginSerialization = { id = "org.jetbrains.kotlin.plugin.serialization", version.ref = "kotlin" }
 testLogger = { id = "com.adarshr.test-logger", version.ref = "testLogger" }
 kotlinter = { id = "org.jmailen.kotlinter", version.ref = "kotlinter" }
diff --git a/list/build.gradle.kts b/list/build.gradle.kts
deleted file mode 100644
index d9752d9..0000000
--- a/list/build.gradle.kts
+++ /dev/null
@@ -1,9 +0,0 @@
-/*
- * This file was generated by the Gradle 'init' task.
- *
- * This project uses @Incubating APIs which are subject to change.
- */
-
-plugins {
-    id("buildlogic.kotlin-library-conventions")
-}
diff --git a/list/src/main/kotlin/org/example/list/LinkedList.kt b/list/src/main/kotlin/org/example/list/LinkedList.kt
deleted file mode 100644
index dbe1222..0000000
--- a/list/src/main/kotlin/org/example/list/LinkedList.kt
+++ /dev/null
@@ -1,86 +0,0 @@
-/*
- * This source file was generated by the Gradle 'init' task
- */
-package org.example.list
-
-class LinkedList {
-    private var head: Node? = null
-
-    fun add(element: String) {
-        val newNode = Node(element)
-
-        val it = tail(head)
-        if (it == null) {
-            head = newNode
-        } else {
-            it.next = newNode
-        }
-    }
-
-    private fun tail(head: Node?): Node? {
-        var it: Node?
-
-        it = head
-        while (it?.next != null) {
-            it = it.next
-        }
-
-        return it
-    }
-
-    fun remove(element: String): Boolean {
-        var result = false
-        var previousIt: Node? = null
-        var it: Node? = head
-        while (!result && it != null) {
-            if (0 == element.compareTo(it.data)) {
-                result = true
-                unlink(previousIt, it)
-                break
-            }
-            previousIt = it
-            it = it.next
-        }
-
-        return result
-    }
-
-    private fun unlink(previousIt: Node?, currentIt: Node) {
-        if (currentIt == head) {
-            head = currentIt.next
-        } else {
-            previousIt?.next = currentIt.next
-        }
-    }
-
-    fun size(): Int {
-        var size = 0
-
-        var it = head
-        while (it != null) {
-            ++size
-            it = it.next
-        }
-
-        return size
-    }
-
-    fun get(idx: Int): String {
-        var index = idx
-        var it = head
-        while (index > 0 && it != null) {
-            it = it.next
-            index--
-        }
-
-        if (it == null) {
-            throw IndexOutOfBoundsException("Index is out of range")
-        }
-
-        return it.data
-    }
-
-    private data class Node(val data: String) {
-        var next: Node? = null
-    }
-}
diff --git a/list/src/test/kotlin/org/example/list/LinkedListTest.kt b/list/src/test/kotlin/org/example/list/LinkedListTest.kt
deleted file mode 100644
index d80cd77..0000000
--- a/list/src/test/kotlin/org/example/list/LinkedListTest.kt
+++ /dev/null
@@ -1,52 +0,0 @@
-package org.example.list
-
-import io.kotest.matchers.shouldBe
-import org.junit.jupiter.api.Test
-
-class LinkedListTest {
-    @Test
-    fun `a new list is empty`() {
-        val list = LinkedList()
-
-        list.size() shouldBe 0
-    }
-
-    @Test
-    fun `adds the elements in order`() {
-        val list = LinkedList()
-
-        list.add("one")
-        list.add("two")
-
-        list.size() shouldBe 2
-        list.get(0) shouldBe "one"
-        list.get(1) shouldBe "two"
-    }
-
-    @Test
-    fun `removes an existing element`() {
-        val list = LinkedList()
-
-        list.add("one")
-        list.add("two")
-
-        list.remove("one") shouldBe true
-
-        list.size() shouldBe 1
-        list.get(0) shouldBe "two"
-
-        list.remove("two") shouldBe true
-        list.size() shouldBe 0
-    }
-
-    @Test
-    fun `does not remove a missing element`() {
-        val list = LinkedList()
-
-        list.add("one")
-        list.add("two")
-
-        list.remove("three") shouldBe false
-        list.size() shouldBe 2
-    }
-}
diff --git a/s3/build.gradle.kts b/s3/build.gradle.kts
new file mode 100644
index 0000000..cbf1d67
--- /dev/null
+++ b/s3/build.gradle.kts
@@ -0,0 +1,57 @@
+plugins {
+    alias(libs.plugins.kotlinJvm)
+    alias(libs.plugins.dependencyManagement)
+    alias(libs.plugins.kotlinter)
+    `java-library`
+}
+
+group = "io.github.hantsy"
+version = "0.1.0-SNAPSHOT"
+
+base { archivesName.set("awskotlin-s3") }
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
+    api(libs.awsSdkKotlinS3)
+    implementation(libs.kotlinStdlib)
+    implementation(libs.kotlinCoroutinesCore)
+    testImplementation(libs.springBootStarterTest)
+    testImplementation(libs.bundles.kotest)
+    testImplementation(libs.bundles.mockk)
+    testImplementation(libs.bundles.kotlintest)
+    testImplementation(libs.kotlinCoroutinesTest)
+    testImplementation(libs.bundles.testcontainers)
+    testRuntimeOnly(libs.junitPlatformLauncher)
+}
+
+kotlin { jvmToolchain(libs.versions.java.get().toInt()) }
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
diff --git a/s3/src/main/kotlin/io/github/hantsy/awskotlin/s3/S3BucketOperations.kt b/s3/src/main/kotlin/io/github/hantsy/awskotlin/s3/S3BucketOperations.kt
new file mode 100644
index 0000000..f26bd24
--- /dev/null
+++ b/s3/src/main/kotlin/io/github/hantsy/awskotlin/s3/S3BucketOperations.kt
@@ -0,0 +1,38 @@
+package io.github.hantsy.awskotlin.s3
+
+import aws.sdk.kotlin.services.s3.S3Client
+import aws.sdk.kotlin.services.s3.headBucket
+import aws.sdk.kotlin.services.s3.listObjectsV2
+import aws.sdk.kotlin.services.s3.model.ListObjectsV2Response
+import aws.sdk.kotlin.services.s3.model.NotFound
+
+/**
+ * Bucket-scoped S3 operations. Created via [S3Template.bucket].
+ */
+class S3BucketOperations internal constructor(private val client: S3Client, private val bucket: String,) {
+    /**
+     * Returns `true` when the bucket exists and is accessible.
+     *
+     * Only [NotFound] (the SDK's modeled HeadBucket 404 error) maps to `false`;
+     * every other SDK exception propagates to the caller.
+     */
+    suspend fun exists(): Boolean = try {
+        client.headBucket { bucket = this@S3BucketOperations.bucket }
+        true
+    } catch (e: NotFound) {
+        false
+    }
+
+    /**
+     * Lists objects in the bucket, returning a single SDK page (no pagination).
+     */
+    suspend fun listObjects(prefix: String? = null, maxKeys: Int? = null,): ListObjectsV2Response =
+        client.listObjectsV2 {
+            bucket = this@S3BucketOperations.bucket
+            this.prefix = prefix
+            this.maxKeys = maxKeys
+        }
+
+    /** Returns operations scoped to the object [key] in this bucket. No request is made. */
+    fun `object`(key: String): S3ObjectOperations = S3ObjectOperations(client, bucket, key)
+}
diff --git a/s3/src/main/kotlin/io/github/hantsy/awskotlin/s3/S3ObjectOperations.kt b/s3/src/main/kotlin/io/github/hantsy/awskotlin/s3/S3ObjectOperations.kt
new file mode 100644
index 0000000..16afe07
--- /dev/null
+++ b/s3/src/main/kotlin/io/github/hantsy/awskotlin/s3/S3ObjectOperations.kt
@@ -0,0 +1,46 @@
+package io.github.hantsy.awskotlin.s3
+
+import aws.sdk.kotlin.services.s3.S3Client
+import aws.sdk.kotlin.services.s3.deleteObject
+import aws.sdk.kotlin.services.s3.model.DeleteObjectResponse
+import aws.sdk.kotlin.services.s3.model.GetObjectRequest
+import aws.sdk.kotlin.services.s3.model.PutObjectResponse
+import aws.sdk.kotlin.services.s3.putObject
+import aws.smithy.kotlin.runtime.content.ByteStream
+import aws.smithy.kotlin.runtime.content.toByteArray
+
+/**
+ * Object-scoped S3 operations. Created via [S3BucketOperations.object].
+ */
+class S3ObjectOperations internal constructor(
+    private val client: S3Client,
+    private val bucket: String,
+    private val key: String,
+) {
+    /** Uploads [body] as the object content, optionally setting the content type. */
+    suspend fun put(body: ByteStream, contentType: String? = null,): PutObjectResponse = client.putObject {
+        bucket = this@S3ObjectOperations.bucket
+        key = this@S3ObjectOperations.key
+        this.body = body
+        this.contentType = contentType
+    }
+
+    /**
+     * Downloads the object content, or returns `null` when the response has no body.
+     *
+     * The streaming body is consumed inside the SDK response-transform overload so the
+     * underlying HTTP connection is released before this method returns.
+     */
+    suspend fun get(): ByteArray? = client.getObject(
+        GetObjectRequest {
+            bucket = this@S3ObjectOperations.bucket
+            key = this@S3ObjectOperations.key
+        },
+    ) { it.body?.toByteArray() }
+
+    /** Deletes the object. */
+    suspend fun delete(): DeleteObjectResponse = client.deleteObject {
+        bucket = this@S3ObjectOperations.bucket
+        key = this@S3ObjectOperations.key
+    }
+}
diff --git a/s3/src/main/kotlin/io/github/hantsy/awskotlin/s3/S3Template.kt b/s3/src/main/kotlin/io/github/hantsy/awskotlin/s3/S3Template.kt
new file mode 100644
index 0000000..a74d7de
--- /dev/null
+++ b/s3/src/main/kotlin/io/github/hantsy/awskotlin/s3/S3Template.kt
@@ -0,0 +1,14 @@
+package io.github.hantsy.awskotlin.s3
+
+import aws.sdk.kotlin.services.s3.S3Client
+
+/**
+ * Entry point for fluent S3 operations backed by the AWS SDK for Kotlin [S3Client].
+ *
+ * Obtain bucket-scoped operations with [bucket], then object-scoped operations
+ * with [S3BucketOperations.object].
+ */
+class S3Template(private val client: S3Client,) {
+    /** Returns operations scoped to [bucket]. No request is made. */
+    fun bucket(bucket: String): S3BucketOperations = S3BucketOperations(client, bucket)
+}
diff --git a/s3/src/test/kotlin/io/github/hantsy/awskotlin/s3/S3TemplateLocalStackTest.kt b/s3/src/test/kotlin/io/github/hantsy/awskotlin/s3/S3TemplateLocalStackTest.kt
new file mode 100644
index 0000000..2994d43
--- /dev/null
+++ b/s3/src/test/kotlin/io/github/hantsy/awskotlin/s3/S3TemplateLocalStackTest.kt
@@ -0,0 +1,116 @@
+package io.github.hantsy.awskotlin.s3
+
+import aws.sdk.kotlin.runtime.auth.credentials.StaticCredentialsProvider
+import aws.sdk.kotlin.services.s3.S3Client
+import aws.sdk.kotlin.services.s3.createBucket
+import aws.sdk.kotlin.services.s3.deleteBucket
+import aws.smithy.kotlin.runtime.content.ByteStream
+import aws.smithy.kotlin.runtime.net.url.Url
+import io.kotest.matchers.collections.shouldContainExactly
+import io.kotest.matchers.nulls.shouldNotBeNull
+import io.kotest.matchers.shouldBe
+import kotlinx.coroutines.runBlocking
+import org.junit.jupiter.api.AfterAll
+import org.junit.jupiter.api.Assumptions.assumeTrue
+import org.junit.jupiter.api.BeforeAll
+import org.junit.jupiter.api.Test
+import org.junit.jupiter.api.TestInstance
+import org.testcontainers.DockerClientFactory
+import org.testcontainers.localstack.LocalStackContainer
+import org.testcontainers.utility.DockerImageName
+import java.util.UUID
+
+/**
+ * End-to-end round trip against LocalStack.
+ *
+ * Skipped unless Docker is available and LOCALSTACK_AUTH_TOKEN is set
+ * (LocalStack 4 requires a license token).
+ */
+@TestInstance(TestInstance.Lifecycle.PER_CLASS)
+class S3TemplateLocalStackTest {
+    private lateinit var container: LocalStackContainer
+    private lateinit var client: S3Client
+    private lateinit var template: S3Template
+    private val bucketName = "awskotlin-s3-test-${UUID.randomUUID().toString().substring(0, 8)}"
+
+    @BeforeAll
+    fun startLocalStack() {
+        val authToken = System.getenv("LOCALSTACK_AUTH_TOKEN")?.takeIf { it.isNotBlank() }
+        assumeTrue(authToken != null, "LOCALSTACK_AUTH_TOKEN is not set; skipping LocalStack tests")
+        assumeTrue(
+            DockerClientFactory.instance().isDockerAvailable,
+            "Docker is not available; skipping LocalStack tests"
+        )
+
+        container =
+            LocalStackContainer(DockerImageName.parse("localstack/localstack:4"))
+                .withServices("s3")
+                .withEnv("LOCALSTACK_AUTH_TOKEN", authToken)
+        container.start()
+
+        client =
+            S3Client {
+                region = "us-east-1"
+                credentialsProvider =
+                    StaticCredentialsProvider {
+                        accessKeyId = "test"
+                        secretAccessKey = "test"
+                    }
+                endpointUrl = Url.parse("http://${container.host}:${container.firstMappedPort}")
+                forcePathStyle = true
+            }
+        template = S3Template(client)
+
+        runBlocking {
+            client.createBucket { bucket = bucketName }
+        }
+    }
+
+    @AfterAll
+    fun stopLocalStack() {
+        if (::client.isInitialized) {
+            runBlocking { runCatching { client.deleteBucket { bucket = bucketName } } }
+            client.close()
+        }
+        if (::container.isInitialized) {
+            container.stop()
+        }
+    }
+
+    @Test
+    fun `put get delete list exists round trip`() = runBlocking<Unit> {
+        val bucket = template.bucket(bucketName)
+        bucket.exists() shouldBe true
+
+        val key = "docs/greeting.txt"
+        val bytes = "hello localstack".encodeToByteArray()
+        bucket.`object`(key).put(ByteStream.fromBytes(bytes), contentType = "text/plain")
+
+        bucket.`object`(key).get() shouldBe bytes
+
+        val listed = bucket.listObjects(prefix = "docs/")
+        listed.contents.shouldNotBeNull().map { it.key } shouldContainExactly listOf(key)
+
+        bucket.`object`(key).delete()
+
+        val afterDelete = bucket.listObjects(prefix = "docs/")
+        (afterDelete.contents ?: emptyList()) shouldBe emptyList()
+    }
+
+    @Test
+    fun `exists returns false for a missing bucket`() = runBlocking<Unit> {
+        template.bucket("awskotlin-missing-${UUID.randomUUID().toString().substring(0, 8)}").exists() shouldBe false
+    }
+
+    @Test
+    fun `get returns the exact bytes stored`() = runBlocking<Unit> {
+        val key = "binary/data.bin"
+        val bytes = ByteArray(256) { it.toByte() }
+        val objectOps = template.bucket(bucketName).`object`(key)
+        objectOps.put(ByteStream.fromBytes(bytes))
+
+        objectOps.get() shouldBe bytes
+
+        objectOps.delete()
+    }
+}
diff --git a/s3/src/test/kotlin/io/github/hantsy/awskotlin/s3/S3TemplateTest.kt b/s3/src/test/kotlin/io/github/hantsy/awskotlin/s3/S3TemplateTest.kt
new file mode 100644
index 0000000..513d4cd
--- /dev/null
+++ b/s3/src/test/kotlin/io/github/hantsy/awskotlin/s3/S3TemplateTest.kt
@@ -0,0 +1,153 @@
+package io.github.hantsy.awskotlin.s3
+
+import aws.sdk.kotlin.services.s3.S3Client
+import aws.sdk.kotlin.services.s3.model.DeleteObjectRequest
+import aws.sdk.kotlin.services.s3.model.DeleteObjectResponse
+import aws.sdk.kotlin.services.s3.model.GetObjectRequest
+import aws.sdk.kotlin.services.s3.model.GetObjectResponse
+import aws.sdk.kotlin.services.s3.model.HeadBucketRequest
+import aws.sdk.kotlin.services.s3.model.HeadBucketResponse
+import aws.sdk.kotlin.services.s3.model.ListObjectsV2Request
+import aws.sdk.kotlin.services.s3.model.ListObjectsV2Response
+import aws.sdk.kotlin.services.s3.model.NotFound
+import aws.sdk.kotlin.services.s3.model.PutObjectRequest
+import aws.sdk.kotlin.services.s3.model.PutObjectResponse
+import aws.sdk.kotlin.services.s3.model.S3Exception
+import aws.smithy.kotlin.runtime.content.ByteStream
+import io.kotest.assertions.throwables.shouldThrow
+import io.kotest.matchers.nulls.shouldBeNull
+import io.kotest.matchers.shouldBe
+import io.mockk.coEvery
+import io.mockk.mockk
+import io.mockk.slot
+import kotlinx.coroutines.test.runTest
+import org.junit.jupiter.api.Test
+import aws.sdk.kotlin.services.s3.model.Object as S3Object
+
+class S3TemplateTest {
+    private val client = mockk<S3Client>()
+    private val template = S3Template(client)
+
+    @Test
+    fun `bucket exists returns true on successful headBucket`() = runTest {
+        val request = slot<HeadBucketRequest>()
+        coEvery { client.headBucket(capture(request)) } returns HeadBucketResponse { }
+
+        template.bucket("test-bucket").exists() shouldBe true
+        request.captured.bucket shouldBe "test-bucket"
+    }
+
+    @Test
+    fun `bucket exists returns false on NotFound`() = runTest {
+        coEvery { client.headBucket(any<HeadBucketRequest>()) } throws NotFound { }
+
+        template.bucket("missing-bucket").exists() shouldBe false
+    }
+
+    @Test
+    fun `bucket exists propagates other SDK exceptions`() = runTest {
+        val failure = S3Exception("access denied")
+        coEvery { client.headBucket(any<HeadBucketRequest>()) } throws failure
+
+        val thrown = shouldThrow<S3Exception> { template.bucket("test-bucket").exists() }
+        thrown shouldBe failure
+    }
+
+    @Test
+    fun `listObjects returns the SDK response and passes prefix and maxKeys`() = runTest {
+        val request = slot<ListObjectsV2Request>()
+        val response =
+            ListObjectsV2Response {
+                contents = listOf(S3Object { key = "docs/readme.txt" })
+            }
+        coEvery { client.listObjectsV2(capture(request)) } returns response
+
+        val result = template.bucket("test-bucket").listObjects(prefix = "docs/", maxKeys = 10)
+
+        result shouldBe response
+        request.captured.bucket shouldBe "test-bucket"
+        request.captured.prefix shouldBe "docs/"
+        request.captured.maxKeys shouldBe 10
+    }
+
+    @Test
+    fun `listObjects without arguments leaves prefix and maxKeys unset`() = runTest {
+        val request = slot<ListObjectsV2Request>()
+        coEvery { client.listObjectsV2(capture(request)) } returns ListObjectsV2Response { }
+
+        template.bucket("test-bucket").listObjects()
+
+        request.captured.bucket shouldBe "test-bucket"
+        request.captured.prefix.shouldBeNull()
+        request.captured.maxKeys.shouldBeNull()
+    }
+
+    @Test
+    fun `put builds PutObjectRequest with bucket key body and contentType`() = runTest {
+        val request = slot<PutObjectRequest>()
+        val response = PutObjectResponse { eTag = "etag-1" }
+        coEvery { client.putObject(capture(request)) } returns response
+        val body = ByteStream.fromBytes("hello".encodeToByteArray())
+
+        val result = template.bucket("test-bucket").`object`("greeting.txt").put(body, contentType = "text/plain")
+
+        result shouldBe response
+        request.captured.bucket shouldBe "test-bucket"
+        request.captured.key shouldBe "greeting.txt"
+        request.captured.body shouldBe body
+        request.captured.contentType shouldBe "text/plain"
+    }
+
+    @Test
+    fun `put without contentType leaves it unset`() = runTest {
+        val request = slot<PutObjectRequest>()
+        coEvery { client.putObject(capture(request)) } returns PutObjectResponse { }
+
+        template.bucket("test-bucket").`object`("greeting.txt").put(ByteStream.fromBytes(byteArrayOf()))
+
+        request.captured.contentType.shouldBeNull()
+    }
+
+    @Test
+    fun `delete builds DeleteObjectRequest with bucket and key`() = runTest {
+        val request = slot<DeleteObjectRequest>()
+        val response = DeleteObjectResponse { }
+        coEvery { client.deleteObject(capture(request)) } returns response
+
+        val result = template.bucket("test-bucket").`object`("greeting.txt").delete()
+
+        result shouldBe response
+        request.captured.bucket shouldBe "test-bucket"
+        request.captured.key shouldBe "greeting.txt"
+    }
+
+    @Test
+    fun `get returns bytes consumed inside the response transform`() = runTest {
+        val bytes = "hello world".encodeToByteArray()
+        val request = slot<GetObjectRequest>()
+        coEvery {
+            client.getObject(capture(request), any<suspend (GetObjectResponse) -> ByteArray?>())
+        } coAnswers {
+            val transform = secondArg<suspend (GetObjectResponse) -> ByteArray?>()
+            transform(GetObjectResponse { body = ByteStream.fromBytes(bytes) })
+        }
+
+        val result = template.bucket("test-bucket").`object`("greeting.txt").get()
+
+        result shouldBe bytes
+        request.captured.bucket shouldBe "test-bucket"
+        request.captured.key shouldBe "greeting.txt"
+    }
+
+    @Test
+    fun `get returns null when the response has no body`() = runTest {
+        coEvery {
+            client.getObject(any<GetObjectRequest>(), any<suspend (GetObjectResponse) -> ByteArray?>())
+        } coAnswers {
+            val transform = secondArg<suspend (GetObjectResponse) -> ByteArray?>()
+            transform(GetObjectResponse { })
+        }
+
+        template.bucket("test-bucket").`object`("greeting.txt").get().shouldBeNull()
+    }
+}
diff --git a/secrets-manager/build.gradle.kts b/secrets-manager/build.gradle.kts
new file mode 100644
index 0000000..f6d3e89
--- /dev/null
+++ b/secrets-manager/build.gradle.kts
@@ -0,0 +1,55 @@
+plugins {
+    alias(libs.plugins.kotlinJvm)
+    alias(libs.plugins.dependencyManagement)
+    alias(libs.plugins.kotlinter)
+    `java-library`
+}
+
+group = "io.github.hantsy"
+version = "0.1.0-SNAPSHOT"
+
+base { archivesName.set("awskotlin-secrets-manager") }
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
+    api(libs.awsSdkKotlinSecretManager)
+    implementation(libs.kotlinStdlib)
+    implementation(libs.kotlinCoroutinesCore)
+    testImplementation(libs.bundles.kotest)
+    testImplementation(libs.bundles.mockk)
+    testImplementation(libs.bundles.kotlintest)
+    testImplementation(libs.kotlinCoroutinesTest)
+    testRuntimeOnly(libs.junitPlatformLauncher)
+}
+
+kotlin { jvmToolchain(libs.versions.java.get().toInt()) }
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
diff --git a/secrets-manager/src/main/kotlin/io/github/hantsy/awskotlin/secretsmanager/SecretOperations.kt b/secrets-manager/src/main/kotlin/io/github/hantsy/awskotlin/secretsmanager/SecretOperations.kt
new file mode 100644
index 0000000..aafa854
--- /dev/null
+++ b/secrets-manager/src/main/kotlin/io/github/hantsy/awskotlin/secretsmanager/SecretOperations.kt
@@ -0,0 +1,45 @@
+package io.github.hantsy.awskotlin.secretsmanager
+
+import aws.sdk.kotlin.services.secretsmanager.SecretsManagerClient
+import aws.sdk.kotlin.services.secretsmanager.createSecret
+import aws.sdk.kotlin.services.secretsmanager.deleteSecret
+import aws.sdk.kotlin.services.secretsmanager.getSecretValue
+import aws.sdk.kotlin.services.secretsmanager.model.CreateSecretResponse
+import aws.sdk.kotlin.services.secretsmanager.model.DeleteSecretResponse
+import aws.sdk.kotlin.services.secretsmanager.model.GetSecretValueResponse
+import aws.sdk.kotlin.services.secretsmanager.model.UpdateSecretResponse
+import aws.sdk.kotlin.services.secretsmanager.updateSecret
+
+/**
+ * Secret-scoped Secrets Manager operations. Created via [SecretsManagerTemplate.secret].
+ *
+ * Each method maps directly to the matching SDK request and propagates SDK exceptions
+ * unchanged.
+ */
+class SecretOperations internal constructor(private val client: SecretsManagerClient, private val secretId: String,) {
+    /** Retrieves the secret value. */
+    suspend fun get(): GetSecretValueResponse = client.getSecretValue {
+        secretId = this@SecretOperations.secretId
+    }
+
+    /** Creates a new secret named [secretId] with the given [secretValue]. */
+    suspend fun create(secretValue: String): CreateSecretResponse = client.createSecret {
+        name = this@SecretOperations.secretId
+        secretString = secretValue
+    }
+
+    /** Updates the value of the existing secret [secretId]. */
+    suspend fun update(secretValue: String): UpdateSecretResponse = client.updateSecret {
+        secretId = this@SecretOperations.secretId
+        secretString = secretValue
+    }
+
+    /**
+     * Deletes the secret [secretId]. When [forceDeleteWithoutRecovery] is `true` the secret
+     * is deleted immediately without a recovery window; otherwise the SDK default applies.
+     */
+    suspend fun delete(forceDeleteWithoutRecovery: Boolean = false): DeleteSecretResponse = client.deleteSecret {
+        secretId = this@SecretOperations.secretId
+        this.forceDeleteWithoutRecovery = forceDeleteWithoutRecovery
+    }
+}
diff --git a/secrets-manager/src/main/kotlin/io/github/hantsy/awskotlin/secretsmanager/SecretsManagerTemplate.kt b/secrets-manager/src/main/kotlin/io/github/hantsy/awskotlin/secretsmanager/SecretsManagerTemplate.kt
new file mode 100644
index 0000000..d3811ac
--- /dev/null
+++ b/secrets-manager/src/main/kotlin/io/github/hantsy/awskotlin/secretsmanager/SecretsManagerTemplate.kt
@@ -0,0 +1,14 @@
+package io.github.hantsy.awskotlin.secretsmanager
+
+import aws.sdk.kotlin.services.secretsmanager.SecretsManagerClient
+
+/**
+ * Entry point for fluent Secrets Manager operations backed by the AWS SDK for Kotlin
+ * [SecretsManagerClient].
+ *
+ * Obtain secret-scoped operations with [secret].
+ */
+class SecretsManagerTemplate(private val client: SecretsManagerClient) {
+    /** Returns operations scoped to the secret [secretId] (name or ARN). No request is made. */
+    fun secret(secretId: String): SecretOperations = SecretOperations(client, secretId)
+}
diff --git a/secrets-manager/src/test/kotlin/io/github/hantsy/awskotlin/secretsmanager/SecretsManagerTemplateTest.kt b/secrets-manager/src/test/kotlin/io/github/hantsy/awskotlin/secretsmanager/SecretsManagerTemplateTest.kt
new file mode 100644
index 0000000..a1533ee
--- /dev/null
+++ b/secrets-manager/src/test/kotlin/io/github/hantsy/awskotlin/secretsmanager/SecretsManagerTemplateTest.kt
@@ -0,0 +1,105 @@
+package io.github.hantsy.awskotlin.secretsmanager
+
+import aws.sdk.kotlin.services.secretsmanager.SecretsManagerClient
+import aws.sdk.kotlin.services.secretsmanager.model.CreateSecretRequest
+import aws.sdk.kotlin.services.secretsmanager.model.CreateSecretResponse
+import aws.sdk.kotlin.services.secretsmanager.model.DeleteSecretRequest
+import aws.sdk.kotlin.services.secretsmanager.model.DeleteSecretResponse
+import aws.sdk.kotlin.services.secretsmanager.model.GetSecretValueRequest
+import aws.sdk.kotlin.services.secretsmanager.model.GetSecretValueResponse
+import aws.sdk.kotlin.services.secretsmanager.model.ResourceNotFoundException
+import aws.sdk.kotlin.services.secretsmanager.model.SecretsManagerException
+import aws.sdk.kotlin.services.secretsmanager.model.UpdateSecretRequest
+import aws.sdk.kotlin.services.secretsmanager.model.UpdateSecretResponse
+import io.kotest.assertions.throwables.shouldThrow
+import io.kotest.matchers.shouldBe
+import io.mockk.coEvery
+import io.mockk.mockk
+import io.mockk.slot
+import kotlinx.coroutines.test.runTest
+import org.junit.jupiter.api.Test
+
+class SecretsManagerTemplateTest {
+    private val client = mockk<SecretsManagerClient>()
+    private val template = SecretsManagerTemplate(client)
+
+    @Test
+    fun `get builds GetSecretValueRequest with secretId and returns the response`() = runTest {
+        val request = slot<GetSecretValueRequest>()
+        val response = GetSecretValueResponse { secretString = "s3cr3t" }
+        coEvery { client.getSecretValue(capture(request)) } returns response
+
+        val result = template.secret("my-secret").get()
+
+        result shouldBe response
+        request.captured.secretId shouldBe "my-secret"
+    }
+
+    @Test
+    fun `create builds CreateSecretRequest with name and secretString`() = runTest {
+        val request = slot<CreateSecretRequest>()
+        val response = CreateSecretResponse { name = "my-secret" }
+        coEvery { client.createSecret(capture(request)) } returns response
+
+        val result = template.secret("my-secret").create("s3cr3t")
+
+        result shouldBe response
+        request.captured.name shouldBe "my-secret"
+        request.captured.secretString shouldBe "s3cr3t"
+    }
+
+    @Test
+    fun `update builds UpdateSecretRequest with secretId and secretString`() = runTest {
+        val request = slot<UpdateSecretRequest>()
+        val response = UpdateSecretResponse { name = "my-secret" }
+        coEvery { client.updateSecret(capture(request)) } returns response
+
+        val result = template.secret("my-secret").update("n3w-s3cr3t")
+
+        result shouldBe response
+        request.captured.secretId shouldBe "my-secret"
+        request.captured.secretString shouldBe "n3w-s3cr3t"
+    }
+
+    @Test
+    fun `delete builds DeleteSecretRequest with secretId and default force flag false`() = runTest {
+        val request = slot<DeleteSecretRequest>()
+        val response = DeleteSecretResponse { name = "my-secret" }
+        coEvery { client.deleteSecret(capture(request)) } returns response
+
+        val result = template.secret("my-secret").delete()
+
+        result shouldBe response
+        request.captured.secretId shouldBe "my-secret"
+        request.captured.forceDeleteWithoutRecovery shouldBe false
+    }
+
+    @Test
+    fun `delete passes forceDeleteWithoutRecovery true when requested`() = runTest {
+        val request = slot<DeleteSecretRequest>()
+        coEvery { client.deleteSecret(capture(request)) } returns DeleteSecretResponse { }
+
+        template.secret("my-secret").delete(forceDeleteWithoutRecovery = true)
+
+        request.captured.secretId shouldBe "my-secret"
+        request.captured.forceDeleteWithoutRecovery shouldBe true
+    }
+
+    @Test
+    fun `get propagates ResourceNotFoundException unchanged`() = runTest {
+        val failure = ResourceNotFoundException { message = "secret not found" }
+        coEvery { client.getSecretValue(any<GetSecretValueRequest>()) } throws failure
+
+        val thrown = shouldThrow<ResourceNotFoundException> { template.secret("missing").get() }
+        thrown shouldBe failure
+    }
+
+    @Test
+    fun `create propagates other SDK exceptions unchanged`() = runTest {
+        val failure = SecretsManagerException("access denied")
+        coEvery { client.createSecret(any<CreateSecretRequest>()) } throws failure
+
+        val thrown = shouldThrow<SecretsManagerException> { template.secret("my-secret").create("v") }
+        thrown shouldBe failure
+    }
+}
diff --git a/settings.gradle.kts b/settings.gradle.kts
index a2d0cf9..3464173 100644
--- a/settings.gradle.kts
+++ b/settings.gradle.kts
@@ -1,20 +1,24 @@
 /*
  * This file was generated by the Gradle 'init' task.
  *
  * The settings file is used to specify which projects to include in your build.
  * For more detailed information on multi-project builds, please refer to https://docs.gradle.org/8.14/userguide/multi_project_builds.html in the Gradle documentation.
  * This project uses @Incubating APIs which are subject to change.
  */
 
-pluginManagement {
-    // Include 'plugins build' to define convention plugins.
-    includeBuild("aws-build-tool")
-}
-
 plugins {
     // Apply the foojay-resolver plugin to allow automatic download of JDKs
     id("org.gradle.toolchains.foojay-resolver-convention") version "0.10.0"
 }
 
 rootProject.name = "aws-sdk-kotlin-spring"
-include( "list", "utilities", "examples")
+include("examples")
+include("core")
+include("s3")
+include("secrets-manager")
+include("sns")
+include("sqs")
+include("starters:s3-starter")
+include("starters:secrets-manager-starter")
+include("starters:sns-starter")
+include("starters:sqs-starter")
diff --git a/sns/build.gradle.kts b/sns/build.gradle.kts
new file mode 100644
index 0000000..98bdf4d
--- /dev/null
+++ b/sns/build.gradle.kts
@@ -0,0 +1,59 @@
+plugins {
+    alias(libs.plugins.kotlinJvm)
+    alias(libs.plugins.dependencyManagement)
+    alias(libs.plugins.kotlinter)
+    `java-library`
+}
+
+group = "io.github.hantsy"
+version = "0.1.0-SNAPSHOT"
+
+base { archivesName.set("awskotlin-sns") }
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
+    api(libs.awsSdkKotlinSns)
+    implementation(libs.kotlinStdlib)
+    implementation(libs.kotlinCoroutinesCore)
+    implementation(libs.kotlinxSerializationJson)
+    testImplementation(libs.awsSqs)
+    testImplementation(libs.testcontainersJunitJupiter)
+    testImplementation(libs.testcontainersLocalstack)
+    testImplementation(libs.bundles.kotest)
+    testImplementation(libs.bundles.mockk)
+    testImplementation(libs.bundles.kotlintest)
+    testImplementation(libs.kotlinCoroutinesTest)
+    testRuntimeOnly(libs.junitPlatformLauncher)
+}
+
+kotlin { jvmToolchain(libs.versions.java.get().toInt()) }
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
diff --git a/sns/src/main/kotlin/io/github/hantsy/awskotlin/sns/SnsNotification.kt b/sns/src/main/kotlin/io/github/hantsy/awskotlin/sns/SnsNotification.kt
new file mode 100644
index 0000000..fb54778
--- /dev/null
+++ b/sns/src/main/kotlin/io/github/hantsy/awskotlin/sns/SnsNotification.kt
@@ -0,0 +1,35 @@
+package io.github.hantsy.awskotlin.sns
+
+/**
+ * An SNS JSON notification envelope as delivered to subscribers (for example an SQS queue
+ * subscribed to a topic without raw message delivery).
+ *
+ * Field names follow the SNS envelope keys: `Type`, `MessageId`, `TopicArn`, `Message`,
+ * `Subject`, `Timestamp`, `SignatureVersion`, `Signature`, `SigningCertURL`,
+ * `UnsubscribeURL` and `Token`. Obtain instances with [SnsNotificationParser.parse].
+ *
+ * @property type the envelope `Type`.
+ * @property messageId unique identifier of the notification.
+ * @property topicArn ARN of the topic the message was published to.
+ * @property message the message body as published (still a String; decode it yourself if it is JSON).
+ * @property subject optional subject supplied at publish time.
+ * @property timestamp ISO-8601 publish time, kept as the raw string.
+ * @property signatureVersion version of the signature algorithm.
+ * @property signature base64-encoded signature of the envelope.
+ * @property signingCertUrl URL of the certificate used to sign the envelope.
+ * @property unsubscribeUrl URL for unsubscribing the endpoint.
+ * @property token confirmation token present on subscription/unsubscribe confirmation messages.
+ */
+data class SnsNotification(
+    val type: SnsNotificationType,
+    val messageId: String,
+    val topicArn: String,
+    val message: String,
+    val subject: String? = null,
+    val timestamp: String? = null,
+    val signatureVersion: String? = null,
+    val signature: String? = null,
+    val signingCertUrl: String? = null,
+    val unsubscribeUrl: String? = null,
+    val token: String? = null
+)
diff --git a/sns/src/main/kotlin/io/github/hantsy/awskotlin/sns/SnsNotificationParseException.kt b/sns/src/main/kotlin/io/github/hantsy/awskotlin/sns/SnsNotificationParseException.kt
new file mode 100644
index 0000000..36da739
--- /dev/null
+++ b/sns/src/main/kotlin/io/github/hantsy/awskotlin/sns/SnsNotificationParseException.kt
@@ -0,0 +1,8 @@
+package io.github.hantsy.awskotlin.sns
+
+/**
+ * Thrown when a message body cannot be parsed as an SNS JSON notification envelope:
+ * invalid JSON, a missing/unknown `Type`, or a missing required `MessageId`, `TopicArn`
+ * or `Message` field. The underlying kotlinx.serialization error, when present, is preserved as the cause.
+ */
+class SnsNotificationParseException(message: String, cause: Throwable? = null) : RuntimeException(message, cause)
diff --git a/sns/src/main/kotlin/io/github/hantsy/awskotlin/sns/SnsNotificationParser.kt b/sns/src/main/kotlin/io/github/hantsy/awskotlin/sns/SnsNotificationParser.kt
new file mode 100644
index 0000000..e561601
--- /dev/null
+++ b/sns/src/main/kotlin/io/github/hantsy/awskotlin/sns/SnsNotificationParser.kt
@@ -0,0 +1,67 @@
+package io.github.hantsy.awskotlin.sns
+
+import kotlinx.serialization.SerializationException
+import kotlinx.serialization.json.Json
+import kotlinx.serialization.json.JsonObject
+import kotlinx.serialization.json.JsonPrimitive
+import kotlinx.serialization.json.contentOrNull
+
+/**
+ * Parses the SNS JSON notification envelope delivered to subscribers.
+ *
+ * Typical use is SNS -> SQS fan-out: subscribe a queue with
+ * [TopicOperations.subscribeSqs] (`rawMessageDelivery = false`, the default), receive the
+ * envelope as a String in an SQS listener, and call [parse] to obtain an [SnsNotification].
+ *
+ * Unknown envelope fields are ignored. A missing or unknown `Type`, or a missing required
+ * `MessageId`, `TopicArn` or `Message` field, raises [SnsNotificationParseException].
+ */
+object SnsNotificationParser {
+    /**
+     * Parses [body] as an SNS JSON notification envelope.
+     *
+     * @throws SnsNotificationParseException if the body is not valid JSON, is not a JSON
+     * object, has a missing/unknown `Type`, or lacks a required string field.
+     */
+    fun parse(body: String): SnsNotification {
+        val root =
+            try {
+                Json.parseToJsonElement(body)
+            } catch (e: SerializationException) {
+                throw SnsNotificationParseException("SNS notification envelope is not valid JSON", e)
+            }
+        if (root !is JsonObject) {
+            throw SnsNotificationParseException("SNS notification envelope must be a JSON object")
+        }
+        val rawType = requiredText(root, "Type")
+        val type =
+            SnsNotificationType.entries.firstOrNull { it.name == rawType }
+                ?: throw SnsNotificationParseException("Unknown SNS notification Type '$rawType'")
+        return SnsNotification(
+            type = type,
+            messageId = requiredText(root, "MessageId"),
+            topicArn = requiredText(root, "TopicArn"),
+            message = requiredText(root, "Message"),
+            subject = optionalText(root, "Subject"),
+            timestamp = optionalText(root, "Timestamp"),
+            signatureVersion = optionalText(root, "SignatureVersion"),
+            signature = optionalText(root, "Signature"),
+            signingCertUrl = optionalText(root, "SigningCertURL"),
+            unsubscribeUrl = optionalText(root, "UnsubscribeURL"),
+            token = optionalText(root, "Token")
+        )
+    }
+
+    private fun requiredText(root: JsonObject, field: String): String {
+        val element = root[field]
+        if (element !is JsonPrimitive || !element.isString) {
+            throw SnsNotificationParseException(
+                "SNS notification envelope is missing required string field '$field'"
+            )
+        }
+        return element.content
+    }
+
+    private fun optionalText(root: JsonObject, field: String): String? =
+        (root[field] as? JsonPrimitive)?.takeIf { it.isString }?.contentOrNull
+}
diff --git a/sns/src/main/kotlin/io/github/hantsy/awskotlin/sns/SnsNotificationType.kt b/sns/src/main/kotlin/io/github/hantsy/awskotlin/sns/SnsNotificationType.kt
new file mode 100644
index 0000000..4609c0d
--- /dev/null
+++ b/sns/src/main/kotlin/io/github/hantsy/awskotlin/sns/SnsNotificationType.kt
@@ -0,0 +1,15 @@
+package io.github.hantsy.awskotlin.sns
+
+/**
+ * The kind of message carried by an SNS JSON envelope, taken from its `Type` field.
+ */
+enum class SnsNotificationType {
+    /** A published message delivered to subscribers. */
+    Notification,
+
+    /** Sent to an endpoint when a subscription is created and must be confirmed. */
+    SubscriptionConfirmation,
+
+    /** Sent to an endpoint when its subscription is removed. */
+    UnsubscribeConfirmation,
+}
diff --git a/sns/src/main/kotlin/io/github/hantsy/awskotlin/sns/SnsTemplate.kt b/sns/src/main/kotlin/io/github/hantsy/awskotlin/sns/SnsTemplate.kt
new file mode 100644
index 0000000..6651be9
--- /dev/null
+++ b/sns/src/main/kotlin/io/github/hantsy/awskotlin/sns/SnsTemplate.kt
@@ -0,0 +1,21 @@
+package io.github.hantsy.awskotlin.sns
+
+import aws.sdk.kotlin.services.sns.SnsClient
+import aws.sdk.kotlin.services.sns.model.UnsubscribeResponse
+import aws.sdk.kotlin.services.sns.unsubscribe
+
+/**
+ * Entry point for fluent SNS operations backed by the AWS SDK for Kotlin [SnsClient].
+ *
+ * Obtain topic-scoped operations with [topic]. The selector accepts a topic ARN only;
+ * no implicit topic creation or name resolution is performed.
+ */
+class SnsTemplate(private val client: SnsClient) {
+    /** Returns operations scoped to the topic identified by [topicArn]. No request is made. */
+    fun topic(topicArn: String): TopicOperations = TopicOperations(client, topicArn)
+
+    /** Cancels the subscription identified by [subscriptionArn]. */
+    suspend fun unsubscribe(subscriptionArn: String): UnsubscribeResponse = client.unsubscribe {
+        this.subscriptionArn = subscriptionArn
+    }
+}
diff --git a/sns/src/main/kotlin/io/github/hantsy/awskotlin/sns/TopicOperations.kt b/sns/src/main/kotlin/io/github/hantsy/awskotlin/sns/TopicOperations.kt
new file mode 100644
index 0000000..9b59fae
--- /dev/null
+++ b/sns/src/main/kotlin/io/github/hantsy/awskotlin/sns/TopicOperations.kt
@@ -0,0 +1,67 @@
+package io.github.hantsy.awskotlin.sns
+
+import aws.sdk.kotlin.services.sns.SnsClient
+import aws.sdk.kotlin.services.sns.deleteTopic
+import aws.sdk.kotlin.services.sns.model.DeleteTopicResponse
+import aws.sdk.kotlin.services.sns.model.PublishResponse
+import aws.sdk.kotlin.services.sns.model.SubscribeResponse
+import aws.sdk.kotlin.services.sns.publish
+import aws.sdk.kotlin.services.sns.setSubscriptionAttributes
+import aws.sdk.kotlin.services.sns.subscribe
+
+/**
+ * Topic-scoped SNS operations. Created via [SnsTemplate.topic].
+ *
+ * Each method maps directly to the matching SDK request and propagates SDK exceptions
+ * unchanged.
+ */
+class TopicOperations internal constructor(private val client: SnsClient, private val topicArn: String,) {
+    /** Publishes [message] to the topic, optionally setting a [subject]. */
+    suspend fun publish(message: String, subject: String? = null): PublishResponse = client.publish {
+        topicArn = this@TopicOperations.topicArn
+        this.message = message
+        this.subject = subject
+    }
+
+    /** Subscribes the given [endpoint] using [protocol] (for example `email`, `sqs`, `https`). */
+    suspend fun subscribe(protocol: String, endpoint: String): SubscribeResponse = client.subscribe {
+        topicArn = this@TopicOperations.topicArn
+        this.protocol = protocol
+        this.endpoint = endpoint
+    }
+
+    /**
+     * Subscribes the SQS queue identified by [queueArn] to this topic for SNS -> SQS fan-out,
+     * then sets the subscription's `RawMessageDelivery` attribute to [rawMessageDelivery].
+     *
+     * With the default `rawMessageDelivery = false`, SQS listeners receive the SNS JSON
+     * envelope as a String body and can call [SnsNotificationParser.parse] to obtain an
+     * [SnsNotification]. With `rawMessageDelivery = true`, the envelope is bypassed and the
+     * queue receives the published message body unchanged.
+     *
+     * The queue must exist and its policy must allow the topic to send messages to it
+     * (`sqs:SendMessage` with an `aws:SourceArn` condition on this topic).
+     *
+     * @throws IllegalStateException if the subscribe response carries no subscription ARN.
+     */
+    suspend fun subscribeSqs(queueArn: String, rawMessageDelivery: Boolean = false): SubscribeResponse {
+        val response = subscribe(protocol = "sqs", endpoint = queueArn)
+        val subArn =
+            response.subscriptionArn
+                ?: throw IllegalStateException(
+                    "SNS subscribe for queue $queueArn returned no subscriptionArn; " +
+                        "cannot set RawMessageDelivery"
+                )
+        client.setSubscriptionAttributes {
+            subscriptionArn = subArn
+            attributeName = "RawMessageDelivery"
+            attributeValue = rawMessageDelivery.toString()
+        }
+        return response
+    }
+
+    /** Deletes the topic. */
+    suspend fun delete(): DeleteTopicResponse = client.deleteTopic {
+        topicArn = this@TopicOperations.topicArn
+    }
+}
diff --git a/sns/src/test/kotlin/io/github/hantsy/awskotlin/sns/SnsNotificationParserTest.kt b/sns/src/test/kotlin/io/github/hantsy/awskotlin/sns/SnsNotificationParserTest.kt
new file mode 100644
index 0000000..e11ae1c
--- /dev/null
+++ b/sns/src/test/kotlin/io/github/hantsy/awskotlin/sns/SnsNotificationParserTest.kt
@@ -0,0 +1,184 @@
+package io.github.hantsy.awskotlin.sns
+
+import io.kotest.assertions.throwables.shouldThrow
+import io.kotest.matchers.nulls.shouldBeNull
+import io.kotest.matchers.nulls.shouldNotBeNull
+import io.kotest.matchers.shouldBe
+import io.kotest.matchers.string.shouldContain
+import io.kotest.matchers.types.shouldBeInstanceOf
+import kotlinx.serialization.SerializationException
+import org.junit.jupiter.api.Test
+
+class SnsNotificationParserTest {
+    private val topicArn = "arn:aws:sns:us-east-1:123456789012:my-topic"
+
+    @Test
+    fun `parse Notification envelope with all fields`() {
+        val body =
+            """
+            {
+              "Type": "Notification",
+              "MessageId": "msg-1",
+              "TopicArn": "$topicArn",
+              "Message": "hello fan-out",
+              "Subject": "greeting",
+              "Timestamp": "2026-01-01T00:00:00.000Z",
+              "SignatureVersion": "1",
+              "Signature": "c2lnbmF0dXJl",
+              "SigningCertURL": "https://sns.us-east-1.amazonaws.com/cert.pem",
+              "UnsubscribeURL": "https://sns.us-east-1.amazonaws.com/?Action=Unsubscribe"
+            }
+            """.trimIndent()
+
+        val notification = SnsNotificationParser.parse(body)
+
+        notification shouldBe
+            SnsNotification(
+                type = SnsNotificationType.Notification,
+                messageId = "msg-1",
+                topicArn = topicArn,
+                message = "hello fan-out",
+                subject = "greeting",
+                timestamp = "2026-01-01T00:00:00.000Z",
+                signatureVersion = "1",
+                signature = "c2lnbmF0dXJl",
+                signingCertUrl = "https://sns.us-east-1.amazonaws.com/cert.pem",
+                unsubscribeUrl = "https://sns.us-east-1.amazonaws.com/?Action=Unsubscribe"
+            )
+        notification.token.shouldBeNull()
+    }
+
+    @Test
+    fun `parse SubscriptionConfirmation envelope with Token`() {
+        val body =
+            """
+            {
+              "Type": "SubscriptionConfirmation",
+              "MessageId": "msg-2",
+              "TopicArn": "$topicArn",
+              "Message": "You have chosen to subscribe to the topic",
+              "Token": "confirm-token",
+              "SubscribeURL": "https://sns.us-east-1.amazonaws.com/?Action=ConfirmSubscription",
+              "Timestamp": "2026-01-01T00:00:00.000Z"
+            }
+            """.trimIndent()
+
+        val notification = SnsNotificationParser.parse(body)
+
+        notification.type shouldBe SnsNotificationType.SubscriptionConfirmation
+        notification.messageId shouldBe "msg-2"
+        notification.topicArn shouldBe topicArn
+        notification.token shouldBe "confirm-token"
+        notification.subject.shouldBeNull()
+    }
+
+    @Test
+    fun `parse UnsubscribeConfirmation envelope`() {
+        val body =
+            """
+            {
+              "Type": "UnsubscribeConfirmation",
+              "MessageId": "msg-3",
+              "TopicArn": "$topicArn",
+              "Message": "You have chosen to deactivate subscription",
+              "Token": "unsubscribe-token"
+            }
+            """.trimIndent()
+
+        val notification = SnsNotificationParser.parse(body)
+
+        notification.type shouldBe SnsNotificationType.UnsubscribeConfirmation
+        notification.token shouldBe "unsubscribe-token"
+    }
+
+    @Test
+    fun `unknown fields are ignored`() {
+        val body =
+            """
+            {
+              "Type": "Notification",
+              "MessageId": "msg-4",
+              "TopicArn": "$topicArn",
+              "Message": "payload",
+              "SomethingNew": {"nested": true},
+              "MessageAttributes": {"key": {"Type": "String", "Value": "v"}}
+            }
+            """.trimIndent()
+
+        val notification = SnsNotificationParser.parse(body)
+
+        notification.message shouldBe "payload"
+        notification.subject.shouldBeNull()
+    }
+
+    @Test
+    fun `minimal Notification leaves optional fields null`() {
+        val body =
+            """
+            {"Type":"Notification","MessageId":"msg-5","TopicArn":"$topicArn","Message":"only-required"}
+            """.trimIndent()
+
+        val notification = SnsNotificationParser.parse(body)
+
+        notification.type shouldBe SnsNotificationType.Notification
+        notification.subject.shouldBeNull()
+        notification.timestamp.shouldBeNull()
+        notification.signatureVersion.shouldBeNull()
+        notification.signature.shouldBeNull()
+        notification.signingCertUrl.shouldBeNull()
+        notification.unsubscribeUrl.shouldBeNull()
+        notification.token.shouldBeNull()
+    }
+
+    @Test
+    fun `missing Type throws typed error`() {
+        val body = """{"MessageId":"m","TopicArn":"$topicArn","Message":"x"}"""
+
+        val thrown = shouldThrow<SnsNotificationParseException> { SnsNotificationParser.parse(body) }
+        thrown.message.shouldNotBeNull() shouldContain "Type"
+    }
+
+    @Test
+    fun `unknown Type throws typed error`() {
+        val body = """{"Type":"Bogus","MessageId":"m","TopicArn":"$topicArn","Message":"x"}"""
+
+        val thrown = shouldThrow<SnsNotificationParseException> { SnsNotificationParser.parse(body) }
+        thrown.message.shouldNotBeNull() shouldContain "Bogus"
+    }
+
+    @Test
+    fun `missing required fields throw typed error`() {
+        val fields =
+            mapOf(
+                "MessageId" to """{"Type":"Notification","TopicArn":"$topicArn","Message":"x"}""",
+                "TopicArn" to """{"Type":"Notification","MessageId":"m","Message":"x"}""",
+                "Message" to """{"Type":"Notification","MessageId":"m","TopicArn":"$topicArn"}"""
+            )
+
+        fields.forEach { (field, body) ->
+            val thrown = shouldThrow<SnsNotificationParseException> { SnsNotificationParser.parse(body) }
+            thrown.message.shouldNotBeNull() shouldContain field
+        }
+    }
+
+    @Test
+    fun `non-string required field throws typed error`() {
+        val body = """{"Type":"Notification","MessageId":42,"TopicArn":"$topicArn","Message":"x"}"""
+
+        val thrown = shouldThrow<SnsNotificationParseException> { SnsNotificationParser.parse(body) }
+        thrown.message.shouldNotBeNull() shouldContain "MessageId"
+    }
+
+    @Test
+    fun `invalid JSON throws typed error with serialization cause`() {
+        val thrown = shouldThrow<SnsNotificationParseException> { SnsNotificationParser.parse("{not json") }
+        thrown.cause.shouldNotBeNull().shouldBeInstanceOf<SerializationException>()
+    }
+
+    @Test
+    fun `non-object JSON throws typed error`() {
+        shouldThrow<SnsNotificationParseException> { SnsNotificationParser.parse("[1,2,3]") }
+        shouldThrow<SnsNotificationParseException> { SnsNotificationParser.parse("\"just a string\"") }
+        shouldThrow<SnsNotificationParseException> { SnsNotificationParser.parse("null") }
+    }
+}
diff --git a/sns/src/test/kotlin/io/github/hantsy/awskotlin/sns/SnsSqsFanOutLocalStackTest.kt b/sns/src/test/kotlin/io/github/hantsy/awskotlin/sns/SnsSqsFanOutLocalStackTest.kt
new file mode 100644
index 0000000..a520c5e
--- /dev/null
+++ b/sns/src/test/kotlin/io/github/hantsy/awskotlin/sns/SnsSqsFanOutLocalStackTest.kt
@@ -0,0 +1,182 @@
+package io.github.hantsy.awskotlin.sns
+
+import aws.sdk.kotlin.runtime.auth.credentials.StaticCredentialsProvider
+import aws.sdk.kotlin.services.sns.SnsClient
+import aws.sdk.kotlin.services.sns.createTopic
+import aws.sdk.kotlin.services.sns.deleteTopic
+import aws.sdk.kotlin.services.sqs.SqsClient
+import aws.sdk.kotlin.services.sqs.createQueue
+import aws.sdk.kotlin.services.sqs.deleteQueue
+import aws.sdk.kotlin.services.sqs.getQueueAttributes
+import aws.sdk.kotlin.services.sqs.model.QueueAttributeName
+import aws.sdk.kotlin.services.sqs.receiveMessage
+import aws.sdk.kotlin.services.sqs.setQueueAttributes
+import aws.smithy.kotlin.runtime.net.url.Url
+import io.kotest.matchers.nulls.shouldNotBeNull
+import io.kotest.matchers.shouldBe
+import kotlinx.coroutines.runBlocking
+import org.junit.jupiter.api.AfterAll
+import org.junit.jupiter.api.Assumptions.assumeTrue
+import org.junit.jupiter.api.BeforeAll
+import org.junit.jupiter.api.Test
+import org.junit.jupiter.api.TestInstance
+import org.testcontainers.DockerClientFactory
+import org.testcontainers.localstack.LocalStackContainer
+import org.testcontainers.utility.DockerImageName
+import java.util.UUID
+
+/**
+ * End-to-end SNS -> SQS fan-out against LocalStack: [TopicOperations.subscribeSqs],
+ * publish, receive from SQS and parse the envelope with [SnsNotificationParser].
+ *
+ * Skipped unless Docker is available and LOCALSTACK_AUTH_TOKEN is set
+ * (LocalStack 4 requires a license token).
+ */
+@TestInstance(TestInstance.Lifecycle.PER_CLASS)
+class SnsSqsFanOutLocalStackTest {
+    private lateinit var container: LocalStackContainer
+    private lateinit var snsClient: SnsClient
+    private lateinit var sqsClient: SqsClient
+    private lateinit var template: SnsTemplate
+
+    @BeforeAll
+    fun startLocalStack() {
+        val authToken = System.getenv("LOCALSTACK_AUTH_TOKEN")?.takeIf { it.isNotBlank() }
+        assumeTrue(authToken != null, "LOCALSTACK_AUTH_TOKEN is not set; skipping LocalStack tests")
+        assumeTrue(
+            DockerClientFactory.instance().isDockerAvailable,
+            "Docker is not available; skipping LocalStack tests"
+        )
+
+        container =
+            LocalStackContainer(DockerImageName.parse("localstack/localstack:4"))
+                .withServices("sns", "sqs")
+                .withEnv("LOCALSTACK_AUTH_TOKEN", authToken)
+        container.start()
+
+        val endpoint = Url.parse("http://${container.host}:${container.firstMappedPort}")
+        snsClient =
+            SnsClient {
+                region = "us-east-1"
+                credentialsProvider =
+                    StaticCredentialsProvider {
+                        accessKeyId = "test"
+                        secretAccessKey = "test"
+                    }
+                endpointUrl = endpoint
+            }
+        sqsClient =
+            SqsClient {
+                region = "us-east-1"
+                credentialsProvider =
+                    StaticCredentialsProvider {
+                        accessKeyId = "test"
+                        secretAccessKey = "test"
+                    }
+                endpointUrl = endpoint
+            }
+        template = SnsTemplate(snsClient)
+    }
+
+    @AfterAll
+    fun stopLocalStack() {
+        if (::snsClient.isInitialized) {
+            runCatching { snsClient.close() }
+        }
+        if (::sqsClient.isInitialized) {
+            runCatching { sqsClient.close() }
+        }
+        if (::container.isInitialized) {
+            runCatching { container.stop() }
+        }
+    }
+
+    @Test
+    fun `subscribeSqs fan-out delivers the SNS envelope to SQS`() = runBlocking<Unit> {
+        val fanOut = newFanOut()
+        try {
+            template.topic(fanOut.topicArn).subscribeSqs(fanOut.queueArn, rawMessageDelivery = false)
+            template.topic(fanOut.topicArn).publish("hello fan-out", subject = "greeting")
+
+            val body = receiveBody(fanOut.queueUrl)
+            val notification = SnsNotificationParser.parse(body)
+
+            notification.type shouldBe SnsNotificationType.Notification
+            notification.message shouldBe "hello fan-out"
+            notification.topicArn shouldBe fanOut.topicArn
+            notification.subject shouldBe "greeting"
+        } finally {
+            fanOut.cleanup()
+        }
+    }
+
+    @Test
+    fun `subscribeSqs with rawMessageDelivery delivers the original body without envelope`() = runBlocking<Unit> {
+        val fanOut = newFanOut()
+        try {
+            template.topic(fanOut.topicArn).subscribeSqs(fanOut.queueArn, rawMessageDelivery = true)
+            template.topic(fanOut.topicArn).publish("raw-body")
+
+            receiveBody(fanOut.queueUrl) shouldBe "raw-body"
+        } finally {
+            fanOut.cleanup()
+        }
+    }
+
+    private suspend fun newFanOut(): FanOut {
+        val suffix = UUID.randomUUID().toString().substring(0, 8)
+        val topicArn = snsClient.createTopic { name = "awskotlin-fanout-$suffix" }.topicArn.shouldNotBeNull()
+        val queueUrl =
+            sqsClient.createQueue { queueName = "awskotlin-fanout-$suffix" }.queueUrl.shouldNotBeNull()
+        val queueArn =
+            sqsClient.getQueueAttributes {
+                this.queueUrl = queueUrl
+                attributeNames = listOf(QueueAttributeName.QueueArn)
+            }.attributes.shouldNotBeNull()[QueueAttributeName.QueueArn].shouldNotBeNull()
+
+        // Real AWS (and policy-enforcing emulators) require the queue to allow the topic to send to it.
+        sqsClient.setQueueAttributes {
+            this.queueUrl = queueUrl
+            attributes = mapOf(QueueAttributeName.Policy to sendPolicy(topicArn, queueArn))
+        }
+        return FanOut(topicArn, queueUrl, queueArn)
+    }
+
+    private fun sendPolicy(topicArn: String, queueArn: String): String =
+        """
+        {
+          "Version": "2012-10-17",
+          "Statement": [{
+            "Effect": "Allow",
+            "Principal": {"Service": "sns.amazonaws.com"},
+            "Action": "sqs:SendMessage",
+            "Resource": "$queueArn",
+            "Condition": {"ArnEquals": {"aws:SourceArn": "$topicArn"}}
+          }]
+        }
+        """.trimIndent()
+
+    /** Long-polls the queue for up to ~60 seconds; fan-out delivery is asynchronous. */
+    private suspend fun receiveBody(queueUrl: String): String {
+        repeat(12) {
+            val message =
+                sqsClient.receiveMessage {
+                    this.queueUrl = queueUrl
+                    waitTimeSeconds = 5
+                    maxNumberOfMessages = 1
+                }.messages?.firstOrNull()
+            if (message != null) {
+                return message.body.shouldNotBeNull()
+            }
+        }
+        error("No SQS message received from $queueUrl within 60 seconds of SNS publish")
+    }
+
+    private inner class FanOut(val topicArn: String, val queueUrl: String, val queueArn: String) {
+        /** Best-effort cleanup so a failed assertion does not leak resources into other tests. */
+        suspend fun cleanup() {
+            runCatching { snsClient.deleteTopic { this.topicArn = this@FanOut.topicArn } }
+            runCatching { sqsClient.deleteQueue { this.queueUrl = this@FanOut.queueUrl } }
+        }
+    }
+}
diff --git a/sns/src/test/kotlin/io/github/hantsy/awskotlin/sns/SnsTemplateTest.kt b/sns/src/test/kotlin/io/github/hantsy/awskotlin/sns/SnsTemplateTest.kt
new file mode 100644
index 0000000..23de4f0
--- /dev/null
+++ b/sns/src/test/kotlin/io/github/hantsy/awskotlin/sns/SnsTemplateTest.kt
@@ -0,0 +1,110 @@
+package io.github.hantsy.awskotlin.sns
+
+import aws.sdk.kotlin.services.sns.SnsClient
+import aws.sdk.kotlin.services.sns.model.DeleteTopicRequest
+import aws.sdk.kotlin.services.sns.model.DeleteTopicResponse
+import aws.sdk.kotlin.services.sns.model.NotFoundException
+import aws.sdk.kotlin.services.sns.model.PublishRequest
+import aws.sdk.kotlin.services.sns.model.PublishResponse
+import aws.sdk.kotlin.services.sns.model.SnsException
+import aws.sdk.kotlin.services.sns.model.SubscribeRequest
+import aws.sdk.kotlin.services.sns.model.SubscribeResponse
+import aws.sdk.kotlin.services.sns.model.UnsubscribeRequest
+import aws.sdk.kotlin.services.sns.model.UnsubscribeResponse
+import io.kotest.assertions.throwables.shouldThrow
+import io.kotest.matchers.nulls.shouldBeNull
+import io.kotest.matchers.shouldBe
+import io.mockk.coEvery
+import io.mockk.mockk
+import io.mockk.slot
+import kotlinx.coroutines.test.runTest
+import org.junit.jupiter.api.Test
+
+class SnsTemplateTest {
+    private val client = mockk<SnsClient>()
+    private val template = SnsTemplate(client)
+    private val topicArn = "arn:aws:sns:us-east-1:123456789012:my-topic"
+
+    @Test
+    fun `publish builds PublishRequest with topicArn message and subject`() = runTest {
+        val request = slot<PublishRequest>()
+        val response = PublishResponse { messageId = "msg-1" }
+        coEvery { client.publish(capture(request)) } returns response
+
+        val result = template.topic(topicArn).publish("hello", subject = "greeting")
+
+        result shouldBe response
+        request.captured.topicArn shouldBe topicArn
+        request.captured.message shouldBe "hello"
+        request.captured.subject shouldBe "greeting"
+    }
+
+    @Test
+    fun `publish without subject leaves it unset`() = runTest {
+        val request = slot<PublishRequest>()
+        coEvery { client.publish(capture(request)) } returns PublishResponse { }
+
+        template.topic(topicArn).publish("hello")
+
+        request.captured.topicArn shouldBe topicArn
+        request.captured.message shouldBe "hello"
+        request.captured.subject.shouldBeNull()
+    }
+
+    @Test
+    fun `subscribe builds SubscribeRequest with topicArn protocol and endpoint`() = runTest {
+        val request = slot<SubscribeRequest>()
+        val response = SubscribeResponse { subscriptionArn = "arn:aws:sns:us-east-1:123456789012:my-topic:sub-1" }
+        coEvery { client.subscribe(capture(request)) } returns response
+
+        val result = template.topic(topicArn).subscribe("email", "user@example.com")
+
+        result shouldBe response
+        request.captured.topicArn shouldBe topicArn
+        request.captured.protocol shouldBe "email"
+        request.captured.endpoint shouldBe "user@example.com"
+    }
+
+    @Test
+    fun `delete builds DeleteTopicRequest with topicArn`() = runTest {
+        val request = slot<DeleteTopicRequest>()
+        val response = DeleteTopicResponse { }
+        coEvery { client.deleteTopic(capture(request)) } returns response
+
+        val result = template.topic(topicArn).delete()
+
+        result shouldBe response
+        request.captured.topicArn shouldBe topicArn
+    }
+
+    @Test
+    fun `unsubscribe builds UnsubscribeRequest with subscriptionArn`() = runTest {
+        val request = slot<UnsubscribeRequest>()
+        val response = UnsubscribeResponse { }
+        coEvery { client.unsubscribe(capture(request)) } returns response
+        val subscriptionArn = "arn:aws:sns:us-east-1:123456789012:my-topic:sub-1"
+
+        val result = template.unsubscribe(subscriptionArn)
+
+        result shouldBe response
+        request.captured.subscriptionArn shouldBe subscriptionArn
+    }
+
+    @Test
+    fun `publish propagates NotFoundException unchanged`() = runTest {
+        val failure = NotFoundException { message = "topic not found" }
+        coEvery { client.publish(any<PublishRequest>()) } throws failure
+
+        val thrown = shouldThrow<NotFoundException> { template.topic(topicArn).publish("hello") }
+        thrown shouldBe failure
+    }
+
+    @Test
+    fun `subscribe propagates other SDK exceptions unchanged`() = runTest {
+        val failure = SnsException("access denied")
+        coEvery { client.subscribe(any<SubscribeRequest>()) } throws failure
+
+        val thrown = shouldThrow<SnsException> { template.topic(topicArn).subscribe("email", "user@example.com") }
+        thrown shouldBe failure
+    }
+}
diff --git a/sns/src/test/kotlin/io/github/hantsy/awskotlin/sns/TopicOperationsSubscribeSqsTest.kt b/sns/src/test/kotlin/io/github/hantsy/awskotlin/sns/TopicOperationsSubscribeSqsTest.kt
new file mode 100644
index 0000000..43f19c6
--- /dev/null
+++ b/sns/src/test/kotlin/io/github/hantsy/awskotlin/sns/TopicOperationsSubscribeSqsTest.kt
@@ -0,0 +1,68 @@
+package io.github.hantsy.awskotlin.sns
+
+import aws.sdk.kotlin.services.sns.SnsClient
+import aws.sdk.kotlin.services.sns.model.SetSubscriptionAttributesRequest
+import aws.sdk.kotlin.services.sns.model.SetSubscriptionAttributesResponse
+import aws.sdk.kotlin.services.sns.model.SubscribeRequest
+import aws.sdk.kotlin.services.sns.model.SubscribeResponse
+import io.kotest.assertions.throwables.shouldThrow
+import io.kotest.matchers.shouldBe
+import io.kotest.matchers.string.shouldContain
+import io.mockk.coEvery
+import io.mockk.coVerify
+import io.mockk.mockk
+import io.mockk.slot
+import kotlinx.coroutines.test.runTest
+import org.junit.jupiter.api.Test
+
+class TopicOperationsSubscribeSqsTest {
+    private val client = mockk<SnsClient>()
+    private val template = SnsTemplate(client)
+    private val topicArn = "arn:aws:sns:us-east-1:123456789012:my-topic"
+    private val queueArn = "arn:aws:sqs:us-east-1:123456789012:my-queue"
+    private val subArn = "arn:aws:sns:us-east-1:123456789012:my-topic:sub-1"
+
+    @Test
+    fun `subscribeSqs subscribes with sqs protocol and defaults RawMessageDelivery to false`() = runTest {
+        val subscribeRequest = slot<SubscribeRequest>()
+        val attributesRequest = slot<SetSubscriptionAttributesRequest>()
+        val response = SubscribeResponse { subscriptionArn = subArn }
+        coEvery { client.subscribe(capture(subscribeRequest)) } returns response
+        coEvery { client.setSubscriptionAttributes(capture(attributesRequest)) } returns
+            SetSubscriptionAttributesResponse { }
+
+        val result = template.topic(topicArn).subscribeSqs(queueArn)
+
+        result shouldBe response
+        subscribeRequest.captured.topicArn shouldBe topicArn
+        subscribeRequest.captured.protocol shouldBe "sqs"
+        subscribeRequest.captured.endpoint shouldBe queueArn
+        attributesRequest.captured.subscriptionArn shouldBe subArn
+        attributesRequest.captured.attributeName shouldBe "RawMessageDelivery"
+        attributesRequest.captured.attributeValue shouldBe "false"
+    }
+
+    @Test
+    fun `subscribeSqs with rawMessageDelivery true sets the attribute to true`() = runTest {
+        val attributesRequest = slot<SetSubscriptionAttributesRequest>()
+        coEvery { client.subscribe(any<SubscribeRequest>()) } returns SubscribeResponse { subscriptionArn = subArn }
+        coEvery { client.setSubscriptionAttributes(capture(attributesRequest)) } returns
+            SetSubscriptionAttributesResponse { }
+
+        template.topic(topicArn).subscribeSqs(queueArn, rawMessageDelivery = true)
+
+        attributesRequest.captured.subscriptionArn shouldBe subArn
+        attributesRequest.captured.attributeName shouldBe "RawMessageDelivery"
+        attributesRequest.captured.attributeValue shouldBe "true"
+    }
+
+    @Test
+    fun `subscribeSqs fails when the subscribe response has no subscriptionArn`() = runTest {
+        coEvery { client.subscribe(any<SubscribeRequest>()) } returns SubscribeResponse { }
+
+        val thrown = shouldThrow<IllegalStateException> { template.topic(topicArn).subscribeSqs(queueArn) }
+
+        thrown.message.shouldContain(queueArn)
+        coVerify(exactly = 0) { client.setSubscriptionAttributes(any<SetSubscriptionAttributesRequest>()) }
+    }
+}
diff --git a/sqs/build.gradle.kts b/sqs/build.gradle.kts
new file mode 100644
index 0000000..0e60822
--- /dev/null
+++ b/sqs/build.gradle.kts
@@ -0,0 +1,61 @@
+plugins {
+    alias(libs.plugins.kotlinJvm)
+    alias(libs.plugins.dependencyManagement)
+    alias(libs.plugins.kotlinter)
+    alias(libs.plugins.kotlinPluginSerialization)
+    `java-library`
+}
+
+group = "io.github.hantsy"
+version = "0.1.0-SNAPSHOT"
+
+base { archivesName.set("awskotlin-sqs") }
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
+    api(libs.awsSqs)
+    implementation(libs.kotlinStdlib)
+    implementation(libs.kotlinCoroutinesCore)
+    implementation(libs.springContext)
+    implementation(libs.springAop)
+    implementation(libs.kotlinReflect)
+    implementation(libs.kotlinxSerializationJson)
+    testImplementation(libs.springBootStarterTest)
+    testImplementation(libs.bundles.kotest)
+    testImplementation(libs.bundles.mockk)
+    testImplementation(libs.bundles.kotlintest)
+    testImplementation(libs.kotlinCoroutinesTest)
+    testRuntimeOnly(libs.junitPlatformLauncher)
+}
+
+kotlin { jvmToolchain(libs.versions.java.get().toInt()) }
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
diff --git a/sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/DefaultSqsDestinationOperations.kt b/sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/DefaultSqsDestinationOperations.kt
new file mode 100644
index 0000000..f72bd30
--- /dev/null
+++ b/sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/DefaultSqsDestinationOperations.kt
@@ -0,0 +1,125 @@
+package io.github.hantsy.awskotlin.sqs
+
+import aws.sdk.kotlin.services.sqs.SqsClient
+import aws.sdk.kotlin.services.sqs.changeMessageVisibility
+import aws.sdk.kotlin.services.sqs.deleteMessage
+import aws.sdk.kotlin.services.sqs.model.ChangeMessageVisibilityResponse
+import aws.sdk.kotlin.services.sqs.model.DeleteMessageResponse
+import aws.sdk.kotlin.services.sqs.model.MessageNotInflight
+import aws.sdk.kotlin.services.sqs.model.MessageSystemAttributeName
+import aws.sdk.kotlin.services.sqs.model.QueueDoesNotExist
+import aws.sdk.kotlin.services.sqs.model.ReceiptHandleIsInvalid
+import aws.sdk.kotlin.services.sqs.model.SendMessageResponse
+import aws.sdk.kotlin.services.sqs.model.SqsException
+import aws.sdk.kotlin.services.sqs.receiveMessage
+import aws.sdk.kotlin.services.sqs.sendMessage
+import kotlin.time.Duration
+
+/**
+ * Default [SqsDestinationOperations] implementation.
+ *
+ * Modeled SDK errors are mapped to typed template errors with the original cause
+ * preserved: `QueueDoesNotExist` becomes [SqsQueueNotFoundException];
+ * `ReceiptHandleIsInvalid`, `MessageNotInflight` and other [SqsException]s become
+ * [SqsOperationException].
+ */
+internal class DefaultSqsDestinationOperations(
+    private val client: SqsClient,
+    private val converter: SqsMessageConverter,
+    private val queueResolver: SqsQueueResolver,
+    private val queueNameOrUrl: String,
+) : SqsDestinationOperations {
+    override suspend fun send(payload: Any, delay: Duration?): SendMessageResponse {
+        val delaySeconds = delay?.inWholeSeconds
+        if (delaySeconds != null && delaySeconds !in 0..MAX_DELAY_SECONDS) {
+            throw SqsOperationException("delay must be between 0 and $MAX_DELAY_SECONDS seconds, got $delay")
+        }
+        val queueUrl = queueResolver.resolve(queueNameOrUrl)
+        val body = converter.toBody(payload)
+        return mapSdkErrors("send message") {
+            client.sendMessage {
+                this.queueUrl = queueUrl
+                messageBody = body
+                this.delaySeconds = delaySeconds?.toInt()
+            }
+        }
+    }
+
+    override suspend fun <T : Any> receive(
+        targetType: Class<T>,
+        timeout: Duration,
+        acknowledgement: SqsAcknowledgementMode,
+    ): SqsMessage<T>? {
+        if (timeout.isNegative()) {
+            throw SqsOperationException("timeout must not be negative, got $timeout")
+        }
+        val waitTimeSeconds = timeout.inWholeSeconds.coerceIn(0, MAX_WAIT_TIME_SECONDS).toInt()
+        val queueUrl = queueResolver.resolve(queueNameOrUrl)
+        val response = mapSdkErrors("receive message") {
+            client.receiveMessage {
+                this.queueUrl = queueUrl
+                maxNumberOfMessages = 1
+                this.waitTimeSeconds = waitTimeSeconds
+                messageAttributeNames = listOf("All")
+                messageSystemAttributeNames = listOf(MessageSystemAttributeName.All)
+            }
+        }
+        val message = response.messages?.firstOrNull() ?: return null
+        val body = message.body ?: ""
+        // Conversion failures propagate before any delete: the message stays in the queue.
+        val payload = converter.fromBody(body, targetType)
+        val result = SqsMessage(
+            payload = payload,
+            messageId = message.messageId ?: "",
+            receiptHandle = message.receiptHandle ?: "",
+            body = body,
+            attributes = message.attributes?.mapKeys { it.key.value } ?: emptyMap(),
+            messageAttributes = message.messageAttributes ?: emptyMap(),
+        )
+        if (acknowledgement == SqsAcknowledgementMode.AUTO) {
+            delete(queueUrl, result.receiptHandle)
+        }
+        return result
+    }
+
+    override suspend fun acknowledge(message: SqsMessage<*>): DeleteMessageResponse {
+        val queueUrl = queueResolver.resolve(queueNameOrUrl)
+        return delete(queueUrl, message.receiptHandle)
+    }
+
+    override suspend fun reject(message: SqsMessage<*>): ChangeMessageVisibilityResponse {
+        val queueUrl = queueResolver.resolve(queueNameOrUrl)
+        return mapSdkErrors("reject message") {
+            client.changeMessageVisibility {
+                this.queueUrl = queueUrl
+                receiptHandle = message.receiptHandle
+                visibilityTimeout = 0
+            }
+        }
+    }
+
+    private suspend fun delete(queueUrl: String, receiptHandle: String): DeleteMessageResponse =
+        mapSdkErrors("delete message") {
+            client.deleteMessage {
+                this.queueUrl = queueUrl
+                this.receiptHandle = receiptHandle
+            }
+        }
+
+    private inline fun <R> mapSdkErrors(operation: String, block: () -> R): R = try {
+        block()
+    } catch (e: QueueDoesNotExist) {
+        throw SqsQueueNotFoundException(queueNameOrUrl, e)
+    } catch (e: ReceiptHandleIsInvalid) {
+        throw SqsOperationException("Cannot $operation: receipt handle is invalid or expired", e)
+    } catch (e: MessageNotInflight) {
+        throw SqsOperationException("Cannot $operation: message is not in flight", e)
+    } catch (e: SqsException) {
+        throw SqsOperationException("Failed to $operation on $queueNameOrUrl", e)
+    }
+
+    private companion object {
+        const val MAX_DELAY_SECONDS = 900L
+        const val MAX_WAIT_TIME_SECONDS = 20L
+    }
+}
diff --git a/sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/KotlinxSqsMessageConverter.kt b/sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/KotlinxSqsMessageConverter.kt
new file mode 100644
index 0000000..dffede2
--- /dev/null
+++ b/sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/KotlinxSqsMessageConverter.kt
@@ -0,0 +1,47 @@
+package io.github.hantsy.awskotlin.sqs
+
+import kotlinx.serialization.json.Json
+import kotlinx.serialization.serializer
+import kotlin.reflect.full.starProjectedType
+
+/**
+ * kotlinx.serialization based converter.
+ *
+ * String payloads pass through unchanged in both directions; all other payloads are
+ * serialized/deserialized as JSON with runtime serializers resolved from the payload or
+ * target class, so non-String payload types must be `@Serializable`. Unknown JSON fields
+ * are ignored when reading. Conversion failures are wrapped in
+ * [SqsMessageConversionException] with the serialization error preserved as the cause.
+ */
+class KotlinxSqsMessageConverter(private val json: Json = Json { ignoreUnknownKeys = true },) : SqsMessageConverter {
+    override fun toBody(payload: Any): String {
+        if (payload is String) return payload
+        return try {
+            json.encodeToString(serializer(payload::class.starProjectedType), payload)
+        } catch (e: IllegalArgumentException) {
+            // SerializationException (serializer lookup and encoding errors) extends
+            // IllegalArgumentException; star-projected generic payloads fail lookup here too.
+            throw SqsMessageConversionException(
+                "Failed to serialize payload of type ${payload.javaClass.name} to a message body",
+                e,
+            )
+        }
+    }
+
+    override fun <T : Any> fromBody(body: String, targetType: Class<T>): T {
+        if (targetType == String::class.java) {
+            @Suppress("UNCHECKED_CAST")
+            return body as T
+        }
+        return try {
+            val value =
+                json.decodeFromString(serializer(targetType.kotlin.starProjectedType), body)
+                    ?: throw SqsMessageConversionException("Message body converted to null for type ${targetType.name}")
+
+            @Suppress("UNCHECKED_CAST")
+            value as T
+        } catch (e: IllegalArgumentException) {
+            throw SqsMessageConversionException("Failed to deserialize message body to ${targetType.name}", e)
+        }
+    }
+}
diff --git a/sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/SqsAcknowledgementMode.kt b/sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/SqsAcknowledgementMode.kt
new file mode 100644
index 0000000..1833c55
--- /dev/null
+++ b/sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/SqsAcknowledgementMode.kt
@@ -0,0 +1,10 @@
+package io.github.hantsy.awskotlin.sqs
+
+/** Controls whether received messages are deleted automatically. */
+enum class SqsAcknowledgementMode {
+    /** Delete the message after successful payload conversion (default). */
+    AUTO,
+
+    /** Leave the message in the queue; the caller must acknowledge or reject it. */
+    MANUAL,
+}
diff --git a/sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/SqsDestinationOperations.kt b/sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/SqsDestinationOperations.kt
new file mode 100644
index 0000000..aa664f4
--- /dev/null
+++ b/sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/SqsDestinationOperations.kt
@@ -0,0 +1,47 @@
+package io.github.hantsy.awskotlin.sqs
+
+import aws.sdk.kotlin.services.sqs.model.ChangeMessageVisibilityResponse
+import aws.sdk.kotlin.services.sqs.model.DeleteMessageResponse
+import aws.sdk.kotlin.services.sqs.model.SendMessageResponse
+import kotlin.time.Duration
+import kotlin.time.Duration.Companion.seconds
+
+/**
+ * Queue-scoped SQS operations. Created via [SqsTemplate.destination].
+ *
+ * Standard queues only; no implicit queue creation, no batch receive.
+ */
+interface SqsDestinationOperations {
+    /**
+     * Sends [payload] to the queue, optionally delaying delivery by [delay]
+     * (0..900 seconds; invalid values throw [SqsOperationException]).
+     */
+    suspend fun send(payload: Any, delay: Duration? = null): SendMessageResponse
+
+    /**
+     * Receives a single message and converts its body to [targetType], or returns
+     * `null` when the queue is empty. [timeout] is clamped to 0..20 seconds for
+     * long polling; negative values throw [SqsOperationException].
+     *
+     * [SqsAcknowledgementMode.AUTO] deletes the message after successful conversion;
+     * [SqsAcknowledgementMode.MANUAL] leaves it in flight. Conversion failures never
+     * delete the message.
+     */
+    suspend fun <T : Any> receive(
+        targetType: Class<T>,
+        timeout: Duration = 10.seconds,
+        acknowledgement: SqsAcknowledgementMode = SqsAcknowledgementMode.AUTO,
+    ): SqsMessage<T>?
+
+    /** Deletes the message from the queue using its receipt handle. */
+    suspend fun acknowledge(message: SqsMessage<*>): DeleteMessageResponse
+
+    /** Makes the message immediately visible again by setting its visibility timeout to 0. */
+    suspend fun reject(message: SqsMessage<*>): ChangeMessageVisibilityResponse
+}
+
+/** Reified convenience overload of [SqsDestinationOperations.receive]. */
+suspend inline fun <reified T : Any> SqsDestinationOperations.receive(
+    timeout: Duration = 10.seconds,
+    acknowledgement: SqsAcknowledgementMode = SqsAcknowledgementMode.AUTO,
+): SqsMessage<T>? = receive(T::class.java, timeout, acknowledgement)
diff --git a/sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/SqsMessage.kt b/sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/SqsMessage.kt
new file mode 100644
index 0000000..2222b14
--- /dev/null
+++ b/sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/SqsMessage.kt
@@ -0,0 +1,18 @@
+package io.github.hantsy.awskotlin.sqs
+
+import aws.sdk.kotlin.services.sqs.model.MessageAttributeValue
+
+/**
+ * A received SQS message with its converted [payload] and the raw SDK metadata.
+ *
+ * [receiptHandle] is required to [SqsDestinationOperations.acknowledge] or
+ * [SqsDestinationOperations.reject] the message in manual acknowledgement mode.
+ */
+data class SqsMessage<T>(
+    val payload: T,
+    val messageId: String,
+    val receiptHandle: String,
+    val body: String,
+    val attributes: Map<String, String>,
+    val messageAttributes: Map<String, MessageAttributeValue>,
+)
diff --git a/sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/SqsMessageConverter.kt b/sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/SqsMessageConverter.kt
new file mode 100644
index 0000000..ddea8de
--- /dev/null
+++ b/sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/SqsMessageConverter.kt
@@ -0,0 +1,10 @@
+package io.github.hantsy.awskotlin.sqs
+
+/** Converts message payloads to and from SQS message bodies. */
+interface SqsMessageConverter {
+    /** Serializes [payload] into a message body. */
+    fun toBody(payload: Any): String
+
+    /** Deserializes [body] into an instance of [targetType]. */
+    fun <T : Any> fromBody(body: String, targetType: Class<T>): T
+}
diff --git a/sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/SqsQueueResolver.kt b/sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/SqsQueueResolver.kt
new file mode 100644
index 0000000..aba7ac0
--- /dev/null
+++ b/sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/SqsQueueResolver.kt
@@ -0,0 +1,40 @@
+package io.github.hantsy.awskotlin.sqs
+
+import aws.sdk.kotlin.services.sqs.SqsClient
+import aws.sdk.kotlin.services.sqs.getQueueUrl
+import aws.sdk.kotlin.services.sqs.model.QueueDoesNotExist
+import kotlinx.coroutines.sync.Mutex
+import kotlinx.coroutines.sync.withLock
+
+/**
+ * Resolves a queue name or URL to a queue URL, caching successful name lookups.
+ *
+ * URLs (`https://` or `http://`) pass through unchanged. Names are resolved with
+ * `getQueueUrl`; the cache is guarded by a [Mutex] so concurrent coroutines never
+ * trigger duplicate lookups for the same name. FIFO queues (`.fifo` suffix) are
+ * rejected with [SqsOperationException] before any SDK call.
+ */
+class SqsQueueResolver(private val client: SqsClient) {
+    private val mutex = Mutex()
+    private val cache = mutableMapOf<String, String>()
+
+    /** Returns the queue URL for [queueNameOrUrl]. */
+    suspend fun resolve(queueNameOrUrl: String): String {
+        if (queueNameOrUrl.endsWith(".fifo")) {
+            throw SqsOperationException("FIFO queues are not supported: $queueNameOrUrl")
+        }
+        if (queueNameOrUrl.startsWith("https://") || queueNameOrUrl.startsWith("http://")) {
+            return queueNameOrUrl
+        }
+        return mutex.withLock {
+            cache.getOrPut(queueNameOrUrl) {
+                val response = try {
+                    client.getQueueUrl { queueName = queueNameOrUrl }
+                } catch (e: QueueDoesNotExist) {
+                    throw SqsQueueNotFoundException(queueNameOrUrl, e)
+                }
+                response.queueUrl ?: throw SqsQueueNotFoundException(queueNameOrUrl)
+            }
+        }
+    }
+}
diff --git a/sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/SqsTemplate.kt b/sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/SqsTemplate.kt
new file mode 100644
index 0000000..705eeb2
--- /dev/null
+++ b/sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/SqsTemplate.kt
@@ -0,0 +1,19 @@
+package io.github.hantsy.awskotlin.sqs
+
+import aws.sdk.kotlin.services.sqs.SqsClient
+
+/**
+ * Entry point for fluent SQS operations backed by the AWS SDK for Kotlin [SqsClient].
+ *
+ * Obtain queue-scoped operations with [destination]; the queue name or URL is
+ * resolved lazily on the first operation.
+ */
+class SqsTemplate(
+    private val client: SqsClient,
+    private val converter: SqsMessageConverter = KotlinxSqsMessageConverter(),
+    private val queueResolver: SqsQueueResolver = SqsQueueResolver(client),
+) {
+    /** Returns operations scoped to [queueNameOrUrl]. No request is made. */
+    fun destination(queueNameOrUrl: String): SqsDestinationOperations =
+        DefaultSqsDestinationOperations(client, converter, queueResolver, queueNameOrUrl)
+}
diff --git a/sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/SqsTemplateException.kt b/sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/SqsTemplateException.kt
new file mode 100644
index 0000000..5e120fd
--- /dev/null
+++ b/sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/SqsTemplateException.kt
@@ -0,0 +1,19 @@
+package io.github.hantsy.awskotlin.sqs
+
+/**
+ * Base error for SQS template operations.
+ *
+ * Named `SqsTemplateException` (not `SqsException`) to avoid collision with the SDK's
+ * [aws.sdk.kotlin.services.sqs.model.SqsException].
+ */
+open class SqsTemplateException(message: String, cause: Throwable? = null) : RuntimeException(message, cause)
+
+/** The queue name or URL could not be resolved to an existing queue. */
+class SqsQueueNotFoundException(queueNameOrUrl: String, cause: Throwable? = null) :
+    SqsTemplateException("Queue not found: $queueNameOrUrl", cause)
+
+/** A message payload could not be serialized or deserialized. */
+class SqsMessageConversionException(message: String, cause: Throwable? = null) : SqsTemplateException(message, cause)
+
+/** An SQS operation failed validation or the SDK reported an error. The original cause is preserved. */
+class SqsOperationException(message: String, cause: Throwable? = null) : SqsTemplateException(message, cause)
diff --git a/sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/listener/Acknowledge.kt b/sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/listener/Acknowledge.kt
new file mode 100644
index 0000000..658495b
--- /dev/null
+++ b/sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/listener/Acknowledge.kt
@@ -0,0 +1,31 @@
+package io.github.hantsy.awskotlin.sqs.listener
+
+import io.github.hantsy.awskotlin.sqs.SqsMessage
+import io.github.hantsy.awskotlin.sqs.SqsTemplate
+
+/**
+ * Manual acknowledgement handle passed to listener methods that declare an
+ * [Acknowledge] parameter. Bound to the received message and its queue.
+ */
+interface Acknowledge {
+    /** Deletes the message from the queue. */
+    suspend fun accept()
+
+    /** Makes the message immediately visible again (visibility timeout 0). */
+    suspend fun reject()
+}
+
+/** Default [Acknowledge] backed by the fluent template's queue-scoped operations. */
+internal class DefaultAcknowledge(
+    private val template: SqsTemplate,
+    private val queueUrl: String,
+    private val message: SqsMessage<*>
+) : Acknowledge {
+    override suspend fun accept() {
+        template.destination(queueUrl).acknowledge(message)
+    }
+
+    override suspend fun reject() {
+        template.destination(queueUrl).reject(message)
+    }
+}
diff --git a/sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/listener/SqsListener.kt b/sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/listener/SqsListener.kt
new file mode 100644
index 0000000..d4e47b6
--- /dev/null
+++ b/sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/listener/SqsListener.kt
@@ -0,0 +1,19 @@
+package io.github.hantsy.awskotlin.sqs.listener
+
+/**
+ * Marks a member function as an SQS message listener for the given standard [queues]
+ * (names or URLs).
+ *
+ * The method must declare exactly one payload parameter — either a plain payload
+ * type `T` or [io.github.hantsy.awskotlin.sqs.SqsMessage]`<T>` with a concrete `T` —
+ * and may optionally declare an [Acknowledge] parameter. Suspend and non-suspend
+ * methods are both supported.
+ *
+ * Without an [Acknowledge] parameter the message is deleted automatically after the
+ * method returns successfully; if the method throws, the message is left in flight.
+ * With an [Acknowledge] parameter the caller decides: [Acknowledge.accept] deletes,
+ * [Acknowledge.reject] makes the message visible again, and no decision leaves it.
+ */
+@Target(AnnotationTarget.FUNCTION)
+@Retention(AnnotationRetention.RUNTIME)
+annotation class SqsListener(vararg val queues: String)
diff --git a/sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/listener/SqsListenerAnnotationRegistrar.kt b/sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/listener/SqsListenerAnnotationRegistrar.kt
new file mode 100644
index 0000000..2fa81cf
--- /dev/null
+++ b/sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/listener/SqsListenerAnnotationRegistrar.kt
@@ -0,0 +1,111 @@
+package io.github.hantsy.awskotlin.sqs.listener
+
+import io.github.hantsy.awskotlin.sqs.SqsQueueResolver
+import io.github.hantsy.awskotlin.sqs.SqsTemplate
+import kotlinx.coroutines.CoroutineScope
+import kotlinx.coroutines.Dispatchers
+import kotlinx.coroutines.SupervisorJob
+import org.springframework.aop.framework.AopProxyUtils
+import org.springframework.beans.factory.config.BeanPostProcessor
+import org.springframework.context.SmartLifecycle
+import java.lang.reflect.Method
+import java.util.concurrent.CopyOnWriteArrayList
+import kotlin.reflect.KFunction
+import kotlin.reflect.KParameter
+import kotlin.reflect.full.findAnnotation
+import kotlin.reflect.full.memberFunctions
+import kotlin.reflect.jvm.jvmErasure
+import kotlin.reflect.jvm.kotlinFunction
+
+/**
+ * Collects [SqsListener]-annotated methods from beans and runs them.
+ *
+ * [postProcessAfterInitialization] only records endpoints; no polling starts before
+ * the context refresh. Annotations are discovered on the ultimate target class behind
+ * Spring AOP proxies, and singleton targets are invoked directly. [start] creates and
+ * starts one [SqsListenerContainer] per
+ * endpoint — a missing queue fails startup and stops any containers already started.
+ * [stop] stops all containers. Testable without an ApplicationContext.
+ */
+class SqsListenerAnnotationRegistrar(
+    private val template: SqsTemplate,
+    private val queueResolver: SqsQueueResolver,
+    private val config: SqsListenerConfig = SqsListenerConfig(),
+    private val scope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
+) : BeanPostProcessor,
+    SmartLifecycle {
+    private val endpoints = CopyOnWriteArrayList<SqsListenerEndpoint>()
+    private val containers = CopyOnWriteArrayList<SqsListenerContainer>()
+
+    @Volatile
+    private var running = false
+
+    override fun postProcessAfterInitialization(bean: Any, beanName: String): Any {
+        // Proxy-aware discovery: CGLIB overrides drop method annotations and JDK proxies only
+        // expose interface methods, so scan the ultimate target class. When the proxy has a
+        // singleton target, invoke that target directly; otherwise fall back to the bean itself.
+        val endpointBean = AopProxyUtils.getSingletonTarget(bean) ?: bean
+        if (endpoints.any { it.bean === endpointBean }) return bean
+        val targetClass = AopProxyUtils.ultimateTargetClass(bean)
+        // Pre-filter with Java reflection: safe and cheap for any bean (proxies, lambdas, Java classes).
+        // declaredMethods is included so private annotated Kotlin listeners are not silently skipped.
+        val annotated = (targetClass.methods.asSequence() + targetClass.declaredMethods.asSequence())
+            .filter { !it.isBridge && !it.isSynthetic && it.isAnnotationPresent(SqsListener::class.java) }
+            .distinct()
+            .toList()
+        if (annotated.isEmpty()) return bean
+        val functions = targetClass.kotlin.memberFunctions
+        annotated.forEach { method ->
+            // Kotlin discovery is authoritative: kotlinFunction resolves internal name mangling
+            // (foo$module) and private members that plain name matching cannot map back.
+            val function = method.kotlinFunction
+                ?: functions.firstOrNull { it.matches(method) }
+                ?: functions.firstOrNull {
+                    it.name == method.name.substringBefore('$') && it.findAnnotation<SqsListener>() != null
+                }
+                ?: error("No Kotlin member function for annotated method ${method.name} on ${targetClass.name}")
+            val annotation = function.findAnnotation<SqsListener>() ?: method.getAnnotation(SqsListener::class.java)
+            endpoints += SqsListenerEndpoint(endpointBean, function, annotation.queues.toList())
+        }
+        return bean
+    }
+
+    override fun isAutoStartup(): Boolean = config.autoStartup
+
+    override fun isRunning(): Boolean = running
+
+    @Synchronized
+    override fun start() {
+        if (running) return
+        val started = mutableListOf<SqsListenerContainer>()
+        try {
+            endpoints.forEach { endpoint ->
+                val container = SqsListenerContainer(endpoint, template, queueResolver, config, scope)
+                container.start()
+                started += container
+            }
+        } catch (e: Exception) {
+            started.forEach { runCatching { it.stop() } }
+            throw e
+        }
+        containers += started
+        running = true
+    }
+
+    @Synchronized
+    override fun stop() {
+        if (!running) return
+        running = false
+        containers.forEach { it.stop() }
+        containers.clear()
+    }
+
+    // Maps a Java method back to its Kotlin function; suspend methods carry a trailing Continuation parameter.
+    private fun KFunction<*>.matches(method: Method): Boolean {
+        if (name != method.name) return false
+        val valueParameters = parameters.filter { it.kind == KParameter.Kind.VALUE }
+        val parameterTypes = method.parameterTypes.let { if (isSuspend) it.dropLast(1) else it.asList() }
+        return valueParameters.size == parameterTypes.size &&
+            valueParameters.zip(parameterTypes).all { (parameter, type) -> parameter.type.jvmErasure.java == type }
+    }
+}
diff --git a/sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/listener/SqsListenerConfig.kt b/sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/listener/SqsListenerConfig.kt
new file mode 100644
index 0000000..054013e
--- /dev/null
+++ b/sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/listener/SqsListenerConfig.kt
@@ -0,0 +1,19 @@
+package io.github.hantsy.awskotlin.sqs.listener
+
+import kotlin.time.Duration
+import kotlin.time.Duration.Companion.seconds
+
+/**
+ * Runtime settings shared by listener containers.
+ *
+ * [maxConcurrentMessages] caps in-flight messages per container across all its
+ * queues; [pollTime] is the long-polling wait per receive (clamped to 0..20 seconds
+ * by the template); [shutdownTimeout] bounds how long stop waits for in-flight
+ * message processing before cancelling it.
+ */
+data class SqsListenerConfig(
+    val autoStartup: Boolean = true,
+    val maxConcurrentMessages: Int = 10,
+    val pollTime: Duration = 10.seconds,
+    val shutdownTimeout: Duration = 20.seconds
+)
diff --git a/sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/listener/SqsListenerContainer.kt b/sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/listener/SqsListenerContainer.kt
new file mode 100644
index 0000000..39932ea
--- /dev/null
+++ b/sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/listener/SqsListenerContainer.kt
@@ -0,0 +1,154 @@
+package io.github.hantsy.awskotlin.sqs.listener
+
+import io.github.hantsy.awskotlin.sqs.SqsAcknowledgementMode
+import io.github.hantsy.awskotlin.sqs.SqsMessage
+import io.github.hantsy.awskotlin.sqs.SqsQueueResolver
+import io.github.hantsy.awskotlin.sqs.SqsTemplate
+import kotlinx.coroutines.CancellationException
+import kotlinx.coroutines.CoroutineScope
+import kotlinx.coroutines.Dispatchers
+import kotlinx.coroutines.Job
+import kotlinx.coroutines.SupervisorJob
+import kotlinx.coroutines.cancel
+import kotlinx.coroutines.delay
+import kotlinx.coroutines.launch
+import kotlinx.coroutines.runBlocking
+import kotlinx.coroutines.sync.Semaphore
+import kotlinx.coroutines.withTimeoutOrNull
+import kotlinx.coroutines.yield
+import org.apache.commons.logging.LogFactory
+import org.springframework.context.SmartLifecycle
+import kotlin.time.Duration.Companion.seconds
+
+/**
+ * Polls the endpoint's queues and invokes the listener method for each message.
+ *
+ * Standard queues only. [start] resolves every queue URL up front — missing queues
+ * ([io.github.hantsy.awskotlin.sqs.SqsQueueNotFoundException]) and FIFO queues
+ * ([io.github.hantsy.awskotlin.sqs.SqsOperationException]) fail startup; nothing is
+ * created implicitly. Each queue gets one long-polling loop in the provided [scope]
+ * under a child [SupervisorJob], so [stop] cancels container work without cancelling
+ * the external scope. A shared [Semaphore] caps in-flight messages at
+ * [SqsListenerConfig.maxConcurrentMessages] across all queues.
+ *
+ * Messages are received in [SqsAcknowledgementMode.MANUAL]; the container deletes
+ * after successful invocation only when the method has no [Acknowledge] parameter.
+ */
+class SqsListenerContainer(
+    private val endpoint: SqsListenerEndpoint,
+    private val template: SqsTemplate,
+    private val queueResolver: SqsQueueResolver,
+    private val config: SqsListenerConfig = SqsListenerConfig(),
+    private val scope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
+) : SmartLifecycle {
+    private val log = LogFactory.getLog(SqsListenerContainer::class.java)
+    private val method = SqsListenerMethod(endpoint.method)
+
+    @Volatile
+    private var running = false
+    private var containerJob: Job? = null
+    private var pollingJob: Job? = null
+    private var processingJob: Job? = null
+
+    init {
+        require(endpoint.queues.isNotEmpty()) { "SqsListenerEndpoint must declare at least one queue" }
+        require(config.maxConcurrentMessages > 0) {
+            "maxConcurrentMessages must be positive, got ${config.maxConcurrentMessages}"
+        }
+    }
+
+    override fun isAutoStartup(): Boolean = config.autoStartup
+
+    override fun isRunning(): Boolean = running
+
+    @Synchronized
+    override fun start() {
+        if (running) return
+        // Resolve every queue before polling; failures propagate and leave the container stopped.
+        val queueUrls = runBlocking { endpoint.queues.map { queueResolver.resolve(it) } }
+        val parent = SupervisorJob(scope.coroutineContext[Job])
+        val polling = SupervisorJob(parent)
+        val processing = SupervisorJob(parent)
+        containerJob = parent
+        pollingJob = polling
+        processingJob = processing
+        val semaphore = Semaphore(config.maxConcurrentMessages)
+        val pollingScope = CoroutineScope(scope.coroutineContext + polling)
+        val processingScope = CoroutineScope(scope.coroutineContext + processing)
+        queueUrls.forEach { queueUrl -> pollingScope.launch { pollLoop(queueUrl, semaphore, processingScope) } }
+        running = true
+    }
+
+    @Synchronized
+    override fun stop() {
+        if (!running) return
+        running = false
+        val parent = containerJob
+        val polling = pollingJob
+        val processing = processingJob
+        containerJob = null
+        pollingJob = null
+        processingJob = null
+        polling?.cancel()
+        if (processing != null) {
+            // Give in-flight processing a chance to finish before cancelling it.
+            runBlocking {
+                withTimeoutOrNull(config.shutdownTimeout) {
+                    processing.children.toList().forEach { it.join() }
+                }
+            }
+        }
+        parent?.cancel()
+    }
+
+    private suspend fun pollLoop(queueUrl: String, semaphore: Semaphore, processingScope: CoroutineScope) {
+        var backoff = INITIAL_BACKOFF
+        while (true) {
+            semaphore.acquire()
+            val message = try {
+                template.destination(queueUrl)
+                    .receive(method.payloadType, config.pollTime, SqsAcknowledgementMode.MANUAL)
+            } catch (e: CancellationException) {
+                semaphore.release()
+                throw e
+            } catch (e: Exception) {
+                semaphore.release()
+                log.warn("Receive failed on $queueUrl; retrying in $backoff", e)
+                delay(backoff)
+                backoff = (backoff * 2).coerceAtMost(MAX_BACKOFF)
+                continue
+            }
+            backoff = INITIAL_BACKOFF
+            if (message == null) {
+                semaphore.release()
+                yield()
+                continue
+            }
+            processingScope.launch {
+                try {
+                    process(queueUrl, message)
+                } finally {
+                    semaphore.release()
+                }
+            }
+        }
+    }
+
+    private suspend fun process(queueUrl: String, message: SqsMessage<Any>) {
+        try {
+            method.invoke(endpoint.bean, message, DefaultAcknowledge(template, queueUrl, message))
+            if (!method.hasAcknowledgeParameter) {
+                template.destination(queueUrl).acknowledge(message)
+            }
+        } catch (e: CancellationException) {
+            throw e
+        } catch (e: Exception) {
+            log.error("Listener ${endpoint.method.name} failed for message ${message.messageId} on $queueUrl", e)
+        }
+    }
+
+    private companion object {
+        val INITIAL_BACKOFF = 1.seconds
+        val MAX_BACKOFF = 30.seconds
+    }
+}
diff --git a/sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/listener/SqsListenerEndpoint.kt b/sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/listener/SqsListenerEndpoint.kt
new file mode 100644
index 0000000..e1eeb67
--- /dev/null
+++ b/sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/listener/SqsListenerEndpoint.kt
@@ -0,0 +1,9 @@
+package io.github.hantsy.awskotlin.sqs.listener
+
+import kotlin.reflect.KFunction
+
+/**
+ * A discovered listener: the target [bean], its annotated [method] and the
+ * standard [queues] (names or URLs) it consumes.
+ */
+data class SqsListenerEndpoint(val bean: Any, val method: KFunction<*>, val queues: List<String>)
diff --git a/sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/listener/SqsListenerMethod.kt b/sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/listener/SqsListenerMethod.kt
new file mode 100644
index 0000000..6649d0b
--- /dev/null
+++ b/sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/listener/SqsListenerMethod.kt
@@ -0,0 +1,96 @@
+package io.github.hantsy.awskotlin.sqs.listener
+
+import io.github.hantsy.awskotlin.sqs.SqsMessage
+import kotlin.reflect.KClass
+import kotlin.reflect.KFunction
+import kotlin.reflect.KParameter
+import kotlin.reflect.full.callSuspendBy
+import kotlin.reflect.jvm.isAccessible
+
+/**
+ * Validated reflective view of a listener method.
+ *
+ * Enforces the endpoint contract: exactly one payload-bearing parameter (plain `T`
+ * or `SqsMessage<T>` with a concrete `T`), at most one [Acknowledge] parameter and
+ * no unsupported parameters. Invalid methods fail with [IllegalArgumentException].
+ */
+internal class SqsListenerMethod(private val function: KFunction<*>) {
+    private enum class Role { INSTANCE, MESSAGE, PAYLOAD, ACKNOWLEDGE }
+
+    private val roles: Map<KParameter, Role>
+    val payloadType: Class<Any>
+    val hasAcknowledgeParameter: Boolean
+
+    init {
+        val roles = mutableMapOf<KParameter, Role>()
+        var payloadClass: KClass<*>? = null
+        var payloadCount = 0
+        var acknowledgeCount = 0
+        for (parameter in function.parameters) {
+            when (parameter.kind) {
+                KParameter.Kind.INSTANCE -> roles[parameter] = Role.INSTANCE
+
+                KParameter.Kind.VALUE -> when (val classifier = parameter.type.classifier) {
+                    Acknowledge::class -> {
+                        roles[parameter] = Role.ACKNOWLEDGE
+                        acknowledgeCount++
+                    }
+
+                    SqsMessage::class -> {
+                        val argument = parameter.type.arguments.singleOrNull()?.type?.classifier as? KClass<*>
+                        require(argument != null) {
+                            "SqsMessage parameter of ${function.name} must have a concrete payload type, " +
+                                "not a star projection"
+                        }
+                        roles[parameter] = Role.MESSAGE
+                        payloadClass = argument
+                        payloadCount++
+                    }
+
+                    is KClass<*> -> {
+                        roles[parameter] = Role.PAYLOAD
+                        payloadClass = classifier
+                        payloadCount++
+                    }
+
+                    else -> throw IllegalArgumentException(
+                        "Unsupported parameter ${parameter.name} of listener method ${function.name}"
+                    )
+                }
+
+                else -> throw IllegalArgumentException(
+                    "Unsupported parameter ${parameter.name} of listener method ${function.name}"
+                )
+            }
+        }
+        require(roles.values.count { it == Role.INSTANCE } == 1) {
+            "Listener method ${function.name} must be a member function"
+        }
+        require(payloadCount == 1) {
+            "Listener method ${function.name} must declare exactly one payload parameter, got $payloadCount"
+        }
+        require(acknowledgeCount <= 1) {
+            "Listener method ${function.name} must declare at most one Acknowledge parameter"
+        }
+        this.roles = roles
+
+        @Suppress("UNCHECKED_CAST")
+        payloadType = requireNotNull(payloadClass).java as Class<Any>
+        hasAcknowledgeParameter = acknowledgeCount == 1
+    }
+
+    /** Invokes the method, resolving each parameter by its validated role. */
+    suspend fun invoke(bean: Any, message: SqsMessage<*>, acknowledge: Acknowledge) {
+        val arguments = function.parameters.associateWith { parameter ->
+            when (roles[parameter]) {
+                Role.INSTANCE -> bean
+                Role.MESSAGE -> message
+                Role.PAYLOAD -> message.payload
+                Role.ACKNOWLEDGE -> acknowledge
+                null -> error("No role for parameter ${parameter.name}")
+            }
+        }
+        function.isAccessible = true
+        if (function.isSuspend) function.callSuspendBy(arguments) else function.callBy(arguments)
+    }
+}
diff --git a/sqs/src/test/kotlin/io/github/hantsy/awskotlin/sqs/KotlinxSqsMessageConverterTest.kt b/sqs/src/test/kotlin/io/github/hantsy/awskotlin/sqs/KotlinxSqsMessageConverterTest.kt
new file mode 100644
index 0000000..339208c
--- /dev/null
+++ b/sqs/src/test/kotlin/io/github/hantsy/awskotlin/sqs/KotlinxSqsMessageConverterTest.kt
@@ -0,0 +1,68 @@
+package io.github.hantsy.awskotlin.sqs
+
+import io.kotest.assertions.throwables.shouldThrow
+import io.kotest.matchers.shouldBe
+import io.kotest.matchers.types.shouldBeInstanceOf
+import kotlinx.serialization.Serializable
+import kotlinx.serialization.SerializationException
+import org.junit.jupiter.api.Test
+
+@Serializable
+data class Order(val id: String, val quantity: Int)
+
+class KotlinxSqsMessageConverterTest {
+    private val converter = KotlinxSqsMessageConverter()
+
+    @Test
+    fun `toBody returns String payload unchanged`() {
+        converter.toBody("plain text, not JSON") shouldBe "plain text, not JSON"
+    }
+
+    @Test
+    fun `fromBody returns body unchanged for String target`() {
+        converter.fromBody("plain text, not JSON", String::class.java) shouldBe "plain text, not JSON"
+    }
+
+    @Test
+    fun `data class roundtrips through JSON`() {
+        val order = Order("o-1", 2)
+        val body = converter.toBody(order)
+        body shouldBe """{"id":"o-1","quantity":2}"""
+        converter.fromBody(body, Order::class.java) shouldBe order
+    }
+
+    @Test
+    fun `unknown JSON fields are ignored`() {
+        val body = """{"id":"o-2","quantity":1,"extra":"ignored"}"""
+        converter.fromBody(body, Order::class.java) shouldBe Order("o-2", 1)
+    }
+
+    @Test
+    fun `serialization failure throws SqsMessageConversionException`() {
+        val cyclic = mutableListOf<Any>()
+        cyclic.add(cyclic)
+        val thrown = shouldThrow<SqsMessageConversionException> { converter.toBody(cyclic) }
+        // Star-projected generic payloads fail serializer lookup with IllegalArgumentException
+        // (SerializationException, its subclass, is used for other serialization errors).
+        thrown.cause.shouldBeInstanceOf<IllegalArgumentException>()
+    }
+
+    @Test
+    fun `non-serializable payload throws SqsMessageConversionException`() {
+        val thrown = shouldThrow<SqsMessageConversionException> { converter.toBody(NotSerializable("x")) }
+        thrown.cause.shouldBeInstanceOf<SerializationException>()
+    }
+
+    @Test
+    fun `deserialization failure throws SqsMessageConversionException`() {
+        val thrown = shouldThrow<SqsMessageConversionException> { converter.fromBody("{not json", Order::class.java) }
+        thrown.cause.shouldBeInstanceOf<SerializationException>()
+    }
+
+    @Test
+    fun `null JSON body throws SqsMessageConversionException`() {
+        shouldThrow<SqsMessageConversionException> { converter.fromBody("null", Order::class.java) }
+    }
+}
+
+private class NotSerializable(val value: String)
diff --git a/sqs/src/test/kotlin/io/github/hantsy/awskotlin/sqs/SqsQueueResolverTest.kt b/sqs/src/test/kotlin/io/github/hantsy/awskotlin/sqs/SqsQueueResolverTest.kt
new file mode 100644
index 0000000..ba7cd82
--- /dev/null
+++ b/sqs/src/test/kotlin/io/github/hantsy/awskotlin/sqs/SqsQueueResolverTest.kt
@@ -0,0 +1,83 @@
+package io.github.hantsy.awskotlin.sqs
+
+import aws.sdk.kotlin.services.sqs.SqsClient
+import aws.sdk.kotlin.services.sqs.model.GetQueueUrlRequest
+import aws.sdk.kotlin.services.sqs.model.GetQueueUrlResponse
+import aws.sdk.kotlin.services.sqs.model.QueueDoesNotExist
+import io.kotest.assertions.throwables.shouldThrow
+import io.kotest.matchers.shouldBe
+import io.mockk.coEvery
+import io.mockk.coVerify
+import io.mockk.confirmVerified
+import io.mockk.mockk
+import io.mockk.slot
+import kotlinx.coroutines.test.runTest
+import org.junit.jupiter.api.Test
+
+class SqsQueueResolverTest {
+    private val client = mockk<SqsClient>()
+    private val resolver = SqsQueueResolver(client)
+    private val queueUrl = "https://sqs.us-east-1.amazonaws.com/123456789012/orders"
+
+    @Test
+    fun `https URL passes through unchanged without SDK call`() = runTest {
+        resolver.resolve(queueUrl) shouldBe queueUrl
+        confirmVerified(client)
+    }
+
+    @Test
+    fun `http URL passes through unchanged without SDK call`() = runTest {
+        val url = "http://localhost:4566/000000000000/orders"
+        resolver.resolve(url) shouldBe url
+        confirmVerified(client)
+    }
+
+    @Test
+    fun `queue name is resolved with getQueueUrl`() = runTest {
+        val url = queueUrl
+        val request = slot<GetQueueUrlRequest>()
+        coEvery { client.getQueueUrl(capture(request)) } returns GetQueueUrlResponse { queueUrl = url }
+
+        resolver.resolve("orders") shouldBe queueUrl
+        request.captured.queueName shouldBe "orders"
+    }
+
+    @Test
+    fun `successful name resolution is cached`() = runTest {
+        val url = queueUrl
+        coEvery { client.getQueueUrl(any<GetQueueUrlRequest>()) } returns GetQueueUrlResponse { queueUrl = url }
+
+        resolver.resolve("orders") shouldBe queueUrl
+        resolver.resolve("orders") shouldBe queueUrl
+
+        coVerify(exactly = 1) { client.getQueueUrl(any<GetQueueUrlRequest>()) }
+    }
+
+    @Test
+    fun `QueueDoesNotExist maps to SqsQueueNotFoundException with cause`() = runTest {
+        val failure = QueueDoesNotExist { message = "queue does not exist" }
+        coEvery { client.getQueueUrl(any<GetQueueUrlRequest>()) } throws failure
+
+        val thrown = shouldThrow<SqsQueueNotFoundException> { resolver.resolve("missing") }
+        thrown.cause shouldBe failure
+    }
+
+    @Test
+    fun `missing queueUrl in response maps to SqsQueueNotFoundException`() = runTest {
+        coEvery { client.getQueueUrl(any<GetQueueUrlRequest>()) } returns GetQueueUrlResponse { }
+
+        shouldThrow<SqsQueueNotFoundException> { resolver.resolve("missing") }
+    }
+
+    @Test
+    fun `FIFO queue name is rejected before any SDK call`() = runTest {
+        shouldThrow<SqsOperationException> { resolver.resolve("orders.fifo") }
+        confirmVerified(client)
+    }
+
+    @Test
+    fun `FIFO queue URL is rejected before any SDK call`() = runTest {
+        shouldThrow<SqsOperationException> { resolver.resolve("$queueUrl.fifo") }
+        confirmVerified(client)
+    }
+}
diff --git a/sqs/src/test/kotlin/io/github/hantsy/awskotlin/sqs/SqsTemplateTest.kt b/sqs/src/test/kotlin/io/github/hantsy/awskotlin/sqs/SqsTemplateTest.kt
new file mode 100644
index 0000000..4f2bef9
--- /dev/null
+++ b/sqs/src/test/kotlin/io/github/hantsy/awskotlin/sqs/SqsTemplateTest.kt
@@ -0,0 +1,324 @@
+package io.github.hantsy.awskotlin.sqs
+
+import aws.sdk.kotlin.services.sqs.SqsClient
+import aws.sdk.kotlin.services.sqs.model.ChangeMessageVisibilityRequest
+import aws.sdk.kotlin.services.sqs.model.ChangeMessageVisibilityResponse
+import aws.sdk.kotlin.services.sqs.model.DeleteMessageRequest
+import aws.sdk.kotlin.services.sqs.model.DeleteMessageResponse
+import aws.sdk.kotlin.services.sqs.model.GetQueueUrlRequest
+import aws.sdk.kotlin.services.sqs.model.GetQueueUrlResponse
+import aws.sdk.kotlin.services.sqs.model.Message
+import aws.sdk.kotlin.services.sqs.model.MessageAttributeValue
+import aws.sdk.kotlin.services.sqs.model.MessageNotInflight
+import aws.sdk.kotlin.services.sqs.model.MessageSystemAttributeName
+import aws.sdk.kotlin.services.sqs.model.QueueDoesNotExist
+import aws.sdk.kotlin.services.sqs.model.ReceiptHandleIsInvalid
+import aws.sdk.kotlin.services.sqs.model.ReceiveMessageRequest
+import aws.sdk.kotlin.services.sqs.model.ReceiveMessageResponse
+import aws.sdk.kotlin.services.sqs.model.SendMessageRequest
+import aws.sdk.kotlin.services.sqs.model.SendMessageResponse
+import aws.sdk.kotlin.services.sqs.model.SqsException
+import io.kotest.assertions.throwables.shouldThrow
+import io.kotest.matchers.nulls.shouldBeNull
+import io.kotest.matchers.shouldBe
+import io.mockk.coEvery
+import io.mockk.coVerify
+import io.mockk.confirmVerified
+import io.mockk.mockk
+import io.mockk.slot
+import kotlinx.coroutines.test.runTest
+import org.junit.jupiter.api.Test
+import kotlin.time.Duration.Companion.seconds
+
+class SqsTemplateTest {
+    private val client = mockk<SqsClient>()
+    private val template = SqsTemplate(client)
+    private val queueName = "orders"
+    private val queueUrl = "https://sqs.us-east-1.amazonaws.com/123456789012/orders"
+    private val orderJson = """{"id":"o-1","quantity":2}"""
+
+    private fun stubQueueUrl() {
+        val url = queueUrl
+        coEvery { client.getQueueUrl(any<GetQueueUrlRequest>()) } returns GetQueueUrlResponse { queueUrl = url }
+    }
+
+    private fun sdkMessage(body: String) = Message {
+        messageId = "m-1"
+        receiptHandle = "rh-1"
+        this.body = body
+        attributes = mapOf(MessageSystemAttributeName.SentTimestamp to "1700000000000")
+        messageAttributes = mapOf(
+            "trace" to MessageAttributeValue {
+                dataType = "String";
+                stringValue = "t-1"
+            }
+        )
+    }
+
+    private fun receivedMessage() = SqsMessage(
+        payload = "p",
+        messageId = "m-1",
+        receiptHandle = "rh-1",
+        body = "p",
+        attributes = emptyMap(),
+        messageAttributes = emptyMap(),
+    )
+
+    @Test
+    fun `send serializes payload as JSON and resolves queue by name`() = runTest {
+        val queueRequest = slot<GetQueueUrlRequest>()
+        val url = queueUrl
+        coEvery { client.getQueueUrl(capture(queueRequest)) } returns GetQueueUrlResponse { queueUrl = url }
+        val request = slot<SendMessageRequest>()
+        val response = SendMessageResponse { messageId = "sent-1" }
+        coEvery { client.sendMessage(capture(request)) } returns response
+
+        val result = template.destination(queueName).send(Order("o-1", 2))
+
+        result shouldBe response
+        queueRequest.captured.queueName shouldBe queueName
+        request.captured.queueUrl shouldBe queueUrl
+        request.captured.messageBody shouldBe orderJson
+        request.captured.delaySeconds.shouldBeNull()
+    }
+
+    @Test
+    fun `send passes String payload through unchanged`() = runTest {
+        stubQueueUrl()
+        val request = slot<SendMessageRequest>()
+        coEvery { client.sendMessage(capture(request)) } returns SendMessageResponse { }
+
+        template.destination(queueName).send("plain text")
+
+        request.captured.messageBody shouldBe "plain text"
+    }
+
+    @Test
+    fun `send accepts queue URL without resolution`() = runTest {
+        val request = slot<SendMessageRequest>()
+        coEvery { client.sendMessage(capture(request)) } returns SendMessageResponse { }
+
+        template.destination(queueUrl).send("hello")
+
+        request.captured.queueUrl shouldBe queueUrl
+        coVerify(exactly = 0) { client.getQueueUrl(any<GetQueueUrlRequest>()) }
+    }
+
+    @Test
+    fun `send sets delaySeconds from delay`() = runTest {
+        stubQueueUrl()
+        val request = slot<SendMessageRequest>()
+        coEvery { client.sendMessage(capture(request)) } returns SendMessageResponse { }
+
+        template.destination(queueName).send("hello", delay = 5.seconds)
+
+        request.captured.delaySeconds shouldBe 5
+    }
+
+    @Test
+    fun `send rejects negative delay without SDK send call`() = runTest {
+        shouldThrow<SqsOperationException> { template.destination(queueName).send("hello", delay = (-1).seconds) }
+        coVerify(exactly = 0) { client.sendMessage(any<SendMessageRequest>()) }
+    }
+
+    @Test
+    fun `send rejects delay above 900 seconds without SDK send call`() = runTest {
+        shouldThrow<SqsOperationException> { template.destination(queueName).send("hello", delay = 901.seconds) }
+        coVerify(exactly = 0) { client.sendMessage(any<SendMessageRequest>()) }
+    }
+
+    @Test
+    fun `send maps QueueDoesNotExist from sendMessage with cause`() = runTest {
+        stubQueueUrl()
+        val failure = QueueDoesNotExist { message = "queue vanished" }
+        coEvery { client.sendMessage(any<SendMessageRequest>()) } throws failure
+
+        val thrown = shouldThrow<SqsQueueNotFoundException> { template.destination(queueName).send("hello") }
+        thrown.cause shouldBe failure
+    }
+
+    @Test
+    fun `receive returns null for empty response and builds expected request`() = runTest {
+        stubQueueUrl()
+        val request = slot<ReceiveMessageRequest>()
+        coEvery { client.receiveMessage(capture(request)) } returns ReceiveMessageResponse { messages = emptyList() }
+
+        template.destination(queueName).receive(String::class.java).shouldBeNull()
+
+        request.captured.queueUrl shouldBe queueUrl
+        request.captured.maxNumberOfMessages shouldBe 1
+        request.captured.waitTimeSeconds shouldBe 10
+        request.captured.messageAttributeNames shouldBe listOf("All")
+        request.captured.messageSystemAttributeNames shouldBe listOf(MessageSystemAttributeName.All)
+        coVerify(exactly = 0) { client.deleteMessage(any<DeleteMessageRequest>()) }
+    }
+
+    @Test
+    fun `receive AUTO converts payload and deletes message`() = runTest {
+        stubQueueUrl()
+        val message = sdkMessage(orderJson)
+        coEvery { client.receiveMessage(any<ReceiveMessageRequest>()) } returns
+            ReceiveMessageResponse { messages = listOf(message) }
+        val deleteRequest = slot<DeleteMessageRequest>()
+        val deleteResponse = DeleteMessageResponse { }
+        coEvery { client.deleteMessage(capture(deleteRequest)) } returns deleteResponse
+
+        val result = template.destination(queueName).receive(Order::class.java)
+
+        result shouldBe SqsMessage(
+            payload = Order("o-1", 2),
+            messageId = "m-1",
+            receiptHandle = "rh-1",
+            body = orderJson,
+            attributes = mapOf("SentTimestamp" to "1700000000000"),
+            messageAttributes = message.messageAttributes!!,
+        )
+        deleteRequest.captured.queueUrl shouldBe queueUrl
+        deleteRequest.captured.receiptHandle shouldBe "rh-1"
+    }
+
+    @Test
+    fun `receive MANUAL does not delete message`() = runTest {
+        stubQueueUrl()
+        coEvery { client.receiveMessage(any<ReceiveMessageRequest>()) } returns
+            ReceiveMessageResponse { messages = listOf(sdkMessage(orderJson)) }
+
+        val result = template.destination(queueName)
+            .receive(Order::class.java, acknowledgement = SqsAcknowledgementMode.MANUAL)
+
+        result!!.payload shouldBe Order("o-1", 2)
+        coVerify(exactly = 0) { client.deleteMessage(any<DeleteMessageRequest>()) }
+    }
+
+    @Test
+    fun `receive conversion failure does not delete message`() = runTest {
+        stubQueueUrl()
+        coEvery { client.receiveMessage(any<ReceiveMessageRequest>()) } returns
+            ReceiveMessageResponse { messages = listOf(sdkMessage("{not json")) }
+
+        shouldThrow<SqsMessageConversionException> { template.destination(queueName).receive(Order::class.java) }
+        coVerify(exactly = 0) { client.deleteMessage(any<DeleteMessageRequest>()) }
+    }
+
+    @Test
+    fun `receive clamps timeout to 20 seconds`() = runTest {
+        stubQueueUrl()
+        val request = slot<ReceiveMessageRequest>()
+        coEvery { client.receiveMessage(capture(request)) } returns ReceiveMessageResponse { }
+
+        template.destination(queueName).receive(String::class.java, timeout = 60.seconds)
+
+        request.captured.waitTimeSeconds shouldBe 20
+    }
+
+    @Test
+    fun `receive rejects negative timeout without SDK receive call`() = runTest {
+        shouldThrow<SqsOperationException> {
+            template.destination(queueName).receive(String::class.java, timeout = (-1).seconds)
+        }
+        coVerify(exactly = 0) { client.receiveMessage(any<ReceiveMessageRequest>()) }
+    }
+
+    @Test
+    fun `receive AUTO delete failure wraps SqsOperationException with cause`() = runTest {
+        stubQueueUrl()
+        coEvery { client.receiveMessage(any<ReceiveMessageRequest>()) } returns
+            ReceiveMessageResponse { messages = listOf(sdkMessage("hello")) }
+        val failure = SqsException("delete denied")
+        coEvery { client.deleteMessage(any<DeleteMessageRequest>()) } throws failure
+
+        val thrown = shouldThrow<SqsOperationException> { template.destination(queueName).receive(String::class.java) }
+        thrown.cause shouldBe failure
+    }
+
+    @Test
+    fun `reified receive infers target type`() = runTest {
+        stubQueueUrl()
+        coEvery { client.receiveMessage(any<ReceiveMessageRequest>()) } returns
+            ReceiveMessageResponse { messages = listOf(sdkMessage(orderJson)) }
+
+        val result = template.destination(queueName).receive<Order>(acknowledgement = SqsAcknowledgementMode.MANUAL)
+
+        result!!.payload shouldBe Order("o-1", 2)
+        coVerify(exactly = 0) { client.deleteMessage(any<DeleteMessageRequest>()) }
+    }
+
+    @Test
+    fun `receive maps QueueDoesNotExist from receiveMessage with cause`() = runTest {
+        stubQueueUrl()
+        val failure = QueueDoesNotExist { message = "queue vanished" }
+        coEvery { client.receiveMessage(any<ReceiveMessageRequest>()) } throws failure
+
+        val thrown =
+            shouldThrow<SqsQueueNotFoundException> { template.destination(queueName).receive(String::class.java) }
+        thrown.cause shouldBe failure
+    }
+
+    @Test
+    fun `acknowledge deletes with receipt handle and resolved URL`() = runTest {
+        stubQueueUrl()
+        val request = slot<DeleteMessageRequest>()
+        val response = DeleteMessageResponse { }
+        coEvery { client.deleteMessage(capture(request)) } returns response
+
+        val result = template.destination(queueName).acknowledge(receivedMessage())
+
+        result shouldBe response
+        request.captured.queueUrl shouldBe queueUrl
+        request.captured.receiptHandle shouldBe "rh-1"
+    }
+
+    @Test
+    fun `reject sets visibility timeout to zero`() = runTest {
+        stubQueueUrl()
+        val request = slot<ChangeMessageVisibilityRequest>()
+        val response = ChangeMessageVisibilityResponse { }
+        coEvery { client.changeMessageVisibility(capture(request)) } returns response
+
+        val result = template.destination(queueName).reject(receivedMessage())
+
+        result shouldBe response
+        request.captured.queueUrl shouldBe queueUrl
+        request.captured.receiptHandle shouldBe "rh-1"
+        request.captured.visibilityTimeout shouldBe 0
+    }
+
+    @Test
+    fun `acknowledge maps ReceiptHandleIsInvalid with cause`() = runTest {
+        stubQueueUrl()
+        val failure = ReceiptHandleIsInvalid { message = "bad handle" }
+        coEvery { client.deleteMessage(any<DeleteMessageRequest>()) } throws failure
+
+        val thrown =
+            shouldThrow<SqsOperationException> { template.destination(queueName).acknowledge(receivedMessage()) }
+        thrown.cause shouldBe failure
+    }
+
+    @Test
+    fun `reject maps MessageNotInflight with cause`() = runTest {
+        stubQueueUrl()
+        val failure = MessageNotInflight { }
+        coEvery { client.changeMessageVisibility(any<ChangeMessageVisibilityRequest>()) } throws failure
+
+        val thrown = shouldThrow<SqsOperationException> { template.destination(queueName).reject(receivedMessage()) }
+        thrown.cause shouldBe failure
+    }
+
+    @Test
+    fun `acknowledge maps QueueDoesNotExist with cause`() = runTest {
+        stubQueueUrl()
+        val failure = QueueDoesNotExist { message = "queue vanished" }
+        coEvery { client.deleteMessage(any<DeleteMessageRequest>()) } throws failure
+
+        val thrown = shouldThrow<SqsQueueNotFoundException> {
+            template.destination(queueName).acknowledge(receivedMessage())
+        }
+        thrown.cause shouldBe failure
+    }
+
+    @Test
+    fun `destination makes no request`() = runTest {
+        template.destination(queueName)
+        confirmVerified(client)
+    }
+}
diff --git a/sqs/src/test/kotlin/io/github/hantsy/awskotlin/sqs/listener/SqsListenerAnnotationRegistrarTest.kt b/sqs/src/test/kotlin/io/github/hantsy/awskotlin/sqs/listener/SqsListenerAnnotationRegistrarTest.kt
new file mode 100644
index 0000000..7bd981f
--- /dev/null
+++ b/sqs/src/test/kotlin/io/github/hantsy/awskotlin/sqs/listener/SqsListenerAnnotationRegistrarTest.kt
@@ -0,0 +1,358 @@
+package io.github.hantsy.awskotlin.sqs.listener
+
+import aws.sdk.kotlin.services.sqs.model.DeleteMessageResponse
+import io.github.hantsy.awskotlin.sqs.SqsDestinationOperations
+import io.github.hantsy.awskotlin.sqs.SqsMessage
+import io.github.hantsy.awskotlin.sqs.SqsQueueNotFoundException
+import io.github.hantsy.awskotlin.sqs.SqsQueueResolver
+import io.github.hantsy.awskotlin.sqs.SqsTemplate
+import io.kotest.assertions.throwables.shouldThrow
+import io.kotest.matchers.shouldBe
+import io.kotest.matchers.types.shouldBeSameInstanceAs
+import io.mockk.coEvery
+import io.mockk.coVerify
+import io.mockk.confirmVerified
+import io.mockk.every
+import io.mockk.mockk
+import kotlinx.coroutines.ExperimentalCoroutinesApi
+import kotlinx.coroutines.awaitCancellation
+import kotlinx.coroutines.test.advanceUntilIdle
+import kotlinx.coroutines.test.runTest
+import org.junit.jupiter.api.Test
+import org.springframework.aop.framework.ProxyFactory
+
+@OptIn(ExperimentalCoroutinesApi::class)
+class SqsListenerAnnotationRegistrarTest {
+    private val template = mockk<SqsTemplate>()
+    private val resolver = mockk<SqsQueueResolver>()
+    private val operations = mockk<SqsDestinationOperations>()
+    private val urlA = "https://sqs.us-east-1.amazonaws.com/123456789012/a"
+    private val urlB = "https://sqs.us-east-1.amazonaws.com/123456789012/b"
+    private val urlC = "https://sqs.us-east-1.amazonaws.com/123456789012/c"
+
+    private fun stubQueue(name: String, url: String) {
+        coEvery { resolver.resolve(name) } returns url
+        every { template.destination(url) } returns operations
+        coEvery { operations.receive(String::class.java, any(), any()) } coAnswers { awaitCancellation() }
+    }
+
+    @Test
+    fun `postProcess collects annotated methods and start resolves their queues`() = runTest {
+        stubQueue("a", urlA)
+        stubQueue("b", urlB)
+        stubQueue("c", urlC)
+        val registrar = SqsListenerAnnotationRegistrar(template, resolver, scope = this)
+        val bean = AnnotatedBean()
+
+        registrar.postProcessAfterInitialization(bean, "listener") shouldBeSameInstanceAs bean
+        registrar.start()
+        registrar.isRunning shouldBe true
+        advanceUntilIdle()
+
+        coVerify(exactly = 1) { resolver.resolve("a") }
+        coVerify(exactly = 1) { resolver.resolve("b") }
+        coVerify(exactly = 1) { resolver.resolve("c") }
+        coVerify(exactly = 3) { operations.receive(String::class.java, any(), any()) }
+        registrar.stop()
+        registrar.isRunning shouldBe false
+    }
+
+    @Test
+    fun `unannotated beans are ignored`() = runTest {
+        val registrar = SqsListenerAnnotationRegistrar(template, resolver, scope = this)
+        val bean = PlainBean()
+
+        registrar.postProcessAfterInitialization(bean, "plain") shouldBeSameInstanceAs bean
+        registrar.start()
+        registrar.isRunning shouldBe true
+        advanceUntilIdle()
+
+        confirmVerified(template, resolver)
+        registrar.stop()
+    }
+
+    @Test
+    fun `postProcessing the same bean twice does not duplicate endpoints`() = runTest {
+        stubQueue("a", urlA)
+        stubQueue("b", urlB)
+        stubQueue("c", urlC)
+        val registrar = SqsListenerAnnotationRegistrar(template, resolver, scope = this)
+        val bean = AnnotatedBean()
+
+        registrar.postProcessAfterInitialization(bean, "listener")
+        registrar.postProcessAfterInitialization(bean, "listener")
+        registrar.start()
+        advanceUntilIdle()
+
+        coVerify(exactly = 1) { resolver.resolve("a") }
+        coVerify(exactly = 1) { resolver.resolve("b") }
+        coVerify(exactly = 1) { resolver.resolve("c") }
+        registrar.stop()
+    }
+
+    @Test
+    fun `missing queue fails registrar startup`() = runTest {
+        stubQueue("a", urlA)
+        stubQueue("c", urlC)
+        coEvery { resolver.resolve("b") } throws SqsQueueNotFoundException("b")
+        val registrar = SqsListenerAnnotationRegistrar(template, resolver, scope = this)
+        registrar.postProcessAfterInitialization(AnnotatedBean(), "listener")
+
+        shouldThrow<SqsQueueNotFoundException> { registrar.start() }
+
+        registrar.isRunning shouldBe false
+        registrar.stop()
+    }
+
+    @Test
+    fun `stop is idempotent and cancels polling`() = runTest {
+        stubQueue("a", urlA)
+        stubQueue("b", urlB)
+        stubQueue("c", urlC)
+        val registrar = SqsListenerAnnotationRegistrar(template, resolver, scope = this)
+        registrar.postProcessAfterInitialization(AnnotatedBean(), "listener")
+
+        registrar.start()
+        advanceUntilIdle()
+        registrar.stop()
+        registrar.stop()
+        advanceUntilIdle()
+
+        registrar.isRunning shouldBe false
+        coVerify(exactly = 3) { operations.receive(String::class.java, any(), any()) }
+    }
+
+    @Test
+    fun `isAutoStartup follows config`() = runTest {
+        val registrar = SqsListenerAnnotationRegistrar(
+            template,
+            resolver,
+            SqsListenerConfig(autoStartup = false),
+            scope = this
+        )
+        registrar.isAutoStartup shouldBe false
+        registrar.isRunning shouldBe false
+    }
+
+    @Test
+    fun `internal annotated listener methods are discovered and invoked`() = runTest {
+        val messageA = message("internal-plain")
+        val messageB = message("internal-suspend")
+        val opsA = stubQueueOnce("a", urlA, messageA)
+        val opsB = stubQueueOnce("b", urlB, messageB)
+        val registrar = SqsListenerAnnotationRegistrar(template, resolver, scope = this)
+        val bean = InternalVisibilityBean()
+
+        registrar.postProcessAfterInitialization(bean, "internalVisibility")
+        registrar.start()
+        advanceUntilIdle()
+
+        bean.internalCalls shouldBe listOf("internal-plain")
+        bean.internalSuspendCalls shouldBe listOf(messageB)
+        coVerify(exactly = 1) { opsA.acknowledge(messageA) }
+        coVerify(exactly = 1) { opsB.acknowledge(messageB) }
+        registrar.stop()
+    }
+
+    @Test
+    fun `private annotated listener methods are discovered and invoked`() = runTest {
+        val messageA = message("private-plain")
+        val messageB = message("private-suspend")
+        val opsA = stubQueueOnce("a", urlA, messageA)
+        val opsB = stubQueueOnce("b", urlB, messageB)
+        val registrar = SqsListenerAnnotationRegistrar(template, resolver, scope = this)
+        val bean = PrivateVisibilityBean()
+
+        registrar.postProcessAfterInitialization(bean, "privateVisibility")
+        registrar.start()
+        advanceUntilIdle()
+
+        bean.privateCalls shouldBe listOf("private-plain")
+        bean.privateSuspendCalls shouldBe listOf(messageB)
+        coVerify(exactly = 1) { opsA.acknowledge(messageA) }
+        coVerify(exactly = 1) { opsB.acknowledge(messageB) }
+        registrar.stop()
+    }
+
+    @Test
+    fun `CGLIB proxied listener beans are discovered and invoked on the target`() = runTest {
+        val message = message("cglib")
+        val ops = stubQueueOnce("a", urlA, message)
+        val registrar = SqsListenerAnnotationRegistrar(template, resolver, scope = this)
+        val target = CglibListenerBean()
+        val proxy = ProxyFactory(target).apply { isProxyTargetClass = true }.proxy
+
+        registrar.postProcessAfterInitialization(proxy, "cglibProxied") shouldBeSameInstanceAs proxy
+        registrar.start()
+        advanceUntilIdle()
+
+        target.calls shouldBe listOf("cglib")
+        coVerify(exactly = 1) { ops.acknowledge(message) }
+        registrar.stop()
+    }
+
+    @Test
+    fun `CGLIB proxied internal listener methods are discovered and invoked`() = runTest {
+        val message = message("cglib-internal")
+        val ops = stubQueueOnce("a", urlA, message)
+        val registrar = SqsListenerAnnotationRegistrar(template, resolver, scope = this)
+        val target = InternalCglibListenerBean()
+        val proxy = ProxyFactory(target).apply { isProxyTargetClass = true }.proxy
+
+        registrar.postProcessAfterInitialization(proxy, "internalCglibProxied")
+        registrar.start()
+        advanceUntilIdle()
+
+        target.calls shouldBe listOf("cglib-internal")
+        coVerify(exactly = 1) { ops.acknowledge(message) }
+        registrar.stop()
+    }
+
+    @Test
+    fun `JDK proxied listener beans with a singleton target are discovered and invoked`() = runTest {
+        val message = message("jdk")
+        val ops = stubQueueOnce("a", urlA, message)
+        val registrar = SqsListenerAnnotationRegistrar(template, resolver, scope = this)
+        val target = JdkListenerBean()
+        val proxy = ProxyFactory().apply {
+            setTarget(target)
+            addInterface(JdkListenerContract::class.java)
+        }.proxy
+
+        registrar.postProcessAfterInitialization(proxy, "jdkProxied")
+        registrar.start()
+        advanceUntilIdle()
+
+        target.calls shouldBe listOf("jdk")
+        coVerify(exactly = 1) { ops.acknowledge(message) }
+        registrar.stop()
+    }
+
+    @Test
+    fun `postProcessing the same proxy twice does not duplicate endpoints`() = runTest {
+        val message = message("cglib-once")
+        val ops = stubQueueOnce("a", urlA, message)
+        val registrar = SqsListenerAnnotationRegistrar(template, resolver, scope = this)
+        val target = CglibListenerBean()
+        val proxy = ProxyFactory(target).apply { isProxyTargetClass = true }.proxy
+
+        registrar.postProcessAfterInitialization(proxy, "cglibProxied")
+        registrar.postProcessAfterInitialization(proxy, "cglibProxied")
+        registrar.start()
+        advanceUntilIdle()
+
+        target.calls shouldBe listOf("cglib-once")
+        coVerify(exactly = 1) { resolver.resolve("a") }
+        coVerify(exactly = 1) { ops.acknowledge(message) }
+        registrar.stop()
+    }
+
+    private fun message(payload: String) = SqsMessage(
+        payload = payload,
+        messageId = "m-$payload",
+        receiptHandle = "rh-$payload",
+        body = payload,
+        attributes = emptyMap(),
+        messageAttributes = emptyMap()
+    )
+
+    // Deliver one message on the queue, then park so the poll loop does not busy-spin.
+    private fun stubQueueOnce(name: String, url: String, message: SqsMessage<String>): SqsDestinationOperations {
+        val queueOperations = mockk<SqsDestinationOperations>()
+        coEvery { resolver.resolve(name) } returns url
+        every { template.destination(url) } returns queueOperations
+        var delivered = false
+        coEvery { queueOperations.receive(String::class.java, any(), any()) } coAnswers {
+            if (delivered) {
+                awaitCancellation()
+            } else {
+                delivered = true
+                message
+            }
+        }
+        coEvery { queueOperations.acknowledge(message) } returns DeleteMessageResponse { }
+        return queueOperations
+    }
+
+    private class AnnotatedBean {
+        @SqsListener("a")
+        fun first(payload: String) {
+        }
+
+        @SqsListener("b", "c")
+        suspend fun second(message: SqsMessage<String>) {
+        }
+
+        fun notAListener(payload: String) {
+        }
+    }
+
+    private class PlainBean {
+        fun handle(payload: String) {
+        }
+    }
+
+    // Not private so internal members compile to mangled public bytecode (foo$module).
+    class InternalVisibilityBean {
+        val internalCalls = mutableListOf<String>()
+        val internalSuspendCalls = mutableListOf<SqsMessage<String>>()
+
+        @SqsListener("a")
+        internal fun handleInternal(payload: String) {
+            internalCalls += payload
+        }
+
+        @SqsListener("b")
+        internal suspend fun handleInternalSuspend(message: SqsMessage<String>) {
+            internalSuspendCalls += message
+        }
+    }
+
+    class PrivateVisibilityBean {
+        val privateCalls = mutableListOf<String>()
+        val privateSuspendCalls = mutableListOf<SqsMessage<String>>()
+
+        @SqsListener("a")
+        private fun handlePrivate(payload: String) {
+            privateCalls += payload
+        }
+
+        @SqsListener("b")
+        private suspend fun handlePrivateSuspend(message: SqsMessage<String>) {
+            privateSuspendCalls += message
+        }
+    }
+
+    // Open class/method so CGLIB can subclass it; the override carries no annotations.
+    open class CglibListenerBean {
+        val calls = mutableListOf<String>()
+
+        @SqsListener("a")
+        open fun handle(payload: String) {
+            calls += payload
+        }
+    }
+
+    open class InternalCglibListenerBean {
+        val calls = mutableListOf<String>()
+
+        @SqsListener("a")
+        internal open fun handleInternal(payload: String) {
+            calls += payload
+        }
+    }
+
+    interface JdkListenerContract {
+        fun handle(payload: String)
+    }
+
+    // The annotation lives on the implementation, not on the proxied interface.
+    class JdkListenerBean : JdkListenerContract {
+        val calls = mutableListOf<String>()
+
+        @SqsListener("a")
+        override fun handle(payload: String) {
+            calls += payload
+        }
+    }
+}
diff --git a/sqs/src/test/kotlin/io/github/hantsy/awskotlin/sqs/listener/SqsListenerContainerTest.kt b/sqs/src/test/kotlin/io/github/hantsy/awskotlin/sqs/listener/SqsListenerContainerTest.kt
new file mode 100644
index 0000000..b8ddd40
--- /dev/null
+++ b/sqs/src/test/kotlin/io/github/hantsy/awskotlin/sqs/listener/SqsListenerContainerTest.kt
@@ -0,0 +1,451 @@
+package io.github.hantsy.awskotlin.sqs.listener
+
+import aws.sdk.kotlin.services.sqs.SqsClient
+import aws.sdk.kotlin.services.sqs.model.ChangeMessageVisibilityResponse
+import aws.sdk.kotlin.services.sqs.model.DeleteMessageResponse
+import io.github.hantsy.awskotlin.sqs.SqsAcknowledgementMode
+import io.github.hantsy.awskotlin.sqs.SqsDestinationOperations
+import io.github.hantsy.awskotlin.sqs.SqsMessage
+import io.github.hantsy.awskotlin.sqs.SqsOperationException
+import io.github.hantsy.awskotlin.sqs.SqsQueueNotFoundException
+import io.github.hantsy.awskotlin.sqs.SqsQueueResolver
+import io.github.hantsy.awskotlin.sqs.SqsTemplate
+import io.kotest.assertions.throwables.shouldThrow
+import io.kotest.matchers.collections.shouldContain
+import io.kotest.matchers.shouldBe
+import io.mockk.coEvery
+import io.mockk.coVerify
+import io.mockk.confirmVerified
+import io.mockk.every
+import io.mockk.mockk
+import kotlinx.coroutines.CompletableDeferred
+import kotlinx.coroutines.ExperimentalCoroutinesApi
+import kotlinx.coroutines.awaitCancellation
+import kotlinx.coroutines.isActive
+import kotlinx.coroutines.test.advanceTimeBy
+import kotlinx.coroutines.test.advanceUntilIdle
+import kotlinx.coroutines.test.runCurrent
+import kotlinx.coroutines.test.runTest
+import org.junit.jupiter.api.Test
+import java.lang.annotation.Retention
+import java.lang.annotation.RetentionPolicy
+import kotlin.concurrent.thread
+import kotlin.reflect.KFunction
+import kotlin.reflect.full.findAnnotation
+import kotlin.reflect.full.memberFunctions
+import kotlin.time.Duration.Companion.seconds
+
+@OptIn(ExperimentalCoroutinesApi::class)
+class SqsListenerContainerTest {
+    private val template = mockk<SqsTemplate>()
+    private val operations = mockk<SqsDestinationOperations>()
+    private val resolver = mockk<SqsQueueResolver>()
+    private val queueUrl = "https://sqs.us-east-1.amazonaws.com/123456789012/orders"
+    private val bean = ListenerBean()
+    private val message = SqsMessage(
+        payload = "p",
+        messageId = "m-1",
+        receiptHandle = "rh-1",
+        body = "p",
+        attributes = mapOf("SentTimestamp" to "1700000000000"),
+        messageAttributes = emptyMap()
+    )
+
+    private fun stubQueue() {
+        coEvery { resolver.resolve("orders") } returns queueUrl
+        every { template.destination(queueUrl) } returns operations
+    }
+
+    // Deliver one message, then suspend forever so the poll loop parks without busy-spinning.
+    private fun stubReceiveOnce() {
+        var delivered = false
+        coEvery {
+            operations.receive(String::class.java, any(), SqsAcknowledgementMode.MANUAL)
+        } coAnswers {
+            if (delivered) {
+                awaitCancellation()
+            } else {
+                delivered = true
+                message
+            }
+        }
+    }
+
+    private fun endpoint(method: KFunction<*>, queues: List<String> = listOf("orders")) =
+        SqsListenerEndpoint(bean, method, queues)
+
+    @Test
+    fun `SqsListener annotation is runtime retained and captures queue names`() {
+        val annotation = ListenerBean::plain.findAnnotation<SqsListener>()
+        annotation!!.queues.toList() shouldBe listOf("orders")
+        SqsListener::class.findAnnotation<Target>()!!.allowedTargets.toList() shouldContain AnnotationTarget.FUNCTION
+        SqsListener::class.java.getAnnotation(Retention::class.java).value shouldBe RetentionPolicy.RUNTIME
+    }
+
+    @Test
+    fun `endpoint with empty queues is rejected`() {
+        shouldThrow<IllegalArgumentException> {
+            SqsListenerContainer(endpoint(ListenerBean::plain, emptyList()), template, resolver)
+        }
+    }
+
+    @Test
+    fun `method without payload parameter is rejected`() {
+        shouldThrow<IllegalArgumentException> {
+            SqsListenerContainer(endpoint(ListenerBean::ackOnly), template, resolver)
+        }
+    }
+
+    @Test
+    fun `method with two payload parameters is rejected`() {
+        shouldThrow<IllegalArgumentException> {
+            SqsListenerContainer(endpoint(ListenerBean::twoPayloads), template, resolver)
+        }
+    }
+
+    @Test
+    fun `method with unsupported generic parameter is rejected`() {
+        val generic = ListenerBean::class.memberFunctions.first { it.name == "generic" }
+        shouldThrow<IllegalArgumentException> {
+            SqsListenerContainer(endpoint(generic), template, resolver)
+        }
+    }
+
+    @Test
+    fun `SqsMessage star projection is rejected`() {
+        shouldThrow<IllegalArgumentException> {
+            SqsListenerContainer(endpoint(ListenerBean::star), template, resolver)
+        }
+    }
+
+    @Test
+    fun `method with two Acknowledge parameters is rejected`() {
+        shouldThrow<IllegalArgumentException> {
+            SqsListenerContainer(endpoint(ListenerBean::twoAcks), template, resolver)
+        }
+    }
+
+    @Test
+    fun `start resolves every queue before polling`() = runTest {
+        val urlA = "https://sqs.us-east-1.amazonaws.com/123456789012/a"
+        val urlB = "https://sqs.us-east-1.amazonaws.com/123456789012/b"
+        val opsA = mockk<SqsDestinationOperations>()
+        val opsB = mockk<SqsDestinationOperations>()
+        coEvery { resolver.resolve("a") } returns urlA
+        coEvery { resolver.resolve("b") } returns urlB
+        every { template.destination(urlA) } returns opsA
+        every { template.destination(urlB) } returns opsB
+        coEvery { opsA.receive(String::class.java, any(), any()) } coAnswers { awaitCancellation() }
+        coEvery { opsB.receive(String::class.java, any(), any()) } coAnswers { awaitCancellation() }
+        val container = SqsListenerContainer(
+            endpoint(ListenerBean::plain, listOf("a", "b")),
+            template,
+            resolver,
+            scope = this
+        )
+
+        container.start()
+        container.isRunning shouldBe true
+        advanceUntilIdle()
+
+        coVerify(exactly = 1) { resolver.resolve("a") }
+        coVerify(exactly = 1) { resolver.resolve("b") }
+        coVerify(exactly = 1) { opsA.receive(String::class.java, 10.seconds, SqsAcknowledgementMode.MANUAL) }
+        coVerify(exactly = 1) { opsB.receive(String::class.java, 10.seconds, SqsAcknowledgementMode.MANUAL) }
+        container.stop()
+    }
+
+    @Test
+    fun `missing queue fails startup without polling`() = runTest {
+        coEvery { resolver.resolve("missing") } throws SqsQueueNotFoundException("missing")
+        val container =
+            SqsListenerContainer(endpoint(ListenerBean::plain, listOf("missing")), template, resolver, scope = this)
+
+        shouldThrow<SqsQueueNotFoundException> { container.start() }
+
+        container.isRunning shouldBe false
+        coVerify(exactly = 0) { template.destination(any()) }
+    }
+
+    @Test
+    fun `FIFO queue fails fast without SDK calls`() = runTest {
+        val client = mockk<SqsClient>()
+        val container = SqsListenerContainer(
+            endpoint(ListenerBean::plain, listOf("orders.fifo")),
+            template,
+            SqsQueueResolver(client),
+            scope = this
+        )
+
+        shouldThrow<SqsOperationException> { container.start() }
+
+        container.isRunning shouldBe false
+        confirmVerified(client)
+    }
+
+    @Test
+    fun `plain payload listener is invoked and message auto-deleted in MANUAL mode`() = runTest {
+        stubQueue()
+        stubReceiveOnce()
+        coEvery { operations.acknowledge(message) } returns DeleteMessageResponse { }
+        val container = SqsListenerContainer(endpoint(ListenerBean::plain), template, resolver, scope = this)
+
+        container.start()
+        advanceUntilIdle()
+
+        bean.plainCalls shouldBe listOf("p")
+        coVerify(exactly = 2) { operations.receive(String::class.java, 10.seconds, SqsAcknowledgementMode.MANUAL) }
+        coVerify(exactly = 1) { operations.acknowledge(message) }
+        container.stop()
+    }
+
+    @Test
+    fun `SqsMessage listener receives full message and auto-deletes`() = runTest {
+        stubQueue()
+        stubReceiveOnce()
+        coEvery { operations.acknowledge(message) } returns DeleteMessageResponse { }
+        val container = SqsListenerContainer(endpoint(ListenerBean::full), template, resolver, scope = this)
+
+        container.start()
+        advanceUntilIdle()
+
+        bean.messageCalls shouldBe listOf(message)
+        coVerify(exactly = 1) { operations.acknowledge(message) }
+        container.stop()
+    }
+
+    @Test
+    fun `Acknowledge accept deletes the message`() = runTest {
+        stubQueue()
+        stubReceiveOnce()
+        coEvery { operations.acknowledge(message) } returns DeleteMessageResponse { }
+        bean.manualAction = { it.accept() }
+        val container = SqsListenerContainer(endpoint(ListenerBean::manual), template, resolver, scope = this)
+
+        container.start()
+        advanceUntilIdle()
+
+        bean.messageCalls shouldBe listOf(message)
+        coVerify(exactly = 1) { operations.acknowledge(message) }
+        coVerify(exactly = 0) { operations.reject(any()) }
+        container.stop()
+    }
+
+    @Test
+    fun `Acknowledge reject makes the message visible again without deleting`() = runTest {
+        stubQueue()
+        stubReceiveOnce()
+        coEvery { operations.reject(message) } returns ChangeMessageVisibilityResponse { }
+        bean.manualAction = { it.reject() }
+        val container = SqsListenerContainer(endpoint(ListenerBean::manual), template, resolver, scope = this)
+
+        container.start()
+        advanceUntilIdle()
+
+        coVerify(exactly = 1) { operations.reject(message) }
+        coVerify(exactly = 0) { operations.acknowledge(any()) }
+        container.stop()
+    }
+
+    @Test
+    fun `listener exception does not delete and poll loop survives`() = runTest {
+        stubQueue()
+        stubReceiveOnce()
+        val container = SqsListenerContainer(endpoint(ListenerBean::boom), template, resolver, scope = this)
+
+        container.start()
+        advanceUntilIdle()
+
+        coVerify(exactly = 0) { operations.acknowledge(any()) }
+        coVerify(exactly = 2) { operations.receive(String::class.java, any(), SqsAcknowledgementMode.MANUAL) }
+        container.isRunning shouldBe true
+        container.stop()
+    }
+
+    @Test
+    fun `unused Acknowledge parameter leaves the message undeleted`() = runTest {
+        stubQueue()
+        stubReceiveOnce()
+        bean.manualAction = { }
+        val container = SqsListenerContainer(endpoint(ListenerBean::manual), template, resolver, scope = this)
+
+        container.start()
+        advanceUntilIdle()
+
+        bean.messageCalls shouldBe listOf(message)
+        coVerify(exactly = 0) { operations.acknowledge(any()) }
+        coVerify(exactly = 0) { operations.reject(any()) }
+        container.stop()
+    }
+
+    @Test
+    fun `empty receive continues polling without processing`() = runTest {
+        stubQueue()
+        var delivered = false
+        coEvery {
+            operations.receive(String::class.java, any(), SqsAcknowledgementMode.MANUAL)
+        } coAnswers {
+            if (delivered) {
+                awaitCancellation()
+            } else {
+                delivered = true
+                null
+            }
+        }
+        val container = SqsListenerContainer(endpoint(ListenerBean::plain), template, resolver, scope = this)
+
+        container.start()
+        advanceUntilIdle()
+
+        bean.plainCalls shouldBe emptyList()
+        coVerify(exactly = 2) { operations.receive(String::class.java, any(), SqsAcknowledgementMode.MANUAL) }
+        coVerify(exactly = 0) { operations.acknowledge(any()) }
+        container.stop()
+    }
+
+    @Test
+    fun `receive errors back off exponentially and reset on success`() = runTest {
+        stubQueue()
+        var calls = 0
+        coEvery {
+            operations.receive(String::class.java, any(), SqsAcknowledgementMode.MANUAL)
+        } coAnswers {
+            calls++
+            if (calls <= 2) throw SqsOperationException("down") else awaitCancellation()
+        }
+        val container = SqsListenerContainer(endpoint(ListenerBean::plain), template, resolver, scope = this)
+
+        container.start()
+        runCurrent()
+        coVerify(exactly = 1) { operations.receive(String::class.java, any(), SqsAcknowledgementMode.MANUAL) }
+
+        advanceTimeBy(1.seconds)
+        runCurrent()
+        coVerify(exactly = 2) { operations.receive(String::class.java, any(), SqsAcknowledgementMode.MANUAL) }
+
+        advanceTimeBy(1.seconds)
+        runCurrent()
+        coVerify(exactly = 2) { operations.receive(String::class.java, any(), SqsAcknowledgementMode.MANUAL) }
+
+        advanceTimeBy(1.seconds)
+        runCurrent()
+        coVerify(exactly = 3) { operations.receive(String::class.java, any(), SqsAcknowledgementMode.MANUAL) }
+        container.stop()
+    }
+
+    @Test
+    fun `stop is idempotent, cancels polling and keeps the external scope alive`() = runTest {
+        stubQueue()
+        stubReceiveOnce()
+        coEvery { operations.acknowledge(message) } returns DeleteMessageResponse { }
+        val container = SqsListenerContainer(endpoint(ListenerBean::plain), template, resolver, scope = this)
+
+        container.start()
+        advanceUntilIdle()
+        container.isRunning shouldBe true
+
+        container.stop()
+        container.stop()
+        container.isRunning shouldBe false
+        advanceUntilIdle()
+
+        coVerify(exactly = 2) { operations.receive(String::class.java, any(), SqsAcknowledgementMode.MANUAL) }
+        isActive shouldBe true
+    }
+
+    @Test
+    fun `stop waits for in-flight processing before cancelling`() = runTest {
+        stubQueue()
+        stubReceiveOnce()
+        coEvery { operations.acknowledge(message) } returns DeleteMessageResponse { }
+        val gate = CompletableDeferred<Unit>()
+        bean.gate = { gate.await() }
+        val container = SqsListenerContainer(
+            endpoint(ListenerBean::gated),
+            template,
+            resolver,
+            SqsListenerConfig(shutdownTimeout = 10.seconds),
+            scope = this
+        )
+
+        container.start()
+        advanceUntilIdle()
+        bean.gatedCalls shouldBe emptyList()
+
+        val stopper = thread { container.stop() }
+        gate.complete(Unit)
+        advanceUntilIdle()
+        stopper.join(10_000)
+
+        stopper.isAlive shouldBe false
+        bean.gatedCalls shouldBe listOf("p")
+        coVerify(exactly = 1) { operations.acknowledge(message) }
+        container.isRunning shouldBe false
+    }
+
+    @Test
+    fun `isAutoStartup follows config`() = runTest {
+        val container = SqsListenerContainer(
+            endpoint(ListenerBean::plain),
+            template,
+            resolver,
+            SqsListenerConfig(autoStartup = false),
+            scope = this
+        )
+        container.isAutoStartup shouldBe false
+        container.isRunning shouldBe false
+    }
+
+    private class ListenerBean {
+        val plainCalls = mutableListOf<String>()
+        val messageCalls = mutableListOf<SqsMessage<String>>()
+        val gatedCalls = mutableListOf<String>()
+        var manualAction: suspend (Acknowledge) -> Unit = { }
+        var gate: suspend () -> Unit = { }
+
+        @SqsListener("orders")
+        fun plain(payload: String) {
+            plainCalls += payload
+        }
+
+        @SqsListener("orders")
+        suspend fun full(message: SqsMessage<String>) {
+            messageCalls += message
+        }
+
+        @SqsListener("orders")
+        suspend fun manual(message: SqsMessage<String>, ack: Acknowledge) {
+            messageCalls += message
+            manualAction(ack)
+        }
+
+        @SqsListener("orders")
+        fun boom(payload: String): Unit = throw IllegalStateException("boom")
+
+        @SqsListener("orders")
+        suspend fun gated(payload: String) {
+            gate()
+            gatedCalls += payload
+        }
+
+        @SqsListener("orders")
+        fun ackOnly(ack: Acknowledge) {
+        }
+
+        @SqsListener("orders")
+        fun twoPayloads(a: String, b: String) {
+        }
+
+        @SqsListener("orders")
+        fun <T> generic(payload: T) {
+        }
+
+        @SqsListener("orders")
+        fun star(message: SqsMessage<*>) {
+        }
+
+        @SqsListener("orders")
+        fun twoAcks(payload: String, a: Acknowledge, b: Acknowledge) {
+        }
+    }
+}
diff --git a/starters/s3-starter/build.gradle.kts b/starters/s3-starter/build.gradle.kts
new file mode 100644
index 0000000..2768411
--- /dev/null
+++ b/starters/s3-starter/build.gradle.kts
@@ -0,0 +1,56 @@
+plugins {
+    alias(libs.plugins.kotlinJvm)
+    alias(libs.plugins.dependencyManagement)
+    alias(libs.plugins.kotlinter)
+    `java-library`
+}
+
+group = "io.github.hantsy"
+version = "0.1.0-SNAPSHOT"
+
+base { archivesName.set("awskotlin-s3-spring-boot-starter") }
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
+    api(project(":s3"))
+    api(project(":core"))
+    implementation(libs.springBootAutoconfigure)
+    implementation(libs.kotlinStdlib)
+    testImplementation(libs.springBootStarterTest)
+    testImplementation(libs.bundles.kotest)
+    testImplementation(libs.bundles.mockk)
+    testImplementation(libs.bundles.kotlintest)
+    testRuntimeOnly(libs.junitPlatformLauncher)
+}
+
+kotlin { jvmToolchain(libs.versions.java.get().toInt()) }
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
diff --git a/starters/s3-starter/src/main/kotlin/io/github/hantsy/awskotlin/s3/autoconfigure/S3AutoConfiguration.kt b/starters/s3-starter/src/main/kotlin/io/github/hantsy/awskotlin/s3/autoconfigure/S3AutoConfiguration.kt
new file mode 100644
index 0000000..9420f41
--- /dev/null
+++ b/starters/s3-starter/src/main/kotlin/io/github/hantsy/awskotlin/s3/autoconfigure/S3AutoConfiguration.kt
@@ -0,0 +1,54 @@
+package io.github.hantsy.awskotlin.s3.autoconfigure
+
+import aws.sdk.kotlin.services.s3.S3Client
+import aws.smithy.kotlin.runtime.auth.awscredentials.CredentialsProvider
+import aws.smithy.kotlin.runtime.net.url.Url
+import io.github.hantsy.awskotlin.core.AwsKotlinProperties
+import io.github.hantsy.awskotlin.core.autoconfigure.AwsKotlinAutoConfiguration
+import io.github.hantsy.awskotlin.s3.S3Template
+import org.springframework.boot.autoconfigure.AutoConfiguration
+import org.springframework.boot.autoconfigure.condition.ConditionalOnClass
+import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
+import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
+import org.springframework.boot.context.properties.EnableConfigurationProperties
+import org.springframework.context.annotation.Bean
+
+/**
+ * Auto-configuration for the AWS SDK for Kotlin S3 client and [S3Template].
+ *
+ * Disabled by either `awskotlin.enabled=false` or `awskotlin.s3.enabled=false`
+ * (both checked by the single property condition below). User-provided
+ * [S3Client]/[S3Template] beans always win.
+ */
+@AutoConfiguration(after = [AwsKotlinAutoConfiguration::class])
+@ConditionalOnClass(S3Client::class)
+@ConditionalOnProperty(
+    prefix = "awskotlin",
+    name = ["enabled", "s3.enabled"],
+    havingValue = "true",
+    matchIfMissing = true,
+)
+@EnableConfigurationProperties(S3Properties::class)
+class S3AutoConfiguration {
+    @Bean
+    @ConditionalOnMissingBean
+    fun s3Client(
+        properties: AwsKotlinProperties,
+        s3Properties: S3Properties,
+        credentialsProvider: CredentialsProvider,
+    ): S3Client {
+        val endpoint = s3Properties.endpoint ?: properties.endpoint
+        return S3Client {
+            region = s3Properties.region ?: properties.region
+            this.credentialsProvider = credentialsProvider
+            endpoint?.let { endpointUrl = Url.parse(it) }
+            // Path-style addressing is the sensible default for endpoint overrides
+            // (LocalStack, MinIO, ...) unless explicitly configured otherwise.
+            forcePathStyle = s3Properties.pathStyleAccessEnabled ?: (endpoint != null)
+        }
+    }
+
+    @Bean
+    @ConditionalOnMissingBean
+    fun s3Template(client: S3Client): S3Template = S3Template(client)
+}
diff --git a/starters/s3-starter/src/main/kotlin/io/github/hantsy/awskotlin/s3/autoconfigure/S3Properties.kt b/starters/s3-starter/src/main/kotlin/io/github/hantsy/awskotlin/s3/autoconfigure/S3Properties.kt
new file mode 100644
index 0000000..7d81b89
--- /dev/null
+++ b/starters/s3-starter/src/main/kotlin/io/github/hantsy/awskotlin/s3/autoconfigure/S3Properties.kt
@@ -0,0 +1,17 @@
+package io.github.hantsy.awskotlin.s3.autoconfigure
+
+import org.springframework.boot.context.properties.ConfigurationProperties
+
+/**
+ * Configuration properties for the S3 module.
+ *
+ * Unset values fall back to the shared `awskotlin.*` properties and then to the
+ * SDK default chains.
+ */
+@ConfigurationProperties(prefix = "awskotlin.s3")
+data class S3Properties(
+    val enabled: Boolean = true,
+    val endpoint: String? = null,
+    val region: String? = null,
+    val pathStyleAccessEnabled: Boolean? = null,
+)
diff --git a/starters/s3-starter/src/main/resources/META-INF/spring-configuration-metadata.json b/starters/s3-starter/src/main/resources/META-INF/spring-configuration-metadata.json
new file mode 100644
index 0000000..586bfd9
--- /dev/null
+++ b/starters/s3-starter/src/main/resources/META-INF/spring-configuration-metadata.json
@@ -0,0 +1,36 @@
+{
+  "groups": [
+    {
+      "name": "awskotlin.s3",
+      "type": "io.github.hantsy.awskotlin.s3.autoconfigure.S3Properties",
+      "sourceType": "io.github.hantsy.awskotlin.s3.autoconfigure.S3Properties"
+    }
+  ],
+  "properties": [
+    {
+      "name": "awskotlin.s3.enabled",
+      "type": "java.lang.Boolean",
+      "description": "Whether S3 auto-configuration is enabled.",
+      "sourceType": "io.github.hantsy.awskotlin.s3.autoconfigure.S3Properties",
+      "defaultValue": true
+    },
+    {
+      "name": "awskotlin.s3.endpoint",
+      "type": "java.lang.String",
+      "description": "Override the S3 endpoint URL (for example, a LocalStack endpoint). Falls back to awskotlin.endpoint.",
+      "sourceType": "io.github.hantsy.awskotlin.s3.autoconfigure.S3Properties"
+    },
+    {
+      "name": "awskotlin.s3.region",
+      "type": "java.lang.String",
+      "description": "Override the S3 region. Falls back to awskotlin.region, then the SDK default region chain.",
+      "sourceType": "io.github.hantsy.awskotlin.s3.autoconfigure.S3Properties"
+    },
+    {
+      "name": "awskotlin.s3.path-style-access-enabled",
+      "type": "java.lang.Boolean",
+      "description": "Force path-style bucket addressing. Defaults to true when an endpoint override is configured, false otherwise.",
+      "sourceType": "io.github.hantsy.awskotlin.s3.autoconfigure.S3Properties"
+    }
+  ]
+}
\ No newline at end of file
diff --git a/starters/s3-starter/src/main/resources/META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports b/starters/s3-starter/src/main/resources/META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports
new file mode 100644
index 0000000..e45ad33
--- /dev/null
+++ b/starters/s3-starter/src/main/resources/META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports
@@ -0,0 +1 @@
+io.github.hantsy.awskotlin.s3.autoconfigure.S3AutoConfiguration
\ No newline at end of file
diff --git a/starters/s3-starter/src/test/kotlin/io/github/hantsy/awskotlin/s3/autoconfigure/S3AutoConfigurationTest.kt b/starters/s3-starter/src/test/kotlin/io/github/hantsy/awskotlin/s3/autoconfigure/S3AutoConfigurationTest.kt
new file mode 100644
index 0000000..d6761d7
--- /dev/null
+++ b/starters/s3-starter/src/test/kotlin/io/github/hantsy/awskotlin/s3/autoconfigure/S3AutoConfigurationTest.kt
@@ -0,0 +1,131 @@
+package io.github.hantsy.awskotlin.s3.autoconfigure
+
+import aws.sdk.kotlin.runtime.auth.credentials.StaticCredentialsProvider
+import aws.sdk.kotlin.services.s3.S3Client
+import io.github.hantsy.awskotlin.core.autoconfigure.AwsKotlinAutoConfiguration
+import io.github.hantsy.awskotlin.s3.S3Template
+import io.kotest.matchers.shouldBe
+import io.kotest.matchers.types.shouldBeInstanceOf
+import io.kotest.matchers.types.shouldBeSameInstanceAs
+import io.mockk.mockk
+import org.junit.jupiter.api.Test
+import org.springframework.boot.autoconfigure.AutoConfigurations
+import org.springframework.boot.test.context.runner.ApplicationContextRunner
+import org.springframework.context.annotation.Bean
+import org.springframework.context.annotation.Configuration
+
+class S3AutoConfigurationTest {
+    private val runner =
+        ApplicationContextRunner().withConfiguration(
+            AutoConfigurations.of(AwsKotlinAutoConfiguration::class.java, S3AutoConfiguration::class.java),
+        )
+
+    @Test
+    fun `default S3Client and S3Template beans with static credentials and region`() {
+        runner.withPropertyValues(
+            "awskotlin.region=us-east-1",
+            "awskotlin.access-key-id=test-access-key",
+            "awskotlin.secret-access-key=test-secret-key",
+        ).run { context ->
+            context.getBeanNamesForType(S3Client::class.java).size shouldBe 1
+            context.getBeanNamesForType(S3Template::class.java).size shouldBe 1
+            val client = context.getBean(S3Client::class.java)
+            client.config.region shouldBe "us-east-1"
+            client.config.credentialsProvider.shouldBeInstanceOf<StaticCredentialsProvider>()
+            client.config.forcePathStyle shouldBe false
+        }
+    }
+
+    @Test
+    fun `s3 region overrides the global region`() {
+        runner.withPropertyValues(
+            "awskotlin.region=us-east-1",
+            "awskotlin.s3.region=eu-west-1",
+            "awskotlin.access-key-id=test-access-key",
+            "awskotlin.secret-access-key=test-secret-key",
+        ).run { context ->
+            context.getBean(S3Client::class.java).config.region shouldBe "eu-west-1"
+        }
+    }
+
+    @Test
+    fun `endpoint override enables forcePathStyle by default`() {
+        runner.withPropertyValues(
+            "awskotlin.region=us-east-1",
+            "awskotlin.s3.endpoint=http://localhost:4566",
+            "awskotlin.access-key-id=test-access-key",
+            "awskotlin.secret-access-key=test-secret-key",
+        ).run { context ->
+            val config = context.getBean(S3Client::class.java).config
+            config.forcePathStyle shouldBe true
+            config.endpointUrl?.toString() shouldBe "http://localhost:4566"
+        }
+    }
+
+    @Test
+    fun `global endpoint override is used when s3 endpoint is unset`() {
+        runner.withPropertyValues(
+            "awskotlin.region=us-east-1",
+            "awskotlin.endpoint=http://localhost:4566",
+            "awskotlin.access-key-id=test-access-key",
+            "awskotlin.secret-access-key=test-secret-key",
+        ).run { context ->
+            val config = context.getBean(S3Client::class.java).config
+            config.forcePathStyle shouldBe true
+            config.endpointUrl?.toString() shouldBe "http://localhost:4566"
+        }
+    }
+
+    @Test
+    fun `explicit path-style false is respected even with an endpoint override`() {
+        runner.withPropertyValues(
+            "awskotlin.region=us-east-1",
+            "awskotlin.s3.endpoint=http://localhost:4566",
+            "awskotlin.s3.path-style-access-enabled=false",
+            "awskotlin.access-key-id=test-access-key",
+            "awskotlin.secret-access-key=test-secret-key",
+        ).run { context ->
+            context.getBean(S3Client::class.java).config.forcePathStyle shouldBe false
+        }
+    }
+
+    @Test
+    fun `awskotlin s3 enabled=false removes the beans`() {
+        runner.withPropertyValues(
+            "awskotlin.region=us-east-1",
+            "awskotlin.s3.enabled=false",
+        ).run { context ->
+            context.getBeanNamesForType(S3Client::class.java).isEmpty() shouldBe true
+            context.getBeanNamesForType(S3Template::class.java).isEmpty() shouldBe true
+        }
+    }
+
+    @Test
+    fun `awskotlin enabled=false removes the beans`() {
+        runner.withPropertyValues(
+            "awskotlin.region=us-east-1",
+            "awskotlin.enabled=false",
+        ).run { context ->
+            context.getBeanNamesForType(S3Client::class.java).isEmpty() shouldBe true
+            context.getBeanNamesForType(S3Template::class.java).isEmpty() shouldBe true
+        }
+    }
+
+    @Test
+    fun `user S3Client bean wins`() {
+        runner.withPropertyValues(
+            "awskotlin.region=us-east-1",
+        ).withUserConfiguration(UserS3ClientConfiguration::class.java).run { context ->
+            context.getBeanNamesForType(S3Client::class.java).size shouldBe 1
+            val userBean = context.getBean("userS3Client")
+            context.getBean(S3Client::class.java) shouldBeSameInstanceAs userBean
+            context.getBeanNamesForType(S3Template::class.java).size shouldBe 1
+        }
+    }
+}
+
+@Configuration(proxyBeanMethods = false)
+class UserS3ClientConfiguration {
+    @Bean
+    fun userS3Client(): S3Client = mockk()
+}
diff --git a/starters/secrets-manager-starter/build.gradle.kts b/starters/secrets-manager-starter/build.gradle.kts
new file mode 100644
index 0000000..409065d
--- /dev/null
+++ b/starters/secrets-manager-starter/build.gradle.kts
@@ -0,0 +1,56 @@
+plugins {
+    alias(libs.plugins.kotlinJvm)
+    alias(libs.plugins.dependencyManagement)
+    alias(libs.plugins.kotlinter)
+    `java-library`
+}
+
+group = "io.github.hantsy"
+version = "0.1.0-SNAPSHOT"
+
+base { archivesName.set("awskotlin-secrets-manager-spring-boot-starter") }
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
+    api(project(":secrets-manager"))
+    api(project(":core"))
+    implementation(libs.springBootAutoconfigure)
+    implementation(libs.kotlinStdlib)
+    testImplementation(libs.springBootStarterTest)
+    testImplementation(libs.bundles.kotest)
+    testImplementation(libs.bundles.mockk)
+    testImplementation(libs.bundles.kotlintest)
+    testRuntimeOnly(libs.junitPlatformLauncher)
+}
+
+kotlin { jvmToolchain(libs.versions.java.get().toInt()) }
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
diff --git a/starters/secrets-manager-starter/src/main/kotlin/io/github/hantsy/awskotlin/secretsmanager/autoconfigure/SecretsManagerAutoConfiguration.kt b/starters/secrets-manager-starter/src/main/kotlin/io/github/hantsy/awskotlin/secretsmanager/autoconfigure/SecretsManagerAutoConfiguration.kt
new file mode 100644
index 0000000..3bdb194
--- /dev/null
+++ b/starters/secrets-manager-starter/src/main/kotlin/io/github/hantsy/awskotlin/secretsmanager/autoconfigure/SecretsManagerAutoConfiguration.kt
@@ -0,0 +1,51 @@
+package io.github.hantsy.awskotlin.secretsmanager.autoconfigure
+
+import aws.sdk.kotlin.services.secretsmanager.SecretsManagerClient
+import aws.smithy.kotlin.runtime.auth.awscredentials.CredentialsProvider
+import aws.smithy.kotlin.runtime.net.url.Url
+import io.github.hantsy.awskotlin.core.AwsKotlinProperties
+import io.github.hantsy.awskotlin.core.autoconfigure.AwsKotlinAutoConfiguration
+import io.github.hantsy.awskotlin.secretsmanager.SecretsManagerTemplate
+import org.springframework.boot.autoconfigure.AutoConfiguration
+import org.springframework.boot.autoconfigure.condition.ConditionalOnClass
+import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
+import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
+import org.springframework.boot.context.properties.EnableConfigurationProperties
+import org.springframework.context.annotation.Bean
+
+/**
+ * Auto-configuration for the AWS SDK for Kotlin Secrets Manager client and [SecretsManagerTemplate].
+ *
+ * Disabled by either `awskotlin.enabled=false` or `awskotlin.secrets-manager.enabled=false`
+ * (both checked by the single property condition below). User-provided
+ * [SecretsManagerClient]/[SecretsManagerTemplate] beans always win.
+ */
+@AutoConfiguration(after = [AwsKotlinAutoConfiguration::class])
+@ConditionalOnClass(SecretsManagerClient::class)
+@ConditionalOnProperty(
+    prefix = "awskotlin",
+    name = ["enabled", "secrets-manager.enabled"],
+    havingValue = "true",
+    matchIfMissing = true,
+)
+@EnableConfigurationProperties(SecretsManagerProperties::class)
+class SecretsManagerAutoConfiguration {
+    @Bean
+    @ConditionalOnMissingBean
+    fun secretsManagerClient(
+        properties: AwsKotlinProperties,
+        secretsManagerProperties: SecretsManagerProperties,
+        credentialsProvider: CredentialsProvider,
+    ): SecretsManagerClient {
+        val endpoint = secretsManagerProperties.endpoint ?: properties.endpoint
+        return SecretsManagerClient {
+            region = secretsManagerProperties.region ?: properties.region
+            this.credentialsProvider = credentialsProvider
+            endpoint?.let { endpointUrl = Url.parse(it) }
+        }
+    }
+
+    @Bean
+    @ConditionalOnMissingBean
+    fun secretsManagerTemplate(client: SecretsManagerClient): SecretsManagerTemplate = SecretsManagerTemplate(client)
+}
diff --git a/starters/secrets-manager-starter/src/main/kotlin/io/github/hantsy/awskotlin/secretsmanager/autoconfigure/SecretsManagerProperties.kt b/starters/secrets-manager-starter/src/main/kotlin/io/github/hantsy/awskotlin/secretsmanager/autoconfigure/SecretsManagerProperties.kt
new file mode 100644
index 0000000..d4d5b5c
--- /dev/null
+++ b/starters/secrets-manager-starter/src/main/kotlin/io/github/hantsy/awskotlin/secretsmanager/autoconfigure/SecretsManagerProperties.kt
@@ -0,0 +1,16 @@
+package io.github.hantsy.awskotlin.secretsmanager.autoconfigure
+
+import org.springframework.boot.context.properties.ConfigurationProperties
+
+/**
+ * Configuration properties for the Secrets Manager module.
+ *
+ * Unset values fall back to the shared `awskotlin.*` properties and then to the
+ * SDK default chains.
+ */
+@ConfigurationProperties(prefix = "awskotlin.secrets-manager")
+data class SecretsManagerProperties(
+    val enabled: Boolean = true,
+    val endpoint: String? = null,
+    val region: String? = null,
+)
diff --git a/starters/secrets-manager-starter/src/main/resources/META-INF/spring-configuration-metadata.json b/starters/secrets-manager-starter/src/main/resources/META-INF/spring-configuration-metadata.json
new file mode 100644
index 0000000..3bc5904
--- /dev/null
+++ b/starters/secrets-manager-starter/src/main/resources/META-INF/spring-configuration-metadata.json
@@ -0,0 +1,30 @@
+{
+  "groups": [
+    {
+      "name": "awskotlin.secrets-manager",
+      "type": "io.github.hantsy.awskotlin.secretsmanager.autoconfigure.SecretsManagerProperties",
+      "sourceType": "io.github.hantsy.awskotlin.secretsmanager.autoconfigure.SecretsManagerProperties"
+    }
+  ],
+  "properties": [
+    {
+      "name": "awskotlin.secrets-manager.enabled",
+      "type": "java.lang.Boolean",
+      "description": "Whether Secrets Manager auto-configuration is enabled.",
+      "sourceType": "io.github.hantsy.awskotlin.secretsmanager.autoconfigure.SecretsManagerProperties",
+      "defaultValue": true
+    },
+    {
+      "name": "awskotlin.secrets-manager.endpoint",
+      "type": "java.lang.String",
+      "description": "Override the Secrets Manager endpoint URL (for example, a LocalStack endpoint). Falls back to awskotlin.endpoint.",
+      "sourceType": "io.github.hantsy.awskotlin.secretsmanager.autoconfigure.SecretsManagerProperties"
+    },
+    {
+      "name": "awskotlin.secrets-manager.region",
+      "type": "java.lang.String",
+      "description": "Override the Secrets Manager region. Falls back to awskotlin.region, then the SDK default region chain.",
+      "sourceType": "io.github.hantsy.awskotlin.secretsmanager.autoconfigure.SecretsManagerProperties"
+    }
+  ]
+}
\ No newline at end of file
diff --git a/starters/secrets-manager-starter/src/main/resources/META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports b/starters/secrets-manager-starter/src/main/resources/META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports
new file mode 100644
index 0000000..dd349b1
--- /dev/null
+++ b/starters/secrets-manager-starter/src/main/resources/META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports
@@ -0,0 +1 @@
+io.github.hantsy.awskotlin.secretsmanager.autoconfigure.SecretsManagerAutoConfiguration
\ No newline at end of file
diff --git a/starters/secrets-manager-starter/src/test/kotlin/io/github/hantsy/awskotlin/secretsmanager/autoconfigure/SecretsManagerAutoConfigurationTest.kt b/starters/secrets-manager-starter/src/test/kotlin/io/github/hantsy/awskotlin/secretsmanager/autoconfigure/SecretsManagerAutoConfigurationTest.kt
new file mode 100644
index 0000000..3a67ed7
--- /dev/null
+++ b/starters/secrets-manager-starter/src/test/kotlin/io/github/hantsy/awskotlin/secretsmanager/autoconfigure/SecretsManagerAutoConfigurationTest.kt
@@ -0,0 +1,115 @@
+package io.github.hantsy.awskotlin.secretsmanager.autoconfigure
+
+import aws.sdk.kotlin.runtime.auth.credentials.StaticCredentialsProvider
+import aws.sdk.kotlin.services.secretsmanager.SecretsManagerClient
+import io.github.hantsy.awskotlin.core.autoconfigure.AwsKotlinAutoConfiguration
+import io.github.hantsy.awskotlin.secretsmanager.SecretsManagerTemplate
+import io.kotest.matchers.shouldBe
+import io.kotest.matchers.types.shouldBeInstanceOf
+import io.kotest.matchers.types.shouldBeSameInstanceAs
+import io.mockk.mockk
+import org.junit.jupiter.api.Test
+import org.springframework.boot.autoconfigure.AutoConfigurations
+import org.springframework.boot.test.context.runner.ApplicationContextRunner
+import org.springframework.context.annotation.Bean
+import org.springframework.context.annotation.Configuration
+
+class SecretsManagerAutoConfigurationTest {
+    private val runner =
+        ApplicationContextRunner().withConfiguration(
+            AutoConfigurations.of(AwsKotlinAutoConfiguration::class.java, SecretsManagerAutoConfiguration::class.java),
+        )
+
+    @Test
+    fun `default SecretsManagerClient and SecretsManagerTemplate beans with static credentials and region`() {
+        runner.withPropertyValues(
+            "awskotlin.region=us-east-1",
+            "awskotlin.access-key-id=test-access-key",
+            "awskotlin.secret-access-key=test-secret-key",
+        ).run { context ->
+            context.getBeanNamesForType(SecretsManagerClient::class.java).size shouldBe 1
+            context.getBeanNamesForType(SecretsManagerTemplate::class.java).size shouldBe 1
+            val client = context.getBean(SecretsManagerClient::class.java)
+            client.config.region shouldBe "us-east-1"
+            client.config.credentialsProvider.shouldBeInstanceOf<StaticCredentialsProvider>()
+        }
+    }
+
+    @Test
+    fun `secrets-manager region overrides the global region`() {
+        runner.withPropertyValues(
+            "awskotlin.region=us-east-1",
+            "awskotlin.secrets-manager.region=eu-west-1",
+            "awskotlin.access-key-id=test-access-key",
+            "awskotlin.secret-access-key=test-secret-key",
+        ).run { context ->
+            context.getBean(SecretsManagerClient::class.java).config.region shouldBe "eu-west-1"
+        }
+    }
+
+    @Test
+    fun `secrets-manager endpoint override is applied`() {
+        runner.withPropertyValues(
+            "awskotlin.region=us-east-1",
+            "awskotlin.secrets-manager.endpoint=http://localhost:4566",
+            "awskotlin.access-key-id=test-access-key",
+            "awskotlin.secret-access-key=test-secret-key",
+        ).run { context ->
+            context.getBean(SecretsManagerClient::class.java).config.endpointUrl?.toString() shouldBe
+                "http://localhost:4566"
+        }
+    }
+
+    @Test
+    fun `global endpoint override is used when secrets-manager endpoint is unset`() {
+        runner.withPropertyValues(
+            "awskotlin.region=us-east-1",
+            "awskotlin.endpoint=http://localhost:4566",
+            "awskotlin.access-key-id=test-access-key",
+            "awskotlin.secret-access-key=test-secret-key",
+        ).run { context ->
+            context.getBean(SecretsManagerClient::class.java).config.endpointUrl?.toString() shouldBe
+                "http://localhost:4566"
+        }
+    }
+
+    @Test
+    fun `awskotlin secrets-manager enabled=false removes the beans`() {
+        runner.withPropertyValues(
+            "awskotlin.region=us-east-1",
+            "awskotlin.secrets-manager.enabled=false",
+        ).run { context ->
+            context.getBeanNamesForType(SecretsManagerClient::class.java).isEmpty() shouldBe true
+            context.getBeanNamesForType(SecretsManagerTemplate::class.java).isEmpty() shouldBe true
+        }
+    }
+
+    @Test
+    fun `awskotlin enabled=false removes the beans`() {
+        runner.withPropertyValues(
+            "awskotlin.region=us-east-1",
+            "awskotlin.enabled=false",
+        ).run { context ->
+            context.getBeanNamesForType(SecretsManagerClient::class.java).isEmpty() shouldBe true
+            context.getBeanNamesForType(SecretsManagerTemplate::class.java).isEmpty() shouldBe true
+        }
+    }
+
+    @Test
+    fun `user SecretsManagerClient bean wins`() {
+        runner.withPropertyValues(
+            "awskotlin.region=us-east-1",
+        ).withUserConfiguration(UserSecretsManagerClientConfiguration::class.java).run { context ->
+            context.getBeanNamesForType(SecretsManagerClient::class.java).size shouldBe 1
+            val userBean = context.getBean("userSecretsManagerClient")
+            context.getBean(SecretsManagerClient::class.java) shouldBeSameInstanceAs userBean
+            context.getBeanNamesForType(SecretsManagerTemplate::class.java).size shouldBe 1
+        }
+    }
+}
+
+@Configuration(proxyBeanMethods = false)
+class UserSecretsManagerClientConfiguration {
+    @Bean
+    fun userSecretsManagerClient(): SecretsManagerClient = mockk(relaxed = true)
+}
diff --git a/starters/sns-starter/build.gradle.kts b/starters/sns-starter/build.gradle.kts
new file mode 100644
index 0000000..a22e68e
--- /dev/null
+++ b/starters/sns-starter/build.gradle.kts
@@ -0,0 +1,56 @@
+plugins {
+    alias(libs.plugins.kotlinJvm)
+    alias(libs.plugins.dependencyManagement)
+    alias(libs.plugins.kotlinter)
+    `java-library`
+}
+
+group = "io.github.hantsy"
+version = "0.1.0-SNAPSHOT"
+
+base { archivesName.set("awskotlin-sns-spring-boot-starter") }
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
+    api(project(":sns"))
+    api(project(":core"))
+    implementation(libs.springBootAutoconfigure)
+    implementation(libs.kotlinStdlib)
+    testImplementation(libs.springBootStarterTest)
+    testImplementation(libs.bundles.kotest)
+    testImplementation(libs.bundles.mockk)
+    testImplementation(libs.bundles.kotlintest)
+    testRuntimeOnly(libs.junitPlatformLauncher)
+}
+
+kotlin { jvmToolchain(libs.versions.java.get().toInt()) }
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
diff --git a/starters/sns-starter/src/main/kotlin/io/github/hantsy/awskotlin/sns/autoconfigure/SnsAutoConfiguration.kt b/starters/sns-starter/src/main/kotlin/io/github/hantsy/awskotlin/sns/autoconfigure/SnsAutoConfiguration.kt
new file mode 100644
index 0000000..f97253a
--- /dev/null
+++ b/starters/sns-starter/src/main/kotlin/io/github/hantsy/awskotlin/sns/autoconfigure/SnsAutoConfiguration.kt
@@ -0,0 +1,51 @@
+package io.github.hantsy.awskotlin.sns.autoconfigure
+
+import aws.sdk.kotlin.services.sns.SnsClient
+import aws.smithy.kotlin.runtime.auth.awscredentials.CredentialsProvider
+import aws.smithy.kotlin.runtime.net.url.Url
+import io.github.hantsy.awskotlin.core.AwsKotlinProperties
+import io.github.hantsy.awskotlin.core.autoconfigure.AwsKotlinAutoConfiguration
+import io.github.hantsy.awskotlin.sns.SnsTemplate
+import org.springframework.boot.autoconfigure.AutoConfiguration
+import org.springframework.boot.autoconfigure.condition.ConditionalOnClass
+import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
+import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
+import org.springframework.boot.context.properties.EnableConfigurationProperties
+import org.springframework.context.annotation.Bean
+
+/**
+ * Auto-configuration for the AWS SDK for Kotlin SNS client and [SnsTemplate].
+ *
+ * Disabled by either `awskotlin.enabled=false` or `awskotlin.sns.enabled=false`
+ * (both checked by the single property condition below). User-provided
+ * [SnsClient]/[SnsTemplate] beans always win.
+ */
+@AutoConfiguration(after = [AwsKotlinAutoConfiguration::class])
+@ConditionalOnClass(SnsClient::class)
+@ConditionalOnProperty(
+    prefix = "awskotlin",
+    name = ["enabled", "sns.enabled"],
+    havingValue = "true",
+    matchIfMissing = true,
+)
+@EnableConfigurationProperties(SnsProperties::class)
+class SnsAutoConfiguration {
+    @Bean
+    @ConditionalOnMissingBean
+    fun snsClient(
+        properties: AwsKotlinProperties,
+        snsProperties: SnsProperties,
+        credentialsProvider: CredentialsProvider,
+    ): SnsClient {
+        val endpoint = snsProperties.endpoint ?: properties.endpoint
+        return SnsClient {
+            region = snsProperties.region ?: properties.region
+            this.credentialsProvider = credentialsProvider
+            endpoint?.let { endpointUrl = Url.parse(it) }
+        }
+    }
+
+    @Bean
+    @ConditionalOnMissingBean
+    fun snsTemplate(client: SnsClient): SnsTemplate = SnsTemplate(client)
+}
diff --git a/starters/sns-starter/src/main/kotlin/io/github/hantsy/awskotlin/sns/autoconfigure/SnsProperties.kt b/starters/sns-starter/src/main/kotlin/io/github/hantsy/awskotlin/sns/autoconfigure/SnsProperties.kt
new file mode 100644
index 0000000..66553a9
--- /dev/null
+++ b/starters/sns-starter/src/main/kotlin/io/github/hantsy/awskotlin/sns/autoconfigure/SnsProperties.kt
@@ -0,0 +1,12 @@
+package io.github.hantsy.awskotlin.sns.autoconfigure
+
+import org.springframework.boot.context.properties.ConfigurationProperties
+
+/**
+ * Configuration properties for the SNS module.
+ *
+ * Unset values fall back to the shared `awskotlin.*` properties and then to the
+ * SDK default chains.
+ */
+@ConfigurationProperties(prefix = "awskotlin.sns")
+data class SnsProperties(val enabled: Boolean = true, val endpoint: String? = null, val region: String? = null,)
diff --git a/starters/sns-starter/src/main/resources/META-INF/spring-configuration-metadata.json b/starters/sns-starter/src/main/resources/META-INF/spring-configuration-metadata.json
new file mode 100644
index 0000000..1daa9dc
--- /dev/null
+++ b/starters/sns-starter/src/main/resources/META-INF/spring-configuration-metadata.json
@@ -0,0 +1,30 @@
+{
+  "groups": [
+    {
+      "name": "awskotlin.sns",
+      "type": "io.github.hantsy.awskotlin.sns.autoconfigure.SnsProperties",
+      "sourceType": "io.github.hantsy.awskotlin.sns.autoconfigure.SnsProperties"
+    }
+  ],
+  "properties": [
+    {
+      "name": "awskotlin.sns.enabled",
+      "type": "java.lang.Boolean",
+      "description": "Whether SNS auto-configuration is enabled.",
+      "sourceType": "io.github.hantsy.awskotlin.sns.autoconfigure.SnsProperties",
+      "defaultValue": true
+    },
+    {
+      "name": "awskotlin.sns.endpoint",
+      "type": "java.lang.String",
+      "description": "Override the SNS endpoint URL (for example, a LocalStack endpoint). Falls back to awskotlin.endpoint.",
+      "sourceType": "io.github.hantsy.awskotlin.sns.autoconfigure.SnsProperties"
+    },
+    {
+      "name": "awskotlin.sns.region",
+      "type": "java.lang.String",
+      "description": "Override the SNS region. Falls back to awskotlin.region, then the SDK default region chain.",
+      "sourceType": "io.github.hantsy.awskotlin.sns.autoconfigure.SnsProperties"
+    }
+  ]
+}
\ No newline at end of file
diff --git a/starters/sns-starter/src/main/resources/META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports b/starters/sns-starter/src/main/resources/META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports
new file mode 100644
index 0000000..0864c79
--- /dev/null
+++ b/starters/sns-starter/src/main/resources/META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports
@@ -0,0 +1 @@
+io.github.hantsy.awskotlin.sns.autoconfigure.SnsAutoConfiguration
\ No newline at end of file
diff --git a/starters/sns-starter/src/test/kotlin/io/github/hantsy/awskotlin/sns/autoconfigure/SnsAutoConfigurationTest.kt b/starters/sns-starter/src/test/kotlin/io/github/hantsy/awskotlin/sns/autoconfigure/SnsAutoConfigurationTest.kt
new file mode 100644
index 0000000..9ef494d
--- /dev/null
+++ b/starters/sns-starter/src/test/kotlin/io/github/hantsy/awskotlin/sns/autoconfigure/SnsAutoConfigurationTest.kt
@@ -0,0 +1,113 @@
+package io.github.hantsy.awskotlin.sns.autoconfigure
+
+import aws.sdk.kotlin.runtime.auth.credentials.StaticCredentialsProvider
+import aws.sdk.kotlin.services.sns.SnsClient
+import io.github.hantsy.awskotlin.core.autoconfigure.AwsKotlinAutoConfiguration
+import io.github.hantsy.awskotlin.sns.SnsTemplate
+import io.kotest.matchers.shouldBe
+import io.kotest.matchers.types.shouldBeInstanceOf
+import io.kotest.matchers.types.shouldBeSameInstanceAs
+import io.mockk.mockk
+import org.junit.jupiter.api.Test
+import org.springframework.boot.autoconfigure.AutoConfigurations
+import org.springframework.boot.test.context.runner.ApplicationContextRunner
+import org.springframework.context.annotation.Bean
+import org.springframework.context.annotation.Configuration
+
+class SnsAutoConfigurationTest {
+    private val runner =
+        ApplicationContextRunner().withConfiguration(
+            AutoConfigurations.of(AwsKotlinAutoConfiguration::class.java, SnsAutoConfiguration::class.java),
+        )
+
+    @Test
+    fun `default SnsClient and SnsTemplate beans with static credentials and region`() {
+        runner.withPropertyValues(
+            "awskotlin.region=us-east-1",
+            "awskotlin.access-key-id=test-access-key",
+            "awskotlin.secret-access-key=test-secret-key",
+        ).run { context ->
+            context.getBeanNamesForType(SnsClient::class.java).size shouldBe 1
+            context.getBeanNamesForType(SnsTemplate::class.java).size shouldBe 1
+            val client = context.getBean(SnsClient::class.java)
+            client.config.region shouldBe "us-east-1"
+            client.config.credentialsProvider.shouldBeInstanceOf<StaticCredentialsProvider>()
+        }
+    }
+
+    @Test
+    fun `sns region overrides the global region`() {
+        runner.withPropertyValues(
+            "awskotlin.region=us-east-1",
+            "awskotlin.sns.region=eu-west-1",
+            "awskotlin.access-key-id=test-access-key",
+            "awskotlin.secret-access-key=test-secret-key",
+        ).run { context ->
+            context.getBean(SnsClient::class.java).config.region shouldBe "eu-west-1"
+        }
+    }
+
+    @Test
+    fun `sns endpoint override is applied`() {
+        runner.withPropertyValues(
+            "awskotlin.region=us-east-1",
+            "awskotlin.sns.endpoint=http://localhost:4566",
+            "awskotlin.access-key-id=test-access-key",
+            "awskotlin.secret-access-key=test-secret-key",
+        ).run { context ->
+            context.getBean(SnsClient::class.java).config.endpointUrl?.toString() shouldBe "http://localhost:4566"
+        }
+    }
+
+    @Test
+    fun `global endpoint override is used when sns endpoint is unset`() {
+        runner.withPropertyValues(
+            "awskotlin.region=us-east-1",
+            "awskotlin.endpoint=http://localhost:4566",
+            "awskotlin.access-key-id=test-access-key",
+            "awskotlin.secret-access-key=test-secret-key",
+        ).run { context ->
+            context.getBean(SnsClient::class.java).config.endpointUrl?.toString() shouldBe "http://localhost:4566"
+        }
+    }
+
+    @Test
+    fun `awskotlin sns enabled=false removes the beans`() {
+        runner.withPropertyValues(
+            "awskotlin.region=us-east-1",
+            "awskotlin.sns.enabled=false",
+        ).run { context ->
+            context.getBeanNamesForType(SnsClient::class.java).isEmpty() shouldBe true
+            context.getBeanNamesForType(SnsTemplate::class.java).isEmpty() shouldBe true
+        }
+    }
+
+    @Test
+    fun `awskotlin enabled=false removes the beans`() {
+        runner.withPropertyValues(
+            "awskotlin.region=us-east-1",
+            "awskotlin.enabled=false",
+        ).run { context ->
+            context.getBeanNamesForType(SnsClient::class.java).isEmpty() shouldBe true
+            context.getBeanNamesForType(SnsTemplate::class.java).isEmpty() shouldBe true
+        }
+    }
+
+    @Test
+    fun `user SnsClient bean wins`() {
+        runner.withPropertyValues(
+            "awskotlin.region=us-east-1",
+        ).withUserConfiguration(UserSnsClientConfiguration::class.java).run { context ->
+            context.getBeanNamesForType(SnsClient::class.java).size shouldBe 1
+            val userBean = context.getBean("userSnsClient")
+            context.getBean(SnsClient::class.java) shouldBeSameInstanceAs userBean
+            context.getBeanNamesForType(SnsTemplate::class.java).size shouldBe 1
+        }
+    }
+}
+
+@Configuration(proxyBeanMethods = false)
+class UserSnsClientConfiguration {
+    @Bean
+    fun userSnsClient(): SnsClient = mockk(relaxed = true)
+}
diff --git a/starters/sqs-starter/build.gradle.kts b/starters/sqs-starter/build.gradle.kts
new file mode 100644
index 0000000..711795d
--- /dev/null
+++ b/starters/sqs-starter/build.gradle.kts
@@ -0,0 +1,59 @@
+plugins {
+    alias(libs.plugins.kotlinJvm)
+    alias(libs.plugins.dependencyManagement)
+    alias(libs.plugins.kotlinter)
+    `java-library`
+}
+
+group = "io.github.hantsy"
+version = "0.1.0-SNAPSHOT"
+
+base { archivesName.set("awskotlin-sqs-spring-boot-starter") }
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
+    testImplementation(libs.springBootStarterTest)
+    testImplementation(libs.bundles.kotest)
+    testImplementation(libs.bundles.mockk)
+    testImplementation(libs.bundles.kotlintest)
+    testImplementation(libs.kotlinCoroutinesCore)
+    testImplementation(libs.bundles.testcontainers)
+    testImplementation(libs.bundles.awaitility)
+    testRuntimeOnly(libs.junitPlatformLauncher)
+}
+
+kotlin { jvmToolchain(libs.versions.java.get().toInt()) }
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
diff --git a/starters/sqs-starter/src/main/kotlin/io/github/hantsy/awskotlin/sqs/autoconfigure/KotlinDurationConverter.kt b/starters/sqs-starter/src/main/kotlin/io/github/hantsy/awskotlin/sqs/autoconfigure/KotlinDurationConverter.kt
new file mode 100644
index 0000000..8b1e5ea
--- /dev/null
+++ b/starters/sqs-starter/src/main/kotlin/io/github/hantsy/awskotlin/sqs/autoconfigure/KotlinDurationConverter.kt
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
diff --git a/starters/sqs-starter/src/main/kotlin/io/github/hantsy/awskotlin/sqs/autoconfigure/SqsAutoConfiguration.kt b/starters/sqs-starter/src/main/kotlin/io/github/hantsy/awskotlin/sqs/autoconfigure/SqsAutoConfiguration.kt
new file mode 100644
index 0000000..09cfea4
--- /dev/null
+++ b/starters/sqs-starter/src/main/kotlin/io/github/hantsy/awskotlin/sqs/autoconfigure/SqsAutoConfiguration.kt
@@ -0,0 +1,98 @@
+package io.github.hantsy.awskotlin.sqs.autoconfigure
+
+import aws.sdk.kotlin.services.sqs.SqsClient
+import aws.smithy.kotlin.runtime.auth.awscredentials.CredentialsProvider
+import aws.smithy.kotlin.runtime.net.url.Url
+import io.github.hantsy.awskotlin.core.AwsKotlinProperties
+import io.github.hantsy.awskotlin.core.autoconfigure.AwsKotlinAutoConfiguration
+import io.github.hantsy.awskotlin.sqs.KotlinxSqsMessageConverter
+import io.github.hantsy.awskotlin.sqs.SqsMessageConverter
+import io.github.hantsy.awskotlin.sqs.SqsQueueResolver
+import io.github.hantsy.awskotlin.sqs.SqsTemplate
+import io.github.hantsy.awskotlin.sqs.listener.SqsListenerAnnotationRegistrar
+import io.github.hantsy.awskotlin.sqs.listener.SqsListenerConfig
+import org.springframework.boot.autoconfigure.AutoConfiguration
+import org.springframework.boot.autoconfigure.condition.ConditionalOnClass
+import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
+import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
+import org.springframework.boot.context.properties.ConfigurationPropertiesBinding
+import org.springframework.boot.context.properties.EnableConfigurationProperties
+import org.springframework.context.annotation.Bean
+
+/**
+ * Auto-configuration for the AWS SDK for Kotlin SQS client, [SqsTemplate] and the
+ * [SqsListenerAnnotationRegistrar] that runs `@SqsListener` methods.
+ *
+ * Disabled by either `awskotlin.enabled=false` or `awskotlin.sqs.enabled=false`
+ * (both checked by the single property condition below). User-provided
+ * [SqsClient]/[SqsMessageConverter]/[SqsQueueResolver]/[SqsTemplate]/
+ * [SqsListenerAnnotationRegistrar] beans always win.
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
+    fun sqsMessageConverter(): SqsMessageConverter = KotlinxSqsMessageConverter()
+
+    @Bean
+    @ConditionalOnMissingBean
+    fun sqsQueueResolver(client: SqsClient): SqsQueueResolver = SqsQueueResolver(client)
+
+    @Bean
+    @ConditionalOnMissingBean
+    fun sqsTemplate(client: SqsClient, converter: SqsMessageConverter, queueResolver: SqsQueueResolver,): SqsTemplate =
+        SqsTemplate(client, converter, queueResolver)
+
+    companion object {
+        // Static (companion + @JvmStatic) so registering this BeanPostProcessor/SmartLifecycle
+        // does not force early instantiation of the auto-configuration class itself.
+        // autoStartup=false prevents automatic polling; the registrar can still be started manually.
+        @Bean
+        @ConditionalOnMissingBean
+        @JvmStatic
+        fun sqsListenerAnnotationRegistrar(
+            template: SqsTemplate,
+            queueResolver: SqsQueueResolver,
+            properties: SqsProperties,
+        ): SqsListenerAnnotationRegistrar = SqsListenerAnnotationRegistrar(
+            template = template,
+            queueResolver = queueResolver,
+            config = SqsListenerConfig(
+                autoStartup = properties.listener.autoStartup,
+                maxConcurrentMessages = properties.listener.maxConcurrentMessages,
+                pollTime = properties.listener.pollTime,
+                shutdownTimeout = properties.listener.shutdownTimeout,
+            ),
+        )
+    }
+}
diff --git a/starters/sqs-starter/src/main/kotlin/io/github/hantsy/awskotlin/sqs/autoconfigure/SqsProperties.kt b/starters/sqs-starter/src/main/kotlin/io/github/hantsy/awskotlin/sqs/autoconfigure/SqsProperties.kt
new file mode 100644
index 0000000..c83e2c4
--- /dev/null
+++ b/starters/sqs-starter/src/main/kotlin/io/github/hantsy/awskotlin/sqs/autoconfigure/SqsProperties.kt
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
diff --git a/starters/sqs-starter/src/main/resources/META-INF/spring-configuration-metadata.json b/starters/sqs-starter/src/main/resources/META-INF/spring-configuration-metadata.json
new file mode 100644
index 0000000..61d453a
--- /dev/null
+++ b/starters/sqs-starter/src/main/resources/META-INF/spring-configuration-metadata.json
@@ -0,0 +1,69 @@
+{
+  "groups": [
+    {
+      "name": "awskotlin.sqs",
+      "type": "io.github.hantsy.awskotlin.sqs.autoconfigure.SqsProperties",
+      "sourceType": "io.github.hantsy.awskotlin.sqs.autoconfigure.SqsProperties"
+    },
+    {
+      "name": "awskotlin.sqs.listener",
+      "type": "io.github.hantsy.awskotlin.sqs.autoconfigure.SqsProperties$Listener",
+      "sourceType": "io.github.hantsy.awskotlin.sqs.autoconfigure.SqsProperties$Listener"
+    }
+  ],
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
diff --git a/starters/sqs-starter/src/main/resources/META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports b/starters/sqs-starter/src/main/resources/META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports
new file mode 100644
index 0000000..36e1d95
--- /dev/null
+++ b/starters/sqs-starter/src/main/resources/META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports
@@ -0,0 +1 @@
+io.github.hantsy.awskotlin.sqs.autoconfigure.SqsAutoConfiguration
diff --git a/starters/sqs-starter/src/test/kotlin/io/github/hantsy/awskotlin/sqs/autoconfigure/SqsAutoConfigurationTest.kt b/starters/sqs-starter/src/test/kotlin/io/github/hantsy/awskotlin/sqs/autoconfigure/SqsAutoConfigurationTest.kt
new file mode 100644
index 0000000..be8b6bc
--- /dev/null
+++ b/starters/sqs-starter/src/test/kotlin/io/github/hantsy/awskotlin/sqs/autoconfigure/SqsAutoConfigurationTest.kt
@@ -0,0 +1,286 @@
+package io.github.hantsy.awskotlin.sqs.autoconfigure
+
+import aws.sdk.kotlin.runtime.auth.credentials.StaticCredentialsProvider
+import aws.sdk.kotlin.services.sqs.SqsClient
+import io.github.hantsy.awskotlin.core.autoconfigure.AwsKotlinAutoConfiguration
+import io.github.hantsy.awskotlin.sqs.KotlinxSqsMessageConverter
+import io.github.hantsy.awskotlin.sqs.SqsMessageConverter
+import io.github.hantsy.awskotlin.sqs.SqsQueueResolver
+import io.github.hantsy.awskotlin.sqs.SqsTemplate
+import io.github.hantsy.awskotlin.sqs.listener.SqsListenerAnnotationRegistrar
+import io.github.hantsy.awskotlin.sqs.listener.SqsListenerConfig
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
+            context.getBeanNamesForType(SqsListenerAnnotationRegistrar::class.java).size shouldBe 1
+            val client = context.getBean(SqsClient::class.java)
+            client.config.region shouldBe "us-east-1"
+            client.config.credentialsProvider.shouldBeInstanceOf<StaticCredentialsProvider>()
+            context.getBean(SqsMessageConverter::class.java).shouldBeInstanceOf<KotlinxSqsMessageConverter>()
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
+            context.getBeanNamesForType(SqsListenerAnnotationRegistrar::class.java).isEmpty() shouldBe true
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
+            context.getBeanNamesForType(SqsListenerAnnotationRegistrar::class.java).isEmpty() shouldBe true
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
+
+    @Test
+    fun `user SqsListenerAnnotationRegistrar bean wins`() {
+        runner.withPropertyValues(
+            "awskotlin.region=us-east-1",
+        ).withUserConfiguration(UserSqsListenerRegistrarConfiguration::class.java).run { context ->
+            context.getBeanNamesForType(SqsListenerAnnotationRegistrar::class.java).size shouldBe 1
+            val userBean = context.getBean("userSqsListenerRegistrar")
+            context.getBean(SqsListenerAnnotationRegistrar::class.java) shouldBeSameInstanceAs userBean
+        }
+    }
+
+    @Test
+    fun `default listener config maps into the registrar`() {
+        runner.withPropertyValues(
+            "awskotlin.region=us-east-1",
+        ).run { context ->
+            val registrar = context.getBean(SqsListenerAnnotationRegistrar::class.java)
+            registrarConfig(registrar) shouldBe SqsListenerConfig()
+            registrar.isAutoStartup shouldBe true
+        }
+    }
+
+    @Test
+    fun `custom listener properties map into the registrar and autoStartup=false prevents polling`() {
+        runner.withPropertyValues(
+            "awskotlin.region=us-east-1",
+            "awskotlin.sqs.listener.auto-startup=false",
+            "awskotlin.sqs.listener.max-concurrent-messages=5",
+            "awskotlin.sqs.listener.poll-time=5s",
+            "awskotlin.sqs.listener.shutdown-timeout=30s",
+        ).run { context ->
+            val registrar = context.getBean(SqsListenerAnnotationRegistrar::class.java)
+            registrarConfig(registrar) shouldBe SqsListenerConfig(
+                autoStartup = false,
+                maxConcurrentMessages = 5,
+                pollTime = 5.seconds,
+                shutdownTimeout = 30.seconds,
+            )
+            // autoStartup=false: the refreshed context did not start the registrar...
+            registrar.isAutoStartup shouldBe false
+            registrar.isRunning shouldBe false
+            // ...but manual start still works (no endpoints registered, so no AWS calls).
+            registrar.start()
+            registrar.isRunning shouldBe true
+            registrar.stop()
+            registrar.isRunning shouldBe false
+        }
+    }
+
+    // The registrar keeps its config private; read it reflectively to verify property mapping.
+    private fun registrarConfig(registrar: SqsListenerAnnotationRegistrar): SqsListenerConfig {
+        val field = SqsListenerAnnotationRegistrar::class.java.getDeclaredField("config")
+        field.isAccessible = true
+        return field.get(registrar) as SqsListenerConfig
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
+
+@Configuration(proxyBeanMethods = false)
+class UserSqsListenerRegistrarConfiguration {
+    @Bean
+    fun userSqsListenerRegistrar(): SqsListenerAnnotationRegistrar =
+        SqsListenerAnnotationRegistrar(mockk(relaxed = true), mockk(relaxed = true))
+}
diff --git a/starters/sqs-starter/src/test/kotlin/io/github/hantsy/awskotlin/sqs/autoconfigure/SqsLocalStackIntegrationTest.kt b/starters/sqs-starter/src/test/kotlin/io/github/hantsy/awskotlin/sqs/autoconfigure/SqsLocalStackIntegrationTest.kt
new file mode 100644
index 0000000..c56e0b3
--- /dev/null
+++ b/starters/sqs-starter/src/test/kotlin/io/github/hantsy/awskotlin/sqs/autoconfigure/SqsLocalStackIntegrationTest.kt
@@ -0,0 +1,214 @@
+package io.github.hantsy.awskotlin.sqs.autoconfigure
+
+import aws.sdk.kotlin.runtime.auth.credentials.StaticCredentialsProvider
+import aws.sdk.kotlin.services.sqs.SqsClient
+import aws.sdk.kotlin.services.sqs.createQueue
+import aws.smithy.kotlin.runtime.net.url.Url
+import io.github.hantsy.awskotlin.core.autoconfigure.AwsKotlinAutoConfiguration
+import io.github.hantsy.awskotlin.sqs.SqsAcknowledgementMode
+import io.github.hantsy.awskotlin.sqs.SqsQueueNotFoundException
+import io.github.hantsy.awskotlin.sqs.SqsTemplate
+import io.github.hantsy.awskotlin.sqs.listener.Acknowledge
+import io.github.hantsy.awskotlin.sqs.listener.SqsListener
+import io.kotest.matchers.nulls.shouldNotBeNull
+import io.kotest.matchers.shouldBe
+import kotlinx.coroutines.runBlocking
+import org.awaitility.kotlin.await
+import org.junit.jupiter.api.AfterAll
+import org.junit.jupiter.api.Assumptions.assumeTrue
+import org.junit.jupiter.api.BeforeAll
+import org.junit.jupiter.api.Test
+import org.junit.jupiter.api.TestInstance
+import org.springframework.boot.autoconfigure.AutoConfigurations
+import org.springframework.boot.test.context.runner.ApplicationContextRunner
+import org.testcontainers.DockerClientFactory
+import org.testcontainers.localstack.LocalStackContainer
+import org.testcontainers.utility.DockerImageName
+import java.time.Duration
+import java.util.UUID
+import java.util.concurrent.CopyOnWriteArrayList
+import kotlin.time.Duration.Companion.seconds
+
+// @SqsListener queue names must be compile-time constants, so listener tests use fixed
+// per-test names (unique within the freshly started container); template tests use UUIDs.
+private const val AUTO_ACK_QUEUE = "awskotlin-sqs-it-auto-ack"
+private const val MANUAL_ACK_QUEUE = "awskotlin-sqs-it-manual-ack"
+private const val MISSING_QUEUE = "awskotlin-sqs-it-missing"
+
+/**
+ * End-to-end SQS template and listener integration against LocalStack.
+ *
+ * Skipped unless Docker is available and LOCALSTACK_AUTH_TOKEN is set
+ * (LocalStack 4 requires a license token).
+ */
+@TestInstance(TestInstance.Lifecycle.PER_CLASS)
+class SqsLocalStackIntegrationTest {
+    private lateinit var container: LocalStackContainer
+    private lateinit var client: SqsClient
+    private lateinit var template: SqsTemplate
+
+    @BeforeAll
+    fun startLocalStack() {
+        val authToken = System.getenv("LOCALSTACK_AUTH_TOKEN")?.takeIf { it.isNotBlank() }
+        assumeTrue(authToken != null, "LOCALSTACK_AUTH_TOKEN is not set; skipping LocalStack tests")
+        assumeTrue(
+            DockerClientFactory.instance().isDockerAvailable,
+            "Docker is not available; skipping LocalStack tests"
+        )
+
+        container =
+            LocalStackContainer(DockerImageName.parse("localstack/localstack:4"))
+                .withServices("sqs")
+                .withEnv("LOCALSTACK_AUTH_TOKEN", authToken)
+        container.start()
+
+        client =
+            SqsClient {
+                region = "us-east-1"
+                credentialsProvider =
+                    StaticCredentialsProvider {
+                        accessKeyId = "test"
+                        secretAccessKey = "test"
+                    }
+                endpointUrl = Url.parse("http://${container.host}:${container.firstMappedPort}")
+            }
+        template = SqsTemplate(client)
+    }
+
+    @AfterAll
+    fun stopLocalStack() {
+        if (::client.isInitialized) {
+            runCatching { client.close() }
+        }
+        if (::container.isInitialized) {
+            runCatching { container.stop() }
+        }
+    }
+
+    @Test
+    fun `template send and AUTO receive round trip deletes the message`() = runBlocking<Unit> {
+        val queue = newQueue()
+        val operations = template.destination(queue)
+        operations.send("hello-auto")
+
+        val received = operations.receive(String::class.java, 10.seconds)
+        received.shouldNotBeNull().payload shouldBe "hello-auto"
+
+        // AUTO acknowledgement already deleted the message; a short poll finds nothing.
+        operations.receive(String::class.java, 2.seconds) shouldBe null
+    }
+
+    @Test
+    fun `manual acknowledge deletes and reject makes the message visible again`() = runBlocking<Unit> {
+        val queue = newQueue()
+        val operations = template.destination(queue)
+        operations.send("hello-manual")
+
+        val first = operations.receive(String::class.java, 10.seconds, SqsAcknowledgementMode.MANUAL)
+        first.shouldNotBeNull().payload shouldBe "hello-manual"
+
+        // reject sets visibility timeout 0, so the same message can be received again.
+        operations.reject(first)
+        val redelivered = operations.receive(String::class.java, 10.seconds, SqsAcknowledgementMode.MANUAL)
+        redelivered.shouldNotBeNull().payload shouldBe "hello-manual"
+        redelivered.messageId shouldBe first.messageId
+
+        operations.acknowledge(redelivered)
+        operations.receive(String::class.java, 2.seconds) shouldBe null
+    }
+
+    @Test
+    fun `listener auto-acks messages through the Spring context`() {
+        createQueue(AUTO_ACK_QUEUE)
+        contextRunner().withBean(AutoAckListener::class.java).run { context ->
+            context.startupFailure shouldBe null
+            val listener = context.getBean(AutoAckListener::class.java)
+            val springTemplate = context.getBean(SqsTemplate::class.java)
+
+            runBlocking { springTemplate.destination(AUTO_ACK_QUEUE).send("hello-listener") }
+            await.atMost(Duration.ofSeconds(30)).until { listener.received.contains("hello-listener") }
+
+            // No Acknowledge parameter: the container deleted the message after invocation.
+            runBlocking {
+                springTemplate.destination(AUTO_ACK_QUEUE).receive(String::class.java, 2.seconds)
+            } shouldBe null
+        }
+    }
+
+    @Test
+    fun `listener manual Acknowledge accept deletes the message`() {
+        createQueue(MANUAL_ACK_QUEUE)
+        contextRunner().withBean(ManualAckListener::class.java).run { context ->
+            context.startupFailure shouldBe null
+            val listener = context.getBean(ManualAckListener::class.java)
+            val springTemplate = context.getBean(SqsTemplate::class.java)
+
+            runBlocking { springTemplate.destination(MANUAL_ACK_QUEUE).send("hello-manual-listener") }
+            await.atMost(Duration.ofSeconds(30)).until { listener.received.contains("hello-manual-listener") }
+
+            // Acknowledge.accept() deleted the message; nothing is redelivered.
+            runBlocking {
+                springTemplate.destination(MANUAL_ACK_QUEUE).receive(String::class.java, 2.seconds)
+            } shouldBe null
+        }
+    }
+
+    @Test
+    fun `missing queue fails context startup without implicit creation`() {
+        contextRunner().withBean(MissingQueueListener::class.java).run { context ->
+            val failure = context.startupFailure
+            failure.shouldNotBeNull()
+            generateSequence(failure) { it.cause }
+                .any { it is SqsQueueNotFoundException } shouldBe true
+        }
+    }
+
+    private fun contextRunner(): ApplicationContextRunner = ApplicationContextRunner()
+        .withConfiguration(
+            AutoConfigurations.of(AwsKotlinAutoConfiguration::class.java, SqsAutoConfiguration::class.java),
+        )
+        .withPropertyValues(
+            "awskotlin.region=us-east-1",
+            "awskotlin.access-key-id=test",
+            "awskotlin.secret-access-key=test",
+            "awskotlin.sqs.endpoint=http://${container.host}:${container.firstMappedPort}",
+            "awskotlin.sqs.listener.poll-time=1s",
+            "awskotlin.sqs.listener.shutdown-timeout=5s",
+        )
+
+    private fun newQueue(): String {
+        val name = "awskotlin-sqs-it-${UUID.randomUUID().toString().substring(0, 8)}"
+        createQueue(name)
+        return name
+    }
+
+    private fun createQueue(name: String) {
+        runBlocking { client.createQueue { queueName = name } }
+    }
+}
+
+class AutoAckListener {
+    val received = CopyOnWriteArrayList<String>()
+
+    @SqsListener(AUTO_ACK_QUEUE)
+    fun onMessage(payload: String) {
+        received += payload
+    }
+}
+
+class ManualAckListener {
+    val received = CopyOnWriteArrayList<String>()
+
+    @SqsListener(MANUAL_ACK_QUEUE)
+    suspend fun onMessage(payload: String, acknowledge: Acknowledge) {
+        received += payload
+        acknowledge.accept()
+    }
+}
+
+class MissingQueueListener {
+    @SqsListener(MISSING_QUEUE)
+    fun onMessage(payload: String) {
+        error("Must never be invoked: the queue does not exist")
+    }
+}
diff --git a/utilities/build.gradle.kts b/utilities/build.gradle.kts
deleted file mode 100644
index 8c8b951..0000000
--- a/utilities/build.gradle.kts
+++ /dev/null
@@ -1,13 +0,0 @@
-/*
- * This file was generated by the Gradle 'init' task.
- *
- * This project uses @Incubating APIs which are subject to change.
- */
-
-plugins {
-    id("buildlogic.kotlin-library-conventions")
-}
-
-dependencies {
-    api(project(":list"))
-}
diff --git a/utilities/src/main/kotlin/org/example/utilities/JoinUtils.kt b/utilities/src/main/kotlin/org/example/utilities/JoinUtils.kt
deleted file mode 100644
index 0173d35..0000000
--- a/utilities/src/main/kotlin/org/example/utilities/JoinUtils.kt
+++ /dev/null
@@ -1,22 +0,0 @@
-/*
- * This source file was generated by the Gradle 'init' task
- */
-package org.example.utilities
-
-import org.example.list.LinkedList
-
-class JoinUtils {
-    companion object {
-        fun join(source: LinkedList): String {
-            val result = StringBuilder()
-            for (i in 0 until source.size()) {
-                if (result.isNotEmpty()) {
-                    result.append(" ")
-                }
-                result.append(source.get(i))
-            }
-
-            return result.toString()
-        }
-    }
-}
diff --git a/utilities/src/main/kotlin/org/example/utilities/SplitUtils.kt b/utilities/src/main/kotlin/org/example/utilities/SplitUtils.kt
deleted file mode 100644
index 170ac5d..0000000
--- a/utilities/src/main/kotlin/org/example/utilities/SplitUtils.kt
+++ /dev/null
@@ -1,42 +0,0 @@
-/*
- * This source file was generated by the Gradle 'init' task
- */
-package org.example.utilities
-
-import org.example.list.LinkedList
-
-class SplitUtils {
-    companion object {
-        fun split(source: String): LinkedList {
-            var lastFind = 0
-            val result = LinkedList()
-
-            var currentFind = source.indexOf(" ", lastFind)
-            while (currentFind != -1) {
-                var token = source.substring(lastFind)
-                if (currentFind != -1) {
-                    token = token.substring(0, currentFind - lastFind)
-                }
-
-                addIfValid(token, result)
-                lastFind = currentFind + 1
-                currentFind = source.indexOf(" ", lastFind)
-            }
-
-            val token = source.substring(lastFind)
-            addIfValid(token, result)
-
-            return result
-        }
-
-        private fun addIfValid(token: String, list: LinkedList) {
-            if (isTokenValid(token)) {
-                list.add(token)
-            }
-        }
-
-        private fun isTokenValid(token: String): Boolean {
-            return token.isNotEmpty()
-        }
-    }
-}
diff --git a/utilities/src/main/kotlin/org/example/utilities/StringUtils.kt b/utilities/src/main/kotlin/org/example/utilities/StringUtils.kt
deleted file mode 100644
index 6e8d819..0000000
--- a/utilities/src/main/kotlin/org/example/utilities/StringUtils.kt
+++ /dev/null
@@ -1,18 +0,0 @@
-/*
- * This source file was generated by the Gradle 'init' task
- */
-package org.example.utilities
-
-import org.example.list.LinkedList
-
-class StringUtils {
-    companion object {
-        fun join(source: LinkedList): String {
-            return JoinUtils.join(source)
-        }
-
-        fun split(source: String): LinkedList {
-            return SplitUtils.split(source)
-        }
-    }
-}
