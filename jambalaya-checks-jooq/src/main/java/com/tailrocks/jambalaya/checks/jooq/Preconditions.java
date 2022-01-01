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
package com.tailrocks.jambalaya.checks.jooq;

import edu.umd.cs.findbugs.annotations.NonNull;
import edu.umd.cs.findbugs.annotations.Nullable;
import org.jooq.UpdatableRecord;

import java.util.List;

import static com.tailrocks.jambalaya.checks.Preconditions.checkNotNull;
import static java.util.Objects.requireNonNull;

/**
 * Preconditions provide static methods to check that a method or a constructor is invoked with proper parameter or not.
 * It checks the pre-conditions. Its methods throw {@link IllegalArgumentException} on failure.
 *
 * @author Alexey Zhokhov
 */
public final class Preconditions {

    private Preconditions() {
    }

    /**
     * Ensures that the parameter being validated is a new record.
     *
     * @param value    parameter's value
     * @param argument the name of parameter
     */
    public static void checkNew(@NonNull UpdatableRecord value, @NonNull String argument) {
        requireNonNull(value, "value");
        requireNonNull(argument, "argument");

        if (!isNew(value)) {
            throw new IllegalArgumentException("The \"" + argument + "\" must be new");
        }
    }

    /**
     * Ensures that the parameter being validated is a collection of new records.
     *
     * @param value    parameter's value
     * @param argument the name of parameter
     */
    public static void checkNew(@NonNull List<? extends UpdatableRecord> value, @NonNull String argument) {
        requireNonNull(value, "value");
        requireNonNull(argument, "argument");

        for (int i = 0; i < value.size(); i++) {
            checkNew(value.get(i), argument + "[" + i + "]");
        }
    }

    /**
     * Ensures that the parameter being validated is not a new record.
     *
     * @param value    parameter's value
     * @param argument the name of parameter
     */
    public static void checkNotNew(@NonNull UpdatableRecord value, @NonNull String argument) {
        requireNonNull(value, "value");
        requireNonNull(argument, "argument");

        if (isNew(value)) {
            throw new IllegalArgumentException("The \"" + argument + "\" must not be new");
        }
    }

    /**
     * Ensures that the parameter being validated is a collection of not new records.
     *
     * @param value    parameter's value
     * @param argument the name of parameter
     */
    public static void checkNotNew(@NonNull List<? extends UpdatableRecord> value, @NonNull String argument) {
        requireNonNull(value, "value");
        requireNonNull(argument, "argument");

        for (int i = 0; i < value.size(); i++) {
            checkNotNew(value.get(i), argument + "[" + i + "]");
        }
    }

    /**
     * Ensures that the parameter being validated is not {@literal null} and is a new record.
     *
     * @param value    parameter's value
     * @param argument the name of parameter
     */
    public static void checkNotNullAndNew(@Nullable UpdatableRecord value, @NonNull String argument) {
        checkNotNull(value, argument);
        checkNew(value, argument);
    }

    /**
     * Ensures that the parameter being validated is not {@literal null} and is a collection of new records.
     *
     * @param value    parameter's value
     * @param argument the name of parameter
     */
    public static void checkNotNullAndNew(@NonNull List<? extends UpdatableRecord> value, @NonNull String argument) {
        checkNotNull(value, argument);

        for (int i = 0; i < value.size(); i++) {
            checkNotNullAndNew(value.get(i), argument + "[" + i + "]");
        }
    }

    /**
     * Ensures that the parameter being validated is not {@literal null} and is not a new record.
     *
     * @param value    parameter's value
     * @param argument the name of parameter
     */
    public static void checkNotNullAndNotNew(@Nullable UpdatableRecord value, @NonNull String argument) {
        checkNotNull(value, argument);
        checkNotNew(value, argument);
    }

    /**
     * Ensures that the parameter being validated is not {@literal null} and is a collection of not new records.
     *
     * @param value    parameter's value
     * @param argument the name of parameter
     */
    public static void checkNotNullAndNotNew(@NonNull List<? extends UpdatableRecord> value, @NonNull String argument) {
        checkNotNull(value, argument);

        for (int i = 0; i < value.size(); i++) {
            checkNotNullAndNotNew(value.get(i), argument + "[" + i + "]");
        }
    }

    private static boolean isNew(UpdatableRecord record) {
        return record.key().getValue(0) == null;
    }

}
