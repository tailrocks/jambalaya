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

import com.google.common.base.Strings;
import com.google.protobuf.StringValue;
import edu.umd.cs.findbugs.annotations.Nullable;
import org.mapstruct.Mapper;

import java.util.UUID;

import static org.mapstruct.InjectionStrategy.CONSTRUCTOR;

/**
 * The MapStruct mapper for converting common Java types to protobuf types and vice-versa.
 *
 * @author Alexey Zhokhov
 */
@Mapper(
        // TODO use MappingConstants.ComponentModel.JSR330
        componentModel = "jsr330",
        injectionStrategy = CONSTRUCTOR
)
public class CommonConvertersMapper {

    private static boolean isBlank(String value) {
        return Strings.nullToEmpty(value).trim().isEmpty();
    }

    /**
     * Convert from {@link UUID} to {@link String}.
     *
     * @param source the source value to convert
     * @return {@literal null} or {@link UUID}
     */
    @Nullable
    public String toString(@Nullable UUID source) {
        if (source == null) {
            return null;
        }
        return source.toString();
    }

    /**
     * Convert from {@link UUID} to {@link StringValue}.
     *
     * @param source the source value to convert
     * @return {@literal null} or {@link StringValue}
     */
    @Nullable
    public StringValue toStringValue(@Nullable UUID source) {
        if (source == null) {
            return null;
        }
        return StringValue.of(source.toString());
    }

    /**
     * Convert from {@link String} to {@link UUID}.
     *
     * @param source the source value to convert
     * @return {@literal null} or {@link UUID}
     */
    @Nullable
    public UUID toUUID(@Nullable String source) {
        if (source == null) {
            return null;
        }
        if (isBlank(source)) {
            return null;
        }
        return UUID.fromString(source);
    }

    /**
     * Convert from {@link StringValue} to {@link UUID}.
     *
     * @param source the source value to convert
     * @return {@literal null} or {@link UUID}
     */
    @Nullable
    public UUID toUUID(@Nullable StringValue source) {
        if (source == null) {
            return null;
        }
        if (isBlank(source.getValue())) {
            return null;
        }
        return UUID.fromString(source.getValue());
    }

}
