plugins {
    `java-library`
    id("com.tailrocks.junit")
}

version = jambalayaLibs.versions.jambalaya.opentelemetry.get()
description = "OpenTelemetry."

apply(plugin = "com.tailrocks.maven-publish")

dependencies {
    api(platform(jambalayaLibs.boms.opentelemetry))
    api("io.opentelemetry:opentelemetry-api")
    compileOnly(jambalayaLibs.error.prone.annotations)
}
