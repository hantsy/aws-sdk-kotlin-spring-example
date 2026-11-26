package com.example.demo

import com.example.demo.StorageServiceTest.StorageServiceTestConfig
import com.example.demo.internal.S3StorageService
import io.github.hantsy.awskotlin.core.autoconfigure.AwsKotlinAutoConfiguration
import io.github.hantsy.awskotlin.s3.autoconfigure.S3AutoConfiguration
import io.kotest.assertions.nondeterministic.eventually
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.kotest.matchers.string.shouldStartWith
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.ImportAutoConfiguration
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.core.io.ClassPathResource
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.springframework.util.FileCopyUtils
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.localstack.LocalStackContainer
import org.testcontainers.utility.DockerImageName
import java.nio.file.Files
import java.util.*
import kotlin.time.Duration.Companion.seconds

@SpringBootTest(classes = [StorageServiceTestConfig::class])
@Testcontainers
@EnabledIfEnvironmentVariable(named = "LOCALSTACK_AUTH_TOKEN", matches = ".+")
class StorageServiceTest @Autowired constructor(val storageService: StorageService) {
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
    @Import(S3StorageService::class)
    @ImportAutoConfiguration(AwsKotlinAutoConfiguration::class, S3AutoConfiguration::class)
    class StorageServiceTestConfig

    // JUnit 6 supports suspend test functions natively.
    @Test
    suspend fun `store text files`() {
        val id = UUID.randomUUID().toString()

        eventually(10.seconds) {
            storageService.store(id, "Hello World".toByteArray().toFluxDataBuffer())
        }

        eventually(30.seconds) {
            val result = storageService.retrieve(id)
            result shouldNotBe null
            String(result!!.toByteArray()) shouldBe "Hello World"
        }

        storageService.delete(id)

        // The delete is not necessarily visible right away, so keep asking until the
        // object is really gone.
        eventually(30.seconds) {
            val failure = runCatching { storageService.retrieve(id) }.exceptionOrNull()
            failure shouldNotBe null
            failure!!.message shouldStartWith "The specified key does not exist"
        }
    }

    @Test
    suspend fun `store binary files`() {
        val id = UUID.randomUUID().toString()

        eventually(10.seconds) {
            val content = withContext(Dispatchers.IO) {
                ClassPathResource("/test.png").contentAsByteArray.toFluxDataBuffer()
            }
            storageService.store(id, content)
        }

        eventually(30.seconds) {
            val result = storageService.retrieve(id)
            result shouldNotBe null
            val outputFile = withContext(Dispatchers.IO) {
                Files.createTempFile("test", ".png")
            }.toFile()
            FileCopyUtils.copy(result!!.toByteArray(), outputFile)

            outputFile.exists() shouldBe true
        }
    }
}
