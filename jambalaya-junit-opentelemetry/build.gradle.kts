plugins {
    `java-library`
    id("com.tailrocks.gradle.maven-publish-conventions")
    id("com.tailrocks.gradle.signing-conventions")
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

publishing.publications {
    (getByName("mavenJava") as MavenPublication).apply {
        pom {
            description.set(project.description)
        }
    }
}
