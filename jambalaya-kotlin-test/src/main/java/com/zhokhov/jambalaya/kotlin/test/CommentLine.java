package com.zhokhov.jambalaya.kotlin.test;

import edu.umd.cs.findbugs.annotations.NonNull;

/**
 * EXPERIMENTAL
 */
public class CommentLine implements AssertLine {

    private final String methodName;
    private final String comment;
    private final Indentation indentation;
    private final int level;

    CommentLine(String methodName, String comment, Indentation indentation, int level) {
        this.methodName = methodName;
        this.comment = comment;
        this.indentation = indentation;
        this.level = level;
    }

    @NonNull
    static CommentLine newRoot(String methodName, String comment, Indentation indentation) {
        return new CommentLine(methodName, comment, indentation, 0);
    }

    @Override
    public String toString() {
        return indentation.print(level) + "// " + MethodNameUtils.printName(methodName) + " - " + comment;
    }

}
