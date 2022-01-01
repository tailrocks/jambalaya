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
package com.zhokhov.jambalaya.jsr310;

import edu.umd.cs.findbugs.annotations.Nullable;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

/**
 * The collection of static methods to convert from old {@link Date} class to the new Date and Time API (JSR-310) and
 * vice versa.
 *
 * @author Alexey Zhokhov
 */
public final class DateTimeConverters {

    private DateTimeConverters() {
    }

    /**
     * Convert from {@link LocalDate} to {@link Date}.
     *
     * @param source the source value to convert
     * @return {@literal null} or {@link Date}.
     */
    @Nullable
    public static Date toDate(@Nullable LocalDate source) {
        if (source == null) {
            return null;
        }
        return toDate(source.atStartOfDay());
    }

    /**
     * Convert from {@link LocalDateTime} to {@link Date}.
     *
     * @param source the source value to convert
     * @return {@literal null} or {@link Date}.
     */
    @Nullable
    public static Date toDate(@Nullable LocalDateTime source) {
        if (source == null) {
            return null;
        }
        return Date.from(source.atZone(ZoneOffset.UTC).toInstant());
    }

    /**
     * Convert from {@link LocalDate} to {@link Date}.
     *
     * @param source the source value to convert
     * @return {@literal null} or {@link Date}.
     */
    @Nullable
    public static LocalDate toLocalDate(@Nullable Date source) {
        if (source == null) {
            return null;
        }
        return source.toInstant()
                .atZone(ZoneOffset.UTC)
                .toLocalDate();
    }

    /**
     * Convert from {@link LocalDate} to {@link LocalDateTime}.
     *
     * @param source the source value to convert
     * @return {@literal null} or {@link LocalDateTime}.
     */
    @Nullable
    public static LocalDateTime toLocalDateTime(@Nullable Date source) {
        if (source == null) {
            return null;
        }
        return source.toInstant()
                .atZone(ZoneOffset.UTC)
                .toLocalDateTime();
    }

    /**
     * Convert from {@link LocalDate} to {@link Instant}.
     *
     * @param source the source value to convert
     * @return {@literal null} or {@link Instant}.
     */
    @Nullable
    public static Instant toInstant(@Nullable LocalDate source) {
        if (source == null) {
            return null;
        }
        return toInstant(source.atStartOfDay());
    }

    /**
     * Convert from {@link LocalDateTime} to {@link Instant}.
     *
     * @param source the source value to convert
     * @return {@literal null} or {@link Instant}.
     */
    @Nullable
    public static Instant toInstant(@Nullable LocalDateTime source) {
        if (source == null) {
            return null;
        }
        return source.toInstant(ZoneOffset.UTC);
    }

}
