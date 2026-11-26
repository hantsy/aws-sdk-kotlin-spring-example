package io.github.hantsy.awskotlin.s3

import aws.sdk.kotlin.services.s3.S3Client
import aws.sdk.kotlin.services.s3.headBucket
import aws.sdk.kotlin.services.s3.listObjectsV2
import aws.sdk.kotlin.services.s3.model.ListObjectsV2Response
import aws.sdk.kotlin.services.s3.model.NotFound

/**
 * Bucket-scoped S3 operations. Created via [S3Template.bucket].
 */
class S3BucketOperations internal constructor(private val client: S3Client, private val bucket: String,) {
    /**
     * Returns `true` when the bucket exists and is accessible.
     *
     * Only [NotFound] (the SDK's modeled HeadBucket 404 error) maps to `false`;
     * every other SDK exception propagates to the caller.
     */
    suspend fun exists(): Boolean = try {
        client.headBucket { bucket = this@S3BucketOperations.bucket }
        true
    } catch (e: NotFound) {
        false
    }

    /**
     * Lists objects in the bucket, returning a single SDK page (no pagination).
     */
    suspend fun listObjects(prefix: String? = null, maxKeys: Int? = null,): ListObjectsV2Response =
        client.listObjectsV2 {
            bucket = this@S3BucketOperations.bucket
            this.prefix = prefix
            this.maxKeys = maxKeys
        }

    /** Returns operations scoped to the object [key] in this bucket. No request is made. */
    fun `object`(key: String): S3ObjectOperations = S3ObjectOperations(client, bucket, key)
}
