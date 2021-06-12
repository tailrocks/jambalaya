package com.zhokhov.jambalaya.tenancy;

/**
 * @author Alexey Zhokhov
 */
public class IncorrectTenantStringException extends RuntimeException {

    public IncorrectTenantStringException(String tenantString) {
        super("Incorrect tenant string: `" + tenantString + "`");
    }

}
