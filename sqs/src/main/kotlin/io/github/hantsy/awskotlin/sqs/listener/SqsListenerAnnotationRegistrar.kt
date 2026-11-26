package io.github.hantsy.awskotlin.sqs.listener

import io.github.hantsy.awskotlin.sqs.SqsQueueResolver
import io.github.hantsy.awskotlin.sqs.SqsTemplate
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.springframework.aop.framework.AopProxyUtils
import org.springframework.beans.factory.config.BeanPostProcessor
import org.springframework.context.SmartLifecycle
import java.lang.reflect.Method
import java.util.concurrent.CopyOnWriteArrayList
import kotlin.reflect.KFunction
import kotlin.reflect.KParameter
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.memberFunctions
import kotlin.reflect.jvm.jvmErasure
import kotlin.reflect.jvm.kotlinFunction

/**
 * Collects [SqsListener]-annotated methods from beans and runs them.
 *
 * [postProcessAfterInitialization] only records endpoints; no polling starts before
 * the context refresh. Annotations are discovered on the ultimate target class behind
 * Spring AOP proxies, and singleton targets are invoked directly. [start] creates and
 * starts one [SqsListenerContainer] per
 * endpoint — a missing queue fails startup and stops any containers already started.
 * [stop] stops all containers. Testable without an ApplicationContext.
 */
class SqsListenerAnnotationRegistrar(
    private val template: SqsTemplate,
    private val queueResolver: SqsQueueResolver,
    private val config: SqsListenerConfig = SqsListenerConfig(),
    private val scope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
) : BeanPostProcessor,
    SmartLifecycle {
    private val endpoints = CopyOnWriteArrayList<SqsListenerEndpoint>()
    private val containers = CopyOnWriteArrayList<SqsListenerContainer>()

    @Volatile
    private var running = false

    override fun postProcessAfterInitialization(bean: Any, beanName: String): Any {
        // Proxy-aware discovery: CGLIB overrides drop method annotations and JDK proxies only
        // expose interface methods, so scan the ultimate target class. When the proxy has a
        // singleton target, invoke that target directly; otherwise fall back to the bean itself.
        val endpointBean = AopProxyUtils.getSingletonTarget(bean) ?: bean
        if (endpoints.any { it.bean === endpointBean }) return bean
        val targetClass = AopProxyUtils.ultimateTargetClass(bean)
        // Pre-filter with Java reflection: safe and cheap for any bean (proxies, lambdas, Java classes).
        // declaredMethods is included so private annotated Kotlin listeners are not silently skipped.
        val annotated = (targetClass.methods.asSequence() + targetClass.declaredMethods.asSequence())
            .filter { !it.isBridge && !it.isSynthetic && it.isAnnotationPresent(SqsListener::class.java) }
            .distinct()
            .toList()
        if (annotated.isEmpty()) return bean
        val functions = targetClass.kotlin.memberFunctions
        annotated.forEach { method ->
            // Kotlin discovery is authoritative: kotlinFunction resolves internal name mangling
            // (foo$module) and private members that plain name matching cannot map back.
            val function = method.kotlinFunction
                ?: functions.firstOrNull { it.matches(method) }
                ?: functions.firstOrNull {
                    it.name == method.name.substringBefore('$') && it.findAnnotation<SqsListener>() != null
                }
                ?: error("No Kotlin member function for annotated method ${method.name} on ${targetClass.name}")
            val annotation = function.findAnnotation<SqsListener>() ?: method.getAnnotation(SqsListener::class.java)
            endpoints += SqsListenerEndpoint(endpointBean, function, annotation.queues.toList())
        }
        return bean
    }

    override fun isAutoStartup(): Boolean = config.autoStartup

    override fun isRunning(): Boolean = running

    @Synchronized
    override fun start() {
        if (running) return
        val started = mutableListOf<SqsListenerContainer>()
        try {
            endpoints.forEach { endpoint ->
                val container = SqsListenerContainer(endpoint, template, queueResolver, config, scope)
                container.start()
                started += container
            }
        } catch (e: Exception) {
            started.forEach { runCatching { it.stop() } }
            throw e
        }
        containers += started
        running = true
    }

    @Synchronized
    override fun stop() {
        if (!running) return
        running = false
        containers.forEach { it.stop() }
        containers.clear()
    }

    // Maps a Java method back to its Kotlin function; suspend methods carry a trailing Continuation parameter.
    private fun KFunction<*>.matches(method: Method): Boolean {
        if (name != method.name) return false
        val valueParameters = parameters.filter { it.kind == KParameter.Kind.VALUE }
        val parameterTypes = method.parameterTypes.let { if (isSuspend) it.dropLast(1) else it.asList() }
        return valueParameters.size == parameterTypes.size &&
            valueParameters.zip(parameterTypes).all { (parameter, type) -> parameter.type.jvmErasure.java == type }
    }
}
