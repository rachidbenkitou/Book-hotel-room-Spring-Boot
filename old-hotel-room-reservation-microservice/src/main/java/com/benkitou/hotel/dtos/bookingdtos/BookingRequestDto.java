package com.benkitou.hotel.dtos.bookingdtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookingRequestDto {
    private Long clientId;
    private Long statusId;
    private Long hotelId;
    private Double price;
    private LocalDate dateCreated;
    private List<RoomBookingRequestDto> roomBookingRequest;
}
