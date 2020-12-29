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
