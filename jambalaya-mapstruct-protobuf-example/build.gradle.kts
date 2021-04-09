dependencies {
    implementation(project(":jambalaya-example-grpc-interface"))

    annotationProcessor("org.mapstruct:mapstruct-processor:${Versions.mapstruct}")
    annotationProcessor(project(":jambalaya-mapstruct-protobuf"))
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
