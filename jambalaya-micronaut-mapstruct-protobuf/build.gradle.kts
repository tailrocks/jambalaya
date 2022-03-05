plugins {
    `java-library`
    id("com.tailrocks.kotlin")
    id("com.tailrocks.junit")
}

version = jambalayaLibs.versions.jambalaya.micronaut.mapstruct.protobuf.get()
description = "MapStruct protobuf utils."

apply(plugin = "com.tailrocks.maven-publish")

dependencies {
    api(project(":jambalaya-protobuf"))

    // Guava
    api(jambalayaLibs.guava)

    // MapStruct
    annotationProcessor(jambalayaLibs.mapstruct.processor)
    api(jambalayaLibs.mapstruct)

    // Micronaut
    implementation(platform(jambalayaLibs.boms.micronaut))
    annotationProcessor(platform(jambalayaLibs.boms.micronaut))
    annotationProcessor("io.micronaut:micronaut-inject-java")
    compileOnly("io.micronaut:micronaut-inject-java")

    // Jakarta
    compileOnly("jakarta.inject:jakarta.inject-api")

    // Kotlin
    testImplementation(kotlin("stdlib-jdk8"))
    testImplementation(kotlin("test-junit5"))

    // TODO remove me later
    compileOnly("javax.inject:javax.inject:1")
}

