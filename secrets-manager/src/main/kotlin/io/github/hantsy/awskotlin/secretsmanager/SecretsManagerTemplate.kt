package io.github.hantsy.awskotlin.secretsmanager

import aws.sdk.kotlin.services.secretsmanager.SecretsManagerClient

/**
 * Entry point for fluent Secrets Manager operations backed by the AWS SDK for Kotlin
 * [SecretsManagerClient].
 *
 * Obtain secret-scoped operations with [secret].
 */
class SecretsManagerTemplate(private val client: SecretsManagerClient) {
    /** Returns operations scoped to the secret [secretId] (name or ARN). No request is made. */
    fun secret(secretId: String): SecretOperations = SecretOperations(client, secretId)
}
