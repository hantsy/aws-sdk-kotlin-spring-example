package com.example.demo.internal

import aws.sdk.kotlin.services.sqs.SqsClient
import aws.sdk.kotlin.services.sqs.model.CreateQueueRequest
import aws.sdk.kotlin.services.sqs.model.GetQueueUrlRequest
import aws.sdk.kotlin.services.sqs.model.ReceiveMessageRequest
import aws.sdk.kotlin.services.sqs.model.SendMessageRequest
import com.example.demo.MessagingService
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.annotation.PostConstruct
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class SqsMessagingService(
    private val properties: AwsProperties,
    private val sqsClient: SqsClient,
    private val objectMapper: ObjectMapper
) : MessagingService {

    companion object {
        private val log = LoggerFactory.getLogger(SqsMessagingService::class.java)
        const val MAX_NUMBER_OF_RECEIVED_MESSAGES = 5
        const val DELAY_IN_SECONDS = 1
    }

    @PostConstruct
    fun init() = runBlocking {
        log.debug("initializing SqsMessagingService...")
        val queueNameVal = createQueue(properties.sqs?.queueName ?: DEFAULT_SQS_QUEUE_NAME)
        log.debug("created queue: $queueNameVal")
    }

    override suspend fun createQueue(queueNameVal: String): String {
        return getQueue(queueNameVal) ?: run {
            log.debug("queue $queueNameVal does not exist, create one")
            val createQueueRequest = CreateQueueRequest {
                queueName = queueNameVal
            }

            val createQueueResponse = withContext(Dispatchers.IO) {
                sqsClient.createQueue(createQueueRequest)
            }
            log.debug("created queue url ${createQueueResponse.queueUrl}")
            return createQueueResponse.queueUrl!!
        }
    }

    override suspend fun getQueue(queueNameVal: String): String? {
        log.debug("get queue by name: $queueNameVal")
        val getQueueUrlRequest = GetQueueUrlRequest {
            queueName = queueNameVal
        }

        val getQueueUrlResponse = withContext(Dispatchers.IO) {
            try {
                sqsClient.getQueueUrl(getQueueUrlRequest)
            } catch (_: Exception) {
                null
            }
        }
        return getQueueUrlResponse?.queueUrl
    }

    override suspend fun send(queueNameVal: String, message: Any) {
        val getQueueUrl = getQueue(queueNameVal)

        val sendMessageRequest = SendMessageRequest {
            queueUrl = getQueueUrl
            delaySeconds = DELAY_IN_SECONDS
            messageBody = objectMapper.writeValueAsString(message)
        }

        val sendMessageResponse = withContext(Dispatchers.IO) {
            sqsClient.sendMessage(sendMessageRequest)
        }
        log.debug("message ${sendMessageResponse.messageId} is sent out")
    }

    override suspend fun <T : Any> receive(queueNameVal: String, clazz: Class<out T>): List<T> {
        val getQueueUrl = getQueue(queueNameVal)

        val receiveMessageRequest = ReceiveMessageRequest {
            queueUrl = getQueueUrl
            maxNumberOfMessages = MAX_NUMBER_OF_RECEIVED_MESSAGES
        }

        val receiveMessageResponse = withContext(Dispatchers.IO) {
            sqsClient.receiveMessage(receiveMessageRequest)
        }
        return receiveMessageResponse
            .messages
            ?.mapNotNull { message ->
                log.debug("received message: $message")
                runCatching { objectMapper.readValue(message.body, clazz) }.getOrNull()
            } ?: emptyList()
    }
}
