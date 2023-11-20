package com.example.demo

import org.springframework.core.io.buffer.DataBuffer
import reactor.core.publisher.Flux

interface StorageService {
    suspend fun store(resourceKey: String, data: Flux<DataBuffer>)
    suspend fun retrieve(resourceKey: String): Flux<DataBuffer>?
    suspend fun delete(resourceKey: String)
}

data class StorageServiceException(val error: String) : RuntimeException(error)
