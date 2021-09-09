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

    api("com.apollographql.apollo:apollo-runtime:${Versions.apollo}")
    api("com.squareup.okhttp3:okhttp-urlconnection:${Versions.okhttp}")
    api("com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}")
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

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}
