plugins {
    `java-library`
    id("com.tailrocks.junit")
}

version = jambalayaLibs.versions.jambalaya.tenancy.junit.get()
description = "Tenancy JUnit."

apply(plugin = "jambalaya-library-conventions")

dependencies {
    api(project(":jambalaya-tenancy"))

    // JUnit
    implementation("org.junit.jupiter:junit-jupiter-api:5.10.0")
}
