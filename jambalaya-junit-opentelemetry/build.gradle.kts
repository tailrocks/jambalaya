plugins {
    `java-library`
    `maven-publish`
}

version = Versions.jambalayaJunitOpentelemetry
description = "JUnit OpenTelemetry extension."

dependencies {
    // OpenTelemetry
    api("io.opentelemetry:opentelemetry-api:${Versions.opentelemetry}")
    api("io.opentelemetry:opentelemetry-semconv:${Versions.opentelemetrySemconv}")

    // JUnit
    api("org.junit.jupiter:junit-jupiter-api:${Versions.junit}")
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
