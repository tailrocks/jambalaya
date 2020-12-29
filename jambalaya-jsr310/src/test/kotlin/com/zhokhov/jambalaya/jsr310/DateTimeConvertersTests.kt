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
package com.zhokhov.jambalaya.jsr310

import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.*
import kotlin.test.assertEquals

class DateTimeConvertersTests {

    companion object {
        @BeforeAll
        @JvmStatic
        internal fun beforeAll() {
            TimeZone.setDefault(TimeZone.getTimeZone(ZoneOffset.UTC))
        }
    }

    @Test
    fun `convert LocalDate to Date`() {
        assertEquals(null, DateTimeConverters.toDate(null as LocalDate?))
        assertEquals(
                GregorianCalendar(2020, Calendar.MARCH, 14).time,
                DateTimeConverters.toDate(LocalDate.of(2020, 3, 14))
        )
    }

    @Test
    fun `convert LocalDateTime to Date`() {
        assertEquals(null, DateTimeConverters.toDate(null as LocalDateTime?))
        assertEquals(
                GregorianCalendar(2020, Calendar.MARCH, 14, 11, 12, 13).time,
                DateTimeConverters.toDate(LocalDateTime.of(2020, 3, 14, 11, 12, 13))
        )
    }

    @Test
    fun `convert Date to LocalDate`() {
        assertEquals(null, DateTimeConverters.toLocalDate(null as Date?))
        assertEquals(
                LocalDate.of(2020, 3, 14),
                DateTimeConverters.toLocalDate(GregorianCalendar(2020, Calendar.MARCH, 14).time)
        )
    }

    @Test
    fun `convert Date to LocalDateTime`() {
        assertEquals(null, DateTimeConverters.toLocalDateTime(null as Date?))
        assertEquals(
                LocalDateTime.of(2020, 3, 14, 11, 12, 13),
                DateTimeConverters.toLocalDateTime(GregorianCalendar(2020, Calendar.MARCH, 14, 11, 12, 13).time)
        )
    }

    @Test
    fun `convert LocalDate to Instant`() {
        assertEquals(null, DateTimeConverters.toInstant(null as LocalDate?))
        assertEquals(
                Instant.parse("2020-03-14T00:00:00Z"),
                DateTimeConverters.toInstant(LocalDate.of(2020, 3, 14))
        )
    }

    @Test
    fun `convert LocalDateTime to Instant`() {
        assertEquals(null, DateTimeConverters.toInstant(null as LocalDateTime?))
        assertEquals(
                Instant.parse("2020-03-14T11:12:13Z"),
                DateTimeConverters.toInstant(LocalDateTime.of(2020, 3, 14, 11, 12, 13))
        )
    }

}
