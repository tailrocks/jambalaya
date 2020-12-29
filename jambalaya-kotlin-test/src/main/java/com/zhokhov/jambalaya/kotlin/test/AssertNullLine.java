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
