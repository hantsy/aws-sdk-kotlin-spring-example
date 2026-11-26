package com.example.demo.internal

import io.github.hantsy.awskotlin.sqs.listener.SqsListener
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class AutomaticMessageReceiver {
    companion object {
        private val log = LoggerFactory.getLogger(AutomaticMessageReceiver::class.java)
    }

    // The starter registrar polls the queue and auto-deletes on successful return.
    @SqsListener(SqsMessagingService.DEFAULT_SQS_QUEUE_NAME)
    suspend fun onMessageReceived(message: String) {
        log.debug("received messaging service: $message")
        log.debug("like an event listener, do side effect...")
    }
}
