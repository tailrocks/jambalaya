plugins {
    `java-library`
    id("com.tailrocks.junit")
}

version = jambalayaLibs.versions.jambalaya.junit.opentelemetry.get()
description = "JUnit OpenTelemetry extension."

apply(plugin = "jambalaya-library-conventions")

dependencies {
    // OpenTelemetry
    api(platform(jambalayaLibs.boms.opentelemetry))
    api(platform(jambalayaLibs.boms.opentelemetry.alpha))
    api("io.opentelemetry:opentelemetry-api")
    api("io.opentelemetry:opentelemetry-semconv")

    // JUnit
    api("org.junit.jupiter:junit-jupiter-api")
}
