package com.benkitou.hotel.services;

import com.benkitou.hotel.criteria.RoomCriteria;
import com.benkitou.hotel.daos.RoomRepository;
import com.benkitou.hotel.dtos.ResponseDto;
import com.benkitou.hotel.dtos.RoomDto;
import com.benkitou.hotel.entities.Room;
import com.benkitou.hotel.exceptions.EntityAlreadyExistsException;
import com.benkitou.hotel.exceptions.EntityNotFoundException;
import com.benkitou.hotel.exceptions.EntityServiceException;
import com.benkitou.hotel.mappers.RoomMapper;
import com.benkitou.hotel.services.inter.RoomService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;

    @Override
    public List<RoomDto> getRooms(RoomCriteria roomCriteria) {
        try {
            return roomRepository.findRoomsByQuery(
                    roomCriteria.getId(),
                    roomCriteria.getNumber(),
                    roomCriteria.getPrice(),
                    roomCriteria.getHotelId(),
                    roomCriteria.getHotelName()
            );
        } catch (Exception e) {
            throw new EntityServiceException("An error occurred while retrieving rooms.", e);
        }
    }

    @Override
    public RoomDto getRoomById(Long id) throws EntityNotFoundException {
        try {
            RoomCriteria roomCriteria = RoomCriteria.builder().id(id).build();

            List<RoomDto> roomDtos = getRooms(roomCriteria);
            return roomDtos.stream()
                    .findFirst()
                    .orElseThrow(() -> new EntityNotFoundException(String.format("The room with the id %d is not found.", id)));
        } catch (Exception e) {
            throw new EntityServiceException("An error occurred while retrieving room.", e);
        }
    }

    @Override
    public RoomDto addRoom(RoomDto roomDto) throws EntityAlreadyExistsException {
        roomDto.setId(null);

        if (roomRepository.existsByNumberAndHotelId(roomDto.getNumber(), roomDto.getHotelId())) {
            throw new EntityAlreadyExistsException(String.format("The room with the number %d that exists in the hotel %d is already exists"
                    , roomDto.getNumber(), roomDto.getHotelId()));
        }
        try {
            Room savedRoom = roomRepository.save(roomMapper.dtoToModel(roomDto));
            return roomMapper.modelToDto(savedRoom);
        } catch (Exception e) {
            throw new EntityServiceException("An error occurred while storing the room.", e);
        }
    }

    @Override
    public RoomDto updateRoom(Long id, RoomDto roomDto) throws EntityNotFoundException, EntityAlreadyExistsException {
        RoomDto existingRoom = Optional.ofNullable(getRoomById(id))
                .orElseThrow(() -> new EntityNotFoundException(String.format("Room with ID %d not found.", id)));

        if (!existingRoom.getId().equals(roomDto.getId())) {
            throw new IllegalArgumentException("The provided ID and DTO ID do not match.");
        }

        try {
            Room updatedRoom = roomRepository.save(roomMapper.dtoToModel(roomDto));
            return roomMapper.modelToDto(updatedRoom);
        } catch (Exception e) {
            throw new EntityServiceException("An error occurred while updating the room.", e);
        }
    }

    @Override
    public ResponseDto deleteRoom(Long id) throws EntityAlreadyExistsException, EntityNotFoundException {
        if (!roomRepository.existsRoomById(id)) {
            throw new EntityNotFoundException(String.format("Room with ID %d not found.", id));
        }
        try {
            roomRepository.deleteById(id);
            return ResponseDto.builder()
                    .message("Room deleted successfully.")
                    .status(HttpStatus.OK.value())
                    .build();
        } catch (Exception e) {
            throw new EntityServiceException("An error occurred while deleting the room.", e);
        }
    }
}
