package com.zhokhov.jambalaya.kotlin.test.apollo;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class AssertGeneratorGrpc implements ComponentAssertGenerator {

    private static final List<String> IGNORE_METHODS = List.of(
            "getClass",
            "getAllFields",
            "getDefaultInstanceForType",
            "getDescriptorForType",
            "isInitialized",
            "getInitializationErrorString",
            "getParserForType",
            "getSerializedSize",
            "getUnknownFields"
    );

    @Nullable
    private Class generatedMessageV3Class;

    public AssertGeneratorGrpc() {
        try {
            generatedMessageV3Class = Class.forName("com.google.protobuf.GeneratedMessageV3");
        } catch (ClassNotFoundException ignored) {
            generatedMessageV3Class = null;
        }
    }

    public List<Method> detectPublicMethods(@NonNull Object value) {
        if (generatedMessageV3Class.isInstance(value)) {
            List<Method> publicMethods = new ArrayList<>();

            try {
                final PropertyDescriptor[] propertyDescriptors = Introspector.getBeanInfo(value.getClass()).getPropertyDescriptors();
                for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
                    Method readMethod = propertyDescriptor.getReadMethod();

                    if (readMethod != null) {
                        if (!IGNORE_METHODS.contains(readMethod.getName())) {
                            if (!(readMethod.getName().endsWith("Bytes"))
                                    && !(readMethod.getName().endsWith("OrBuilder"))
                                    && !(readMethod.getName().endsWith("OrBuilderList"))) {
                                publicMethods.add(readMethod);
                            }
                        }
                    }
                }
            } catch (IntrospectionException e) {
                throw new RuntimeException(e);
            }

            return publicMethods;
        }

        return null;
    }

}
