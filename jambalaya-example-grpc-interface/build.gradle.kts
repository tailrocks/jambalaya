import com.google.protobuf.gradle.id

plugins {
    `java-library`
    id("com.google.protobuf") version "0.9.4"
}

val javaVersion = 21

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(javaVersion))
    }
}

dependencies {
    // gRPC
    api("io.grpc:grpc-protobuf:${jambalayaLibs.versions.grpc.get()}")
    api("io.grpc:grpc-services:${jambalayaLibs.versions.grpc.get()}")
    api("io.grpc:grpc-stub:${jambalayaLibs.versions.grpc.get()}")
    api("io.grpc:grpc-netty-shaded:${jambalayaLibs.versions.grpc.get()}")

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
