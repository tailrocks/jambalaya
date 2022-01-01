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
