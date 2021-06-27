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
package com.zhokhov.jambalaya.tenancy;

import com.zhokhov.jambalaya.opentelemetry.BaggageUtils;
import edu.umd.cs.findbugs.annotations.Nullable;
import io.opentelemetry.api.baggage.Baggage;
import io.opentelemetry.context.Context;
import io.opentelemetry.context.Scope;

/**
 * @author Alexey Zhokhov
 */
public final class TenancyUtils {

    private static final String TENANT_KEY = "tenant";

    private TenancyUtils() {
    }

    public static Tenant getOrThrow() {
        String tenantString = getString();

        if (tenantString == null) {
            throw new EmptyTenantException();
        }

        return new Tenant(tenantString);
    }

    public static Tenant getOrDefault() {
        String tenantString = getString();

        if (tenantString == null) {
            return Tenant.getDefault();
        }

        return new Tenant(tenantString);
    }

    public static <T> T callWithTenant(@Nullable String tenantString, UncheckedCallable<T> callable) {
        if (!StringUtils.hasText(tenantString)) {
            tenantString = getStringOrDefault();
        }
        try (Scope ignored = setStringClosable(tenantString)) {
            return callable.call();
        }
    }

    public static void runWithTenant(@Nullable String tenantString, Runnable runnable) {
        if (!StringUtils.hasText(tenantString)) {
            tenantString = getStringOrDefault();
        }
        try (Scope ignored = setStringClosable(tenantString)) {
            runnable.run();
        }
    }

    @Nullable
    public static String getString() {
        return Baggage.current().getEntryValue(TENANT_KEY);
    }

    @Nullable
    public static String getStringOrDefault() {
        String tenantString = getString();
        if (StringUtils.hasText(tenantString)) {
            return tenantString;
        }
        return Tenant.DEFAULT;
    }

    public static Context setString(String value) {
        return BaggageUtils.put(TENANT_KEY, value);
    }

    public static Scope setStringClosable(String value) {
        return BaggageUtils.putClosable(TENANT_KEY, value);
    }

}
