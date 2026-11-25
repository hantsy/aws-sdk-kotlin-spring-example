import org.gradle.api.artifacts.VersionCatalogsExtension
import org.jetbrains.kotlin.gradle.dsl.JvmDefaultMode

/*
 * Shared build configuration for the library modules of this build.
 */
plugins {
    // Apply the org.jetbrains.kotlin.jvm Plugin to add support for Kotlin.
    id("org.jetbrains.kotlin.jvm")
}

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
}

// Precompiled script plugins have no generated "libs" accessor, so the version catalog
// is looked up explicitly from the consuming build.
val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

dependencies {
    constraints {
        // Define dependency versions as constraints
        implementation("org.apache.commons:commons-text:1.14.0")
    }

    testImplementation(libs.findBundle("kotest").get())
    testImplementation(libs.findBundle("kotlintest").get())
    testRuntimeOnly(libs.findLibrary("junitPlatformLauncher").get())
}

testing {
    suites {
        // Configure the built-in test suite
        val test = getByName<JvmTestSuite>("test")
        test.useJUnitJupiter(libs.findVersion("junit").get().requiredVersion)
    }
}

kotlin {
    compilerOptions {
        jvmDefault = JvmDefaultMode.NO_COMPATIBILITY
        progressiveMode.set(true)
        freeCompilerArgs.addAll(
            "-Xjsr305=strict",
        )
        optIn.addAll(
            "kotlin.RequiresOptIn",
        )
    }
}

// Apply a specific Java toolchain to ease working on different environments.
java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(libs.findVersion("java").get().requiredVersion.toInt())
    }
}
