package io.github.hantsy.awskotlin.s3

import aws.sdk.kotlin.runtime.auth.credentials.StaticCredentialsProvider
import aws.sdk.kotlin.services.s3.S3Client
import aws.sdk.kotlin.services.s3.createBucket
import aws.sdk.kotlin.services.s3.deleteBucket
import aws.smithy.kotlin.runtime.content.ByteStream
import aws.smithy.kotlin.runtime.net.url.Url
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assumptions.assumeTrue
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.testcontainers.DockerClientFactory
import org.testcontainers.localstack.LocalStackContainer
import org.testcontainers.utility.DockerImageName
import java.util.UUID

/**
 * End-to-end round trip against LocalStack.
 *
 * Skipped unless Docker is available and LOCALSTACK_AUTH_TOKEN is set
 * (LocalStack 4 requires a license token).
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class S3TemplateLocalStackTest {
    private lateinit var container: LocalStackContainer
    private lateinit var client: S3Client
    private lateinit var template: S3Template
    private val bucketName = "awskotlin-s3-test-${UUID.randomUUID().toString().substring(0, 8)}"

    @BeforeAll
    fun startLocalStack() {
        val authToken = System.getenv("LOCALSTACK_AUTH_TOKEN")?.takeIf { it.isNotBlank() }
        assumeTrue(authToken != null, "LOCALSTACK_AUTH_TOKEN is not set; skipping LocalStack tests")
        assumeTrue(
            DockerClientFactory.instance().isDockerAvailable,
            "Docker is not available; skipping LocalStack tests"
        )

        container =
            LocalStackContainer(DockerImageName.parse("localstack/localstack:4"))
                .withServices("s3")
                .withEnv("LOCALSTACK_AUTH_TOKEN", authToken)
        container.start()

        client =
            S3Client {
                region = "us-east-1"
                credentialsProvider =
                    StaticCredentialsProvider {
                        accessKeyId = "test"
                        secretAccessKey = "test"
                    }
                endpointUrl = Url.parse("http://${container.host}:${container.firstMappedPort}")
                forcePathStyle = true
            }
        template = S3Template(client)

        runBlocking {
            client.createBucket { bucket = bucketName }
        }
    }

    @AfterAll
    fun stopLocalStack() {
        if (::client.isInitialized) {
            runBlocking { runCatching { client.deleteBucket { bucket = bucketName } } }
            client.close()
        }
        if (::container.isInitialized) {
            container.stop()
        }
    }

    @Test
    fun `put get delete list exists round trip`() = runBlocking<Unit> {
        val bucket = template.bucket(bucketName)
        bucket.exists() shouldBe true

        val key = "docs/greeting.txt"
        val bytes = "hello localstack".encodeToByteArray()
        bucket.`object`(key).put(ByteStream.fromBytes(bytes), contentType = "text/plain")

        bucket.`object`(key).get() shouldBe bytes

        val listed = bucket.listObjects(prefix = "docs/")
        listed.contents.shouldNotBeNull().map { it.key } shouldContainExactly listOf(key)

        bucket.`object`(key).delete()

        val afterDelete = bucket.listObjects(prefix = "docs/")
        (afterDelete.contents ?: emptyList()) shouldBe emptyList()
    }

    @Test
    fun `exists returns false for a missing bucket`() = runBlocking<Unit> {
        template.bucket("awskotlin-missing-${UUID.randomUUID().toString().substring(0, 8)}").exists() shouldBe false
    }

    @Test
    fun `get returns the exact bytes stored`() = runBlocking<Unit> {
        val key = "binary/data.bin"
        val bytes = ByteArray(256) { it.toByte() }
        val objectOps = template.bucket(bucketName).`object`(key)
        objectOps.put(ByteStream.fromBytes(bytes))

        objectOps.get() shouldBe bytes

        objectOps.delete()
    }
}
