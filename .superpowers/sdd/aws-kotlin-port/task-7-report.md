# Task 7 Report — SQS coroutine listener runtime

## Status: DONE

Added the coroutine listener runtime to the `sqs` module under
`io.github.hantsy.awskotlin.sqs.listener`. `starter-sqs` remains auto-configuration
only (Task 8 will wire the registrar into auto-config and LocalStack tests). No
commits made; changes left in the working tree. `examples/`, `list/`, `utilities/`,
`aws-build-tool/` untouched.

## Build changes
- `gradle/libs.versions.toml`: added catalog library
  `springContext = { module = "org.springframework:spring-context" }` (version managed
  by the Spring Boot 4.2.0 BOM).
- `sqs/build.gradle.kts`: added `implementation(libs.springContext)` and
  `implementation(libs.kotlinReflect)`. Existing deps and the ktlint compiler-pin
  workaround preserved. No Spring Boot autoconfigure dependency added to `sqs`.

## Files created (main)
All under `sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/listener/`:
- `SqsListener.kt` — `@Target(FUNCTION) @Retention(RUNTIME) annotation class SqsListener(vararg val queues: String)`.
- `Acknowledge.kt` — public `interface Acknowledge { suspend fun accept(); suspend fun reject() }`
  plus `internal DefaultAcknowledge` bound to queue URL + message, delegating to
  `template.destination(queueUrl).acknowledge/reject(message)`.
- `SqsListenerEndpoint.kt` — `data class SqsListenerEndpoint(bean: Any, method: KFunction<*>, queues: List<String>)`.
- `SqsListenerConfig.kt` — `data class SqsListenerConfig(autoStartup=true, maxConcurrentMessages=10, pollTime=10.seconds, shutdownTimeout=20.seconds)`.
- `SqsListenerMethod.kt` — internal validated reflective view of the listener method
  (parameter role resolution, payload type extraction, suspend/non-suspend invocation).
- `SqsListenerContainer.kt` — `SmartLifecycle` container: per-queue long-polling loops,
  shared concurrency semaphore, message processing, acknowledgement policy, graceful stop.
- `SqsListenerAnnotationRegistrar.kt` — `BeanPostProcessor` + `SmartLifecycle`: collects
  endpoints during post-processing, creates/starts one container per endpoint on `start()`.

## Files created (test)
All under `sqs/src/test/kotlin/io/github/hantsy/awskotlin/sqs/listener/`:
- `SqsListenerContainerTest.kt` — 21 tests.
- `SqsListenerAnnotationRegistrarTest.kt` — 6 tests.

## Public API
Matches the brief exactly. The only signature nuance: the container/registrar `scope`
constructor parameter is stored as `private val scope` (required to launch/stop work);
default value `CoroutineScope(SupervisorJob() + Dispatchers.IO)` is unchanged. Internal
helpers (`SqsListenerMethod`, `DefaultAcknowledge`) and a commons-logging error logger
(`org.apache.commons.logging.LogFactory`, available via spring-context/spring-jcl) were
added. No public error-handler API was introduced.

## Behavior implementation
- **Validation** (`SqsListenerMethod` init + container init): queues non-empty;
  `maxConcurrentMessages > 0`; exactly one payload-bearing parameter (plain `T` or
  `SqsMessage<T>`); `SqsMessage<T>` requires a concrete `KClass` type argument (star
  projection rejected); at most one `Acknowledge` parameter; unsupported parameter kinds
  (e.g. unbound generic type parameters, extension receivers) rejected. Invalid endpoints
  throw `IllegalArgumentException`.
- **start()**: resolves every queue URL up front via `runBlocking { queues.map { queueResolver.resolve(it) } }`.
  `SqsQueueNotFoundException` (missing queue) and `SqsOperationException` (FIFO, raised by
  `SqsQueueResolver` before any SDK call) propagate and leave the container stopped — no
  implicit creation. Launches one polling loop per resolved URL under a child
  `SupervisorJob(parent)` so `stop()` cancels container work without cancelling the external
  scope. `isRunning` becomes true only after successful start; `isAutoStartup = config.autoStartup`.
- **Poll loop**: shared `Semaphore(config.maxConcurrentMessages)` across queues. Permit
  acquired before receive; released on empty/error; the processing coroutine releases in
  `finally`. Receive uses
  `template.destination(queueUrl).receive(payloadType, config.pollTime, SqsAcknowledgementMode.MANUAL)`.
  Empty receive releases + `yield()` + continues. `CancellationException` propagates (permit
  released). Other receive errors are logged, permit released, and the loop backs off
  exponentially (1s doubling, capped at 30s, reset on any successful receive).
