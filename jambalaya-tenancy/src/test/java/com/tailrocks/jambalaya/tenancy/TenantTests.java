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

import com.tailrocks.jambalaya.tenancy.IncorrectTenantStringException;
import com.tailrocks.jambalaya.tenancy.Tenant;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Alexey Zhokhov
 */
class TenantTests {

    @Test
    void test1() {
        Tenant tenant = Tenant.parse(null);

        assertEquals("abc", tenant.withDefault("abc").toString());
        assertEquals("abc", tenant.withDefault("abc").getByService("service1"));
        assertEquals("default", tenant.toString());
        assertEquals("default", tenant.getByService("service1"));
        assertEquals("default", tenant.getByService("service2"));
    }

    @Test
    void test2() {
        Tenant tenant = Tenant.parse(" ");

        assertEquals("abc", tenant.withDefault("abc").toString());
        assertEquals("abc", tenant.withDefault("abc").getByService("service1"));
        assertEquals("default", tenant.toString());
        assertEquals("default", tenant.getByService("service1"));
        assertEquals("default", tenant.getByService("service2"));
    }

    @Test
    void test3() {
        Tenant tenant = Tenant.parse("   ");

        assertEquals("abc", tenant.withDefault("abc").toString());
        assertEquals("abc", tenant.withDefault("abc").getByService("service1"));
        assertEquals("default", tenant.toString());
        assertEquals("default", tenant.getByService("service1"));
        assertEquals("default", tenant.getByService("service2"));
    }

    @Test
    void test4() {
        Tenant tenant = Tenant.parse("testing");

        assertEquals("abc", tenant.withDefault("abc").toString());
        assertEquals("abc", tenant.withDefault("abc").getByService("service1"));
        assertEquals("testing", tenant.toString());
        assertEquals("testing", tenant.getByService("service1"));
        assertEquals("testing", tenant.getByService("service2"));
    }

    @Test
    void test5() {
        Tenant tenant = Tenant.parse("testing ");

        assertEquals("testing", tenant.toString());
        assertEquals("testing", tenant.getByService("service1"));
        assertEquals("testing", tenant.getByService("service2"));
    }

    @Test
    void test6() {
        Tenant tenant = Tenant.parse("testing service1=main");

        assertEquals("abc service1=main", tenant.withDefault("abc").toString());
        assertEquals("main", tenant.withDefault("abc").getByService("service1"));
        assertEquals("testing service1=main service2=copy", tenant.withService("service2", "copy").toString());
        assertEquals("testing service1=analog", tenant.withService("service1", "analog").toString());
        assertEquals("testing service1=main", tenant.toString());
        assertEquals("main", tenant.getByService("service1"));
        assertEquals("testing", tenant.getByService("service2"));
    }

    @Test
    void test7() {
        Tenant tenant = Tenant.parse("service1=main");

        assertEquals("default service1=main", tenant.toString());
        assertEquals("main", tenant.getByService("service1"));
        assertEquals("default", tenant.getByService("service2"));
    }

    @Test
    void test8() {
        Tenant tenant = Tenant.parse(" service1 = main ");

        assertEquals("default service1=main", tenant.toString());
        assertEquals("main", tenant.getByService("service1"));
        assertEquals("default", tenant.getByService("service2"));
    }

    @Test
    void test9() {
        IncorrectTenantStringException exception = assertThrows(IncorrectTenantStringException.class, () ->
                Tenant.parse("=main")
        );

        assertEquals("Incorrect tenant string: `=main`", exception.getMessage());
    }

    @Test
    void test10() {
        Tenant tenant = Tenant.parse("x =main");

        assertEquals("default x=main", tenant.toString());
        assertEquals("main", tenant.getByService("x"));
        assertEquals("default", tenant.getByService("service2"));
    }

    @Test
    void test11() {
        IncorrectTenantStringException exception = assertThrows(IncorrectTenantStringException.class, () ->
                Tenant.parse("x service=")
        );

        assertEquals("Incorrect tenant string: `x service=`", exception.getMessage());
    }

}
