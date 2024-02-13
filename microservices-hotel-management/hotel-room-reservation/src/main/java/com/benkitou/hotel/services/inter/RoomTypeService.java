package com.benkitou.hotel.services.inter;

import com.benkitou.hotel.dtos.ResponseDto;
import com.benkitou.hotel.dtos.RoomTypeDto;
import com.benkitou.hotel.exceptions.EntityAlreadyExistsException;
import com.benkitou.hotel.exceptions.EntityNotFoundException;

import java.util.List;

public interface RoomTypeService {
    List<RoomTypeDto> getRoomTypes();

    RoomTypeDto getRoomTypeById(Long id) throws EntityNotFoundException;
    RoomTypeDto getRoomTypeByName(String name) throws EntityNotFoundException;


    RoomTypeDto addRoomType(RoomTypeDto roomTypeDto) throws EntityAlreadyExistsException;

    RoomTypeDto updateRoomType(Long id, RoomTypeDto roomTypeDto) throws EntityNotFoundException, EntityAlreadyExistsException;

    ResponseDto deleteRoomType(Long id) throws EntityAlreadyExistsException, EntityNotFoundException;
}
