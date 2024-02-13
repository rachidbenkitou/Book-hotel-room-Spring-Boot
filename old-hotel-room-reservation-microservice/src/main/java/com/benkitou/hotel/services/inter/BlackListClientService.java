package com.benkitou.hotel.services.inter;

import com.benkitou.hotel.criteria.BlackListClientCriteria;
import com.benkitou.hotel.dtos.BlackListClientDto;
import com.benkitou.hotel.exceptions.EntityAlreadyExistsException;
import com.benkitou.hotel.exceptions.EntityNotFoundException;

import java.util.List;

public interface BlackListClientService {
    List<BlackListClientDto> getBlackListClients(BlackListClientCriteria blackListClientCriteria);

    BlackListClientDto getBlackListClientById(Long id) throws EntityNotFoundException;

    BlackListClientDto addBlackListClient(BlackListClientDto blackListClientDto) throws EntityAlreadyExistsException;

    BlackListClientDto modifyBlackListClientStatusToAllowed(Long id) throws EntityNotFoundException;

    BlackListClientDto modifyBlackListClientStatusToDisallowed(Long id) throws EntityNotFoundException;
}
