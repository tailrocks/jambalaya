plugins {
    kotlin("jvm")
}

version = Versions.jambalayaKotlinTest
description = "Kotlin Test utils."

dependencies {
    testImplementation(project(":jambalaya-kotlin-test-graphql-example"))

    // Kotlin
    testImplementation(kotlin("stdlib-jdk8"))
    testImplementation(kotlin("test-junit5"))
}
