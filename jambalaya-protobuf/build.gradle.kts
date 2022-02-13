plugins {
    `java-library`
    id("com.tailrocks.gradle.maven-publish-conventions")
    id("com.tailrocks.gradle.signing-conventions")
    kotlin("jvm")
}

version = jambalayaLibs.versions.jambalaya.protobuf.get()
description = "Protobuf utils."

dependencies {
    api(project(":jambalaya-checks"))

    api(jambalayaLibs.protobuf.java)

    api(jambalayaLibs.proto.google.common.protos)

    // Kotlin
    testImplementation(kotlin("stdlib-jdk8"))
    testImplementation(kotlin("test-junit5"))
}
