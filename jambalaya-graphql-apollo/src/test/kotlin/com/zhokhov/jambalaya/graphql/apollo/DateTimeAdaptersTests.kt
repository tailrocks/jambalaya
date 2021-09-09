package com.zhokhov.jambalaya.graphql.apollo

import com.apollographql.apollo.api.CustomTypeValue
import com.zhokhov.jambalaya.graphql.apollo.DateTimeAdapters.YEAR_MONTH
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.YearMonth
import kotlin.test.assertEquals

class DateTimeAdaptersTests {

    @Test
    fun `year month converter`() {
        assertEquals(
            YearMonth.of(2021, 1),
            YEAR_MONTH.decode(CustomTypeValue.fromRawValue("2021-01"))
        )

        YEAR_MONTH.encode(YearMonth.of(2021, 1)).apply {
            assertEquals("2021-01", value)
        }

        assertThrows<RuntimeException> {
            YEAR_MONTH.decode(CustomTypeValue.fromRawValue("abc"))
        }.apply {
            assertEquals("Incorrect value: abc", message)
        }
    }

}
