dependencies {
    implementation(project(":jambalaya-example-grpc-interface"))
    implementation(project(":jambalaya-mapstruct"))
    implementation(project(":jambalaya-micronaut-mapstruct-protobuf"))

    // Micronaut
    implementation(platform("io.micronaut:micronaut-bom:${Versions.micronaut}"))

    annotationProcessor("org.mapstruct:mapstruct-processor:${Versions.mapstruct}")
    annotationProcessor(project(":jambalaya-mapstruct-processor"))
    implementation("org.mapstruct:mapstruct:${Versions.mapstruct}")

    implementation("com.google.protobuf:protobuf-java:${Versions.protobuf}")
    implementation("org.jooq:jooq")

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
