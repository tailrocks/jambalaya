plugins {
    `java-library`
    id("groovy")
}

version = jambalayaLibs.versions.jambalaya.tenancy.spock.get()
description = "Tenancy Spock."

apply(plugin = "jambalaya-library-conventions")

dependencies {
    api(project(":jambalaya-tenancy"))

    // JUnit
    api("org.spockframework:spock-core:2.1-groovy-3.0")
}
