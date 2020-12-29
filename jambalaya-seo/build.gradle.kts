plugins {
    kotlin("jvm")
}

version = Versions.jambalayaSeo

dependencies {
    api(project(":jambalaya-checks"))

    api("com.github.slugify:slugify:${Versions.slugify}")

    // Kotlin
    testImplementation(kotlin("stdlib-jdk8"))
    testImplementation(kotlin("test-junit5"))
}
