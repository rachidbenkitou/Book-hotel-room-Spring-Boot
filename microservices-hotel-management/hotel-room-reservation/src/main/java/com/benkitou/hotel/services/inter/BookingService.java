package com.benkitou.hotel.services.inter;

import com.benkitou.hotel.criteria.BookingCriteria;
import com.benkitou.hotel.dtos.bookingdtos.BookingDto;
import com.benkitou.hotel.dtos.bookingdtos.BookingRequestDto;
import com.benkitou.hotel.dtos.bookingdtos.BookingSumPricePerYearDTO;
import com.benkitou.hotel.exceptions.EntityNotFoundException;

import java.util.List;

public interface BookingService {
    List<BookingDto> getAllBooking(BookingCriteria bookingCriteria);
    BookingDto getBookingById(Long id) throws EntityNotFoundException;

    BookingDto makeBooking(BookingRequestDto bookingRequestDto) throws EntityNotFoundException;

    BookingDto modifyBookingStatusToActive(Long activeStatusId) throws EntityNotFoundException;

    BookingDto modifyBookingStatusToCancelled(Long cancelledStatusId) throws EntityNotFoundException;

    Long countReservationsByHotelId(Long hotelId);

    Double sumReservationsPriceByHotelId(Long hotelId);

    List<BookingSumPricePerYearDTO>  sumReservationsPriceForEveryYearByHotelId(Long hotelId);
}