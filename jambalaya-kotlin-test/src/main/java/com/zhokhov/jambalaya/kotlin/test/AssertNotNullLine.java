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

import org.jspecify.annotations.NonNull;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.joining;

/**
 * EXPERIMENTAL
 */
public final class AssertNotNullLine implements com.zhokhov.jambalaya.kotlin.test.AssertLine {

    private final String methodName;
    private final List<com.zhokhov.jambalaya.kotlin.test.AssertLine> subLines = new ArrayList<>();
    private final Indentation indentation;
    private final int level;

    AssertNotNullLine(String methodName, Indentation indentation, int level) {
        this.methodName = methodName;
        this.indentation = indentation;
        this.level = level;
    }

    @NonNull
    static AssertNotNullLine newRoot(String methodName, Indentation indentation) {
        return new AssertNotNullLine(methodName, indentation, 0);
    }

    @NonNull
    AssertNotNullLine addAssertNotNullLine(String methodName) {
        AssertNotNullLine line = new AssertNotNullLine(methodName, indentation, level + 1);
        subLines.add(line);
        return line;
    }

    @NonNull
    AssertNullLine addAssertNullLine(String methodName) {
        AssertNullLine line = new AssertNullLine(methodName, indentation, level + 1);
        subLines.add(line);
        return line;
    }

    @NonNull
    AssertEquals addAssertEqualsLine(String methodName, Object value) {
        AssertEquals line = new AssertEquals(methodName, value, indentation, level + 1);
        subLines.add(line);
        return line;
    }

    @NonNull
    CommentLine addCommentLine(String methodName, String comment) {
        CommentLine line = new CommentLine(methodName, comment, indentation, level + 1);
        subLines.add(line);
        return line;
    }

    @Override
    public String toString() {
        return indentation.print(level) + "assertNotNull(" + MethodNameUtils.printName(methodName) + ").apply {\n" +
                subLines.stream()
                        .map(Object::toString)
                        .collect(joining("\n")) + "\n" +
                indentation.print(level) + "}";
    }

}
