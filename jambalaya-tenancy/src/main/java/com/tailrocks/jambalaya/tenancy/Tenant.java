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

import edu.umd.cs.findbugs.annotations.NonNull;
import edu.umd.cs.findbugs.annotations.Nullable;

import javax.annotation.concurrent.Immutable;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import static com.tailrocks.jambalaya.checks.Preconditions.checkNotBlank;

/**
 * @author Alexey Zhokhov
 */
@Immutable
public class Tenant implements Serializable {

    // Predefined tenant. Default one, can be used in production as a main tenant.
    public static final String DEFAULT = "default";

    // Predefined tenant. Represents testing environment.
    public static final String TESTING = "testing";

    private static final long serialVersionUID = 1715942534351608194L;

    private final Map<String, String> tenantMapping = new ConcurrentHashMap<>();
    private final String defaultTenant;
    private final String tenantString;

    private Tenant(@NonNull String defaultTenant, @Nullable Map<String, String> tenantMapping) {
        this.defaultTenant = defaultTenant;
        if (tenantMapping != null) {
            this.tenantMapping.putAll(tenantMapping);
        }
        this.tenantString = generateTenantString();
    }

    public static Tenant getDefault() {
        return new Tenant(DEFAULT, null);
    }

    public static Tenant getTesting() {
        return new Tenant(TESTING, null);
    }

    public static Tenant parse(@Nullable String tenantString) {
        if (tenantString == null || !StringUtils.hasText(tenantString)) {
            return getDefault();
        } else {
            return parseTenantString(cleanString(tenantString));
        }
    }

    private static Tenant parseTenantString(String tenantString) {
        String[] parts = tenantString.trim().split(" ");

        String defaultTenant = null;
        Map<String, String> tenantMapping = new HashMap<>();

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

                    // TODO check tenant for permitted symbols
                    tenantMapping.put(key, value);
                } else {
                    if (defaultTenant != null) {
                        throw new IncorrectTenantStringException(tenantString);
                    }

                    defaultTenant = part.trim();
                }
            }
        }

        return new Tenant(defaultTenant == null ? DEFAULT : defaultTenant, tenantMapping);
    }

    private static String cleanString(String value) {
        while (value.contains("  ")) {
            value = value.replace("  ", " ");
        }

        value = value
                .trim()
                .replace(" = ", "=")
                .replace(" =", "=")
                .replace("= ", "=");
        return value;
    }

    public String getByService(@NonNull String service) {
        checkNotBlank(service, "service");

        return tenantMapping.getOrDefault(service, defaultTenant);
    }

    public Tenant withDefault(@NonNull String tenantName) {
        checkNotBlank(tenantName, "tenantName");

        // TODO check tenant for permitted symbols
        return new Tenant(tenantName, tenantMapping);
    }

    public Tenant withService(@NonNull String serviceName, @NonNull String tenantName) {
        checkNotBlank(serviceName, "serviceName");
        checkNotBlank(tenantName, "tenantName");

        Map<String, String> clonedTenantMapping = new HashMap<>(tenantMapping);
        clonedTenantMapping.put(serviceName, tenantName);

        // TODO check tenant for permitted symbols
        return new Tenant(defaultTenant, clonedTenantMapping);
    }

    @Override
    public String toString() {
        return tenantString;
    }

    private String generateTenantString() {
        StringBuilder sb = new StringBuilder(defaultTenant);

        List<String> services = new ArrayList<>(tenantMapping.keySet())
                .stream().sorted().collect(Collectors.toList());

        for (String service : services) {
            sb.append(" ").append(service).append("=").append(tenantMapping.get(service));
        }

        return sb.toString();
    }

}
