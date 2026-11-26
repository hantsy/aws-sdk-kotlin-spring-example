import com.adarshr.gradle.testlogger.theme.ThemeType.MOCHA
import org.jetbrains.kotlin.gradle.dsl.JvmDefaultMode
import org.jmailen.gradle.kotlinter.tasks.FormatTask
import org.jmailen.gradle.kotlinter.tasks.LintTask

plugins {
    alias(libs.plugins.springBoot)
    alias(libs.plugins.dependencyManagement)
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.kotlinPluginSpring)
    alias(libs.plugins.kotlinPluginSerialization)
    alias(libs.plugins.kotlinter)
    alias(libs.plugins.testLogger)
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // spring libs
    implementation(libs.bundles.core)

    // kotlin support
    implementation(libs.bundles.kotlinSupport)

    // awskotlin spring boot starters (S3, Secrets Manager, SQS)
    implementation(project(":starters:s3-starter"))
    implementation(project(":starters:secrets-manager-starter"))
    implementation(project(":starters:sqs-starter"))

    // kotlinx converter runtime and @Serializable example payloads
    implementation(libs.kotlinxSerializationJson)

    // okhttp
    implementation(libs.bundles.okhttp)

    // test libs
    testImplementation(libs.bundles.testcore)
    testImplementation(libs.bundles.testcontainers)
    testImplementation(libs.bundles.kotest)
    testImplementation(libs.bundles.mockk)
    testImplementation(libs.bundles.awaitility)
    testImplementation(libs.bundles.kotlintest)
    testRuntimeOnly(libs.junitPlatformLauncher)
}

kotlin {
    jvmToolchain(libs.versions.java.get().toInt())
    compilerOptions {
        jvmDefault = JvmDefaultMode.NO_COMPATIBILITY
        progressiveMode.set(true)
        freeCompilerArgs.addAll(
            "-Xjsr305=strict",
        )
        optIn.addAll(
            "kotlin.RequiresOptIn",
            "kotlinx.coroutines.ExperimentalCoroutinesApi",
        )
    }
}

// ktlint 1.8.0 (applied by kotlinter) requires kotlin-compiler-embeddable 2.2.x.
// Pin the compiler artifacts for the lint classpath only; compilation itself uses
// the Kotlin version from the version catalog.
configurations.named("ktlint") {
    resolutionStrategy.eachDependency {
        if (requested.group == "org.jetbrains.kotlin" &&
            requested.name in
            setOf(
                "kotlin-compiler-embeddable",
                "kotlin-daemon-embeddable",
                "kotlin-script-runtime",
                "kotlin-build-tools-api",
            )
        ) {
            useVersion("2.2.21")
            because("ktlint 1.8.0 is not compatible with kotlin-compiler-embeddable 2.4.x")
        }
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
    minHeapSize = "128m"
    maxHeapSize = "2048m"
}

tasks.withType<LintTask> {
    exclude { it.file.path.contains("build/generated") }
}

tasks.withType<FormatTask> {
    exclude { it.file.path.contains("build/generated") }
}

kotlinter {
    ignoreLintFailures = false
    reporters = arrayOf("checkstyle", "plain")
}

testlogger {
    theme = MOCHA
    showExceptions = true
    showStackTraces = true
    showFullStackTraces = false
    showCauses = true
    slowThreshold = 2000
    showSummary = true
    showPassed = true
    showSkipped = true
    showFailed = true
    showStandardStreams = false
    logLevel = LogLevel.LIFECYCLE
}

springBoot {
    buildInfo()
}
