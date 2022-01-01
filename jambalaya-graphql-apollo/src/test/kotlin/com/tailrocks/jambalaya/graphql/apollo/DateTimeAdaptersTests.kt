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
package com.zhokhov.jambalaya.graphql.apollo

import com.apollographql.apollo3.api.json.MapJsonReader
import com.apollographql.apollo3.api.json.MapJsonWriter
import com.zhokhov.jambalaya.graphql.apollo.DateTimeAdapters.CUSTOM_TYPE_ADAPTER_MAP
import com.zhokhov.jambalaya.graphql.apollo.DateTimeAdapters.YEAR_MONTH
import org.junit.jupiter.api.Test
import java.time.YearMonth
import kotlin.test.assertEquals

class DateTimeAdaptersTests {

    @Test
    fun `year month converter`() {
        assertEquals(
            YearMonth.of(2021, 1),
            YEAR_MONTH.fromJson(MapJsonReader(mapOf(Pair("", "2021-01"))), CUSTOM_TYPE_ADAPTER_MAP)
        )

        // FIXME
        YEAR_MONTH.toJson(MapJsonWriter(), CUSTOM_TYPE_ADAPTER_MAP, YearMonth.of(2021, 1)).apply {
            //assertEquals("2021-01", value)
        }

        // FIXME
        /*
        assertThrows<RuntimeException> {
            YEAR_MONTH.decode(CustomTypeValue.fromRawValue("abc"))
        }.apply {
            assertEquals("Incorrect value: abc", message)
        }
         */
    }

}
