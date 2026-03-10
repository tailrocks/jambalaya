plugins {
    // https://plugins.gradle.org/plugin/com.tailrocks.java
    alias(jambalayaLibs.plugins.tailrocks.java)

    // https://plugins.gradle.org/plugin/com.tailrocks.spotless
    alias(jambalayaLibs.plugins.tailrocks.spotless)

    // https://plugins.gradle.org/plugin/com.tailrocks.idea
    alias(jambalayaLibs.plugins.tailrocks.idea) apply false

    // https://plugins.gradle.org/plugin/com.tailrocks.junit
    alias(jambalayaLibs.plugins.tailrocks.junit) apply false

    // https://plugins.gradle.org/plugin/com.tailrocks.versions
    alias(jambalayaLibs.plugins.tailrocks.versions) apply false

    // https://plugins.gradle.org/plugin/com.tailrocks.kotlin
    alias(jambalayaLibs.plugins.tailrocks.kotlin) apply false

    // https://plugins.gradle.org/plugin/com.adarshr.test-logger
    alias(jambalayaLibs.plugins.test.logger) apply false

    // https://plugins.gradle.org/plugin/com.google.protobuf
    alias(jambalayaLibs.plugins.protobuf) apply false

    // https://github.com/GradleUp/nmcp
    id("com.gradleup.nmcp.aggregation")
}

val javaVersion = 21

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(javaVersion))
    }
}

allprojects {
    apply(plugin = "com.tailrocks.idea")
    apply(plugin = "com.tailrocks.spotless")
    apply(plugin = "com.tailrocks.versions")

    group = "com.tailrocks.jambalaya"

    spotless {
        java {
            licenseHeaderFile("$rootDir/gradle/licenseHeader.txt")
        }
        kotlin {
            licenseHeaderFile("$rootDir/gradle/licenseHeader.txt")
        }
    }
}

subprojects {
    apply(plugin = "com.tailrocks.java")

    apply(plugin = "com.adarshr.test-logger")
}

// Credentials are read from MAVEN_CENTRAL_USERNAME / MAVEN_CENTRAL_PASSWORD env vars.
nmcpAggregation {
    centralPortal {
        username = System.getenv("MAVEN_CENTRAL_USERNAME") ?: ""
        password = System.getenv("MAVEN_CENTRAL_PASSWORD") ?: ""
        // Publish manually from the Central Portal UI after upload.
        publishingType = "USER_MANAGED"
    }
}

// Each subproject that applies jambalaya-library-conventions already has com.gradleup.nmcp
// applied (which registers the nmcpProducer outgoing variant). Here we wire those variants
// into the aggregation. When publishModule is set only that one module is included,
// enabling per-module publishing from the CI workflow.
val publishModule = providers.gradleProperty("publishModule").orNull
if (publishModule != null) {
    dependencies {
        "nmcpAggregation"(project(":$publishModule"))
    }
} else {
    subprojects {
        val subproject = this
        pluginManager.withPlugin("com.gradleup.nmcp") {
            rootProject.dependencies.add("nmcpAggregation", subproject)
        }
    }
}
