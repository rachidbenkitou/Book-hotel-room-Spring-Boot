package com.benkitou.hotel.controller;

import com.benkitou.hotel.dtos.EmailDto;
import com.benkitou.hotel.dtos.ResponseDto;
import com.benkitou.hotel.exceptions.EntityAlreadyExistsException;
import com.benkitou.hotel.exceptions.EntityNotFoundException;
import com.benkitou.hotel.services.inter.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emails")
@RequiredArgsConstructor
public class EmailController {
    private final EmailService emailService;

    @GetMapping
    public ResponseEntity<List<EmailDto>> getEmails() {
        return new ResponseEntity<>(emailService.getEmails(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmailDto> getEmailById(@PathVariable(name = "id") Long id) throws EntityNotFoundException {
        return new ResponseEntity<>(emailService.getEmailById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EmailDto> addEmail(@RequestBody EmailDto emailDto) throws EntityAlreadyExistsException {
        return new ResponseEntity<>(emailService.addEmail(emailDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmailDto> updateEmail(@PathVariable(name = "id") Long id, @RequestBody EmailDto emailDto) throws EntityNotFoundException, EntityAlreadyExistsException {
        return new ResponseEntity<>(emailService.updateEmail(id, emailDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> deleteEmail(@PathVariable(name = "id") Long id) throws EntityAlreadyExistsException, EntityNotFoundException {
        return new ResponseEntity<>(emailService.deleteEmail(id), HttpStatus.OK);
    }

}
