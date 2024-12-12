import com.adarshr.gradle.testlogger.theme.ThemeType
import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion
import org.jmailen.gradle.kotlinter.tasks.FormatTask
import org.jmailen.gradle.kotlinter.tasks.LintTask

plugins {
    alias(libs.plugins.springBoot)
    alias(libs.plugins.dependencyManagement)
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.kotlinPluginSpring)
    alias(libs.plugins.kotlinter)
    alias(libs.plugins.testLogger)
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
    maven { url = uri("https://repo.spring.io/milestone") }
}

dependencies {

    // maven bom
    // implementation(platform("aws.sdk.kotlin:bom:1.0.0"))
    // implementation(platform("com.squareup.okhttp3:okhttp-bom:5.0.0-alpha.11"))

    // spring libs
    implementation(libs.bundles.core)
    //implementation("org.springframework.boot:spring-boot-starter-webflux")

    implementation(libs.bundles.kotlinSupport)
//    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
//    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
//    implementation("org.jetbrains.kotlin:kotlin-reflect")
//    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")

    // aws sdk kotlin
    implementation(libs.bundles.awsSdkKotlin)
//    implementation("aws.sdk.kotlin:s3:1.0.66")
//    implementation("aws.sdk.kotlin:sqs:1.0.66")
//    implementation("aws.sdk.kotlin:secretsmanager:1.0.66")

    // okhttp
    implementation(libs.bundles.okhttp)
//    implementation("com.squareup.okhttp3:okhttp:5.0.0-alpha.14")
//    implementation("com.squareup.okhttp3:okhttp-coroutines:5.0.0-alpha.14")
//    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.14")

    // test libs
    testImplementation(libs.bundles.testcore)
   // testImplementation(libs.bundles.mockk)
//    testImplementation("io.projectreactor:reactor-test")
//    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test")

    testImplementation(libs.bundles.testcontainers)
//    testImplementation("org.springframework.boot:spring-boot-testcontainers")
//    testImplementation("org.testcontainers:junit-jupiter")

    // https://mvnrepository.com/artifact/io.kotest/kotest-assertions-core-jvm
    testImplementation(libs.bundles.kotest)
    //testImplementation("io.kotest:kotest-assertions-core-jvm:5.8.1")
}

kotlin {
    jvmToolchain(21)
    compilerOptions {
        apiVersion.set(KotlinVersion.KOTLIN_2_0)
        languageVersion.set(KotlinVersion.KOTLIN_2_0)
        freeCompilerArgs.addAll(
            "-Xjsr305=strict",
            "-opt-in=kotlin.RequiresOptIn",
            "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi"
        )
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
    testLogging {
        events = setOf(TestLogEvent.PASSED, TestLogEvent.SKIPPED, TestLogEvent.FAILED)//, "standardOut", "standardError"

        showExceptions = true
        exceptionFormat = TestExceptionFormat.FULL
        showCauses = true
        showStackTraces = true
        showStandardStreams = false
    }
}

kotlinter {
    ignoreLintFailures = false
    reporters = arrayOf("checkstyle", "plain")
}

tasks.withType<LintTask>() {
    //dependsOn("kspKotlin")
    source = source.minus(fileTree("build/generated")).asFileTree
}

tasks.withType<FormatTask>() {
    //dependsOn("kspKotlin")
    source = source.minus(fileTree("build/generated")).asFileTree
}

// remove lintKotlin from check task's dependencies
project.afterEvaluate {
    val check = tasks.findByName("check")
    if (check != null) {
        val dependencies = check.taskDependencies.getDependencies(check).filter { it.name != "lintKotlin" }
        check.setDependsOn(dependencies)
    }
}

testlogger {
    // pick a theme - mocha, standard, plain, mocha-parallel, standard-parallel or plain-parallel
    theme = ThemeType.MOCHA

    // set to false to disable detailed failure logs
    showExceptions = true

    // set to false to hide stack traces
    showStackTraces = true

    // set to true to remove any filtering applied to stack traces
    showFullStackTraces = false

    // set to false to hide exception causes
    showCauses = true

    // set threshold in milliseconds to highlight slow tests
    slowThreshold = 2000

    // displays a breakdown of passes, failures and skips along with total duration
    showSummary = true

    // set to true to see simple class names
    showSimpleNames = false

    // set to false to hide passed tests
    showPassed = true

    // set to false to hide skipped tests
    showSkipped = true

    // set to false to hide failed tests
    showFailed = true

    // enable to see standard out and error streams inline with the test results
    showStandardStreams = false

    // set to false to hide passed standard out and error streams
    showPassedStandardStreams = true

    // set to false to hide skipped standard out and error streams
    showSkippedStandardStreams = true

    // set to false to hide failed standard out and error streams
    showFailedStandardStreams = true

    logLevel = LogLevel.LIFECYCLE
}
