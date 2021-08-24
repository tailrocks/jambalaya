plugins {
    `java-library`
    `maven-publish`
}

version = Versions.jambalayaTenancyJooq
description = "Tenancy jOOQ."

dependencies {
    api(project(":jambalaya-checks"))
    api(project(":jambalaya-tenancy"))

    // Micronaut
    implementation(platform("io.micronaut:micronaut-bom:${Versions.micronaut}"))

    api("org.jooq:jooq")
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
