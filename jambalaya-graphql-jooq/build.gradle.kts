plugins {
    `java-library`
    id("com.tailrocks.junit")
}

version = jambalayaLibs.versions.jambalaya.graphql.jooq.get()
description = "GraphQL jOOQ utils."

apply(plugin = "com.tailrocks.maven-publish")
apply(plugin = "com.tailrocks.signing")

dependencies {
    api(project(":jambalaya-checks"))

    // Micronaut
    implementation(platform(jambalayaLibs.boms.micronaut))

    implementation("org.jooq:jooq")
}
