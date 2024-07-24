/*
 * Copyright 2021 original authors
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
package com.tailrocks.jambalaya.micronaut.mapstruct.protobuf;

import com.google.protobuf.BoolValue;
import com.google.protobuf.FloatValue;
import com.google.protobuf.Int32Value;
import com.google.protobuf.Int64Value;
import com.google.protobuf.ProtocolStringList;
import com.google.protobuf.StringValue;
import com.google.protobuf.Timestamp;
import com.google.protobuf.UInt32Value;
import com.google.protobuf.UInt64Value;
import com.tailrocks.jambalaya.protobuf.ProtobufConverters;
import org.jspecify.annotations.Nullable;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

import static org.mapstruct.InjectionStrategy.CONSTRUCTOR;

/**
 * The MapStruct mapper for converting protobuf types to Java types and vice-versa.
 *
 * @author Alexey Zhokhov
 */
@Mapper(
        componentModel = MappingConstants.ComponentModel.JAKARTA,
        injectionStrategy = CONSTRUCTOR
)
public class ProtobufConvertersMapper {

    /**
     * @param source the source value to convert
     * @return {@literal null} or String's value
     * @see ProtobufConverters#toString(StringValue)
     */
    @Nullable
    public String toString(@Nullable StringValue source) {
        return ProtobufConverters.toString(source);
    }

    /**
     * @param source the source value to convert
     * @return {@literal null} or {@link List} of {@link String}.
     * @see ProtobufConverters#toListString(List)
     */
    @Nullable
    public List<String> toListString(@Nullable List<StringValue> source) {
        return ProtobufConverters.toListString(source);
    }

    /**
     * @param source the source value to convert
     * @return {@literal null} or {@link List} of {@link String}.
     * @see ProtobufConverters#toListString(ProtocolStringList)
     */
    @Nullable
    public List<String> toListString(@Nullable ProtocolStringList source) {
        return ProtobufConverters.toListString(source);
    }

    /**
     * @param source the source value to convert
     * @return {@literal null} or {@link Integer}.
     * @see ProtobufConverters#toInteger(Int32Value)
     */
    @Nullable
    public Integer toInteger(@Nullable Int32Value source) {
        return ProtobufConverters.toInteger(source);
    }

    /**
     * @param source the source value to convert
     * @return {@literal null} or {@link Integer}.
     * @see ProtobufConverters#toInteger(UInt32Value)
     */
    @Nullable
    public Integer toInteger(@Nullable UInt32Value source) {
        return ProtobufConverters.toInteger(source);
    }

    /**
     * @param source the source value to convert
     * @return {@literal null} or {@link Long}.
     * @see ProtobufConverters#toLong(Int64Value)
     */
    @Nullable
    public Long toLong(@Nullable Int64Value source) {
        return ProtobufConverters.toLong(source);
    }

    /**
     * @param source the source value to convert
     * @return {@literal null} or {@link Long}.
     * @see ProtobufConverters#toLong(UInt64Value)
     */
    @Nullable
    public Long toLong(@Nullable UInt64Value source) {
        return ProtobufConverters.toLong(source);
    }

    /**
     * @param source the source value to convert
     * @return {@literal null} or {@link Float}.
     * @see ProtobufConverters#toFloat(FloatValue)
     */
    @Nullable
    public Float toFloat(@Nullable FloatValue source) {
        return ProtobufConverters.toFloat(source);
    }

    /**
     * @param source the source value to convert
     * @return {@literal null} or {@link Boolean}.
     * @see ProtobufConverters#toBoolean(BoolValue)
     */
    @Nullable
    public Boolean toBoolean(@Nullable BoolValue source) {
        return ProtobufConverters.toBoolean(source);
    }

    /**
     * @param source the source value to convert
     * @return {@literal null} or {@link LocalDate}.
     * @see ProtobufConverters#toLocalDate(Timestamp)
     */
    @Nullable
    public LocalDate toLocalDate(@Nullable Timestamp source) {
        return ProtobufConverters.toLocalDate(source);
    }

