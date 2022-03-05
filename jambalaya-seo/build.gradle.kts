plugins {
    `java-library`
    id("com.tailrocks.kotlin")
    id("com.tailrocks.junit")
}

version = jambalayaLibs.versions.jambalaya.seo.get()
description = "SEO utils."

apply(plugin = "com.tailrocks.maven-publish")

dependencies {
    api(project(":jambalaya-checks"))

    api(jambalayaLibs.slugify)

    // Kotlin
    testImplementation(kotlin("stdlib-jdk8"))
    testImplementation(kotlin("test-junit5"))
}
