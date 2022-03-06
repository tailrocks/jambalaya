plugins {
    `java-library`
    id("com.tailrocks.junit")
}

version = jambalayaLibs.versions.jambalaya.graphql.asProvider().get()
description = "GraphQL utils."

apply(plugin = "com.tailrocks.maven-publish")

dependencies {
    api(project(":jambalaya-checks"))

    api(jambalayaLibs.java.dataloader)
}
