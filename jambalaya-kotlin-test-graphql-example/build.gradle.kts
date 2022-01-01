import org.apache.tools.ant.taskdefs.condition.Os

plugins {
    `java-library`
    id("com.apollographql.apollo3") version "3.0.0"
}

dependencies {
    api(jambalayaLibs.apollo.runtime)
}

apollo {
    generateKotlinModels.set(false)

    packageName.set("com.zhokhov.jambalaya.test.sample.apollo")

    schemaFile.set(file("$projectDir/src/main/graphql/schema.json"))
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

task("installApolloCodegen", Exec::class) {
    val npmCmd = if (Os.isFamily(Os.FAMILY_WINDOWS)) "npm.cmd" else "npm"

    commandLine(npmCmd, "install", "-g", "apollo-codegen")
}

task("generateGraphQLSchema", Exec::class) {
    dependsOn("installApolloCodegen")

    if (gradle.startParameter.taskNames.contains("generateGraphQLSchema")) {
        finalizedBy("generateApolloSources")
    }

    workingDir = file("$projectDir")

    val apolloCodegenCmd = if (Os.isFamily(Os.FAMILY_WINDOWS)) "apollo-codegen.cmd" else "apollo-codegen"

    file("$projectDir/src/main/graphql").mkdirs()

    commandLine(
        apolloCodegenCmd,
        "introspect-schema",
        file("$projectDir/src/main/resources/graphql/dumper.graphqls").absolutePath,
        "--output",
        file("$projectDir/src/main/graphql/schema.json").absolutePath
    )
}
