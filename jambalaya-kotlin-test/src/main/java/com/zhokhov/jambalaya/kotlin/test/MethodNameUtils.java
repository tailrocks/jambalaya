package com.zhokhov.jambalaya.kotlin.test;

import edu.umd.cs.findbugs.annotations.NonNull;

import static java.util.Objects.requireNonNull;

/**
 * EXPERIMENTAL
 */
public final class MethodNameUtils {

    private MethodNameUtils() {
    }

    @NonNull
    public static String printName(@NonNull String name) {
        requireNonNull(name, name);

        if (name.startsWith("get") && !name.startsWith("get(") && name.endsWith("()")) {
            char[] chars = name.toCharArray();

            if (Character.isUpperCase(chars[3])) {
                StringBuilder result = new StringBuilder();
                for (int i = 3; i < chars.length - 2; i++) {
                    if (i == 3) {
                        result.append(Character.toLowerCase(chars[i]));
                    } else {
                        result.append(chars[i]);
                    }
                }
                return result.toString();
            } else {
                return name;
            }
        } else if (name.startsWith("is") && !name.startsWith("is(") && name.endsWith("()")) {
            char[] chars = name.toCharArray();

            if (Character.isUpperCase(chars[2])) {
                return name.substring(0, name.length() - 2);
            } else {
                return name;
            }
        } else {
            return name;
        }
    }

}
