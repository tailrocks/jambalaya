plugins {
    `java-library`
    id("com.tailrocks.junit")
}

version = jambalayaLibs.versions.jambalaya.graphql.jooq.get()
description = "GraphQL jOOQ utils."

apply(plugin = "jambalaya-library-conventions")

dependencies {
    api(project(":jambalaya-checks"))

    implementation(jambalayaLibs.jooq)
}
