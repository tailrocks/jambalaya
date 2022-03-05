plugins {
    `java-library`
    id("com.tailrocks.junit")
}

version = jambalayaLibs.versions.jambalaya.tenancy.junit.get()
description = "Tenancy JUnit."

apply(plugin = "com.tailrocks.maven-publish")

dependencies {
    api(project(":jambalaya-tenancy"))

    // JUnit
    api("org.junit.jupiter:junit-jupiter-api")
}
