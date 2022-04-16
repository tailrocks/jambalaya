plugins {
    `java-library`
    id("com.tailrocks.junit")
}

version = jambalayaLibs.versions.jambalaya.opentelemetry.get()
description = "OpenTelemetry."

apply(plugin = "com.tailrocks.maven-publish")
apply(plugin = "com.tailrocks.signing")

dependencies {
    api(platform(jambalayaLibs.boms.opentelemetry))
    api("io.opentelemetry:opentelemetry-api")
    compileOnly(jambalayaLibs.error.prone.annotations)
}
