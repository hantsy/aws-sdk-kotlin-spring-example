package io.github.hantsy.awskotlin.sqs

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import org.junit.jupiter.api.Test

@Serializable
data class Order(val id: String, val quantity: Int)

class KotlinxSqsMessageConverterTest {
    private val converter = KotlinxSqsMessageConverter()

    @Test
    fun `toBody returns String payload unchanged`() {
        converter.toBody("plain text, not JSON") shouldBe "plain text, not JSON"
    }

    @Test
    fun `fromBody returns body unchanged for String target`() {
        converter.fromBody("plain text, not JSON", String::class.java) shouldBe "plain text, not JSON"
    }

    @Test
    fun `data class roundtrips through JSON`() {
        val order = Order("o-1", 2)
        val body = converter.toBody(order)
        body shouldBe """{"id":"o-1","quantity":2}"""
        converter.fromBody(body, Order::class.java) shouldBe order
    }

    @Test
    fun `unknown JSON fields are ignored`() {
        val body = """{"id":"o-2","quantity":1,"extra":"ignored"}"""
        converter.fromBody(body, Order::class.java) shouldBe Order("o-2", 1)
    }

    @Test
    fun `serialization failure throws SqsMessageConversionException`() {
        val cyclic = mutableListOf<Any>()
        cyclic.add(cyclic)
        val thrown = shouldThrow<SqsMessageConversionException> { converter.toBody(cyclic) }
        // Star-projected generic payloads fail serializer lookup with IllegalArgumentException
        // (SerializationException, its subclass, is used for other serialization errors).
        thrown.cause.shouldBeInstanceOf<IllegalArgumentException>()
    }

    @Test
    fun `non-serializable payload throws SqsMessageConversionException`() {
        val thrown = shouldThrow<SqsMessageConversionException> { converter.toBody(NotSerializable("x")) }
        thrown.cause.shouldBeInstanceOf<SerializationException>()
    }

    @Test
    fun `deserialization failure throws SqsMessageConversionException`() {
        val thrown = shouldThrow<SqsMessageConversionException> { converter.fromBody("{not json", Order::class.java) }
        thrown.cause.shouldBeInstanceOf<SerializationException>()
    }

    @Test
    fun `null JSON body throws SqsMessageConversionException`() {
        shouldThrow<SqsMessageConversionException> { converter.fromBody("null", Order::class.java) }
    }
}

private class NotSerializable(val value: String)
