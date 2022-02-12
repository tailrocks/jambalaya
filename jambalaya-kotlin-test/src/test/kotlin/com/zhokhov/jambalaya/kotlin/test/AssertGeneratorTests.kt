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
package com.zhokhov.jambalaya.kotlin.test

import com.zhokhov.jambalaya.test.sample.TestData
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class AssertGeneratorTests {

    @Test
    fun test() {
        // GIVEN:
        val order = TestData.ORDER

        // WHEN:
        val result = AssertGenerator.generate(order, "order")

        // THEN:
        val expected = """
            assertNotNull(order).apply {
                assertEquals(1, id)
                assertNotNull(items).apply {
                    assertEquals(2, size)
                    assertNotNull(get(0)).apply {
                        assertEquals(3, id)
                        assertEquals("Test product", productName)
                        assertEquals("3AZ", sku)
                    }
                    assertNotNull(get(1)).apply {
                        assertEquals(7, id)
                        assertEquals("Abc Xyz", productName)
                        assertEquals("ABC-1", sku)
                    }
                }
                assertEquals("123-789-xyz", orderNumber)
            }
        """.trimIndent()

        assertEquals(expected, result.toString())
    }

}
