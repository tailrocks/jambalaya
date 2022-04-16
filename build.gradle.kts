plugins {
    // https://plugins.gradle.org/plugin/com.adarshr.test-logger
    id("com.adarshr.test-logger") version "3.2.0" apply false

    // https://plugins.gradle.org/plugin/io.github.gradle-nexus.publish-plugin
    id("io.github.gradle-nexus.publish-plugin") version "1.1.0"

    // https://plugins.gradle.org/plugin/com.tailrocks.java
    id("com.tailrocks.java") version "0.1.4"

    // https://plugins.gradle.org/plugin/com.tailrocks.spotless
    id("com.tailrocks.spotless") version "0.1.3"

    // https://plugins.gradle.org/plugin/com.tailrocks.idea
    id("com.tailrocks.idea") version "0.1.3" apply false

    // https://plugins.gradle.org/plugin/com.tailrocks.junit
    id("com.tailrocks.junit") version "0.1.5" apply false

    // https://plugins.gradle.org/plugin/com.tailrocks.maven-publish
    id("com.tailrocks.maven-publish") version "0.1.6" apply false

    // https://plugins.gradle.org/plugin/com.tailrocks.versions
    id("com.tailrocks.versions") version "0.1.4" apply false

    // https://plugins.gradle.org/plugin/com.tailrocks.kotlin
    id("com.tailrocks.kotlin") version "0.1.1" apply false

    // https://plugins.gradle.org/plugin/com.tailrocks.signing
    id("com.tailrocks.signing") version "0.1.3" apply false

    // https://plugins.gradle.org/plugin/com.google.protobuf
    id("com.google.protobuf") version "0.8.18" apply false
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
    apply(plugin = "java")
    apply(plugin = "com.adarshr.test-logger")

    java {
        withJavadocJar()
        withSourcesJar()
    }

    dependencies {
        // Micronaut
        implementation(platform(rootProject.jambalayaLibs.boms.micronaut))

        // SpotBugs
        implementation("com.github.spotbugs:spotbugs-annotations")

        // JUnit
        testImplementation("org.junit.jupiter:junit-jupiter-api")
        testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
    }
}

nexusPublishing {
    repositories {
        sonatype {
            // only for users registered in Sonatype after 24 Feb 2021
            nexusUrl.set(uri("https://s01.oss.sonatype.org/service/local/"))
            snapshotRepositoryUrl.set(uri("https://s01.oss.sonatype.org/content/repositories/snapshots/"))

            username.set(System.getenv("OSSRH_USER") ?: return@sonatype)
            password.set(System.getenv("OSSRH_PASSWORD") ?: return@sonatype)
        }
    }
}
