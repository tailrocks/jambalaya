plugins {
    `java-library`
    `maven-publish`
    kotlin("jvm")
}

version = jambalayaLibs.versions.jambalaya.checks.asProvider().get()
description = "Preconditions."

dependencies {
    api(jambalayaLibs.guava)

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
