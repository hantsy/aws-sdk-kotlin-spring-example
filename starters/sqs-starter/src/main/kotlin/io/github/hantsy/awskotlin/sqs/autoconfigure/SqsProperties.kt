package io.github.hantsy.awskotlin.sqs.autoconfigure

import org.springframework.boot.context.properties.ConfigurationProperties
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

/**
 * Configuration properties for the SQS module.
 *
 * Unset values fall back to the shared `awskotlin.*` properties and then to the
 * SDK default chains. Listener settings are consumed by the listener runtime;
 * values are not validated here.
 */
@ConfigurationProperties(prefix = "awskotlin.sqs")
data class SqsProperties(
    val enabled: Boolean = true,
    val endpoint: String? = null,
    val region: String? = null,
    val listener: Listener = Listener(),
) {
    data class Listener(
        val autoStartup: Boolean = true,
        val maxConcurrentMessages: Int = 10,
        @KotlinDuration val pollTime: Duration = 10.seconds,
        @KotlinDuration val shutdownTimeout: Duration = 20.seconds,
    )
}
