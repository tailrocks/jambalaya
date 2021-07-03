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

import io.opentelemetry.api.baggage.Baggage;
import io.opentelemetry.context.Context;
import io.opentelemetry.context.Scope;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author Alexey Zhokhov
 */
class TenancyUtilsTests {

    @Test
    void test1() {
        // GIVEN:
        String tenant = "test";

        // WHEN:
        Context context = TenancyUtils.setTenantString(tenant);

        // THEN:
        assertNotNull(context);

        // WHEN:
        Baggage baggage = Baggage.fromContextOrNull(context);

        // THEN:
        assertNotNull(baggage);
        assertNotNull(baggage.getEntryValue("tenant"));
        assertEquals("dGVzdA", baggage.getEntryValue("tenant"));
    }

    @Test
    void test2() {
        // GIVEN:
        String tenant = "test";

        // EXPECT:
        try (Scope ignored = TenancyUtils.setTenantStringClosable(tenant)) {
            assertEquals(tenant, TenancyUtils.getTenantString());
        }
    }

    @Test
    void test3() {
        // GIVEN:
        String tenant = "test";

        // EXPECT:
        assertEquals(Tenant.DEFAULT, TenancyUtils.getTenantStringOrDefault());
    }

}
