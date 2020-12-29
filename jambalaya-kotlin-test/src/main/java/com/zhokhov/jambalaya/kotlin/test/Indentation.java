package com.zhokhov.jambalaya.kotlin.test;

import edu.umd.cs.findbugs.annotations.NonNull;

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
