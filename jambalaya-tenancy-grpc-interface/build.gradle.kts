import com.google.protobuf.gradle.id

plugins {
    `java-library`
    id("com.google.protobuf")
}

version = jambalayaLibs.versions.jambalaya.tenancy.grpc.api.get()
description = "Tenancy gRPC interface."

apply(plugin = "jambalaya-library-conventions")

dependencies {
    // gRPC
    api("io.grpc:grpc-protobuf:1.58.0")
    api("io.grpc:grpc-stub:1.58.0")

    // TODO remove me later
    compileOnly("javax.annotation:javax.annotation-api:1.3.2")
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:${jambalayaLibs.versions.protobuf.get()}"
    }
    plugins {
        id("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java:${jambalayaLibs.versions.grpc.get()}"
        }
    }
    generateProtoTasks {
        all().forEach {
            it.plugins {
                id("grpc")
            }
        }
    }
}

sourceSets {
    main {
        java {
            srcDirs(
                "${protobuf.generatedFilesBaseDir}/main/grpc",
                "${protobuf.generatedFilesBaseDir}/main/java"
            )
        }
    }
}

// TODO temp fix, probably will be fixed here: https://github.com/gradle/gradle/issues/17236
tasks.withType<AbstractCopyTask> {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}
