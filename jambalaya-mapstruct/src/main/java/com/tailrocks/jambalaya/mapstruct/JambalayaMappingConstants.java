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
package com.tailrocks.jambalaya.mapstruct;

import org.mapstruct.EnumMapping;

/**
 * Contains all constants defined in the mapping process.
 *
 * @author Alexey Zhokhov
 */
public final class JambalayaMappingConstants {

    /**
     * In an {@link EnumMapping} this represent the enum transformation strategy that converts the source enum to
     * different case format.
     */
    public static final String CASE_FORMAT_TRANSFORMATION = "caseFormat";
    /**
     * In an {@link EnumMapping} this represent the enum configuration that converts the source enum from lower
     * hyphen to lower underscore format.
     */
    public static final String LOWER_HYPHEN_TO_LOWER_UNDERSCORE = "lowerHyphenToLowerUnderscore";

    // LOWER_HYPHEN
    /**
     * In an {@link EnumMapping} this represent the enum configuration that converts the source enum from lower
     * hyphen to lower camel format.
     */
    public static final String LOWER_HYPHEN_TO_LOWER_CAMEL = "lowerHyphenToLowerCamel";
    /**
     * In an {@link EnumMapping} this represent the enum configuration that converts the source enum from lower
     * hyphen to upper camel format.
     */
    public static final String LOWER_HYPHEN_TO_UPPER_CAMEL = "lowerHyphenToUpperCamel";
    /**
     * In an {@link EnumMapping} this represent the enum configuration that converts the source enum from lower
     * hyphen to upper underscore format.
     */
    public static final String LOWER_HYPHEN_TO_UPPER_UNDERSCORE = "lowerHyphenToUpperUnderscore";
    /**
     * In an {@link EnumMapping} this represent the enum configuration that converts the source enum from lower
     * underscore to lower hyphen format.
     */
    public static final String LOWER_UNDERSCORE_TO_LOWER_HYPHEN = "lowerUnderscoreToLowerHyphen";

    // LOWER_UNDERSCORE
    /**
     * In an {@link EnumMapping} this represent the enum configuration that converts the source enum from lower
     * underscore to lower camel format.
     */
    public static final String LOWER_UNDERSCORE_TO_LOWER_CAMEL = "lowerUnderscoreToLowerCamel";
    /**
     * In an {@link EnumMapping} this represent the enum configuration that converts the source enum from lower
     * underscore to upper camel format.
     */
    public static final String LOWER_UNDERSCORE_TO_UPPER_CAMEL = "lowerUnderscoreToUpperCamel";
    /**
     * In an {@link EnumMapping} this represent the enum configuration that converts the source enum from lower
     * underscore to upper underscore format.
     */
    public static final String LOWER_UNDERSCORE_TO_UPPER_UNDERSCORE = "lowerUnderscoreToUpperUnderscore";
    /**
     * In an {@link EnumMapping} this represent the enum configuration that converts the source enum from lower
     * camel to lower hyphen format.
     */
    public static final String LOWER_CAMEL_TO_LOWER_HYPHEN = "lowerCamelToLowerHyphen";

    // LOWER_CAMEL
    /**
     * In an {@link EnumMapping} this represent the enum configuration that converts the source enum from lower
     * camel to lower underscore format.
     */
    public static final String LOWER_CAMEL_TO_LOWER_UNDERSCORE = "lowerCamelToLowerUnderscore";
    /**
     * In an {@link EnumMapping} this represent the enum configuration that converts the source enum from lower
     * camel to upper camel format.
     */
    public static final String LOWER_CAMEL_TO_UPPER_CAMEL = "lowerCamelToUpperCamel";
    /**
     * In an {@link EnumMapping} this represent the enum configuration that converts the source enum from lower
     * camel to upper underscore format.
     */
    public static final String LOWER_CAMEL_TO_UPPER_UNDERSCORE = "lowerCamelToUpperUnderscore";
    /**
     * In an {@link EnumMapping} this represent the enum configuration that converts the source enum from upper
     * camel to lower hyphen format.
     */
    public static final String UPPER_CAMEL_TO_LOWER_HYPHEN = "upperCamelToLowerHyphen";

    // UPPER_CAMEL
    /**
     * In an {@link EnumMapping} this represent the enum configuration that converts the source enum from upper
     * camel to lower underscore format.
     */
    public static final String UPPER_CAMEL_TO_LOWER_UNDERSCORE = "upperCamelToLowerUnderscore";
    /**
     * In an {@link EnumMapping} this represent the enum configuration that converts the source enum from upper
     * camel to lower camel format.
     */
    public static final String UPPER_CAMEL_TO_LOWER_CAMEL = "upperCamelToLowerCamel";
    /**
     * In an {@link EnumMapping} this represent the enum configuration that converts the source enum from upper
     * camel to upper underscore format.
     */
    public static final String UPPER_CAMEL_TO_UPPER_UNDERSCORE = "upperCamelToUpperUnderscore";
    /**
     * In an {@link EnumMapping} this represent the enum configuration that converts the source enum from upper
     * underscore to lower hyphen format.
     */
    public static final String UPPER_UNDERSCORE_TO_LOWER_HYPHEN = "upperUnderscoreToLowerHyphen";

    // UPPER_UNDERSCORE
    /**
     * In an {@link EnumMapping} this represent the enum configuration that converts the source enum from upper
     * underscore to lower underscore format.
     */
    public static final String UPPER_UNDERSCORE_TO_LOWER_UNDERSCORE = "upperUnderscoreToLowerUnderscore";
    /**
     * In an {@link EnumMapping} this represent the enum configuration that converts the source enum from upper
     * underscore to lower camel format.
     */
    public static final String UPPER_UNDERSCORE_TO_LOWER_CAMEL = "upperUnderscoreToLowerCamel";
    /**
     * In an {@link EnumMapping} this represent the enum configuration that converts the source enum from upper
     * underscore to upper camel format.
     */
    public static final String UPPER_UNDERSCORE_TO_UPPER_CAMEL = "upperUnderscoreToUpperCamel";

    private JambalayaMappingConstants() {
    }

}
