plugins {
    `java-library`
    id("com.tailrocks.kotlin")
    id("com.tailrocks.junit")
}

version = jambalayaLibs.versions.jambalaya.checks.asProvider().get()
description = "Preconditions."

apply(plugin = "jambalaya-library-conventions")

dependencies {
    api("com.github.spotbugs:spotbugs-annotations:4.8.0")

    // Kotlin
    testImplementation(kotlin("stdlib-jdk8"))
    testImplementation(kotlin("test-junit5"))
}
