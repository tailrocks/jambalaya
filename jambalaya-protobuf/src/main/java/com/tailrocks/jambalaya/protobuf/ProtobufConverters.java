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
package com.zhokhov.jambalaya.protobuf;

import com.google.protobuf.BoolValue;
import com.google.protobuf.FloatValue;
import com.google.protobuf.Int32Value;
import com.google.protobuf.Int64Value;
import com.google.protobuf.LazyStringArrayList;
import com.google.protobuf.ProtocolStringList;
import com.google.protobuf.StringValue;
import com.google.protobuf.Timestamp;
import com.google.protobuf.UInt32Value;
import com.google.protobuf.UInt64Value;
import com.google.type.Money;
import edu.umd.cs.findbugs.annotations.NonNull;
import edu.umd.cs.findbugs.annotations.Nullable;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.tailrocks.jambalaya.checks.Preconditions.checkNotBlank;
import static com.tailrocks.jambalaya.checks.Preconditions.checkNotNull;

/**
 * The collection of static methods to convert from gRPC wrappers for primitive (non-message) types to non-primitive
 * Java data types and vice versa.
 *
 * @author Alexey Zhokhov
 */
public final class ProtobufConverters {

    private ProtobufConverters() {
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                                                                                                //
    // to Java types                                                                                                  //
    //                                                                                                                //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Convert from {@link StringValue} to {@link String}.
     *
     * @param source the source value to convert
     * @return {@literal null} or String's value
     */
    @Nullable
    public static String toString(@Nullable StringValue source) {
        if (source == null) {
            return null;
        }
        return source.getValue();
    }

    /**
     * Convert from {@link List} of {@link StringValue} to {@link List} of {@link String}.
     *
     * @param source the source value to convert
     * @return {@literal null} or {@link List} of {@link String}.
     */
    @Nullable
    public static List<String> toListString(@Nullable List<StringValue> source) {
        if (source == null) {
            return null;
        }
        return source.stream()
                .map(StringValue::getValue)
                .collect(Collectors.toList());
    }

    /**
     * Convert from {@link ProtocolStringList} to {@link List} of {@link String}.
     *
     * @param source the source value to convert
     * @return {@literal null} or {@link List} of {@link String}.
     */
    @Nullable
    public static List<String> toListString(@Nullable ProtocolStringList source) {
        if (source == null) {
            return null;
        }
        return new ArrayList<>(source);
    }

    /**
     * Convert from {@link Int32Value} to {@link Integer}.
     *
     * @param source the source value to convert
     * @return {@literal null} or {@link Integer}.
     */
    @Nullable
    public static Integer toInteger(@Nullable Int32Value source) {
        if (source == null) {
            return null;
        }
        return source.getValue();
    }

    /**
     * Convert from {@link UInt32Value} to {@link Integer}.
     *
     * @param source the source value to convert
     * @return {@literal null} or {@link Integer}.
     */
    @Nullable
    public static Integer toInteger(@Nullable UInt32Value source) {
        if (source == null) {
            return null;
        }
        return source.getValue();
    }

    /**
     * Convert from {@link Int64Value} to {@link Long}.
     *
     * @param source the source value to convert
     * @return {@literal null} or {@link Long}.
     */
    @Nullable
    public static Long toLong(@Nullable Int64Value source) {
        if (source == null) {
            return null;
        }
        return source.getValue();
    }

    /**
     * Convert from {@link UInt64Value} to {@link Long}.
     *
     * @param source the source value to convert
     * @return {@literal null} or {@link Long}.
     */
    @Nullable
    public static Long toLong(@Nullable UInt64Value source) {
        if (source == null) {
            return null;
        }
        return source.getValue();
    }

    /**
     * Convert from {@link FloatValue} to {@link Float}.
     *
     * @param source the source value to convert
     * @return {@literal null} or {@link Float}.
     */
    @Nullable
    public static Float toFloat(@Nullable FloatValue source) {
        if (source == null) {
            return null;
        }
        return source.getValue();
    }

    /**
     * Convert from {@link BoolValue} to {@link Boolean}.
     *
     * @param source the source value to convert
     * @return {@literal null} or {@link Boolean}.
     */
    @Nullable
    public static Boolean toBoolean(@Nullable BoolValue source) {
        if (source == null) {
            return null;
        }
        return source.getValue();
    }

    /**
     * Convert from {@link Timestamp} to {@link LocalDate}.
     *
     * @param source the source value to convert
     * @return {@literal null} or {@link LocalDate}.
     */
    @Nullable
    public static LocalDate toLocalDate(@Nullable Timestamp source) {
        if (source == null) {
            return null;
        }
        return toZonedDateTime(source).toLocalDate();
    }

    /**
     * Convert from {@link Timestamp} to {@link LocalDateTime}.
     *
     * @param source the source value to convert
     * @return {@literal null} or {@link LocalDateTime}.
     */
    @Nullable
    public static LocalDateTime toLocalDateTime(@Nullable Timestamp source) {
        if (source == null) {
            return null;
        }
        return toZonedDateTime(source).toLocalDateTime();
    }

    /**
     * Convert from {@link Timestamp} to {@link ZonedDateTime}.
     *
     * @param source the source value to convert
     * @return {@literal null} or {@link ZonedDateTime}.
     */
    @Nullable
    public static ZonedDateTime toZonedDateTime(@Nullable Timestamp source) {
        if (source == null) {
            return null;
        }

        return Instant
                .ofEpochSecond(source.getSeconds(), source.getNanos())
                .atZone(ZoneOffset.UTC);
    }

    /**
     * Convert from {@link com.google.type.Date} to {@link LocalDate}.
     *
     * @param source the source value to convert
     * @return {@literal null} or {@link LocalDate}.
     */
    @Nullable
    public static LocalDate toLocalDate(@Nullable com.google.type.Date source) {
        if (source == null) {
            return null;
        }
        if (source.equals(com.google.type.Date.getDefaultInstance())) {
            return null;
        }
        return LocalDate.of(source.getYear(), source.getMonth(), source.getDay());
    }

    /**
     * Convert from {@link com.google.type.DateTime} to {@link LocalDateTime}.
     *
     * @param source the source value to convert
     * @return {@literal null} or {@link LocalDate}.
     */
    @Nullable
    public static LocalDateTime toLocalDateTime(@Nullable com.google.type.DateTime source) {
        if (source == null) {
            return null;
        }
        if (source.equals(com.google.type.DateTime.getDefaultInstance())) {
            return null;
        }
        return LocalDateTime.of(
                source.getYear(), source.getMonth(), source.getDay(),
                source.getHours(), source.getMinutes(), source.getSeconds(),
                source.getNanos()
        );
    }

    /**
     * Convert from {@link Money} to {@link BigDecimal}.
     *
     * @param source the source value to convert
     * @return {@literal null} or {@link BigDecimal}.
     */
    @Nullable
    public static BigDecimal toBigDecimal(@Nullable Money source) {
        if (source == null) {
            return null;
        }
        if (source.getNanos() > 0) {
            return new BigDecimal(source.getUnits() + "." + source.getNanos());
        } else {
            return new BigDecimal(source.getUnits());
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                                                                                                //
    // to gRPC types                                                                                                  //
    //                                                                                                                //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Convert from {@link String} to {@link StringValue}.
     *
     * @param source the source value to convert
     * @return {@literal null} or {@link StringValue}.
     */
    @Nullable
    public static StringValue toStringValue(@Nullable String source) {
        if (source == null) {
            return null;
        }
        return StringValue.of(source);
    }

    /**
     * Convert from {@link List} of {@link String} to {@link List} of {@link StringValue}.
     *
     * @param source the source value to convert
     * @return {@literal null} or {@link List} of {@link StringValue}.
     */
    @Nullable
    public static List<StringValue> toListStringValue(@Nullable List<String> source) {
        if (source == null) {
            return null;
        }
        return source.stream()
                .map(StringValue::of)
                .collect(Collectors.toList());
    }

    /**
     * Convert from {@link List} of {@link String} to {@link ProtocolStringList}.
     *
     * @param source the source value to convert
     * @return {@literal null} or {@link ProtocolStringList}.
     */
    @Nullable
    public static ProtocolStringList toProtocolStringList(@Nullable List<String> source) {
        if (source == null) {
            return null;
        }
        return new LazyStringArrayList(source);
    }

    /**
     * Convert from {@link Integer} to {@link Int32Value}.
     *
     * @param source the source value to convert
     * @return {@literal null} or {@link Int32Value}.
     */
    @Nullable
    public static Int32Value toInt32Value(@Nullable Integer source) {
        if (source == null) {
            return null;
        }
        return Int32Value.of(source);
    }

    /**
     * Convert from {@link Integer} to {@link UInt32Value}.
     *
     * @param source the source value to convert
     * @return {@literal null} or {@link UInt32Value}.
     */
    @Nullable
    public static UInt32Value toUInt32Value(@Nullable Integer source) {
        if (source == null) {
            return null;
        }
        return UInt32Value.of(source);
    }

    /**
     * Convert from {@link Long} to {@link Int64Value}.
     *
     * @param source the source value to convert
     * @return {@literal null} or {@link Int64Value}.
     */
    @Nullable
    public static Int64Value toInt64Value(@Nullable Long source) {
        if (source == null) {
            return null;
        }
        return Int64Value.of(source);
    }

    /**
     * Convert from {@link Long} to {@link UInt64Value}.
     *
     * @param source the source value to convert
     * @return {@literal null} or {@link UInt64Value}.
     */
    @Nullable
    public static UInt64Value toUInt64Value(@Nullable Long source) {
        if (source == null) {
            return null;
        }
        return UInt64Value.of(source);
    }

    /**
     * Convert from {@link Float} to {@link FloatValue}.
     *
     * @param source the source value to convert
     * @return {@literal null} or {@link FloatValue}.
     */
    @Nullable
    public static FloatValue toFloatValue(@Nullable Float source) {
        if (source == null) {
            return null;
        }
        return FloatValue.of(source);
    }

    /**
     * Convert from {@link Boolean} to {@link BoolValue}.
     *
     * @param source the source value to convert
     * @return {@literal null} or {@link BoolValue}.
     */
    @Nullable
    public static BoolValue toBoolValue(@Nullable Boolean source) {
        if (source == null) {
            return null;
        }
        return BoolValue.of(source);
    }

    /**
     * Convert from {@link LocalDate} to {@link Timestamp}.
     *
     * @param source the source value to convert
     * @return {@literal null} or {@link Timestamp}.
     */
    @Nullable
    public static Timestamp toTimestamp(@Nullable LocalDate source) {
        if (source == null) {
            return null;
        }
        return toTimestamp(source.atStartOfDay(ZoneOffset.UTC).toLocalDateTime());
    }

    /**
     * Convert from {@link LocalDateTime} to {@link Timestamp}.
     *
     * @param source the source value to convert
     * @return {@literal null} or {@link Timestamp}.
     */
    @Nullable
    public static Timestamp toTimestamp(@Nullable LocalDateTime source) {
        if (source == null) {
            return null;
        }
        return Timestamp.newBuilder()
                .setSeconds(source.toEpochSecond(ZoneOffset.UTC))
                .setNanos(source.getNano())
                .build();
    }

    /**
     * Convert from {@link LocalDate} to {@link com.google.type.Date}.
     *
     * @param source the source value to convert
     * @return {@literal null} or {@link com.google.type.Date}.
     */
    @Nullable
    public static com.google.type.Date toDate(@Nullable LocalDate source) {
        if (source == null) {
            return null;
        }
        return com.google.type.Date.newBuilder()
                .setYear(source.getYear())
                .setMonth(source.getMonth().getValue())
                .setDay(source.getDayOfMonth())
                .build();
    }

    /**
     * Convert from {@link LocalDateTime} to {@link com.google.type.DateTime}.
     *
     * @param source the source value to convert
     * @return {@literal null} or {@link com.google.type.DateTime}.
     */
    @Nullable
    public static com.google.type.DateTime toDateTime(@Nullable LocalDateTime source) {
        if (source == null) {
            return null;
        }
        return com.google.type.DateTime.newBuilder()
                .setYear(source.getYear())
                .setMonth(source.getMonth().getValue())
                .setDay(source.getDayOfMonth())
                .setHours(source.getHour())
                .setMinutes(source.getMinute())
                .setSeconds(source.getSecond())
                .setNanos(source.getNano())
                .build();
    }

    /**
     * Convert from {@link com.google.type.Date} to {@link LocalDate}.
     *
     * @param source the source value to convert
     * @return {@literal null} or {@link LocalDate}.
     */
    @Nullable
    public static Money toMoney(@Nullable BigDecimal source) {
        if (source == null) {
            return null;
        }
        return toMoney(source, "USD");
    }

    /**
     * Convert from {@link com.google.type.Date} to {@link LocalDate}.
     *
     * @param source the source value to convert
     * @return {@literal null} or {@link Money}.
     */
    public static Money toMoney(@NonNull BigDecimal source, @NonNull String currencyCode) {
        checkNotNull(source, "source");
        checkNotBlank(currencyCode, "currencyCode");

        return Money.newBuilder()
                .setCurrencyCode(currencyCode)
                .setUnits(source.longValue())
                .setNanos(source.remainder(BigDecimal.ONE).movePointRight(source.scale()).intValue())
                .build();
    }

}
