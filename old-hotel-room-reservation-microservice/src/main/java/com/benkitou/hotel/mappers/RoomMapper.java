package com.benkitou.hotel.mappers;

import com.benkitou.hotel.dtos.ClientDto;
import com.benkitou.hotel.dtos.RoomDto;
import com.benkitou.hotel.entities.Client;
import com.benkitou.hotel.entities.Room;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface RoomMapper {
    RoomDto modelToDto(Room room);

    Room dtoToModel(RoomDto roomDto);

    List<RoomDto> modelsToDtos(List<Room> rooms);
}
