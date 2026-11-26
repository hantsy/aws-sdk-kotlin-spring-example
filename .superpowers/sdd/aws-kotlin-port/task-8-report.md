# Task 8 Report — SQS listener auto-configuration + proxy-aware discovery + LocalStack integration tests

## Status: DONE

Wired `SqsListenerAnnotationRegistrar` into `starter-sqs` auto-configuration, made
listener discovery proxy-aware in the `sqs` module, and added skip-safe LocalStack
integration tests to `starter-sqs`. No commits made; working-tree changes only.
`examples/`, `list/`, `utilities/`, `aws-build-tool/` untouched. Task 7
internal/private visibility fix preserved.

## Files changed

Build / catalog:
- `gradle/libs.versions.toml` — added `springAop = { module = "org.springframework:spring-aop" }`
  (version managed by the Spring Boot 4.2.0 BOM).
- `sqs/build.gradle.kts` — added `implementation(libs.springAop)`.
- `starters/starter-sqs/build.gradle.kts` — added test deps:
  `testImplementation(libs.kotlinCoroutinesCore)`, `testImplementation(libs.bundles.testcontainers)`,
  `testImplementation(libs.bundles.awaitility)`.

Main:
- `sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/listener/SqsListenerAnnotationRegistrar.kt`
  — proxy-aware discovery (Part B).
- `starters/starter-sqs/src/main/kotlin/io/github/hantsy/awskotlin/sqs/autoconfigure/SqsAutoConfiguration.kt`
  — static registrar `@Bean` in a `companion object` (Part A).

Test:
- `sqs/src/test/kotlin/io/github/hantsy/awskotlin/sqs/listener/SqsListenerAnnotationRegistrarTest.kt`
  — 4 new proxy tests (8 → 12).
- `starters/starter-sqs/src/test/kotlin/io/github/hantsy/awskotlin/sqs/autoconfigure/SqsAutoConfigurationTest.kt`
  — registrar assertions + 3 new tests (12 → 15).
- `starters/starter-sqs/src/test/kotlin/io/github/hantsy/awskotlin/sqs/autoconfigure/SqsLocalStackIntegrationTest.kt`
  — new, 5 skip-safe integration tests (Part C).

## Part A — registrar auto-configuration
- Added a `companion object` with `@Bean @ConditionalOnMissingBean @JvmStatic fun
  sqsListenerAnnotationRegistrar(template, queueResolver, properties)`. Static factory
  avoids forcing early instantiation of the auto-config class when the
  `BeanPostProcessor`/`SmartLifecycle` is registered. Maps `SqsProperties.Listener`
  (`autoStartup`, `maxConcurrentMessages`, `pollTime`, `shutdownTimeout`) into
  `SqsListenerConfig`.
- User-provided registrar bean wins (`@ConditionalOnMissingBean`); the combined
  `awskotlin.enabled` / `awskotlin.sqs.enabled` condition on the class already disables it.
- No `listener.enabled` property added. `autoStartup=false` prevents automatic polling
  (`isAutoStartup`/`isRunning` false after refresh) while manual `start()` still works.
- Context tests added: default context contains registrar; user registrar wins; default
  and custom listener config map into the registrar (read reflectively via the private
  `config` field) and `autoStartup=false` blocks auto-start but allows manual start/stop;
  both disable flags remove the registrar.

## Part B — proxy-aware discovery
- Scan target class via `AopProxyUtils.ultimateTargetClass(bean)` instead of
  `bean.javaClass`, so CGLIB overrides (which drop method annotations) and JDK proxies
  (which expose only interface methods) no longer hide `@SqsListener`.
- Invocation bean = `AopProxyUtils.getSingletonTarget(bean) ?: bean`. For an Advised proxy
  with a static singleton target the real target is invoked directly; otherwise the bean
  itself is used. Plain beans are unaffected (both utilities return the bean/its class).
