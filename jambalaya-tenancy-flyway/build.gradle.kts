plugins {
    `java-library`
    id("com.tailrocks.junit")
}

version = jambalayaLibs.versions.jambalaya.tenancy.flyway.get()
description = "Tenancy Flyway."

apply(plugin = "com.tailrocks.maven-publish")

dependencies {
    api(project(":jambalaya-checks"))

    api("org.flywaydb:flyway-core")
    api("org.slf4j:slf4j-api")
}
