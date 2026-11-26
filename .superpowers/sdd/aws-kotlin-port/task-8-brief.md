# Task 8 — SQS listener auto-configuration + LocalStack integration tests

## Context
Worktree: D:/hantsylabs/aws-sdk-kotlin-spring-example/.worktrees/awskotlin-spring-port
No commits authorized. Do not modify examples/list/utilities/aws-build-tool. Do not dispatch subagents.
Tasks 5/6/7 created sqs template, starter auto-config, and listener runtime. This task wires the registrar into starter-sqs and adds LocalStack integration tests.

Carried Important finding from Task 7: proxied listener beans are silently skipped (CGLIB overrides don't carry method annotations; JDK proxies expose only interface methods). Fix discovery in this task using Spring AOP target-class utilities.

## Global constraints
- Prefix awskotlin; Spring Boot 4.2.0; AWS SDK Kotlin 1.9.45; Kotlin 2.4.10; Java 25.
- User beans win; combined enabled condition already on SqsAutoConfiguration.
- Standard queues only; missing queues fail startup; no implicit creation.
- LocalStack tests skip unless Docker available AND LOCALSTACK_AUTH_TOKEN set (LocalStack 4). Use localstack/localstack:4 image.
- JUnit 6 test methods return Unit.

## Part A — registrar auto-configuration
Modify starters/starter-sqs:
1. Add a BeanPostProcessor/SmartLifecycle bean for SqsListenerAnnotationRegistrar in SqsAutoConfiguration (or a nested static configuration class). Prefer a static @Bean (Kotlin companion object + @JvmStatic) so BeanPostProcessor registration does not force early auto-config instantiation.
2. Bean method signature roughly:
```kotlin
@Bean
@ConditionalOnMissingBean
fun sqsListenerAnnotationRegistrar(
    template: SqsTemplate,
    queueResolver: SqsQueueResolver,
    properties: SqsProperties,
): SqsListenerAnnotationRegistrar = SqsListenerAnnotationRegistrar(
    template = template,
    queueResolver = queueResolver,
    config = SqsListenerConfig(
        autoStartup = properties.listener.autoStartup,
        maxConcurrentMessages = properties.listener.maxConcurrentMessages,
        pollTime = properties.listener.pollTime,
        shutdownTimeout = properties.listener.shutdownTimeout,
    ),
)
```
3. User-provided SqsListenerAnnotationRegistrar bean wins.
4. Do not add listener.enabled property unless required; autoStartup=false must prevent automatic polling while still allowing manual start.
5. Add/adjust context tests:
   - default context contains registrar.
   - user registrar bean wins.
   - listener config properties map into registrar (use reflection/test accessor if needed, or assert via behavior with autoStartup=false).
   - awskotlin.enabled=false / awskotlin.sqs.enabled=false disable registrar.

## Part B — proxy-aware listener discovery
Modify sqs listener registrar to discover annotations on the target class behind Spring proxies:
- Add catalog library `springAop = { module = "org.springframework:spring-aop" }` and `implementation(libs.springAop)` to sqs module.
- Use `AopProxyUtils.ultimateTargetClass(bean)` (or AopUtils.getTargetClass + ClassUtils.getUserClass) for annotation scanning.
- If bean is an Advised proxy with a singleton target, use the target object as the endpoint invocation bean; otherwise use the bean itself. Do not break plain beans.
- Keep internal/private Kotlin visibility fix from Task 7.
- Add a registrar unit test using ProxyFactory with proxyTargetClass=true (CGLIB) around a listener bean; assert discovery and invocation. If JDK-proxy interface limitation remains, document it as a deferred minor rather than expanding scope.

## Part C — LocalStack integration tests
Add tests under starters/starter-sqs/src/test/kotlin/... (or sqs module if cleaner, but starter is preferred for auto-config integration).
Build deps: add testcontainers bundle, awaitility bundle, and any needed test libs to starter-sqs build. Follow s3/src/test/kotlin/.../S3TemplateLocalStackTest.kt skip pattern exactly:
- @TestInstance(PER_CLASS)
- @BeforeAll assumeTrue LOCALSTACK_AUTH_TOKEN non-blank and DockerClientFactory.instance().isDockerAvailable
- LocalStackContainer(DockerImageName.parse("localstack/localstack:4")).withServices("sqs").withEnv token
- SqsClient with StaticCredentialsProvider test/test, region us-east-1, endpointUrl http://host:port
- @AfterAll close client/container best-effort.

Required integration coverage (all skip-safe):
1. Template send/receive round trip: create queue, send payload, receive AUTO deletes; receive empty returns null after short timeout.
2. Manual acknowledgement: receive MANUAL, acknowledge deletes; reject makes message visible again (visibility 0) and can be received again.
3. Listener auto-ack through Spring context: ApplicationContextRunner or SpringApplication with SqsAutoConfiguration + AwsKotlinAutoConfiguration, endpoint properties pointing at LocalStack, a listener bean with @SqsListener(queueName), send via template, await receipt and deletion.
4. Listener manual Acknowledge.accept through Spring context deletes message.
5. Missing queue fails listener startup (context fails to start) if practical with LocalStack; otherwise unit coverage from Task 7 is acceptable—document choice.
Use awaitility for async receipt; keep timeouts bounded. Use unique queue names. Do not require real AWS.

## Verification
Run from worktree root:
- `./gradlew :sqs:test :starters:starter-sqs:test`
- `./gradlew :sqs:lintKotlin :starters:starter-sqs:lintKotlin`
LocalStack tests may skip; report skipped count and reason. Do not run full examples tests.

## Report contract
Write full report to `.superpowers/sdd/aws-kotlin-port/task-8-report.md`.
Return only status, one-line test summary, concerns.