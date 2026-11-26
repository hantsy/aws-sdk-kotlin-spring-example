# Task 6 Report — SQS starter auto-configuration

Status: DONE_WITH_CONCERNS (one documented, necessary deviation from the brief's property snippet; all required behavior implemented and verified)

## Scope delivered

New module `starters/starter-sqs` (artifact `awskotlin-spring-starter-sqs`, package `io.github.hantsy.awskotlin.sqs.autoconfigure`), auto-configuration only. No listener runtime. No commits made; all changes left in the working tree. `examples`, `list`, `utilities`, `aws-build-tool` untouched.

### Files created
- `starters/starter-sqs/build.gradle.kts` — mirrors `starter-s3` exactly (kapt applied without version, `additionalMetadataLocations` kapt arg pointing at `src/main/resources`, ktlint compiler pin block for `kotlin-compiler-embeddable`/`kotlin-daemon-embeddable`/`kotlin-script-runtime`/`kotlin-build-tools-api` to 2.2.21, `java-library`, Boot BOM via dependency-management). Dependencies: `api(project(":sqs"))`, `api(project(":core"))`, `implementation(libs.springBootAutoconfigure)`, `implementation(libs.kotlinStdlib)`, `kapt(libs.springBootConfigurationProcessor)`, `testImplementation(libs.springBootStarterTest)`, kotest/mockk/kotlintest bundles, `testRuntimeOnly(libs.junitPlatformLauncher)`.
- `starters/starter-sqs/gradle.properties` — byte-for-byte copy of the starter-s3 workaround (`kotlin.compiler.runViaBuildToolsApi=false` with the same comment).
- `src/main/kotlin/io/github/hantsy/awskotlin/sqs/autoconfigure/SqsProperties.kt`
- `src/main/kotlin/io/github/hantsy/awskotlin/sqs/autoconfigure/SqsAutoConfiguration.kt`
- `src/main/kotlin/io/github/hantsy/awskotlin/sqs/autoconfigure/KotlinDurationConverter.kt` (deviation, see below)
- `src/main/resources/META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports`
- `src/main/resources/META-INF/additional-spring-configuration-metadata.json`
- `src/test/kotlin/io/github/hantsy/awskotlin/sqs/autoconfigure/SqsAutoConfigurationTest.kt`

### Files modified
- `settings.gradle.kts` — added `include("starters:starter-sqs")` (only line added by this task).

All text files end with a newline (verified byte-wise).

## Implementation

### SqsProperties
Exactly the brief's shape: `enabled=true`, `endpoint=null`, `region=null`, nested `Listener(autoStartup=true, maxConcurrentMessages=10, pollTime=10.seconds, shutdownTimeout=20.seconds)` using `kotlin.time.Duration`. No validation. The only addition is the `@KotlinDuration` marker annotation on the two Duration constructor parameters (see deviation below).

### SqsAutoConfiguration
- `@AutoConfiguration(after = [AwsKotlinAutoConfiguration::class])`, `@ConditionalOnClass(SqsClient::class)`, combined `@ConditionalOnProperty(prefix="awskotlin", name=["enabled","sqs.enabled"], havingValue="true", matchIfMissing=true)`, `@EnableConfigurationProperties(SqsProperties::class)`.
- `sqsClient(properties, sqsProperties, credentialsProvider)` — AWS SDK Kotlin `SqsClient { }` DSL; `region = sqsProperties.region ?: properties.region`; injected core `CredentialsProvider` bean; `endpoint = sqsProperties.endpoint ?: properties.endpoint`, applied via `endpointUrl = Url.parse(it)` when non-null. No `forcePathStyle` (S3-specific).
- `sqsMessageConverter()` = `JacksonSqsMessageConverter()`, `sqsQueueResolver(client)` = `SqsQueueResolver(client)`, `sqsTemplate(client, converter, queueResolver)` = `SqsTemplate(client, converter, queueResolver)`. All `@Bean @ConditionalOnMissingBean` so user beans win.
- Additional `@Bean @ConfigurationPropertiesBinding kotlinDurationConverter()` (deviation, below).

### Metadata
`additional-spring-configuration-metadata.json` documents all 8 constructor-bound properties in kebab-case: `awskotlin.sqs.enabled` (Boolean, true), `.endpoint` (String), `.region` (String), `.listener` (group, `SqsProperties$Listener`), `.listener.auto-startup` (Boolean, true), `.listener.max-concurrent-messages` (Integer, 10), `.listener.poll-time` (`kotlin.time.Duration`, "10s"), `.listener.shutdown-timeout` (`kotlin.time.Duration`, "20s"). Verified the kapt-generated `build/tmp/kapt3/classes/main/META-INF/spring-configuration-metadata.json` contains all entries with correct types/defaults.

## Deviation: kotlin.time.Duration binding support (necessary)

The brief's verbatim property snippet does not bind under Spring Boot 4.2.0 as-is. Evidence gathered during implementation:

1. First test run: 10/11 passed; `custom listener values bind` failed with `ConversionFailedException: Failed to convert from type [java.lang.String] to type [long] for value [5s]` / `BindException: Failed to bind properties under 'awskotlin.sqs.listener.poll-time' to long`.
2. Root cause (verified against spring-boot-4.2.0 and spring-core-7.1.0 sources): `ValueObjectBinder.KotlinValueObject` derives parameter types via `ReflectJvmMapping.getJavaType(...)`, which erases non-null Kotlin value classes to their underlying JVM type (`long` for `kotlin.time.Duration`). Neither Boot nor Framework ships any `kotlin.time` converter (grep of both source jars: zero hits), so a plain `Converter<String, Duration>` is never consulted.
3. Fix implemented without changing the public property API (names, types, defaults stay verbatim):
   - `@KotlinDuration` marker annotation (VALUE_PARAMETER, RUNTIME) on `pollTime`/`shutdownTimeout`. Boot passes constructor-parameter annotations into the conversion `TypeDescriptor` (confirmed in the failure trace: target shown as `@KotlinDuration long`).
   - `KotlinDurationConverter : ConditionalGenericConverter`, registered as a `@ConfigurationPropertiesBinding` bean (these beans are tried first by `ConversionServiceDeducer`). It matches only annotated targets (or a boxed `Duration` target), so no other property binding is affected.
   - Two non-obvious mechanics discovered empirically and documented in code comments:
     a. Convertible pairs must use `Long::class.javaObjectType` (`java.lang.Long`), not primitive `long`: `GenericConversionService` resolves primitives to wrappers during converter lookup, so a `(String, long)` registration never matches.
     b. `Binder.handleBindResult` re-converts the bound result to the declared target type, so the converter must also accept `(Duration -> java.lang.Long)` and return the boxed Duration unchanged; Kotlin constructor invocation (`BeanUtils` -> `KFunction.callBy`) then receives the boxed value class instance correctly.
   - Accepted value formats: Kotlin duration strings (`10s`, `500ms`) and ISO-8601 (`PT10S`), via `Duration.parse`. Plain unit-less numbers are rejected (no implicit unit is invented).

This adds one file and one bean beyond the brief's list; everything else is verbatim. Tasks 7/8 should reuse `@KotlinDuration` + `KotlinDurationConverter` for any further Duration-typed configuration properties (or promote them to `core` if more modules need them).

## Tests

`SqsAutoConfigurationTest` (JUnit 6, all `@Test` methods have block bodies returning Unit; `ApplicationContextRunner` with `AutoConfigurations.of(AwsKotlinAutoConfiguration, SqsAutoConfiguration)`; assertions via `getBean`/`getBeanNamesForType`, no `hasSingleBean`):

1. default context provides SqsClient (region + StaticCredentialsProvider verified), SqsMessageConverter (Jackson instance), SqsQueueResolver, SqsTemplate, SqsProperties — one bean each
2. `awskotlin.enabled=false` removes all five bean types
3. `awskotlin.sqs.enabled=false` removes all five bean types
4. user SqsClient bean wins (relaxed mockk; template still auto-configured)
5. user SqsMessageConverter bean wins
6. user SqsQueueResolver bean wins (real resolver over relaxed mock client)
7. user SqsTemplate bean wins
8. sqs region/endpoint override global values (client config + bound properties asserted)
9. global endpoint fallback when sqs endpoint unset
10. listener defaults bind (true / 10 / 10s / 20s)
11. custom listener values bind (`auto-startup=false`, `max-concurrent-messages=5`, `poll-time=5s`, `shutdown-timeout=30s`)
12. ISO-8601 duration format binds (`PT2S`, `PT1M`)

## Verification (run from worktree root)

- `./gradlew :starters:starter-sqs:test` — BUILD SUCCESSFUL; 12 tests, 0 failures, 0 errors, 0 skipped (JUnit XML: `tests="12" skipped="0" failures="0" errors="0"`).
- `./gradlew :starters:starter-sqs:lintKotlin` — BUILD SUCCESSFUL (after `formatKotlin` applied the project's own formatting: single-line signatures where they fit, final newlines).
- Full examples tests not run, per brief.

## Concerns

1. The Duration binding workaround relies on Boot binder internals (parameter-annotation propagation into TypeDescriptor, `handleBindResult` re-conversion, wrapper-type converter lookup). It is covered by tests 10-12 and documented in code, but a future Boot upgrade could change these mechanics; re-run these tests on any Boot bump. Longer term, native value-class/`kotlin.time.Duration` binding support upstream would let this be deleted.
2. `@KotlinDuration`/`KotlinDurationConverter` currently live in starter-sqs. If Task 7/8 (or other modules) add Duration properties, reuse or promote them to `core` rather than duplicating.
3. Duration properties accept only unit-suffixed or ISO-8601 strings (e.g. `10s`, `PT10S`); a bare number like `10` fails binding by design. Documented in the metadata descriptions.
4. Pre-existing working-tree state: earlier tasks' files (core/s3/sns/secrets-manager/sqs modules, settings includes, libs.versions.toml) remain staged/modified as found; this task added only the `starter-sqs` include line and the new `starters/starter-sqs/` directory. Nothing committed.
