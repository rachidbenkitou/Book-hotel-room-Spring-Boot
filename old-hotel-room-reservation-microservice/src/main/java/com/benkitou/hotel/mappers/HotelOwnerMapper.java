package com.benkitou.hotel.mappers;

import com.benkitou.hotel.dtos.HotelOwnerDto;
import com.benkitou.hotel.entities.HotelOwner;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface HotelOwnerMapper {
    HotelOwnerDto modelToDto(HotelOwner hotelOwner);

    HotelOwner dtoToModel(HotelOwnerDto hotelOwnerDto);

    List<HotelOwnerDto> modelsToDtos(List<HotelOwner> hotelOwners);

}
