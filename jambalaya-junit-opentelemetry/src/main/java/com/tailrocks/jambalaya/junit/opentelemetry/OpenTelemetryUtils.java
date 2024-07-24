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
package com.tailrocks.jambalaya.junit.opentelemetry;

import io.opentelemetry.api.GlobalOpenTelemetry;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.StatusCode;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.context.Scope;
import org.jspecify.annotations.Nullable;

import java.util.function.Supplier;

import static java.lang.System.out;

/**
 * @author Alexey Zhokhov
 */
public final class OpenTelemetryUtils {

    private static final String GIVEN_PREFIX = "GIVEN:";
    private static final String WHEN_PREFIX = "WHEN:";
    private static final String THEN_PREFIX = "THEN:";

    private OpenTelemetryUtils() {
    }

    public static void GIVEN(Runnable runnable) {
        WHEN(null, runnable);
    }

    public static void GIVEN(@Nullable String description, Runnable runnable) {
        invokeRunnable(GIVEN_PREFIX, description, runnable);
    }

    public static void WHEN(Runnable runnable) {
        WHEN(null, runnable);
    }

    public static void WHEN(@Nullable String description, Runnable runnable) {
        invokeRunnable(WHEN_PREFIX, description, runnable);
    }

    public static void THEN(Runnable runnable) {
        THEN(null, runnable);
    }

    public static void THEN(@Nullable String description, Runnable runnable) {
        invokeRunnable(THEN_PREFIX, description, runnable);
    }

    public static <T> T GIVEN_(Supplier<T> callable) {
        return WHEN_(null, callable);
    }

    public static <T> T GIVEN_(@Nullable String description, Supplier<T> callable) {
        return invokeCallable(GIVEN_PREFIX, description, callable);
    }

    public static <T> T WHEN_(Supplier<T> callable) {
        return WHEN_(null, callable);
    }

    public static <T> T WHEN_(@Nullable String description, Supplier<T> callable) {
        return invokeCallable(WHEN_PREFIX, description, callable);
    }

    public static <T> T THEN_(Supplier<T> callable) {
        return THEN_(null, callable);
    }

    public static <T> T THEN_(@Nullable String description, Supplier<T> callable) {
        return invokeCallable(THEN_PREFIX, description, callable);
    }

    private static void invokeRunnable(String prefix, @Nullable String description, Runnable runnable) {
        Span span = startSpan(prefix, description);

        try (Scope ignored = span.makeCurrent()) {
            runnable.run();
        } catch (Throwable t) {
            span.recordException(t);
            span.setStatus(StatusCode.ERROR);
            throw t;
        } finally {
            span.end();
        }
    }

    private static <T> T invokeCallable(String prefix, @Nullable String description, Supplier<T> callable) {
        Span span = startSpan(prefix, description);

        try (Scope ignored = span.makeCurrent()) {
            return callable.get();
        } catch (Throwable t) {
            span.recordException(t);
            span.setStatus(StatusCode.ERROR);
            throw t;
        } finally {
            span.end();
        }
    }

    private static Span startSpan(String prefix, String description) {
        Tracer tracer = GlobalOpenTelemetry.getTracer("test");
        String spanName = prefix + (description != null ? " " + description : "");
        Span span = tracer.spanBuilder(spanName).startSpan();

        out.println(">> " + spanName);
        return span;
    }

}
