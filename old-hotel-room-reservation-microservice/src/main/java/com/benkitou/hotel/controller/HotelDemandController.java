package com.benkitou.hotel.controller;

import com.benkitou.hotel.dtos.HotelDemandDto;
import com.benkitou.hotel.exceptions.EntityNotFoundException;
import com.benkitou.hotel.services.inter.HotelDemandService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotelDemands")
@AllArgsConstructor
public class HotelDemandController {
    private final HotelDemandService hotelDemandService;

    @GetMapping
    public ResponseEntity<List<HotelDemandDto>> getAllHotelDemandByQuery(
            @RequestParam(value = "id", required = false) Long id,
            @RequestParam(value = "status", required = false) String status
    ) {
        return new ResponseEntity<>(hotelDemandService.getAllHotelDemands(id, status), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<HotelDemandDto> addHotelDemand(@RequestBody HotelDemandDto hotelDemandDto) throws EntityNotFoundException {
        return new ResponseEntity<>(hotelDemandService.addHotelDemand(hotelDemandDto), HttpStatus.CREATED);
    }

    @PatchMapping("/{hotelDemandId}/accepted")
    public ResponseEntity<HotelDemandDto> modifyHotelDemandStatusToAccepted(@PathVariable Long hotelDemandId) throws EntityNotFoundException {
        HotelDemandDto updatedHotelDemand = hotelDemandService.modifyHotelDemandStatusToAccepted(hotelDemandId);
        return ResponseEntity.ok(updatedHotelDemand);
    }

    @PatchMapping("/{hotelDemandId}/cancelled")
    public ResponseEntity<HotelDemandDto> modifyBookingStatusToCancelled(@PathVariable Long hotelDemandId) throws EntityNotFoundException {
        HotelDemandDto updatedHotelDemand = hotelDemandService.modifyHotelDemandStatusToCancelled(hotelDemandId);
        return ResponseEntity.ok(updatedHotelDemand);
    }
}
