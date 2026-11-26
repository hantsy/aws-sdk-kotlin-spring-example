package io.github.hantsy.awskotlin.sqs.autoconfigure

import aws.sdk.kotlin.runtime.auth.credentials.StaticCredentialsProvider
import aws.sdk.kotlin.services.sqs.SqsClient
import io.github.hantsy.awskotlin.core.autoconfigure.AwsKotlinAutoConfiguration
import io.github.hantsy.awskotlin.sqs.KotlinxSqsMessageConverter
import io.github.hantsy.awskotlin.sqs.SqsMessageConverter
import io.github.hantsy.awskotlin.sqs.SqsQueueResolver
import io.github.hantsy.awskotlin.sqs.SqsTemplate
import io.github.hantsy.awskotlin.sqs.listener.SqsListenerAnnotationRegistrar
import io.github.hantsy.awskotlin.sqs.listener.SqsListenerConfig
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import io.kotest.matchers.types.shouldBeSameInstanceAs
import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.springframework.boot.autoconfigure.AutoConfigurations
import org.springframework.boot.test.context.runner.ApplicationContextRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import kotlin.time.Duration.Companion.seconds

class SqsAutoConfigurationTest {
    private val runner =
        ApplicationContextRunner().withConfiguration(
            AutoConfigurations.of(AwsKotlinAutoConfiguration::class.java, SqsAutoConfiguration::class.java),
        )

    @Test
    fun `default context provides SQS beans with static credentials and region`() {
        runner.withPropertyValues(
            "awskotlin.region=us-east-1",
            "awskotlin.access-key-id=test-access-key",
            "awskotlin.secret-access-key=test-secret-key",
        ).run { context ->
            context.getBeanNamesForType(SqsClient::class.java).size shouldBe 1
            context.getBeanNamesForType(SqsMessageConverter::class.java).size shouldBe 1
            context.getBeanNamesForType(SqsQueueResolver::class.java).size shouldBe 1
            context.getBeanNamesForType(SqsTemplate::class.java).size shouldBe 1
            context.getBeanNamesForType(SqsProperties::class.java).size shouldBe 1
            context.getBeanNamesForType(SqsListenerAnnotationRegistrar::class.java).size shouldBe 1
            val client = context.getBean(SqsClient::class.java)
            client.config.region shouldBe "us-east-1"
            client.config.credentialsProvider.shouldBeInstanceOf<StaticCredentialsProvider>()
            context.getBean(SqsMessageConverter::class.java).shouldBeInstanceOf<KotlinxSqsMessageConverter>()
        }
    }

    @Test
    fun `awskotlin enabled=false removes the beans`() {
        runner.withPropertyValues(
            "awskotlin.region=us-east-1",
            "awskotlin.enabled=false",
        ).run { context ->
            context.getBeanNamesForType(SqsClient::class.java).isEmpty() shouldBe true
            context.getBeanNamesForType(SqsMessageConverter::class.java).isEmpty() shouldBe true
            context.getBeanNamesForType(SqsQueueResolver::class.java).isEmpty() shouldBe true
            context.getBeanNamesForType(SqsTemplate::class.java).isEmpty() shouldBe true
            context.getBeanNamesForType(SqsProperties::class.java).isEmpty() shouldBe true
            context.getBeanNamesForType(SqsListenerAnnotationRegistrar::class.java).isEmpty() shouldBe true
        }
    }

    @Test
    fun `awskotlin sqs enabled=false removes the beans`() {
        runner.withPropertyValues(
            "awskotlin.region=us-east-1",
            "awskotlin.sqs.enabled=false",
        ).run { context ->
            context.getBeanNamesForType(SqsClient::class.java).isEmpty() shouldBe true
            context.getBeanNamesForType(SqsMessageConverter::class.java).isEmpty() shouldBe true
            context.getBeanNamesForType(SqsQueueResolver::class.java).isEmpty() shouldBe true
            context.getBeanNamesForType(SqsTemplate::class.java).isEmpty() shouldBe true
            context.getBeanNamesForType(SqsProperties::class.java).isEmpty() shouldBe true
            context.getBeanNamesForType(SqsListenerAnnotationRegistrar::class.java).isEmpty() shouldBe true
        }
    }

