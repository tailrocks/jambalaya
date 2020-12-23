version = Versions.jambalayaMicronautGraphql

dependencies {
    api(project(":jambalaya-checks"))

    implementation("io.micronaut.graphql:micronaut-graphql:${Versions.micronautGraphql}")
}
