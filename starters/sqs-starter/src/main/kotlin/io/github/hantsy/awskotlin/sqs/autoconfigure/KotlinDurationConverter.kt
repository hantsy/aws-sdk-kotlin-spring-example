package io.github.hantsy.awskotlin.sqs.autoconfigure

import org.springframework.core.convert.TypeDescriptor
import org.springframework.core.convert.converter.ConditionalGenericConverter
import org.springframework.core.convert.converter.GenericConverter
import kotlin.time.Duration

/**
 * Marks a [Duration] constructor parameter for configuration property binding.
 *
 * Spring Boot's value-object binder erases non-null Kotlin value class parameters to their
 * underlying JVM type (`long` for [Duration]), so a plain `Converter<String, Duration>` is
 * never consulted for them. [KotlinDurationConverter] matches on this annotation instead
 * and supplies the boxed [Duration] that Kotlin constructor invocation expects.
 */
@Target(AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
annotation class KotlinDuration

/**
 * Converts duration strings such as `10s`, `500ms` or ISO-8601 `PT15S` to [Duration]
 * for constructor-bound configuration properties annotated with [KotlinDuration].
 */
class KotlinDurationConverter : ConditionalGenericConverter {
    override fun getConvertibleTypes(): Set<GenericConverter.ConvertiblePair> = setOf(
        // The binder targets the erased primitive `long`; converter lookup resolves
        // primitives to wrappers, so the pairs must use java.lang.Long (javaObjectType).
        GenericConverter.ConvertiblePair(String::class.java, Long::class.javaObjectType),
        // Binder.handleBindResult re-converts the bound value to the declared target
        // type, so the boxed Duration must also be accepted for the erased long target.
        GenericConverter.ConvertiblePair(Duration::class.java, Long::class.javaObjectType),
        GenericConverter.ConvertiblePair(String::class.java, Duration::class.java),
    )

    override fun matches(sourceType: TypeDescriptor, targetType: TypeDescriptor,): Boolean =
        targetType.hasAnnotation(KotlinDuration::class.java) || targetType.type == Duration::class.java

    override fun convert(source: Any?, sourceType: TypeDescriptor, targetType: TypeDescriptor,): Any? = when (source) {
        null -> null
        is Duration -> source
        else -> Duration.parse(source.toString().trim())
    }
}
