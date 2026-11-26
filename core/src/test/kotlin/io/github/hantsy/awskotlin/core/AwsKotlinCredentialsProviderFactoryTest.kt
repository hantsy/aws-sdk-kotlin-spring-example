package io.github.hantsy.awskotlin.core

import aws.sdk.kotlin.runtime.auth.credentials.DefaultChainCredentialsProvider
import aws.sdk.kotlin.runtime.auth.credentials.ProfileCredentialsProvider
import aws.sdk.kotlin.runtime.auth.credentials.StaticCredentialsProvider
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class AwsKotlinCredentialsProviderFactoryTest {
    @Test
    fun `static credentials require both access key id and secret access key`() {
        shouldThrow<IllegalArgumentException> {
            AwsKotlinCredentialsProviderFactory.create(AwsKotlinProperties(accessKeyId = "test-access-key"))
        }
        shouldThrow<IllegalArgumentException> {
            AwsKotlinCredentialsProviderFactory.create(AwsKotlinProperties(secretAccessKey = "test-secret-key"))
        }
    }

    @Test
    fun `static credentials produce StaticCredentialsProvider when both keys are present`() {
        val provider =
            AwsKotlinCredentialsProviderFactory.create(
                AwsKotlinProperties(accessKeyId = "test-access-key", secretAccessKey = "test-secret-key"),
            )
        provider.shouldBeInstanceOf<StaticCredentialsProvider>()
        val credentials = runBlocking { provider.resolve() }
        credentials.accessKeyId shouldBe "test-access-key"
        credentials.secretAccessKey shouldBe "test-secret-key"
    }

    @Test
    fun `profile override produces ProfileCredentialsProvider`() {
        val provider =
            AwsKotlinCredentialsProviderFactory.create(
                AwsKotlinProperties(profileName = "test-profile", region = "us-east-1"),
            )
        val profileProvider = provider.shouldBeInstanceOf<ProfileCredentialsProvider>()
        profileProvider.profileName shouldBe "test-profile"
        profileProvider.region shouldBe "us-east-1"
        profileProvider.close()
    }

    @Test
    fun `no overrides produce DefaultChainCredentialsProvider`() {
        val provider = AwsKotlinCredentialsProviderFactory.create(AwsKotlinProperties())
        val chain = provider.shouldBeInstanceOf<DefaultChainCredentialsProvider>()
        chain.close()
    }

    @Test
    fun `region override is passed to DefaultChainCredentialsProvider`() {
        val provider = AwsKotlinCredentialsProviderFactory.create(AwsKotlinProperties(region = "eu-west-1"))
        val chain = provider.shouldBeInstanceOf<DefaultChainCredentialsProvider>()
        chain.region shouldBe "eu-west-1"
        chain.close()
    }
}
