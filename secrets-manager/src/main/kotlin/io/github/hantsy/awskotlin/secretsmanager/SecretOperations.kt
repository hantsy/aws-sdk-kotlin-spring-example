package io.github.hantsy.awskotlin.secretsmanager

import aws.sdk.kotlin.services.secretsmanager.SecretsManagerClient
import aws.sdk.kotlin.services.secretsmanager.createSecret
import aws.sdk.kotlin.services.secretsmanager.deleteSecret
import aws.sdk.kotlin.services.secretsmanager.getSecretValue
import aws.sdk.kotlin.services.secretsmanager.model.CreateSecretResponse
import aws.sdk.kotlin.services.secretsmanager.model.DeleteSecretResponse
import aws.sdk.kotlin.services.secretsmanager.model.GetSecretValueResponse
import aws.sdk.kotlin.services.secretsmanager.model.UpdateSecretResponse
import aws.sdk.kotlin.services.secretsmanager.updateSecret

/**
 * Secret-scoped Secrets Manager operations. Created via [SecretsManagerTemplate.secret].
 *
 * Each method maps directly to the matching SDK request and propagates SDK exceptions
 * unchanged.
 */
class SecretOperations internal constructor(private val client: SecretsManagerClient, private val secretId: String,) {
    /** Retrieves the secret value. */
    suspend fun get(): GetSecretValueResponse = client.getSecretValue {
        secretId = this@SecretOperations.secretId
    }

    /** Creates a new secret named [secretId] with the given [secretValue]. */
    suspend fun create(secretValue: String): CreateSecretResponse = client.createSecret {
        name = this@SecretOperations.secretId
        secretString = secretValue
    }

    /** Updates the value of the existing secret [secretId]. */
    suspend fun update(secretValue: String): UpdateSecretResponse = client.updateSecret {
        secretId = this@SecretOperations.secretId
        secretString = secretValue
    }

    /**
     * Deletes the secret [secretId]. When [forceDeleteWithoutRecovery] is `true` the secret
     * is deleted immediately without a recovery window; otherwise the SDK default applies.
     */
    suspend fun delete(forceDeleteWithoutRecovery: Boolean = false): DeleteSecretResponse = client.deleteSecret {
        secretId = this@SecretOperations.secretId
        this.forceDeleteWithoutRecovery = forceDeleteWithoutRecovery
    }
}
