package io.github.hantsy.awskotlin.sqs

import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer
import kotlin.reflect.full.starProjectedType

/**
 * kotlinx.serialization based converter.
 *
 * String payloads pass through unchanged in both directions; all other payloads are
 * serialized/deserialized as JSON with runtime serializers resolved from the payload or
 * target class, so non-String payload types must be `@Serializable`. Unknown JSON fields
 * are ignored when reading. Conversion failures are wrapped in
 * [SqsMessageConversionException] with the serialization error preserved as the cause.
 */
class KotlinxSqsMessageConverter(private val json: Json = Json { ignoreUnknownKeys = true },) : SqsMessageConverter {
    override fun toBody(payload: Any): String {
        if (payload is String) return payload
        return try {
            json.encodeToString(serializer(payload::class.starProjectedType), payload)
        } catch (e: IllegalArgumentException) {
            // SerializationException (serializer lookup and encoding errors) extends
            // IllegalArgumentException; star-projected generic payloads fail lookup here too.
            throw SqsMessageConversionException(
                "Failed to serialize payload of type ${payload.javaClass.name} to a message body",
                e,
            )
        }
    }

    override fun <T : Any> fromBody(body: String, targetType: Class<T>): T {
        if (targetType == String::class.java) {
            @Suppress("UNCHECKED_CAST")
            return body as T
        }
        return try {
            val value =
                json.decodeFromString(serializer(targetType.kotlin.starProjectedType), body)
                    ?: throw SqsMessageConversionException("Message body converted to null for type ${targetType.name}")

            @Suppress("UNCHECKED_CAST")
            value as T
        } catch (e: IllegalArgumentException) {
            throw SqsMessageConversionException("Failed to deserialize message body to ${targetType.name}", e)
        }
    }
}
