import org.apache.tools.ant.taskdefs.condition.Os

plugins {
    `java-library`
    id("com.apollographql.apollo") version Versions.gradleApolloPlugin
}

dependencies {
    api("com.apollographql.apollo:apollo-runtime:${Versions.apollo}")
}

apollo {
    generateKotlinModels.set(false)

    onCompilationUnit {
        schemaFile.set(file("$projectDir/src/main/graphql/schema.json"))
        rootPackageName.set("com.zhokhov.jambalaya.test.sample.apollo")
    }
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
