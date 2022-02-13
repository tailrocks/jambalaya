plugins {
    `java-library`
    id("com.tailrocks.gradle.maven-publish-conventions")
    id("com.tailrocks.gradle.signing-conventions")
}

version = jambalayaLibs.versions.jambalaya.mapstruct.asProvider().get()
description = "MapStruct utils."

dependencies {
    api(jambalayaLibs.mapstruct)
}
