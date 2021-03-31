import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    idea
    `maven-publish`
    signing
    id("com.adarshr.test-logger") version Versions.gradleTestLoggerPlugin apply false
    id("net.rdrei.android.buildtimetracker") version Versions.gradleBuildTimeTrackerPlugin
    id("com.diffplug.spotless") version Versions.gradleSpotlessPlugin
    id("io.github.gradle-nexus.publish-plugin") version Versions.gradleNexusPublishPlugin
    kotlin("jvm") version Versions.kotlin apply false
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

buildtimetracker {
    reporters {
        register("summary") {
            options["ordered"] = "true"
            options["barstyle"] = "none"
            options["shortenTaskNames"] = "false"
        }
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
    apply(plugin = "idea")
    apply(plugin = "net.rdrei.android.buildtimetracker")
    apply(plugin = "com.diffplug.spotless")

    apply(from = "${project.rootDir}/gradle/dependencyUpdates.gradle.kts")

    group = "io.github.expatiat.jambalaya"

    idea {
        module {
            isDownloadJavadoc = false
            isDownloadSources = false
        }
    }

    spotless {
        java {
            licenseHeaderFile("$rootDir/gradle/licenseHeader.txt")
            removeUnusedImports()
            trimTrailingWhitespace()
            endWithNewline()
            targetExclude("**/generated/**")
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

val publishingProjects = setOf(
    "jambalaya-checks",
    "jambalaya-checks-jooq",
    "jambalaya-graphql",
    "jambalaya-graphql-apollo",
    "jambalaya-graphql-jooq",
    "jambalaya-jsr310",
    "jambalaya-kotlin-test",
    "jambalaya-micronaut-graphql",
    "jambalaya-protobuf",
    "jambalaya-seo"
)

subprojects {
    apply(plugin = "java")
    apply(plugin = "com.adarshr.test-logger")
    if (publishingProjects.contains(project.name)) {
        apply(plugin = "java-library")
        apply(plugin = "maven-publish")
        apply(plugin = "signing")
    }

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

    if (publishingProjects.contains(project.name)) {
        publishing {
            publications {
                create<MavenPublication>("mavenJava") {
                    from(components["java"])
                    versionMapping {
                        allVariants {
                            fromResolutionResult()
                        }
                    }
                    pom {
                        url.set(projectScmUrl)
                        licenses {
                            license {
                                name.set(projectLicenseName)
                                url.set(projectLicenseUrl)
                                distribution.set("repo")
                            }
                        }
                        developers {
                            developer {
                                id.set("donbeave")
                                name.set("Alexey Zhokhov")
                                email.set("alexey@zhokhov.com")
                            }
                        }
                        scm {
                            url.set(projectScmUrl)
                            connection.set(projectScmConnection)
                            developerConnection.set(projectScmDeveloperConnection)
                        }
                        issueManagement {
                            url.set(projectIssueManagementUrl)
                        }
                    }
                }
            }
        }

        signing {
            val key = System.getenv("SIGNING_KEY") ?: return@signing
            val password = System.getenv("SIGNING_PASSWORD") ?: return@signing
            val publishing: PublishingExtension by project

            useInMemoryPgpKeys(key, password)
            sign(publishing.publications)
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
