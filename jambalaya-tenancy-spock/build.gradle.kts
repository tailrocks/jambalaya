plugins {
    `java-library`
    groovy
}

version = jambalayaLibs.versions.jambalaya.tenancy.spock.get()
description = "Tenancy Spock."

apply(plugin = "jambalaya-library-conventions")

dependencies {
    api(project(":jambalaya-tenancy"))

    // JUnit
    api(jambalayaLibs.spock.core)
}
