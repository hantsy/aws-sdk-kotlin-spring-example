package com.example.demo.internal

import jakarta.annotation.PostConstruct
import org.springframework.boot.context.properties.ConfigurationProperties


const val DEFAULT_BUCKET_NAME = "default"
const val DEFAULT_SQS_QUEUE_NAME = "default"
const val DEFAULT_REGION = "us-east-1"

@ConfigurationProperties(prefix = "aws")
data class AwsProperties(
    val accessKeyId: String,
    val secretAccessKey: String,
    val region: String = DEFAULT_REGION,
    val endpoint: String? = null,
    val s3: S3Properties? = null,
    val secretsManager: SecretsManagerProperties? = null,
    val sqs: SqsProperties? = null
) {
    data class S3Properties(
        val bucketName: String = DEFAULT_BUCKET_NAME,
        val endpoint: String? = null
    )

    data class SecretsManagerProperties(
        val endpoint: String?= null
    )

    data class SqsProperties(
        val queueName: String = DEFAULT_SQS_QUEUE_NAME,
        val endpoint: String?= null
    )

}
