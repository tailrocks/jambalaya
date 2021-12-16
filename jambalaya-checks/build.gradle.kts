plugins {
    `java-library`
    `maven-publish`
    kotlin("jvm") version Versions.kotlin
}

version = Versions.jambalayaChecks
description = "Preconditions."

dependencies {
    api("com.google.guava:guava:${Versions.guava}")

    // Kotlin
    testImplementation(kotlin("stdlib-jdk8"))
    testImplementation(kotlin("test-junit5"))
}

// POM name/description fix
publishing {
    publications {
        (getByName("mavenJava") as MavenPublication).apply {
            pom {
                name.set(project.name)
                description.set(project.description)
            }
        }
    }
}
