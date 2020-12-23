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

}
