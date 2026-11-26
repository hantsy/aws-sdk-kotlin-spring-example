# Tasks 3+4 review package

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
?? .kotlin/
?? .superpowers/

## Diff stat
 secrets-manager/build.gradle.kts                   |  55 +++++++++
 secrets-manager/gradle.properties                  |   5 +
 .../awskotlin/secretsmanager/SecretOperations.kt   |  45 +++++++
 .../secretsmanager/SecretsManagerTemplate.kt       |  14 +++
 .../secretsmanager/SecretsManagerTemplateTest.kt   | 105 +++++++++++++++++
 settings.gradle.kts                                |   7 ++
 sns/build.gradle.kts                               |  55 +++++++++
 sns/gradle.properties                              |   5 +
 .../io/github/hantsy/awskotlin/sns/SnsTemplate.kt  |  21 ++++
 .../github/hantsy/awskotlin/sns/TopicOperations.kt |  36 ++++++
 .../github/hantsy/awskotlin/sns/SnsTemplateTest.kt | 110 +++++++++++++++++
 starters/starter-s3/build.gradle.kts               |  74 ++++++++++++
 starters/starter-s3/gradle.properties              |   5 +
 .../s3/autoconfigure/S3AutoConfiguration.kt        |  54 +++++++++
 .../awskotlin/s3/autoconfigure/S3Properties.kt     |  17 +++
 .../additional-spring-configuration-metadata.json  |  29 +++++
 ...rk.boot.autoconfigure.AutoConfiguration.imports |   1 +
 .../s3/autoconfigure/S3AutoConfigurationTest.kt    | 131 +++++++++++++++++++++
 starters/starter-secrets-manager/build.gradle.kts  |  74 ++++++++++++
 starters/starter-secrets-manager/gradle.properties |   5 +
 .../SecretsManagerAutoConfiguration.kt             |  51 ++++++++
 .../autoconfigure/SecretsManagerProperties.kt      |  16 +++
 .../additional-spring-configuration-metadata.json  |  23 ++++
 ...rk.boot.autoconfigure.AutoConfiguration.imports |   1 +
 .../SecretsManagerAutoConfigurationTest.kt         | 115 ++++++++++++++++++
 starters/starter-sns/build.gradle.kts              |  74 ++++++++++++
 starters/starter-sns/gradle.properties             |   5 +
 .../sns/autoconfigure/SnsAutoConfiguration.kt      |  51 ++++++++
 .../awskotlin/sns/autoconfigure/SnsProperties.kt   |  12 ++
 .../additional-spring-configuration-metadata.json  |  23 ++++
 ...rk.boot.autoconfigure.AutoConfiguration.imports |   1 +
 .../sns/autoconfigure/SnsAutoConfigurationTest.kt  | 113 ++++++++++++++++++
 32 files changed, 1333 insertions(+)

