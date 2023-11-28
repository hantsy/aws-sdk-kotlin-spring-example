package com.example.demo

interface MessagingService {
    suspend fun send(queueNameVal: String, message: Any)

    suspend fun <T : Any> receive(queueNameVal: String, clazz: Class<out T>): List<T>
    suspend fun createQueue(queueNameVal: String): String
    suspend fun getQueue(queueNameVal: String): String?
}

suspend inline fun <reified T : Any> MessagingService.receive(queueNameVal: String): List<T> =
    this.receive(queueNameVal, T::class.java)

class MessagingServiceException(val msg: String) : RuntimeException(msg)
