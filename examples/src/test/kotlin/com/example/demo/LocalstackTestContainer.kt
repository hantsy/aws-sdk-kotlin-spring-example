package com.example.demo

import org.testcontainers.localstack.LocalStackContainer
import org.testcontainers.utility.DockerImageName
import java.net.URI

/**
 * A single LocalStack container shared by all the integration tests of this module.
 *
 * LocalStack has no Spring Boot service connection, so the endpoint is injected with
 * [org.springframework.test.context.DynamicPropertySource] instead.
 *
 * The image and the license token are taken from the environment, so that CI can run the
 * tests against a licensed LocalStack image.
 */
object LocalstackTestContainer {
    /**
     * LocalStack 4 requires a license token, set LOCALSTACK_AUTH_TOKEN to run it.
     * Use LOCALSTACK_IMAGE=localstack/localstack:3.0 for the last community edition
     * that runs without a token.
     */
    private const val DEFAULT_IMAGE = "localstack/localstack:4"

    private val image = System.getenv("LOCALSTACK_IMAGE")?.takeIf { it.isNotBlank() } ?: DEFAULT_IMAGE
    private val authToken = System.getenv("LOCALSTACK_AUTH_TOKEN")?.takeIf { it.isNotBlank() }

    private val requiredServices = listOf("s3", "sqs", "secretsmanager")
    private const val READY_TIMEOUT_MILLIS = 120_000L
    private const val POLL_INTERVAL_MILLIS = 500L

    private val container: LocalStackContainer =
        LocalStackContainer(DockerImageName.parse(image)).also { localStack ->
            authToken?.let { localStack.withEnv("LOCALSTACK_AUTH_TOKEN", it) }
            localStack.start()
        }

    /**
     * The endpoint of the running LocalStack instance, for example http://localhost:32768.
     */
    val endpoint: String
        get() = "http://${container.host}:${container.firstMappedPort}"

    init {
        Runtime.getRuntime().addShutdownHook(Thread { container.stop() })
        awaitServices()
    }

    /**
     * Waits until the AWS services used by the tests are actually served by the container.
     */
    private fun awaitServices() {
        val deadline = System.currentTimeMillis() + READY_TIMEOUT_MILLIS
        while (System.currentTimeMillis() < deadline) {
            if (servicesReady()) {
                return
            }
            Thread.sleep(POLL_INTERVAL_MILLIS)
        }
        throw IllegalStateException("LocalStack did not become ready within $READY_TIMEOUT_MILLIS ms")
    }

    private fun servicesReady(): Boolean {
        val body =
            runCatching {
                URI("$endpoint/_localstack/health")
                    .toURL()
                    .openStream()
                    .bufferedReader()
                    .use { it.readText() }
            }.getOrNull() ?: return false

        return requiredServices.all {
            body.contains("\"$it\": \"available\"") || body.contains("\"$it\": \"running\"")
        }
    }
}
