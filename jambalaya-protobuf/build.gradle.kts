plugins {
    kotlin("jvm")
}

version = Versions.jambalayaProtobuf
description = "Protobuf utils."

dependencies {
    api(project(":jambalaya-checks"))

    implementation("com.google.protobuf:protobuf-java:${Versions.protobuf}")

    // Kotlin
    testImplementation(kotlin("stdlib-jdk8"))
    testImplementation(kotlin("test-junit5"))
}
