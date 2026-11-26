package io.github.hantsy.awskotlin.s3.autoconfigure

import aws.sdk.kotlin.runtime.auth.credentials.StaticCredentialsProvider
import aws.sdk.kotlin.services.s3.S3Client
import io.github.hantsy.awskotlin.core.autoconfigure.AwsKotlinAutoConfiguration
import io.github.hantsy.awskotlin.s3.S3Template
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import io.kotest.matchers.types.shouldBeSameInstanceAs
import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.springframework.boot.autoconfigure.AutoConfigurations
import org.springframework.boot.test.context.runner.ApplicationContextRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

class S3AutoConfigurationTest {
    private val runner =
        ApplicationContextRunner().withConfiguration(
            AutoConfigurations.of(AwsKotlinAutoConfiguration::class.java, S3AutoConfiguration::class.java),
        )

    @Test
    fun `default S3Client and S3Template beans with static credentials and region`() {
        runner.withPropertyValues(
            "awskotlin.region=us-east-1",
            "awskotlin.access-key-id=test-access-key",
            "awskotlin.secret-access-key=test-secret-key",
        ).run { context ->
            context.getBeanNamesForType(S3Client::class.java).size shouldBe 1
            context.getBeanNamesForType(S3Template::class.java).size shouldBe 1
            val client = context.getBean(S3Client::class.java)
            client.config.region shouldBe "us-east-1"
            client.config.credentialsProvider.shouldBeInstanceOf<StaticCredentialsProvider>()
            client.config.forcePathStyle shouldBe false
        }
    }

    @Test
    fun `s3 region overrides the global region`() {
        runner.withPropertyValues(
            "awskotlin.region=us-east-1",
            "awskotlin.s3.region=eu-west-1",
            "awskotlin.access-key-id=test-access-key",
            "awskotlin.secret-access-key=test-secret-key",
        ).run { context ->
            context.getBean(S3Client::class.java).config.region shouldBe "eu-west-1"
        }
    }

    @Test
    fun `endpoint override enables forcePathStyle by default`() {
        runner.withPropertyValues(
            "awskotlin.region=us-east-1",
            "awskotlin.s3.endpoint=http://localhost:4566",
            "awskotlin.access-key-id=test-access-key",
            "awskotlin.secret-access-key=test-secret-key",
        ).run { context ->
            val config = context.getBean(S3Client::class.java).config
            config.forcePathStyle shouldBe true
            config.endpointUrl?.toString() shouldBe "http://localhost:4566"
        }
    }

    @Test
    fun `global endpoint override is used when s3 endpoint is unset`() {
        runner.withPropertyValues(
            "awskotlin.region=us-east-1",
            "awskotlin.endpoint=http://localhost:4566",
            "awskotlin.access-key-id=test-access-key",
            "awskotlin.secret-access-key=test-secret-key",
        ).run { context ->
            val config = context.getBean(S3Client::class.java).config
            config.forcePathStyle shouldBe true
            config.endpointUrl?.toString() shouldBe "http://localhost:4566"
        }
    }

    @Test
    fun `explicit path-style false is respected even with an endpoint override`() {
        runner.withPropertyValues(
            "awskotlin.region=us-east-1",
            "awskotlin.s3.endpoint=http://localhost:4566",
            "awskotlin.s3.path-style-access-enabled=false",
            "awskotlin.access-key-id=test-access-key",
            "awskotlin.secret-access-key=test-secret-key",
        ).run { context ->
            context.getBean(S3Client::class.java).config.forcePathStyle shouldBe false
        }
    }

    @Test
    fun `awskotlin s3 enabled=false removes the beans`() {
        runner.withPropertyValues(
            "awskotlin.region=us-east-1",
            "awskotlin.s3.enabled=false",
        ).run { context ->
            context.getBeanNamesForType(S3Client::class.java).isEmpty() shouldBe true
            context.getBeanNamesForType(S3Template::class.java).isEmpty() shouldBe true
        }
    }

    @Test
    fun `awskotlin enabled=false removes the beans`() {
        runner.withPropertyValues(
            "awskotlin.region=us-east-1",
            "awskotlin.enabled=false",
        ).run { context ->
            context.getBeanNamesForType(S3Client::class.java).isEmpty() shouldBe true
            context.getBeanNamesForType(S3Template::class.java).isEmpty() shouldBe true
        }
    }

    @Test
    fun `user S3Client bean wins`() {
        runner.withPropertyValues(
            "awskotlin.region=us-east-1",
        ).withUserConfiguration(UserS3ClientConfiguration::class.java).run { context ->
            context.getBeanNamesForType(S3Client::class.java).size shouldBe 1
            val userBean = context.getBean("userS3Client")
            context.getBean(S3Client::class.java) shouldBeSameInstanceAs userBean
            context.getBeanNamesForType(S3Template::class.java).size shouldBe 1
        }
    }
}

@Configuration(proxyBeanMethods = false)
class UserS3ClientConfiguration {
    @Bean
    fun userS3Client(): S3Client = mockk()
}