    @Test
    fun `user SqsClient bean wins`() {
        runner.withPropertyValues(
            "awskotlin.region=us-east-1",
        ).withUserConfiguration(UserSqsClientConfiguration::class.java).run { context ->
            context.getBeanNamesForType(SqsClient::class.java).size shouldBe 1
            val userBean = context.getBean("userSqsClient")
            context.getBean(SqsClient::class.java) shouldBeSameInstanceAs userBean
            context.getBeanNamesForType(SqsTemplate::class.java).size shouldBe 1
        }
    }

    @Test
    fun `user SqsMessageConverter bean wins`() {
        runner.withPropertyValues(
            "awskotlin.region=us-east-1",
        ).withUserConfiguration(UserSqsMessageConverterConfiguration::class.java).run { context ->
            context.getBeanNamesForType(SqsMessageConverter::class.java).size shouldBe 1
            val userBean = context.getBean("userSqsMessageConverter")
            context.getBean(SqsMessageConverter::class.java) shouldBeSameInstanceAs userBean
            context.getBeanNamesForType(SqsTemplate::class.java).size shouldBe 1
        }
    }

    @Test
    fun `user SqsQueueResolver bean wins`() {
        runner.withPropertyValues(
            "awskotlin.region=us-east-1",
        ).withUserConfiguration(UserSqsQueueResolverConfiguration::class.java).run { context ->
            context.getBeanNamesForType(SqsQueueResolver::class.java).size shouldBe 1
            val userBean = context.getBean("userSqsQueueResolver")
            context.getBean(SqsQueueResolver::class.java) shouldBeSameInstanceAs userBean
            context.getBeanNamesForType(SqsTemplate::class.java).size shouldBe 1
        }
    }

    @Test
    fun `user SqsTemplate bean wins`() {
        runner.withPropertyValues(
            "awskotlin.region=us-east-1",
        ).withUserConfiguration(UserSqsTemplateConfiguration::class.java).run { context ->
            context.getBeanNamesForType(SqsTemplate::class.java).size shouldBe 1
            val userBean = context.getBean("userSqsTemplate")
            context.getBean(SqsTemplate::class.java) shouldBeSameInstanceAs userBean
        }
    }

    @Test
    fun `sqs region and endpoint override the global values`() {
        runner.withPropertyValues(
            "awskotlin.region=us-east-1",
            "awskotlin.endpoint=http://global:4566",
            "awskotlin.sqs.region=eu-west-1",
            "awskotlin.sqs.endpoint=http://localhost:4566",
            "awskotlin.access-key-id=test-access-key",
            "awskotlin.secret-access-key=test-secret-key",
        ).run { context ->
            val config = context.getBean(SqsClient::class.java).config
            config.region shouldBe "eu-west-1"
            config.endpointUrl?.toString() shouldBe "http://localhost:4566"
            val properties = context.getBean(SqsProperties::class.java)
            properties.region shouldBe "eu-west-1"
            properties.endpoint shouldBe "http://localhost:4566"
        }
    }

    @Test
    fun `global endpoint override is used when sqs endpoint is unset`() {
        runner.withPropertyValues(
            "awskotlin.region=us-east-1",
            "awskotlin.endpoint=http://localhost:4566",
            "awskotlin.access-key-id=test-access-key",
            "awskotlin.secret-access-key=test-secret-key",
        ).run { context ->
            context.getBean(SqsClient::class.java).config.endpointUrl?.toString() shouldBe "http://localhost:4566"
        }
    }

    @Test
    fun `listener properties have sensible defaults`() {
        runner.withPropertyValues(
            "awskotlin.region=us-east-1",
        ).run { context ->
            val listener = context.getBean(SqsProperties::class.java).listener
            listener.autoStartup shouldBe true
            listener.maxConcurrentMessages shouldBe 10
            listener.pollTime shouldBe 10.seconds
            listener.shutdownTimeout shouldBe 20.seconds
        }
    }

    @Test
    fun `custom listener values bind`() {
        runner.withPropertyValues(
            "awskotlin.region=us-east-1",
            "awskotlin.sqs.listener.auto-startup=false",
            "awskotlin.sqs.listener.max-concurrent-messages=5",
            "awskotlin.sqs.listener.poll-time=5s",
            "awskotlin.sqs.listener.shutdown-timeout=30s",
        ).run { context ->
            val listener = context.getBean(SqsProperties::class.java).listener
            listener.autoStartup shouldBe false
            listener.maxConcurrentMessages shouldBe 5
            listener.pollTime shouldBe 5.seconds
            listener.shutdownTimeout shouldBe 30.seconds
        }
    }

