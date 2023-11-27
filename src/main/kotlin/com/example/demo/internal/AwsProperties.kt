package com.example.demo.internal

import org.springframework.boot.context.properties.ConfigurationProperties


const val DEFAULT_BUCKET_NAME = "default"
const val DEFAULT_SQS_QUEUE_NAME = "default"

@ConfigurationProperties(prefix = "aws")
data class AwsProperties(
    val accessKeyId: String,
    val secretAccessKey: String,
    val region: String="us-east-1",
    val s3: S3Properties? = null,
    val secretsManager: SecretsManagerProperties? = null,
    val sqs: SqsProperties? = null
) {
    data class S3Properties(
        val bucketName: String = DEFAULT_BUCKET_NAME,
        val endpoint: String? = "http://localhost:4566"
    )

    data class SecretsManagerProperties(
        val endpoint: String? = "http://localhost:4566"
    )

    data class SqsProperties(
        val queueName: String = DEFAULT_SQS_QUEUE_NAME,
        val endpoint: String? = "http://localhost:4566"
    )

}
