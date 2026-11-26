package io.github.hantsy.awskotlin.core.autoconfigure

import aws.sdk.kotlin.runtime.auth.credentials.DefaultChainCredentialsProvider
import aws.sdk.kotlin.runtime.auth.credentials.StaticCredentialsProvider
import aws.smithy.kotlin.runtime.auth.awscredentials.CredentialsProvider
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import io.kotest.matchers.types.shouldBeSameInstanceAs
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.springframework.boot.autoconfigure.AutoConfigurations
import org.springframework.boot.test.context.runner.ApplicationContextRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

class AwsKotlinAutoConfigurationTest {
    private val runner =
        ApplicationContextRunner().withConfiguration(AutoConfigurations.of(AwsKotlinAutoConfiguration::class.java))

    @Test
    fun `context has single CredentialsProvider bean by default`() {
        runner.run { context ->
            context.getBeanNamesForType(CredentialsProvider::class.java).size shouldBe 1
            context.getBean(CredentialsProvider::class.java).shouldBeInstanceOf<DefaultChainCredentialsProvider>()
        }
    }

    @Test
    fun `user CredentialsProvider bean wins`() {
        runner.withUserConfiguration(UserCredentialsConfiguration::class.java).run { context ->
            context.getBeanNamesForType(CredentialsProvider::class.java).size shouldBe 1
            val userBean = context.getBean("userCredentialsProvider")
            context.getBean(CredentialsProvider::class.java) shouldBeSameInstanceAs userBean
        }
    }

    @Test
    fun `awskotlin enabled=false removes auto-configured provider`() {
        runner.withPropertyValues("awskotlin.enabled=false").run { context ->
            context.getBeanNamesForType(CredentialsProvider::class.java).isEmpty() shouldBe true
        }
    }

    @Test
    fun `static properties produce StaticCredentialsProvider bean`() {
        runner.withPropertyValues(
            "awskotlin.access-key-id=test-access-key",
            "awskotlin.secret-access-key=test-secret-key",
        ).run { context ->
            val provider = context.getBean(CredentialsProvider::class.java)
            provider.shouldBeInstanceOf<StaticCredentialsProvider>()
            val credentials = runBlocking { provider.resolve() }
            credentials.accessKeyId shouldBe "test-access-key"
            credentials.secretAccessKey shouldBe "test-secret-key"
        }
    }

    @Test
    fun `static properties missing secret access key fail context startup`() {
        runner.withPropertyValues("awskotlin.access-key-id=test-access-key").run { context ->
            context.startupFailure.shouldNotBeNull()
        }
    }
}

@Configuration(proxyBeanMethods = false)
class UserCredentialsConfiguration {
    @Bean
    fun userCredentialsProvider(): CredentialsProvider = mockk()
}
