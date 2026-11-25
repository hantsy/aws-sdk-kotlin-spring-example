package com.example.demo.internal

import aws.sdk.kotlin.services.secretsmanager.SecretsManagerClient
import aws.sdk.kotlin.services.secretsmanager.model.CreateSecretRequest
import aws.sdk.kotlin.services.secretsmanager.model.GetSecretValueRequest
import com.example.demo.SecretsManagementService
import com.example.demo.SecretsManagementServiceException
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class AwsSecretsManagementService(private val client: SecretsManagerClient) : SecretsManagementService {
    companion object {
        private val log = LoggerFactory.getLogger(SecretsManagementService::class.java)
    }

    override suspend fun write(secretName: String, secretValue: String, secretDescription: String?): String? {
        val request = CreateSecretRequest {
            name = secretName
            description = secretDescription
            secretString = secretValue
            forceOverwriteReplicaSecret = true // override the duplicated secret name
        }

        log.debug("writing secrets: $secretName, $secretValue, $secretDescription")
        val response = try {
            client.createSecret(request)
        } catch (e: Exception) {
            log.error("error occurred when writing secrets: ${e.message}")
            throw SecretsManagementServiceException(e.message ?: "Failed to write secrets:")
        }
        return response.arn
    }

    override suspend fun read(secretName: String): String? {
        val valueRequest = GetSecretValueRequest {
            secretId = secretName
        }

        log.debug("read secrets by name: $secretName")
        val response = try {
            client.getSecretValue(valueRequest)
        } catch (e: Exception) {
            log.error("error occurred when reading secrets: ${e.message}")
            throw SecretsManagementServiceException(e.message ?: "Failed to read secrets")
        }
        return response.secretString
    }
}
