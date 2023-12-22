package com.benkitou.hotel.services;

import com.benkitou.hotel.criteria.HotelOwnerCriteria;
import com.benkitou.hotel.daos.HotelOwnerRepository;
import com.benkitou.hotel.dtos.HotelOwnerDto;
import com.benkitou.hotel.dtos.ResponseDto;
import com.benkitou.hotel.entities.HotelOwner;
import com.benkitou.hotel.exceptions.EntityAlreadyExistsException;
import com.benkitou.hotel.exceptions.EntityNotFoundException;
import com.benkitou.hotel.exceptions.EntityServiceException;
import com.benkitou.hotel.mappers.HotelOwnerMapper;
import com.benkitou.hotel.services.inter.HotelOwnerService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class HotelOwnerServiceImpl implements HotelOwnerService {
    private final HotelOwnerRepository hotelOwnerRepository;
    private final HotelOwnerMapper hotelOwnerMapper;

    @Override
    public List<HotelOwnerDto> getHotelOwners(HotelOwnerCriteria hotelOwnerCriteria) {
        try {
            return hotelOwnerRepository.findHotelOwnersByQuery(
                    hotelOwnerCriteria.getId(),
                    hotelOwnerCriteria.getPhone(),
                    hotelOwnerCriteria.getEmail()
            );
        } catch (Exception e) {
            throw new EntityServiceException("An error occurred while retrieving hotel owners.", e);
        }
    }

    @Override
    public HotelOwnerDto getHotelOwnerById(Long id) throws EntityNotFoundException {
        try {
            HotelOwnerCriteria hotelOwnerCriteria = new HotelOwnerCriteria(id);

            List<HotelOwnerDto> hotelOwnerDtos = getHotelOwners(hotelOwnerCriteria);
            return hotelOwnerDtos.stream()
                    .findFirst()
                    .orElseThrow(() -> new EntityNotFoundException(String.format("The hotel owner with the id %d is not found.", id)));
        } catch (Exception e) {
            throw new EntityServiceException("An error occurred while retrieving hotel owner.", e);
        }
    }

    private boolean isHotelOwnerExistsByEmail(String email) {
        return hotelOwnerRepository.existsHotelOwnersByEmail(email);
    }

    private boolean isHotelOwnerExistsByPhone(String phone) {
        return hotelOwnerRepository.existsHotelOwnersByPhone(phone);
    }

    @Override
    public HotelOwnerDto addHotelOwner(HotelOwnerDto hotelOwnerDto) throws EntityAlreadyExistsException {
        hotelOwnerDto.setId(null);

        throwExceptionIfExistsByEmailOrPhone(hotelOwnerDto.getEmail(), hotelOwnerDto.getPhone());

        try {
            HotelOwner savedHotelOwner = hotelOwnerRepository.save(hotelOwnerMapper.dtoToModel(hotelOwnerDto));
            return hotelOwnerMapper.modelToDto(savedHotelOwner);
        } catch (Exception e) {
            throw new EntityServiceException("An error occurred while storing the hotel owner.", e);
        }
    }

    private void throwExceptionIfExistsByEmailOrPhone(String email, String phone) throws EntityAlreadyExistsException {
        if (isHotelOwnerExistsByEmail(email)) {
            throw new EntityAlreadyExistsException(String.format("The hotel owner with email %s already exists.", email));
        }
        if (isHotelOwnerExistsByPhone(phone)) {
            throw new EntityAlreadyExistsException(String.format("The hotel owner with phone %s already exists.", phone));
        }
    }

    @Override
    public HotelOwnerDto updateHotelOwner(Long id, HotelOwnerDto hotelOwnerDto) throws EntityNotFoundException, EntityAlreadyExistsException {
        HotelOwnerDto existingHotelOwner = Optional.ofNullable(getHotelOwnerById(id))
                .orElseThrow(() -> new EntityNotFoundException(String.format("Hotel owner with ID %d not found.", id)));

        if (!existingHotelOwner.getId().equals(hotelOwnerDto.getId())) {
            throw new IllegalArgumentException("The provided ID and DTO ID do not match.");
        }
//        throwExceptionIfExistsByEmailOrPhone(hotelOwnerDto.getEmail(), hotelOwnerDto.getPhone());
        try {
            HotelOwner updatedHotelOwner = hotelOwnerRepository.save(hotelOwnerMapper.dtoToModel(hotelOwnerDto));
            return hotelOwnerMapper.modelToDto(updatedHotelOwner);
        } catch (Exception e) {
            throw new EntityServiceException("An error occurred while updating the hotel owner.", e);
        }
    }

    @Override
    public ResponseDto deleteHotelOwner(Long id) throws EntityAlreadyExistsException, EntityNotFoundException {
        if (!hotelOwnerRepository.existsHotelOwnerById(id)) {
            throw new EntityNotFoundException(String.format("Hotel owner with ID %d not found.", id));
        }
        try {
            hotelOwnerRepository.deleteById(id);
            return ResponseDto.builder()
                    .message("Hotel owner deleted successfully.")
                    .status(HttpStatus.OK.value())
                    .build();
        } catch (Exception e) {
            throw new EntityServiceException("An error occurred while deleting the hotel owner.", e);
        }
    }
}
