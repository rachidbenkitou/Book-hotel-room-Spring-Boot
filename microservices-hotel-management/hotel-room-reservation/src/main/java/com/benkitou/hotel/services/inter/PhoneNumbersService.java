package com.benkitou.hotel.services.inter;

import com.benkitou.hotel.dtos.PhoneNumberDto;
import com.benkitou.hotel.dtos.ResponseDto;
import com.benkitou.hotel.exceptions.EntityAlreadyExistsException;
import com.benkitou.hotel.exceptions.EntityNotFoundException;

import java.util.List;

public interface PhoneNumbersService {
    List<PhoneNumberDto> getPhoneNumbers();

    PhoneNumberDto getPhoneNumberById(Long id) throws EntityNotFoundException;

    PhoneNumberDto addPhoneNumber(PhoneNumberDto phoneNumberDto) throws EntityAlreadyExistsException;

    PhoneNumberDto updatePhoneNumber(Long id, PhoneNumberDto phoneNumberDto) throws EntityNotFoundException, EntityAlreadyExistsException;

    ResponseDto deletePhoneNumber(Long id) throws EntityAlreadyExistsException, EntityNotFoundException;
}
