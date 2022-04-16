plugins {
    `java-library`
    id("com.tailrocks.junit")
}

version = jambalayaLibs.versions.jambalaya.tenancy.asProvider().get()
description = "Tenancy."

apply(plugin = "com.tailrocks.maven-publish")
apply(plugin = "com.tailrocks.signing")

dependencies {
    api(project(":jambalaya-checks"))
    api(project(":jambalaya-opentelemetry"))

    compileOnly("com.google.errorprone:error_prone_annotations:2.13.1")
}
