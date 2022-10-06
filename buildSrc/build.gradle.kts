plugins {
    `kotlin-dsl`
}

dependencies {
    // https://plugins.gradle.org/plugin/com.tailrocks.maven-publish
    implementation("com.tailrocks.gradle:maven-publish-conventions:0.1.11")

    // https://plugins.gradle.org/plugin/com.tailrocks.signing
    implementation("com.tailrocks.gradle:signing-conventions:0.1.3")
}
