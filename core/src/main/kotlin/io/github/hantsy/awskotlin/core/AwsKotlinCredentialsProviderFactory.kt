package io.github.hantsy.awskotlin.core

import aws.sdk.kotlin.runtime.auth.credentials.DefaultChainCredentialsProvider
import aws.sdk.kotlin.runtime.auth.credentials.ProfileCredentialsProvider
import aws.sdk.kotlin.runtime.auth.credentials.StaticCredentialsProvider
import aws.smithy.kotlin.runtime.auth.awscredentials.CredentialsProvider

/**
 * Creates an AWS SDK for Kotlin [CredentialsProvider] from [AwsKotlinProperties].
 *
 * Resolution order:
 * 1. Static credentials when either key is configured (both keys are then required).
 * 2. Profile credentials when [AwsKotlinProperties.profileName] is configured.
 * 3. The SDK [DefaultChainCredentialsProvider] otherwise.
 */
object AwsKotlinCredentialsProviderFactory {
    fun create(properties: AwsKotlinProperties): CredentialsProvider {
        val accessKeyId = properties.accessKeyId
        val secretAccessKey = properties.secretAccessKey
        if (accessKeyId != null || secretAccessKey != null) {
            require(accessKeyId != null && secretAccessKey != null) {
                "awskotlin.access-key-id and awskotlin.secret-access-key must both be configured for static credentials"
            }
            return StaticCredentialsProvider {
                this.accessKeyId = accessKeyId
                this.secretAccessKey = secretAccessKey
            }
        }
        val profileName = properties.profileName
        if (profileName != null) {
            return ProfileCredentialsProvider(profileName = profileName, region = properties.region)
        }
        return DefaultChainCredentialsProvider(region = properties.region)
    }
}
