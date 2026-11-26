package io.github.hantsy.awskotlin.secretsmanager.autoconfigure

import org.springframework.boot.context.properties.ConfigurationProperties

/**
 * Configuration properties for the Secrets Manager module.
 *
 * Unset values fall back to the shared `awskotlin.*` properties and then to the
 * SDK default chains.
 */
@ConfigurationProperties(prefix = "awskotlin.secrets-manager")
data class SecretsManagerProperties(
    val enabled: Boolean = true,
    val endpoint: String? = null,
    val region: String? = null,
)
