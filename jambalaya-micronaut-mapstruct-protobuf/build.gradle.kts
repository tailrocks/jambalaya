plugins {
    `java-library`
    `maven-publish`
}

version = Versions.jambalayaMicronautMapstructProtobuf
description = "MapStruct protobuf utils."

dependencies {
    api(project(":jambalaya-protobuf"))

    // MapStruct
    annotationProcessor("org.mapstruct:mapstruct-processor:${Versions.mapstruct}")
    api("org.mapstruct:mapstruct:${Versions.mapstruct}")

    // Micronaut
    annotationProcessor("io.micronaut:micronaut-inject-java:${Versions.micronaut}")
    compileOnly("io.micronaut:micronaut-inject-java:${Versions.micronaut}")

    // Jakarta
    compileOnly("jakarta.inject:jakarta.inject-api:${Versions.jakartaInjectApi}")
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
