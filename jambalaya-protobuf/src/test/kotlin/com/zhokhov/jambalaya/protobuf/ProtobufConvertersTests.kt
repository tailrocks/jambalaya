package com.zhokhov.jambalaya.protobuf

import com.google.protobuf.*
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import kotlin.test.assertEquals

class ProtobufConvertersTests {

    // to Java types

    @Test
    fun `convert StringValue to String`() {
        assertEquals(null, ProtobufConverters.toString(null))
        assertEquals("", ProtobufConverters.toString(StringValue.getDefaultInstance()))
        assertEquals("abc", ProtobufConverters.toString(StringValue.of("abc")))
    }

    @Test
    fun `convert List of StringValue to List of String`() {
        assertEquals(null, ProtobufConverters.toListString(null as List<StringValue>?))
        assertEquals(listOf(), ProtobufConverters.toListString(listOf()))
        assertEquals(listOf(""), ProtobufConverters.toListString(listOf(StringValue.getDefaultInstance())))
        assertEquals(
                listOf("abc", "xyz"),
                ProtobufConverters.toListString(listOf(StringValue.of("abc"), StringValue.of("xyz")))
        )
    }

    @Test
    fun `convert ProtocolStringList to List of String`() {
        assertEquals(null, ProtobufConverters.toListString(null as ProtocolStringList?))
        assertEquals(listOf(), ProtobufConverters.toListString(LazyStringArrayList.EMPTY))
        assertEquals(listOf("abc", "xyz"), ProtobufConverters.toListString(LazyStringArrayList(listOf("abc", "xyz"))))
    }

    @Test
    fun `convert Int32Value to Integer`() {
        assertEquals(null, ProtobufConverters.toInteger(null as Int32Value?))
        assertEquals(0, ProtobufConverters.toInteger(Int32Value.getDefaultInstance()))
        assertEquals(0, ProtobufConverters.toInteger(Int32Value.of(0)))
        assertEquals(-1, ProtobufConverters.toInteger(Int32Value.of(-1)))
        assertEquals(1, ProtobufConverters.toInteger(Int32Value.of(1)))
    }

    @Test
    fun `convert UInt32Value to Integer`() {
        assertEquals(null, ProtobufConverters.toInteger(null as UInt32Value?))
        assertEquals(0, ProtobufConverters.toInteger(UInt32Value.getDefaultInstance()))
        assertEquals(0, ProtobufConverters.toInteger(UInt32Value.of(0)))
        assertEquals(-1, ProtobufConverters.toInteger(UInt32Value.of(-1)))
        assertEquals(1, ProtobufConverters.toInteger(UInt32Value.of(1)))
    }

    @Test
    fun `convert Int64Value to Long`() {
        assertEquals(null, ProtobufConverters.toLong(null as Int64Value?))
        assertEquals(0, ProtobufConverters.toLong(Int64Value.getDefaultInstance()))
        assertEquals(0, ProtobufConverters.toLong(Int64Value.of(0)))
        assertEquals(-1, ProtobufConverters.toLong(Int64Value.of(-1)))
        assertEquals(1, ProtobufConverters.toLong(Int64Value.of(1)))
    }

    @Test
    fun `convert UInt64Value to Long`() {
        assertEquals(null, ProtobufConverters.toLong(null as UInt64Value?))
        assertEquals(0, ProtobufConverters.toLong(UInt64Value.getDefaultInstance()))
        assertEquals(0, ProtobufConverters.toLong(UInt64Value.of(0)))
        assertEquals(-1, ProtobufConverters.toLong(UInt64Value.of(-1)))
        assertEquals(1, ProtobufConverters.toLong(UInt64Value.of(1)))
    }

    @Test
    fun `convert FloatValue to Float`() {
        assertEquals(null, ProtobufConverters.toFloat(null as FloatValue?))
        assertEquals(0F, ProtobufConverters.toFloat(FloatValue.getDefaultInstance()))
        assertEquals(0.0F, ProtobufConverters.toFloat(FloatValue.of(0.0F)))
        assertEquals(-1.0F, ProtobufConverters.toFloat(FloatValue.of(-1.0F)))
        assertEquals(1.0F, ProtobufConverters.toFloat(FloatValue.of(1.0F)))
    }

    @Test
    fun `convert BoolValue to Boolean`() {
        assertEquals(null, ProtobufConverters.toBoolean(null as BoolValue?))
        assertEquals(false, ProtobufConverters.toBoolean(BoolValue.getDefaultInstance()))
        assertEquals(false, ProtobufConverters.toBoolean(BoolValue.of(false)))
        assertEquals(true, ProtobufConverters.toBoolean(BoolValue.of(true)))
    }

    @Test
    fun `convert Timestamp to LocalDate`() {
        assertEquals(null, ProtobufConverters.toLocalDate(null as Timestamp?))
        assertEquals(
                LocalDate.of(1970, 1, 1),
                ProtobufConverters.toLocalDate(Timestamp.getDefaultInstance())
        )
        assertEquals(
                LocalDate.of(1987, 12, 26),
                ProtobufConverters.toLocalDate(Timestamp.newBuilder().setSeconds(567475200).setNanos(0).build())
        )
    }

