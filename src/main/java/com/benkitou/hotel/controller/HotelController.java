package com.benkitou.hotel.controller;

import com.benkitou.hotel.criteria.HotelCriteria;
import com.benkitou.hotel.dtos.HotelDto;
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
        HotelCriteria hotelCriteria= HotelCriteria
                .builder()
                .id(id)
                .name(name)
                .address(address)
                .cityId(cityId)
                .build();
        return new ResponseEntity<>(hotelService.getHotels(hotelCriteria), HttpStatus.OK);
    }
}
