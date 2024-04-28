package com.example.demo

import com.example.demo.StorageServiceTest.StorageServiceTestConfig
import com.example.demo.internal.AwsConfig
import com.example.demo.internal.AwsProperties
import com.example.demo.internal.S3StorageService
import io.kotest.assertions.nondeterministic.continually
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.kotest.matchers.string.shouldStartWith
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.withContext
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.core.io.ClassPathResource
import org.springframework.test.context.ContextConfiguration
import org.springframework.util.FileCopyUtils
import java.nio.file.Files
import java.util.*
import kotlin.time.Duration.Companion.milliseconds

@SpringBootTest(classes = [StorageServiceTestConfig::class])
@ContextConfiguration(initializers = [LocalstackDockerInitializer::class])
class StorageServiceTest @Autowired constructor(val storageService: StorageService) {

    @Configuration
    @Import(S3StorageService::class, AwsConfig::class)
    @EnableConfigurationProperties(AwsProperties::class)
    class StorageServiceTestConfig

    @Test
    fun `store text files`() = runTest {
        val id = UUID.randomUUID().toString()
        val content = "Hello World".toByteArray().toFluxDataBuffer()

        storageService.store(id, content)

        continually(1000.milliseconds) {
            val result = storageService.retrieve(id)
            result shouldNotBe null
            String(result!!.toByteArray()) shouldBe "Hello World"
        }

        storageService.delete(id)

        continually(1000.milliseconds) {
            try {
                storageService.retrieve(id)
            } catch (e: Exception) {
                e.message shouldStartWith "The specified key does not exist"
            }
        }
    }

    @Test
    fun `store binary files`() = runTest {
        val id = UUID.randomUUID().toString()
        val content = withContext(Dispatchers.IO) {
            ClassPathResource("/test.png").contentAsByteArray.toFluxDataBuffer()
        }
        storageService.store(id, content)

        continually(1000.milliseconds) {
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
