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
package com.jambalaya.example;

import com.jambalaya.example.jooq.tables.records.PaymentMethodRecord;
import com.zhokhov.jambalaya.micronaut.mapstruct.protobuf.ProtobufConvertersMapper;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

import java.time.LocalDate;

import static org.mapstruct.InjectionStrategy.CONSTRUCTOR;

@Mapper(
        componentModel = "jsr330",
        injectionStrategy = CONSTRUCTOR,
        collectionMappingStrategy = CollectionMappingStrategy.SETTER_PREFERRED,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        unmappedTargetPolicy = ReportingPolicy.ERROR,
        uses = {
                ProtobufConvertersMapper.class
        }
)
public interface PaymentMethodMapper {

    @Mapping(target = "stripePaymentMethodId", ignore = true)
    @Mapping(target = "card", source = "paymentMethodRecord")
    PaymentMethod toPaymentMethod(PaymentMethodRecord paymentMethodRecord);

    @Mapping(target = "brand", source = "cardBrand")
    @Mapping(target = "number", source = "cardNumber")
    @Mapping(target = "expirationMonth", source = "cardExpirationDate.year")
    @Mapping(target = "expirationYear", source = "cardExpirationDate.monthValue")
    PaymentMethodCard toPaymentMethodCard(PaymentMethodRecord paymentMethodRecord);

    PaymentMethodCardBrand toGrpcPaymentMethodCardBrand(MyPaymentMethodCardBrand myPaymentMethodCardBrand);

    MyPaymentMethodCardBrand toMyPaymentMethodCardBrand(PaymentMethodCardBrand paymentMethodCardBrand);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "stripePaymentMethodId", ignore = true)
    @Mapping(target = "cardBrand", ignore = true)
    @Mapping(target = "cardExpirationDate", source = "card", qualifiedByName = "toCardExpirationDate")
    @Mapping(target = "cardNumber", source = "card.number")
    @Mapping(target = "cardHolderName", source = "card.cardHolderName")
    PaymentMethodRecord toPaymentMethodRecord(PaymentMethodInput paymentMethodInput,
                                              @MappingTarget PaymentMethodRecord paymentMethodRecord);

    @Named("toCardExpirationDate")
    static LocalDate toCardExpirationDate(PaymentMethodCardInput paymentMethodCardInput) {
        if (paymentMethodCardInput.hasExpirationYear() && paymentMethodCardInput.hasExpirationMonth()) {
            return LocalDate.of(
                    paymentMethodCardInput.getExpirationYear().getValue(),
                    paymentMethodCardInput.getExpirationMonth().getValue(),
                    1
            );
        }
        return null;
    }

}