- **Processing**: builds `DefaultAcknowledge(queueUrl, message)`; resolves arguments by role
  (`SqsMessage<T>` param → full message; plain param → `message.payload`; `Acknowledge` param →
  implementation; instance param → bean). Invokes suspend methods with `callSuspendBy`,
  non-suspend with `callBy`; sets `isAccessible = true`. No `Acknowledge` parameter →
  auto-delete after successful invocation; invocation exception → no delete. `Acknowledge`
  parameter present → never auto-delete (user decides; no decision leaves the message).
  Processing exceptions are logged and never kill the poll loop; `CancellationException`
  is rethrown so shutdown leaves the message undeleted.
- **stop()**: idempotent (`@Synchronized`, guarded by `running`); marks not running; cancels
  the polling job; waits up to `config.shutdownTimeout` for in-flight processing children to
  finish (`runBlocking { withTimeoutOrNull(...) { children.join() } }`); then cancels the
  parent container job. The external scope is never cancelled. `SmartLifecycle.stop(Runnable)`
  uses the interface default (calls `stop()` then the callback).
- **Registrar**: `postProcessAfterInitialization` records endpoints only (no polling before
  refresh); duplicate post-processing of the same bean instance is ignored. `start()` creates
  and starts one container per endpoint; if any start fails, already-started containers are
  stopped and the exception propagates. `stop()` stops all containers. Testable without an
  `ApplicationContext` (no `BeanFactory` dependency).

## Notes / deviations (with evidence)
1. **Kotlin 2.4 reflection API relocations** (finding, not a behavioral deviation):
   `KClass.java` is now provided by `kotlin.jvm.JvmClassMappingKt` (default-imported on JVM),
   and `KType.jvmErasure` moved from `kotlin.reflect.full` to `kotlin.reflect.jvm`. Verified by
   inspecting `kotlin-reflect-2.4.10.jar` (`javap`): `kotlin/reflect/jvm/KTypesJvm.getJvmErasure`,
   `kotlin/jvm/JvmClassMappingKt.getJavaClass`. Imports adjusted accordingly; the originally
   attempted `kotlin.reflect.jvm.java` / `kotlin.reflect.full.jvmErasure` do not resolve in 2.4.10.
2. **Registrar scanning strategy**: the brief says "scan bean class Kotlin member functions".
   Implementation pre-filters with Java reflection (`bean.javaClass.methods` +
   `isAnnotationPresent(SqsListener)`) and only then resolves the matching Kotlin `KFunction`
   via `memberFunctions` (matched by name + parameter erasure, dropping the trailing
   `Continuation` for suspend methods). This is equivalent for annotated Kotlin methods and is
   safe/cheap for arbitrary beans (CGLIB proxies, lambdas, Java classes) that may not support
   full Kotlin reflection — important because a `BeanPostProcessor` sees every bean. Avoids
   depending on the internal `kotlin.reflect.jvm.internal.KotlinReflectionInternalError` type.
3. **Endpoint validation exception type**: the brief fixes `SqsTemplateException` subtypes for
   queue resolution/FIFO (honored via `SqsQueueResolver`) but does not specify a type for method
   signature validation; `IllegalArgumentException` (via `require`) is used.
4. **`yield()` after empty receive**: added to keep the loop cooperative and provide a
   cancellation point; consistent with "empty receive continues".
5. **Test mocking detail**: MockK's `andThenAnswer` lambda is non-suspend, so
   `... andThenAnswer { awaitCancellation() }` does not compile. Tests use `coAnswers` with a
   delivery/call flag to deliver one message (or null, or errors) and then park via
   `awaitCancellation()`, avoiding busy-spin under `StandardTestDispatcher`.
6. **MANUAL-mode receive count**: the poll loop calls `receive` twice per delivered message
   (one delivers, the next parks), so the exact-call verification asserts `exactly = 2`.
7. Test classes carry `@OptIn(ExperimentalCoroutinesApi::class)` for
   `advanceUntilIdle`/`advanceTimeBy`/`runCurrent`. All `@Test` methods return `Unit`
   (`runTest`/plain blocks). An accidental jar-extraction artifact (`kotlin/` at the worktree
   root) created during API inspection was removed; it was never project content.

## Verification (run from worktree root)
- `./gradlew :sqs:test` — **BUILD SUCCESSFUL**. 63 tests, 0 failures, 0 errors:
  - `SqsListenerContainerTest` 21, `SqsListenerAnnotationRegistrarTest` 6 (27 new listener tests)
  - existing: `SqsTemplateTest` 22, `SqsQueueResolverTest` 8, `JacksonSqsMessageConverterTest` 6.
- `./gradlew :sqs:lintKotlin` — **BUILD SUCCESSFUL** (after `:sqs:formatKotlin` auto-fixed
  final-newline / class-signature / blank-line-between-when-conditions; re-lint clean).
- `./gradlew :starters:starter-sqs:test` — **BUILD SUCCESSFUL**. `SqsAutoConfigurationTest`
  12 tests, 0 failures, 0 errors (starter still compiles/tests against the updated `sqs`).

