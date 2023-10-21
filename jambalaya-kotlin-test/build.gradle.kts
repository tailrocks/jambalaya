plugins {
    `java-library`
    id("com.tailrocks.kotlin")
    id("com.tailrocks.junit")
}

version = jambalayaLibs.versions.jambalaya.kotlin.test.get()
description = "Kotlin Test utils."

apply(plugin = "jambalaya-library-conventions")

dependencies {
    testImplementation(project(":jambalaya-example-kotlin-test-graphql"))

    api("com.github.spotbugs:spotbugs-annotations:4.8.0")

    // Kotlin
    testImplementation(kotlin("stdlib-jdk8"))
    testImplementation(kotlin("test-junit5"))
}
