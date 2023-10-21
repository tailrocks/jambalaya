import com.google.protobuf.gradle.id

plugins {
    `java-library`
    id("com.google.protobuf") version "0.9.4"
}

dependencies {
    // gRPC
    api("io.grpc:grpc-protobuf:1.59.0")
    api("io.grpc:grpc-services:1.59.0")
    api("io.grpc:grpc-stub:1.59.0")
    api("io.grpc:grpc-netty-shaded:1.59.0")

    compileOnly(jambalayaLibs.tomcat.annotations.api)
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
