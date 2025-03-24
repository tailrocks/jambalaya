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
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.InvocationInterceptor;
import org.junit.jupiter.api.extension.ReflectiveInvocationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static io.opentelemetry.semconv.SemanticAttributes.CODE_FUNCTION;
import static io.opentelemetry.semconv.SemanticAttributes.CODE_NAMESPACE;
import static io.opentelemetry.semconv.SemanticAttributes.THREAD_ID;
import static io.opentelemetry.semconv.SemanticAttributes.THREAD_NAME;

/**
 * @author Alexey Zhokhov
 */
public class OpenTelemetryExtension implements BeforeEachCallback, AfterEachCallback, AfterAllCallback,
        InvocationInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(OpenTelemetryExtension.class.getName());

    private static Tracer tracer;

    private final Map<String, Scope> scopes = new HashMap<>();
    private final Map<String, Span> spans = new HashMap<>();

    private static Span startSpan(String spanName) {
        if (tracer == null) {
            tracer = GlobalOpenTelemetry.get().getTracer("junit5-extension");
        }

        return tracer
                .spanBuilder(spanName)
                .startSpan();
    }

    @Override
    public void beforeEach(ExtensionContext context) {
        Span span = startSpan(context.getDisplayName());

        span.setAttribute(THREAD_ID, Thread.currentThread().getId());
        span.setAttribute(THREAD_NAME, Thread.currentThread().getName());
        span.setAttribute(CODE_FUNCTION, context.getRequiredTestMethod().getName());
        span.setAttribute(CODE_NAMESPACE, context.getRequiredTestClass().getName());

        spans.put(context.getUniqueId(), span);
        scopes.put(context.getUniqueId(), span.makeCurrent());

        logger.info(String.format("Run test %s > %s", context.getRequiredTestClass().getSimpleName(),
                context.getDisplayName()));
        logger.info(String.format("Trace ID: %s", span.getSpanContext().getTraceId()));
    }

    @Override
    public void afterEach(ExtensionContext context) {
        context.getExecutionException().ifPresent(throwable -> {
            spans.get(context.getUniqueId()).recordException(throwable);
            spans.get(context.getUniqueId()).setStatus(StatusCode.ERROR);
        });
        scopes.get(context.getUniqueId()).close();
        scopes.remove(context.getUniqueId());
        spans.get(context.getUniqueId()).end();
        spans.remove(context.getUniqueId());
    }

    @Override
    public void afterAll(ExtensionContext context) {
        for (Map.Entry<String, Scope> entry : scopes.entrySet()) {
            entry.getValue().close();
        }
        for (Map.Entry<String, Span> entry : spans.entrySet()) {
            entry.getValue().end();
        }
        scopes.clear();
        spans.clear();
    }

    @Override
    public void interceptBeforeAllMethod(Invocation<Void> invocation,
                                         ReflectiveInvocationContext<Method> invocationContext,
                                         ExtensionContext extensionContext) throws Throwable {
        interceptMethod("@BeforeAll: ", invocation, invocationContext, extensionContext);
    }

    @Override
    public void interceptAfterAllMethod(Invocation<Void> invocation,
                                        ReflectiveInvocationContext<Method> invocationContext,
                                        ExtensionContext extensionContext) throws Throwable {
        interceptMethod("@AfterAll: ", invocation, invocationContext, extensionContext);
    }

    @Override
    public void interceptBeforeEachMethod(Invocation<Void> invocation,
                                          ReflectiveInvocationContext<Method> invocationContext,
                                          ExtensionContext extensionContext) throws Throwable {
        interceptMethod("@BeforeEach: ", invocation, invocationContext, extensionContext);
    }

    @Override
    public void interceptAfterEachMethod(Invocation<Void> invocation,
                                         ReflectiveInvocationContext<Method> invocationContext,
                                         ExtensionContext extensionContext) throws Throwable {
        interceptMethod("@AfterEach: ", invocation, invocationContext, extensionContext);
    }

    private void interceptMethod(
            String prefix,
            Invocation<Void> invocation,
            ReflectiveInvocationContext<Method> invocationContext,
            ExtensionContext extensionContext
    ) throws Throwable {
        String spanName = prefix + invocationContext.getExecutable().getName();

        Span span = startSpan(spanName);

        span.setAttribute(THREAD_ID, Thread.currentThread().getId());
        span.setAttribute(THREAD_NAME, Thread.currentThread().getName());
        span.setAttribute(CODE_FUNCTION, invocationContext.getExecutable().getName());
        span.setAttribute(CODE_NAMESPACE, invocationContext.getTargetClass().getName());

        logger.info(">>  {}", spanName);

        try (Scope ignored = span.makeCurrent()) {
            invocation.proceed();
        } catch (Throwable t) {
            span.recordException(t);
            span.setStatus(StatusCode.ERROR);
            throw t;
        } finally {
            span.end();
        }
    }

}
