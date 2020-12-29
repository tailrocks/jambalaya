plugins {
    kotlin("jvm")
}

version = Versions.jambalayaKotlinTest

dependencies {
    // Kotlin
    testImplementation(kotlin("stdlib-jdk8"))
    testImplementation(kotlin("test-junit5"))
}
