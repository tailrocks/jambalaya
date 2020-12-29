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
