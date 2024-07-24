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
package com.tailrocks.jambalaya.tenancy.jooq;

import com.tailrocks.jambalaya.tenancy.Tenant;
import org.jooq.DSLContext;
import org.jspecify.annotations.NonNull;

import static com.tailrocks.jambalaya.checks.Preconditions.checkNotBlank;
import static com.tailrocks.jambalaya.checks.Preconditions.checkNotNull;
import static com.tailrocks.jambalaya.tenancy.TenancyUtils.getTenantOrThrow;

/**
 * @author Alexey Zhokhov
 */
public abstract class AbstractTenantRepository {

    private final DSLContext dslContext;
    private final String applicationName;

    protected AbstractTenantRepository(@NonNull String applicationName, @NonNull DSLContext dslContext) {
        checkNotBlank(applicationName, "applicationName");
        checkNotNull(dslContext, "dslContext");

        this.dslContext = dslContext;
        this.applicationName = applicationName;
    }

    protected String getTenant() {
        return getTenantOrThrow().getByService(applicationName);
    }

    protected DSLContext getDslContext() {
        String tenant = getTenant();
        dslContext.setSchema(getSchema(tenant)).execute();
        return dslContext;
    }

    protected String getSchema(String tenant) {
        if (tenant.equals(Tenant.DEFAULT)) {
            return "public";
        } else if (tenant.equals(Tenant.TESTING)) {
            return "test";
        } else {
            return tenant;
        }
    }

}
