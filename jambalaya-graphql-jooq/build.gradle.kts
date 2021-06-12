plugins {
    `java-library`
    `maven-publish`
}

version = Versions.jambalayaGraphqlJooq
description = "GraphQL jOOQ utils."

dependencies {
    api(project(":jambalaya-checks"))

    implementation("org.jooq:jooq:${Versions.jooq}")
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
