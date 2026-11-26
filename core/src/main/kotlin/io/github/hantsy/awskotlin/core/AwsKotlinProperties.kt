package io.github.hantsy.awskotlin.core

import org.springframework.boot.context.properties.ConfigurationProperties

/**
 * Shared configuration properties for AWS SDK for Kotlin based Spring modules.
 *
 * When no overrides are configured, the SDK default credential and region chains are used.
 */
@ConfigurationProperties(prefix = "awskotlin")
data class AwsKotlinProperties(
    val enabled: Boolean = true,
    val region: String? = null,
    val endpoint: String? = null,
    val accessKeyId: String? = null,
    val secretAccessKey: String? = null,
    val profileName: String? = null,
)
