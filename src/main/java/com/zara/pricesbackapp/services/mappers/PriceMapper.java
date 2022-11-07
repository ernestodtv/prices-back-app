package com.zara.pricesbackapp.services.mappers;

import com.zara.pricesbackapp.dtos.PriceDTO;
import com.zara.pricesbackapp.models.Price;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PriceMapper {

    @Mapping(target = "productId", source = "product.id")
    @Mapping(target = "brandId", source = "brand.id")
    @Mapping(target = "tariffId", source = "tariff.id")
    PriceDTO mapPriceToPriceDTO(Price price);

}