- Kept the Task 7 strategy: Java-reflection pre-filter (`methods` + `declaredMethods`,
  skipping bridge/synthetic) then authoritative Kotlin `KFunction` resolution
  (`kotlinFunction`, name+erasure match, mangled-name fallback) so internal (`foo$module`)
  and private listeners still resolve. Endpoint dedup now keys on the resolved
  `endpointBean` identity.
- New unit tests using `ProxyFactory`: CGLIB (`proxyTargetClass=true`) discovery+invocation,
  CGLIB around an `internal open` listener, JDK interface proxy with a singleton target
  (annotation on the impl, not the interface), and proxy dedup. All assert the target
  received the payload and the message was acknowledged.

## Part C — LocalStack integration tests
Follows the s3 skip pattern exactly: `@TestInstance(PER_CLASS)`, `@BeforeAll assumeTrue`
on non-blank `LOCALSTACK_AUTH_TOKEN` and `DockerClientFactory.instance().isDockerAvailable`,
`LocalStackContainer(DockerImageName.parse("localstack/localstack:4")).withServices("sqs")
.withEnv(token)`, `SqsClient` with `StaticCredentialsProvider` test/test, region us-east-1,
`endpointUrl http://host:port`, best-effort `@AfterAll` close. Coverage:
1. Template send + AUTO receive round trip deletes; short re-poll returns null.
2. MANUAL receive → `reject` (visibility 0) → same message redelivered → `acknowledge`
   deletes; short re-poll returns null.
3. Listener auto-ack through `ApplicationContextRunner` (AwsKotlinAutoConfiguration +
   SqsAutoConfiguration, endpoint at LocalStack): send via template, awaitility receipt,
   then null re-poll confirms deletion.
4. Listener manual `Acknowledge.accept()` through the Spring context deletes the message.
5. Missing queue fails context startup (`startupFailure` cause chain contains
   `SqsQueueNotFoundException`); no implicit creation. Implemented with LocalStack.
Awaitility bounded at 30s; template queues use UUID names. Listener queues use fixed
per-test constant names because `@SqsListener` requires compile-time constants (unique
within the freshly started container).

## Notes / deviations (with evidence)
1. **Boot 4.2 API change**: `AssertableApplicationContext.hasFailed()` no longer exists in
   spring-boot-test 4.2.0 (verified via `javap` on
   `ApplicationContextAssertProvider`/`AssertableApplicationContext`). Used
   `context.startupFailure` (`getStartupFailure()`): `shouldBe null` for success,
   `shouldNotBeNull()` + cause-chain check for the missing-queue failure.
2. **JDK-proxy deferred minor**: JDK proxies *with a singleton target* are now discovered
   and invoked (test included). The remaining limitation is JDK proxies backed by a
   non-static target source (prototype/pooled), where `getSingletonTarget` returns null and
   only interface methods are visible — annotations on the impl are not discovered. Left as
   a deferred minor per the brief; not expanded in scope.
3. **Registrar config assertion**: `SqsListenerConfig` is held in a private field; the
   context test reads it reflectively (`getDeclaredField("config")`, `isAccessible=true`)
   to verify property mapping, as the brief permits.
4. `formatKotlinTest` applied to the new integration test (function-signature, indent,
   final-newline); no semantic change.

## Verification (run from worktree root)
- `./gradlew :sqs:test :starters:starter-sqs:test` — **BUILD SUCCESSFUL**.
  - sqs: 69 tests, 0 skipped, 0 failures (Registrar 12, Container 21, Template 22,
    QueueResolver 8, Converter 6).
  - starter-sqs: 20 tests, 5 skipped, 0 failures (SqsAutoConfigurationTest 15,
    SqsLocalStackIntegrationTest 5).
- `./gradlew :sqs:lintKotlin :starters:starter-sqs:lintKotlin` — **BUILD SUCCESSFUL**.
- **LocalStack skipped: 5/5.** Reason: `LOCALSTACK_AUTH_TOKEN` unset (Docker IS available
  on this host). Tests abort in `@BeforeAll` via `assumeTrue`; they run when a token is set.
- JUnit 6 test methods return Unit. Examples tests not run.