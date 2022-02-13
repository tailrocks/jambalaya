plugins {
    `java-library`
    id("com.tailrocks.gradle.maven-publish-conventions")
    id("com.tailrocks.gradle.signing-conventions")
}

version = jambalayaLibs.versions.jambalaya.tenancy.asProvider().get()
description = "Tenancy."

dependencies {
    api(project(":jambalaya-checks"))
    api(project(":jambalaya-opentelemetry"))
}
