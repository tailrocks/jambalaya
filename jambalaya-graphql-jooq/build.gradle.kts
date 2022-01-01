plugins {
    `java-library`
    `maven-publish`
}

version = jambalayaLibs.versions.jambalaya.graphql.jooq.get()
description = "GraphQL jOOQ utils."

dependencies {
    api(project(":jambalaya-checks"))

    // Micronaut
    implementation(platform(jambalayaLibs.boms.micronaut))

    implementation("org.jooq:jooq")
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