    @Test
    fun `convert Timestamp to LocalDateTime`() {
        assertEquals(null, ProtobufConverters.toLocalDateTime(null as Timestamp?))
        assertEquals(
                LocalDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT),
                ProtobufConverters.toLocalDateTime(Timestamp.getDefaultInstance())
        )
        assertEquals(
                LocalDateTime.of(LocalDate.of(1987, 12, 26), LocalTime.MIDNIGHT),
                ProtobufConverters.toLocalDateTime(Timestamp.newBuilder().setSeconds(567475200).setNanos(0).build())
        )
        assertEquals(
                LocalDateTime.of(LocalDate.of(1987, 12, 26), LocalTime.of(11, 12, 13, 14)),
                ProtobufConverters.toLocalDateTime(Timestamp.newBuilder().setSeconds(567515533).setNanos(14).build())
        )
    }

    // to gRPC types

    @Test
    fun `convert String to StringValue`() {
        assertEquals(null, ProtobufConverters.toStringValue(null))
        assertEquals(StringValue.of(""), ProtobufConverters.toStringValue(""))
        assertEquals(StringValue.of("abc"), ProtobufConverters.toStringValue("abc"))
    }

    @Test
    fun `convert List of String to List of StringValue`() {
        assertEquals(null, ProtobufConverters.toListStringValue(null as List<String>?))
        assertEquals(listOf(), ProtobufConverters.toListStringValue(listOf()))
        assertEquals(listOf(StringValue.of("")), ProtobufConverters.toListStringValue(listOf("")))
        assertEquals(
                listOf(StringValue.of("abc"), StringValue.of("xyz")),
                ProtobufConverters.toListStringValue(listOf("abc", "xyz"))
        )
    }

    @Test
    fun `convert List of String to ProtocolStringList`() {
        assertEquals(null, ProtobufConverters.toProtocolStringList(null as List<String>?))
        assertEquals(LazyStringArrayList.EMPTY, ProtobufConverters.toProtocolStringList(listOf()))
        assertEquals(
                LazyStringArrayList(listOf("abc", "xyz")),
                ProtobufConverters.toProtocolStringList(listOf("abc", "xyz"))
        )
    }

    @Test
    fun `convert Integer to Int32Value`() {
        assertEquals(null, ProtobufConverters.toInt32Value(null as Int?))
        assertEquals(Int32Value.of(0), ProtobufConverters.toInt32Value(0))
        assertEquals(Int32Value.of(-1), ProtobufConverters.toInt32Value(-1))
        assertEquals(Int32Value.of(1), ProtobufConverters.toInt32Value(1))
    }

    @Test
    fun `convert Integer to UInt32Value`() {
        assertEquals(null, ProtobufConverters.toUInt32Value(null as Int?))
        assertEquals(UInt32Value.of(0), ProtobufConverters.toUInt32Value(0))
        assertEquals(UInt32Value.of(-1), ProtobufConverters.toUInt32Value(-1))
        assertEquals(UInt32Value.of(1), ProtobufConverters.toUInt32Value(1))
    }

    @Test
    fun `convert Long to Int64Value`() {
        assertEquals(null, ProtobufConverters.toInt64Value(null as Long?))
        assertEquals(Int64Value.of(0), ProtobufConverters.toInt64Value(0))
        assertEquals(Int64Value.of(-1), ProtobufConverters.toInt64Value(-1))
        assertEquals(Int64Value.of(1), ProtobufConverters.toInt64Value(1))
    }

    @Test
    fun `convert Long to UInt64Value`() {
        assertEquals(null, ProtobufConverters.toUInt64Value(null as Long?))
        assertEquals(UInt64Value.of(0), ProtobufConverters.toUInt64Value(0))
        assertEquals(UInt64Value.of(-1), ProtobufConverters.toUInt64Value(-1))
        assertEquals(UInt64Value.of(1), ProtobufConverters.toUInt64Value(1))
    }

    @Test
    fun `convert Float to FloatValue`() {
        assertEquals(null, ProtobufConverters.toFloatValue(null as Float?))
        assertEquals(FloatValue.of(0F), ProtobufConverters.toFloatValue(0F))
        assertEquals(FloatValue.of(0.0F), ProtobufConverters.toFloatValue(0.0F))
        assertEquals(FloatValue.of(-1.0F), ProtobufConverters.toFloatValue(-1.0F))
        assertEquals(FloatValue.of(1.0F), ProtobufConverters.toFloatValue(1.0F))
    }

    @Test
    fun `convert Boolean to BoolValue`() {
        assertEquals(null, ProtobufConverters.toBoolValue(null as Boolean?))
        assertEquals(BoolValue.of(false), ProtobufConverters.toBoolValue(false))
        assertEquals(BoolValue.of(true), ProtobufConverters.toBoolValue(true))
    }

    @Test
    fun `convert LocalDate to Timestamp`() {
        assertEquals(null, ProtobufConverters.toTimestamp(null as LocalDate?))
        assertEquals(
                Timestamp.getDefaultInstance(),
                ProtobufConverters.toTimestamp(LocalDate.of(1970, 1, 1))
        )
        assertEquals(
                Timestamp.newBuilder().setSeconds(567475200).setNanos(0).build(),
                ProtobufConverters.toTimestamp(LocalDate.of(1987, 12, 26))
        )
    }

    @Test
    fun `convert LocalDateTime to Timestamp`() {
        assertEquals(null, ProtobufConverters.toTimestamp(null as LocalDateTime?))
        assertEquals(
                Timestamp.getDefaultInstance(),
                ProtobufConverters.toTimestamp(LocalDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT))
        )
        assertEquals(
                Timestamp.newBuilder().setSeconds(567475200).setNanos(0).build(),
                ProtobufConverters.toTimestamp(LocalDateTime.of(LocalDate.of(1987, 12, 26), LocalTime.MIDNIGHT))
        )
        assertEquals(
                Timestamp.newBuilder().setSeconds(567515533).setNanos(14).build(),
                ProtobufConverters.toTimestamp(LocalDateTime.of(LocalDate.of(1987, 12, 26), LocalTime.of(11, 12, 13, 14)))
        )
    }

}
