version = Versions.jambalayaChecksJooq
description = "jOOQ preconditions."

dependencies {
    api(project(":jambalaya-checks"))

    implementation("org.jooq:jooq:${Versions.jooq}")
}
