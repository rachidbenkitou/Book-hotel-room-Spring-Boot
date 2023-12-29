package com.benkitou.hotel.controller;

import com.benkitou.hotel.criteria.RoomCriteria;
import com.benkitou.hotel.dtos.ResponseDto;
import com.benkitou.hotel.dtos.RoomDto;
import com.benkitou.hotel.exceptions.EntityAlreadyExistsException;
import com.benkitou.hotel.exceptions.EntityNotFoundException;
import com.benkitou.hotel.services.inter.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
@RequiredArgsConstructor
public class RoomController {
    private final RoomService roomService;

    @GetMapping
    public ResponseEntity<List<RoomDto>> getRoomsByQuery(
            @RequestParam(value = "id", required = false) Long id,
            @RequestParam(value = "number", required = false) Integer number,
            @RequestParam(value = "price", required = false) Double price,
            @RequestParam(value = "hotelId", required = false) Long hotelId,
            @RequestParam(value = "hotelName", required = false) String hotelName
    ) {
        RoomCriteria hotelCriteria = RoomCriteria.builder()
                .id(id)
                .number(number)
                .price(price)
                .hotelId(hotelId)
                .hotelName(hotelName)
                .build();

        return new ResponseEntity<>(roomService.getRooms(hotelCriteria), HttpStatus.OK);
    }

    @PostMapping("/searchBySpecification")
    public ResponseEntity<List<RoomDto>> searchRooms(@RequestBody RoomCriteria criteria) {
        List<RoomDto> rooms = roomService.getRoomsBySpecification(criteria);
        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomDto> getRoomById(@PathVariable(name = "id") Long id) throws EntityNotFoundException {
        return new ResponseEntity<>(roomService.getRoomById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RoomDto> addRoom(@RequestBody RoomDto roomDto) throws EntityAlreadyExistsException {
        return new ResponseEntity<>(roomService.addRoom(roomDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoomDto> updateRoom(@PathVariable(name = "id") Long id, @RequestBody RoomDto roomDto) throws EntityNotFoundException, EntityAlreadyExistsException {
        return new ResponseEntity<>(roomService.updateRoom(id, roomDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> deleteRoom(@PathVariable(name = "id") Long id) throws EntityAlreadyExistsException, EntityNotFoundException {
        return new ResponseEntity<>(roomService.deleteRoom(id), HttpStatus.OK);
    }

}
