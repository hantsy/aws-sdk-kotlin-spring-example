package io.github.hantsy.awskotlin.s3

import aws.sdk.kotlin.services.s3.S3Client

/**
 * Entry point for fluent S3 operations backed by the AWS SDK for Kotlin [S3Client].
 *
 * Obtain bucket-scoped operations with [bucket], then object-scoped operations
 * with [S3BucketOperations.object].
 */
class S3Template(private val client: S3Client,) {
    /** Returns operations scoped to [bucket]. No request is made. */
    fun bucket(bucket: String): S3BucketOperations = S3BucketOperations(client, bucket)
}
