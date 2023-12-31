package com.benkitou.hotel.controller;

import com.benkitou.hotel.criteria.ClientCriteria;
import com.benkitou.hotel.dtos.ClientDto;
import com.benkitou.hotel.dtos.ResponseDto;
import com.benkitou.hotel.exceptions.EntityAlreadyExistsException;
import com.benkitou.hotel.exceptions.EntityNotFoundException;
import com.benkitou.hotel.factorypattern.ClientCriteriaFactory;
import com.benkitou.hotel.services.inter.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;

    @GetMapping
    public ResponseEntity<List<ClientDto>> getClientsByQuery(
            @RequestParam(value = "id", required = false) Long id,
            @RequestParam(value = "phone", required = false) String phone,
            @RequestParam(value = "email", required = false) String email,
            @RequestParam(value = "cin", required = false) String cin
    ) {
        ClientCriteria clientCriteria = ClientCriteriaFactory.createByClientObject(id, phone, email, cin);

        return new ResponseEntity<>(clientService.getClients(clientCriteria), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDto> getClientById(@PathVariable(name = "id") Long id) throws EntityNotFoundException {
        return new ResponseEntity<>(clientService.getClientById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ClientDto> addClient(@RequestBody ClientDto clientDto) throws EntityAlreadyExistsException {
        return new ResponseEntity<>(clientService.addClient(clientDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientDto> updateClientById(@PathVariable(name = "id") Long id, @RequestBody ClientDto clientDto) throws EntityNotFoundException, EntityAlreadyExistsException {
        return new ResponseEntity<>(clientService.updateClient(id,clientDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> deleteClient(@PathVariable(name = "id") Long id) throws EntityAlreadyExistsException, EntityNotFoundException {
        return new ResponseEntity<>(clientService.deleteClient(id), HttpStatus.OK);
    }

}
