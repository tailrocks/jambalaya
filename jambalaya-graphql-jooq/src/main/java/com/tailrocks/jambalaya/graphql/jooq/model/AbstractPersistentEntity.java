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
package com.tailrocks.jambalaya.graphql.jooq.model;

import edu.umd.cs.findbugs.annotations.NonNull;
import org.jooq.UpdatableRecord;

import static com.tailrocks.jambalaya.checks.Preconditions.checkNotNull;

/**
 * The abstract class for wrapping jOOQ persistent entities.
 *
 * @param <I> the type of entity identifier
 * @param <E> the type of persistent entity
 * @author Alexey Zhokhov
 */
public abstract class AbstractPersistentEntity<I, E extends UpdatableRecord> {

    protected E entity;

    /**
     * @param entity the entity instance
     */
    public AbstractPersistentEntity(@NonNull E entity) {
        checkNotNull(entity, "entity");

        this.entity = entity;
    }

    /**
     * @return the entity identifier
     */
    public I getId() {
        return (I) entity.key().get(0);
    }

    /**
     * @return the persistent entity
     */
    public E getEntity() {
        return entity;
    }

}
