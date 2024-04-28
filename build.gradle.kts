import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {

//    id("org.springframework.boot") version "3.2.5"
    alias(libs.plugins.springBoot)
//    id("io.spring.dependency-management") version "1.1.4"
    alias(libs.plugins.dependencyManagement)
//    kotlin("jvm") version "1.9.23"
    alias(libs.plugins.kotlinJvm)
//    kotlin("plugin.spring") version "1.9.23"
    alias(libs.plugins.kotlinPluginSpring)
    alias(libs.plugins.kotlinter)
    alias(libs.plugins.testLogger)
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_21
}

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

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "21"
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
