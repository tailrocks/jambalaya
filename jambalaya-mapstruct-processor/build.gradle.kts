plugins {
    `java-library`
    `maven-publish`
}

version = Versions.jambalayaMapstructProcessor
description = "MapStruct smart SPI."

dependencies {
    api(project(":jambalaya-mapstruct"))

    api("org.mapstruct:mapstruct-processor:${Versions.mapstruct}")
    api("org.mapstruct:mapstruct:${Versions.mapstruct}")
    api("com.google.guava:guava:${Versions.guava}")
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
