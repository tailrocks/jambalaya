plugins {
    `java-library`
    `maven-publish`
}

version = jambalayaLibs.versions.jambalaya.tenancy.jooq.get()
description = "Tenancy jOOQ."

dependencies {
    api(project(":jambalaya-checks"))
    api(project(":jambalaya-tenancy"))

    // Micronaut
    implementation(platform(jambalayaLibs.boms.micronaut))

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
