package com.example.demo

interface SecretsManagementService {
    suspend fun read(secretName: String): String?
    suspend fun write(secretName: String, secretValue: String, secretDescription: String?): String?
}

data class SecretsManagementServiceException(val error: String) : RuntimeException(error)
