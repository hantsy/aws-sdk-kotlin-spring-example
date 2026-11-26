package io.github.hantsy.awskotlin.s3

import aws.sdk.kotlin.services.s3.S3Client
import aws.sdk.kotlin.services.s3.deleteObject
import aws.sdk.kotlin.services.s3.model.DeleteObjectResponse
import aws.sdk.kotlin.services.s3.model.GetObjectRequest
import aws.sdk.kotlin.services.s3.model.PutObjectResponse
import aws.sdk.kotlin.services.s3.putObject
import aws.smithy.kotlin.runtime.content.ByteStream
import aws.smithy.kotlin.runtime.content.toByteArray

/**
 * Object-scoped S3 operations. Created via [S3BucketOperations.object].
 */
class S3ObjectOperations internal constructor(
    private val client: S3Client,
    private val bucket: String,
    private val key: String,
) {
    /** Uploads [body] as the object content, optionally setting the content type. */
    suspend fun put(body: ByteStream, contentType: String? = null,): PutObjectResponse = client.putObject {
        bucket = this@S3ObjectOperations.bucket
        key = this@S3ObjectOperations.key
        this.body = body
        this.contentType = contentType
    }

    /**
     * Downloads the object content, or returns `null` when the response has no body.
     *
     * The streaming body is consumed inside the SDK response-transform overload so the
     * underlying HTTP connection is released before this method returns.
     */
    suspend fun get(): ByteArray? = client.getObject(
        GetObjectRequest {
            bucket = this@S3ObjectOperations.bucket
            key = this@S3ObjectOperations.key
        },
    ) { it.body?.toByteArray() }

    /** Deletes the object. */
    suspend fun delete(): DeleteObjectResponse = client.deleteObject {
        bucket = this@S3ObjectOperations.bucket
        key = this@S3ObjectOperations.key
    }
}
