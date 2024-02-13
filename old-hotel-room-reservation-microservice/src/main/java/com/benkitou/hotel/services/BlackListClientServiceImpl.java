package com.benkitou.hotel.services;

import com.benkitou.hotel.criteria.BlackListClientCriteria;
import com.benkitou.hotel.daos.BlackListClientRepository;
import com.benkitou.hotel.dtos.BlackListClientDto;
import com.benkitou.hotel.entities.BlacklistClient;
import com.benkitou.hotel.exceptions.EntityAlreadyExistsException;
import com.benkitou.hotel.exceptions.EntityNotFoundException;
import com.benkitou.hotel.exceptions.EntityServiceException;
import com.benkitou.hotel.mappers.BlackListClientMapper;
import com.benkitou.hotel.services.inter.BlackListClientService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class BlackListClientServiceImpl implements BlackListClientService {
    private final BlackListClientRepository blackListClientRepository;
    private final BlackListClientMapper blackListClientMapper;

    @Override
    public List<BlackListClientDto> getBlackListClients(BlackListClientCriteria blackListClientCriteria) {
        try {
            return blackListClientRepository.findBlackListClientsByQuery(
                    blackListClientCriteria.getId(),
                    blackListClientCriteria.getHotelId(),
                    blackListClientCriteria.getPhone(),
                    blackListClientCriteria.getCin(),
                    blackListClientCriteria.getIsAllowed(),
                    blackListClientCriteria.getNumberOfTries(),
                    blackListClientCriteria.getClientId()
            );
        } catch (Exception e) {
            throw new EntityServiceException("An error occurred while retrieving all black list clients.", e);
        }
    }

    @Override
    public BlackListClientDto getBlackListClientById(Long id) throws EntityNotFoundException {
        try {
            BlackListClientCriteria blackListClientCriteria = BlackListClientCriteria.builder()
                    .id(id)
                    .build();

            List<BlackListClientDto> blackListClientDtos = getBlackListClients(blackListClientCriteria);
            return blackListClientDtos.stream()
                    .findFirst()
                    .orElseThrow(() -> new EntityNotFoundException(String.format("The client with the id %d is not found in the black list.", id)));
        } catch (Exception e) {
            throw new EntityServiceException("An error occurred while retrieving a client in black list.", e);
        }
    }

    @Override
    public BlackListClientDto addBlackListClient(BlackListClientDto blackListClientDto) throws EntityAlreadyExistsException {
        blackListClientDto.setId(null);
        if (blackListClientRepository.existsByCinAndHotelId(blackListClientDto.getCin(), blackListClientDto.getHotelId())) {
            throw new EntityAlreadyExistsException(String.format("The client with the cin %s in the hotel %d is already exists.", blackListClientDto.getCin(), blackListClientDto.getHotelId()));
        }

        try {
            BlacklistClient savedClientInBlackList = blackListClientRepository.save(blackListClientMapper.dtoToModel(blackListClientDto));
            return blackListClientMapper.modelToDto(savedClientInBlackList);
        } catch (Exception e) {
            // Handle any exceptions during the save process
            throw new EntityServiceException("An error occurred while storing the client in the black list.", e);
        }
    }

    private BlacklistClient retrieveBlackListClientById(Long id) throws EntityNotFoundException {
        return blackListClientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("The black list client with id %d not found.", id)));
    }

    @Override
    public BlackListClientDto modifyBlackListClientStatusToAllowed(Long id) throws EntityNotFoundException {
        BlacklistClient blacklistClient = retrieveBlackListClientById(id);
        blacklistClient.setIsAllowed(true);
        return blackListClientMapper.modelToDto(blackListClientRepository.save(blacklistClient));
    }

    @Override
    public BlackListClientDto modifyBlackListClientStatusToDisallowed(Long id) throws EntityNotFoundException {
        BlacklistClient blacklistClient = retrieveBlackListClientById(id);
        blacklistClient.setIsAllowed(false);
        return blackListClientMapper.modelToDto(blackListClientRepository.save(blacklistClient));
    }
}
