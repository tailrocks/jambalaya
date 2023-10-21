plugins {
    `java-library`
    id("com.tailrocks.junit")
}

version = jambalayaLibs.versions.jambalaya.tenancy.flyway.get()
description = "Tenancy Flyway."

apply(plugin = "jambalaya-library-conventions")

dependencies {
    api(project(":jambalaya-checks"))

    implementation("org.flywaydb:flyway-core:9.22.3")
    implementation("org.slf4j:slf4j-api:2.0.9")
}
