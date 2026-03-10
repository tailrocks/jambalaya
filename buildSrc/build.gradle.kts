plugins {
    `kotlin-dsl`
}

dependencies {
    // https://plugins.gradle.org/plugin/com.tailrocks.maven-publish
    implementation("com.tailrocks.gradle:maven-publish-conventions:0.8.0")

    // https://plugins.gradle.org/plugin/com.tailrocks.signing
    implementation("com.tailrocks.gradle:signing-conventions:0.7.0")

    // https://github.com/GradleUp/nmcp
    implementation("com.gradleup.nmcp:nmcp:1.4.4")
}
