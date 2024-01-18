plugins {
    `java-library`
    id("com.tailrocks.junit")
}

version = jambalayaLibs.versions.jambalaya.opentelemetry.get()
description = "OpenTelemetry."

apply(plugin = "jambalaya-library-conventions")

dependencies {
    // OpenTelemetry
    implementation(jambalayaLibs.opentelemetry.api)

    compileOnly(jambalayaLibs.error.prone.annotations)
}
