plugins {
    `java-library`
    id("com.tailrocks.junit")
}

version = jambalayaLibs.versions.jambalaya.mapstruct.asProvider().get()
description = "MapStruct utils."

apply(plugin = "com.tailrocks.maven-publish")

dependencies {
    api(jambalayaLibs.mapstruct)
}
