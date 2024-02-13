package com.benkitou.hotel.mappers;

import com.benkitou.hotel.dtos.BlackListClientDto;
import com.benkitou.hotel.entities.BlacklistClient;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface BlackListClientMapper {
    BlackListClientDto modelToDto(BlacklistClient blacklistClient);

    BlacklistClient dtoToModel(BlackListClientDto blackListClientDto);

    List<BlackListClientDto> modelsToDtos(List<BlacklistClient> blacklistClients);
}
