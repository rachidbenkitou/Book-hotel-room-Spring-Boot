package com.benkitou.hotel.services.inter;

import com.benkitou.hotel.dtos.HotelDemandDto;
import com.benkitou.hotel.exceptions.EntityNotFoundException;

import java.util.List;

public interface HotelDemandService {
    List<HotelDemandDto> getAllHotelDemands(Long id, String status);

    HotelDemandDto getHotelDemandById(Long id) throws EntityNotFoundException;

    HotelDemandDto addHotelDemand(HotelDemandDto hotelDemandDto) throws EntityNotFoundException;

    HotelDemandDto modifyHotelDemandStatusToAccepted(Long hotelDemandId) throws EntityNotFoundException;

    HotelDemandDto modifyHotelDemandStatusToCancelled(Long hotelDemandId) throws EntityNotFoundException;

}
