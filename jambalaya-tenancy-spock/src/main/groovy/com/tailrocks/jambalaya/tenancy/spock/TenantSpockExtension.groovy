package com.tailrocks.jambalaya.tenancy.spock

import com.tailrocks.jambalaya.tenancy.StringUtils
import com.tailrocks.jambalaya.tenancy.TenancyUtils
import io.opentelemetry.context.Scope
import org.spockframework.runtime.extension.IAnnotationDrivenExtension
import org.spockframework.runtime.extension.IMethodInterceptor
import org.spockframework.runtime.extension.IMethodInvocation
import org.spockframework.runtime.model.FeatureInfo
import org.spockframework.runtime.model.SpecInfo

class TenantSpockExtension implements IAnnotationDrivenExtension<ActiveTenant> {

    @Override
    void visitSpecAnnotation(ActiveTenant annotation, SpecInfo spec) {
        spec.addInterceptor(new TenantMethodInterceptor(annotation))
    }

    @Override
    void visitFeatureAnnotation(ActiveTenant annotation, FeatureInfo feature) {
        feature.addInterceptor(new TenantMethodInterceptor(annotation))
    }

    private static class TenantMethodInterceptor implements IMethodInterceptor {

        ActiveTenant activeTenant

        TenantMethodInterceptor(ActiveTenant activeTenant) {
            this.activeTenant = activeTenant
        }

        @Override
        void intercept(IMethodInvocation invocation) throws Throwable {
            String tenantString = activeTenant.value()
            if (StringUtils.isNotEmpty(tenantString)) {
                Scope ignored = TenancyUtils.setTenantStringClosable(tenantString)

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
