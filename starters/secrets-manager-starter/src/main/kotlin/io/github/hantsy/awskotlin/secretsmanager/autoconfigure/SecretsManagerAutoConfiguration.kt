package io.github.hantsy.awskotlin.secretsmanager.autoconfigure

import aws.sdk.kotlin.services.secretsmanager.SecretsManagerClient
import aws.smithy.kotlin.runtime.auth.awscredentials.CredentialsProvider
import aws.smithy.kotlin.runtime.net.url.Url
import io.github.hantsy.awskotlin.core.AwsKotlinProperties
import io.github.hantsy.awskotlin.core.autoconfigure.AwsKotlinAutoConfiguration
import io.github.hantsy.awskotlin.secretsmanager.SecretsManagerTemplate
import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean

/**
 * Auto-configuration for the AWS SDK for Kotlin Secrets Manager client and [SecretsManagerTemplate].
 *
 * Disabled by either `awskotlin.enabled=false` or `awskotlin.secrets-manager.enabled=false`
 * (both checked by the single property condition below). User-provided
 * [SecretsManagerClient]/[SecretsManagerTemplate] beans always win.
 */
@AutoConfiguration(after = [AwsKotlinAutoConfiguration::class])
@ConditionalOnClass(SecretsManagerClient::class)
@ConditionalOnProperty(
    prefix = "awskotlin",
    name = ["enabled", "secrets-manager.enabled"],
    havingValue = "true",
    matchIfMissing = true,
)
@EnableConfigurationProperties(SecretsManagerProperties::class)
class SecretsManagerAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean
    fun secretsManagerClient(
        properties: AwsKotlinProperties,
        secretsManagerProperties: SecretsManagerProperties,
        credentialsProvider: CredentialsProvider,
    ): SecretsManagerClient {
        val endpoint = secretsManagerProperties.endpoint ?: properties.endpoint
        return SecretsManagerClient {
            region = secretsManagerProperties.region ?: properties.region
            this.credentialsProvider = credentialsProvider
            endpoint?.let { endpointUrl = Url.parse(it) }
        }
    }

    @Bean
    @ConditionalOnMissingBean
    fun secretsManagerTemplate(client: SecretsManagerClient): SecretsManagerTemplate = SecretsManagerTemplate(client)
}
