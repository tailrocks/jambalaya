plugins {
    java

    // https://plugins.gradle.org/plugin/com.adarshr.test-logger
    id("com.adarshr.test-logger") version "3.2.0" apply false

    // https://plugins.gradle.org/plugin/io.github.gradle-nexus.publish-plugin
    id("io.github.gradle-nexus.publish-plugin") version "1.1.0"

    id("com.tailrocks.spotless") version "0.1.3"

    id("com.tailrocks.idea") version "0.1.3" apply false
    id("com.tailrocks.junit") version "0.1.4" apply false
    id("com.tailrocks.maven-publish") version "0.1.3" apply false
    id("com.tailrocks.versions") version "0.1.3" apply false
    id("com.tailrocks.kotlin") version "0.1.1" apply false
}

val javaVersion = 17

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
