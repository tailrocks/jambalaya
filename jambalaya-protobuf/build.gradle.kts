plugins {
    kotlin("jvm") version Versions.kotlin
}

version = Versions.jambalayaProtobuf
description = "Protobuf utils."

dependencies {
    api(project(":jambalaya-checks"))

    api("com.google.protobuf:protobuf-java:${Versions.protobuf}")

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

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = "11"
    }
}
