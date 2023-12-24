package com.benkitou.hotel.controller;

import com.benkitou.hotel.criteria.BookingCriteria;
import com.benkitou.hotel.dtos.bookingDtos.BookingDto;
import com.benkitou.hotel.dtos.bookingDtos.BookingRequestDto;
import com.benkitou.hotel.exceptions.EntityAlreadyExistsException;
import com.benkitou.hotel.exceptions.EntityNotFoundException;
import com.benkitou.hotel.services.inter.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
public class BookingController {
    private final BookingService bookingService;

    @GetMapping
    public ResponseEntity<List<BookingDto>> getAllBookingByQuery(
            @RequestParam(value = "id", required = false) Long id,
            @RequestParam(value = "statusId", required = false) Long statusId,
            @RequestParam(value = "clientId", required = false) Long clientId,
            @RequestParam(value = "dateCreated", required = false) LocalDate dateCreated,
            @RequestParam(value = "price", required = false) Integer price
    ) {
        BookingCriteria bookingCriteria = BookingCriteria.builder()
                .id(id)
                .clientId(clientId)
                .statusId(statusId)
                .price(price)
                .dateCreated(dateCreated)
                .build();

        return new ResponseEntity<>(bookingService.getAllBooking(bookingCriteria), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BookingDto> makeBooking(@RequestBody BookingRequestDto bookingRequestDto) throws EntityAlreadyExistsException, EntityNotFoundException {
        return new ResponseEntity<>(bookingService.makeBooking(bookingRequestDto), HttpStatus.CREATED);
    }

    @PatchMapping("/{bookingId}/activate")
    public ResponseEntity<BookingDto> modifyBookingStatusToActive(@PathVariable Long bookingId) throws EntityNotFoundException {
        BookingDto updatedBooking = bookingService.modifyBookingStatusToActive(bookingId);
        return ResponseEntity.ok(updatedBooking);
    }

    @PatchMapping("/{bookingId}/cancel")
    public ResponseEntity<BookingDto> modifyBookingStatusToCancelled(@PathVariable Long bookingId) throws EntityNotFoundException {
        BookingDto updatedBooking = bookingService.modifyBookingStatusToCancelled(bookingId);
        return ResponseEntity.ok(updatedBooking);
    }

}
