package io.github.hantsy.awskotlin.secretsmanager

import aws.sdk.kotlin.services.secretsmanager.SecretsManagerClient
import aws.sdk.kotlin.services.secretsmanager.model.CreateSecretRequest
import aws.sdk.kotlin.services.secretsmanager.model.CreateSecretResponse
import aws.sdk.kotlin.services.secretsmanager.model.DeleteSecretRequest
import aws.sdk.kotlin.services.secretsmanager.model.DeleteSecretResponse
import aws.sdk.kotlin.services.secretsmanager.model.GetSecretValueRequest
import aws.sdk.kotlin.services.secretsmanager.model.GetSecretValueResponse
import aws.sdk.kotlin.services.secretsmanager.model.ResourceNotFoundException
import aws.sdk.kotlin.services.secretsmanager.model.SecretsManagerException
import aws.sdk.kotlin.services.secretsmanager.model.UpdateSecretRequest
import aws.sdk.kotlin.services.secretsmanager.model.UpdateSecretResponse
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.slot
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test

class SecretsManagerTemplateTest {
    private val client = mockk<SecretsManagerClient>()
    private val template = SecretsManagerTemplate(client)

    @Test
    fun `get builds GetSecretValueRequest with secretId and returns the response`() = runTest {
        val request = slot<GetSecretValueRequest>()
        val response = GetSecretValueResponse { secretString = "s3cr3t" }
        coEvery { client.getSecretValue(capture(request)) } returns response

        val result = template.secret("my-secret").get()

        result shouldBe response
        request.captured.secretId shouldBe "my-secret"
    }

    @Test
    fun `create builds CreateSecretRequest with name and secretString`() = runTest {
        val request = slot<CreateSecretRequest>()
        val response = CreateSecretResponse { name = "my-secret" }
        coEvery { client.createSecret(capture(request)) } returns response

        val result = template.secret("my-secret").create("s3cr3t")

        result shouldBe response
        request.captured.name shouldBe "my-secret"
        request.captured.secretString shouldBe "s3cr3t"
    }

    @Test
    fun `update builds UpdateSecretRequest with secretId and secretString`() = runTest {
        val request = slot<UpdateSecretRequest>()
        val response = UpdateSecretResponse { name = "my-secret" }
        coEvery { client.updateSecret(capture(request)) } returns response

        val result = template.secret("my-secret").update("n3w-s3cr3t")

        result shouldBe response
        request.captured.secretId shouldBe "my-secret"
        request.captured.secretString shouldBe "n3w-s3cr3t"
    }

    @Test
    fun `delete builds DeleteSecretRequest with secretId and default force flag false`() = runTest {
        val request = slot<DeleteSecretRequest>()
        val response = DeleteSecretResponse { name = "my-secret" }
        coEvery { client.deleteSecret(capture(request)) } returns response

        val result = template.secret("my-secret").delete()

        result shouldBe response
        request.captured.secretId shouldBe "my-secret"
        request.captured.forceDeleteWithoutRecovery shouldBe false
    }

    @Test
    fun `delete passes forceDeleteWithoutRecovery true when requested`() = runTest {
        val request = slot<DeleteSecretRequest>()
        coEvery { client.deleteSecret(capture(request)) } returns DeleteSecretResponse { }

        template.secret("my-secret").delete(forceDeleteWithoutRecovery = true)

        request.captured.secretId shouldBe "my-secret"
        request.captured.forceDeleteWithoutRecovery shouldBe true
    }

    @Test
    fun `get propagates ResourceNotFoundException unchanged`() = runTest {
        val failure = ResourceNotFoundException { message = "secret not found" }
        coEvery { client.getSecretValue(any<GetSecretValueRequest>()) } throws failure

        val thrown = shouldThrow<ResourceNotFoundException> { template.secret("missing").get() }
        thrown shouldBe failure
    }

    @Test
    fun `create propagates other SDK exceptions unchanged`() = runTest {
        val failure = SecretsManagerException("access denied")
        coEvery { client.createSecret(any<CreateSecretRequest>()) } throws failure

        val thrown = shouldThrow<SecretsManagerException> { template.secret("my-secret").create("v") }
        thrown shouldBe failure
    }
}
