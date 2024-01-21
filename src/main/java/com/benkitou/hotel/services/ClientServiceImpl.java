package com.benkitou.hotel.services;

import com.benkitou.hotel.criteria.ClientCriteria;
import com.benkitou.hotel.daos.ClientRepository;
import com.benkitou.hotel.dtos.ClientDto;
import com.benkitou.hotel.dtos.ResponseDto;
import com.benkitou.hotel.entities.Client;
import com.benkitou.hotel.exceptions.EntityAlreadyExistsException;
import com.benkitou.hotel.exceptions.EntityNotFoundException;
import com.benkitou.hotel.exceptions.EntityServiceException;
import com.benkitou.hotel.patterns.factory.ClientCriteriaFactory;
import com.benkitou.hotel.mappers.ClientMapper;
import com.benkitou.hotel.services.inter.ClientService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    @Override
    public List<ClientDto> getClients(ClientCriteria clientCriteria) {
        try {
            return clientRepository.findClientsByQuery(
                    clientCriteria.getId(),
                    clientCriteria.getPhone(),
                    clientCriteria.getEmail(),
                    clientCriteria.getCin()
            );
        } catch (Exception e) {
            throw new EntityServiceException("An error occurred while retrieving clients.", e);
        }
    }

    @Override
    public ClientDto getClientById(Long id) throws EntityNotFoundException {
        try {
            ClientCriteria clientCriteria = ClientCriteriaFactory.createById(id);

            List<ClientDto> clientDtos = getClients(clientCriteria);
            return clientDtos.stream()
                    .findFirst()
                    .orElseThrow(() -> new EntityNotFoundException(String.format("The client with the id %d is not found.", id)));
        } catch (Exception e) {
            throw new EntityServiceException("An error occurred while retrieving client.", e);
        }
    }

    private boolean isClientExistsByEmail(String email) {
        return clientRepository.existsClientByEmail(email);
    }

    private boolean isClientExistsByPhone(String phone) {
        return clientRepository.existsClientByPhone(phone);
    }

    @Override
    public ClientDto addClient(ClientDto clientDto) throws EntityAlreadyExistsException {
        clientDto.setId(null);

        throwExceptionIfExistsByEmailOrPhone(clientDto.getEmail(), clientDto.getPhone());

        try {
            // Save the hotel and return the DTO
            Client savedClient = clientRepository.save(clientMapper.dtoToModel(clientDto));
            return clientMapper.modelToDto(savedClient);
        } catch (Exception e) {
            // Handle any exceptions during the save process
            throw new EntityServiceException("An error occurred while storing the client.", e);
        }
    }

    private void throwExceptionIfExistsByEmailOrPhone(String email, String phone) throws EntityAlreadyExistsException {
        if (isClientExistsByEmail(email)) {
            throw new EntityAlreadyExistsException(String.format("The client with email %s already exists.", email));
        }
        if (isClientExistsByPhone(phone)) {
            throw new EntityAlreadyExistsException(String.format("The client with phone %s already exists.", phone));
        }
    }

    @Override
    public ClientDto updateClient(Long id, ClientDto clientDto) throws EntityNotFoundException, EntityAlreadyExistsException {

        // Use Optional for clarity
        ClientDto existingClient = Optional.ofNullable(getClientById(id))
                .orElseThrow(() -> new EntityNotFoundException(String.format("Client with ID %d not found.", id)));

        // Ensure ID consistency
        if (!existingClient.getId().equals(clientDto.getId())) {
            throw new IllegalArgumentException("The provided ID and DTO ID do not match.");
        }

        try {
            Client updatedClient = clientRepository.save(clientMapper.dtoToModel(clientDto));
            return clientMapper.modelToDto(updatedClient);
        } catch (Exception e) {
            // Handle any exceptions during the update process
            throw new EntityServiceException("An error occurred while updating the client.", e);
        }
    }

    @Override
    public ResponseDto deleteClient(Long id) throws EntityAlreadyExistsException, EntityNotFoundException {
        if (!clientRepository.existsClientById(id)) {
            throw new EntityNotFoundException(String.format("Client with ID %d not found.", id));
        }
        try {
            clientRepository.deleteById(id);
            return ResponseDto.builder()
                    .message("Client deleted successfully.")
                    .status(HttpStatus.OK.value())
                    .build();
        } catch (Exception e) {
            throw new EntityServiceException("An error occurred while deleting the client.", e);
        }
    }
}
