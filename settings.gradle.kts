apply(from = File(settingsDir, "gradle/repositoriesSettings.gradle.kts"))

dependencyResolutionManagement {
    versionCatalogs {
        create("jambalayaLibs") {
            from(files("gradle/libs.versions.toml"))
        }
    }
}

rootProject.name = "jambalaya"

include(
    "jambalaya-checks",
    "jambalaya-checks-jooq",
    "jambalaya-example-grpc-interface",
    "jambalaya-example-jooq",
    "jambalaya-example-kotlin-test-graphql",
    "jambalaya-example-mapstruct-processors",
    "jambalaya-graphql",
    "jambalaya-graphql-apollo",
    "jambalaya-graphql-jooq",
    "jambalaya-jsr310",
    "jambalaya-junit-opentelemetry",
    "jambalaya-kotlin-test",
    "jambalaya-mapstruct",
    "jambalaya-mapstruct-processor",
    "jambalaya-micronaut-graphql",
    "jambalaya-micronaut-mapstruct-protobuf",
    "jambalaya-opentelemetry",
    "jambalaya-protobuf",
    "jambalaya-seo",
    "jambalaya-tenancy",
    "jambalaya-tenancy-flyway",
    "jambalaya-tenancy-grpc-interface",
    "jambalaya-tenancy-jooq",
    "jambalaya-tenancy-junit",
    "jambalaya-tenancy-spock"
)
