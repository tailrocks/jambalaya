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
package com.zhokhov.jambalaya.kotlin.test.apollo;

import com.zhokhov.jambalaya.kotlin.test.AssertGeneratorConfig;
import edu.umd.cs.findbugs.annotations.NonNull;
import edu.umd.cs.findbugs.annotations.Nullable;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AssertGeneratorApollo {

    private final AssertGeneratorConfig config;

    @Nullable
    private Class responseClass;

    @Nullable
    private Class operationDataClass;

    @Nullable
    private Class queryClass;

    @Nullable
    private Class mutationClass;

    public AssertGeneratorApollo(AssertGeneratorConfig config) {
        this.config = config;

        try {
            responseClass = Class.forName("com.apollographql.apollo.api.Response");
        } catch (ClassNotFoundException ignored) {
            responseClass = null;
        }

        try {
            operationDataClass = Class.forName("com.apollographql.apollo.api.Operation$Data");
        } catch (ClassNotFoundException ignored) {
            operationDataClass = null;
        }

        try {
            queryClass = Class.forName("com.apollographql.apollo.api.Query");
        } catch (ClassNotFoundException ignored) {
            queryClass = null;
        }

        try {
            mutationClass = Class.forName("com.apollographql.apollo.api.Mutation");
        } catch (ClassNotFoundException ignored) {
            mutationClass = null;
        }
    }

    private static final List<String> RESPONSE_PUBLIC_METHODS_NAME =
            List.of("isFromCache", "hasErrors", "getErrors", "getData");

    public List<Method> detectPublicMethods(@NonNull Object value) {
        if (responseClass == null) {
            return null;
        }

        if (responseClass.isInstance(value)) {
            // Get the public methods associated with this class.
            Method[] methods = value.getClass().getMethods();

            return Arrays.stream(methods)
                    .filter(it -> !it.getReturnType().equals(Void.TYPE)
                            && it.getParameterCount() == 0
                            && RESPONSE_PUBLIC_METHODS_NAME.contains(it.getName()))
                    .collect(Collectors.toList());
        }

        if (operationDataClass == null) {
            return null;
        }

        if (operationDataClass.isInstance(value)) {
            // Get the public methods associated with this class.
            Method[] methods = value.getClass().getMethods();

            return Arrays.stream(methods)
                    .filter(it -> !it.getReturnType().equals(Void.TYPE)
                            && it.getParameterCount() == 0
                            && !"marshaller".equals(it.getName())
                            && !config.getGlobalIgnoredMethods().contains(it.getName()))
                    .collect(Collectors.toList());
        }

        if (queryClass == null) {
            return null;
        }

        Class declaringClass = value.getClass().getDeclaringClass();

        if (declaringClass != null) {
            if (queryClass.isAssignableFrom(declaringClass) || mutationClass.isAssignableFrom(declaringClass)) {
                // Get the public methods associated with this class.
                Method[] methods = value.getClass().getMethods();

                return Arrays.stream(methods)
                        .filter(it -> !it.getReturnType().equals(Void.TYPE)
                                && it.getParameterCount() == 0
                                && !"marshaller".equals(it.getName())
                                && !"__typename".equals(it.getName())
                                && !config.getGlobalIgnoredMethods().contains(it.getName()))
                        .collect(Collectors.toList());
            }
        }

        return null;
    }

}
