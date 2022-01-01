plugins {
    `java-library`
    `maven-publish`
}

version = jambalayaLibs.versions.jambalaya.junit.opentelemetry.get()
description = "JUnit OpenTelemetry extension."

dependencies {
    // OpenTelemetry
    implementation(platform(jambalayaLibs.boms.opentelemetry))
    implementation(platform(jambalayaLibs.opentelemetry.bom.alpha))
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
