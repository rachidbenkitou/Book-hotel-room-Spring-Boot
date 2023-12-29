package com.benkitou.hotel.mappers;

import com.benkitou.hotel.dtos.RoomTypeDto;
import com.benkitou.hotel.entities.RoomType;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface RoomTypeMapper {

    RoomTypeDto modelToDto(RoomType roomType);

    RoomType dtoToModel(RoomTypeDto roomTypeDto);

    List<RoomTypeDto> modelsToDtos(List<RoomType> roomTypes);
}
