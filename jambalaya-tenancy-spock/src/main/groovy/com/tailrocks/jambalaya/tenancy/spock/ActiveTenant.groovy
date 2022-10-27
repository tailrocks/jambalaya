package com.tailrocks.jambalaya.tenancy.spock


import org.spockframework.runtime.extension.ExtensionAnnotation

import java.lang.annotation.*

@Retention(RetentionPolicy.RUNTIME)
@Target([ElementType.TYPE, ElementType.FIELD, ElementType.METHOD])
@ExtensionAnnotation(TenantSpockExtension.class)
@Inherited
@interface ActiveTenant {
    String value();
}