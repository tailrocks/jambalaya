version = Versions.jambalayaMicronautGraphql
description = "Micronaut GraphQL utils."

dependencies {
    api(project(":jambalaya-checks"))

    implementation("io.micronaut.graphql:micronaut-graphql:${Versions.micronautGraphql}")
}
