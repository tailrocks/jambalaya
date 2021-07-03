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
