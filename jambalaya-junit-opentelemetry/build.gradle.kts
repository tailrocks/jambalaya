plugins {
    `java-library`
    id("com.tailrocks.junit")
}

version = jambalayaLibs.versions.jambalaya.junit.opentelemetry.get()
description = "JUnit OpenTelemetry extension."

apply(plugin = "jambalaya-library-conventions")

dependencies {
    // OpenTelemetry
    implementation(jambalayaLibs.opentelemetry.api)
    implementation(jambalayaLibs.opentelemetry.semconv)

    api(jambalayaLibs.spotbugs.annotations)

    // JUnit
    implementation(jambalayaLibs.junit.jupiter.api)
}
