package io.github.hantsy.awskotlin.sqs.listener

import io.github.hantsy.awskotlin.sqs.SqsAcknowledgementMode
import io.github.hantsy.awskotlin.sqs.SqsMessage
import io.github.hantsy.awskotlin.sqs.SqsQueueResolver
import io.github.hantsy.awskotlin.sqs.SqsTemplate
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Semaphore
import kotlinx.coroutines.withTimeoutOrNull
import kotlinx.coroutines.yield
import org.apache.commons.logging.LogFactory
import org.springframework.context.SmartLifecycle
import kotlin.time.Duration.Companion.seconds

/**
 * Polls the endpoint's queues and invokes the listener method for each message.
 *
 * Standard queues only. [start] resolves every queue URL up front — missing queues
 * ([io.github.hantsy.awskotlin.sqs.SqsQueueNotFoundException]) and FIFO queues
 * ([io.github.hantsy.awskotlin.sqs.SqsOperationException]) fail startup; nothing is
 * created implicitly. Each queue gets one long-polling loop in the provided [scope]
 * under a child [SupervisorJob], so [stop] cancels container work without cancelling
 * the external scope. A shared [Semaphore] caps in-flight messages at
 * [SqsListenerConfig.maxConcurrentMessages] across all queues.
 *
 * Messages are received in [SqsAcknowledgementMode.MANUAL]; the container deletes
 * after successful invocation only when the method has no [Acknowledge] parameter.
 */
class SqsListenerContainer(
    private val endpoint: SqsListenerEndpoint,
    private val template: SqsTemplate,
    private val queueResolver: SqsQueueResolver,
    private val config: SqsListenerConfig = SqsListenerConfig(),
    private val scope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
) : SmartLifecycle {
    private val log = LogFactory.getLog(SqsListenerContainer::class.java)
    private val method = SqsListenerMethod(endpoint.method)

    @Volatile
    private var running = false
    private var containerJob: Job? = null
    private var pollingJob: Job? = null
    private var processingJob: Job? = null

    init {
        require(endpoint.queues.isNotEmpty()) { "SqsListenerEndpoint must declare at least one queue" }
        require(config.maxConcurrentMessages > 0) {
            "maxConcurrentMessages must be positive, got ${config.maxConcurrentMessages}"
        }
    }

    override fun isAutoStartup(): Boolean = config.autoStartup

    override fun isRunning(): Boolean = running

    @Synchronized
    override fun start() {
        if (running) return
        // Resolve every queue before polling; failures propagate and leave the container stopped.
        val queueUrls = runBlocking { endpoint.queues.map { queueResolver.resolve(it) } }
        val parent = SupervisorJob(scope.coroutineContext[Job])
        val polling = SupervisorJob(parent)
        val processing = SupervisorJob(parent)
        containerJob = parent
        pollingJob = polling
        processingJob = processing
        val semaphore = Semaphore(config.maxConcurrentMessages)
        val pollingScope = CoroutineScope(scope.coroutineContext + polling)
        val processingScope = CoroutineScope(scope.coroutineContext + processing)
        queueUrls.forEach { queueUrl -> pollingScope.launch { pollLoop(queueUrl, semaphore, processingScope) } }
        running = true
    }

    @Synchronized
    override fun stop() {
        if (!running) return
        running = false
        val parent = containerJob
        val polling = pollingJob
        val processing = processingJob
        containerJob = null
        pollingJob = null
        processingJob = null
        polling?.cancel()
        if (processing != null) {
            // Give in-flight processing a chance to finish before cancelling it.
            runBlocking {
                withTimeoutOrNull(config.shutdownTimeout) {
                    processing.children.toList().forEach { it.join() }
                }
            }
        }
        parent?.cancel()
    }

    private suspend fun pollLoop(queueUrl: String, semaphore: Semaphore, processingScope: CoroutineScope) {
        var backoff = INITIAL_BACKOFF
        while (true) {
            semaphore.acquire()
            val message = try {
                template.destination(queueUrl)
                    .receive(method.payloadType, config.pollTime, SqsAcknowledgementMode.MANUAL)
            } catch (e: CancellationException) {
                semaphore.release()
                throw e
            } catch (e: Exception) {
                semaphore.release()
                log.warn("Receive failed on $queueUrl; retrying in $backoff", e)
                delay(backoff)
                backoff = (backoff * 2).coerceAtMost(MAX_BACKOFF)
                continue
            }
            backoff = INITIAL_BACKOFF
            if (message == null) {
                semaphore.release()
                yield()
                continue
            }
            processingScope.launch {
                try {
                    process(queueUrl, message)
                } finally {
                    semaphore.release()
                }
            }
        }
    }

    private suspend fun process(queueUrl: String, message: SqsMessage<Any>) {
        try {
            method.invoke(endpoint.bean, message, DefaultAcknowledge(template, queueUrl, message))
            if (!method.hasAcknowledgeParameter) {
                template.destination(queueUrl).acknowledge(message)
            }
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            log.error("Listener ${endpoint.method.name} failed for message ${message.messageId} on $queueUrl", e)
        }
    }

    private companion object {
        val INITIAL_BACKOFF = 1.seconds
        val MAX_BACKOFF = 30.seconds
    }
}
