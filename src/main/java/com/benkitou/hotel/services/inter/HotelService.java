package com.benkitou.hotel.services.inter;

import com.benkitou.hotel.criteria.HotelCriteria;
import com.benkitou.hotel.dtos.HotelDto;
import com.benkitou.hotel.dtos.ResponseDto;

import java.util.List;

public interface HotelService {
    List<HotelDto> getHotels(HotelCriteria hotelCriteria);
    HotelDto getHotelById(Long id);
    HotelDto addHotel(HotelDto hotelDto);
    HotelDto updateHotel(Long id, HotelDto hotelDto);
    ResponseDto deleteHotel(Long id);

}
