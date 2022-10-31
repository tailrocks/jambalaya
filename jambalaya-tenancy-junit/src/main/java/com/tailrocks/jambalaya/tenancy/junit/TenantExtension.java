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
package com.tailrocks.jambalaya.tenancy.junit;

import com.tailrocks.jambalaya.tenancy.StringUtils;
import com.tailrocks.jambalaya.tenancy.TenancyUtils;
import io.opentelemetry.context.Scope;
import org.junit.jupiter.api.extension.DynamicTestInvocationContext;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.InvocationInterceptor;
import org.junit.jupiter.api.extension.ReflectiveInvocationContext;
import org.junit.platform.commons.support.AnnotationSupport;

import java.lang.reflect.Method;
import java.util.Optional;

import static com.tailrocks.jambalaya.tenancy.TenancyUtils.setTenantStringClosable;

/**
 * @author Alexey Zhokhov
 */
public class TenantExtension implements InvocationInterceptor {

    @Override
    public void interceptBeforeAllMethod(Invocation<Void> invocation,
                                         ReflectiveInvocationContext<Method> invocationContext,
                                         ExtensionContext extensionContext) throws Throwable {
        proceedWithTenant(invocation, invocationContext, extensionContext);
    }

    @Override
    public void interceptAfterAllMethod(Invocation<Void> invocation,
                                        ReflectiveInvocationContext<Method> invocationContext,
                                        ExtensionContext extensionContext) throws Throwable {
        proceedWithTenant(invocation, invocationContext, extensionContext);
    }

    @Override
    public void interceptBeforeEachMethod(Invocation<Void> invocation,
                                          ReflectiveInvocationContext<Method> invocationContext,
                                          ExtensionContext extensionContext) throws Throwable {
        proceedWithTenant(invocation, invocationContext, extensionContext);
    }

    @Override
    public void interceptAfterEachMethod(Invocation<Void> invocation,
                                         ReflectiveInvocationContext<Method> invocationContext,
                                         ExtensionContext extensionContext) throws Throwable {
        proceedWithTenant(invocation, invocationContext, extensionContext);
    }

    @Override
    public void interceptTestMethod(Invocation<Void> invocation,
                                    ReflectiveInvocationContext<Method> invocationContext,
                                    ExtensionContext extensionContext) throws Throwable {
        proceedWithTenant(invocation, invocationContext, extensionContext);
    }

    @Override
    public void interceptTestTemplateMethod(Invocation<Void> invocation,
                                            ReflectiveInvocationContext<Method> invocationContext,
                                            ExtensionContext extensionContext) throws Throwable {
        proceedWithTenant(invocation, invocationContext, extensionContext);
    }

    @Override
    public <T> T interceptTestFactoryMethod(Invocation<T> invocation,
                                            ReflectiveInvocationContext<Method> invocationContext,
                                            ExtensionContext extensionContext) throws Throwable {
        return proceedWithTenant(invocation, invocationContext, extensionContext);
    }

    private <T> T proceedWithTenant(Invocation<T> invocation,
                                   ReflectiveInvocationContext<Method> invocationContext,
                                   ExtensionContext extensionContext) throws Throwable {

        String tenant = TenancyUtils.getTenantString();

        ActiveTenant methodTenant = invocationContext.getExecutable().getAnnotation(ActiveTenant.class);
        if (methodTenant == null) {
            Optional<ActiveTenant> clazzTenant = AnnotationSupport.findAnnotation(
                    extensionContext.getRequiredTestClass(),
                    ActiveTenant.class
            );

            if (clazzTenant.isPresent()) {
                tenant = clazzTenant.get().value();
            }
        } else {
            tenant = methodTenant.value();
        }

        if (StringUtils.isNotEmpty(tenant)) {
            try (Scope ignored = setTenantStringClosable(tenant)) {
                return invocation.proceed();
            }
        } else {
            return invocation.proceed();
        }
    }

}
