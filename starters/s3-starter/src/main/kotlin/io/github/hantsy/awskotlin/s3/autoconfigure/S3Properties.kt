package io.github.hantsy.awskotlin.s3.autoconfigure

import org.springframework.boot.context.properties.ConfigurationProperties

/**
 * Configuration properties for the S3 module.
 *
 * Unset values fall back to the shared `awskotlin.*` properties and then to the
 * SDK default chains.
 */
@ConfigurationProperties(prefix = "awskotlin.s3")
data class S3Properties(
    val enabled: Boolean = true,
    val endpoint: String? = null,
    val region: String? = null,
    val pathStyleAccessEnabled: Boolean? = null,
)
