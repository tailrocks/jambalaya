version = Versions.jambalayaMicronautGraphql
description = "Micronaut GraphQL utils."

dependencies {
    api(project(":jambalaya-checks"))

    implementation("io.micronaut.graphql:micronaut-graphql:${Versions.micronautGraphql}")
}

// POM name/description fix
publishing {
    publications {
        (getByName("mavenJava") as MavenPublication).apply {
            pom {
                name.set(project.name)
                description.set(project.description)
            }
        }
    }
}
