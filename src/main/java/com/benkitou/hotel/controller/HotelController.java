package com.benkitou.hotel.controller;

import com.benkitou.hotel.criteria.HotelCriteria;
import com.benkitou.hotel.dtos.HotelDto;
import com.benkitou.hotel.dtos.ResponseDto;
import com.benkitou.hotel.exceptions.EntityAlreadyExistsException;
import com.benkitou.hotel.exceptions.EntityNotFoundException;
import com.benkitou.hotel.services.inter.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
@RequiredArgsConstructor
public class HotelController {
    private final HotelService hotelService;
    @GetMapping
    public ResponseEntity<List<HotelDto>> getHotelsByQuery(
            @RequestParam(value = "id", required = false) Long id,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "address", required = false) String address,
            @RequestParam(value = "cityId", required = false) Long cityId
    ){
        HotelCriteria hotelCriteria = HotelCriteria.builder()
                .id(id)
                .name(name)
                .address(address)
                .cityId(cityId)
                .build();

        return new ResponseEntity<>(hotelService.getHotels(hotelCriteria), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HotelDto> getHotelById(@PathVariable(name = "id") Long id) throws EntityNotFoundException {
        return new ResponseEntity<>(hotelService.getHotelById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<HotelDto> addHotel(@RequestBody HotelDto hotelDto) throws EntityAlreadyExistsException {
        return new ResponseEntity<>(hotelService.addHotel(hotelDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HotelDto> updateHotelById(@PathVariable(name = "id") Long id, @RequestBody HotelDto hotelDto) throws EntityNotFoundException {
        return new ResponseEntity<>(hotelService.updateHotel(id,hotelDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> addHotel(@PathVariable(name = "id") Long id) throws EntityAlreadyExistsException, EntityNotFoundException {
        return new ResponseEntity<>(hotelService.deleteHotel(id), HttpStatus.OK);
    }
}


