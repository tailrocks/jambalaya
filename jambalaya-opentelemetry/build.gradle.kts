plugins {
    `java-library`
    `maven-publish`
}

version = Versions.jambalayaOpentelemetry
description = "OpenTelemetry."

dependencies {
    api(platform("io.opentelemetry:opentelemetry-bom:${Versions.opentelemetry}"))
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
