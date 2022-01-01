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
package com.zhokhov.jambalaya.checks

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

class PreconditionsTests {

    private val positiveInt: Int = 1
    private val nullInt: Int? = null
    private val zeroInt: Int = 0
    private val negativeInt: Int = -1
    private val nullIntCollection: Collection<Int>? = null
    private val nullIntStringMap: Map<Int, String>? = null

    @Test
    fun checkNull() {
        assertDoesNotThrow {
            Preconditions.checkNull(null, "arg")
        }

        assertThrows<IllegalArgumentException> {
            Preconditions.checkNull(object {}, "arg")
        }.apply {
            assertEquals("The \"arg\" must be null", message)
        }
    }

    @Test
    fun checkNotNull() {
        assertDoesNotThrow {
            Preconditions.checkNotNull(object {}, "arg")
        }

        assertThrows<IllegalArgumentException> {
            Preconditions.checkNotNull(null, "arg")
        }.apply {
            assertEquals("The \"arg\" must not be null", message)
        }
    }

    @Test
    fun checkTrue() {
        assertDoesNotThrow {
            Preconditions.checkTrue(true, "arg")
        }

        assertThrows<IllegalArgumentException> {
            Preconditions.checkTrue(false, "arg")
        }.apply {
            assertEquals("The \"arg\" must be true", message)
        }

        assertThrows<IllegalArgumentException> {
            Preconditions.checkTrue(null, "arg")
        }.apply {
            assertEquals("The \"arg\" must be true", message)
        }
    }

    @Test
    fun checkFalse() {
        assertDoesNotThrow {
            Preconditions.checkFalse(false, "arg")
        }

        assertThrows<IllegalArgumentException> {
            Preconditions.checkFalse(null, "arg")
        }.apply {
            assertEquals("The \"arg\" must be false", message)
        }

        assertThrows<IllegalArgumentException> {
            Preconditions.checkFalse(true, "arg")
        }.apply {
            assertEquals("The \"arg\" must be false", message)
        }
    }

    @Test
    fun checkBlank() {
        assertDoesNotThrow {
            Preconditions.checkBlank("", "arg")
        }

        assertThrows<IllegalArgumentException> {
            Preconditions.checkBlank(null, "arg")
        }.apply {
            assertEquals("The \"arg\" must be blank", message)
        }

        assertThrows<IllegalArgumentException> {
            Preconditions.checkBlank("test", "arg")
        }.apply {
            assertEquals("The \"arg\" must be blank", message)
        }
    }

    @Test
    fun checkNotBlank() {
        assertDoesNotThrow {
            Preconditions.checkNotBlank("test", "arg")
        }

        assertThrows<IllegalArgumentException> {
            Preconditions.checkNotBlank(null, "arg")
        }.apply {
            assertEquals("The \"arg\" must not be blank", message)
        }

        assertThrows<IllegalArgumentException> {
            Preconditions.checkNotBlank("", "arg")
        }.apply {
            assertEquals("The \"arg\" must not be blank", message)
        }
    }

    @Test
    fun checkPositive() {
        assertDoesNotThrow {
            Preconditions.checkPositive(positiveInt, "arg")
        }

        assertThrows<IllegalArgumentException> {
            Preconditions.checkPositive(nullInt, "arg")
        }.apply {
            assertEquals("The \"arg\" must be positive", message)
        }

        assertThrows<IllegalArgumentException> {
            Preconditions.checkPositive(zeroInt, "arg")
        }.apply {
            assertEquals("The \"arg\" must be positive", message)
        }

        assertThrows<IllegalArgumentException> {
            Preconditions.checkPositive(negativeInt, "arg")
        }.apply {
            assertEquals("The \"arg\" must be positive", message)
        }
    }

    @Test
    fun checkPositiveOrZero() {
        assertDoesNotThrow {
            Preconditions.checkPositiveOrZero(positiveInt, "arg")
        }

        assertDoesNotThrow {
            Preconditions.checkPositiveOrZero(zeroInt, "arg")
        }

        assertThrows<IllegalArgumentException> {
            Preconditions.checkPositiveOrZero(nullInt, "arg")
        }.apply {
            assertEquals("The \"arg\" must be positive or zero", message)
        }

        assertThrows<IllegalArgumentException> {
            Preconditions.checkPositiveOrZero(negativeInt, "arg")
        }.apply {
            assertEquals("The \"arg\" must be positive or zero", message)
        }
    }

