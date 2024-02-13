package com.benkitou.hotel.mappers;

import com.benkitou.hotel.dtos.HotelDemandDto;
import com.benkitou.hotel.entities.HotelDemand;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface HotelDemandMapper {
    HotelDemandDto modelToDto(HotelDemand hotelDemand);

    HotelDemand dtoToModel(HotelDemandDto hotelDemandDto);

    List<HotelDemandDto> modelsToDtos(List<HotelDemand> hotelDemands);
}
