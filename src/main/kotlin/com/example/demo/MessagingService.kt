package com.example.demo

interface MessagingService {
    suspend fun send(queueNameVal: String, message: Any)

    suspend fun <T : Any> receive(queueNameVal: String, clazz: Class<out T>): List<T>
    suspend fun createQueue(queueNameVal: String): String
    suspend fun getQueue(queueNameVal: String): String?
}

class MessagingServiceException(val msg: String) : RuntimeException(msg)
