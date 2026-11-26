package com.example.demo.internal

import aws.sdk.kotlin.services.s3.S3Client
import aws.sdk.kotlin.services.s3.createBucket
import aws.smithy.kotlin.runtime.content.ByteStream
import com.example.demo.StorageService
import com.example.demo.StorageServiceException
import com.example.demo.toByteArray
import com.example.demo.toFluxDataBuffer
import io.github.hantsy.awskotlin.s3.S3Template
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.buffer.DataBuffer
import org.springframework.http.MediaType
import org.springframework.http.MediaTypeFactory
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux

@Service
class S3StorageService(
    private val template: S3Template,
    private val client: S3Client,
    @Value("\${example.s3.bucket-name:uploaded-files}") private val bucketName: String,
) : StorageService {
    override suspend fun store(resourceKey: String, data: Flux<DataBuffer>) {
        try {
            // The template has no bucket creation, so use the starter-provided client for it.
            if (!template.bucket(bucketName).exists()) {
                client.createBucket { bucket = bucketName }
            }
            val contentType = MediaTypeFactory.getMediaType(resourceKey)
                .orElseGet { MediaType.APPLICATION_OCTET_STREAM }
                .toString()
            template.bucket(bucketName).`object`(resourceKey)
                .put(ByteStream.fromBytes(data.toByteArray()), contentType = contentType)
        } catch (e: Exception) {
            throw StorageServiceException(e.message ?: "Failed to upload object $resourceKey")
        }
    }

    override suspend fun retrieve(resourceKey: String): Flux<DataBuffer>? = try {
        template.bucket(bucketName).`object`(resourceKey).get()?.toFluxDataBuffer() ?: Flux.empty()
    } catch (e: Exception) {
        // Keep the SDK message (for example "The specified key does not exist") intact.
        throw StorageServiceException(e.message ?: "Failed to download object $resourceKey")
    }

    override suspend fun delete(resourceKey: String) {
        try {
            template.bucket(bucketName).`object`(resourceKey).delete()
        } catch (e: Exception) {
            throw StorageServiceException(e.message ?: "Failed to delete object $resourceKey")
        }
    }
}
