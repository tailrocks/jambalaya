import com.jfrog.bintray.gradle.BintrayExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        mavenLocal()
        gradlePluginPortal()
        jcenter()
        mavenCentral()
    }
}

plugins {
    java
    idea
    `maven-publish`
    id("com.jfrog.bintray") version Versions.gradleBintrayPlugin apply false
    id("com.diffplug.spotless") version Versions.gradleSpotlessPlugin
    kotlin("jvm") version Versions.kotlin apply false
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

allprojects {
    apply(plugin = "idea")
    apply(plugin = "com.diffplug.spotless")

    idea {
        module {
            isDownloadJavadoc = false
            isDownloadSources = false
        }
    }

    repositories {
        mavenLocal()
        gradlePluginPortal()
        jcenter()
        mavenCentral()
    }

    spotless {
        java {
            licenseHeaderFile("$rootDir/gradle/licenseHeader.txt")
            removeUnusedImports()
            trimTrailingWhitespace()
            endWithNewline()
        }
        kotlin {
            licenseHeaderFile("$rootDir/gradle/licenseHeader.txt")
        }
        kotlinGradle {
            ktlint()
        }
    }

    tasks.withType<JavaCompile> {
        options.release.set(11)
    }
}

subprojects {
    apply(plugin = "java-library")
    apply(plugin = "maven-publish")
    apply(plugin = "com.jfrog.bintray")

    group = "com.zhokhov.jambalaya"

    java {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11

        withJavadocJar()
        withSourcesJar()
    }

    dependencies {
        // SpotBugs
        implementation("com.github.spotbugs:spotbugs-annotations:${Versions.spotbugsAnnotations}")

        // JUnit
        testImplementation("org.junit.jupiter:junit-jupiter-api:${Versions.junit}")
        testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:${Versions.junit}")
    }

    publishing {
        publications {
            create<MavenPublication>("mavenJava") {
                from(components["java"])
                versionMapping {
                    allVariants {
                        fromResolutionResult()
                    }
                }
            }
        }

        repositories {
            maven {
                name = "GitHubPackages"
                url = uri("https://maven.pkg.github.com/expatiat/jambalaya")
                credentials {
                    username = System.getenv("GITHUB_ACTOR")
                    password = System.getenv("GITHUB_TOKEN")
                }
            }
        }
    }

    configure<BintrayExtension> {
        user = System.getenv("BINTRAY_USER")
        key = System.getenv("BINTRAY_KEY")
        publish = true
        override = true
        setPublications("mavenJava")
        pkg.apply {
            repo = "jambalaya"
            name = "jambalaya"
            userOrg = "expatiat"
            vcsUrl = "https://github.com/expatiat/jambalaya.git"
            setLicenses("Apache-2.0")
        }
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "11"
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform {
            includeEngines = setOf("junit-jupiter")
            excludeEngines = setOf("junit-vintage")
        }
    }
}