    @Test
    fun `listener durations also accept ISO-8601 format`() {
        runner.withPropertyValues(
            "awskotlin.region=us-east-1",
            "awskotlin.sqs.listener.poll-time=PT2S",
            "awskotlin.sqs.listener.shutdown-timeout=PT1M",
        ).run { context ->
            val listener = context.getBean(SqsProperties::class.java).listener
            listener.pollTime shouldBe 2.seconds
            listener.shutdownTimeout shouldBe 60.seconds
        }
    }

    @Test
    fun `user SqsListenerAnnotationRegistrar bean wins`() {
        runner.withPropertyValues(
            "awskotlin.region=us-east-1",
        ).withUserConfiguration(UserSqsListenerRegistrarConfiguration::class.java).run { context ->
            context.getBeanNamesForType(SqsListenerAnnotationRegistrar::class.java).size shouldBe 1
            val userBean = context.getBean("userSqsListenerRegistrar")
            context.getBean(SqsListenerAnnotationRegistrar::class.java) shouldBeSameInstanceAs userBean
        }
    }

    @Test
    fun `default listener config maps into the registrar`() {
        runner.withPropertyValues(
            "awskotlin.region=us-east-1",
        ).run { context ->
            val registrar = context.getBean(SqsListenerAnnotationRegistrar::class.java)
            registrarConfig(registrar) shouldBe SqsListenerConfig()
            registrar.isAutoStartup shouldBe true
        }
    }

    @Test
    fun `custom listener properties map into the registrar and autoStartup=false prevents polling`() {
        runner.withPropertyValues(
            "awskotlin.region=us-east-1",
            "awskotlin.sqs.listener.auto-startup=false",
            "awskotlin.sqs.listener.max-concurrent-messages=5",
            "awskotlin.sqs.listener.poll-time=5s",
            "awskotlin.sqs.listener.shutdown-timeout=30s",
        ).run { context ->
            val registrar = context.getBean(SqsListenerAnnotationRegistrar::class.java)
            registrarConfig(registrar) shouldBe SqsListenerConfig(
                autoStartup = false,
                maxConcurrentMessages = 5,
                pollTime = 5.seconds,
                shutdownTimeout = 30.seconds,
            )
            // autoStartup=false: the refreshed context did not start the registrar...
            registrar.isAutoStartup shouldBe false
            registrar.isRunning shouldBe false
            // ...but manual start still works (no endpoints registered, so no AWS calls).
            registrar.start()
            registrar.isRunning shouldBe true
            registrar.stop()
            registrar.isRunning shouldBe false
        }
    }

    // The registrar keeps its config private; read it reflectively to verify property mapping.
    private fun registrarConfig(registrar: SqsListenerAnnotationRegistrar): SqsListenerConfig {
        val field = SqsListenerAnnotationRegistrar::class.java.getDeclaredField("config")
        field.isAccessible = true
        return field.get(registrar) as SqsListenerConfig
    }
}

@Configuration(proxyBeanMethods = false)
class UserSqsClientConfiguration {
    @Bean
    fun userSqsClient(): SqsClient = mockk(relaxed = true)
}

@Configuration(proxyBeanMethods = false)
class UserSqsMessageConverterConfiguration {
    @Bean
    fun userSqsMessageConverter(): SqsMessageConverter = mockk(relaxed = true)
}

@Configuration(proxyBeanMethods = false)
class UserSqsQueueResolverConfiguration {
    @Bean
    fun userSqsQueueResolver(): SqsQueueResolver = SqsQueueResolver(mockk(relaxed = true))
}

@Configuration(proxyBeanMethods = false)
class UserSqsTemplateConfiguration {
    @Bean
    fun userSqsTemplate(): SqsTemplate = SqsTemplate(mockk(relaxed = true))
}

@Configuration(proxyBeanMethods = false)
class UserSqsListenerRegistrarConfiguration {
    @Bean
    fun userSqsListenerRegistrar(): SqsListenerAnnotationRegistrar =
        SqsListenerAnnotationRegistrar(mockk(relaxed = true), mockk(relaxed = true))
}
