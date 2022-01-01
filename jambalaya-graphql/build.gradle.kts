plugins {
    `java-library`
    `maven-publish`
}

version = jambalayaLibs.versions.jambalaya.graphql.asProvider().get()
description = "GraphQL utils."

dependencies {
    api(project(":jambalaya-checks"))

    api(jambalayaLibs.java.dataloader)
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