    @Test
    fun checkNegative() {
        assertDoesNotThrow {
            Preconditions.checkNegative(negativeInt, "arg")
        }

        assertThrows<IllegalArgumentException> {
            Preconditions.checkNegative(nullInt, "arg")
        }.apply {
            assertEquals("The \"arg\" must be negative", message)
        }

        assertThrows<IllegalArgumentException> {
            Preconditions.checkNegative(zeroInt, "arg")
        }.apply {
            assertEquals("The \"arg\" must be negative", message)
        }

        assertThrows<IllegalArgumentException> {
            Preconditions.checkNegative(positiveInt, "arg")
        }.apply {
            assertEquals("The \"arg\" must be negative", message)
        }
    }

    @Test
    fun checkNegativeOrZero() {
        assertDoesNotThrow {
            Preconditions.checkNegativeOrZero(negativeInt, "arg")
        }

        assertDoesNotThrow {
            Preconditions.checkNegativeOrZero(zeroInt, "arg")
        }

        assertThrows<IllegalArgumentException> {
            Preconditions.checkNegativeOrZero(nullInt, "arg")
        }.apply {
            assertEquals("The \"arg\" must be negative or zero", message)
        }

        assertThrows<IllegalArgumentException> {
            Preconditions.checkNegativeOrZero(positiveInt, "arg")
        }.apply {
            assertEquals("The \"arg\" must be negative or zero", message)
        }
    }

    @Test
    fun checkCollectionEmpty() {
        assertDoesNotThrow {
            Preconditions.checkEmpty(listOf<Int>(), "arg")
        }

        assertThrows<IllegalArgumentException> {
            Preconditions.checkEmpty(nullIntCollection, "arg")
        }.apply {
            assertEquals("The \"arg\" must be an empty collection", message)
        }

        assertThrows<IllegalArgumentException> {
            Preconditions.checkEmpty(listOf(1), "arg")
        }.apply {
            assertEquals("The \"arg\" must be an empty collection", message)
        }
    }

    @Test
    fun checkCollectionNotEmpty() {
        assertDoesNotThrow {
            Preconditions.checkNotEmpty(listOf(1), "arg")
        }

        assertThrows<IllegalArgumentException> {
            Preconditions.checkNotEmpty(nullIntCollection, "arg")
        }.apply {
            assertEquals("The \"arg\" must not be an empty collection", message)
        }

        assertThrows<IllegalArgumentException> {
            Preconditions.checkNotEmpty(listOf<Int>(), "arg")
        }.apply {
            assertEquals("The \"arg\" must not be an empty collection", message)
        }
    }

    @Test
    fun checkMapEmpty() {
        assertDoesNotThrow {
            Preconditions.checkEmpty(mapOf<Int, String>(), "arg")
        }

        assertThrows<IllegalArgumentException> {
            Preconditions.checkEmpty(nullIntStringMap, "arg")
        }.apply {
            assertEquals("The \"arg\" must be an empty map", message)
        }

        assertThrows<IllegalArgumentException> {
            Preconditions.checkEmpty(mapOf(Pair(1, "test")), "arg")
        }.apply {
            assertEquals("The \"arg\" must be an empty map", message)
        }
    }

    @Test
    fun checkMapNotEmpty() {
        assertDoesNotThrow {
            Preconditions.checkNotEmpty(mapOf(Pair(1, "test")), "arg")
        }

        assertThrows<IllegalArgumentException> {
            Preconditions.checkNotEmpty(nullIntStringMap, "arg")
        }.apply {
            assertEquals("The \"arg\" must not be an empty map", message)
        }

        assertThrows<IllegalArgumentException> {
            Preconditions.checkNotEmpty(mapOf<Int, String>(), "arg")
        }.apply {
            assertEquals("The \"arg\" must not be an empty map", message)
        }
    }

}
