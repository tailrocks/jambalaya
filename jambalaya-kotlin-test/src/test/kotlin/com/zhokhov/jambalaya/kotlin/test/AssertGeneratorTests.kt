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

import com.apollographql.apollo.api.ApolloResponse
import com.zhokhov.jambalaya.test.sample.TestData
import jambalaya.test.sample.apollo.TableCreateMutation
import jambalaya.test.sample.apollo.TableListQuery
import jambalaya.test.sample.apollo.type.TableCreateInput
import jambalaya.test.sample.apollo.type.TableListInput
import org.junit.jupiter.api.Test
import java.util.*
import kotlin.test.assertEquals

class AssertGeneratorTests {

    @Test
    fun pojo() {
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

    @Test
    fun `public fields`() {
        // GIVEN:
        val car = TestData.CAR

        // WHEN:
        val result = AssertGenerator.generate(car, "car")

        // THEN:
        val expected = """
            assertNotNull(car).apply {
                assertEquals("Tesla", brand)
                assertEquals("Model 3", model)
            }
        """.trimIndent()

        assertEquals(expected, result.toString())
    }

    @Test
    fun `apollo query test`() {
        // GIVEN:
        val tableListQuery = TableListQuery(TableListInput("test"))
        val tableList = TableListQuery.TableList(
            listOf(
                TableListQuery.Data1(
                    "account",
                    listOf(
                        TableListQuery.Column("id", "int8"),
                        TableListQuery.Column("created_date", "timestamp")
                    )
                )
            ),
            null
        )
        val data = TableListQuery.Data(tableList)

        val response = ApolloResponse.Builder<TableListQuery.Data>(
            tableListQuery,
            UUID.fromString("466cfca5-02b5-4e82-b2b8-73a2391f8a39"),
            data
        ).build()

        // WHEN:
        val result = AssertGenerator.generate(response, "response")

        // THEN:
        val expected = """
            assertNotNull(response).apply {
                assertNotNull(requestUuid).apply {
                    assertEquals(-5568573798663353799, leastSignificantBits)
                    assertEquals(5074708665784946306, mostSignificantBits)
                }
                assertNotNull(operation).apply {
                    assertNotNull(input).apply {
                        assertEquals("test", databaseName)
                    }
                }
                assertNotNull(data).apply {
                    assertNotNull(tableList).apply {
                        assertNotNull(data).apply {
                            assertEquals(1, size)
                            assertNotNull(get(0)).apply {
                                assertEquals("account", name)
                                assertNotNull(columns).apply {
                                    assertEquals(2, size)
                                    assertNotNull(get(0)).apply {
                                        assertEquals("id", name)
                                        assertEquals("int8", kind)
                                    }
                                    assertNotNull(get(1)).apply {
                                        assertEquals("created_date", name)
                                        assertEquals("timestamp", kind)
                                    }
                                }
                            }
                        }
                        assertNull(error)
                    }
                }
                assertNull(errors)
                assertNotNull(extensions).apply {
                    assertEquals(true, isEmpty)
                    // entries - java.lang.IllegalAccessException: class com.zhokhov.jambalaya.kotlin.test.AssertGenerator cannot access a member of class kotlin.collections.EmptyMap with modifiers "public"
                    // keys - java.lang.IllegalAccessException: class com.zhokhov.jambalaya.kotlin.test.AssertGenerator cannot access a member of class kotlin.collections.EmptyMap with modifiers "public"
                    // size - java.lang.IllegalAccessException: class com.zhokhov.jambalaya.kotlin.test.AssertGenerator cannot access a member of class kotlin.collections.EmptyMap with modifiers "public"
                    // values - java.lang.IllegalAccessException: class com.zhokhov.jambalaya.kotlin.test.AssertGenerator cannot access a member of class kotlin.collections.EmptyMap with modifiers "public"
                }
                assertNotNull(executionContext).apply {
            
                }
                assertEquals(false, isLast)
            }
        """.trimIndent()

        assertEquals(expected, result.toString())
    }

    @Test
    fun `apollo mutation test`() {
        // GIVEN:
        val tableCreateMutation = TableCreateMutation(TableCreateInput("test"))
        val tableCreate = TableCreateMutation.TableCreate(
            TableCreateMutation.Data1(
                "account",
                listOf(
                    TableCreateMutation.Column("id", "int8"),
                    TableCreateMutation.Column("created_date", "timestamp")
                )
            ),
            null
        )
        val data = TableCreateMutation.Data(tableCreate)

        val response = ApolloResponse.Builder<TableCreateMutation.Data>(
            tableCreateMutation,
            UUID.fromString("466cfca5-02b5-4e82-b2b8-73a2391f8a39"),
            data
        ).build()

        // WHEN:
        val result = AssertGenerator.generate(response, "response")

        // THEN:
        val expected = """
            assertNotNull(response).apply {
                assertNotNull(requestUuid).apply {
                    assertEquals(-5568573798663353799, leastSignificantBits)
                    assertEquals(5074708665784946306, mostSignificantBits)
                }
                assertNotNull(operation).apply {
                    assertNotNull(input).apply {
                        assertEquals("test", tableName)
                    }
                }
                assertNotNull(data).apply {
                    assertNotNull(tableCreate).apply {
                        assertNotNull(data).apply {
                            assertEquals("account", name)
                            assertNotNull(columns).apply {
                                assertEquals(2, size)
                                assertNotNull(get(0)).apply {
                                    assertEquals("id", name)
                                    assertEquals("int8", kind)
                                }
                                assertNotNull(get(1)).apply {
                                    assertEquals("created_date", name)
                                    assertEquals("timestamp", kind)
                                }
                            }
                        }
                        assertNull(error)
                    }
                }
                assertNull(errors)
                assertNotNull(extensions).apply {
                    assertEquals(true, isEmpty)
                    // entries - java.lang.IllegalAccessException: class com.zhokhov.jambalaya.kotlin.test.AssertGenerator cannot access a member of class kotlin.collections.EmptyMap with modifiers "public"
                    // keys - java.lang.IllegalAccessException: class com.zhokhov.jambalaya.kotlin.test.AssertGenerator cannot access a member of class kotlin.collections.EmptyMap with modifiers "public"
                    // size - java.lang.IllegalAccessException: class com.zhokhov.jambalaya.kotlin.test.AssertGenerator cannot access a member of class kotlin.collections.EmptyMap with modifiers "public"
                    // values - java.lang.IllegalAccessException: class com.zhokhov.jambalaya.kotlin.test.AssertGenerator cannot access a member of class kotlin.collections.EmptyMap with modifiers "public"
                }
                assertNotNull(executionContext).apply {
            
                }
                assertEquals(false, isLast)
            }
        """.trimIndent()

        assertEquals(expected, result.toString())
    }

}
