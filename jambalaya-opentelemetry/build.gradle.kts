plugins {
    `java-library`
    `maven-publish`
}

version = Versions.jambalayaOpentelemetry
description = "Micronaut OpenTelemetry."

dependencies {
    implementation("io.opentelemetry:opentelemetry-api:${Versions.opentelemetry}")
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
