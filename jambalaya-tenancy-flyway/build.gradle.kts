plugins {
    `java-library`
    id("com.tailrocks.gradle.maven-publish-conventions")
    id("com.tailrocks.gradle.signing-conventions")
}

version = jambalayaLibs.versions.jambalaya.tenancy.flyway.get()
description = "Tenancy Flyway."

dependencies {
    api(project(":jambalaya-checks"))

    api("org.flywaydb:flyway-core")
    api("org.slf4j:slf4j-api")
}

publishing.publications {
    (getByName("mavenJava") as MavenPublication).apply {
        pom {
            description.set(project.description)
        }
    }
}
