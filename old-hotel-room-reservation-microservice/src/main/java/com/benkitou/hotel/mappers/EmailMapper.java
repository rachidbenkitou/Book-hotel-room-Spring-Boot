package com.benkitou.hotel.mappers;

import com.benkitou.hotel.dtos.EmailDto;
import com.benkitou.hotel.dtos.PhoneNumberDto;
import com.benkitou.hotel.entities.Email;
import com.benkitou.hotel.entities.PhoneNumber;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface EmailMapper {
    EmailDto modelToDto(Email email);

    Email dtoToModel(EmailDto emailDto);

    List<EmailDto> modelsToDtos(List<Email> emails);
}
