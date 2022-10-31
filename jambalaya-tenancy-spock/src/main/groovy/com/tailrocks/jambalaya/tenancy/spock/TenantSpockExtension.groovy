package com.tailrocks.jambalaya.tenancy.spock

import com.tailrocks.jambalaya.tenancy.StringUtils
import com.tailrocks.jambalaya.tenancy.TenancyUtils
import io.opentelemetry.context.Scope
import org.junit.platform.commons.support.AnnotationSupport
import org.spockframework.runtime.extension.IAnnotationDrivenExtension
import org.spockframework.runtime.extension.IMethodInterceptor
import org.spockframework.runtime.extension.IMethodInvocation
import org.spockframework.runtime.model.FeatureInfo
import org.spockframework.runtime.model.SpecInfo

class TenantSpockExtension implements IAnnotationDrivenExtension<ActiveTenant> {

    @Override
    void visitSpecAnnotation(ActiveTenant annotation, SpecInfo spec) {
        spec.addInterceptor(new TenantMethodInterceptor(annotation,spec))
    }

    @Override
    void visitFeatureAnnotation(ActiveTenant annotation, FeatureInfo feature) {
        feature.addInterceptor(new TenantMethodInterceptor(annotation, feature.spec))
    }

    private static class TenantMethodInterceptor implements IMethodInterceptor {

        ActiveTenant activeTenant
        SpecInfo spec

        TenantMethodInterceptor(ActiveTenant activeTenant, SpecInfo spec) {
            this.activeTenant = activeTenant
            this.spec = spec
        }

        @Override
        void intercept(IMethodInvocation invocation) throws Throwable {
            String tenant = TenancyUtils.getTenantString()
            ActiveTenant methodTenant = activeTenant
            if (methodTenant == null) {
                ActiveTenant clazzTenant = spec.getAnnotation(ActiveTenant)

                if (clazzTenant != null) {
                    tenant = clazzTenant.value()
                }
            } else {
                tenant = methodTenant.value()
            }

            if (StringUtils.isNotEmpty(tenant)) {
                Scope ignored = TenancyUtils.setTenantStringClosable(tenant)

                try {
                    invocation.proceed()
                } catch (Throwable throwable) {
                    if (ignored != null) {
                        try {
                            ignored.close()
                        } catch (Throwable ex) {
                            throwable.addSuppressed(ex)
                        }
                    }

                    throw throwable
                }

                if (ignored != null) {
                    ignored.close()
                }
            } else {
                invocation.proceed()
            }
        }

    }

}
