package io.etip.backend.infrastructure.aws

import aws.sdk.kotlin.runtime.auth.credentials.EnvironmentCredentialsProvider
import aws.sdk.kotlin.runtime.auth.credentials.StaticCredentialsProvider
import aws.sdk.kotlin.services.s3.S3Client
import aws.sdk.kotlin.services.s3.endpoints.DefaultS3EndpointProvider
import aws.sdk.kotlin.services.s3.endpoints.S3EndpointProvider
import aws.smithy.kotlin.runtime.auth.awscredentials.CachedCredentialsProvider
import aws.smithy.kotlin.runtime.auth.awscredentials.Credentials
import aws.smithy.kotlin.runtime.auth.awscredentials.CredentialsProvider
import aws.smithy.kotlin.runtime.auth.awscredentials.CredentialsProviderChain
import aws.smithy.kotlin.runtime.client.LogMode
import aws.smithy.kotlin.runtime.client.endpoints.Endpoint
import com.example.demo.internal.AwsProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AwsConfig() {

    @Bean
    fun awsCredentialsProvider(properties: AwsProperties): CredentialsProvider {
        val chain = CredentialsProviderChain(
            EnvironmentCredentialsProvider(),
            StaticCredentialsProvider(
                Credentials(
                    secretAccessKey = properties.secretAccessKey,
                    accessKeyId = properties.accessKeyId
                )
            )
        )

        return CachedCredentialsProvider(chain)
    }

    @Bean
    fun awsS3Client(awsCredentialsProvider: CredentialsProvider, properties: AwsProperties): S3Client {
        return S3Client {
            credentialsProvider = awsCredentialsProvider
            region = properties.region
            endpointProvider = properties.endpoint?.let { url ->
                S3EndpointProvider { Endpoint(url) }
            } ?: DefaultS3EndpointProvider()
            forcePathStyle = !properties.endpoint.isNullOrBlank()
            logMode = LogMode.LogRequestWithBody

        }
    }
}