## Test coverage map (brief requirement → test)
- Annotation metadata + endpoint validation (empty queues, unsupported parameter, star
  projection, two payloads, two Acknowledge params, generic param) → ContainerTest.
- Registrar scans annotated methods / ignores unannotated beans / dedups bean → RegistrarTest.
- Startup resolves queues; missing queue fails startup; FIFO fails fast (real resolver +
  mocked `SqsClient`, `confirmVerified`) → ContainerTest.
- Plain payload auto-delete; `SqsMessage<T>` full message + auto-delete → ContainerTest.
- `Acknowledge.accept` deletes; `Acknowledge.reject` calls reject (visibility 0 via template);
  unused Acknowledge leaves message → ContainerTest.
- Listener exception with no Acknowledge does not delete and loop survives → ContainerTest.
- MANUAL receive mode + pollTime verified against mocked template → ContainerTest.
- Empty receive continues; exponential backoff (1s→2s) verified with virtual time → ContainerTest.
- Stop idempotent, cancels polling, keeps external scope alive; stop waits for in-flight
  processing (real thread + gate) → ContainerTest.
- Registrar missing-queue startup failure, idempotent stop, autoStartup config → RegistrarTest.

## Concerns
- None blocking. The registrar/container are intentionally not yet registered as Spring beans
  or covered by LocalStack integration tests — that is Task 8's scope. AOP-proxied listener
  beans (annotation on target class vs proxy) are not specially handled here; if Task 8 wires
  the registrar behind proxies, target-class annotation discovery may need attention.

---

## Fix report — registrar visibility handling (review Important finding)

### Finding (verbatim summary)
Registrar crashed on `internal`-visibility `@SqsListener` methods (Kotlin mangles internal
function names to `foo$module` in bytecode; the Java prefilter found the annotation but
name-equality `matches()` never matched → `error(...)` threw during
`postProcessAfterInitialization`, failing context refresh) and silently skipped `private`
annotated methods (absent from `javaClass.methods`).

### Fix
File: `sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/listener/SqsListenerAnnotationRegistrar.kt`
- Java prefilter kept (proxy/Java-bean safety) but widened to
  `javaClass.methods + javaClass.declaredMethods` (deduped via `distinct()`), so private
  annotated methods are no longer invisible. Bridge/synthetic methods are excluded.
- Kotlin discovery is now authoritative: each annotated Java method is mapped via
  `Method.kotlinFunction` (`kotlin.reflect.jvm`), which resolves internal name mangling and
  private members directly. Fallbacks retained in order: existing name/parameter-erasure
  `matches()` (Java beans), then `memberFunctions` matched by unmangled name
  (`substringBefore('$')`) + `findAnnotation<SqsListener>()`. `error(...)` remains only as a
  last resort when all three fail.
- Queue names are read from the resolved Kotlin function's `findAnnotation<SqsListener>()`
  first, falling back to the Java method annotation.
- No container changes needed: `SqsListenerMethod.invoke` already sets `isAccessible = true`,
  which covers private/internal invocation (verified by the new tests delivering messages).

### Covering tests
File: `sqs/src/test/kotlin/io/github/hantsy/awskotlin/sqs/listener/SqsListenerAnnotationRegistrarTest.kt`
- `internal annotated listener methods are discovered and invoked` — non-private nested bean
  with `internal fun` (plain payload, queue "a") and `internal suspend fun`
  (`SqsMessage<String>`, queue "b"); asserts both are invoked with the delivered payloads and
  both messages auto-deleted (`acknowledge` verified per queue).
- `private annotated listener methods are discovered and invoked` — same shape with
  `private fun` / `private suspend fun`; asserts invocation and auto-delete.
- TDD evidence: before the fix, the internal test failed with
  `java.lang.IllegalStateException` (the `error(...)` crash from the finding) and the private
  test failed with an assertion error (listener silently skipped, no invocation). Both pass
  after the fix.

### Verification (run from worktree root)
- `./gradlew :sqs:test` — **BUILD SUCCESSFUL**. 65 tests, 0 failures, 0 errors:
  `SqsListenerAnnotationRegistrarTest` 8 (was 6, +2 visibility tests),
  `SqsListenerContainerTest` 21, `SqsTemplateTest` 22, `SqsQueueResolverTest` 8,
  `JacksonSqsMessageConverterTest` 6.
- `./gradlew :sqs:lintKotlin` — **BUILD SUCCESSFUL** (one `multiline-if-else` violation in the
  new test helper fixed by adding braces; re-lint clean).
- `./gradlew :starters:starter-sqs:test` — **BUILD SUCCESSFUL** (sanity check; starter
  unaffected, public API unchanged).

### Scope notes
- Proxy target-class discovery (Task 8) and deferred minors untouched.
- Inherited private annotated methods (declared in a superclass, private there) are still not
  discovered — they are not member functions of the bean's Kotlin class and cannot be
  reflectively invoked as such; out of the finding's scope.
- No commits made; only the registrar and its test changed by this fix.