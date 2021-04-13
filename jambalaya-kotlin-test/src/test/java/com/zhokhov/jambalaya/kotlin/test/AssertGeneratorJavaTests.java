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

import com.apollographql.apollo.api.Response;
import com.zhokhov.jambalaya.test.sample.apollo.mutation.TableCreateMutation;
import com.zhokhov.jambalaya.test.sample.apollo.query.TableListQuery;
import com.zhokhov.jambalaya.test.sample.apollo.type.TableCreateInput;
import com.zhokhov.jambalaya.test.sample.apollo.type.TableListInput;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class AssertGeneratorJavaTests {

    @Test
    public void queryTest() {
        TableListQuery tableListQuery = TableListQuery.builder()
                .input(
                        TableListInput.builder()
                                .databaseName("test")
                                .build()
                )
                .build();

        TableListQuery.TableList tableList = new TableListQuery.TableList(
                "TableListPayload",
                Arrays.asList(
                        new TableListQuery.Data1("Table", "account",
                                Arrays.asList(
                                        new TableListQuery.Column("TableColumn", "id", "int8"),
                                        new TableListQuery.Column("TableColumn", "created_date", "timestamp")
                                )
                        )
                ),
                null
        );

        TableListQuery.Data data = new TableListQuery.Data(tableList);

        Response<TableListQuery.Data> response = (Response) Response.builder(tableListQuery)
                .data(data)
                .build();

        AssertGenerator.generate(response, "response");
    }

    @Test
    public void mutationTest() {
        TableCreateMutation tableListQuery = TableCreateMutation.builder()
                .input(
                        TableCreateInput.builder()
                                .tableName("test")
                                .build()
                )
                .build();

        TableCreateMutation.TableCreate tableCreate = new TableCreateMutation.TableCreate(
                "TableCreatePayload",
                new TableCreateMutation.Data1("Table", "account",
                        Arrays.asList(
                                new TableCreateMutation.Column("TableColumn", "id", "int8"),
                                new TableCreateMutation.Column("TableColumn", "created_date", "timestamp")
                        )
                ),
                null
        );

        TableCreateMutation.Data data = new TableCreateMutation.Data(tableCreate);

        Response<TableCreateMutation.Data> response = (Response) Response.builder(tableListQuery)
                .data(data)
                .build();

        AssertGenerator.generate(response, "response");
    }

}
