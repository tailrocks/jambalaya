plugins {
    `java-library`
    id("com.tailrocks.junit")
}

version = jambalayaLibs.versions.jambalaya.junit.opentelemetry.get()
description = "JUnit OpenTelemetry extension."

apply(plugin = "jambalaya-library-conventions")

dependencies {
    // OpenTelemetry
    api(jambalayaLibs.opentelemetry.api)
    api(jambalayaLibs.opentelemetry.semconv)

    api(jambalayaLibs.spotbugs.annotations)

    // JUnit
    implementation("org.junit.jupiter:junit-jupiter-api:5.10.0")
}
