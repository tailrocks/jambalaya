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
package com.zhokhov.jambalaya.kotlin.test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;

/**
 * EXPERIMENTAL
 */
public final class AssertEquals implements AssertLine {

    private final String methodName;
    private final Object value;
    private final Indentation indentation;
    private final int level;

    AssertEquals(String methodName, Object value, Indentation indentation, int level) {
        this.value = value;
        this.methodName = methodName;
        this.indentation = indentation;
        this.level = level;
    }

    String valueToString() {
        if (value instanceof Enum) {
            return value.getClass().getName() + "." + ((Enum) value).name();
        } else if (value instanceof LocalDateTime) {
            LocalDateTime localDateTime = (LocalDateTime) value;
            return "java.time.LocalDateTime.of(" +
                    localDateTime.getYear() + ", " +
                    localDateTime.getMonthValue() + ", " +
                    localDateTime.getDayOfMonth() + ", " +
                    localDateTime.getHour() + ", " +
                    localDateTime.getSecond() + ", " +
                    localDateTime.getNano() +
                    ")";
        } else if (value instanceof LocalDate) {
            LocalDate localDate = (LocalDate) value;
            return "java.time.LocalDate.of(" +
                    localDate.getYear() + ", " +
                    localDate.getMonthValue() + ", " +
                    localDate.getDayOfMonth() +
                    ")";
        } else if (value instanceof YearMonth) {
            YearMonth localDate = (YearMonth) value;
            return "java.time.YearMonth.of(" +
                    localDate.getYear() + ", " +
                    localDate.getMonthValue() +
                    ")";
        } else if (value instanceof BigDecimal) {
            BigDecimal bigDecimal = (BigDecimal) value;
            return "java.math.BigDecimal(\"" +
                    bigDecimal.toString() +
                    "\")";
        } else if (value instanceof String) {
            return "\"" + value.toString().replace("\"", "\\\"").replace("$", "\\$") + "\"";
        }
        return value.toString();
    }

    @Override
    public String toString() {
        return indentation.print(level) + "assertEquals(" + valueToString() + ", " + MethodNameUtils.printName(methodName) + ")";
    }

}
