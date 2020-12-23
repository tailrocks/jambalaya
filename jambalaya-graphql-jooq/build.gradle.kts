version = Versions.jambalayaGraphqlJooq

dependencies {
    api(project(":jambalaya-checks"))

    implementation("org.jooq:jooq:${Versions.jooq}")
}
