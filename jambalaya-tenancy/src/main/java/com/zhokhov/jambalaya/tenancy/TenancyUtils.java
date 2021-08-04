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
import edu.umd.cs.findbugs.annotations.NonNull;
import edu.umd.cs.findbugs.annotations.Nullable;
import io.opentelemetry.api.baggage.Baggage;
import io.opentelemetry.context.Context;
import io.opentelemetry.context.Scope;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.function.Supplier;

import static com.zhokhov.jambalaya.checks.Preconditions.checkNotBlank;
import static com.zhokhov.jambalaya.checks.Preconditions.checkNotNull;

/**
 * @author Alexey Zhokhov
 */
public final class TenancyUtils {

    private static final String TENANT_KEY = "tenant";

    private TenancyUtils() {
    }

    public static Tenant getTenantOrThrow() {
        String tenantString = getTenantString();

        if (tenantString == null) {
            throw new EmptyTenantException();
        }

        return Tenant.parse(tenantString);
    }

    public static Tenant getTenantOrDefault() {
        String tenantString = getTenantString();

        if (tenantString == null) {
            return Tenant.getDefault();
        }

        return Tenant.parse(tenantString);
    }

    public static <T> T callWithDefaultTenant(@NonNull Supplier<T> callable) {
        return callWithTenant(Tenant.DEFAULT, callable);
    }

    public static <T> T callWithTestingTenant(@NonNull Supplier<T> callable) {
        return callWithTenant(Tenant.TESTING, callable);
    }

    public static <T> T callWithTenantForService(
            @NonNull String serviceName,
            @NonNull String tenantName,
            @NonNull Supplier<T> callable
    ) {
        checkNotBlank(serviceName, "serviceName");
        checkNotBlank(tenantName, "tenantName");

        String tenantString = getTenantOrDefault()
                .withService(serviceName, tenantName)
                .toString();

        return callWithTenant(tenantString, callable);
    }

    public static <T> T callWithTenant(@Nullable String tenantString, @NonNull Supplier<T> callable) {
        checkNotNull(callable, "callable");

        if (tenantString == null || !StringUtils.hasText(tenantString)) {
            tenantString = getTenantStringOrDefault();
        }
        try (Scope ignored = setTenantStringClosable(tenantString)) {
            return callable.get();
        }
    }

    public static void runWithDefaultTenant(@NonNull Runnable runnable) {
        runWithTenant(Tenant.DEFAULT, runnable);
    }

    public static void runWithTestingTenant(@NonNull Runnable runnable) {
        runWithTenant(Tenant.TESTING, runnable);
    }

    public static void runWithTenantForService(
            @NonNull String serviceName,
            @NonNull String tenantName,
            @NonNull Runnable runnable
    ) {
        checkNotBlank(serviceName, "serviceName");
        checkNotBlank(tenantName, "tenantName");

        String tenantString = getTenantOrDefault()
                .withService(serviceName, tenantName)
                .toString();

        runWithTenant(tenantString, runnable);
    }

    public static void runWithTenant(@Nullable String tenantString, @NonNull Runnable runnable) {
        checkNotNull(runnable, "runnable");

        if (tenantString == null || !StringUtils.hasText(tenantString)) {
            tenantString = getTenantStringOrDefault();
        }
        try (Scope ignored = setTenantStringClosable(tenantString)) {
            runnable.run();
        }
    }

    @Nullable
    public static String getTenantString() {
        return decode(Baggage.current().getEntryValue(TENANT_KEY));
    }

    public static String getTenantStringOrDefault() {
        return getTenantStringOrElse(Tenant.DEFAULT);
    }

    public static String getTenantStringOrElse(@NonNull String other) {
        checkNotBlank(other, "other");

        String tenantString = getTenantString();
        if (StringUtils.hasText(tenantString)) {
            return tenantString;
        }
        return other;
    }

    public static Context setTenantString(@NonNull String value) {
        checkNotBlank(value, "value");

        return BaggageUtils.put(TENANT_KEY, encode(value));
    }

    public static Scope setTenantStringClosable(@NonNull String value) {
        checkNotBlank(value, "value");

        return BaggageUtils.putClosable(TENANT_KEY, encode(value));
    }

    private static String encode(String value) {
        if (value == null) {
            return null;
        }
        return Base64.getUrlEncoder().withoutPadding().encodeToString(value.getBytes(StandardCharsets.UTF_8));
    }

    private static String decode(String value) {
        if (value == null) {
            return null;
        }
        return new String(Base64.getUrlDecoder().decode(value));
    }

}
