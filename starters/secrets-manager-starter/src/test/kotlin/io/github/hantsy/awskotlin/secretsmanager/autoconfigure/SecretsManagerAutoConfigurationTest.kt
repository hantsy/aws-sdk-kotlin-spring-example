package io.github.hantsy.awskotlin.secretsmanager.autoconfigure

import aws.sdk.kotlin.runtime.auth.credentials.StaticCredentialsProvider
import aws.sdk.kotlin.services.secretsmanager.SecretsManagerClient
import io.github.hantsy.awskotlin.core.autoconfigure.AwsKotlinAutoConfiguration
import io.github.hantsy.awskotlin.secretsmanager.SecretsManagerTemplate
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import io.kotest.matchers.types.shouldBeSameInstanceAs
import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.springframework.boot.autoconfigure.AutoConfigurations
import org.springframework.boot.test.context.runner.ApplicationContextRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

class SecretsManagerAutoConfigurationTest {
    private val runner =
        ApplicationContextRunner().withConfiguration(
            AutoConfigurations.of(AwsKotlinAutoConfiguration::class.java, SecretsManagerAutoConfiguration::class.java),
        )

    @Test
    fun `default SecretsManagerClient and SecretsManagerTemplate beans with static credentials and region`() {
        runner.withPropertyValues(
            "awskotlin.region=us-east-1",
            "awskotlin.access-key-id=test-access-key",
            "awskotlin.secret-access-key=test-secret-key",
        ).run { context ->
            context.getBeanNamesForType(SecretsManagerClient::class.java).size shouldBe 1
            context.getBeanNamesForType(SecretsManagerTemplate::class.java).size shouldBe 1
            val client = context.getBean(SecretsManagerClient::class.java)
            client.config.region shouldBe "us-east-1"
            client.config.credentialsProvider.shouldBeInstanceOf<StaticCredentialsProvider>()
        }
    }

    @Test
    fun `secrets-manager region overrides the global region`() {
        runner.withPropertyValues(
            "awskotlin.region=us-east-1",
            "awskotlin.secrets-manager.region=eu-west-1",
            "awskotlin.access-key-id=test-access-key",
            "awskotlin.secret-access-key=test-secret-key",
        ).run { context ->
            context.getBean(SecretsManagerClient::class.java).config.region shouldBe "eu-west-1"
        }
    }

    @Test
    fun `secrets-manager endpoint override is applied`() {
        runner.withPropertyValues(
            "awskotlin.region=us-east-1",
            "awskotlin.secrets-manager.endpoint=http://localhost:4566",
            "awskotlin.access-key-id=test-access-key",
            "awskotlin.secret-access-key=test-secret-key",
        ).run { context ->
            context.getBean(SecretsManagerClient::class.java).config.endpointUrl?.toString() shouldBe
                "http://localhost:4566"
        }
    }

    @Test
    fun `global endpoint override is used when secrets-manager endpoint is unset`() {
        runner.withPropertyValues(
            "awskotlin.region=us-east-1",
            "awskotlin.endpoint=http://localhost:4566",
            "awskotlin.access-key-id=test-access-key",
            "awskotlin.secret-access-key=test-secret-key",
        ).run { context ->
            context.getBean(SecretsManagerClient::class.java).config.endpointUrl?.toString() shouldBe
                "http://localhost:4566"
        }
    }

    @Test
    fun `awskotlin secrets-manager enabled=false removes the beans`() {
        runner.withPropertyValues(
            "awskotlin.region=us-east-1",
            "awskotlin.secrets-manager.enabled=false",
        ).run { context ->
            context.getBeanNamesForType(SecretsManagerClient::class.java).isEmpty() shouldBe true
            context.getBeanNamesForType(SecretsManagerTemplate::class.java).isEmpty() shouldBe true
        }
    }

    @Test
    fun `awskotlin enabled=false removes the beans`() {
        runner.withPropertyValues(
            "awskotlin.region=us-east-1",
            "awskotlin.enabled=false",
        ).run { context ->
            context.getBeanNamesForType(SecretsManagerClient::class.java).isEmpty() shouldBe true
            context.getBeanNamesForType(SecretsManagerTemplate::class.java).isEmpty() shouldBe true
        }
    }

    @Test
    fun `user SecretsManagerClient bean wins`() {
        runner.withPropertyValues(
            "awskotlin.region=us-east-1",
        ).withUserConfiguration(UserSecretsManagerClientConfiguration::class.java).run { context ->
            context.getBeanNamesForType(SecretsManagerClient::class.java).size shouldBe 1
            val userBean = context.getBean("userSecretsManagerClient")
            context.getBean(SecretsManagerClient::class.java) shouldBeSameInstanceAs userBean
            context.getBeanNamesForType(SecretsManagerTemplate::class.java).size shouldBe 1
        }
    }
}

@Configuration(proxyBeanMethods = false)
class UserSecretsManagerClientConfiguration {
    @Bean
    fun userSecretsManagerClient(): SecretsManagerClient = mockk(relaxed = true)
}
