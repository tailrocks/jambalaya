plugins {
    `java-library`
    `maven-publish`
}

version = Versions.jambalayaTenancyJooq
description = "Tenancy jOOQ."

dependencies {
    api(project(":jambalaya-checks"))
    api(project(":jambalaya-tenancy"))

    api("org.jooq:jooq:${Versions.jooq}")
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
