plugins {
    `java-library`
    id("com.tailrocks.junit")
}

version = jambalayaLibs.versions.jambalaya.opentelemetry.get()
description = "OpenTelemetry."

apply(plugin = "jambalaya-library-conventions")

dependencies {
    // OpenTelemetry
    api("io.opentelemetry:opentelemetry-api:1.31.0")

    compileOnly(jambalayaLibs.error.prone.annotations)
}
