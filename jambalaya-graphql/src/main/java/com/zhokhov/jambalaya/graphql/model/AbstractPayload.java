package com.zhokhov.jambalaya.graphql.model;

import edu.umd.cs.findbugs.annotations.NonNull;
import edu.umd.cs.findbugs.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

import static com.zhokhov.jambalaya.checks.Preconditions.checkNotNull;

/**
 * The GraphQL payload is carrying successful data of the operation result or the returned error is operation was
 * failed.
 *
 * @param <D> the type of data class
 * @param <E> the type or error class
 * @author Alexey Zhokhov
 */
public abstract class AbstractPayload<D, E extends AbstractError> {

    private final CompletableFuture<D> data;
    private final E error;

    /**
     * @param data successfully retrieved data of the operation
     */
    public AbstractPayload(@NonNull D data) {
        checkNotNull(data, "data");

        this.data = CompletableFuture.completedFuture(data);
        this.error = null;
    }

    /**
     * @param data the asynchronous data which will be retrieved only if it will be requested
     */
    public AbstractPayload(@NonNull CompletableFuture<D> data) {
        checkNotNull(data, "data");

        this.data = data;
        this.error = null;
    }

    /**
     * @param error the error entity, use only when operation was failed to inform user with details of the error
     */
    public AbstractPayload(@NonNull E error) {
        checkNotNull(error, "error");

        this.data = null;
        this.error = error;
    }

    /**
     * @return the successful data of this payload, can be {@literal null} if there is an error happened
     */
    @Nullable
    public CompletableFuture<D> getData() {
        return data;
    }

    /**
     * @return the error entity, must be {@literal null} when operation was success
     */
    @Nullable
    public E getError() {
        return error;
    }

}
