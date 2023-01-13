import com.google.protobuf.gradle.id

plugins {
    `java-library`
    id("com.google.protobuf") version "0.9.2"
}

dependencies {
    // gRPC
    api(platform(jambalayaLibs.boms.grpc))
    api("io.grpc:grpc-protobuf")
    api("io.grpc:grpc-services")
    api("io.grpc:grpc-stub")
    api("io.grpc:grpc-netty-shaded")

    api("jakarta.annotation:jakarta.annotation-api")
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
