dependencies {
    implementation(project(":jambalaya-example-grpc-interface"))
    implementation(project(":jambalaya-example-jooq"))
    implementation(project(":jambalaya-mapstruct"))
    implementation(project(":jambalaya-micronaut-mapstruct-protobuf"))

    annotationProcessor(jambalayaLibs.mapstruct.processor)
    annotationProcessor(project(":jambalaya-mapstruct-processor"))
    implementation(jambalayaLibs.mapstruct)

    implementation(jambalayaLibs.protobuf.java)

    // TODO remove me later
    compileOnly("javax.inject:javax.inject:1")
}

tasks {
    compileJava {
        options.compilerArgs.addAll(
            listOf(
                "-Amapstruct.suppressGeneratorTimestamp=true",
                "-Amapstruct.suppressGeneratorVersionInfoComment=true",
                "-Amapstruct.verbose=true"
            )
        )
    }
}
