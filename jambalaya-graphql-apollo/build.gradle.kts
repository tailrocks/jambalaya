version = Versions.jambalayaGraphqlApollo
description = "GraphQL Apollo client."

dependencies {
    api(project(":jambalaya-checks"))

    api("com.apollographql.apollo:apollo-runtime:${Versions.apollo}")
    api("com.squareup.okhttp3:okhttp-urlconnection:${Versions.okhttp}")
    api("com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}")
    api("org.slf4j:slf4j-api:${Versions.slf4j}")
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
