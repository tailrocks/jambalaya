version = Versions.jambalayaGraphqlApollo

dependencies {
    api(project(":jambalaya-checks"))

    api("com.apollographql.apollo:apollo-runtime:${Versions.apollo}")
    api("org.slf4j:slf4j-api:${Versions.slf4j}")
}
