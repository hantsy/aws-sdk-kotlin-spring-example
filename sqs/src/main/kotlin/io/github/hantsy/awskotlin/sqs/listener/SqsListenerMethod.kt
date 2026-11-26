package io.github.hantsy.awskotlin.sqs.listener

import io.github.hantsy.awskotlin.sqs.SqsMessage
import kotlin.reflect.KClass
import kotlin.reflect.KFunction
import kotlin.reflect.KParameter
import kotlin.reflect.full.callSuspendBy
import kotlin.reflect.jvm.isAccessible

/**
 * Validated reflective view of a listener method.
 *
 * Enforces the endpoint contract: exactly one payload-bearing parameter (plain `T`
 * or `SqsMessage<T>` with a concrete `T`), at most one [Acknowledge] parameter and
 * no unsupported parameters. Invalid methods fail with [IllegalArgumentException].
 */
internal class SqsListenerMethod(private val function: KFunction<*>) {
    private enum class Role { INSTANCE, MESSAGE, PAYLOAD, ACKNOWLEDGE }

    private val roles: Map<KParameter, Role>
    val payloadType: Class<Any>
    val hasAcknowledgeParameter: Boolean

    init {
        val roles = mutableMapOf<KParameter, Role>()
        var payloadClass: KClass<*>? = null
        var payloadCount = 0
        var acknowledgeCount = 0
        for (parameter in function.parameters) {
            when (parameter.kind) {
                KParameter.Kind.INSTANCE -> roles[parameter] = Role.INSTANCE

                KParameter.Kind.VALUE -> when (val classifier = parameter.type.classifier) {
                    Acknowledge::class -> {
                        roles[parameter] = Role.ACKNOWLEDGE
                        acknowledgeCount++
                    }

                    SqsMessage::class -> {
                        val argument = parameter.type.arguments.singleOrNull()?.type?.classifier as? KClass<*>
                        require(argument != null) {
                            "SqsMessage parameter of ${function.name} must have a concrete payload type, " +
                                "not a star projection"
                        }
                        roles[parameter] = Role.MESSAGE
                        payloadClass = argument
                        payloadCount++
                    }

                    is KClass<*> -> {
                        roles[parameter] = Role.PAYLOAD
                        payloadClass = classifier
                        payloadCount++
                    }

                    else -> throw IllegalArgumentException(
                        "Unsupported parameter ${parameter.name} of listener method ${function.name}"
                    )
                }

                else -> throw IllegalArgumentException(
                    "Unsupported parameter ${parameter.name} of listener method ${function.name}"
                )
            }
        }
        require(roles.values.count { it == Role.INSTANCE } == 1) {
            "Listener method ${function.name} must be a member function"
        }
        require(payloadCount == 1) {
            "Listener method ${function.name} must declare exactly one payload parameter, got $payloadCount"
        }
        require(acknowledgeCount <= 1) {
            "Listener method ${function.name} must declare at most one Acknowledge parameter"
        }
        this.roles = roles

        @Suppress("UNCHECKED_CAST")
        payloadType = requireNotNull(payloadClass).java as Class<Any>
        hasAcknowledgeParameter = acknowledgeCount == 1
    }

    /** Invokes the method, resolving each parameter by its validated role. */
    suspend fun invoke(bean: Any, message: SqsMessage<*>, acknowledge: Acknowledge) {
        val arguments = function.parameters.associateWith { parameter ->
            when (roles[parameter]) {
                Role.INSTANCE -> bean
                Role.MESSAGE -> message
                Role.PAYLOAD -> message.payload
                Role.ACKNOWLEDGE -> acknowledge
                null -> error("No role for parameter ${parameter.name}")
            }
        }
        function.isAccessible = true
        if (function.isSuspend) function.callSuspendBy(arguments) else function.callBy(arguments)
    }
}
