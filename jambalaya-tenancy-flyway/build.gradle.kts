plugins {
    `java-library`
    `maven-publish`
}

version = jambalayaLibs.versions.jambalaya.tenancy.flyway.get()
description = "Tenancy Flyway."

dependencies {
    api(project(":jambalaya-checks"))

    api("org.flywaydb:flyway-core")
    api("org.slf4j:slf4j-api")
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
