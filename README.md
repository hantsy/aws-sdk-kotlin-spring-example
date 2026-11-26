# AWS SDK for Kotlin and Spring Examples
[AWS SDK for Kotlin](https://github.com/awslabs/aws-sdk-kotlin) is not an extension of the existing [AWS SDK for Java](https://github.com/aws/aws-sdk-java), it is rewritten in Kotlin from scratch, and ready for Kotlin Multiple Platform.

## Stack

| Component | Version |
| --- | --- |
| Spring Boot | 4.2.0 |
| Kotlin | 2.4.10 |
| Java | 25 |
| Gradle | 9.9.2 |
| JUnit Jupiter | 6.1.3 (JUnit 6) |
| Kotest assertions | 6.2.7 |
| AWS SDK for Kotlin | 1.9.45 |
| OkHttp | 5.5.1 |
| Jackson | 3.x (examples, via `tools.jackson.module:jackson-module-kotlin`) |
| kotlinx-serialization | 1.9.0 (port modules) |
| Testcontainers | 2.0.5 |

## Modules

* `core`, `s3`, `secrets-manager`, `sns`, `sqs` – AWS SDK for Kotlin support libraries
  (artifacts `awskotlin-core`, `awskotlin-s3`, ...). JSON conversion uses kotlinx-serialization.
* `starters/<service>-starter` – Spring Boot auto-configuration starters
  (artifacts `awskotlin-<service>-spring-boot-starter`).
* `examples` – the Spring Boot 4 application: S3, SQS and Secrets Manager services
  built on the AWS SDK for Kotlin, plus the integration tests. Keeps Jackson for WebFlux HTTP.

## Testing

The integration tests run against a LocalStack container that is started by the test
suite itself; the endpoint is injected with `@DynamicPropertySource`, because
LocalStack has no Spring Boot service connection.

The tests run against `localstack/localstack:4`, which needs a license token that is
taken from the environment:

| Variable | Purpose |
| --- | --- |
| `LOCALSTACK_AUTH_TOKEN` | license token of the LocalStack image |

```bash
export LOCALSTACK_AUTH_TOKEN=<your token>
./gradlew build
```

## Resources

* [AWS SDK for Kotlin (Developer Preview)](https://aws.amazon.com/sdk-for-kotlin/)
