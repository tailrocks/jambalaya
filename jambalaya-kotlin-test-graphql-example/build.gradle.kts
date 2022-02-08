plugins {
    `java-library`
    id("com.apollographql.apollo3") version "3.1.0"
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
