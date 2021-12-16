plugins {
    `java-library`
    `maven-publish`
    kotlin("jvm") version Versions.kotlin
}

version = Versions.jambalayaMicronautMapstructProtobuf
description = "MapStruct protobuf utils."

dependencies {
    api(project(":jambalaya-protobuf"))

    // MapStruct
    annotationProcessor("org.mapstruct:mapstruct-processor:${Versions.mapstruct}")
    api("org.mapstruct:mapstruct:${Versions.mapstruct}")

    // Micronaut
    implementation(platform("io.micronaut:micronaut-bom:${Versions.micronaut}"))
    annotationProcessor(platform("io.micronaut:micronaut-bom:${Versions.micronaut}"))
    annotationProcessor("io.micronaut:micronaut-inject-java")
    compileOnly("io.micronaut:micronaut-inject-java")

    // Jakarta
    compileOnly("jakarta.inject:jakarta.inject-api")

    // Kotlin
    testImplementation(kotlin("stdlib-jdk8"))
    testImplementation(kotlin("test-junit5"))

    // TODO remove me later
    compileOnly("javax.inject:javax.inject:1")
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
