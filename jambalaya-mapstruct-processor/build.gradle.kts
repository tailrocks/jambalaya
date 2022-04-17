plugins {
    `java-library`
    id("com.tailrocks.junit")
}

version = jambalayaLibs.versions.jambalaya.mapstruct.processor.get()
description = "MapStruct smart SPI."

apply(plugin = "jambalaya-library-conventions")

dependencies {
    api(project(":jambalaya-mapstruct"))

    api(jambalayaLibs.mapstruct.processor)
    api(jambalayaLibs.mapstruct)
    api(jambalayaLibs.guava)
}
