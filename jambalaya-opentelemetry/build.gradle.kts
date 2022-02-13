plugins {
    `java-library`
    id("com.tailrocks.gradle.maven-publish-conventions")
    id("com.tailrocks.gradle.signing-conventions")
}

version = jambalayaLibs.versions.jambalaya.opentelemetry.get()
description = "OpenTelemetry."

dependencies {
    api(platform(jambalayaLibs.boms.opentelemetry))
    api("io.opentelemetry:opentelemetry-api")
    compileOnly(jambalayaLibs.error.prone.annotations)
}

publishing.publications {
    (getByName("mavenJava") as MavenPublication).apply {
        pom {
            description.set(project.description)
        }
    }
}
