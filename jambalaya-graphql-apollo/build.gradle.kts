version = Versions.jambalayaGraphqlApollo

dependencies {
    api(project(":jambalaya-checks"))

    api("com.apollographql.apollo:apollo-runtime:${Versions.apollo}")
    api("com.squareup.okhttp3:okhttp-urlconnection:${Versions.okhttp}")
    api("com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}")
    api("org.slf4j:slf4j-api:${Versions.slf4j}")
}
