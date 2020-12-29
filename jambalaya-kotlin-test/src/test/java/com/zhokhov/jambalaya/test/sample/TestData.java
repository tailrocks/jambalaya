package com.zhokhov.jambalaya.test.sample;

import java.util.List;

public class TestData {

    public static final Order ORDER = new Order(
            1L, "123-789-xyz",
            List.of(
                    new OrderItem(3L, "3AZ", "Test product"),
                    new OrderItem(7L, "ABC-1", "Abc Xyz")
            )
    );

}
