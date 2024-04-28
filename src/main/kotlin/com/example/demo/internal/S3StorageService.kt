package com.example.demo.internal

import aws.sdk.kotlin.services.s3.S3Client
import com.example.demo.StorageService
import com.example.demo.StorageServiceException
import org.springframework.core.io.buffer.DataBuffer
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux

@Service
class S3StorageService(
    private val properties: AwsProperties,
    private val client: S3Client,
) : StorageService {
    val bucketName = properties.s3?.bucketName ?: DEFAULT_BUCKET_NAME

    override suspend fun store(resourceKey: String, data: Flux<DataBuffer>) {
        try {
            client.store(bucketName, resourceKey, data)
        } catch (e: S3ClientException) {
            throw StorageServiceException(e.message ?: "Failed to upload object $resourceKey")
        }
    }

    override suspend fun retrieve(resourceKey: String): Flux<DataBuffer>? {
        return try {
            client.retrieveAsFluxDataBuffer(bucketName, resourceKey)
        } catch (e: S3ClientException) {
            throw StorageServiceException(e.message ?: "Failed to download object $resourceKey")
        }
    }

    override suspend fun delete(resourceKey: String) {
        try {
            client.delete(bucketName, resourceKey)
        } catch (e: S3ClientException) {
            throw StorageServiceException(e.message ?: "Failed to delete object $resourceKey")
        }
    }
}
