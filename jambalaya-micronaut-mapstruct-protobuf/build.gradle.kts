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
    annotationProcessor("io.micronaut:micronaut-inject-java:4.1.10")
    compileOnly("io.micronaut:micronaut-inject-java:4.1.10")

    // Kotlin
    testImplementation(kotlin("stdlib-jdk8"))
    testImplementation(kotlin("test-junit5"))
}
