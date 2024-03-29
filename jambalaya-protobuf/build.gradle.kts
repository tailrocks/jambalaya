plugins {
    `java-library`
    id("com.tailrocks.kotlin")
    id("com.tailrocks.junit")
}

version = jambalayaLibs.versions.jambalaya.protobuf.get()
description = "Protobuf utils."

apply(plugin = "jambalaya-library-conventions")

dependencies {
    api(project(":jambalaya-checks"))

    api(jambalayaLibs.protobuf.java)

    api(jambalayaLibs.proto.google.common.protos)

    // Kotlin
    testImplementation(kotlin("stdlib-jdk8"))
    testImplementation(kotlin("test-junit5"))
}
