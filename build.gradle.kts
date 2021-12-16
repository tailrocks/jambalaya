plugins {
    java
    idea
    `maven-publish`
    signing
    id("com.adarshr.test-logger") version Versions.gradleTestLoggerPlugin apply false
    id("com.diffplug.spotless") version Versions.gradleSpotlessPlugin
    id("io.github.gradle-nexus.publish-plugin") version Versions.gradleNexusPublishPlugin
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
    apply(plugin = "idea")
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
    }

    tasks.withType<JavaCompile> {
        options.release.set(8)
    }
}

subprojects {
    apply(plugin = "java")
    apply(plugin = "com.adarshr.test-logger")

    plugins.withId("maven-publish") {
        apply(plugin = "signing")
    }

    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(javaVersion))
        }

        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8

        withJavadocJar()
        withSourcesJar()
    }

    dependencies {
        // Micronaut
        implementation(platform("io.micronaut:micronaut-bom:${Versions.micronaut}"))

        // SpotBugs
        implementation("com.github.spotbugs:spotbugs-annotations")

        // JUnit
        testImplementation("org.junit.jupiter:junit-jupiter-api")
        testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
    }

    plugins.withId("maven-publish") {
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
                        // TODO temp fix: https://github.com/gradle/gradle/issues/10861
                        withXml {
                            val root = asNode()
                            var nodes = root["dependencyManagement"] as groovy.util.NodeList
                            while (nodes.isNotEmpty()) {
                                root.remove(nodes.first() as groovy.util.Node)

                                nodes = root["dependencyManagement"] as groovy.util.NodeList
                            }
                        }
                        // @end temp fix
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
            repositories {
                maven {
                    name = "OSSRH"
                    setUrl("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2")
                    credentials {
                        username = System.getenv("OSSRH_USER") ?: return@credentials
                        password = System.getenv("OSSRH_PASSWORD") ?: return@credentials
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
