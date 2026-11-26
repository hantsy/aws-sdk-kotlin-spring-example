plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.dependencyManagement)
    alias(libs.plugins.kotlinter)
    `java-library`
}

group = "io.github.hantsy"
version = "0.1.0-SNAPSHOT"

base { archivesName.set("awskotlin-s3-spring-boot-starter") }

repositories { mavenCentral() }

dependencyManagement {
    imports {
        mavenBom("org.springframework.boot:spring-boot-dependencies:${libs.versions.springboot.get()}")
    }
}

dependencies {
    api(project(":s3"))
    api(project(":core"))
    implementation(libs.springBootAutoconfigure)
    implementation(libs.kotlinStdlib)
    testImplementation(libs.springBootStarterTest)
    testImplementation(libs.bundles.kotest)
    testImplementation(libs.bundles.mockk)
    testImplementation(libs.bundles.kotlintest)
    testRuntimeOnly(libs.junitPlatformLauncher)
}

kotlin { jvmToolchain(libs.versions.java.get().toInt()) }

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

tasks.withType<Test> { useJUnitPlatform() }