dependencies {
    // Micronaut
    implementation(platform(jambalayaLibs.boms.micronaut))

    implementation("org.jooq:jooq")
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
