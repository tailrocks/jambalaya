package com.zhokhov.jambalaya.graphql.jooq.model;

import edu.umd.cs.findbugs.annotations.NonNull;
import org.jooq.UpdatableRecord;

import static com.zhokhov.jambalaya.checks.Preconditions.checkNotNull;

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
