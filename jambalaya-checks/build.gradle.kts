plugins {
    kotlin("jvm")
}

version = Versions.jambalayaChecks
description = "Preconditions."

dependencies {
    // Kotlin
    testImplementation(kotlin("stdlib-jdk8"))
    testImplementation(kotlin("test-junit5"))
}
