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
