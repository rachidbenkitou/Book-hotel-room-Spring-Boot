package com.benkitou.hotel.mappers;

import com.benkitou.hotel.dtos.PhoneNumberDto;
import com.benkitou.hotel.entities.PhoneNumber;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface PhoneNumbersMapper {
    PhoneNumberDto modelToDto(PhoneNumber phoneNumber);

    PhoneNumber dtoToModel(PhoneNumberDto phoneNumberDto);

    List<PhoneNumberDto> modelsToDtos(List<PhoneNumber> phoneNumbers);
}
