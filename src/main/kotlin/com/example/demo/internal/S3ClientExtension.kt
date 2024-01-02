package com.example.demo.internal

import aws.sdk.kotlin.services.s3.S3Client
import aws.sdk.kotlin.services.s3.model.*
import aws.smithy.kotlin.runtime.content.ByteStream
import aws.smithy.kotlin.runtime.content.toByteArray
import aws.smithy.kotlin.runtime.content.toByteStream
import aws.smithy.kotlin.runtime.content.toFlow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactor.asFlux
import org.springframework.core.io.buffer.DataBuffer
import org.springframework.core.io.buffer.DataBufferUtils
import org.springframework.core.io.buffer.DefaultDataBufferFactory
import org.springframework.http.MediaType
import org.springframework.http.MediaTypeFactory
import reactor.core.publisher.Flux

class S3ClientException(message: String) : RuntimeException(message)

private val applicationScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

suspend fun S3Client.delete(bucketName: String, resourceKey: String) {
    val request = DeleteObjectRequest {
        bucket = bucketName
        key = resourceKey
    }

    try {
        this.deleteObject(request)
    } catch (e: Exception) {
        throw S3ClientException(e.message ?: "Failed to delete object $resourceKey")
    }
}

suspend fun S3Client.bucketExists(s3bucket: String) = try {
    headBucket(HeadBucketRequest { bucket = s3bucket })
    true
} catch (e: Exception) {
    e.printStackTrace()
    false
}

suspend fun S3Client.createBucketIfNotExists(bucketName: String) {
    if (!this.bucketExists(bucketName)) {
        val request = CreateBucketRequest {
            bucket = bucketName
        }
        try {
            this.createBucket(request)
        } catch (e: Exception) {
            e.printStackTrace()
            throw S3ClientException(e.message ?: "Failed to create bucket $bucketName")
        }
    }
}

suspend fun S3Client.store(bucketName: String, resourceKey: String, data: ByteArray) {
    this.createBucketIfNotExists(bucketName)
    val mediaType = MediaTypeFactory.getMediaType(resourceKey)
        .orElseGet { MediaType.APPLICATION_OCTET_STREAM }

    val request = PutObjectRequest {
        bucket = bucketName
        body = ByteStream.fromBytes(data)
        key = resourceKey
        contentType = mediaType.toString()
    }
    val result = try {
        this.putObject(request)
    } catch (e: Exception) {
        throw S3ClientException(e.message ?: "Failed to store object $resourceKey")
    }
    println("store object to $bucketName: ${result.eTag}")
}

suspend fun S3Client.store(bucketName: String, resourceKey: String, data: Flux<DataBuffer>) {
    this.createBucketIfNotExists(bucketName)
    val mediaType = MediaTypeFactory.getMediaType(resourceKey)
        .orElseGet { MediaType.APPLICATION_OCTET_STREAM }

    val byteArrayFlow = data
        .map { dataBuffer ->
            val bytes = ByteArray(dataBuffer.readableByteCount())
            dataBuffer.read(bytes)
            DataBufferUtils.release(dataBuffer)
            bytes
        }
        .asFlow()

    val request = PutObjectRequest {
        bucket = bucketName
        body = byteArrayFlow.toByteStream(applicationScope)
        key = resourceKey
        contentType = mediaType.toString()
    }
    val result = try {
        this.putObject(request)
    } catch (e: Exception) {
        throw S3ClientException(e.message ?: "Failed to store object $resourceKey")
    }
    println("store object to $bucketName: ${result.eTag}")
}

suspend fun S3Client.retrieve(bucketName: String, resourceKey: String): ByteArray? {
    val request = GetObjectRequest {
        bucket = bucketName
        key = resourceKey
    }

    return try {
        this.getObject(request) { result ->
            val body = result.body ?: return@getObject null
            val bytes = body.toByteArray()

            bytes
        }
    } catch (e: Exception) {
        throw S3ClientException(e.message ?: "Failed to retrieve object $resourceKey")
    }
}

suspend fun S3Client.retrieveAsFluxDataBuffer(bucketName: String, resourceKey: String): Flux<DataBuffer> {
    val request = GetObjectRequest {
        bucket = bucketName
        key = resourceKey
    }

    return try {
        this.getObject(request) { result ->
            val body = result.body ?: return@getObject Flux.empty()
            body.toFlow()
                .map {
                    DefaultDataBufferFactory().wrap(it)
                }
                .asFlux()
        }
    } catch (e: Exception) {
        throw S3ClientException(e.message ?: "Failed to retrieve object $resourceKey")
    }
}
