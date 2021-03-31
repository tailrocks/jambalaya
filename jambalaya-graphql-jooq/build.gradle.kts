version = Versions.jambalayaGraphqlJooq
description = "GraphQL jOOQ utils."

dependencies {
    api(project(":jambalaya-checks"))

    implementation("org.jooq:jooq:${Versions.jooq}")
}
