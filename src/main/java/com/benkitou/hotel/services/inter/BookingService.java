package com.benkitou.hotel.services.inter;

import com.benkitou.hotel.criteria.BookingCriteria;
import com.benkitou.hotel.dtos.bookingDtos.BookingDto;
import com.benkitou.hotel.dtos.bookingDtos.BookingRequestDto;
import com.benkitou.hotel.exceptions.EntityNotFoundException;

import java.util.List;

public interface BookingService {
    List<BookingDto> getAllBooking(BookingCriteria bookingCriteria);
    BookingDto getBookingById(Long id) throws EntityNotFoundException;

    BookingDto makeBooking(BookingRequestDto bookingRequestDto) throws EntityNotFoundException;

    BookingDto modifyBookingStatusToActive(Long activeStatusId) throws EntityNotFoundException;

    BookingDto modifyBookingStatusToCancelled(Long cancelledStatusId) throws EntityNotFoundException;

}