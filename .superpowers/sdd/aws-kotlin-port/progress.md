# SDD ledger — plan: approved in-conversation AWS SDK Kotlin Spring Boot port plan (internal briefs in this directory)

Worktree: D:/hantsylabs/aws-sdk-kotlin-spring-example/.worktrees/awskotlin-spring-port
Branch: awskotlin-spring-port
BASE: 5e56ebb730a4e340125aec61632f9994ee7126bd
Spec: approved conversation design; no separate user-requested spec document.

## Global constraints
- AWS SDK for Kotlin 1.9.45; Spring Boot 4.2.0; Kotlin 2.4.10; Java 25; Gradle wrapper 9.9.2.
- Prefix awskotlin; package io.github.hantsy.awskotlin.
- Modules core/s3/secrets-manager/sqs/sns and starters/starter-*; artifact IDs awskotlin-spring-*.
- No Java AWS SDK, Spring Cloud, Spring Integration.
- Do not modify examples/list/utilities/aws-build-tool.
- SDK default credential/region chains with optional overrides; static credentials require both keys; user beans win.
- SQS template auto-ack by default; listener Acknowledge.accept/reject; reject visibility zero; errors/no decision leave message.
- Standard queues only; FIFO fails fast; missing queues fail listener startup; no implicit queue creation.
- LocalStack tests skip without Docker/token.
- No commits unless explicitly authorized.

## Preflight scan
| Tasks | Shared file/interface | Finding |
|---|---|---|
| 1 -> 2/3/4/6 | AwsKotlinProperties, CredentialsProvider | Consumers use global region/endpoint and provider bean; consistent. |
| 1 -> 6 | AutoConfiguration.imports | Core and starters register separate classes; consistent. |
| 2 -> 9 | S3Template/S3Client | Verification targets match created modules. |
| 3 -> 9 | SecretsManagerTemplate/Client | Verification targets match created modules. |
| 4 -> 9 | SnsTemplate/Client | Verification targets match created modules. |
| 5 -> 6 | SqsMessageConverter, SqsTemplate | Starter consumes converter/template signatures; consistent. |
| 5 -> 7 | SqsQueueResolver, SqsMessageConverter | Listener consumes resolver/converter; consistent. |
| 6 -> 7/8 | SqsProperties.Listener | Listener runtime/auto-config consume same property type; consistent. |
| 7 -> 8 | SqsListenerAnnotationRegistrar | Auto-config creates registrar; consistent. |
| 1 self | build script/tests | kapt plugin added before core build uses it; consistent. |
| 2 self | S3 get/exists | get uses response-transform; exists catches NotFound only; consistent. |
| 5 self | resolver suspend/cache | plan allows Mutex replacement if computeIfAbsent/runBlocking problematic; public signature stable. |
| 7 self | container/test dispatcher | injectable dispatcher supports virtual-time tests; consistent. |

## Rulings
- Ruling: execute in isolated worktree without commits — user authorized continuation but not commits; cost if wrong: work must later be merged/committed by user.
- Ruling: internal SDD briefs/ledger are git-ignored scratch, not user-facing plan docs — preserves no-plan-document preference; cost if wrong: scratch artifacts need cleanup.
- Ruling: core always registers a CredentialsProvider bean using SDK DefaultChainCredentialsProvider unless user bean exists — satisfies SDK-default chain and backoff; cost if wrong: extra provider bean lifecycle management.
- Ruling: S3 get returns ByteArray? consumed inside SDK response-transform — avoids returning closed streaming body; cost if wrong: metadata access deferred.
- Ruling: SNS topic selector accepts topicArn only in stage one — avoids implicit topic creation/name resolution not approved; cost if wrong: name-based convenience deferred.

