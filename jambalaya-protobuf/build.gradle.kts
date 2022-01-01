plugins {
    `java-library`
    `maven-publish`
    kotlin("jvm")
}

version = jambalayaLibs.versions.jambalaya.protobuf.get()
description = "Protobuf utils."

dependencies {
    api(project(":jambalaya-checks"))

    api(jambalayaLibs.protobuf.java)

    api(jambalayaLibs.proto.google.common.protos)

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
