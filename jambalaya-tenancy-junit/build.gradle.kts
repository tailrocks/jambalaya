plugins {
    `java-library`
    `maven-publish`
}

version = Versions.jambalayaTenancyJunit
description = "Tenancy JUnit."

dependencies {
    api(project(":jambalaya-tenancy"))

    // JUnit
    api("org.junit.jupiter:junit-jupiter-api:${Versions.junit}")
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
