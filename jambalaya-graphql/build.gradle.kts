version = Versions.jambalayaGraphql
description = "GraphQL utils."

dependencies {
    api(project(":jambalaya-checks"))

    api("com.graphql-java:java-dataloader:${Versions.graphQlDataLoader}")
}
