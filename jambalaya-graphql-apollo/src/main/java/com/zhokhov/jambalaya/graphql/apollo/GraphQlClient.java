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

import com.apollographql.apollo3.ApolloClient;
import com.apollographql.apollo3.api.ApolloResponse;
import com.apollographql.apollo3.api.Operation;
import com.apollographql.apollo3.api.Query;
import com.apollographql.apollo3.api.ScalarType;
import com.apollographql.apollo3.network.http.DefaultHttpEngine;
import com.apollographql.apollo3.network.ws.WebSocketNetworkTransport;
import edu.umd.cs.findbugs.annotations.NonNull;
import edu.umd.cs.findbugs.annotations.Nullable;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.jetbrains.annotations.NotNull;

import java.net.CookieManager;
import java.time.Duration;
import java.util.concurrent.Executor;

import static com.zhokhov.jambalaya.checks.Preconditions.checkNotBlank;
import static com.zhokhov.jambalaya.checks.Preconditions.checkNotNull;
import static com.zhokhov.jambalaya.graphql.apollo.DateTimeAdapters.CUSTOM_TYPE_ADAPTER_MAP;

/**
 * Simple GraphQL client which utilizes Apollo GraphQL client inside with out-of-box supports graphql-java-datetime
 * scalars. The basic use case is to simplify writing GraphQL API integration tests.
 *
 * @author Alexey Zhokhov
 */
public class GraphQlClient {

    private final ApolloClient apolloClient;

    private Duration timeout = Duration.ofSeconds(15);

    public GraphQlClient(@NonNull String serverUrl, @NonNull String webSocketUrl, @NonNull ScalarType[] scalarTypes,
                         @Nullable OkHttpClient okHttpClient, @Nullable Executor executor) {
        checkNotBlank(serverUrl, "serverUrl");

        if (okHttpClient == null) {
            CookieManager cookieHandler = new CookieManager();

            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            okHttpClient = new OkHttpClient.Builder()
                    .cookieJar(new JavaNetCookieJar(cookieHandler))
                    .addInterceptor(logging)
                    .build();
        }

        ApolloClient.Builder apolloClientBuilder = new ApolloClient.Builder()
                .serverUrl(serverUrl)
                .httpEngine(new DefaultHttpEngine(okHttpClient))
                .subscriptionNetworkTransport(
                        new WebSocketNetworkTransport.Builder()
                                .serverUrl(webSocketUrl)
                                .build()
                )
                .customScalarAdapters(CUSTOM_TYPE_ADAPTER_MAP);

        // FIXME
        if (executor != null) {
            //apolloClientBuilder.requestedDispatcher()
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

    // FIXME
    public <D extends Operation.Data & Query.Data> ApolloResponse<D> blockingQuery(
            @NotNull Query<D> query
    ) throws Exception {
        apolloClient.query(query).execute(new Continuation<ApolloResponse<D>>() {
            @Override
            public void resumeWith(@NotNull Object o) {

            }

            @NotNull
            @Override
            public CoroutineContext getContext() {
                return null;
            }
        });

        return null;
    }

    // FIXME
    /*
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
        return new ApolloCall.Callback<T>() {
            @Override
            public void onResponse(@NotNull Response<T> response) {
                future.complete(response);
            }

            @Override
            public void onFailure(@NotNull ApolloException e) {
                future.completeExceptionally(e);
            }
        };
    }
     */

}
