package io.github.hantsy.awskotlin.s3

import aws.sdk.kotlin.services.s3.S3Client
import aws.sdk.kotlin.services.s3.model.DeleteObjectRequest
import aws.sdk.kotlin.services.s3.model.DeleteObjectResponse
import aws.sdk.kotlin.services.s3.model.GetObjectRequest
import aws.sdk.kotlin.services.s3.model.GetObjectResponse
import aws.sdk.kotlin.services.s3.model.HeadBucketRequest
import aws.sdk.kotlin.services.s3.model.HeadBucketResponse
import aws.sdk.kotlin.services.s3.model.ListObjectsV2Request
import aws.sdk.kotlin.services.s3.model.ListObjectsV2Response
import aws.sdk.kotlin.services.s3.model.NotFound
import aws.sdk.kotlin.services.s3.model.PutObjectRequest
import aws.sdk.kotlin.services.s3.model.PutObjectResponse
import aws.sdk.kotlin.services.s3.model.S3Exception
import aws.smithy.kotlin.runtime.content.ByteStream
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.slot
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import aws.sdk.kotlin.services.s3.model.Object as S3Object

class S3TemplateTest {
    private val client = mockk<S3Client>()
    private val template = S3Template(client)

    @Test
    fun `bucket exists returns true on successful headBucket`() = runTest {
        val request = slot<HeadBucketRequest>()
        coEvery { client.headBucket(capture(request)) } returns HeadBucketResponse { }

        template.bucket("test-bucket").exists() shouldBe true
        request.captured.bucket shouldBe "test-bucket"
    }

    @Test
    fun `bucket exists returns false on NotFound`() = runTest {
        coEvery { client.headBucket(any<HeadBucketRequest>()) } throws NotFound { }

        template.bucket("missing-bucket").exists() shouldBe false
    }

    @Test
    fun `bucket exists propagates other SDK exceptions`() = runTest {
        val failure = S3Exception("access denied")
        coEvery { client.headBucket(any<HeadBucketRequest>()) } throws failure

        val thrown = shouldThrow<S3Exception> { template.bucket("test-bucket").exists() }
        thrown shouldBe failure
    }

    @Test
    fun `listObjects returns the SDK response and passes prefix and maxKeys`() = runTest {
        val request = slot<ListObjectsV2Request>()
        val response =
            ListObjectsV2Response {
                contents = listOf(S3Object { key = "docs/readme.txt" })
            }
        coEvery { client.listObjectsV2(capture(request)) } returns response

        val result = template.bucket("test-bucket").listObjects(prefix = "docs/", maxKeys = 10)

        result shouldBe response
        request.captured.bucket shouldBe "test-bucket"
        request.captured.prefix shouldBe "docs/"
        request.captured.maxKeys shouldBe 10
    }

    @Test
    fun `listObjects without arguments leaves prefix and maxKeys unset`() = runTest {
        val request = slot<ListObjectsV2Request>()
        coEvery { client.listObjectsV2(capture(request)) } returns ListObjectsV2Response { }

        template.bucket("test-bucket").listObjects()

        request.captured.bucket shouldBe "test-bucket"
        request.captured.prefix.shouldBeNull()
        request.captured.maxKeys.shouldBeNull()
    }

    @Test
    fun `put builds PutObjectRequest with bucket key body and contentType`() = runTest {
        val request = slot<PutObjectRequest>()
        val response = PutObjectResponse { eTag = "etag-1" }
        coEvery { client.putObject(capture(request)) } returns response
        val body = ByteStream.fromBytes("hello".encodeToByteArray())

        val result = template.bucket("test-bucket").`object`("greeting.txt").put(body, contentType = "text/plain")

        result shouldBe response
        request.captured.bucket shouldBe "test-bucket"
        request.captured.key shouldBe "greeting.txt"
        request.captured.body shouldBe body
        request.captured.contentType shouldBe "text/plain"
    }

    @Test
    fun `put without contentType leaves it unset`() = runTest {
        val request = slot<PutObjectRequest>()
        coEvery { client.putObject(capture(request)) } returns PutObjectResponse { }

        template.bucket("test-bucket").`object`("greeting.txt").put(ByteStream.fromBytes(byteArrayOf()))

        request.captured.contentType.shouldBeNull()
    }

    @Test
    fun `delete builds DeleteObjectRequest with bucket and key`() = runTest {
        val request = slot<DeleteObjectRequest>()
        val response = DeleteObjectResponse { }
        coEvery { client.deleteObject(capture(request)) } returns response

        val result = template.bucket("test-bucket").`object`("greeting.txt").delete()

        result shouldBe response
        request.captured.bucket shouldBe "test-bucket"
        request.captured.key shouldBe "greeting.txt"
    }

    @Test
    fun `get returns bytes consumed inside the response transform`() = runTest {
        val bytes = "hello world".encodeToByteArray()
        val request = slot<GetObjectRequest>()
        coEvery {
            client.getObject(capture(request), any<suspend (GetObjectResponse) -> ByteArray?>())
        } coAnswers {
            val transform = secondArg<suspend (GetObjectResponse) -> ByteArray?>()
            transform(GetObjectResponse { body = ByteStream.fromBytes(bytes) })
        }

        val result = template.bucket("test-bucket").`object`("greeting.txt").get()

        result shouldBe bytes
        request.captured.bucket shouldBe "test-bucket"
        request.captured.key shouldBe "greeting.txt"
    }

    @Test
    fun `get returns null when the response has no body`() = runTest {
        coEvery {
            client.getObject(any<GetObjectRequest>(), any<suspend (GetObjectResponse) -> ByteArray?>())
        } coAnswers {
            val transform = secondArg<suspend (GetObjectResponse) -> ByteArray?>()
            transform(GetObjectResponse { })
        }

        template.bucket("test-bucket").`object`("greeting.txt").get().shouldBeNull()
    }
}
