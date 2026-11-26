plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.dependencyManagement)
    alias(libs.plugins.kotlinter)
    `java-library`
}

group = "io.github.hantsy"
version = "0.1.0-SNAPSHOT"

base { archivesName.set("awskotlin-sqs-spring-boot-starter") }

repositories { mavenCentral() }

dependencyManagement {
    imports {
        mavenBom("org.springframework.boot:spring-boot-dependencies:${libs.versions.springboot.get()}")
    }
}

dependencies {
    api(project(":sqs"))
    api(project(":core"))
    implementation(libs.springBootAutoconfigure)
    implementation(libs.kotlinStdlib)
    testImplementation(libs.springBootStarterTest)
    testImplementation(libs.bundles.kotest)
    testImplementation(libs.bundles.mockk)
    testImplementation(libs.bundles.kotlintest)
    testImplementation(libs.kotlinCoroutinesCore)
    testImplementation(libs.bundles.testcontainers)
    testImplementation(libs.bundles.awaitility)
    testRuntimeOnly(libs.junitPlatformLauncher)
}

kotlin { jvmToolchain(libs.versions.java.get().toInt()) }

// ktlint 1.8.0 (applied by kotlinter) requires kotlin-compiler-embeddable 2.2.x.
// A resolution rule in this build aligns Kotlin artifacts to Gradle's embedded 2.4.21,
// which breaks ktlint ("Extensions storage is not registered"). Pin the compiler
// artifacts for the lint classpath only; compilation itself still uses Kotlin 2.4.10.
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

tasks.withType<Test> { useJUnitPlatform() }
