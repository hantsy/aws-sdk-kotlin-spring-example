# Task 7 — SQS listener runtime

## Context
Worktree: D:/hantsylabs/aws-sdk-kotlin-spring-example/.worktrees/awskotlin-spring-port
No commits authorized. Do not modify examples/list/utilities/aws-build-tool. Do not dispatch subagents.
Tasks 5/6 created the sqs template and starter auto-config. This task adds the listener runtime to the `sqs` module. Task 8 will wire it into starter auto-configuration and LocalStack tests.

Ruling: listener runtime lives in `sqs` module under `io.github.hantsy.awskotlin.sqs.listener`; starter-sqs remains auto-configuration only. This keeps @SqsListener/Acknowledge usable as library API.

## Global constraints
- AWS SDK Kotlin 1.9.45; Spring Boot 4.2.0 BOM; Kotlin 2.4.10; Java 25.
- Standard queues only; FIFO fails fast; missing queues fail listener startup; no implicit queue creation.
- Auto ack by default when listener method has no Acknowledge parameter. If Acknowledge parameter is present, container does not auto-delete; accept deletes, reject sets visibility 0. Exceptions/no decision leave message undeleted.
- Use Task 5 APIs: SqsTemplate, SqsMessage, SqsMessageConverter, SqsQueueResolver, SqsAcknowledgementMode, SqsTemplateException.
- Receive system attributes field is `messageSystemAttributeNames` (verified); Task 5 template already handles this.
- JUnit 6 test methods must return Unit.

## Build changes
- Add catalog library `springContext = { module = "org.springframework:spring-context" }` (Boot BOM manages version).
- sqs/build.gradle.kts: add `implementation(libs.springContext)` and `implementation(libs.kotlinReflect)`. Keep existing deps/workarounds.
- Do not add Spring Boot autoconfigure to sqs module.

## Required public API
```kotlin
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class SqsListener(vararg val queues: String)

interface Acknowledge {
    suspend fun accept()
    suspend fun reject()
}

data class SqsListenerEndpoint(
    val bean: Any,
    val method: KFunction<*>,
    val queues: List<String>,
)

data class SqsListenerConfig(
    val autoStartup: Boolean = true,
    val maxConcurrentMessages: Int = 10,
    val pollTime: Duration = 10.seconds,
    val shutdownTimeout: Duration = 20.seconds,
)

class SqsListenerContainer(
    private val endpoint: SqsListenerEndpoint,
    private val template: SqsTemplate,
    private val queueResolver: SqsQueueResolver,
    private val config: SqsListenerConfig = SqsListenerConfig(),
    scope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.IO),
) : SmartLifecycle

class SqsListenerAnnotationRegistrar(
    private val template: SqsTemplate,
    private val queueResolver: SqsQueueResolver,
    private val config: SqsListenerConfig = SqsListenerConfig(),
    scope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.IO),
) : BeanPostProcessor, SmartLifecycle
```
You may add internal helpers and a small error logger using Spring's commons-logging (org.apache.commons.logging.LogFactory) already available via spring-context. Do not add a public error-handler API unless needed for tests.

## Container behavior
1. Validate endpoint at construction or start:
   - queues non-empty.
   - method has exactly one payload-bearing parameter: either plain payload type T or SqsMessage<T>; optional Acknowledge parameter; no unsupported parameters.
   - SqsMessage<T> must have a concrete T (no star projection).
   - FIFO queue names/URLs fail fast via SqsQueueResolver/SqsTemplateException.
2. start():
   - Resolve every queue URL before polling. QueueDoesNotExist/SqsQueueNotFoundException must fail startup (throw), not be swallowed.
   - Launch one long-polling loop per queue URL in the provided CoroutineScope using a child SupervisorJob so stop can cancel container work without cancelling an external TestScope.
   - isRunning true only after successful start. isAutoStartup = config.autoStartup.
3. Poll loop:
   - Use a Semaphore(config.maxConcurrentMessages) shared across queues in the container.
   - Acquire permit before receive; release on empty/error; processing coroutine releases in finally.
   - Receive via `template.destination(queueUrl).receive(payloadType, config.pollTime, SqsAcknowledgementMode.MANUAL)`.
   - Empty receive continues. CancellationException propagates. Other receive errors are logged, permit released, and loop backs off exponentially (e.g. 1s doubling to max 30s, reset on success).
4. Message processing:
   - Build Acknowledge implementation bound to queueUrl and message: accept -> template.destination(queueUrl).acknowledge(message); reject -> template.destination(queueUrl).reject(message).
   - Resolve method arguments by type: SqsMessage<T> param gets received message; plain payload param gets message.payload; Acknowledge param gets implementation.
   - Invoke suspend methods with callSuspend; non-suspend with call. Make KCallable accessible if needed.
   - If no Acknowledge parameter: after successful invocation, auto-acknowledge (delete). If invocation throws, do not delete.
   - If Acknowledge parameter present: do not auto-delete regardless of success; user decides. If neither accept nor reject called, message remains.
   - Processing exceptions are logged and do not kill the poll loop.
5. stop():
   - Idempotent; mark not running; cancel polling; wait up to config.shutdownTimeout for in-flight processing; then cancel remaining container job. Do not cancel the external scope itself.
   - Support SmartLifecycle stop(Runnable callback) if useful; default stop() may call callback.

## Registrar behavior
- BeanPostProcessor.postProcessAfterInitialization: scan bean class Kotlin member functions for @SqsListener; create SqsListenerEndpoint for each annotated method; ignore beans with no annotations.
- Do not create containers during post-processing if that would start polling before context refresh; collect endpoints.
- SmartLifecycle.start(): create one SqsListenerContainer per endpoint and start them. Missing queue failure propagates.
- stop(): stop all containers. isRunning reflects started state. isAutoStartup = config.autoStartup.
- Keep registrar testable without a full ApplicationContext; no BeanFactory dependency required if not needed.

## Tests required
Use MockK, kotlinx-coroutines-test TestScope/StandardTestDispatcher where practical, runTest/runBlocking<Unit>. All @Test methods return Unit.
Cover at least:
- annotation metadata and endpoint validation (empty queues, unsupported parameter, SqsMessage star projection).
- registrar scans annotated bean methods and ignores unannotated beans.
- container startup resolves queues; missing queue fails startup; FIFO fails fast.
- plain payload listener auto-deletes after success.
- SqsMessage<T> listener receives full message and auto-deletes.
- Acknowledge.accept deletes; Acknowledge.reject calls reject (visibility 0 via template).
- listener exception with no Acknowledge does not delete.
- Acknowledge parameter present but unused does not delete.
- manual receive mode is used by container (verify SqsAcknowledgementMode.MANUAL if mocking template).
- stop is idempotent and cancels polling.
Mock SqsTemplate/SqsQueueResolver rather than real network. If mocking SqsTemplate.destination, return mock SqsDestinationOperations. Verify exact calls where practical.

## Verification
Run from worktree root:
- `./gradlew :sqs:test`
- `./gradlew :sqs:lintKotlin`
- `./gradlew :starters:starter-sqs:test` (ensure starter still compiles/tests)
Report exact summaries.

## Report contract
Write full report to `.superpowers/sdd/aws-kotlin-port/task-7-report.md`.
Return only status, one-line test summary, concerns.