package com.zhokhov.jambalaya.kotlin.test.apollo;

import org.jspecify.annotations.NonNull;

import java.lang.reflect.Method;
import java.util.List;

public interface ComponentAssertGenerator {

    List<Method> detectPublicMethods(@NonNull Object value);

}
