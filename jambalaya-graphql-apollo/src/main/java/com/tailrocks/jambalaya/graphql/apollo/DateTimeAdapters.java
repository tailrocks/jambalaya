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
package com.tailrocks.jambalaya.graphql.apollo;

import com.apollographql.apollo.api.Adapter;
import com.apollographql.apollo.api.CustomScalarAdapters;
import com.apollographql.apollo.api.CustomScalarType;
import com.apollographql.apollo.api.json.JsonReader;
import com.apollographql.apollo.api.json.JsonWriter;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
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

    @NotNull
    public static final Adapter<Date> DATE = new DateAdapter();

    @NotNull
    public static final Adapter<LocalDate> LOCAL_DATE = new LocalDateAdapter();

    @NotNull
    public static final Adapter<LocalDateTime> LOCAL_DATE_TIME = new LocalDateTimeAdapter();

    @NotNull
    public static final Adapter<LocalTime> LOCAL_TIME = new LocalTimeAdapter();

    @NotNull
    public static final Adapter<OffsetDateTime> OFFSET_DATE_TIME = new OffsetDateTimeAdapter();

    @NotNull
    public static final Adapter<YearMonth> YEAR_MONTH = new YearMonthAdapter();

    @NotNull
    public static final Adapter<Duration> DURATION = new DurationAdapter();

    @NotNull
    public static final CustomScalarAdapters CUSTOM_TYPE_ADAPTER_MAP;

    static {
        CUSTOM_TYPE_ADAPTER_MAP = new CustomScalarAdapters.Builder()
                .add(
                        new CustomScalarType(Date.class.getSimpleName(), Date.class.getName()),
                        DateTimeAdapters.DATE
                )
                .add(
                        new CustomScalarType(LocalDate.class.getSimpleName(), LocalDate.class.getName()),
                        DateTimeAdapters.LOCAL_DATE
                )
                .add(
                        new CustomScalarType(LocalDateTime.class.getSimpleName(), LocalDateTime.class.getName()),
                        DateTimeAdapters.LOCAL_DATE_TIME
                )
                .add(
                        new CustomScalarType(LocalTime.class.getSimpleName(), LocalTime.class.getName()),
                        DateTimeAdapters.LOCAL_TIME
                )
                .add(
                        new CustomScalarType(OffsetDateTime.class.getSimpleName(), OffsetDateTime.class.getName()),
                        DateTimeAdapters.OFFSET_DATE_TIME
                )
                .add(
                        new CustomScalarType(YearMonth.class.getSimpleName(), YearMonth.class.getName()),
                        DateTimeAdapters.YEAR_MONTH
                )
                .add(
                        new CustomScalarType(Duration.class.getSimpleName(), Duration.class.getName()),
                        DateTimeAdapters.DURATION
                )
                .build();
    }

    private static final LocalDateTimeConverter converter = new LocalDateTimeConverter(false);
    private static final Pattern YEAR_MONTH_PATTERN = Pattern.compile("(\\d{1,4})-(\\d{1,2})");

    private DateTimeAdapters() {
    }

    private static final class DateAdapter implements Adapter<Date> {

        @Override
        public Date fromJson(
                @NotNull JsonReader jsonReader,
                @NotNull CustomScalarAdapters customScalarAdapters
        ) throws IOException {
            LocalDateTime localDateTime = DateTimeHelper.parseDate(jsonReader.nextString());

            return DateTimeHelper.toDate(localDateTime);
        }

        @Override
        public void toJson(
                @NotNull JsonWriter jsonWriter,
                @NotNull CustomScalarAdapters customScalarAdapters,
                Date date
        ) throws IOException {
            jsonWriter.value(DateTimeHelper.toISOString(date));
        }

    }

    private static final class LocalDateAdapter implements Adapter<LocalDate> {

        @Override
        public LocalDate fromJson(
                @NotNull JsonReader jsonReader,
                @NotNull CustomScalarAdapters customScalarAdapters
        ) throws IOException {
            LocalDateTime localDateTime = converter.parseDate(jsonReader.nextString());

            if (localDateTime == null) {
                return null;
            }

            return localDateTime.toLocalDate();
        }

        @Override
        public void toJson(
                @NotNull JsonWriter jsonWriter,
                @NotNull CustomScalarAdapters customScalarAdapters,
                LocalDate localDate
        ) throws IOException {
            jsonWriter.value(converter.formatDate(localDate, DateTimeFormatter.ISO_LOCAL_DATE));
        }

    }

    private static final class LocalDateTimeAdapter implements Adapter<LocalDateTime> {

        @Override
        public LocalDateTime fromJson(
                @NotNull JsonReader jsonReader,
                @NotNull CustomScalarAdapters customScalarAdapters
        ) throws IOException {
            return converter.parseDate(jsonReader.nextString());
        }

        @Override
        public void toJson(
                @NotNull JsonWriter jsonWriter,
                @NotNull CustomScalarAdapters customScalarAdapters,
                LocalDateTime localDateTime
        ) throws IOException {
            jsonWriter.value(converter.formatDate(localDateTime, DateTimeFormatter.ISO_INSTANT));
        }

    }

    private static final class LocalTimeAdapter implements Adapter<LocalTime> {

        @Override
        public LocalTime fromJson(
                @NotNull JsonReader jsonReader,
                @NotNull CustomScalarAdapters customScalarAdapters
        ) throws IOException {
            return LocalTime.parse(jsonReader.nextString(), DateTimeFormatter.ISO_LOCAL_TIME.withZone(ZoneOffset.UTC));
        }

        @Override
        public void toJson(
                @NotNull JsonWriter jsonWriter,
                @NotNull CustomScalarAdapters customScalarAdapters,
                LocalTime localTime
        ) throws IOException {
            jsonWriter.value(DateTimeHelper.toISOString(localTime));
        }

    }

    private static final class OffsetDateTimeAdapter implements Adapter<OffsetDateTime> {

        @Override
        public OffsetDateTime fromJson(
                @NotNull JsonReader jsonReader,
                @NotNull CustomScalarAdapters customScalarAdapters
        ) throws IOException {
            return OffsetDateTime.parse(jsonReader.nextString(), DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        }

        @Override
        public void toJson(
                @NotNull JsonWriter jsonWriter,
                @NotNull CustomScalarAdapters customScalarAdapters,
                OffsetDateTime offsetDateTime
        ) throws IOException {
            jsonWriter.value(DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(offsetDateTime));
        }

    }

    private static final class YearMonthAdapter implements Adapter<YearMonth> {

        @Override
        public YearMonth fromJson(
                @NotNull JsonReader jsonReader,
                @NotNull CustomScalarAdapters customScalarAdapters
        ) throws IOException {
            String stringValue = jsonReader.nextString();

            Matcher m = YEAR_MONTH_PATTERN.matcher(stringValue);

            if (m.find()) {
                return YearMonth.of(Integer.parseInt(m.group(1)), Integer.parseInt(m.group(2)));
            } else {
                throw new RuntimeException("Incorrect value: " + stringValue);
            }
        }

        @Override
        public void toJson(
                @NotNull JsonWriter jsonWriter,
                @NotNull CustomScalarAdapters customScalarAdapters,
                YearMonth yearMonth
        ) throws IOException {
            jsonWriter.value(yearMonth.toString());
        }

    }

    private static final class DurationAdapter implements Adapter<Duration> {

        @Override
        public Duration fromJson(
                @NotNull JsonReader jsonReader,
                @NotNull CustomScalarAdapters customScalarAdapters
        ) throws IOException {
            return Duration.parse(jsonReader.nextString());
        }

        @Override
        public void toJson(
                @NotNull JsonWriter jsonWriter,
                @NotNull CustomScalarAdapters customScalarAdapters,
                Duration duration
        ) throws IOException {
            jsonWriter.value(duration.toString());
        }

    }

}
