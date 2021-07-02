pluginManagement {
    repositories {
        mavenLocal()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        mavenLocal()
        mavenCentral()
    }
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
}

rootProject.name = "jambalaya"

include(
    "jambalaya-checks",
    "jambalaya-checks-jooq",
    "jambalaya-example-grpc-interface",
    "jambalaya-graphql",
    "jambalaya-graphql-apollo",
    "jambalaya-graphql-jooq",
    "jambalaya-jsr310",
    "jambalaya-junit-opentelemetry",
    "jambalaya-kotlin-test",
    "jambalaya-kotlin-test-graphql-example",
    "jambalaya-mapstruct",
    "jambalaya-mapstruct-processor",
    "jambalaya-mapstruct-processors-example",
    "jambalaya-micronaut-graphql",
    "jambalaya-micronaut-mapstruct-protobuf",
    "jambalaya-opentelemetry",
    "jambalaya-protobuf",
    "jambalaya-seo",
    "jambalaya-tenancy",
    "jambalaya-tenancy-flyway",
    "jambalaya-tenancy-grpc-interface"
)
