plugins {
    `java-library`
    `maven-publish`
}

version = jambalayaLibs.versions.jambalaya.mapstruct.asProvider().get()
description = "MapStruct utils."

dependencies {
    api(jambalayaLibs.mapstruct)
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
