package com.example.demo.internal

import aws.sdk.kotlin.runtime.auth.credentials.EnvironmentCredentialsProvider
import aws.sdk.kotlin.runtime.auth.credentials.StaticCredentialsProvider
import aws.sdk.kotlin.services.s3.S3Client
import aws.sdk.kotlin.services.secretsmanager.SecretsManagerClient
import aws.sdk.kotlin.services.sqs.SqsClient
import aws.smithy.kotlin.runtime.auth.awscredentials.CachedCredentialsProvider
import aws.smithy.kotlin.runtime.auth.awscredentials.Credentials
import aws.smithy.kotlin.runtime.auth.awscredentials.CredentialsProvider
import aws.smithy.kotlin.runtime.auth.awscredentials.CredentialsProviderChain
import aws.smithy.kotlin.runtime.client.LogMode
import aws.smithy.kotlin.runtime.net.url.Url
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
                    accessKeyId = properties.accessKeyId,
                )
            )
        )

        return CachedCredentialsProvider(chain)
    }

    // see: https://github.com/awslabs/aws-sdk-kotlin/issues/842
    @Bean
    fun awsS3Client(awsCredentialsProvider: CredentialsProvider, properties: AwsProperties): S3Client {
        return S3Client {
            credentialsProvider = awsCredentialsProvider
            region = properties.region
//            endpointProvider = properties.endpoint?.let { url ->
//                S3EndpointProvider { Endpoint(url) }
//            } ?: DefaultS3EndpointProvider()
            endpointUrl = (properties.s3?.endpoint ?: properties.endpoint)?.let { Url.parse(it) }
            forcePathStyle = !properties.s3?.endpoint.isNullOrBlank()
            logMode = LogMode.LogRequestWithBody
        }
    }

    @Bean
    fun secretsManagerClient(
        awsCredentialsProvider: CredentialsProvider,
        properties: AwsProperties
    ): SecretsManagerClient {
        return SecretsManagerClient {
            credentialsProvider = awsCredentialsProvider
            region = properties.region
//            endpointProvider = properties.endpoint?.let { url ->
//                SecretsManagerEndpointProvider { Endpoint(url) }
//            } ?: DefaultSecretsManagerEndpointProvider()
            endpointUrl = (properties.secretsManager?.endpoint ?: properties.endpoint)?.let { Url.parse(it) }
            // there is no forcePathStyle for SecretsManagerClient
            // forcePathStyle = !properties.endpoint.isNullOrBlank()
            logMode = LogMode.LogRequestWithBody
        }
    }

    @Bean
    fun sqsClient(awsCredentialsProvider: CredentialsProvider, properties: AwsProperties): SqsClient {
        return SqsClient {
            credentialsProvider = awsCredentialsProvider
            region = properties.region
            endpointUrl = (properties.sqs?.endpoint ?: properties.endpoint)?.let { Url.parse(it) }
            // there is no forcePathStyle for SqsClient
            logMode = LogMode.LogRequestWithBody
        }
    }
}
