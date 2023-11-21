import org.jetbrains.kotlin.gradle.dsl.jvm.JvmTargetValidationMode

plugins {
    `kotlin-dsl`
}

dependencies {
    // https://plugins.gradle.org/plugin/com.tailrocks.maven-publish
    implementation("com.tailrocks.gradle:maven-publish-conventions:0.5.1")

    // https://plugins.gradle.org/plugin/com.tailrocks.signing
    implementation("com.tailrocks.gradle:signing-conventions:0.5.1")
}

project.tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = "20"
        javaParameters = true
    }
    jvmTargetValidationMode.set(JvmTargetValidationMode.WARNING)
}
