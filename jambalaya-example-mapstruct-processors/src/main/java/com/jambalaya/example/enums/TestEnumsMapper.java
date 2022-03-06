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
package com.jambalaya.example.enums;

import com.jambalaya.example.PaymentMethodCardBrand;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.EnumMapping;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

import static com.tailrocks.jambalaya.mapstruct.JambalayaMappingConstants.CASE_FORMAT_TRANSFORMATION;
import static com.tailrocks.jambalaya.mapstruct.JambalayaMappingConstants.LOWER_CAMEL_TO_UPPER_UNDERSCORE;
import static com.tailrocks.jambalaya.mapstruct.JambalayaMappingConstants.LOWER_UNDERSCORE_TO_UPPER_UNDERSCORE;
import static com.tailrocks.jambalaya.mapstruct.JambalayaMappingConstants.UPPER_CAMEL_TO_UPPER_UNDERSCORE;
import static com.tailrocks.jambalaya.mapstruct.JambalayaMappingConstants.UPPER_UNDERSCORE_TO_UPPER_CAMEL;
import static org.mapstruct.InjectionStrategy.CONSTRUCTOR;

@Mapper(
        injectionStrategy = CONSTRUCTOR,
        collectionMappingStrategy = CollectionMappingStrategy.SETTER_PREFERRED,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface TestEnumsMapper {

    ChangeType toGrpcChangeType(MyChangeType myChangeType);

    MyChangeType toMyChangeType(ChangeType changeType);

    SlotFillingStatus toGrpcSlotFillingStatus(MySlotFillingStatus mySlotFillingStatus);

    MySlotFillingStatus toMySlotFillingStatus(SlotFillingStatus slotFillingStatus);

    AccessReasonEnum.AccessReason toGrpcAccessReason(MyAccessReason myAccessReason);

    MyAccessReason toMyAccessReason(AccessReasonEnum.AccessReason accessReason);

    FooBar toGrpcFooBar(MyFooBar myFooBar);

    MyFooBar toMyFooBar(FooBar fooBar);

    @EnumMapping(
            nameTransformationStrategy = CASE_FORMAT_TRANSFORMATION,
            configuration = LOWER_CAMEL_TO_UPPER_UNDERSCORE
    )
    FooBar toGrpcFooBar(MyFooBarCamelCase myFooBarCamelCase);

    @EnumMapping(
            nameTransformationStrategy = CASE_FORMAT_TRANSFORMATION,
            configuration = UPPER_CAMEL_TO_UPPER_UNDERSCORE
    )
    FooBar toGrpcFooBar(MyFooBarPascalCase myFooBarPascalCase);

    @EnumMapping(
            nameTransformationStrategy = CASE_FORMAT_TRANSFORMATION,
            configuration = LOWER_UNDERSCORE_TO_UPPER_UNDERSCORE
    )
    FooBar toGrpcFooBar(MyFooBarSnakeCase myFooBarSnakeCase);

    @EnumMapping(
            nameTransformationStrategy = CASE_FORMAT_TRANSFORMATION,
            configuration = UPPER_CAMEL_TO_UPPER_UNDERSCORE
    )
    PaymentMethodCardBrand toGrpcPaymentMethodCardBrand(PaymentMethodCardBrandPascalCase paymentMethodCardBrandPascalCase);

    @EnumMapping(
            nameTransformationStrategy = CASE_FORMAT_TRANSFORMATION,
            configuration = UPPER_UNDERSCORE_TO_UPPER_CAMEL
    )
    PaymentMethodCardBrandPascalCase toPaymentMethodCardBrandPascalCase(PaymentMethodCardBrand paymentMethodCardBrand);

}
