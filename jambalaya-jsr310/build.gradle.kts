plugins {
    kotlin("jvm")
}

version = Versions.jambalayaJsr310

dependencies {
    // Kotlin
    testImplementation(kotlin("stdlib-jdk8"))
    testImplementation(kotlin("test-junit5"))
}
