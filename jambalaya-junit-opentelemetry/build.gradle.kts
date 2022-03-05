plugins {
    `java-library`
    id("com.tailrocks.junit")
}

version = jambalayaLibs.versions.jambalaya.junit.opentelemetry.get()
description = "JUnit OpenTelemetry extension."

apply(plugin = "com.tailrocks.maven-publish")

dependencies {
    // OpenTelemetry
    implementation(platform(jambalayaLibs.boms.opentelemetry))
    implementation(platform(jambalayaLibs.opentelemetry.bom.alpha))
    api("io.opentelemetry:opentelemetry-api")
    api("io.opentelemetry:opentelemetry-semconv")

    // JUnit
    api("org.junit.jupiter:junit-jupiter-api")
}
