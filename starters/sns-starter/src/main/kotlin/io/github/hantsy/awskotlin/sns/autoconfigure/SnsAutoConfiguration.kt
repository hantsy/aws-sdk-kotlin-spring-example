package io.github.hantsy.awskotlin.sns.autoconfigure

import aws.sdk.kotlin.services.sns.SnsClient
import aws.smithy.kotlin.runtime.auth.awscredentials.CredentialsProvider
import aws.smithy.kotlin.runtime.net.url.Url
import io.github.hantsy.awskotlin.core.AwsKotlinProperties
import io.github.hantsy.awskotlin.core.autoconfigure.AwsKotlinAutoConfiguration
import io.github.hantsy.awskotlin.sns.SnsTemplate
import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean

/**
 * Auto-configuration for the AWS SDK for Kotlin SNS client and [SnsTemplate].
 *
 * Disabled by either `awskotlin.enabled=false` or `awskotlin.sns.enabled=false`
 * (both checked by the single property condition below). User-provided
 * [SnsClient]/[SnsTemplate] beans always win.
 */
@AutoConfiguration(after = [AwsKotlinAutoConfiguration::class])
@ConditionalOnClass(SnsClient::class)
@ConditionalOnProperty(
    prefix = "awskotlin",
    name = ["enabled", "sns.enabled"],
    havingValue = "true",
    matchIfMissing = true,
)
@EnableConfigurationProperties(SnsProperties::class)
class SnsAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean
    fun snsClient(
        properties: AwsKotlinProperties,
        snsProperties: SnsProperties,
        credentialsProvider: CredentialsProvider,
    ): SnsClient {
        val endpoint = snsProperties.endpoint ?: properties.endpoint
        return SnsClient {
            region = snsProperties.region ?: properties.region
            this.credentialsProvider = credentialsProvider
            endpoint?.let { endpointUrl = Url.parse(it) }
        }
    }

    @Bean
    @ConditionalOnMissingBean
    fun snsTemplate(client: SnsClient): SnsTemplate = SnsTemplate(client)
}
