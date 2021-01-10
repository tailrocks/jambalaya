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
package com.zhokhov.jambalaya.graphql.apollo;

import com.apollographql.apollo.api.CustomTypeAdapter;
import com.apollographql.apollo.api.CustomTypeValue;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.YearMonth;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * List of date-time adapters 100% compatible with graphql-java-datetime.
 *
 * @author Alexey Zhokhov
 */
public final class DateTimeAdapters {

    private static final LocalDateTimeConverter converter = new LocalDateTimeConverter(false);

    private static final Pattern YEAR_MONTH_PATTERN = Pattern.compile("(\\d{1,4})-(\\d{1,2})");

    private static final class DateAdapter implements CustomTypeAdapter<Date> {
        @Override
        public Date decode(CustomTypeValue<?> value) {
            LocalDateTime localDateTime = DateTimeHelper.parseDate(value.value.toString());

            return DateTimeHelper.toDate(localDateTime);
        }

        @Override
        public CustomTypeValue<String> encode(Date value) {
            return new CustomTypeValue.GraphQLString(DateTimeHelper.toISOString(value));
        }
    }

    private static final class LocalDateAdapter implements CustomTypeAdapter<LocalDate> {
        @Override
        public LocalDate decode(CustomTypeValue<?> value) {
            LocalDateTime localDateTime = converter.parseDate(value.value.toString());

            return localDateTime.toLocalDate();
        }

        @Override
        public CustomTypeValue<String> encode(LocalDate value) {
            return new CustomTypeValue.GraphQLString(converter.formatDate(value, DateTimeFormatter.ISO_LOCAL_DATE));
        }
    }

    private static final class LocalDateTimeAdapter implements CustomTypeAdapter<LocalDateTime> {
        @Override
        public LocalDateTime decode(CustomTypeValue<?> value) {
            return converter.parseDate(value.value.toString());
        }

        @Override
        public CustomTypeValue<String> encode(LocalDateTime value) {
            return new CustomTypeValue.GraphQLString(converter.formatDate(value, DateTimeFormatter.ISO_INSTANT));
        }
    }

    private static final class LocalTimeAdapter implements CustomTypeAdapter<LocalTime> {
        @Override
        public LocalTime decode(CustomTypeValue<?> value) {
            return LocalTime.parse(value.value.toString(), DateTimeFormatter.ISO_LOCAL_TIME.withZone(ZoneOffset.UTC));
        }

        @Override
        public CustomTypeValue<String> encode(LocalTime value) {
            return new CustomTypeValue.GraphQLString(DateTimeHelper.toISOString(value));
        }
    }

    private static final class OffsetDateTimeAdapter implements CustomTypeAdapter<OffsetDateTime> {
        @Override
        public OffsetDateTime decode(CustomTypeValue<?> value) {
            return OffsetDateTime.parse(value.value.toString(), DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        }

        @Override
        public CustomTypeValue<String> encode(OffsetDateTime value) {
            return new CustomTypeValue.GraphQLString(DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(value));
        }
    }

    private static final class YearMonthAdapter implements CustomTypeAdapter<YearMonth> {
        @Override
        public YearMonth decode(CustomTypeValue<?> value) {
            Matcher m = YEAR_MONTH_PATTERN.matcher(value.value.toString());
            return YearMonth.of(Integer.parseInt(m.group(1)), Integer.parseInt(m.group(2)));
        }

        @Override
        public CustomTypeValue<String> encode(YearMonth value) {
            return new CustomTypeValue.GraphQLString(value.toString());
        }
    }

    private static final class DurationAdapter implements CustomTypeAdapter<Duration> {
        @Override
        public Duration decode(CustomTypeValue<?> value) {
            return Duration.parse(value.value.toString());
        }

        @Override
        public CustomTypeValue<String> encode(Duration value) {
            return new CustomTypeValue.GraphQLString(value.toString());
        }
    }

    public static final CustomTypeAdapter<Date> DATE = new DateAdapter();
    public static final CustomTypeAdapter<LocalDate> LOCAL_DATE = new LocalDateAdapter();
    public static final CustomTypeAdapter<LocalDateTime> LOCAL_DATE_TIME = new LocalDateTimeAdapter();
    public static final CustomTypeAdapter<LocalTime> LOCAL_TIME = new LocalTimeAdapter();
    public static final CustomTypeAdapter<OffsetDateTime> OFFSET_DATE_TIME = new OffsetDateTimeAdapter();
    public static final CustomTypeAdapter<YearMonth> YEAR_MONTH = new YearMonthAdapter();
    public static final CustomTypeAdapter<Duration> DURATION = new DurationAdapter();

    private DateTimeAdapters() {
    }

}
