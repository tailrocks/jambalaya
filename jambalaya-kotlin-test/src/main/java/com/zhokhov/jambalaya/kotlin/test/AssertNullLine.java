package com.zhokhov.jambalaya.kotlin.test;

import edu.umd.cs.findbugs.annotations.NonNull;

/**
 * EXPERIMENTAL
 */
public final class AssertNullLine implements com.zhokhov.jambalaya.kotlin.test.AssertLine {

    private final String methodName;
    private final Indentation indentation;
    private final int level;

    AssertNullLine(String methodName, Indentation indentation, int level) {
        this.methodName = methodName;
        this.indentation = indentation;
        this.level = level;
    }

    @NonNull
    static AssertNullLine newRoot(String methodName, Indentation indentation) {
        return new AssertNullLine(methodName, indentation, 0);
    }

    @Override
    public String toString() {
        return indentation.print(level) + "assertNull(" + MethodNameUtils.printName(methodName) + ")";
    }

}
