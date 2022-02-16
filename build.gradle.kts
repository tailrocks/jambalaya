plugins {
    java

    // https://plugins.gradle.org/plugin/com.adarshr.test-logger
    id("com.adarshr.test-logger") version "3.1.0" apply false

    // https://plugins.gradle.org/plugin/com.diffplug.spotless
    id("com.diffplug.spotless") version "6.3.0"

    // https://plugins.gradle.org/plugin/io.github.gradle-nexus.publish-plugin
    id("io.github.gradle-nexus.publish-plugin") version "1.1.0"

    id("com.tailrocks.gradle.idea-conventions") version "1.0" apply false
    id("com.tailrocks.gradle.junit-conventions") version "1.0" apply false
    id("com.tailrocks.gradle.maven-publish-conventions") version "1.0" apply false
    id("com.tailrocks.gradle.signing-conventions") version "1.0" apply false
    id("com.tailrocks.gradle.spotless-conventions") version "1.0" apply false
    id("com.tailrocks.gradle.versions-conventions") version "1.0" apply false
}

val javaVersion = 17

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(javaVersion))
    }
}

val projectLicenseShortName: String by project
val projectLicenseName: String by project
val projectLicenseUrl: String by project
val projectScmUrl: String by project
val projectScmConnection: String by project
val projectScmDeveloperConnection: String by project
val projectIssueManagementUrl: String by project

allprojects {
    apply(plugin = "com.tailrocks.gradle.spotless-conventions")
    apply(plugin = "com.tailrocks.gradle.idea-conventions")
    apply(plugin = "com.tailrocks.gradle.versions-conventions")

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
