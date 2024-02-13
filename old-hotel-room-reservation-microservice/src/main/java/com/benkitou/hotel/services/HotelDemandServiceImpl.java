package com.benkitou.hotel.services;

import com.benkitou.hotel.daos.HotelDemandRepository;
import com.benkitou.hotel.dtos.HotelDemandDto;
import com.benkitou.hotel.entities.HotelDemand;
import com.benkitou.hotel.exceptions.EntityNotFoundException;
import com.benkitou.hotel.exceptions.EntityServiceException;
import com.benkitou.hotel.mappers.HotelDemandMapper;
import com.benkitou.hotel.services.inter.HotelDemandService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class HotelDemandServiceImpl implements HotelDemandService {
    private final HotelDemandRepository hotelDemandRepository;
    private final HotelDemandMapper hotelDemandMapper;

    @Override
    public List<HotelDemandDto> getAllHotelDemands(Long id, String status) {
        try {
            return hotelDemandRepository.findHotelDemandsByQuery(id, status);
        } catch (Exception e) {
            throw new EntityServiceException("An error occurred while retrieving all hotel demands.", e);
        }
    }

    @Override
    public HotelDemandDto getHotelDemandById(Long id) throws EntityNotFoundException {
        try {
            List<HotelDemandDto> hotelDemandDtos = getAllHotelDemands(id, null);
            return hotelDemandDtos.stream()
                    .findFirst()
                    .orElseThrow(() -> new EntityNotFoundException(String.format("The Hotel Demand with the id %d is not found.", id)));
        } catch (Exception e) {
            throw new EntityServiceException("An error occurred while retrieving hotel demand.", e);
        }

    }

    @Override
    public HotelDemandDto addHotelDemand(HotelDemandDto hotelDemandDto) throws EntityNotFoundException {
        hotelDemandDto.setId(null);
        try {
            HotelDemand savedHotelDemand = hotelDemandRepository.save(hotelDemandMapper.dtoToModel(hotelDemandDto));
            return hotelDemandMapper.modelToDto(savedHotelDemand);
        } catch (Exception e) {
            throw new EntityServiceException("An error occurred while storing the hotel demand.", e);
        }
    }

    @Override
    public HotelDemandDto modifyHotelDemandStatusToAccepted(Long hotelDemandId) throws EntityNotFoundException {
        try {
            HotelDemandDto hotelDemandDto = getHotelDemandById(hotelDemandId);
            hotelDemandDto.setHotelStatus("ACCEPTED");
            return hotelDemandMapper.modelToDto(hotelDemandRepository.save(hotelDemandMapper.dtoToModel(hotelDemandDto)));
        } catch (Exception e) {
            throw new EntityServiceException("An error occurred while modifying hotel demand status to accepted.", e);

        }
    }

    @Override
    public HotelDemandDto modifyHotelDemandStatusToCancelled(Long hotelDemandId) throws EntityNotFoundException {
        try {
            HotelDemandDto hotelDemandDto = getHotelDemandById(hotelDemandId);
            hotelDemandDto.setHotelStatus("CANCELLED");
            return hotelDemandMapper.modelToDto(hotelDemandRepository.save(hotelDemandMapper.dtoToModel(hotelDemandDto)));
        } catch (Exception e) {
            throw new EntityServiceException("An error occurred while modifying hotel demand status to cancelled.", e);

        }
    }
}
