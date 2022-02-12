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
package com.zhokhov.jambalaya.test.sample;

import java.util.Arrays;

public class TestData {

    public static final Order ORDER = new Order(
            1L, "123-789-xyz",
            Arrays.asList(
                    new OrderItem(3L, "3AZ", "Test product"),
                    new OrderItem(7L, "ABC-1", "Abc Xyz")
            )
    );

    public static final Car CAR = new Car("Tesla", "Model 3");

}
