package com.zhokhov.jambalaya.opentelemetry;

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

    public static String get() {
        return Baggage.current().getEntryValue(TENANT_KEY);
    }

    public static Context set(String value) {
        return BaggageUtils.put(TENANT_KEY, value);
    }

    public static Scope setClosable(String value) {
        return BaggageUtils.putClosable(TENANT_KEY, value);
    }

}
