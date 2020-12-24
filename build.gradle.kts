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
    kotlin("jvm") version Versions.kotlin apply false
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

allprojects {
    apply(plugin = "idea")

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

    if (JavaVersion.current().isCompatibleWith(JavaVersion.VERSION_11)) {
        tasks.withType<JavaCompile> {
            options.release.set(11)
        }
    }
}

subprojects {
    apply(plugin = "java-library")
    apply(plugin = "maven-publish")

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
