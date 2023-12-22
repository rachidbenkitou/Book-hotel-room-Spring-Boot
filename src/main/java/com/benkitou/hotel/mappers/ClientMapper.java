package com.benkitou.hotel.mappers;

import com.benkitou.hotel.dtos.ClientDto;
import com.benkitou.hotel.entities.Client;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface ClientMapper {
    ClientDto modelToDto(Client client);

    Client dtoToModel(ClientDto clientDto);

    List<ClientDto> modelsToDtos(List<Client> clients);
}
