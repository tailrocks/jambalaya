plugins {
    `java-library`
    `maven-publish`
    kotlin("jvm") version Versions.kotlin
}

version = Versions.jambalayaGraphqlApollo
description = "GraphQL Apollo client."

dependencies {
    api(project(":jambalaya-checks"))

    // Micronaut
    implementation(platform("io.micronaut:micronaut-bom:${Versions.micronaut}"))

    // Apollo
    api("com.apollographql.apollo3:apollo-runtime:${Versions.apollo}")

    // OkHttp
    api("com.squareup.okhttp3:okhttp-urlconnection:${Versions.okhttp}")
    api("com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}")

    // SLF4J
    api("org.slf4j:slf4j-api")

    // Kotlin
    testImplementation(kotlin("stdlib-jdk8"))
    testImplementation(kotlin("test-junit5"))
}

// POM name/description fix
publishing {
    publications {
        (getByName("mavenJava") as MavenPublication).apply {
            pom {
                name.set(project.name)
                description.set(project.description)
            }
        }
    }
}
