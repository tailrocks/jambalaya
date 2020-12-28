package com.zhokhov.jambalaya.protobuf;

import com.google.protobuf.*;
import edu.umd.cs.findbugs.annotations.Nullable;

import java.time.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Collection of static helper method to convert from gRPC wrappers for primitive (non-message) types to non-primitive
 * Java data types.
 *
 * @author Alexey Zhokhov
 */
public final class ProtobufConverters {

    private ProtobufConverters() {
    }

    // to Java types

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
     * Convert from {@link List<StringValue>} to {@link List<String>}.
     *
     * @param source the source value to convert
     * @return {@literal null} or {@link List<String>}.
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
     * Convert from {@link ProtocolStringList} to {@link List<String>}.
     *
     * @param source the source value to convert
     * @return {@literal null} or {@link ArrayList<String>}.
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
    private static ZonedDateTime toZonedDateTime(@Nullable Timestamp source) {
        if (source == null) {
            return null;
        }

        return Instant.ofEpochSecond(source.getSeconds(), source.getNanos()).atZone(ZoneOffset.UTC);
    }

    // to gRPC types

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
     * Convert from {@link List<String>} to {@link List<StringValue>}.
     *
     * @param source the source value to convert
     * @return {@literal null} or {@link List<StringValue>}.
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
     * Convert from {@link List<String>} to {@link ProtocolStringList}.
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
        return Timestamp.newBuilder()
                .setSeconds(source.toEpochSecond(LocalTime.MIDNIGHT, ZoneOffset.UTC))
                .build();
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

}
