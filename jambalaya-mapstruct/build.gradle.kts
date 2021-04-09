version = Versions.jambalayaMapstruct
description = "MapStruct utils."

dependencies {
    api("org.mapstruct:mapstruct:${Versions.mapstruct}")
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
