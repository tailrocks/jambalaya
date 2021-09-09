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
package com.zhokhov.jambalaya.micronaut.mapstruct.protobuf

import com.google.protobuf.StringValue
import org.junit.jupiter.api.Test
import java.util.*
import kotlin.test.assertEquals

/**
 * @author Alexey Zhokhov
 */
class CommonConvertersMapperTests {

    private val mapper = CommonConvertersMapper()

    @Test
    fun `UUID to String`() {
        assertEquals(null, mapper.toString(null))
        assertEquals(
            "fc8650ef-7c2a-4db3-ad28-2ef92cd27c80",
            mapper.toString(UUID.fromString("fc8650ef-7c2a-4db3-ad28-2ef92cd27c80"))
        )
    }

    @Test
    fun `UUID to StringValue`() {
        assertEquals(null, mapper.toStringValue(null))
        assertEquals(
            StringValue.of("fc8650ef-7c2a-4db3-ad28-2ef92cd27c80"),
            mapper.toStringValue(UUID.fromString("fc8650ef-7c2a-4db3-ad28-2ef92cd27c80"))
        )
    }

    @Test
    fun `String to UUID`() {
        assertEquals(null, mapper.toUUID(null as String?))
        assertEquals(
            UUID.fromString("fc8650ef-7c2a-4db3-ad28-2ef92cd27c80"),
            mapper.toUUID("fc8650ef-7c2a-4db3-ad28-2ef92cd27c80")
        )
    }

    @Test
    fun `StringValue to UUID`() {
        assertEquals(null, mapper.toUUID(null as StringValue?))
        assertEquals(null, mapper.toUUID(StringValue.getDefaultInstance()))
        assertEquals(
            UUID.fromString("fc8650ef-7c2a-4db3-ad28-2ef92cd27c80"),
            mapper.toUUID(StringValue.of("fc8650ef-7c2a-4db3-ad28-2ef92cd27c80"))
        )
    }

}
