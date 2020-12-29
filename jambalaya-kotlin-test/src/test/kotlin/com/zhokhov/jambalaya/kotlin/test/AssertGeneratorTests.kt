package com.zhokhov.jambalaya.kotlin.test

import com.zhokhov.jambalaya.test.sample.TestData
import org.junit.jupiter.api.Test

class AssertGeneratorTests {

    @Test
    fun test() {
        val order = TestData.ORDER

        AssertGenerator.generate(order, "order")
    }

}
