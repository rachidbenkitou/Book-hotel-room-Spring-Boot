package com.benkitou.hotel.services.inter;

import com.benkitou.hotel.criteria.RoomCriteria;
import com.benkitou.hotel.dtos.ResponseDto;
import com.benkitou.hotel.dtos.RoomDto;
import com.benkitou.hotel.exceptions.EntityAlreadyExistsException;
import com.benkitou.hotel.exceptions.EntityNotFoundException;

import java.util.List;

public interface RoomService {
    List<RoomDto> getRooms(RoomCriteria roomCriteria);

    List<RoomDto> getRoomsBySpecification(RoomCriteria roomCriteria);

    RoomDto getRoomById(Long id) throws EntityNotFoundException;

    RoomDto addRoom(RoomDto roomDto) throws EntityAlreadyExistsException;

    RoomDto updateRoom(Long id, RoomDto roomDto) throws EntityNotFoundException, EntityAlreadyExistsException;

    ResponseDto deleteRoom(Long id) throws EntityAlreadyExistsException, EntityNotFoundException;
}
