#!/usr/bin/env bash
ABSOLUTE_PATH=$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)
cd "${ABSOLUTE_PATH}" || exit

set -e

./jambalaya-checks/publish-sonatype-release.sh
./jambalaya-checks-jooq/publish-sonatype-release.sh
./jambalaya-graphql/publish-sonatype-release.sh
./jambalaya-graphql-apollo/publish-sonatype-release.sh
./jambalaya-graphql-jooq/publish-sonatype-release.sh
./jambalaya-jsr310/publish-sonatype-release.sh
./jambalaya-junit-opentelemetry/publish-sonatype-release.sh
./jambalaya-kotlin-test/publish-sonatype-release.sh
./jambalaya-mapstruct/publish-sonatype-release.sh
./jambalaya-mapstruct-processor/publish-sonatype-release.sh
./jambalaya-micronaut-graphql/publish-sonatype-release.sh
./jambalaya-micronaut-mapstruct-protobuf/publish-sonatype-release.sh
./jambalaya-opentelemetry/publish-sonatype-release.sh
./jambalaya-protobuf/publish-sonatype-release.sh
./jambalaya-seo/publish-sonatype-release.sh
./jambalaya-tenancy/publish-sonatype-release.sh
./jambalaya-tenancy-flyway/publish-sonatype-release.sh
./jambalaya-tenancy-grpc-interface/publish-sonatype-release.sh
./jambalaya-tenancy-jooq/publish-sonatype-release.sh
./jambalaya-tenancy-junit/publish-sonatype-release.sh
