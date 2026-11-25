package com.example.demo

import com.example.demo.AwsSecretsManagementServiceTest.AwsSecretsManagementServiceTestConfig
import com.example.demo.internal.AwsConfig
import com.example.demo.internal.AwsProperties
import com.example.demo.internal.AwsSecretsManagementService
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource

@SpringBootTest(classes = [AwsSecretsManagementServiceTestConfig::class])
class AwsSecretsManagementServiceTest {
    companion object {
        private val log = LoggerFactory.getLogger(AwsSecretsManagementServiceTest::class.java)

        @JvmStatic
        @DynamicPropertySource
        fun localstackProperties(registry: DynamicPropertyRegistry) {
            registry.add("aws.endpoint") { LocalstackTestContainer.endpoint }
        }
    }

    @Configuration
    @Import(AwsSecretsManagementService::class, AwsConfig::class)
    @EnableConfigurationProperties(AwsProperties::class)
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
