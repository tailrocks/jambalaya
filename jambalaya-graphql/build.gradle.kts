version = Versions.jambalayaGraphql
description = "GraphQL utils."

dependencies {
    api(project(":jambalaya-checks"))

    api("com.graphql-java:java-dataloader:${Versions.graphQlDataLoader}")
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
