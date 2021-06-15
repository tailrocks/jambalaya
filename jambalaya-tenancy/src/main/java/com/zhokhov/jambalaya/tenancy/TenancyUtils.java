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

    @Nullable
    public static String getString() {
        return Baggage.current().getEntryValue(TENANT_KEY);
    }

    public static Context setString(String value) {
        return BaggageUtils.put(TENANT_KEY, value);
    }

    public static Scope setStringClosable(String value) {
        return BaggageUtils.putClosable(TENANT_KEY, value);
    }

}
