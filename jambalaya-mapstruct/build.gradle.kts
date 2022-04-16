plugins {
    `java-library`
    id("com.tailrocks.junit")
}

version = jambalayaLibs.versions.jambalaya.mapstruct.asProvider().get()
description = "MapStruct utils."

apply(plugin = "com.tailrocks.maven-publish")
apply(plugin = "com.tailrocks.signing")

dependencies {
    api(jambalayaLibs.mapstruct)
}
