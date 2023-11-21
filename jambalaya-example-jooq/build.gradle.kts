plugins {
    `java-library`
}

val javaVersion = 17

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(javaVersion))
    }
}

dependencies {
    api(jambalayaLibs.jooq)

    api(jambalayaLibs.spotbugs.annotations)
}
