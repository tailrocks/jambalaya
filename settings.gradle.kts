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
    "jambalaya-graphql",
    "jambalaya-graphql-apollo",
    "jambalaya-graphql-jooq",
    "jambalaya-jsr310",
    "jambalaya-kotlin-test",
    "jambalaya-kotlin-test-graphql-example",
    "jambalaya-micronaut-graphql",
    "jambalaya-protobuf",
    "jambalaya-seo"
)
