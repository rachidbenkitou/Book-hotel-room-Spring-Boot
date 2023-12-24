package com.benkitou.hotel.mappers;

import com.benkitou.hotel.dtos.bookingDtos.BookingDto;
import com.benkitou.hotel.entities.Booking;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface BookingMapper {
    BookingDto modelToDto(Booking booking);
    Booking dtoToModel(BookingDto bookingDto);
    List<BookingDto> modelsToDtos(List<Booking> bookings);
}
