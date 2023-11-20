plugins {
    `java-library`
    id("com.tailrocks.junit")
}

version = jambalayaLibs.versions.jambalaya.tenancy.flyway.get()
description = "Tenancy Flyway."

apply(plugin = "jambalaya-library-conventions")

dependencies {
    api(project(":jambalaya-checks"))

    implementation(jambalayaLibs.flyway.core)
    implementation(jambalayaLibs.slf4j.api)
}
