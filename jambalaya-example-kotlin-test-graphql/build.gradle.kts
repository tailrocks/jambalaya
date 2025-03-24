plugins {
    `java-library`

    // https://plugins.gradle.org/plugin/com.apollographql.apollo3
    id("com.apollographql.apollo3") version "3.8.5"
}

val javaVersion = 21

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(javaVersion))
    }
}

dependencies {
    api(jambalayaLibs.apollo.runtime)
}

apollo {
    generateKotlinModels.set(false)

    packageName.set("jambalaya.test.sample.apollo")

    schemaFile.set(file("$projectDir/src/main/resources/graphql/dumper.graphqls"))
}

tasks {
    compileJava {
        dependsOn("generateApolloSources")
    }
}

sourceSets {
    main {
        java {
            srcDir("$buildDir/generated/source/apollo/main/service")
        }
    }
}
