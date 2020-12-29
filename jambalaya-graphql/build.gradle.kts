version = Versions.jambalayaGraphql

dependencies {
    api(project(":jambalaya-checks"))

    api("com.graphql-java:java-dataloader:${Versions.graphQlDataLoader}")
}
