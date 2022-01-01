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
package com.zhokhov.jambalaya.mapstruct.processor;

import com.google.common.base.CaseFormat;
import org.mapstruct.MappingConstants;
import org.mapstruct.ap.spi.DefaultEnumMappingStrategy;
import org.mapstruct.ap.spi.EnumMappingStrategy;

import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The implementation of {@link EnumMappingStrategy} for Protocol Buffers (protobuf) generated enums.
 *
 * @author Alexey Zhokhov
 */
public class JambalayaEnumMappingStrategy extends DefaultEnumMappingStrategy {

    private static final String PROTOCOL_MESSAGE_ENUM = "com.google.protobuf.ProtocolMessageEnum";

    private static final String UNRECOGNIZED = "UNRECOGNIZED";

    @Override
    public String getDefaultNullEnumConstant(TypeElement enumType) {
        if (isProtocolMessageEnum(enumType)) {
            List<String> enumValues = enumType.getEnclosedElements().stream()
                    .filter(it -> it.getKind() == ElementKind.ENUM_CONSTANT)
                    .map(it -> it.getSimpleName().toString())
                    .collect(Collectors.toList());

            if (enumValues.isEmpty()) {
                return null;
            }

            String firstValue = enumValues.get(0);

            if (isUnspecifiedOrUnknown(firstValue)) {
                return firstValue;
            }
        }

        return super.getDefaultNullEnumConstant(enumType);
    }

    @Override
    public String getEnumConstant(TypeElement enumType, String sourceEnumValue) {
        if (isProtocolMessageEnum(enumType)) {
            if (UNRECOGNIZED.equals(sourceEnumValue)) {
                return MappingConstants.NULL;
            }

            List<String> sourceEnumValues = enumType.getEnclosedElements().stream()
                    .filter(it -> it.getKind() == ElementKind.ENUM_CONSTANT)
                    .map(it -> it.getSimpleName().toString())
                    .collect(Collectors.toList());

            if (isUnspecifiedOrUnknown(sourceEnumValue) && sourceEnumValues.get(0).equals(sourceEnumValue)) {
                return MappingConstants.NULL;
            } else {
                String enumPrefix = getEnumPrefix(enumType);

                if (sourceEnumValue.startsWith(enumPrefix)) {
                    // return value without enum prefix
                    return sourceEnumValue.replace(enumPrefix + "_", "");
                }
            }
        }

        return super.getEnumConstant(enumType, sourceEnumValue);
    }

    private String getEnumPrefix(TypeElement enumType) {
        String enumName = enumType.getSimpleName().toString();
        return CaseFormat.UPPER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, enumName);
    }

    private boolean isUnspecifiedOrUnknown(String enumValue) {
        return enumValue.equals("UNSPECIFIED") ||
                enumValue.endsWith("_UNSPECIFIED") ||
                enumValue.startsWith("UNSPECIFIED_") ||
                enumValue.equals("UNKNOWN") ||
                enumValue.endsWith("_UNKNOWN") ||
                enumValue.startsWith("UNKNOWN_");
    }

    private boolean isProtocolMessageEnum(TypeElement enumType) {
        List<? extends TypeMirror> interfaces = enumType.getInterfaces();
        for (TypeMirror implementedInterface : interfaces) {
            if (PROTOCOL_MESSAGE_ENUM.equals(implementedInterface.toString())) {
                return true;
            }
        }
        return false;
    }

}
