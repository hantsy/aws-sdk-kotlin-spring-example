package com.example.demo.internal

import aws.sdk.kotlin.services.s3.S3Client
import aws.sdk.kotlin.services.s3.model.CreateBucketRequest
import aws.sdk.kotlin.services.s3.model.DeleteObjectRequest
import aws.sdk.kotlin.services.s3.model.GetObjectRequest
import aws.sdk.kotlin.services.s3.model.HeadBucketRequest
import aws.sdk.kotlin.services.s3.model.PutObjectRequest
import aws.smithy.kotlin.runtime.content.ByteStream
import aws.smithy.kotlin.runtime.content.toByteArray
import aws.smithy.kotlin.runtime.content.toFlow
import com.example.demo.toByteArray
import com.example.demo.toFluxDataBuffer
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.reactor.asFlux
import org.springframework.core.io.buffer.DataBuffer
import org.springframework.core.io.buffer.DataBufferFactory
import org.springframework.core.io.buffer.DefaultDataBufferFactory
import org.springframework.http.MediaType
import org.springframework.http.MediaTypeFactory
import reactor.core.publisher.Flux
import reactor.kotlin.core.publisher.toFlux

class S3ClientException(message: String) : RuntimeException(message)

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
    this.store(bucketName, resourceKey, data.toByteArray())
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
                .map{
                    DefaultDataBufferFactory().wrap(it)
                }
                .asFlux()
        }
    } catch (e: Exception) {
        throw S3ClientException(e.message ?: "Failed to retrieve object $resourceKey")
    }
}
