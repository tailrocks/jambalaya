plugins {
    `java-library`
    id("com.tailrocks.gradle.maven-publish-conventions")
    id("com.tailrocks.gradle.signing-conventions")
}

version = jambalayaLibs.versions.jambalaya.micronaut.graphql.get()
description = "Micronaut GraphQL utils."

dependencies {
    api(project(":jambalaya-checks"))

    implementation("io.micronaut.graphql:micronaut-graphql")
}
