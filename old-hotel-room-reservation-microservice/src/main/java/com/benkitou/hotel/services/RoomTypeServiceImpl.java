package com.benkitou.hotel.services;

import com.benkitou.hotel.daos.RoomTypeRepository;
import com.benkitou.hotel.dtos.ResponseDto;
import com.benkitou.hotel.dtos.RoomTypeDto;
import com.benkitou.hotel.entities.RoomType;
import com.benkitou.hotel.exceptions.EntityAlreadyExistsException;
import com.benkitou.hotel.exceptions.EntityNotFoundException;
import com.benkitou.hotel.exceptions.EntityServiceException;
import com.benkitou.hotel.mappers.RoomTypeMapper;
import com.benkitou.hotel.services.inter.RoomTypeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class RoomTypeServiceImpl implements RoomTypeService {

    private final RoomTypeRepository roomTypeRepository;
    private final RoomTypeMapper roomTypeMapper;

    @Override
    public List<RoomTypeDto> getRoomTypes() {
        try {
            return roomTypeMapper.modelsToDtos(roomTypeRepository.findAll());
        } catch (Exception e) {
            throw new EntityServiceException("An error occurred while retrieving room types.", e);
        }
    }

    @Override
    public RoomTypeDto getRoomTypeById(Long id) throws EntityNotFoundException {
        try {
            RoomType roomType = roomTypeRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException(String.format("The room type with the id %d is not found", id)));

            return roomTypeMapper.modelToDto(roomType);
        } catch (Exception e) {
            throw new EntityServiceException("An error occurred while retrieving room type.", e);
        }
    }

    @Override
    public RoomTypeDto getRoomTypeByName(String name) throws EntityNotFoundException {
        try {
            RoomType roomType = roomTypeRepository.findRoomTypeByName(name)
                    .orElseThrow(() -> new EntityNotFoundException(String.format("The room type with the name %s is not found", name)));

            return roomTypeMapper.modelToDto(roomType);
        } catch (Exception e) {
            throw new EntityServiceException("An error occurred while retrieving room type.", e);
        }
    }

    @Override
    public RoomTypeDto addRoomType(RoomTypeDto roomTypeDto) throws EntityAlreadyExistsException {
        roomTypeDto.setId(null);

        if (roomTypeRepository.existsRoomTypeByName(roomTypeDto.getName())) {
            throw new EntityAlreadyExistsException(String.format("The room type with name  %s  is already exists"
                    , roomTypeDto.getName()));
        }
        try {
            RoomType savedRoomType = roomTypeRepository.save(roomTypeMapper.dtoToModel(roomTypeDto));
            return roomTypeMapper.modelToDto(savedRoomType);
        } catch (Exception e) {
            throw new EntityServiceException("An error occurred while storing the room type.", e);
        }
    }

    @Override
    public RoomTypeDto updateRoomType(Long id, RoomTypeDto roomTypeDto) throws EntityNotFoundException, EntityAlreadyExistsException {
        RoomTypeDto existingRoomType = Optional.ofNullable(getRoomTypeById(id))
                .orElseThrow(() -> new EntityNotFoundException(String.format("Room type with ID %d not found.", id)));

        if (!existingRoomType.getId().equals(roomTypeDto.getId())) {
            throw new IllegalArgumentException("The provided ID and DTO ID do not match.");
        }

        try {
            RoomType updatedRoomType = roomTypeRepository.save(roomTypeMapper.dtoToModel(roomTypeDto));
            return roomTypeMapper.modelToDto(updatedRoomType);
        } catch (Exception e) {
            throw new EntityServiceException("An error occurred while updating the room type.", e);
        }
    }

    @Override
    public ResponseDto deleteRoomType(Long id) throws EntityAlreadyExistsException, EntityNotFoundException {
        if (!roomTypeRepository.existsById(id)) {
            throw new EntityNotFoundException(String.format("Room type with ID %d not found.", id));
        }
        try {
            roomTypeRepository.deleteById(id);
            return ResponseDto.builder()
                    .message("Room type deleted successfully.")
                    .status(HttpStatus.OK.value())
                    .build();
        } catch (Exception e) {
            throw new EntityServiceException("An error occurred while deleting the room type.", e);
        }
    }
}
