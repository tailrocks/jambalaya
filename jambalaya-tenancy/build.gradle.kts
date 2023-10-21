plugins {
    `java-library`
    id("com.tailrocks.junit")
}

version = jambalayaLibs.versions.jambalaya.tenancy.asProvider().get()
description = "Tenancy."

apply(plugin = "jambalaya-library-conventions")

dependencies {
    api(project(":jambalaya-checks"))
    api(project(":jambalaya-opentelemetry"))

    compileOnly("com.google.errorprone:error_prone_annotations:2.23.0")

    implementation("org.junit.jupiter:junit-jupiter-api:5.10.0")
}
