/*
 * Copyright 2021 original authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.zhokhov.jambalaya.graphql.apollo;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.ApolloClient;
import com.apollographql.apollo.api.CustomTypeAdapter;
import com.apollographql.apollo.api.Error;
import com.apollographql.apollo.api.Mutation;
import com.apollographql.apollo.api.Query;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.api.ScalarType;
import com.apollographql.apollo.exception.ApolloException;
import com.apollographql.apollo.fetcher.ApolloResponseFetchers;
import edu.umd.cs.findbugs.annotations.NonNull;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.YearMonth;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static com.zhokhov.jambalaya.checks.Preconditions.checkNotBlank;
import static com.zhokhov.jambalaya.checks.Preconditions.checkNotNull;

/**
 * Simple GraphQL client which utilizes Apollo GraphQL client inside with out-of-box supports graphql-java-datetime
 * scalars. The basic use case is to simplify writing GraphQL API integration tests.
 *
 * @author Alexey Zhokhov
 */
public class GraphQlClient {

    private static final Logger LOG = LoggerFactory.getLogger(GraphQlClient.class);

    private static final Map<String, CustomTypeAdapter<?>> CUSTOM_TYPE_ADAPTER_MAP = new HashMap<>();

    static {
        CUSTOM_TYPE_ADAPTER_MAP.put(Date.class.getName(), DateTimeAdapters.DATE);
        CUSTOM_TYPE_ADAPTER_MAP.put(LocalDate.class.getName(), DateTimeAdapters.LOCAL_DATE);
        CUSTOM_TYPE_ADAPTER_MAP.put(LocalDateTime.class.getName(), DateTimeAdapters.LOCAL_DATE_TIME);
        CUSTOM_TYPE_ADAPTER_MAP.put(LocalTime.class.getName(), DateTimeAdapters.LOCAL_TIME);
        CUSTOM_TYPE_ADAPTER_MAP.put(OffsetDateTime.class.getName(), DateTimeAdapters.OFFSET_DATE_TIME);
        CUSTOM_TYPE_ADAPTER_MAP.put(YearMonth.class.getName(), DateTimeAdapters.YEAR_MONTH);
        CUSTOM_TYPE_ADAPTER_MAP.put(Duration.class.getName(), DateTimeAdapters.DURATION);
    }

    private final ApolloClient apolloClient;

    private Duration timeout = Duration.ofSeconds(15);

    public GraphQlClient(@NonNull String serverUrl, @NonNull ScalarType[] scalarTypes) {
        checkNotBlank(serverUrl, "serverUrl");

        ApolloClient.Builder apolloClientBuilder = ApolloClient.builder()
                .serverUrl(serverUrl)
                .defaultResponseFetcher(ApolloResponseFetchers.NETWORK_ONLY);

        for (ScalarType scalarType : scalarTypes) {
            apolloClientBuilder.addCustomTypeAdapter(scalarType, CUSTOM_TYPE_ADAPTER_MAP.get(scalarType.className()));
        }

        apolloClient = apolloClientBuilder.build();
    }

    public ApolloClient getApolloClient() {
        return apolloClient;
    }

    public Duration getTimeout() {
        return timeout;
    }

    public void setTimeout(@NonNull Duration timeout) {
        checkNotNull(timeout, "timeout");

        this.timeout = timeout;
    }

    public <D extends Query.Data, T, V extends Query.Variables> Response<T> blockingQuery(
            @NotNull Query<D, T, V> query
    ) throws Exception {
        CompletableFuture<Response<T>> future = new CompletableFuture<>();

        apolloClient
                .query(query)
                .enqueue(createCallback(future));

        return future.get(timeout.getSeconds(), TimeUnit.SECONDS);
    }

    public <D extends Mutation.Data, T, V extends Mutation.Variables> Response<T> blockingMutate(
            @NotNull Mutation<D, T, V> mutation
    ) throws Exception {
        CompletableFuture<Response<T>> future = new CompletableFuture<>();

        apolloClient
                .mutate(mutation)
                .enqueue(createCallback(future));

        return future.get(timeout.getSeconds(), TimeUnit.SECONDS);
    }

    private <T> ApolloCall.Callback<T> createCallback(CompletableFuture<Response<T>> future) {
        return new ApolloCall.Callback<>() {
            @Override
            public void onResponse(@NotNull Response<T> response) {
                LOG.debug("Response: {}", response.getData());
                if (response.getErrors() != null) {
                    LOG.error("Errors: {}", response.getErrors().stream().map(Error::getMessage).collect(Collectors.toList()));
                }
                future.complete(response);
            }

            @Override
            public void onFailure(@NotNull ApolloException e) {
                LOG.error(e.getMessage(), e);
                future.completeExceptionally(e);
            }
        };
    }

}
