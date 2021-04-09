dependencies {
    implementation(project(":jambalaya-example-grpc-interface"))
    implementation(project(":jambalaya-mapstruct"))

    annotationProcessor("org.mapstruct:mapstruct-processor:${Versions.mapstruct}")
    annotationProcessor(project(":jambalaya-mapstruct-processor"))
    annotationProcessor(project(":jambalaya-mapstruct-protobuf-processor"))
    implementation("org.mapstruct:mapstruct:${Versions.mapstruct}")

    implementation("com.google.protobuf:protobuf-java:${Versions.protobuf}")
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
