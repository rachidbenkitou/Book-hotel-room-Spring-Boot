package com.benkitou.hotel.services;

import com.benkitou.hotel.criteria.HotelCriteria;
import com.benkitou.hotel.daos.HotelRepository;
import com.benkitou.hotel.dtos.HotelDto;
import com.benkitou.hotel.dtos.ResponseDto;
import com.benkitou.hotel.entities.Hotel;
import com.benkitou.hotel.mappers.HotelMapper;
import com.benkitou.hotel.services.inter.HotelService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {
    private final HotelRepository hotelRepository;
    private final HotelMapper hotelMapper;
    @Override
    public List<HotelDto> getHotels(HotelCriteria hotelCriteria) {
        HotelCriteria hotelCriteria1 = HotelCriteria
                .builder()
                .id(hotelCriteria.getId())
                .name(hotelCriteria.getName())
                .address(hotelCriteria.getAddress())
                .cityId(hotelCriteria.getCityId())
                .build();
        return hotelRepository.findHotelsByQuery(
                hotelCriteria1.getId(),
                hotelCriteria1.getName(),
                hotelCriteria1.getAddress(),
                hotelCriteria1.getCityId()
        );
    }

    @Override
    public HotelDto getHotelById(Long id) {
        return null;
    }

    @Override
    public HotelDto addHotel(HotelDto hotelDto) {
        return null;
    }

    @Override
    public HotelDto updateHotel(Long id, HotelDto hotelDto) {
        return null;
    }

    @Override
    public ResponseDto deleteHotel(Long id) {
        return null;
    }
}
