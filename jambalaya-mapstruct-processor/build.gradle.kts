plugins {
    `java-library`
    id("com.tailrocks.junit")
}

version = jambalayaLibs.versions.jambalaya.mapstruct.processor.get()
description = "MapStruct smart SPI."

apply(plugin = "com.tailrocks.maven-publish")
apply(plugin = "com.tailrocks.signing")

dependencies {
    api(project(":jambalaya-mapstruct"))

    api(jambalayaLibs.mapstruct.processor)
    api(jambalayaLibs.mapstruct)
    api(jambalayaLibs.guava)
}
