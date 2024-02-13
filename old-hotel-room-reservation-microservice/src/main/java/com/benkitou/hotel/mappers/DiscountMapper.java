package com.benkitou.hotel.mappers;

import com.benkitou.hotel.dtos.DiscountDto;
import com.benkitou.hotel.entities.Discount;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface DiscountMapper {
    DiscountDto modelToDto(Discount discount);

    Discount dtoToModel(DiscountDto discountDto);

    List<DiscountDto> modelsToDtos(List<Discount> discounts);
}
