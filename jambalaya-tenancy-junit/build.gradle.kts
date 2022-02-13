plugins {
    `java-library`
    id("com.tailrocks.gradle.maven-publish-conventions")
    id("com.tailrocks.gradle.signing-conventions")
}

version = jambalayaLibs.versions.jambalaya.tenancy.junit.get()
description = "Tenancy JUnit."

dependencies {
    api(project(":jambalaya-tenancy"))

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
