package com.benkitou.hotel.controller;

import com.benkitou.hotel.dtos.PhoneNumberDto;
import com.benkitou.hotel.dtos.ResponseDto;
import com.benkitou.hotel.exceptions.EntityAlreadyExistsException;
import com.benkitou.hotel.exceptions.EntityNotFoundException;
import com.benkitou.hotel.services.inter.PhoneNumbersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/phoneNumbers")
@RequiredArgsConstructor
public class PhoneNumbersController {
    private final PhoneNumbersService phoneNumbersService;

    @GetMapping
    public ResponseEntity<List<PhoneNumberDto>> getPhoneNumbers() {
        return new ResponseEntity<>(phoneNumbersService.getPhoneNumbers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PhoneNumberDto> getPhoneNumberById(@PathVariable(name = "id") Long id) throws EntityNotFoundException {
        return new ResponseEntity<>(phoneNumbersService.getPhoneNumberById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PhoneNumberDto> addPhoneNumber(@RequestBody PhoneNumberDto phoneNumberDto) throws EntityAlreadyExistsException {
        return new ResponseEntity<>(phoneNumbersService.addPhoneNumber(phoneNumberDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PhoneNumberDto> updatePhoneNumber(@PathVariable(name = "id") Long id, @RequestBody PhoneNumberDto phoneNumberDto) throws EntityNotFoundException, EntityAlreadyExistsException {
        return new ResponseEntity<>(phoneNumbersService.updatePhoneNumber(id, phoneNumberDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> deletePhoneNumber(@PathVariable(name = "id") Long id) throws EntityAlreadyExistsException, EntityNotFoundException {
        return new ResponseEntity<>(phoneNumbersService.deletePhoneNumber(id), HttpStatus.OK);
    }
}
