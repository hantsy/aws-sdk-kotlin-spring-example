package io.github.hantsy.awskotlin.core.autoconfigure

import aws.smithy.kotlin.runtime.auth.awscredentials.CredentialsProvider
import io.github.hantsy.awskotlin.core.AwsKotlinCredentialsProviderFactory
import io.github.hantsy.awskotlin.core.AwsKotlinProperties
import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean

/**
 * Auto-configuration shared by all AWS SDK for Kotlin Spring modules.
 *
 * Provides a [CredentialsProvider] backed by the SDK default chain unless overrides are
 * configured via [AwsKotlinProperties]. A user-provided [CredentialsProvider] bean always wins.
 */
@AutoConfiguration
@ConditionalOnClass(CredentialsProvider::class)
@ConditionalOnProperty(prefix = "awskotlin", name = ["enabled"], havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties(AwsKotlinProperties::class)
class AwsKotlinAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean(CredentialsProvider::class)
    fun awsKotlinCredentialsProvider(properties: AwsKotlinProperties): CredentialsProvider =
        AwsKotlinCredentialsProviderFactory.create(properties)
}
