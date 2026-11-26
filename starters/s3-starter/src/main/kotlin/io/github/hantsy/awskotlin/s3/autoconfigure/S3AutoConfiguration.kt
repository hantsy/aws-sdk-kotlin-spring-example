package io.github.hantsy.awskotlin.s3.autoconfigure

import aws.sdk.kotlin.services.s3.S3Client
import aws.smithy.kotlin.runtime.auth.awscredentials.CredentialsProvider
import aws.smithy.kotlin.runtime.net.url.Url
import io.github.hantsy.awskotlin.core.AwsKotlinProperties
import io.github.hantsy.awskotlin.core.autoconfigure.AwsKotlinAutoConfiguration
import io.github.hantsy.awskotlin.s3.S3Template
import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean

/**
 * Auto-configuration for the AWS SDK for Kotlin S3 client and [S3Template].
 *
 * Disabled by either `awskotlin.enabled=false` or `awskotlin.s3.enabled=false`
 * (both checked by the single property condition below). User-provided
 * [S3Client]/[S3Template] beans always win.
 */
@AutoConfiguration(after = [AwsKotlinAutoConfiguration::class])
@ConditionalOnClass(S3Client::class)
@ConditionalOnProperty(
    prefix = "awskotlin",
    name = ["enabled", "s3.enabled"],
    havingValue = "true",
    matchIfMissing = true,
)
@EnableConfigurationProperties(S3Properties::class)
class S3AutoConfiguration {
    @Bean
    @ConditionalOnMissingBean
    fun s3Client(
        properties: AwsKotlinProperties,
        s3Properties: S3Properties,
        credentialsProvider: CredentialsProvider,
    ): S3Client {
        val endpoint = s3Properties.endpoint ?: properties.endpoint
        return S3Client {
            region = s3Properties.region ?: properties.region
            this.credentialsProvider = credentialsProvider
            endpoint?.let { endpointUrl = Url.parse(it) }
            // Path-style addressing is the sensible default for endpoint overrides
            // (LocalStack, MinIO, ...) unless explicitly configured otherwise.
            forcePathStyle = s3Properties.pathStyleAccessEnabled ?: (endpoint != null)
        }
    }

    @Bean
    @ConditionalOnMissingBean
    fun s3Template(client: S3Client): S3Template = S3Template(client)
}
