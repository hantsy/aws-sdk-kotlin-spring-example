package com.example.demo

import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.core.io.ByteArrayResource
import org.springframework.core.io.buffer.DataBuffer
import org.springframework.core.io.buffer.DataBufferUtils
import org.springframework.core.io.buffer.DefaultDataBufferFactory
import reactor.core.publisher.Flux


fun ByteArray.toFluxDataBuffer(): Flux<DataBuffer> =
    DataBufferUtils.read(ByteArrayResource(this), DefaultDataBufferFactory(), 4096)

suspend fun Flux<DataBuffer>.toByteArray(): ByteArray {
    return DataBufferUtils.join(this)
        .map { dataBuffer ->
            val bytes = ByteArray(dataBuffer.readableByteCount())
            dataBuffer.read(bytes)
            DataBufferUtils.release(dataBuffer)
            bytes
        }
        .awaitSingle()
}