## Full diff
diff --git a/secrets-manager/build.gradle.kts b/secrets-manager/build.gradle.kts
new file mode 100644
index 0000000..0026af6
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
+base { archivesName.set("awskotlin-spring-secrets-manager") }
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
diff --git a/secrets-manager/gradle.properties b/secrets-manager/gradle.properties
new file mode 100644
index 0000000..92b73fb
--- /dev/null
+++ b/secrets-manager/gradle.properties
@@ -0,0 +1,5 @@
+# Workaround for KGP 2.4.10 + Gradle 9.9.2: Gradle ships Kotlin 2.4.21 compiler classes
+# in its parent classloader, which breaks the Build Tools API path used by kapt stub
+# generation (NoSuchMethodError: ArgumentsToStrings.toArgumentStrings$default).
+# The legacy compiler runner avoids that path. Deprecated in KGP 2.4.x, removed in 2.5.0.
+kotlin.compiler.runViaBuildToolsApi=false
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
index a2d0cf9..1947df7 100644
--- a/settings.gradle.kts
+++ b/settings.gradle.kts
@@ -11,10 +11,17 @@ pluginManagement {
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
+include("starters:starter-s3")
+include("starters:starter-secrets-manager")
+include("starters:starter-sns")
diff --git a/sns/build.gradle.kts b/sns/build.gradle.kts
new file mode 100644
index 0000000..9a55e8e
--- /dev/null
+++ b/sns/build.gradle.kts
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
+base { archivesName.set("awskotlin-spring-sns") }
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
diff --git a/sns/gradle.properties b/sns/gradle.properties
new file mode 100644
index 0000000..92b73fb
--- /dev/null
+++ b/sns/gradle.properties
@@ -0,0 +1,5 @@
+# Workaround for KGP 2.4.10 + Gradle 9.9.2: Gradle ships Kotlin 2.4.21 compiler classes
+# in its parent classloader, which breaks the Build Tools API path used by kapt stub
+# generation (NoSuchMethodError: ArgumentsToStrings.toArgumentStrings$default).
+# The legacy compiler runner avoids that path. Deprecated in KGP 2.4.x, removed in 2.5.0.
+kotlin.compiler.runViaBuildToolsApi=false
\ No newline at end of file
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
index 0000000..85dc748
--- /dev/null
+++ b/sns/src/main/kotlin/io/github/hantsy/awskotlin/sns/TopicOperations.kt
@@ -0,0 +1,36 @@
+package io.github.hantsy.awskotlin.sns
+
+import aws.sdk.kotlin.services.sns.SnsClient
+import aws.sdk.kotlin.services.sns.deleteTopic
+import aws.sdk.kotlin.services.sns.model.DeleteTopicResponse
+import aws.sdk.kotlin.services.sns.model.PublishResponse
+import aws.sdk.kotlin.services.sns.model.SubscribeResponse
+import aws.sdk.kotlin.services.sns.publish
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
+    /** Deletes the topic. */
+    suspend fun delete(): DeleteTopicResponse = client.deleteTopic {
+        topicArn = this@TopicOperations.topicArn
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
diff --git a/starters/starter-s3/build.gradle.kts b/starters/starter-s3/build.gradle.kts
new file mode 100644
index 0000000..031290f
--- /dev/null
+++ b/starters/starter-s3/build.gradle.kts
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
+base { archivesName.set("awskotlin-spring-starter-s3") }
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
diff --git a/starters/starter-s3/gradle.properties b/starters/starter-s3/gradle.properties
new file mode 100644
index 0000000..92b73fb
--- /dev/null
+++ b/starters/starter-s3/gradle.properties
@@ -0,0 +1,5 @@
+# Workaround for KGP 2.4.10 + Gradle 9.9.2: Gradle ships Kotlin 2.4.21 compiler classes
+# in its parent classloader, which breaks the Build Tools API path used by kapt stub
+# generation (NoSuchMethodError: ArgumentsToStrings.toArgumentStrings$default).
+# The legacy compiler runner avoids that path. Deprecated in KGP 2.4.x, removed in 2.5.0.
+kotlin.compiler.runViaBuildToolsApi=false
\ No newline at end of file
diff --git a/starters/starter-s3/src/main/kotlin/io/github/hantsy/awskotlin/s3/autoconfigure/S3AutoConfiguration.kt b/starters/starter-s3/src/main/kotlin/io/github/hantsy/awskotlin/s3/autoconfigure/S3AutoConfiguration.kt
new file mode 100644
index 0000000..9420f41
--- /dev/null
+++ b/starters/starter-s3/src/main/kotlin/io/github/hantsy/awskotlin/s3/autoconfigure/S3AutoConfiguration.kt
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
diff --git a/starters/starter-s3/src/main/kotlin/io/github/hantsy/awskotlin/s3/autoconfigure/S3Properties.kt b/starters/starter-s3/src/main/kotlin/io/github/hantsy/awskotlin/s3/autoconfigure/S3Properties.kt
new file mode 100644
index 0000000..7d81b89
--- /dev/null
+++ b/starters/starter-s3/src/main/kotlin/io/github/hantsy/awskotlin/s3/autoconfigure/S3Properties.kt
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
diff --git a/starters/starter-s3/src/main/resources/META-INF/additional-spring-configuration-metadata.json b/starters/starter-s3/src/main/resources/META-INF/additional-spring-configuration-metadata.json
new file mode 100644
index 0000000..1057f7d
--- /dev/null
+++ b/starters/starter-s3/src/main/resources/META-INF/additional-spring-configuration-metadata.json
@@ -0,0 +1,29 @@
+{
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
diff --git a/starters/starter-s3/src/main/resources/META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports b/starters/starter-s3/src/main/resources/META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports
new file mode 100644
index 0000000..e45ad33
--- /dev/null
+++ b/starters/starter-s3/src/main/resources/META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports
@@ -0,0 +1 @@
+io.github.hantsy.awskotlin.s3.autoconfigure.S3AutoConfiguration
\ No newline at end of file
diff --git a/starters/starter-s3/src/test/kotlin/io/github/hantsy/awskotlin/s3/autoconfigure/S3AutoConfigurationTest.kt b/starters/starter-s3/src/test/kotlin/io/github/hantsy/awskotlin/s3/autoconfigure/S3AutoConfigurationTest.kt
new file mode 100644
index 0000000..d6761d7
--- /dev/null
+++ b/starters/starter-s3/src/test/kotlin/io/github/hantsy/awskotlin/s3/autoconfigure/S3AutoConfigurationTest.kt
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
diff --git a/starters/starter-secrets-manager/build.gradle.kts b/starters/starter-secrets-manager/build.gradle.kts
new file mode 100644
index 0000000..c75e2d8
--- /dev/null
+++ b/starters/starter-secrets-manager/build.gradle.kts
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
+base { archivesName.set("awskotlin-spring-starter-secrets-manager") }
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
diff --git a/starters/starter-secrets-manager/gradle.properties b/starters/starter-secrets-manager/gradle.properties
new file mode 100644
index 0000000..92b73fb
--- /dev/null
+++ b/starters/starter-secrets-manager/gradle.properties
@@ -0,0 +1,5 @@
+# Workaround for KGP 2.4.10 + Gradle 9.9.2: Gradle ships Kotlin 2.4.21 compiler classes
+# in its parent classloader, which breaks the Build Tools API path used by kapt stub
+# generation (NoSuchMethodError: ArgumentsToStrings.toArgumentStrings$default).
+# The legacy compiler runner avoids that path. Deprecated in KGP 2.4.x, removed in 2.5.0.
+kotlin.compiler.runViaBuildToolsApi=false
\ No newline at end of file
diff --git a/starters/starter-secrets-manager/src/main/kotlin/io/github/hantsy/awskotlin/secretsmanager/autoconfigure/SecretsManagerAutoConfiguration.kt b/starters/starter-secrets-manager/src/main/kotlin/io/github/hantsy/awskotlin/secretsmanager/autoconfigure/SecretsManagerAutoConfiguration.kt
new file mode 100644
index 0000000..3bdb194
--- /dev/null
+++ b/starters/starter-secrets-manager/src/main/kotlin/io/github/hantsy/awskotlin/secretsmanager/autoconfigure/SecretsManagerAutoConfiguration.kt
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
diff --git a/starters/starter-secrets-manager/src/main/kotlin/io/github/hantsy/awskotlin/secretsmanager/autoconfigure/SecretsManagerProperties.kt b/starters/starter-secrets-manager/src/main/kotlin/io/github/hantsy/awskotlin/secretsmanager/autoconfigure/SecretsManagerProperties.kt
new file mode 100644
index 0000000..d4d5b5c
--- /dev/null
+++ b/starters/starter-secrets-manager/src/main/kotlin/io/github/hantsy/awskotlin/secretsmanager/autoconfigure/SecretsManagerProperties.kt
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
diff --git a/starters/starter-secrets-manager/src/main/resources/META-INF/additional-spring-configuration-metadata.json b/starters/starter-secrets-manager/src/main/resources/META-INF/additional-spring-configuration-metadata.json
new file mode 100644
index 0000000..b21c284
--- /dev/null
+++ b/starters/starter-secrets-manager/src/main/resources/META-INF/additional-spring-configuration-metadata.json
@@ -0,0 +1,23 @@
+{
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
diff --git a/starters/starter-secrets-manager/src/main/resources/META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports b/starters/starter-secrets-manager/src/main/resources/META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports
new file mode 100644
index 0000000..dd349b1
--- /dev/null
+++ b/starters/starter-secrets-manager/src/main/resources/META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports
@@ -0,0 +1 @@
+io.github.hantsy.awskotlin.secretsmanager.autoconfigure.SecretsManagerAutoConfiguration
\ No newline at end of file
diff --git a/starters/starter-secrets-manager/src/test/kotlin/io/github/hantsy/awskotlin/secretsmanager/autoconfigure/SecretsManagerAutoConfigurationTest.kt b/starters/starter-secrets-manager/src/test/kotlin/io/github/hantsy/awskotlin/secretsmanager/autoconfigure/SecretsManagerAutoConfigurationTest.kt
new file mode 100644
index 0000000..3a67ed7
--- /dev/null
+++ b/starters/starter-secrets-manager/src/test/kotlin/io/github/hantsy/awskotlin/secretsmanager/autoconfigure/SecretsManagerAutoConfigurationTest.kt
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
diff --git a/starters/starter-sns/build.gradle.kts b/starters/starter-sns/build.gradle.kts
new file mode 100644
index 0000000..303169a
--- /dev/null
+++ b/starters/starter-sns/build.gradle.kts
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
+base { archivesName.set("awskotlin-spring-starter-sns") }
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
diff --git a/starters/starter-sns/gradle.properties b/starters/starter-sns/gradle.properties
new file mode 100644
index 0000000..92b73fb
--- /dev/null
+++ b/starters/starter-sns/gradle.properties
@@ -0,0 +1,5 @@
+# Workaround for KGP 2.4.10 + Gradle 9.9.2: Gradle ships Kotlin 2.4.21 compiler classes
+# in its parent classloader, which breaks the Build Tools API path used by kapt stub
+# generation (NoSuchMethodError: ArgumentsToStrings.toArgumentStrings$default).
+# The legacy compiler runner avoids that path. Deprecated in KGP 2.4.x, removed in 2.5.0.
+kotlin.compiler.runViaBuildToolsApi=false
\ No newline at end of file
diff --git a/starters/starter-sns/src/main/kotlin/io/github/hantsy/awskotlin/sns/autoconfigure/SnsAutoConfiguration.kt b/starters/starter-sns/src/main/kotlin/io/github/hantsy/awskotlin/sns/autoconfigure/SnsAutoConfiguration.kt
new file mode 100644
index 0000000..f97253a
--- /dev/null
+++ b/starters/starter-sns/src/main/kotlin/io/github/hantsy/awskotlin/sns/autoconfigure/SnsAutoConfiguration.kt
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
diff --git a/starters/starter-sns/src/main/kotlin/io/github/hantsy/awskotlin/sns/autoconfigure/SnsProperties.kt b/starters/starter-sns/src/main/kotlin/io/github/hantsy/awskotlin/sns/autoconfigure/SnsProperties.kt
new file mode 100644
index 0000000..66553a9
--- /dev/null
+++ b/starters/starter-sns/src/main/kotlin/io/github/hantsy/awskotlin/sns/autoconfigure/SnsProperties.kt
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
diff --git a/starters/starter-sns/src/main/resources/META-INF/additional-spring-configuration-metadata.json b/starters/starter-sns/src/main/resources/META-INF/additional-spring-configuration-metadata.json
new file mode 100644
index 0000000..1af4578
--- /dev/null
+++ b/starters/starter-sns/src/main/resources/META-INF/additional-spring-configuration-metadata.json
@@ -0,0 +1,23 @@
+{
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
diff --git a/starters/starter-sns/src/main/resources/META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports b/starters/starter-sns/src/main/resources/META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports
new file mode 100644
index 0000000..0864c79
--- /dev/null
+++ b/starters/starter-sns/src/main/resources/META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports
@@ -0,0 +1 @@
+io.github.hantsy.awskotlin.sns.autoconfigure.SnsAutoConfiguration
\ No newline at end of file
diff --git a/starters/starter-sns/src/test/kotlin/io/github/hantsy/awskotlin/sns/autoconfigure/SnsAutoConfigurationTest.kt b/starters/starter-sns/src/test/kotlin/io/github/hantsy/awskotlin/sns/autoconfigure/SnsAutoConfigurationTest.kt
new file mode 100644
index 0000000..9ef494d
--- /dev/null
+++ b/starters/starter-sns/src/test/kotlin/io/github/hantsy/awskotlin/sns/autoconfigure/SnsAutoConfigurationTest.kt
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
