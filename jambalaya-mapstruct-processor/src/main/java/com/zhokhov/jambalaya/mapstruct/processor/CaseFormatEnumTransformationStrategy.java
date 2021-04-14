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
import com.zhokhov.jambalaya.mapstruct.JambalayaMappingConstants;
import org.mapstruct.MappingConstants;
import org.mapstruct.ap.spi.EnumTransformationStrategy;

/**
 * @author Alexey Zhokhov
 */
public class CaseFormatEnumTransformationStrategy implements EnumTransformationStrategy {

    @Override
    public String getStrategyName() {
        return JambalayaMappingConstants.CASE_FORMAT_TRANSFORMATION;
    }

    @Override
    public String transform(String value, String configuration) {
        if (isSpecialConstant(value)) {
            return value;
        }

        switch (configuration) {
            case JambalayaMappingConstants.LOWER_HYPHEN_TO_LOWER_UNDERSCORE:
                return CaseFormat.LOWER_HYPHEN.to(CaseFormat.LOWER_UNDERSCORE, value);

            case JambalayaMappingConstants.LOWER_HYPHEN_TO_LOWER_CAMEL:
                return CaseFormat.LOWER_HYPHEN.to(CaseFormat.LOWER_CAMEL, value);

            case JambalayaMappingConstants.LOWER_HYPHEN_TO_UPPER_CAMEL:
                return CaseFormat.LOWER_HYPHEN.to(CaseFormat.UPPER_CAMEL, value);

            case JambalayaMappingConstants.LOWER_HYPHEN_TO_UPPER_UNDERSCORE:
                return CaseFormat.LOWER_HYPHEN.to(CaseFormat.UPPER_UNDERSCORE, value);

            case JambalayaMappingConstants.LOWER_UNDERSCORE_TO_LOWER_HYPHEN:
                return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_HYPHEN, value);

            case JambalayaMappingConstants.LOWER_UNDERSCORE_TO_LOWER_CAMEL:
                return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, value);

            case JambalayaMappingConstants.LOWER_UNDERSCORE_TO_UPPER_CAMEL:
                return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, value);

            case JambalayaMappingConstants.LOWER_UNDERSCORE_TO_UPPER_UNDERSCORE:
                return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_UNDERSCORE, value);

            case JambalayaMappingConstants.LOWER_CAMEL_TO_LOWER_HYPHEN:
                return CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_HYPHEN, value);

            case JambalayaMappingConstants.LOWER_CAMEL_TO_LOWER_UNDERSCORE:
                return CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, value);

            case JambalayaMappingConstants.LOWER_CAMEL_TO_UPPER_CAMEL:
                return CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, value);

            case JambalayaMappingConstants.LOWER_CAMEL_TO_UPPER_UNDERSCORE:
                return CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, value);

            case JambalayaMappingConstants.UPPER_CAMEL_TO_LOWER_HYPHEN:
                return CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_HYPHEN, value);

            case JambalayaMappingConstants.UPPER_CAMEL_TO_LOWER_UNDERSCORE:
                return CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, value);

            case JambalayaMappingConstants.UPPER_CAMEL_TO_LOWER_CAMEL:
                return CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, value);

            case JambalayaMappingConstants.UPPER_CAMEL_TO_UPPER_UNDERSCORE:
                return CaseFormat.UPPER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, value);

            case JambalayaMappingConstants.UPPER_UNDERSCORE_TO_LOWER_HYPHEN:
                return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_HYPHEN, value);

            case JambalayaMappingConstants.UPPER_UNDERSCORE_TO_LOWER_UNDERSCORE:
                return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_UNDERSCORE, value);

            case JambalayaMappingConstants.UPPER_UNDERSCORE_TO_LOWER_CAMEL:
                return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, value);

            case JambalayaMappingConstants.UPPER_UNDERSCORE_TO_UPPER_CAMEL:
                return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, value);

            default:
                throw new RuntimeException("Unknown configuration: " + configuration);
        }
    }

    private boolean isSpecialConstant(String value) {
        if (value.equals(MappingConstants.NULL)) {
            return true;
        } else if (value.equals(MappingConstants.ANY_REMAINING)) {
            return true;
        } else if (value.equals(MappingConstants.ANY_UNMAPPED)) {
            return true;
        }
        return false;
    }

}
