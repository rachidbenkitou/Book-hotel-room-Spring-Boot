package com.benkitou.hotel.controller;

import com.benkitou.hotel.dtos.ResponseDto;
import com.benkitou.hotel.dtos.RoomTypeDto;
import com.benkitou.hotel.exceptions.EntityAlreadyExistsException;
import com.benkitou.hotel.exceptions.EntityNotFoundException;
import com.benkitou.hotel.services.inter.RoomTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roomTypes")
@RequiredArgsConstructor
public class RoomTypeController {
    private final RoomTypeService roomTypeService;

    @GetMapping
    public ResponseEntity<List<RoomTypeDto>> getRoomTypes() {
        return new ResponseEntity<>(roomTypeService.getRoomTypes(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomTypeDto> getRoomTypeById(@PathVariable(name = "id") Long id) throws EntityNotFoundException {
        return new ResponseEntity<>(roomTypeService.getRoomTypeById(id), HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<RoomTypeDto> getRoomTypeByName(@PathVariable(name = "name") String name) throws EntityNotFoundException {
        return new ResponseEntity<>(roomTypeService.getRoomTypeByName(name), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RoomTypeDto> addRoomType(@RequestBody RoomTypeDto roomTypeDto) throws EntityAlreadyExistsException {
        return new ResponseEntity<>(roomTypeService.addRoomType(roomTypeDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoomTypeDto> updateRoomType(@PathVariable(name = "id") Long id, @RequestBody RoomTypeDto roomTypeDto) throws EntityNotFoundException, EntityAlreadyExistsException {
        return new ResponseEntity<>(roomTypeService.updateRoomType(id, roomTypeDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> deleteRoomType(@PathVariable(name = "id") Long id) throws EntityAlreadyExistsException, EntityNotFoundException {
        return new ResponseEntity<>(roomTypeService.deleteRoomType(id), HttpStatus.OK);
    }
}
