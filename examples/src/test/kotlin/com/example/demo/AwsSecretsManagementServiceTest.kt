package com.example.demo

import com.example.demo.AwsSecretsManagementServiceTest.AwsSecretsManagementServiceTestConfig
import com.example.demo.internal.AwsSecretsManagementService
import io.github.hantsy.awskotlin.core.autoconfigure.AwsKotlinAutoConfiguration
import io.github.hantsy.awskotlin.secretsmanager.autoconfigure.SecretsManagerAutoConfiguration
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable
import org.slf4j.LoggerFactory
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

@SpringBootTest(classes = [AwsSecretsManagementServiceTestConfig::class])
@Testcontainers
@EnabledIfEnvironmentVariable(named = "LOCALSTACK_AUTH_TOKEN", matches = ".+")
class AwsSecretsManagementServiceTest {
    companion object {
        private val log = LoggerFactory.getLogger(AwsSecretsManagementServiceTest::class.java)

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
    @Import(AwsSecretsManagementService::class)
    @ImportAutoConfiguration(AwsKotlinAutoConfiguration::class, SecretsManagerAutoConfiguration::class)
    class AwsSecretsManagementServiceTestConfig

    @Autowired
    lateinit var client: SecretsManagementService

    // JUnit 6 supports suspend test functions natively.
    @Test
    suspend fun `read and write secrets`() {
        val arn = client.write("/mysecrests/test", "test", "")
        log.debug("writing secrets, return arn: $arn")
        arn shouldNotBe null

        val secretVal = client.read("/mysecrests/test")
        secretVal shouldBe "test"
    }
}
