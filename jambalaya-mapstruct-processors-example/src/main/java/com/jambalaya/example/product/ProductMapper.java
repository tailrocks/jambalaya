package com.jambalaya.example.product;

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
public interface ProductMapper {

    @Mapping(target = "id", ignore = true)
    Product toGrpc(CreateProductInput input);

}
