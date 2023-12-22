package com.benkitou.hotel.services;

import com.benkitou.hotel.criteria.HotelCriteria;
import com.benkitou.hotel.daos.HotelRepository;
import com.benkitou.hotel.dtos.HotelDto;
import com.benkitou.hotel.dtos.ResponseDto;
import com.benkitou.hotel.exceptions.EntityNotFoundException;
import com.benkitou.hotel.exceptions.HotelServiceException;
import com.benkitou.hotel.mappers.HotelMapper;
import com.benkitou.hotel.services.inter.HotelService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
        try{
            return hotelRepository.findHotelsByQuery(
                    hotelCriteria.getId(),
                    hotelCriteria.getName(),
                    hotelCriteria.getAddress(),
                    hotelCriteria.getCityId()
            );
        } catch (Exception e) {
            throw new HotelServiceException("An error occurred while retrieving hotels.", e);
        }
    }

    @Override
    public HotelDto getHotelById(Long id) throws EntityNotFoundException {

        try {
            HotelCriteria hotelCriteria = HotelCriteria.builder()
                    .id(id)
                    .build();

            List<HotelDto> hotelDtos = getHotels(hotelCriteria);
            return hotelDtos.stream()
                    .findFirst()
                    .orElseThrow(() -> new EntityNotFoundException(String.format("The hotel with the id %d is not found.", id)));
        } catch (Exception e) {
            throw new HotelServiceException("An error occurred while retrieving hotels.", e);
        }

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
