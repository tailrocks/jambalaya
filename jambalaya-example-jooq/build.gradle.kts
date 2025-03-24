plugins {
    `java-library`
}

val javaVersion = 21

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(javaVersion))
    }
}

dependencies {
    api(jambalayaLibs.jooq)

    // JSpecify
    api(jambalayaLibs.jspecify)
}
