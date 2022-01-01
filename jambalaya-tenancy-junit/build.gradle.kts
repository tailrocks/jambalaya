plugins {
    `java-library`
    `maven-publish`
}

version = jambalayaLibs.versions.jambalaya.tenancy.junit.get()
description = "Tenancy JUnit."

dependencies {
    api(project(":jambalaya-tenancy"))

    // JUnit
    api("org.junit.jupiter:junit-jupiter-api")
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
