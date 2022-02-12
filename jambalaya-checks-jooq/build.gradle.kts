plugins {
    `java-library`
    `maven-publish`
    kotlin("jvm")
}

version = jambalayaLibs.versions.jambalaya.checks.jooq.get()
description = "jOOQ preconditions."

dependencies {
    api(project(":jambalaya-checks"))
    testImplementation(project(":jambalaya-example-jooq"))

    // Micronaut
    implementation(platform(jambalayaLibs.boms.micronaut))

    implementation("org.jooq:jooq")

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
