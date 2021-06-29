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

import edu.umd.cs.findbugs.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @author Alexey Zhokhov
 */
public class Tenant {

    // Predefined tenant. Default one, can be used in production as a main tenant.
    public static final String DEFAULT = "default";

    // Predefined tenant. Represents testing environment.
    public static final String TESTING = "testing";

    private final String tenantString;
    private final Map<String, String> tenantMapping = new ConcurrentHashMap<>();
    private String defaultTenant;

    public Tenant(@Nullable String tenantString) {
        if (!StringUtils.hasText(tenantString)) {
            this.tenantString = DEFAULT;
            defaultTenant = DEFAULT;
        } else {
            while (tenantString.contains("  ")) {
                tenantString = tenantString.replace("  ", " ");
            }

            tenantString = tenantString
                    .trim()
                    .replace(" = ", "=")
                    .replace(" =", "=")
                    .replace("= ", "=");

            String[] parts = tenantString.trim().split(" ");

            for (String part : parts) {
                part = part.trim();

                if (StringUtils.hasText(part)) {
                    if (part.contains("=")) {
                        String[] kv = part.split("=");

                        if (kv.length != 2) {
                            throw new IncorrectTenantStringException(tenantString);
                        }

                        String key = kv[0].trim();
                        String value = kv[1].trim();

                        if (StringUtils.isEmpty(key)) {
                            throw new IncorrectTenantStringException(tenantString);
                        }

                        if (StringUtils.isEmpty(value)) {
                            throw new IncorrectTenantStringException(tenantString);
                        }

                        if (tenantMapping.containsKey(key)) {
                            throw new IncorrectTenantStringException(tenantString);
                        }

                        tenantMapping.put(key, value);
                    } else {
                        if (defaultTenant != null) {
                            throw new IncorrectTenantStringException(tenantString);
                        }

                        defaultTenant = part.trim();
                    }
                }
            }

            if (defaultTenant == null) {
                defaultTenant = DEFAULT;
            }

            StringBuilder sb = new StringBuilder(defaultTenant);

            List<String> services = new ArrayList<>(tenantMapping.keySet())
                    .stream().sorted().collect(Collectors.toList());

            for (String service : services) {
                sb.append(" ").append(service).append("=").append(tenantMapping.get(service));
            }

            this.tenantString = sb.toString();
        }
    }

    public static Tenant getDefault() {
        return new Tenant(null);
    }

    public String getTenantByService(String service) {
        return tenantMapping.getOrDefault(service, defaultTenant);
    }

    public String toString() {
        return tenantString;
    }

}
