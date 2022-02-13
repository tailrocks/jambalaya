plugins {
    `java-library`
    id("com.tailrocks.gradle.maven-publish-conventions")
    id("com.tailrocks.gradle.signing-conventions")
}

version = jambalayaLibs.versions.jambalaya.mapstruct.processor.get()
description = "MapStruct smart SPI."

dependencies {
    api(project(":jambalaya-mapstruct"))

    api(jambalayaLibs.mapstruct.processor)
    api(jambalayaLibs.mapstruct)
    api(jambalayaLibs.guava)
}
