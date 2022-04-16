plugins {
    `java-library`
    id("com.tailrocks.kotlin")
    id("com.tailrocks.junit")
}

version = jambalayaLibs.versions.jambalaya.jsr310.get()
description = "JSR 310 utils."

apply(plugin = "com.tailrocks.maven-publish")
apply(plugin = "com.tailrocks.signing")

dependencies {
    // Kotlin
    testImplementation(kotlin("stdlib-jdk8"))
    testImplementation(kotlin("test-junit5"))
}
