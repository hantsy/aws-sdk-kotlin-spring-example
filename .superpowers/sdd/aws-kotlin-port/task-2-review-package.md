# Task 2 review package

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
 M settings.gradle.kts
 A starters/starter-s3/build.gradle.kts
 A starters/starter-s3/gradle.properties
 A starters/starter-s3/src/main/kotlin/io/github/hantsy/awskotlin/s3/autoconfigure/S3AutoConfiguration.kt
 A starters/starter-s3/src/main/kotlin/io/github/hantsy/awskotlin/s3/autoconfigure/S3Properties.kt
 A starters/starter-s3/src/main/resources/META-INF/additional-spring-configuration-metadata.json
 A starters/starter-s3/src/main/resources/META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports
 A starters/starter-s3/src/test/kotlin/io/github/hantsy/awskotlin/s3/autoconfigure/S3AutoConfigurationTest.kt
?? .kotlin/
?? .superpowers/

## Diff stat
 s3/build.gradle.kts                                |  57 ++++++++
 s3/gradle.properties                               |   5 +
 .../hantsy/awskotlin/s3/S3BucketOperations.kt      |  38 +++++
 .../hantsy/awskotlin/s3/S3ObjectOperations.kt      |  46 +++++++
 .../io/github/hantsy/awskotlin/s3/S3Template.kt    |  14 ++
 .../awskotlin/s3/S3TemplateLocalStackTest.kt       | 116 ++++++++++++++++
 .../github/hantsy/awskotlin/s3/S3TemplateTest.kt   | 153 +++++++++++++++++++++
 settings.gradle.kts                                |   3 +
 starters/starter-s3/build.gradle.kts               |  74 ++++++++++
 starters/starter-s3/gradle.properties              |   5 +
 .../s3/autoconfigure/S3AutoConfiguration.kt        |  54 ++++++++
 .../awskotlin/s3/autoconfigure/S3Properties.kt     |  17 +++
 .../additional-spring-configuration-metadata.json  |  29 ++++
 ...rk.boot.autoconfigure.AutoConfiguration.imports |   1 +
 .../s3/autoconfigure/S3AutoConfigurationTest.kt    | 131 ++++++++++++++++++
 15 files changed, 743 insertions(+)

## Full diff
diff --git a/s3/build.gradle.kts b/s3/build.gradle.kts
new file mode 100644
index 0000000..f737844
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
+base { archivesName.set("awskotlin-spring-s3") }
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
diff --git a/s3/gradle.properties b/s3/gradle.properties
new file mode 100644
index 0000000..92b73fb
--- /dev/null
+++ b/s3/gradle.properties
@@ -0,0 +1,5 @@
+# Workaround for KGP 2.4.10 + Gradle 9.9.2: Gradle ships Kotlin 2.4.21 compiler classes
+# in its parent classloader, which breaks the Build Tools API path used by kapt stub
+# generation (NoSuchMethodError: ArgumentsToStrings.toArgumentStrings$default).
+# The legacy compiler runner avoids that path. Deprecated in KGP 2.4.x, removed in 2.5.0.
+kotlin.compiler.runViaBuildToolsApi=false
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
diff --git a/settings.gradle.kts b/settings.gradle.kts
index a2d0cf9..eae13a4 100644
--- a/settings.gradle.kts
+++ b/settings.gradle.kts
@@ -11,10 +11,13 @@ pluginManagement {
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
+include("starters:starter-s3")
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
