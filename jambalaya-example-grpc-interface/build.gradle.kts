import com.google.protobuf.gradle.generateProtoTasks
import com.google.protobuf.gradle.id
import com.google.protobuf.gradle.plugins
import com.google.protobuf.gradle.protobuf
import com.google.protobuf.gradle.protoc

plugins {
    `java-library`
    id("com.google.protobuf") version Versions.gradleProtobufPlugin
}

java {
    withJavadocJar()
    withSourcesJar()
}

dependencies {
    implementation("io.grpc:grpc-protobuf:${Versions.grpc}")
    implementation("io.grpc:grpc-services:${Versions.grpc}")
    implementation("io.grpc:grpc-stub:${Versions.grpc}")
    implementation("io.grpc:grpc-netty:${Versions.grpc}")
    implementation("jakarta.annotation:jakarta.annotation-api:${Versions.jakartaAnnotationApi}")
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:${Versions.protobuf}"
    }
    plugins {
        id("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java:${Versions.grpc}"
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
