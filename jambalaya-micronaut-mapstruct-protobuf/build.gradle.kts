plugins {
    `java-library`
    id("com.tailrocks.kotlin")
    id("com.tailrocks.junit")
}

version = jambalayaLibs.versions.jambalaya.micronaut.mapstruct.protobuf.get()
description = "MapStruct protobuf utils."

apply(plugin = "jambalaya-library-conventions")

dependencies {
    api(project(":jambalaya-protobuf"))

    // Guava
    api(jambalayaLibs.guava)

    // MapStruct
    annotationProcessor(jambalayaLibs.mapstruct.processor)
    api(jambalayaLibs.mapstruct)

    // Micronaut
    annotationProcessor(jambalayaLibs.micronaut.inject.java)
    compileOnly(jambalayaLibs.micronaut.inject.java)

    // Kotlin
    testImplementation(kotlin("stdlib-jdk8"))
    testImplementation(kotlin("test-junit5"))
}
