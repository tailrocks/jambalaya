package com.tailrocks.jambalayay.tenancy.spock


import org.spockframework.runtime.extension.ExtensionAnnotation

import java.lang.annotation.*

@Retention(RetentionPolicy.RUNTIME)
@Target([ElementType.TYPE, ElementType.FIELD, ElementType.METHOD])
@ExtensionAnnotation(TenantSpockExtension.class)
@Inherited
@interface ActiveTenantSpock {
    String value();
}