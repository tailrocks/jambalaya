plugins {
    `java-library`
    id("com.tailrocks.gradle.maven-publish-conventions")
    id("com.tailrocks.gradle.signing-conventions")
    kotlin("jvm")
}

version = jambalayaLibs.versions.jambalaya.micronaut.mapstruct.protobuf.get()
description = "MapStruct protobuf utils."

dependencies {
    api(project(":jambalaya-protobuf"))

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

publishing.publications {
    (getByName("mavenJava") as MavenPublication).apply {
        pom {
            description.set(project.description)
        }
    }
}
