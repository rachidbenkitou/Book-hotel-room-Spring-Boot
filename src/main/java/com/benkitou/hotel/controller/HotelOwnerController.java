package com.benkitou.hotel.controller;

import com.benkitou.hotel.criteria.HotelOwnerCriteria;
import com.benkitou.hotel.dtos.HotelOwnerDto;
import com.benkitou.hotel.dtos.ResponseDto;
import com.benkitou.hotel.exceptions.EntityAlreadyExistsException;
import com.benkitou.hotel.exceptions.EntityNotFoundException;
import com.benkitou.hotel.services.inter.HotelOwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotelOwners")
@RequiredArgsConstructor
public class HotelOwnerController {
    private final HotelOwnerService hotelOwnerService;

    @GetMapping
    public ResponseEntity<List<HotelOwnerDto>> getHotelOwnersByQuery(
            @RequestParam(value = "id", required = false) Long id,
            @RequestParam(value = "phone", required = false) String phone,
            @RequestParam(value = "email", required = false) String email
    ) {
        HotelOwnerCriteria hotelOwnerCriteria = new HotelOwnerCriteria(id, phone, email);

        return new ResponseEntity<>(hotelOwnerService.getHotelOwners(hotelOwnerCriteria), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HotelOwnerDto> getHotelOwnerById(@PathVariable(name = "id") Long id) throws EntityNotFoundException {
        return new ResponseEntity<>(hotelOwnerService.getHotelOwnerById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<HotelOwnerDto> addHotelOwner(@RequestBody HotelOwnerDto hotelOwnerDto) throws EntityAlreadyExistsException {
        return new ResponseEntity<>(hotelOwnerService.addHotelOwner(hotelOwnerDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HotelOwnerDto> updateHotelOwnerById(@PathVariable(name = "id") Long id, @RequestBody HotelOwnerDto hotelOwnerDto) throws EntityNotFoundException, EntityAlreadyExistsException {
        return new ResponseEntity<>(hotelOwnerService.updateHotelOwner(id, hotelOwnerDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> addHotelOwner(@PathVariable(name = "id") Long id) throws EntityAlreadyExistsException, EntityNotFoundException {
        return new ResponseEntity<>(hotelOwnerService.deleteHotelOwner(id), HttpStatus.OK);
    }
}
