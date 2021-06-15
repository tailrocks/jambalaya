package com.zhokhov.jambalaya.tenancy;

/**
 * @author Alexey Zhokhov
 */
public class EmptyTenantException extends RuntimeException {

    public EmptyTenantException() {
        super("Tenant not set");
    }

}
