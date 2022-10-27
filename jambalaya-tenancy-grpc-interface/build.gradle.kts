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
    implementation(platform(jambalayaLibs.boms.grpc))
    api("io.grpc:grpc-protobuf")
    api("io.grpc:grpc-stub")

    // TODO remove me later
    compileOnly("javax.annotation:javax.annotation-api")
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
