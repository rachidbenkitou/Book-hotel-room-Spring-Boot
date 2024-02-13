package com.benkitou.hotel.controller;

import com.benkitou.hotel.dtos.CityDto;
import com.benkitou.hotel.services.inter.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cities")
@RequiredArgsConstructor
public class CityController {
    private final CityService cityService;

    @GetMapping
    public ResponseEntity<List<CityDto>> getCities(
    ) {
        return new ResponseEntity<>(cityService.getCities(), HttpStatus.OK);
    }

}
