package com.benkitou.hotel.services.inter;

import com.benkitou.hotel.criteria.ClientCriteria;
import com.benkitou.hotel.criteria.HotelCriteria;
import com.benkitou.hotel.dtos.ClientDto;
import com.benkitou.hotel.dtos.HotelDto;
import com.benkitou.hotel.dtos.ResponseDto;
import com.benkitou.hotel.exceptions.EntityAlreadyExistsException;
import com.benkitou.hotel.exceptions.EntityNotFoundException;

import java.util.List;

public interface ClientService {
    List<ClientDto> getClients(ClientCriteria clientCriteria);
    ClientDto getClientById(Long id) throws EntityNotFoundException;
    ClientDto addClient(ClientDto clientDto) throws EntityAlreadyExistsException;
    ClientDto updateClient(Long id, ClientDto clientDto) throws EntityNotFoundException, EntityAlreadyExistsException;
    ResponseDto deleteClient(Long id) throws EntityAlreadyExistsException, EntityNotFoundException;

}
