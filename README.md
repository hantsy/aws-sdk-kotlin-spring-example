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
| Jackson | 3.x (via `tools.jackson.module:jackson-module-kotlin`) |
| Testcontainers | 2.0.5 |

## Modules

* `list` – a small plain Kotlin library (LinkedList) with JUnit 6 style tests.
* `utilities` – plain Kotlin string helpers.
* `examples` – the Spring Boot 4 application: S3, SQS and Secrets Manager services
  built on the AWS SDK for Kotlin, plus the integration tests.

Shared build conventions live in the `aws-build-tool` included build.

## Testing

The integration tests run against a LocalStack container that is started by the test
suite itself; the endpoint is injected with `@DynamicPropertySource`, because
LocalStack has no Spring Boot service connection.

LocalStack 4 needs a license token, both are taken from the environment:

| Variable | Purpose |
| --- | --- |
| `LOCALSTACK_AUTH_TOKEN` | license token of the LocalStack image |
| `LOCALSTACK_IMAGE` | image to run, defaults to `localstack/localstack:4` |

```bash
export LOCALSTACK_AUTH_TOKEN=<your token>
./gradlew build
```

The license free community edition, `localstack/localstack:3.0`, can be used as well:

```bash
LOCALSTACK_IMAGE=localstack/localstack:3.0 AWS_DISABLE_CHECKSUM_VALIDATION=true ./gradlew build
```

LocalStack 3.x does not implement the flexible checksum headers that the AWS SDK
validates by default, so the checksum validation has to be turned off for it, see
`aws.disable-checksum-validation`.

## Resources

* [AWS SDK for Kotlin (Developer Preview)](https://aws.amazon.com/sdk-for-kotlin/)
