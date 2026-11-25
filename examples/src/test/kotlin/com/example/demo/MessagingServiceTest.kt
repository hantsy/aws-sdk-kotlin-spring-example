package com.example.demo

import com.example.demo.internal.AwsConfig
import com.example.demo.internal.AwsProperties
import com.example.demo.internal.SqsMessagingService
import io.kotest.assertions.nondeterministic.eventually
import io.kotest.inspectors.forAny
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.ImportAutoConfiguration
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.jackson.autoconfigure.JacksonAutoConfiguration
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import kotlin.time.Duration.Companion.seconds

@SpringBootTest(classes = [MessagingServiceTest.TestConfig::class])
class MessagingServiceTest @Autowired constructor(val messagingService: MessagingService) {
    companion object {
        @JvmStatic
        @DynamicPropertySource
        fun localstackProperties(registry: DynamicPropertyRegistry) {
            registry.add("aws.endpoint") { LocalstackTestContainer.endpoint }
        }
    }

    @Configuration
    @Import(SqsMessagingService::class, AwsConfig::class)
    @EnableConfigurationProperties(AwsProperties::class)
    @ImportAutoConfiguration(JacksonAutoConfiguration::class)
    internal class TestConfig

    // JUnit 6 supports suspend test functions natively.
    @Test
    suspend fun `send and receive message`() {
        messagingService.createQueue("test")

        eventually(5.seconds) {
            messagingService.send("test", MyMessage("Hello, SQS!"))
        }

        eventually(5.seconds) {
            messagingService.receive("test", MyMessage::class.java).forAny {
                it.text shouldBe "Hello, SQS!"
            }
        }
    }

    data class MyMessage(val text: String)
}
