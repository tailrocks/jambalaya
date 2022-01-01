plugins {
    `java-library`
    `maven-publish`
}

version = jambalayaLibs.versions.jambalaya.tenancy.asProvider().get()
description = "Tenancy."

dependencies {
    api(project(":jambalaya-checks"))
    api(project(":jambalaya-opentelemetry"))
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
