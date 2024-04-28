package com.example.demo

import com.example.demo.AwsSecretsManagementServiceTest.AwsSecretsManagementServiceTestConfig
import com.example.demo.internal.AwsConfig
import com.example.demo.internal.AwsProperties
import com.example.demo.internal.AwsSecretsManagementService
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.test.context.ContextConfiguration

@SpringBootTest(classes = [AwsSecretsManagementServiceTestConfig::class])
@ContextConfiguration(initializers = [LocalstackDockerInitializer::class])
class AwsSecretsManagementServiceTest {
    companion object {
        private val log = LoggerFactory.getLogger(AwsSecretsManagementServiceTest::class.java)
    }

    @Configuration
    @Import(AwsSecretsManagementService::class, AwsConfig::class)
    @EnableConfigurationProperties(AwsProperties::class)
    class AwsSecretsManagementServiceTestConfig

    @Autowired
    lateinit var client: SecretsManagementService

    @Test
    fun `read and write secrets`() = runTest {
        val arn = client.write("/mysecrests/test", "test", "")
        log.debug("writing secrets, return arn: $arn")
        val secretVal = client.read("/mysecrests/test")
        secretVal shouldBe "test"
    }
}
