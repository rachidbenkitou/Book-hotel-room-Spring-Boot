package com.benkitou.hotel.mappers;

import com.benkitou.hotel.dtos.HotelDto;
import com.benkitou.hotel.entities.Hotel;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface HotelMapper {
    HotelDto modelToDto(Hotel hotel);
    Hotel dtoToModel(HotelDto hotelDto);
    List<HotelDto> modelsToDtos(List<Hotel> hotels);
}
