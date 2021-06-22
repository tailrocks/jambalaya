package com.jambalaya.example;

import com.jambalaya.example.dto.SimplePaymentMethodCard;
import com.zhokhov.jambalaya.micronaut.mapstruct.protobuf.ProtobufConvertersMapper;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

import static org.mapstruct.InjectionStrategy.CONSTRUCTOR;

/**
 * @author Alexey Zhokhov
 */
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
public interface PaymentMethodAlternativeMapper {

    @Mapping(target = "brand", ignore = true)
    PaymentMethodCard toPaymentMethodCard(SimplePaymentMethodCard simplePaymentMethodCard);

}
