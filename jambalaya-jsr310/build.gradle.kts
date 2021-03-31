plugins {
    kotlin("jvm")
}

version = Versions.jambalayaJsr310
description = "JSR 310 utils."

dependencies {
    // Kotlin
    testImplementation(kotlin("stdlib-jdk8"))
    testImplementation(kotlin("test-junit5"))
}
