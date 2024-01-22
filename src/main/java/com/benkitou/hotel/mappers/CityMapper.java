package com.benkitou.hotel.mappers;

import com.benkitou.hotel.dtos.CityDto;
import com.benkitou.hotel.entities.City;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface CityMapper {
    CityDto modelToDto(City city);

    City dtoToModel(CityDto cityDto);

    List<CityDto> modelsToDtos(List<City> cities);
}
