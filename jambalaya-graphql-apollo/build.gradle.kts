plugins {
    `java-library`
    id("com.tailrocks.kotlin")
    id("com.tailrocks.junit")
}

version = jambalayaLibs.versions.jambalaya.graphql.apollo.get()
description = "GraphQL Apollo client."

apply(plugin = "jambalaya-library-conventions")

dependencies {
    api(project(":jambalaya-checks"))

    // Apollo
    api(jambalayaLibs.apollo.runtime)

    // Kotlin Coroutines
    api(jambalayaLibs.kotlinx.coroutines.rx3)

    // OkHttp
    api(jambalayaLibs.okhttp.urlconnection)
    api(jambalayaLibs.logging.interceptor)

    // SLF4J
    implementation(jambalayaLibs.slf4j.api)

    // Kotlin
    testImplementation(kotlin("stdlib-jdk8"))
    testImplementation(kotlin("test-junit5"))
}
