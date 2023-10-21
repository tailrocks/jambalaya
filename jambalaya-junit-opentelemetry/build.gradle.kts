plugins {
    `java-library`
    id("com.tailrocks.junit")
}

version = jambalayaLibs.versions.jambalaya.junit.opentelemetry.get()
description = "JUnit OpenTelemetry extension."

apply(plugin = "jambalaya-library-conventions")

dependencies {
    // OpenTelemetry
    api("io.opentelemetry:opentelemetry-api:1.31.0")
    api("io.opentelemetry:opentelemetry-semconv:1.30.1-alpha")

    api("com.github.spotbugs:spotbugs-annotations:4.8.0")

    // JUnit
    implementation("org.junit.jupiter:junit-jupiter-api:5.10.0")
}