    /**
     * @param source the source value to convert
     * @return {@literal null} or {@link LocalDateTime}.
     * @see ProtobufConverters#toLocalDateTime(Timestamp)
     */
    @Nullable
    public LocalDateTime toLocalDateTime(@Nullable Timestamp source) {
        return ProtobufConverters.toLocalDateTime(source);
    }

    /**
     * @param source the source value to convert
     * @return {@literal null} or {@link ZonedDateTime}.
     * @see ProtobufConverters#toZonedDateTime(Timestamp)
     */
    @Nullable
    public ZonedDateTime toZonedDateTime(@Nullable Timestamp source) {
        return ProtobufConverters.toZonedDateTime(source);
    }

    // to gRPC types

    /**
     * @param source the source value to convert
     * @return {@literal null} or {@link StringValue}.
     * @see ProtobufConverters#toStringValue(String)
     */
    @Nullable
    public StringValue toStringValue(@Nullable String source) {
        return ProtobufConverters.toStringValue(source);
    }

    /**
     * @param source the source value to convert
     * @return {@literal null} or {@link List} of {@link StringValue}.
     * @see ProtobufConverters#toListStringValue(List)
     */
    @Nullable
    public List<StringValue> toListStringValue(@Nullable List<String> source) {
        return ProtobufConverters.toListStringValue(source);
    }

    /**
     * @param source the source value to convert
     * @return {@literal null} or {@link ProtocolStringList}.
     * @see ProtobufConverters#toProtocolStringList(List)
     */
    @Nullable
    public ProtocolStringList toProtocolStringList(@Nullable List<String> source) {
        return ProtobufConverters.toProtocolStringList(source);
    }

    /**
     * @param source the source value to convert
     * @return {@literal null} or {@link Int32Value}.
     * @see ProtobufConverters#toInt32Value(Integer)
     */
    @Nullable
    public Int32Value toInt32Value(@Nullable Integer source) {
        return ProtobufConverters.toInt32Value(source);
    }

    /**
     * @param source the source value to convert
     * @return {@literal null} or {@link UInt32Value}.
     * @see ProtobufConverters#toUInt32Value(Integer)
     */
    @Nullable
    public UInt32Value toUInt32Value(@Nullable Integer source) {
        return ProtobufConverters.toUInt32Value(source);
    }

    /**
     * @param source the source value to convert
     * @return {@literal null} or {@link Int64Value}.
     * @see ProtobufConverters#toInt64Value(Long)
     */
    @Nullable
    public Int64Value toInt64Value(@Nullable Long source) {
        return ProtobufConverters.toInt64Value(source);
    }

    /**
     * @param source the source value to convert
     * @return {@literal null} or {@link UInt64Value}.
     * @see ProtobufConverters#toUInt64Value(Long)
     */
    @Nullable
    public UInt64Value toUInt64Value(@Nullable Long source) {
        return ProtobufConverters.toUInt64Value(source);
    }

    /**
     * @param source the source value to convert
     * @return {@literal null} or {@link FloatValue}.
     * @see ProtobufConverters#toFloatValue(Float)
     */
    @Nullable
    public FloatValue toFloatValue(@Nullable Float source) {
        return ProtobufConverters.toFloatValue(source);
    }

    /**
     * @param source the source value to convert
     * @return {@literal null} or {@link BoolValue}.
     * @see ProtobufConverters#toBoolValue(Boolean)
     */
    @Nullable
    public BoolValue toBoolValue(@Nullable Boolean source) {
        return ProtobufConverters.toBoolValue(source);
    }

    /**
     * @param source the source value to convert
     * @return {@literal null} or {@link Timestamp}.
     * @see ProtobufConverters#toTimestamp(LocalDate)
     */
    @Nullable
    public Timestamp toTimestamp(@Nullable LocalDate source) {
        return ProtobufConverters.toTimestamp(source);
    }

    /**
     * @param source the source value to convert
     * @return {@literal null} or {@link Timestamp}.
     * @see ProtobufConverters#toTimestamp(LocalDateTime)
     */
    @Nullable
    public Timestamp toTimestamp(@Nullable LocalDateTime source) {
        return ProtobufConverters.toTimestamp(source);
    }

}
