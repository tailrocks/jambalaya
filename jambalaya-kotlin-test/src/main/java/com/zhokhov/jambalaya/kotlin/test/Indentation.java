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

import java.util.Collections;

import static java.util.Objects.requireNonNull;

/**
 * EXPERIMENTAL
 */
public class Indentation {

    private final String symbol;
    private final int count;

    public Indentation() {
        this(" ", 4);
    }

    public Indentation(@NonNull String symbol, int count) {
        requireNonNull(symbol, "symbol");

        this.symbol = symbol;
        this.count = count;
    }

    @NonNull
    public String print(int level) {
        return String.join("", Collections.nCopies(level * count, symbol));
    }

}
