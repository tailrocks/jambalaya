package com.zhokhov.jambalaya.junit.opentelemetry;

/**
 * @author Alexey Zhokhov
 */
@FunctionalInterface
public interface UncheckedCallable<V> {

    V call();

}
