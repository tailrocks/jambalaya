plugins {
    `java-library`
}

dependencies {
    // Micronaut
    implementation(platform(jambalayaLibs.boms.micronaut))

    api("org.jooq:jooq")
}
