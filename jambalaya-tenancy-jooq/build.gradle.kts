plugins {
    `java-library`
    id("com.tailrocks.gradle.maven-publish-conventions")
    id("com.tailrocks.gradle.signing-conventions")
}

version = jambalayaLibs.versions.jambalaya.tenancy.jooq.get()
description = "Tenancy jOOQ."

dependencies {
    api(project(":jambalaya-checks"))
    api(project(":jambalaya-tenancy"))

    // Micronaut
    implementation(platform(jambalayaLibs.boms.micronaut))

    api("org.jooq:jooq")
}
