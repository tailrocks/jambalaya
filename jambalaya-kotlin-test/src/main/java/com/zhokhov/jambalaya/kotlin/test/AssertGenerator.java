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

import com.zhokhov.jambalaya.kotlin.test.apollo.AssertGeneratorApollo;
import edu.umd.cs.findbugs.annotations.NonNull;
import edu.umd.cs.findbugs.annotations.Nullable;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

/**
 * EXPERIMENTAL
 */
public final class AssertGenerator {

    private static final Set<String> IGNORED_VALUES = new HashSet<>();

    static {
        IGNORED_VALUES.add("java.lang.Class");
    }

    private final AssertGeneratorConfig config;
    private final AssertGeneratorApollo assertGeneratorApollo;

    public AssertGenerator(@NonNull AssertGeneratorConfig config) {
        requireNonNull(config, "config");
        this.config = config;
        this.assertGeneratorApollo = new AssertGeneratorApollo(config);
    }

    @Nullable
    public static AssertLine generate(@Nullable Object value, @NonNull String variableName) {
        AssertGenerator assertGenerator = new AssertGenerator(AssertGeneratorConfig.getDefaultConfig());
        AssertLine rootAssertLine = assertGenerator.generateLine(value, variableName);
        if (rootAssertLine == null) {
            return null;
        }
        System.out.println();
        System.out.println();
        System.out.println(rootAssertLine);
        System.out.println();
        System.out.println();
        return rootAssertLine;
    }

    @Nullable
    public AssertLine generateLine(@Nullable Object value, @NonNull String variableName) {
        requireNonNull(variableName, "variableName");

        if (value == null) {
            return AssertNullLine.newRoot(variableName, config.getIndentation());
        } else {
            if (IGNORED_VALUES.contains(value.getClass().getName())) {
                return null;
            }

            AssertNotNullLine line = AssertNotNullLine.newRoot(variableName, config.getIndentation());

            traverse(value, line);

            return line;
        }
    }

    private void traverse(@NonNull Object value, @NonNull AssertNotNullLine assertLineContainer) {
        if (IGNORED_VALUES.contains(value.getClass().getName())) {
            return;
        }

        List<Field> publicFields = Arrays.stream(value.getClass().getDeclaredFields())
                .filter(f -> Modifier.isPublic(f.getModifiers()) && !Modifier.isStatic(f.getModifiers()))
                .toList();

        for (Field field : publicFields) {
            Object result;

            try {
                result = field.get(value);
            } catch (IllegalAccessException e) {
                assertLineContainer.addCommentLine(field.getName(), e.toString());
                continue;
            }

            if (result == null) {
                assertLineContainer.addAssertNullLine(field.getName());
            } else {
                if (IGNORED_VALUES.contains(result.getClass().getName())) {
                    continue;
                }

                if (isStandardClass(result)) {
                    assertLineContainer.addAssertEqualsLine(field.getName(), result);
                } else {
                    AssertNotNullLine notNullLine = assertLineContainer.addAssertNotNullLine(field.getName());

                    if (result instanceof List) {
                        traverseList((List) result, notNullLine);
                    } else if (result instanceof Set) {
                        // ignore
                    } else {
                        traverse(result, notNullLine);
                    }
                }
            }
        }

        List<Method> publicMethods;

        publicMethods = assertGeneratorApollo.detectPublicMethods(value);

        if (publicMethods == null) {
            if (isScanAllPublicMethods(value)) {
                // Get the public methods associated with this class.
                Method[] methods = value.getClass().getMethods();

                publicMethods = Arrays.stream(methods)
                        .filter(it -> !it.getReturnType().equals(Void.TYPE)
                                && it.getParameterCount() == 0
                                && !config.getGlobalIgnoredMethods().contains(it.getName()))
                        .collect(Collectors.toList());
            } else {
                publicMethods = new ArrayList<>();

                try {
                    final PropertyDescriptor[] propertyDescriptors = Introspector.getBeanInfo(value.getClass()).getPropertyDescriptors();
                    for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
                        Method readMethod = propertyDescriptor.getReadMethod();

                        if (readMethod != null && !config.getGlobalIgnoredMethods().contains(readMethod.getName())) {
                            publicMethods.add(readMethod);
                        }
                    }
                } catch (IntrospectionException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        for (Method method : publicMethods) {
            Object result;

            try {
                result = method.invoke(value);
            } catch (InvocationTargetException e) {
                assertLineContainer.addCommentLine(method.getName() + "()", e.getTargetException().toString());
                continue;
            } catch (IllegalAccessException e) {
                assertLineContainer.addCommentLine(method.getName() + "()", e.toString());
                continue;
            }

            if (result == null) {
                assertLineContainer.addAssertNullLine(method.getName() + "()");
            } else {
                if (IGNORED_VALUES.contains(result.getClass().getName())) {
                    continue;
                }

                if (isStandardClass(result)) {
                    assertLineContainer.addAssertEqualsLine(method.getName() + "()", result);
                } else {
                    AssertNotNullLine notNullLine = assertLineContainer.addAssertNotNullLine(method.getName() + "()");

                    if (result instanceof List) {
                        traverseList((List) result, notNullLine);
                    } else if (result instanceof Set) {
                        // ignore
                    } else {
                        traverse(result, notNullLine);
                    }
                }
            }
        }
    }

    private void traverseList(List<Object> list, AssertNotNullLine assertLineContainer) {
        assertLineContainer.addAssertEqualsLine("size", list.size());

        if (!list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                Object value = list.get(i);

                if (value == null) {
                    assertLineContainer.addAssertNullLine("get(" + i + ")");
                } else {
                    if (IGNORED_VALUES.contains(value.getClass().getName())) {
                        continue;
                    }

                    if (isStandardClass(value)) {
                        assertLineContainer.addAssertEqualsLine("get(" + i + ")", value);
                    } else {
                        AssertNotNullLine notNullLine =
                                assertLineContainer.addAssertNotNullLine("get(" + i + ")");

                        if (value instanceof List) {
                            traverseList((List) value, notNullLine);
                        } else if (value instanceof Set) {
                            // ignore
                        } else {
                            traverse(value, notNullLine);
                        }
                    }
                }
            }
        }
    }

    private boolean isScanAllPublicMethods(Object value) {
        for (String packageName : config.getPackagesToPrintAllPublicMethods()) {
            if (value.getClass().getName().startsWith(packageName)) {
                return true;
            }
        }
        return false;
    }

    private boolean isStandardClass(Object value) {
        return value instanceof String
                || value instanceof Number
                || value instanceof Boolean
                || value instanceof Enum
                || value instanceof Date
                || value instanceof Temporal;
    }

}
