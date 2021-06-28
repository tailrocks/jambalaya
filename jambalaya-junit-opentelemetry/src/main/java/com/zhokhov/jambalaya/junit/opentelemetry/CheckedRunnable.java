package com.zhokhov.jambalaya.junit.opentelemetry;

/**
 * @author Alexey Zhokhov
 */
@FunctionalInterface
public interface CheckedRunnable {

    void run() throws Throwable;

}
