/*
 * Copyright 2022 original authors
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
package com.tailrocks.jambalaya.checks.jooq

import com.jambalaya.example.jooq.enums.PaymentMethodCardBrand
import com.jambalaya.example.jooq.tables.records.PaymentMethodRecord
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import java.time.LocalDate
import java.time.LocalDateTime

class PreconditionsTests {

    @Test
    fun checkNew() {
        var item = PaymentMethodRecord()

        assertDoesNotThrow {
            Preconditions.checkNew(item, "arg")
        }

        assertDoesNotThrow {
            Preconditions.checkNew(listOf(item), "arg")
        }

        item = PaymentMethodRecord(
            123, LocalDateTime.now(), LocalDateTime.now(), 1, "test", PaymentMethodCardBrand.VISA,
            LocalDate.now(), "1234567890", "TEST TEST"
        )

        assertThrows<IllegalArgumentException> {
            Preconditions.checkNew(item, "arg")
        }.apply {
            assertEquals("The \"arg\" must be new", message)
        }

        assertThrows<IllegalArgumentException> {
            Preconditions.checkNew(listOf(item), "arg")
        }.apply {
            assertEquals("The \"arg[0]\" must be new", message)
        }
    }

    @Test
    fun checkNotNew() {
        var item = PaymentMethodRecord(
            123, LocalDateTime.now(), LocalDateTime.now(), 1, "test", PaymentMethodCardBrand.VISA,
            LocalDate.now(), "1234567890", "TEST TEST"
        )

        assertDoesNotThrow {
            Preconditions.checkNotNew(item, "arg")
        }

        assertDoesNotThrow {
            Preconditions.checkNotNew(listOf(item), "arg")
        }

        item = PaymentMethodRecord()

        assertThrows<IllegalArgumentException> {
            Preconditions.checkNotNew(item, "arg")
        }.apply {
            assertEquals("The \"arg\" must not be new", message)
        }

        assertThrows<IllegalArgumentException> {
            Preconditions.checkNotNew(listOf(item), "arg")
        }.apply {
            assertEquals("The \"arg[0]\" must not be new", message)
        }
    }

    @Test
    fun checkNotNullAndNew() {
        // null values
        // TODO
        /*
        assertThrows<IllegalArgumentException> {
            Preconditions.checkNotNullAndNew(null, "arg")
        }.apply {
            assertEquals("The \"arg\" must not be null", message)
        }
         */

        assertThrows<IllegalArgumentException> {
            Preconditions.checkNotNullAndNew(listOf(null), "arg")
        }.apply {
            assertEquals("The \"arg[0]\" must not be null", message)
        }

        // new record
        var item = PaymentMethodRecord()

        assertDoesNotThrow {
            Preconditions.checkNotNullAndNew(item, "arg")
        }

        assertDoesNotThrow {
            Preconditions.checkNotNullAndNew(listOf(item), "arg")
        }

        // not new record
        item = PaymentMethodRecord(
            123, LocalDateTime.now(), LocalDateTime.now(), 1, "test", PaymentMethodCardBrand.VISA,
            LocalDate.now(), "1234567890", "TEST TEST"
        )

        assertThrows<IllegalArgumentException> {
            Preconditions.checkNotNullAndNew(item, "arg")
        }.apply {
            assertEquals("The \"arg\" must be new", message)
        }

        assertThrows<IllegalArgumentException> {
            Preconditions.checkNotNullAndNew(listOf(item), "arg")
        }.apply {
            assertEquals("The \"arg[0]\" must be new", message)
        }
    }

    @Test
    fun checkNotNullAndNotNew() {
        // null values
        // TODO
        /*
        assertThrows<IllegalArgumentException> {
            Preconditions.checkNotNullAndNotNew(null, "arg")
        }.apply {
            assertEquals("The \"arg\" must not be null", message)
        }
         */

        assertThrows<IllegalArgumentException> {
            Preconditions.checkNotNullAndNotNew(listOf(null), "arg")
        }.apply {
            assertEquals("The \"arg[0]\" must not be null", message)
        }

        // not new record
        var item = PaymentMethodRecord(
            123, LocalDateTime.now(), LocalDateTime.now(), 1, "test", PaymentMethodCardBrand.VISA,
            LocalDate.now(), "1234567890", "TEST TEST"
        )

        assertDoesNotThrow {
            Preconditions.checkNotNullAndNotNew(item, "arg")
        }

        assertDoesNotThrow {
            Preconditions.checkNotNullAndNotNew(listOf(item), "arg")
        }

        // new record
        item = PaymentMethodRecord()

        assertThrows<IllegalArgumentException> {
            Preconditions.checkNotNullAndNotNew(item, "arg")
        }.apply {
            assertEquals("The \"arg\" must not be new", message)
        }

        assertThrows<IllegalArgumentException> {
            Preconditions.checkNotNullAndNotNew(listOf(item), "arg")
        }.apply {
            assertEquals("The \"arg[0]\" must not be new", message)
        }
    }

}
