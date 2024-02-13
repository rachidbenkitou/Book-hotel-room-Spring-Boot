package com.benkitou.hotel.controller;

import com.benkitou.hotel.criteria.HotelCriteria;
import com.benkitou.hotel.dtos.HotelDto;
import com.benkitou.hotel.dtos.ImageDto;
import com.benkitou.hotel.dtos.ResponseDto;
import com.benkitou.hotel.exceptions.EntityAlreadyExistsException;
import com.benkitou.hotel.exceptions.EntityNotFoundException;
import com.benkitou.hotel.services.inter.HotelService;
import com.benkitou.hotel.services.inter.ImageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/hotels")
@RequiredArgsConstructor
public class HotelController {
    private final HotelService hotelService;
    private final ImageService imageService;

    @GetMapping
    public ResponseEntity<List<HotelDto>> getHotelsByQuery(
            @RequestParam(value = "id", required = false) Long id,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "address", required = false) String address,
            @RequestParam(value = "cityId", required = false) Long cityId,
            @RequestParam(value = "status", required = false) String status
    ) {
        HotelCriteria hotelCriteria = HotelCriteria.builder()
                .id(id)
                .name(name)
                .address(address)
                .status(status)
                .cityId(cityId)
                .build();

        return new ResponseEntity<>(hotelService.getHotels(hotelCriteria), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HotelDto> getHotelById(@PathVariable(name = "id") Long id) throws EntityNotFoundException {
        return new ResponseEntity<>(hotelService.getHotelById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<HotelDto> addHotel(
            @RequestPart(name = "hotelDto") String hotelDtoJson,
            @RequestPart(name = "images", required = false) List<MultipartFile> images
    ) throws EntityAlreadyExistsException {
        ObjectMapper objectMapper = new ObjectMapper();
        HotelDto hotelDto = null;
        try {
            // Convert JSON string to HotelDto
            hotelDto = objectMapper.readValue(hotelDtoJson, HotelDto.class);
        } catch (IOException e) {
            // Handle exception
        }

        HotelDto savedHotel = hotelService.addHotel(hotelDto, images);
        return new ResponseEntity<>(savedHotel, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<HotelDto> updateHotelById(@PathVariable(name = "id") Long id, @RequestBody HotelDto hotelDto) throws EntityNotFoundException, EntityAlreadyExistsException {
        return new ResponseEntity<>(hotelService.updateHotel(id, hotelDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> deleteHotel(@PathVariable(name = "id") Long id) throws EntityAlreadyExistsException, EntityNotFoundException {
        return new ResponseEntity<>(hotelService.deleteHotel(id), HttpStatus.OK);
    }
}


