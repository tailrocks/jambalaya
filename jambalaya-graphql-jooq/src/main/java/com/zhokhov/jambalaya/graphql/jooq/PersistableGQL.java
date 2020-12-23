package com.zhokhov.jambalaya.graphql.jooq;

import edu.umd.cs.findbugs.annotations.NonNull;
import org.jooq.UpdatableRecord;

import static com.zhokhov.jambalaya.checks.Preconditions.checkNotNull;

public abstract class PersistableGQL<ID, ENTITY extends UpdatableRecord> {

    protected ENTITY entity;

    public PersistableGQL(@NonNull ENTITY entity) {
        checkNotNull(entity, "entity");

        this.entity = entity;
    }

    public ID getId() {
        return (ID) entity.key().get(0);
    }

    public ENTITY getEntity() {
        return entity;
    }

}
