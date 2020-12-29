/*
 * Copyright 2020 original authors
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
package com.zhokhov.jambalaya.kotlin.test;

import edu.umd.cs.findbugs.annotations.NonNull;

import java.util.HashSet;
import java.util.Set;

import static java.util.Objects.requireNonNull;

/**
 * EXPERIMENTAL
 */
public final class AssertGeneratorConfig {

    public static final Set<String> DEFAULT_IGNORED_METHODS = new HashSet<>();

    static {
        DEFAULT_IGNORED_METHODS.add("toString");
        DEFAULT_IGNORED_METHODS.add("hashCode");
        DEFAULT_IGNORED_METHODS.add("getClass");
        DEFAULT_IGNORED_METHODS.add("listIterator");
        DEFAULT_IGNORED_METHODS.add("marshaller");
        DEFAULT_IGNORED_METHODS.add("__typename");
    }

    private static AssertGeneratorConfig DEFAULT_CONFIG = new AssertGeneratorConfig(
            new Indentation(),
            new HashSet<>(),
            DEFAULT_IGNORED_METHODS
    );

    private final Indentation indentation;
    private final Set<String> packagesToPrintAllPublicMethods;
    private final Set<String> globalIgnoredMethods;

    public AssertGeneratorConfig(@NonNull Indentation indentation,
                                 @NonNull Set<String> packagesToPrintAllPublicMethods,
                                 @NonNull Set<String> globalIgnoredMethods) {
        requireNonNull(indentation, "indentation");
        requireNonNull(packagesToPrintAllPublicMethods, "packagesToPrintAllPublicMethods");
        requireNonNull(globalIgnoredMethods, "globalIgnoredMethods");

        this.indentation = indentation;
        this.packagesToPrintAllPublicMethods = packagesToPrintAllPublicMethods;
        this.globalIgnoredMethods = globalIgnoredMethods;
    }

    public static AssertGeneratorConfig getDefaultConfig() {
        return DEFAULT_CONFIG;
    }

    public static void setDefaultConfig(@NonNull AssertGeneratorConfig config) {
        requireNonNull(config, "config");

        DEFAULT_CONFIG = config;
    }

    Indentation getIndentation() {
        return indentation;
    }

    Set<String> getPackagesToPrintAllPublicMethods() {
        return packagesToPrintAllPublicMethods;
    }

    Set<String> getGlobalIgnoredMethods() {
        return globalIgnoredMethods;
    }

}
