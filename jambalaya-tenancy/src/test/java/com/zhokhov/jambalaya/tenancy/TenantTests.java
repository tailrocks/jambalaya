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
package com.zhokhov.jambalaya.tenancy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Alexey Zhokhov
 */
public class TenantTests {

    @Test
    public void test1() {
        Tenant tenant = new Tenant(null);

        assertEquals("default", tenant.toString());
        assertEquals("default", tenant.getTenantByService("service1"));
        assertEquals("default", tenant.getTenantByService("service2"));
    }

    @Test
    public void test2() {
        Tenant tenant = new Tenant("testing");

        assertEquals("testing", tenant.toString());
        assertEquals("testing", tenant.getTenantByService("service1"));
        assertEquals("testing", tenant.getTenantByService("service2"));
    }

    @Test
    public void test3() {
        Tenant tenant = new Tenant("testing ");

        assertEquals("testing", tenant.toString());
        assertEquals("testing", tenant.getTenantByService("service1"));
        assertEquals("testing", tenant.getTenantByService("service2"));
    }

    @Test
    public void test4() {
        Tenant tenant = new Tenant("testing service1=main");

        assertEquals("testing service1=main", tenant.toString());
        assertEquals("main", tenant.getTenantByService("service1"));
        assertEquals("testing", tenant.getTenantByService("service2"));
    }

    @Test
    public void test5() {
        Tenant tenant = new Tenant("service1=main");

        assertEquals("default service1=main", tenant.toString());
        assertEquals("main", tenant.getTenantByService("service1"));
        assertEquals("default", tenant.getTenantByService("service2"));
    }

    @Test
    public void test6() {
        Tenant tenant = new Tenant(" service1 = main ");

        assertEquals("default service1=main", tenant.toString());
        assertEquals("main", tenant.getTenantByService("service1"));
        assertEquals("default", tenant.getTenantByService("service2"));
    }

    @Test
    public void test7() {
        IncorrectTenantStringException exception = assertThrows(IncorrectTenantStringException.class, () ->
                new Tenant("=main")
        );

        assertEquals("Incorrect tenant string: `=main`", exception.getMessage());
    }

    @Test
    public void test8() {
        Tenant tenant = new Tenant("x =main");

        assertEquals("default x=main", tenant.toString());
        assertEquals("main", tenant.getTenantByService("x"));
        assertEquals("default", tenant.getTenantByService("service2"));
    }

    @Test
    public void test9() {
        IncorrectTenantStringException exception = assertThrows(IncorrectTenantStringException.class, () ->
                new Tenant("x service=")
        );

        assertEquals("Incorrect tenant string: `x service=`", exception.getMessage());
    }

}
