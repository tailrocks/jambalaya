import com.google.protobuf.gradle.generateProtoTasks
import com.google.protobuf.gradle.id
import com.google.protobuf.gradle.plugins
import com.google.protobuf.gradle.protobuf
import com.google.protobuf.gradle.protoc

plugins {
    `java-library`
    id("com.google.protobuf")
}

version = jambalayaLibs.versions.jambalaya.tenancy.grpc.api.get()
description = "Tenancy gRPC interface."

apply(plugin = "com.tailrocks.maven-publish")
apply(plugin = "com.tailrocks.signing")

dependencies {
    // gRPC
    api(platform(jambalayaLibs.boms.grpc))
    api("io.grpc:grpc-protobuf")
    api("io.grpc:grpc-services")
    api("io.grpc:grpc-stub")
    api("io.grpc:grpc-netty-shaded")

    implementation("jakarta.annotation:jakarta.annotation-api")

    // TODO remove me later
    implementation("javax.annotation:javax.annotation-api")
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
                "${protobuf.protobuf.generatedFilesBaseDir}/main/grpc",
                "${protobuf.protobuf.generatedFilesBaseDir}/main/java"
            )
        }
    }
}
