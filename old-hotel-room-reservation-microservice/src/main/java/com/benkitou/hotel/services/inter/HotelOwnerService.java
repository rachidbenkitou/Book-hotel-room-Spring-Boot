package com.benkitou.hotel.services.inter;

import com.benkitou.hotel.criteria.HotelOwnerCriteria;
import com.benkitou.hotel.dtos.HotelOwnerDto;
import com.benkitou.hotel.dtos.ResponseDto;
import com.benkitou.hotel.exceptions.EntityAlreadyExistsException;
import com.benkitou.hotel.exceptions.EntityNotFoundException;

import java.util.List;

public interface HotelOwnerService {
    List<HotelOwnerDto> getHotelOwners(HotelOwnerCriteria hotelOwnerCriteria);

    HotelOwnerDto getHotelOwnerById(Long id) throws EntityNotFoundException;

    HotelOwnerDto addHotelOwner(HotelOwnerDto hotelOwnerDto) throws EntityAlreadyExistsException;

    HotelOwnerDto updateHotelOwner(Long id, HotelOwnerDto hotelOwnerDto) throws EntityNotFoundException, EntityAlreadyExistsException;

    ResponseDto deleteHotelOwner(Long id) throws EntityAlreadyExistsException, EntityNotFoundException;

}
