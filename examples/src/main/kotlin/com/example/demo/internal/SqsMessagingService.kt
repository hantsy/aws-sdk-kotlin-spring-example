package com.example.demo.internal

import aws.sdk.kotlin.services.sqs.SqsClient
import aws.sdk.kotlin.services.sqs.model.CreateQueueRequest
import aws.sdk.kotlin.services.sqs.model.GetQueueUrlRequest
import aws.sdk.kotlin.services.sqs.model.ReceiveMessageRequest
import com.example.demo.MessagingService
import com.example.demo.MessagingServiceException
import io.github.hantsy.awskotlin.sqs.SqsMessageConverter
import io.github.hantsy.awskotlin.sqs.SqsTemplate
import jakarta.annotation.PostConstruct
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import kotlin.time.Duration.Companion.seconds

@Service
class SqsMessagingService(
    private val template: SqsTemplate,
    private val sqsClient: SqsClient,
    private val converter: SqsMessageConverter,
) : MessagingService {
    companion object {
        private val log = LoggerFactory.getLogger(SqsMessagingService::class.java)
        const val DEFAULT_SQS_QUEUE_NAME = "default"
        const val MAX_NUMBER_OF_RECEIVED_MESSAGES = 5
    }

    @PostConstruct
    fun init() = runBlocking {
        log.debug("initializing SqsMessagingService...")
        val queueNameVal = createQueue(DEFAULT_SQS_QUEUE_NAME)
        log.debug("created queue: $queueNameVal")
    }

    // The template has no queue creation, so keep the raw starter-provided client here.
    override suspend fun createQueue(queueNameVal: String): String {
        return getQueue(queueNameVal) ?: run {
            log.debug("queue $queueNameVal does not exist, create one")
            val createQueueRequest =
                CreateQueueRequest {
                    queueName = queueNameVal
                }

            val createQueueResponse =
                withContext(Dispatchers.IO) {
                    try {
                        sqsClient.createQueue(createQueueRequest)
                    } catch (e: Exception) {
                        throw MessagingServiceException(e.message ?: "Failed to create queue: $queueNameVal")
                    }
                }
            log.debug("created queue url ${createQueueResponse.queueUrl}")
            return createQueueResponse.queueUrl!!
        }
    }

    override suspend fun getQueue(queueNameVal: String): String? {
        log.debug("get queue by name: $queueNameVal")
        val getQueueUrlRequest =
            GetQueueUrlRequest {
                queueName = queueNameVal
            }

        val getQueueUrlResponse =
            withContext(Dispatchers.IO) {
                try {
                    sqsClient.getQueueUrl(getQueueUrlRequest)
                } catch (_: Exception) {
                    null
                }
            }
        return getQueueUrlResponse?.queueUrl
    }

    override suspend fun send(queueNameVal: String, message: Any) {
        val sendMessageResponse =
            try {
                template.destination(queueNameVal).send(message, delay = 1.seconds)
            } catch (e: Exception) {
                throw MessagingServiceException(e.message ?: "Failed to send message")
            }
        log.debug("message ${sendMessageResponse.messageId} is sent out")
    }

    // The template has no batch receive, so keep the raw client request and convert
    // each body with the starter-provided converter. Messages are not deleted here.
    override suspend fun <T : Any> receive(queueNameVal: String, clazz: Class<out T>): List<T> {
        val getQueueUrl = getQueue(queueNameVal)

        val receiveMessageRequest =
            ReceiveMessageRequest {
                queueUrl = getQueueUrl
                maxNumberOfMessages = MAX_NUMBER_OF_RECEIVED_MESSAGES
            }

        val receiveMessageResponse =
            withContext(Dispatchers.IO) {
                try {
                    sqsClient.receiveMessage(receiveMessageRequest)
                } catch (e: Exception) {
                    throw MessagingServiceException(e.message ?: "Failed to receive message")
                }
            }
        return receiveMessageResponse
            .messages
            ?.mapNotNull { message ->
                log.debug("received message: $message")
                runCatching { converter.fromBody(message.body ?: "", clazz) }.getOrNull()
            } ?: emptyList()
    }
}
