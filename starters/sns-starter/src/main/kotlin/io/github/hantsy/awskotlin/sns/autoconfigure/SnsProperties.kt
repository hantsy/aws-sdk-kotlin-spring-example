package io.github.hantsy.awskotlin.sns.autoconfigure

import org.springframework.boot.context.properties.ConfigurationProperties

/**
 * Configuration properties for the SNS module.
 *
 * Unset values fall back to the shared `awskotlin.*` properties and then to the
 * SDK default chains.
 */
@ConfigurationProperties(prefix = "awskotlin.sns")
data class SnsProperties(val enabled: Boolean = true, val endpoint: String? = null, val region: String? = null,)
