plugins {
    `java-library`
    `maven-publish`
}

version = Versions.jambalayaTenancy
description = "Micronaut Tenancy."

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
