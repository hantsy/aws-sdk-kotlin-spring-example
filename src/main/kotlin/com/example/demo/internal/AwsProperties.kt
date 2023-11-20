package com.example.demo.internal

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "aws")
data class AwsProperties(
    val accessKeyId: String,
    val secretAccessKey: String,
    val region: String,
    val bucketName: String,
    var endpoint: String? = null
)
