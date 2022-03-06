plugins {
    `java-library`
    id("com.tailrocks.kotlin")
    id("com.tailrocks.junit")
}

version = jambalayaLibs.versions.jambalaya.kotlin.test.get()
description = "Kotlin Test utils."

apply(plugin = "com.tailrocks.maven-publish")

dependencies {
    testImplementation(project(":jambalaya-example-kotlin-test-graphql"))

    // Kotlin
    testImplementation(kotlin("stdlib-jdk8"))
    testImplementation(kotlin("test-junit5"))
}
