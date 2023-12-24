package com.benkitou.hotel.services;

import com.benkitou.hotel.criteria.PhoneNumberCriteria;
import com.benkitou.hotel.daos.PhoneNumberRepository;
import com.benkitou.hotel.dtos.PhoneNumberDto;
import com.benkitou.hotel.dtos.ResponseDto;
import com.benkitou.hotel.entities.PhoneNumber;
import com.benkitou.hotel.exceptions.EntityAlreadyExistsException;
import com.benkitou.hotel.exceptions.EntityNotFoundException;
import com.benkitou.hotel.exceptions.EntityServiceException;
import com.benkitou.hotel.mappers.PhoneNumbersMapper;
import com.benkitou.hotel.services.inter.PhoneNumbersService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class PhoneNumbersServiceImpl implements PhoneNumbersService {
    private final PhoneNumberRepository phoneNumberRepository;
    private final PhoneNumbersMapper phoneNumbersMapper;

    @Override
    public List<PhoneNumberDto> getPhoneNumbers() {
        try {
            return phoneNumbersMapper.modelsToDtos(phoneNumberRepository.findAll());
        } catch (Exception e) {
            throw new EntityServiceException("An error occurred while retrieving phone numbers.", e);
        }
    }

    @Override
    public PhoneNumberDto getPhoneNumberById(Long id) throws EntityNotFoundException {
        try {
            PhoneNumber phoneNumber = phoneNumberRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException(String.format("The phone with the id %d is not found", id)));

            return phoneNumbersMapper.modelToDto(phoneNumber);
        } catch (Exception e) {
            throw new EntityServiceException("An error occurred while retrieving phone number.", e);
        }
    }


    @Override
    public PhoneNumberDto addPhoneNumber(PhoneNumberDto phoneNumberDto) throws EntityAlreadyExistsException {
        phoneNumberDto.setId(null);

        if (phoneNumberRepository.existsPhoneNumberByPhone(phoneNumberDto.getPhone())) {
            throw new EntityAlreadyExistsException(String.format("The phone number %s that exists in the hotel %d is already exists"
                    , phoneNumberDto.getPhone(), phoneNumberDto.getHotelId()));
        }
        try {
            PhoneNumber savedPhoneNumber = phoneNumberRepository.save(phoneNumbersMapper.dtoToModel(phoneNumberDto));
            return phoneNumbersMapper.modelToDto(savedPhoneNumber);
        } catch (Exception e) {
            throw new EntityServiceException("An error occurred while storing the phone number.", e);
        }
    }

    @Override
    public PhoneNumberDto updatePhoneNumber(Long id, PhoneNumberDto phoneNumberDto) throws EntityNotFoundException, EntityAlreadyExistsException {
        PhoneNumberDto existingPhoneNumber = Optional.ofNullable(getPhoneNumberById(id))
                .orElseThrow(() -> new EntityNotFoundException(String.format("Phone number with ID %d not found.", id)));

        if (!existingPhoneNumber.getId().equals(phoneNumberDto.getId())) {
            throw new IllegalArgumentException("The provided ID and DTO ID do not match.");
        }

        try {
            PhoneNumber updatedPhoneNumber = phoneNumberRepository.save(phoneNumbersMapper.dtoToModel(phoneNumberDto));
            return phoneNumbersMapper.modelToDto(updatedPhoneNumber);
        } catch (Exception e) {
            throw new EntityServiceException("An error occurred while updating the phone number.", e);
        }
    }

    @Override
    public ResponseDto deletePhoneNumber(Long id) throws EntityAlreadyExistsException, EntityNotFoundException {
        if (!phoneNumberRepository.existsById(id)) {
            throw new EntityNotFoundException(String.format("Phone number with ID %d not found.", id));
        }
        try {
            phoneNumberRepository.deleteById(id);
            return ResponseDto.builder()
                    .message("Phone number deleted successfully.")
                    .status(HttpStatus.OK.value())
                    .build();
        } catch (Exception e) {
            throw new EntityServiceException("An error occurred while deleting the phone number.", e);
        }
    }
}
