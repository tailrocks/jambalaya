plugins {
    `java-library`

    // https://plugins.gradle.org/plugin/com.apollographql.apollo
    id("com.apollographql.apollo") version "4.3.3"
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

/*
apollo {
    generateKotlinModels.set(false)

    packageName.set("jambalaya.test.sample.apollo")

    schemaFile.set(file("$projectDir/src/main/resources/graphql/dumper.graphqls"))
}
 */

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
