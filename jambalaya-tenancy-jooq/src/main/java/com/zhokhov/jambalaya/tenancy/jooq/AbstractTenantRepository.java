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
package com.zhokhov.jambalaya.tenancy.jooq;

import com.zhokhov.jambalaya.tenancy.Tenant;
import edu.umd.cs.findbugs.annotations.NonNull;
import org.jooq.DSLContext;

import static com.zhokhov.jambalaya.checks.Preconditions.checkNotBlank;
import static com.zhokhov.jambalaya.checks.Preconditions.checkNotNull;
import static com.zhokhov.jambalaya.tenancy.TenancyUtils.getTenantOrThrow;

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

    protected DSLContext getDslContext() {
        String tenant = getTenantOrThrow().getByService(applicationName);
        dslContext.setSchema(getSchema(tenant)).execute();
        return dslContext;
    }

    private String getSchema(String tenant) {
        if (tenant.equals(Tenant.DEFAULT)) {
            return "public";
        } else if (tenant.equals(Tenant.TESTING)) {
            return "test";
        } else {
            return tenant;
        }
    }

}
