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
package com.tailrocks.jambalaya.graphql.apollo;

import com.apollographql.apollo3.ApolloClient;
import com.apollographql.apollo3.api.ApolloResponse;
import com.apollographql.apollo3.api.Mutation;
import com.apollographql.apollo3.api.Operation;
import com.apollographql.apollo3.api.Query;
import com.apollographql.apollo3.api.ScalarType;
import com.apollographql.apollo3.network.http.DefaultHttpEngine;
import com.apollographql.apollo3.network.ws.WebSocketNetworkTransport;
import com.apollographql.apollo3.rx3.Rx3Apollo;
import edu.umd.cs.findbugs.annotations.NonNull;
import edu.umd.cs.findbugs.annotations.Nullable;
import okhttp3.Call;
import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.jetbrains.annotations.NotNull;

import java.net.CookieManager;
import java.time.Duration;
import java.util.concurrent.Executor;

import static com.tailrocks.jambalaya.checks.Preconditions.checkNotBlank;
import static com.tailrocks.jambalaya.checks.Preconditions.checkNotNull;
import static com.tailrocks.jambalaya.graphql.apollo.DateTimeAdapters.CUSTOM_TYPE_ADAPTER_MAP;

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
                         @Nullable Call.Factory okHttpClient, @Nullable Executor executor) {
        checkNotBlank(serverUrl, "serverUrl");

        if (okHttpClient == null) {
            CookieManager cookieHandler = new CookieManager();

            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            okHttpClient = (Call.Factory) new OkHttpClient.Builder()
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

    public <D extends Operation.Data & Query.Data> ApolloResponse<D> blockingQuery(
            @NotNull Query<D> query
    ) {
        var apolloCall = apolloClient.query(query);

        return Rx3Apollo.flowable(apolloCall).blockingSingle();
    }

    public <D extends Operation.Data & Mutation.Data> ApolloResponse<D> blockingMutate(
            @NotNull Mutation<D> mutation
    ) {
        var apolloCall = apolloClient.mutation(mutation);

        return Rx3Apollo.flowable(apolloCall).blockingSingle();
    }

}
