package com.benkitou.hotel.services;

import com.benkitou.hotel.daos.CityRepository;
import com.benkitou.hotel.dtos.CityDto;
import com.benkitou.hotel.exceptions.EntityServiceException;
import com.benkitou.hotel.mappers.CityMapper;
import com.benkitou.hotel.services.inter.CityService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {
    private final CityRepository cityRepository;
    private final CityMapper cityMapper;

    @Override
    public List<CityDto> getCities() {
        try {
            return cityMapper.modelsToDtos(cityRepository.findAll());
        } catch (Exception e) {
            throw new EntityServiceException("An error occurred while retrieving cities.", e);
        }
    }
}
