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
