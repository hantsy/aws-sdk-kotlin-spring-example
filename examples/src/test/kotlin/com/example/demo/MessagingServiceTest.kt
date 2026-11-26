package com.example.demo

import com.example.demo.internal.SqsMessagingService
import io.github.hantsy.awskotlin.core.autoconfigure.AwsKotlinAutoConfiguration
import io.github.hantsy.awskotlin.sqs.autoconfigure.SqsAutoConfiguration
import io.kotest.assertions.nondeterministic.eventually
import io.kotest.inspectors.forAny
import io.kotest.matchers.shouldBe
import kotlinx.serialization.Serializable
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.ImportAutoConfiguration
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.localstack.LocalStackContainer
import org.testcontainers.utility.DockerImageName
import kotlin.time.Duration.Companion.seconds

@SpringBootTest(classes = [MessagingServiceTest.TestConfig::class])
@Testcontainers
@EnabledIfEnvironmentVariable(named = "LOCALSTACK_AUTH_TOKEN", matches = ".+")
class MessagingServiceTest @Autowired constructor(val messagingService: MessagingService) {
    companion object {
        @JvmField
        @Container
        val localstack: LocalStackContainer =
            LocalStackContainer(DockerImageName.parse("localstack/localstack:4"))
                .withServices("s3", "sqs", "secretsmanager")
                .apply {
                    System.getenv("LOCALSTACK_AUTH_TOKEN")?.takeIf { it.isNotBlank() }
                        ?.let { withEnv("LOCALSTACK_AUTH_TOKEN", it) }
                }

        @JvmStatic
        @DynamicPropertySource
        fun localstackProperties(registry: DynamicPropertyRegistry) {
            registry.add("awskotlin.endpoint") { localstack.endpoint.toString() }
        }
    }

    @Configuration
    @Import(SqsMessagingService::class)
    @ImportAutoConfiguration(AwsKotlinAutoConfiguration::class, SqsAutoConfiguration::class)
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

    @Serializable
    data class MyMessage(val text: String)
}
