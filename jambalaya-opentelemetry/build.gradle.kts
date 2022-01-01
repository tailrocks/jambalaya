plugins {
    `java-library`
    `maven-publish`
}

version = jambalayaLibs.versions.jambalaya.opentelemetry.get()
description = "OpenTelemetry."

dependencies {
    api(platform(jambalayaLibs.boms.opentelemetry))
    api("io.opentelemetry:opentelemetry-api")
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
