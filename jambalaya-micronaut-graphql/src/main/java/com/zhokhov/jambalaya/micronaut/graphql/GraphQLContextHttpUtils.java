package com.zhokhov.jambalaya.micronaut.graphql;

import edu.umd.cs.findbugs.annotations.Nullable;
import graphql.GraphQLContext;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.MutableHttpResponse;

/**
 * The helper class to work with {@link graphql.GraphQLContext} instance. The main purpose is to add ability to manage
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
    public static HttpRequest getRequest(GraphQLContext context) {
        return context.get(HTTP_REQUEST);
    }

    /**
     * Bind a http request to GraphQL context object.
     *
     * @param context The GraphQL context object
     * @param request The HTTP request
     */
    public static synchronized void setRequest(GraphQLContext context, HttpRequest request) {
        context.put(HTTP_REQUEST, request);
    }

    /**
     * @param context The GraphQL context object
     * @return a HttpRequest from GraphQL context object or {@literal null} if no request bound to the context object
     */
    @Nullable
    public static MutableHttpResponse<String> getResponse(GraphQLContext context) {
        return context.get(HTTP_RESPONSE);
    }

    /**
     * Bind a http response to GraphQL context object.
     *
     * @param context  The GraphQL context object
     * @param response The HTTP response
     */
    public static synchronized void setResponse(GraphQLContext context, MutableHttpResponse<String> response) {
        context.put(HTTP_RESPONSE, response);
    }

}
