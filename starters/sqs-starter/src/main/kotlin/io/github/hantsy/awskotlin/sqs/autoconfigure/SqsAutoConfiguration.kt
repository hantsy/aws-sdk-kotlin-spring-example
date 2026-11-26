package io.github.hantsy.awskotlin.sqs.autoconfigure

import aws.sdk.kotlin.services.sqs.SqsClient
import aws.smithy.kotlin.runtime.auth.awscredentials.CredentialsProvider
import aws.smithy.kotlin.runtime.net.url.Url
import io.github.hantsy.awskotlin.core.AwsKotlinProperties
import io.github.hantsy.awskotlin.core.autoconfigure.AwsKotlinAutoConfiguration
import io.github.hantsy.awskotlin.sqs.KotlinxSqsMessageConverter
import io.github.hantsy.awskotlin.sqs.SqsMessageConverter
import io.github.hantsy.awskotlin.sqs.SqsQueueResolver
import io.github.hantsy.awskotlin.sqs.SqsTemplate
import io.github.hantsy.awskotlin.sqs.listener.SqsListenerAnnotationRegistrar
import io.github.hantsy.awskotlin.sqs.listener.SqsListenerConfig
import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean

/**
 * Auto-configuration for the AWS SDK for Kotlin SQS client, [SqsTemplate] and the
 * [SqsListenerAnnotationRegistrar] that runs `@SqsListener` methods.
 *
 * Disabled by either `awskotlin.enabled=false` or `awskotlin.sqs.enabled=false`
 * (both checked by the single property condition below). User-provided
 * [SqsClient]/[SqsMessageConverter]/[SqsQueueResolver]/[SqsTemplate]/
 * [SqsListenerAnnotationRegistrar] beans always win.
 */
@AutoConfiguration(after = [AwsKotlinAutoConfiguration::class])
@ConditionalOnClass(SqsClient::class)
@ConditionalOnProperty(
    prefix = "awskotlin",
    name = ["enabled", "sqs.enabled"],
    havingValue = "true",
    matchIfMissing = true,
)
@EnableConfigurationProperties(SqsProperties::class)
class SqsAutoConfiguration {
    // Boot's binder erases non-null kotlin.time.Duration (a value class) constructor
    // parameters to long, so binding needs this annotation-scoped converter rather than
    // a plain Converter<String, Duration>. See KotlinDurationConverter.
    @Bean
    @ConfigurationPropertiesBinding
    fun kotlinDurationConverter(): KotlinDurationConverter = KotlinDurationConverter()

    @Bean
    @ConditionalOnMissingBean
    fun sqsClient(
        properties: AwsKotlinProperties,
        sqsProperties: SqsProperties,
        credentialsProvider: CredentialsProvider,
    ): SqsClient {
        val endpoint = sqsProperties.endpoint ?: properties.endpoint
        return SqsClient {
            region = sqsProperties.region ?: properties.region
            this.credentialsProvider = credentialsProvider
            endpoint?.let { endpointUrl = Url.parse(it) }
        }
    }

    @Bean
    @ConditionalOnMissingBean
    fun sqsMessageConverter(): SqsMessageConverter = KotlinxSqsMessageConverter()

    @Bean
    @ConditionalOnMissingBean
    fun sqsQueueResolver(client: SqsClient): SqsQueueResolver = SqsQueueResolver(client)

    @Bean
    @ConditionalOnMissingBean
    fun sqsTemplate(client: SqsClient, converter: SqsMessageConverter, queueResolver: SqsQueueResolver,): SqsTemplate =
        SqsTemplate(client, converter, queueResolver)

    companion object {
        // Static (companion + @JvmStatic) so registering this BeanPostProcessor/SmartLifecycle
        // does not force early instantiation of the auto-configuration class itself.
        // autoStartup=false prevents automatic polling; the registrar can still be started manually.
        @Bean
        @ConditionalOnMissingBean
        @JvmStatic
        fun sqsListenerAnnotationRegistrar(
            template: SqsTemplate,
            queueResolver: SqsQueueResolver,
            properties: SqsProperties,
        ): SqsListenerAnnotationRegistrar = SqsListenerAnnotationRegistrar(
            template = template,
            queueResolver = queueResolver,
            config = SqsListenerConfig(
                autoStartup = properties.listener.autoStartup,
                maxConcurrentMessages = properties.listener.maxConcurrentMessages,
                pollTime = properties.listener.pollTime,
                shutdownTimeout = properties.listener.shutdownTimeout,
            ),
        )
    }
}
