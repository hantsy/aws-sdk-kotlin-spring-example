package io.github.hantsy.awskotlin.sns.autoconfigure

import aws.sdk.kotlin.runtime.auth.credentials.StaticCredentialsProvider
import aws.sdk.kotlin.services.sns.SnsClient
import io.github.hantsy.awskotlin.core.autoconfigure.AwsKotlinAutoConfiguration
import io.github.hantsy.awskotlin.sns.SnsTemplate
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import io.kotest.matchers.types.shouldBeSameInstanceAs
import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.springframework.boot.autoconfigure.AutoConfigurations
import org.springframework.boot.test.context.runner.ApplicationContextRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

class SnsAutoConfigurationTest {
    private val runner =
        ApplicationContextRunner().withConfiguration(
            AutoConfigurations.of(AwsKotlinAutoConfiguration::class.java, SnsAutoConfiguration::class.java),
        )

    @Test
    fun `default SnsClient and SnsTemplate beans with static credentials and region`() {
        runner.withPropertyValues(
            "awskotlin.region=us-east-1",
            "awskotlin.access-key-id=test-access-key",
            "awskotlin.secret-access-key=test-secret-key",
        ).run { context ->
            context.getBeanNamesForType(SnsClient::class.java).size shouldBe 1
            context.getBeanNamesForType(SnsTemplate::class.java).size shouldBe 1
            val client = context.getBean(SnsClient::class.java)
            client.config.region shouldBe "us-east-1"
            client.config.credentialsProvider.shouldBeInstanceOf<StaticCredentialsProvider>()
        }
    }

    @Test
    fun `sns region overrides the global region`() {
        runner.withPropertyValues(
            "awskotlin.region=us-east-1",
            "awskotlin.sns.region=eu-west-1",
            "awskotlin.access-key-id=test-access-key",
            "awskotlin.secret-access-key=test-secret-key",
        ).run { context ->
            context.getBean(SnsClient::class.java).config.region shouldBe "eu-west-1"
        }
    }

    @Test
    fun `sns endpoint override is applied`() {
        runner.withPropertyValues(
            "awskotlin.region=us-east-1",
            "awskotlin.sns.endpoint=http://localhost:4566",
            "awskotlin.access-key-id=test-access-key",
            "awskotlin.secret-access-key=test-secret-key",
        ).run { context ->
            context.getBean(SnsClient::class.java).config.endpointUrl?.toString() shouldBe "http://localhost:4566"
        }
    }

    @Test
    fun `global endpoint override is used when sns endpoint is unset`() {
        runner.withPropertyValues(
            "awskotlin.region=us-east-1",
            "awskotlin.endpoint=http://localhost:4566",
            "awskotlin.access-key-id=test-access-key",
            "awskotlin.secret-access-key=test-secret-key",
        ).run { context ->
            context.getBean(SnsClient::class.java).config.endpointUrl?.toString() shouldBe "http://localhost:4566"
        }
    }

    @Test
    fun `awskotlin sns enabled=false removes the beans`() {
        runner.withPropertyValues(
            "awskotlin.region=us-east-1",
            "awskotlin.sns.enabled=false",
        ).run { context ->
            context.getBeanNamesForType(SnsClient::class.java).isEmpty() shouldBe true
            context.getBeanNamesForType(SnsTemplate::class.java).isEmpty() shouldBe true
        }
    }

    @Test
    fun `awskotlin enabled=false removes the beans`() {
        runner.withPropertyValues(
            "awskotlin.region=us-east-1",
            "awskotlin.enabled=false",
        ).run { context ->
            context.getBeanNamesForType(SnsClient::class.java).isEmpty() shouldBe true
            context.getBeanNamesForType(SnsTemplate::class.java).isEmpty() shouldBe true
        }
    }

    @Test
    fun `user SnsClient bean wins`() {
        runner.withPropertyValues(
            "awskotlin.region=us-east-1",
        ).withUserConfiguration(UserSnsClientConfiguration::class.java).run { context ->
            context.getBeanNamesForType(SnsClient::class.java).size shouldBe 1
            val userBean = context.getBean("userSnsClient")
            context.getBean(SnsClient::class.java) shouldBeSameInstanceAs userBean
            context.getBeanNamesForType(SnsTemplate::class.java).size shouldBe 1
        }
    }
}

@Configuration(proxyBeanMethods = false)
class UserSnsClientConfiguration {
    @Bean
    fun userSnsClient(): SnsClient = mockk(relaxed = true)
}
