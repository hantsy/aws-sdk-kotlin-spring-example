package com.example.demo.internal

import com.example.demo.MessagingService
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class AutomaticMessageReceiver(val messagingService: MessagingService) {
    companion object {
        private val log = LoggerFactory.getLogger(AutomaticMessageReceiver::class.java)
    }

    @Scheduled(initialDelay = 500, fixedRate = 100)
    suspend fun onMessageReceived() {
        messagingService.receive(DEFAULT_SQS_QUEUE_NAME, String::class.java)
            .forEach {
                log.debug("received messaging service: $it")
            }
        log.debug("like an event listener, do side effect...")
    }
}
