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
