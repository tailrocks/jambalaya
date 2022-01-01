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
package com.zhokhov.jambalaya.kotlin.test;

import jambalaya.test.sample.apollo.TableCreateMutation;
import jambalaya.test.sample.apollo.TableListQuery;
import jambalaya.test.sample.apollo.type.TableCreateInput;
import jambalaya.test.sample.apollo.type.TableListInput;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class AssertGeneratorJavaTests {

    @Test
    public void queryTest() {
        TableListQuery tableListQuery = new TableListQuery(new TableListInput("test"));

        TableListQuery.TableList tableList = new TableListQuery.TableList(
                Arrays.asList(
                        new TableListQuery.Data1(
                                "account",
                                Arrays.asList(
                                        new TableListQuery.Column("id", "int8"),
                                        new TableListQuery.Column("created_date", "timestamp")
                                )
                        )
                ),
                null
        );

        TableListQuery.Data data = new TableListQuery.Data(tableList);

        AssertGenerator.generate(data, "data");
    }

    @Test
    public void mutationTest() {
        TableCreateMutation tableCreateMutation = new TableCreateMutation(new TableCreateInput("test"));

        TableCreateMutation.TableCreate tableCreate = new TableCreateMutation.TableCreate(
                new TableCreateMutation.Data1(
                        "account",
                        Arrays.asList(
                                new TableCreateMutation.Column("id", "int8"),
                                new TableCreateMutation.Column("created_date", "timestamp")
                        )
                ),
                null
        );

        TableCreateMutation.Data data = new TableCreateMutation.Data(tableCreate);

        AssertGenerator.generate(data, "data");
    }

}
