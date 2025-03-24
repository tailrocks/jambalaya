plugins {
    `java-library`
    `maven-publish`
    id("com.tailrocks.maven-publish")
    id("com.tailrocks.signing")
}

val javaVersion = 21

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(javaVersion))
    }
}

java {
    withJavadocJar()
    withSourcesJar()
}

publishing {
    publications {
        getByName<MavenPublication>("mavenJava") {
            pom {
                developers {
                    developer {
                        id.set("donbeave")
                        name.set("Alexey Zhokhov")
                        email.set("alexey@zhokhov.com")
                    }
                }
            }
        }
    }
}
