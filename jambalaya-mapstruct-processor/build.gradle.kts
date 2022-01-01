plugins {
    `java-library`
    `maven-publish`
}

version = jambalayaLibs.versions.jambalaya.mapstruct.processor.get()
description = "MapStruct smart SPI."

dependencies {
    api(project(":jambalaya-mapstruct"))

    api(jambalayaLibs.mapstruct.processor)
    api(jambalayaLibs.mapstruct)
    api(jambalayaLibs.guava)
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
