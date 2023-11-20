plugins {
    `java-library`
    id("com.tailrocks.junit")
}

version = jambalayaLibs.versions.jambalaya.micronaut.graphql.get()
description = "Micronaut GraphQL utils."

apply(plugin = "jambalaya-library-conventions")

dependencies {
    api(project(":jambalaya-checks"))

    implementation("io.micronaut.graphql:micronaut-graphql:4.1.0")
}
