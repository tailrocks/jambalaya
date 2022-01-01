plugins {
    `java-library`
    `maven-publish`
}

version = jambalayaLibs.versions.jambalaya.micronaut.graphql.get()
description = "Micronaut GraphQL utils."

dependencies {
    api(project(":jambalaya-checks"))

    implementation("io.micronaut.graphql:micronaut-graphql")
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
