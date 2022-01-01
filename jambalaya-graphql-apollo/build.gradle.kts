plugins {
    `java-library`
    `maven-publish`
    kotlin("jvm")
}

version = jambalayaLibs.versions.jambalaya.graphql.apollo.get()
description = "GraphQL Apollo client."

dependencies {
    api(project(":jambalaya-checks"))

    // Micronaut
    implementation(platform(jambalayaLibs.boms.micronaut))

    // Apollo
    api(jambalayaLibs.apollo.runtime)

    // OkHttp
    api(jambalayaLibs.okhttp.urlconnection)
    api(jambalayaLibs.logging.interceptor)

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
