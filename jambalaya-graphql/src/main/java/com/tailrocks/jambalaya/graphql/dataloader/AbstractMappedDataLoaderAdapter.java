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
package com.tailrocks.jambalaya.graphql.dataloader;

import org.dataloader.DataLoader;
import org.dataloader.DataLoaderFactory;
import org.dataloader.DataLoaderOptions;
import org.dataloader.DataLoaderRegistry;
import org.dataloader.MappedBatchLoaderWithContext;
import org.jspecify.annotations.NonNull;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static com.tailrocks.jambalaya.checks.Preconditions.checkNotNull;

/**
 * Abstract class for implementing mapped GraphQL data loaders with strict type checking. It hides all complexity of
 * initializing and retrieving correct data loader from the registry. Data loader will be auto-registering only if any
 * data was requested.
 *
 * <p><b>Sample Usage</b> (Note that the following classes are all
 * made-up.)
 *
 * <pre>{@code
 * public class AccountByIdDataLoader extends AbstractMappedDataLoaderAdapter<Long, AccountRecord> {
 *
 *   private final AccountRepository accountRepository;
 *
 *   public AccountByIdDataLoader(AccountRepository accountRepository) {
 *     this.accountRepository = accountRepository;
 *   }
 *
 *   protected MappedBatchLoaderWithContext<Long, AccountRecord> getBatchLoader() {
 *     return (keys, batchLoaderEnvironment) -> CompletableFuture.completedFuture(
 *         accountRepository
 *             .findAllByIdIn(keys)
 *             .stream()
 *             .collect(toMap(AccountRecord::getId, Function.identity()))
 *     );
 *   }
 *
 * }
 * }</pre>
 * And use this data loader in the resolver class:
 * <pre>{@code
 *
 *   public CompletableFuture<PublicProfile> author(Post post, DataFetchingEnvironment env) {
 *     return accountByIdDataLoader
 *                .load(post.getAuthorId(), env.getDataLoaderRegistry())
 *                .thenApply(PublicProfile::new);
 *   }
 *
 * }</pre>
 *
 * @param <K> type parameter indicating the type of keys to use for data load requests.
 * @param <V> type parameter indicating the type of values returned
 * @author Alexey Zhokhov
 */
public abstract class AbstractMappedDataLoaderAdapter<K, V> {

    /**
     * @return new instance of batch loader
     */
    protected abstract MappedBatchLoaderWithContext<K, V> getBatchLoader();

    /**
     * Requests to load asynchronously a single value for specified key.
     *
     * @param key      the unique key to retrieve data
     * @param registry the data loader registry
     * @return {@link CompletableFuture} with the value for the provided key
     */
    public CompletableFuture<V> load(@NonNull K key, @NonNull DataLoaderRegistry registry) {
        checkNotNull(key, "key");
        checkNotNull(registry, "registry");

        return getOrRegisterDataLoader(registry).load(key);
    }

    /**
     * Requests to load asynchronously multiple values for specified keys.
     *
     * @param keys     the list of unique keys to retrieve data
     * @param registry the data loader registry
     * @return {@link CompletableFuture} with list of values for provided keys
     */
    public CompletableFuture<List<V>> loadMany(@NonNull List<K> keys, @NonNull DataLoaderRegistry registry) {
        checkNotNull(keys, "keys");
        checkNotNull(registry, "registry");

        return getOrRegisterDataLoader(registry).loadMany(keys);
    }

    /**
     * @return the unique data loader key which will be used to store and retrieve this data loader from the registry
     */
    protected String getDataLoaderKey() {
        return this.getClass().getName();
    }

    /**
     * @return the data loader options
     */
    protected DataLoaderOptions getDataLoaderOptions() {
        return DataLoaderOptions.newOptions().build();
    }

    /**
     * @param registry the data loader registry
     * @return the data loader from the registry if it was registered or register a new instance
     */
    private DataLoader<K, V> getOrRegisterDataLoader(@NonNull DataLoaderRegistry registry) {
        return registry.computeIfAbsent(getDataLoaderKey(), s ->
                DataLoaderFactory.newMappedDataLoader(getBatchLoader(), getDataLoaderOptions())
        );
    }

}
