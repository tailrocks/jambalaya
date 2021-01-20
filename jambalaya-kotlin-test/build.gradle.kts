plugins {
    kotlin("jvm")
}

version = Versions.jambalayaKotlinTest

dependencies {
    testImplementation(project(":jambalaya-kotlin-test-graphql-example"))

    // Kotlin
    testImplementation(kotlin("stdlib-jdk8"))
    testImplementation(kotlin("test-junit5"))
}
