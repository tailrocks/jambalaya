plugins {
    `java-library`
    id("com.tailrocks.gradle.maven-publish-conventions")
    id("com.tailrocks.gradle.signing-conventions")
}

version = jambalayaLibs.versions.jambalaya.graphql.jooq.get()
description = "GraphQL jOOQ utils."

dependencies {
    api(project(":jambalaya-checks"))

    // Micronaut
    implementation(platform(jambalayaLibs.boms.micronaut))

    implementation("org.jooq:jooq")
}
