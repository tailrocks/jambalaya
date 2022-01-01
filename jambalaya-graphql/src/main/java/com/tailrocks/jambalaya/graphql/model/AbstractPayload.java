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
package com.tailrocks.jambalaya.graphql.model;

import edu.umd.cs.findbugs.annotations.NonNull;
import edu.umd.cs.findbugs.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

import static com.tailrocks.jambalaya.checks.Preconditions.checkNotNull;

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
