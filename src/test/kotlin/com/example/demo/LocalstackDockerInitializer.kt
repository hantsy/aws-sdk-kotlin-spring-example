package com.example.demo

import org.slf4j.LoggerFactory
import org.springframework.boot.test.util.TestPropertyValues
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ApplicationListener
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.context.event.ContextClosedEvent
import org.testcontainers.containers.localstack.LocalStackContainer
import org.testcontainers.utility.DockerImageName

class LocalstackDockerInitializer : ApplicationContextInitializer<ConfigurableApplicationContext> {
    companion object {
        private val log = LoggerFactory.getLogger(LocalstackDockerInitializer::class.java)
        private val DOCKER_IMAGE = DockerImageName.parse("localstack/localstack:latest")
    }

    override fun initialize(ctx: ConfigurableApplicationContext) {
        val localStackContainer = LocalStackContainer(DOCKER_IMAGE)

        log.debug(">>>start localstack Docker container...")
        localStackContainer.start()

        ctx.addApplicationListener(
            ApplicationListener<ContextClosedEvent> {
                log.debug(">>>stop localstack Docker container before closing application context...")
                localStackContainer.stop()
            }
        )

        TestPropertyValues
            .of("aws.endpoint=http://${localStackContainer.host}:${localStackContainer.firstMappedPort}")
            .applyTo(ctx)
    }
}
