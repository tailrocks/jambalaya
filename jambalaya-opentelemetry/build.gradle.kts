plugins {
    `java-library`
    id("com.tailrocks.junit")
}

version = jambalayaLibs.versions.jambalaya.opentelemetry.get()
description = "OpenTelemetry."

apply(plugin = "jambalaya-library-conventions")

dependencies {
    // OpenTelemetry
    api(platform(jambalayaLibs.boms.opentelemetry))
    api(platform(jambalayaLibs.boms.opentelemetry.alpha))
    api("io.opentelemetry:opentelemetry-api")

    compileOnly(jambalayaLibs.error.prone.annotations)
}
