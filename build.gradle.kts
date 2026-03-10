plugins {
    // https://plugins.gradle.org/plugin/com.tailrocks.java
    id("com.tailrocks.java") version "0.7.0"

    // https://plugins.gradle.org/plugin/com.tailrocks.spotless
    id("com.tailrocks.spotless") version "0.7.0"

    // https://plugins.gradle.org/plugin/com.tailrocks.idea
    id("com.tailrocks.idea") version "0.7.0" apply false

    // https://plugins.gradle.org/plugin/com.tailrocks.junit
    id("com.tailrocks.junit") version "0.7.0" apply false

    // https://plugins.gradle.org/plugin/com.tailrocks.versions
    id("com.tailrocks.versions") version "0.7.0" apply false

    // https://plugins.gradle.org/plugin/com.tailrocks.kotlin
    id("com.tailrocks.kotlin") version "0.11.0" apply false

    // https://plugins.gradle.org/plugin/com.adarshr.test-logger
    id("com.adarshr.test-logger") version "4.0.0" apply false

    // https://plugins.gradle.org/plugin/com.google.protobuf
    id("com.google.protobuf") version "0.9.6" apply false

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
