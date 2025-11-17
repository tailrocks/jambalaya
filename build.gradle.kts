plugins {
    // https://plugins.gradle.org/plugin/com.tailrocks.java
    id("com.tailrocks.java") version "0.6.0"

    // https://plugins.gradle.org/plugin/com.tailrocks.spotless
    id("com.tailrocks.spotless") version "0.6.0"

    // https://plugins.gradle.org/plugin/com.tailrocks.idea
    id("com.tailrocks.idea") version "0.6.0" apply false

    // https://plugins.gradle.org/plugin/com.tailrocks.junit
    id("com.tailrocks.junit") version "0.6.0" apply false

    // https://plugins.gradle.org/plugin/com.tailrocks.versions
    id("com.tailrocks.versions") version "0.6.0" apply false

    // https://plugins.gradle.org/plugin/com.tailrocks.kotlin
    id("com.tailrocks.kotlin") version "0.7.0" apply false

    // https://plugins.gradle.org/plugin/com.adarshr.test-logger
    id("com.adarshr.test-logger") version "4.0.0" apply false

    // https://plugins.gradle.org/plugin/com.google.protobuf
    id("com.google.protobuf") version "0.9.5" apply false

    id("com.gradleup.nmcp.aggregation") version "1.3.0"
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
    // TODO restore me later
    //apply(plugin = "com.tailrocks.java")

    // TODO remove me later
    apply(plugin = "java")

    apply(plugin = "com.adarshr.test-logger")
}

nmcpAggregation {
    centralPortal {
        // use User Token's username and password
        username = System.getenv("MAVEN_CENTRAL_USERNAME")
        password = System.getenv("MAVEN_CENTRAL_PASSWORD")
        // publish manually from the portal
        publishingType = "USER_MANAGED"
    }

    publishAllProjectsProbablyBreakingProjectIsolation()
}
