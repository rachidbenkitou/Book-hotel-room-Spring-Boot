package com.benkitou.hotel.controller;

import com.benkitou.hotel.criteria.BlackListClientCriteria;
import com.benkitou.hotel.dtos.BlackListClientDto;
import com.benkitou.hotel.exceptions.EntityAlreadyExistsException;
import com.benkitou.hotel.exceptions.EntityNotFoundException;
import com.benkitou.hotel.services.inter.BlackListClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blackListClients")
@RequiredArgsConstructor
public class BlackListClientController {
    private final BlackListClientService blackListClientService;

    @PostMapping("/searchBySpecification")
    public ResponseEntity<List<BlackListClientDto>> searchBlackListClients(@RequestBody BlackListClientCriteria blackListClientCriteria) {
        List<BlackListClientDto> blackListClientDtos = blackListClientService.getBlackListClients(blackListClientCriteria);
        return new ResponseEntity<>(blackListClientDtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BlackListClientDto> getBlackListClientById(@PathVariable(name = "id") Long id) throws EntityNotFoundException {
        return new ResponseEntity<>(blackListClientService.getBlackListClientById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BlackListClientDto> addBlackListClient(@RequestBody BlackListClientDto blackListClientDto) throws EntityAlreadyExistsException {
        return new ResponseEntity<>(blackListClientService.addBlackListClient(blackListClientDto), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}/allowed")
    public ResponseEntity<BlackListClientDto> modifyDiscountStatusToAllowed(@PathVariable Long id) throws EntityNotFoundException {
        BlackListClientDto updatedBlackListClientDto = blackListClientService.modifyBlackListClientStatusToAllowed(id);
        return ResponseEntity.ok(updatedBlackListClientDto);
    }

    @PatchMapping("/{id}/disallowed")
    public ResponseEntity<BlackListClientDto> modifyDiscountStatusToDisallowed(@PathVariable Long id) throws EntityNotFoundException {
        BlackListClientDto updatedBlackListClientDto = blackListClientService.modifyBlackListClientStatusToDisallowed(id);
        return ResponseEntity.ok(updatedBlackListClientDto);
    }

}
