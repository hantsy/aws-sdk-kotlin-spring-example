package io.github.hantsy.awskotlin.sqs

import aws.sdk.kotlin.services.sqs.SqsClient
import aws.sdk.kotlin.services.sqs.changeMessageVisibility
import aws.sdk.kotlin.services.sqs.deleteMessage
import aws.sdk.kotlin.services.sqs.model.ChangeMessageVisibilityResponse
import aws.sdk.kotlin.services.sqs.model.DeleteMessageResponse
import aws.sdk.kotlin.services.sqs.model.MessageNotInflight
import aws.sdk.kotlin.services.sqs.model.MessageSystemAttributeName
import aws.sdk.kotlin.services.sqs.model.QueueDoesNotExist
import aws.sdk.kotlin.services.sqs.model.ReceiptHandleIsInvalid
import aws.sdk.kotlin.services.sqs.model.SendMessageResponse
import aws.sdk.kotlin.services.sqs.model.SqsException
import aws.sdk.kotlin.services.sqs.receiveMessage
import aws.sdk.kotlin.services.sqs.sendMessage
import kotlin.time.Duration

/**
 * Default [SqsDestinationOperations] implementation.
 *
 * Modeled SDK errors are mapped to typed template errors with the original cause
 * preserved: `QueueDoesNotExist` becomes [SqsQueueNotFoundException];
 * `ReceiptHandleIsInvalid`, `MessageNotInflight` and other [SqsException]s become
 * [SqsOperationException].
 */
internal class DefaultSqsDestinationOperations(
    private val client: SqsClient,
    private val converter: SqsMessageConverter,
    private val queueResolver: SqsQueueResolver,
    private val queueNameOrUrl: String,
) : SqsDestinationOperations {
    override suspend fun send(payload: Any, delay: Duration?): SendMessageResponse {
        val delaySeconds = delay?.inWholeSeconds
        if (delaySeconds != null && delaySeconds !in 0..MAX_DELAY_SECONDS) {
            throw SqsOperationException("delay must be between 0 and $MAX_DELAY_SECONDS seconds, got $delay")
        }
        val queueUrl = queueResolver.resolve(queueNameOrUrl)
        val body = converter.toBody(payload)
        return mapSdkErrors("send message") {
            client.sendMessage {
                this.queueUrl = queueUrl
                messageBody = body
                this.delaySeconds = delaySeconds?.toInt()
            }
        }
    }

    override suspend fun <T : Any> receive(
        targetType: Class<T>,
        timeout: Duration,
        acknowledgement: SqsAcknowledgementMode,
    ): SqsMessage<T>? {
        if (timeout.isNegative()) {
            throw SqsOperationException("timeout must not be negative, got $timeout")
        }
        val waitTimeSeconds = timeout.inWholeSeconds.coerceIn(0, MAX_WAIT_TIME_SECONDS).toInt()
        val queueUrl = queueResolver.resolve(queueNameOrUrl)
        val response = mapSdkErrors("receive message") {
            client.receiveMessage {
                this.queueUrl = queueUrl
                maxNumberOfMessages = 1
                this.waitTimeSeconds = waitTimeSeconds
                messageAttributeNames = listOf("All")
                messageSystemAttributeNames = listOf(MessageSystemAttributeName.All)
            }
        }
        val message = response.messages?.firstOrNull() ?: return null
        val body = message.body ?: ""
        // Conversion failures propagate before any delete: the message stays in the queue.
        val payload = converter.fromBody(body, targetType)
        val result = SqsMessage(
            payload = payload,
            messageId = message.messageId ?: "",
            receiptHandle = message.receiptHandle ?: "",
            body = body,
            attributes = message.attributes?.mapKeys { it.key.value } ?: emptyMap(),
            messageAttributes = message.messageAttributes ?: emptyMap(),
        )
        if (acknowledgement == SqsAcknowledgementMode.AUTO) {
            delete(queueUrl, result.receiptHandle)
        }
        return result
    }

    override suspend fun acknowledge(message: SqsMessage<*>): DeleteMessageResponse {
        val queueUrl = queueResolver.resolve(queueNameOrUrl)
        return delete(queueUrl, message.receiptHandle)
    }

    override suspend fun reject(message: SqsMessage<*>): ChangeMessageVisibilityResponse {
        val queueUrl = queueResolver.resolve(queueNameOrUrl)
        return mapSdkErrors("reject message") {
            client.changeMessageVisibility {
                this.queueUrl = queueUrl
                receiptHandle = message.receiptHandle
                visibilityTimeout = 0
            }
        }
    }

    private suspend fun delete(queueUrl: String, receiptHandle: String): DeleteMessageResponse =
        mapSdkErrors("delete message") {
            client.deleteMessage {
                this.queueUrl = queueUrl
                this.receiptHandle = receiptHandle
            }
        }

    private inline fun <R> mapSdkErrors(operation: String, block: () -> R): R = try {
        block()
    } catch (e: QueueDoesNotExist) {
        throw SqsQueueNotFoundException(queueNameOrUrl, e)
    } catch (e: ReceiptHandleIsInvalid) {
        throw SqsOperationException("Cannot $operation: receipt handle is invalid or expired", e)
    } catch (e: MessageNotInflight) {
        throw SqsOperationException("Cannot $operation: message is not in flight", e)
    } catch (e: SqsException) {
        throw SqsOperationException("Failed to $operation on $queueNameOrUrl", e)
    }

    private companion object {
        const val MAX_DELAY_SECONDS = 900L
        const val MAX_WAIT_TIME_SECONDS = 20L
    }
}
