/*
 * Copyright 2020 original authors
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
package com.tailrocks.jambalaya.micronaut.graphql;

import graphql.GraphQLContext;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.MutableHttpResponse;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import static com.tailrocks.jambalaya.checks.Preconditions.checkNotNull;

/**
 * The helper class to work with {@link GraphQLContext} instance. The main purpose is to add ability to manage
 * cookies and headers inside GraphQL {@link graphql.schema.DataFetcher}.
 *
 * @author Alexey Zhokhov
 */
public final class GraphQLContextHttpUtils {

    private static final String HTTP_REQUEST = "MICRONAUT_HTTP_REQUEST";
    private static final String HTTP_RESPONSE = "MICRONAUT_HTTP_RESPONSE";

    private GraphQLContextHttpUtils() {
    }

    /**
     * @param context The GraphQL context object
     * @return a HttpRequest from GraphQL context object or {@literal null} if no request bound to the context object
     */
    @Nullable
    public static HttpRequest getRequest(@NonNull GraphQLContext context) {
        checkNotNull(context, "context");

        return context.get(HTTP_REQUEST);
    }

    /**
     * Bind a http request to GraphQL context object.
     *
     * @param context The GraphQL context object
     * @param request The HTTP request
     */
    public static synchronized void setRequest(@NonNull GraphQLContext context, HttpRequest request) {
        checkNotNull(context, "context");

        context.put(HTTP_REQUEST, request);
    }

    /**
     * @param context The GraphQL context object
     * @return a HttpRequest from GraphQL context object or {@literal null} if no request bound to the context object
     */
    @Nullable
    public static MutableHttpResponse<String> getResponse(@NonNull GraphQLContext context) {
        checkNotNull(context, "context");

        return context.get(HTTP_RESPONSE);
    }

    /**
     * Bind a http response to GraphQL context object.
     *
     * @param context  The GraphQL context object
     * @param response The HTTP response
     */
    public static synchronized void setResponse(@NonNull GraphQLContext context, MutableHttpResponse<String> response) {
        checkNotNull(context, "context");

        context.put(HTTP_RESPONSE, response);
    }

}
