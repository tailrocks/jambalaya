dependencies {
    implementation(project(":jambalaya-example-grpc-interface"))
    implementation(project(":jambalaya-mapstruct"))
    implementation(project(":jambalaya-micronaut-mapstruct-protobuf"))

    annotationProcessor("org.mapstruct:mapstruct-processor:${Versions.mapstruct}")
    annotationProcessor(project(":jambalaya-mapstruct-processor"))
    implementation("org.mapstruct:mapstruct:${Versions.mapstruct}")

    implementation("com.google.protobuf:protobuf-java:${Versions.protobuf}")
    implementation("org.jooq:jooq:${Versions.jooq}")
    implementation("jakarta.inject:jakarta.inject-api:${Versions.jakartaInjectApi}")
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
