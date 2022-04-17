plugins {
    `java-library`
    id("com.tailrocks.junit")
}

version = jambalayaLibs.versions.jambalaya.tenancy.jooq.get()
description = "Tenancy jOOQ."

apply(plugin = "jambalaya-library-conventions")

dependencies {
    api(project(":jambalaya-checks"))
    api(project(":jambalaya-tenancy"))

    // Micronaut
    implementation(platform(jambalayaLibs.boms.micronaut))

    api("org.jooq:jooq")
}