## Progress
Task 1: minor (deferred): weak startupFailure assertion in AwsKotlinAutoConfigurationTest; metadata drift risk; inaccurate build comment; missing trailing newlines; unused springmockk.
Task 1: complete (working tree, no commits; review clean)
Task 2: minor (deferred): strict mock S3Client close noise; best-effort LocalStack bucket cleanup; missing trailing newlines; same-line trailing commas; copied gradle.properties comment.
Task 2: Ruling: use modeled error class NotFound (not NotFoundException) for S3 head not-found — verified in Smithy model and published jar; cost if wrong: none, semantics unchanged.
Task 2: complete (working tree, no commits; review clean)
Task 1: minor (deferred): weak startupFailure assertion; metadata drift risk; inaccurate build comment; missing trailing newlines; unused springmockk.
Task 1: complete (working tree, no commits; review clean)
Task 2: minor (deferred): strict mock S3Client close noise; best-effort LocalStack cleanup; missing trailing newlines; same-line trailing commas; copied gradle.properties comment.
Task 2: Ruling: use modeled error class NotFound (not NotFoundException) for S3 head not-found — verified in Smithy model and published jar; cost if wrong: none.
Task 2: complete (working tree, no commits; review clean)
Task 3+4: dispatched batch implementer; SNS listener feasibility research requested by user.
Task 3+4: minor (deferred): missing final newlines; user-client-wins tests could assert delegation; cosmetic trailing commas; awsSdkKotlin bundle omits SNS (final review).
Task 3+4: complete (working tree, no commits; review clean)
Task 18: Ruling: AWS SDK Kotlin SNS has no receive/poll operation and no Notification/NotificationStatus shapes; SNS listener must use SNS->SQS fan-out or external HTTP endpoint. Choose SNS->SQS envelope support reusing SQS listener; defer HTTP endpoint/signature verification. Cost if wrong: HTTP-native SNS listener not provided in stage one.
Task 18: complete (research only; findings recorded)
Task 5: Ruling: name module base error SqsTemplateException instead of SqsException to avoid collision with SDK aws.sdk.kotlin.services.sqs.model.SqsException — keeps typed errors unambiguous; cost if wrong: public error name differs from earlier sketch.
Task 5: minor (deferred): resolver Mutex serializes different-name lookups; send sub-second negative delay truncates to 0 (brief formula); error mapping extended to send/receive.
Task 5: Ruling: use messageSystemAttributeNames = listOf(MessageSystemAttributeName.All) for receive system attributes — attributeNames is List<QueueAttributeName> in 1.9.45 jar; cost if wrong: none, verified API.
Task 5: complete (working tree, no commits; review clean)
Task 6: minor (deferred): converter @Bean non-static; KotlinDurationConverter.matches could guard target type; bare-number Duration binding intentionally unsupported; Boot/Framework version fragility.
Task 6: Ruling: keep @KotlinDuration + KotlinDurationConverter in starter-sqs for now; promote to core only if another starter needs it — smallest fix preserving kotlin.time.Duration properties; cost if wrong: duplicated converter later.
Task 6: complete (working tree, no commits; review clean)
Task 7: minor (deferred): graceful-stop snapshot race; stop holds monitor across runBlocking; permit leak on launch-into-cancelled-scope; misleading auto-ack failure log; no maxConcurrentMessages cap test; star-projection error text.
Task 7: Important open: registrar Java-method prefilter crashes on internal @SqsListener and skips private ones; proxy discovery deferred to Task 8.
Task 7: fix round 1/5 (1 addressed, 0 open; working-tree fix, no commits)
Task 7: minor (deferred): last-resort overload fallback could bind wrong overload; inherited private listeners undiscoverable; AOP proxy discovery carried to Task 8.
Task 7: complete (working tree, no commits; review clean)
Task 8: minor (deferred): LocalStack listener deletion assertions timing-weak; singleton-target invocation bypasses AOP advice; BPP early client graph; JDK non-static target source limitation.
Task 8: complete (working tree, no commits; review clean; LocalStack runtime token-gated)
Task 19: minor (deferred): optional non-string envelope fields coerced null; fan-out test partial-setup cleanup; sns build/properties missing final newlines; subscribe/setRaw race inherent.
Task 19: Ruling: SNS SetSubscriptionAttributes uses single attributeName/attributeValue, not attributes map — jar-verified; cost if wrong: none.
Task 19: complete (working tree, no commits; review clean; LocalStack runtime token-gated)

## Cleanup pass (user request)
Ruling: remove list/utilities and aws-build-tool — only list/utilities consume buildlogic conventions; examples/port modules do not. Cost if wrong: template scaffolding lost.
Ruling: module artifact IDs drop 'spring' (awskotlin-core/s3/secrets-manager/sns/sqs); starter artifacts follow Boot guide awskotlin-<svc>-spring-boot-starter; starter dirs renamed starters/<svc>-starter. Cost if wrong: folder layout differs from user expectation.
Ruling: port modules replace Jackson with kotlinx-serialization-json; examples keeps Jackson for WebFlux HTTP. Cost if wrong: examples still depends on Jackson.
Ruling: remove kapt + spring-boot-configuration-processor; ship hand-written META-INF/spring-configuration-metadata.json instead of processor-generated metadata. Cost if wrong: metadata must be maintained manually.
Task 20: minor (deferred): kotlinxSerializationJson implementation vs api; unreachable null branch; root plugin alias cosmetic; stale build/libs jars.
Task 20: complete (working tree, no commits; review clean)

## Examples starter adoption (user request)
Ruling: examples consume s3/secrets-manager/sqs starters only; SNS starter not demonstrated because examples have no SNS flow. Cost if wrong: SNS example missing.
Ruling: keep public example interfaces and LocalStack assertions; use starter-provided raw SDK clients only where templates lack operations (S3 bucket creation, SQS queue creation/batch receive, Secrets write description/forceOverwrite). Cost if wrong: examples still touch raw SDK clients.
Ruling: replace scheduled AutomaticMessageReceiver with @SqsListener; add kotlinx-serialization plugin/runtime to examples for converter payloads. Cost if wrong: examples build differs from old Jackson-only setup.
Task 21: dispatched implementer (brief task-21-brief.md; no commits).
Task 21: environment check — Docker daemon available (29.9.3); BASE HEAD 5e56ebb (no commits).
Ruling: delete examples LocalstackTestContainer wrapper; use official testcontainers-localstack LocalStackContainer with @Testcontainers/@Container and LOCALSTACK_AUTH_TOKEN gating — user request; jar API verified (2.0.5). Cost if wrong: test lifecycle differs from old shared singleton.
Task 21: re-dispatched implementer with updated brief (official Testcontainers LocalStack).
Task 21: implementer DONE — compile/lint green; examples 4 tests token-gated skipped; full suite green without token. Review dispatched; full suite with LOCALSTACK_AUTH_TOKEN running locally.
Task 22: GitHub secret LOCALSTACK_AUTH_TOKEN set via gh; user chose direct push to master; origin/master at BASE 5e56ebb.
Task 21: local full suite with LOCALSTACK_AUTH_TOKEN — examples LocalStack tests 4/4 PASSED (port tests up-to-date from prior run).
Task 22: committed 45203d0 (106 files) and pushed HEAD:master (5e56ebb..45203d0); CI run 45807820274 in_progress.
Task 21: review Approved (spec PASS); 1 Minor — application.properties trailing newline; working tree already clean (no diff), finding not reproducible.
Task 21: complete (committed in 45203d0; pushed to master).
