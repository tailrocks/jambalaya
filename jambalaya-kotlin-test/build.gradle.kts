plugins {
    `java-library`
    id("com.tailrocks.gradle.maven-publish-conventions")
    id("com.tailrocks.gradle.signing-conventions")
    kotlin("jvm")
}

version = jambalayaLibs.versions.jambalaya.kotlin.test.get()
description = "Kotlin Test utils."

dependencies {
    testImplementation(project(":jambalaya-kotlin-test-graphql-example"))

    // Kotlin
    testImplementation(kotlin("stdlib-jdk8"))
    testImplementation(kotlin("test-junit5"))
}

publishing.publications {
    (getByName("mavenJava") as MavenPublication).apply {
        pom {
            description.set(project.description)
        }
    }
}
