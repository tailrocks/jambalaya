plugins {
    `java-library`
    `maven-publish`
    kotlin("jvm") version Versions.kotlin
}

version = Versions.jambalayaSeo
description = "SEO utils."

dependencies {
    api(project(":jambalaya-checks"))

    api("com.github.slugify:slugify:${Versions.slugify}")

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
