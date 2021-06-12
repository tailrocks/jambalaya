package com.zhokhov.jambalaya.opentelemetry;

import io.opentelemetry.api.baggage.Baggage;
import io.opentelemetry.context.Context;
import io.opentelemetry.context.Scope;

/**
 * @author Alexey Zhokhov
 */
public final class BaggageUtils {

    private BaggageUtils() {
    }

    public static Context put(String key, String value) {
        return Baggage
                .current()
                .toBuilder()
                .put(key, value)
                .build()
                .storeInContext(Context.current());
    }

    public static Scope putClosable(String key, String value) {
        Context context = put(key, value);
        return context.makeCurrent();
    }

}
