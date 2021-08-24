plugins {
    `java-library`
    `maven-publish`
}

version = Versions.jambalayaJunitOpentelemetry
description = "JUnit OpenTelemetry extension."

dependencies {
    // OpenTelemetry
    implementation(platform("io.opentelemetry:opentelemetry-bom:${Versions.opentelemetry}"))
    implementation(platform("io.opentelemetry:opentelemetry-bom-alpha:${Versions.opentelemetryAlpha}"))
    api("io.opentelemetry:opentelemetry-api")
    api("io.opentelemetry:opentelemetry-semconv")

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
