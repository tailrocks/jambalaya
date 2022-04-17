plugins {
    `java-library`
    id("com.tailrocks.junit")
}

version = jambalayaLibs.versions.jambalaya.mapstruct.asProvider().get()
description = "MapStruct utils."

apply(plugin = "jambalaya-library-conventions")

dependencies {
    api(jambalayaLibs.mapstruct)
}
