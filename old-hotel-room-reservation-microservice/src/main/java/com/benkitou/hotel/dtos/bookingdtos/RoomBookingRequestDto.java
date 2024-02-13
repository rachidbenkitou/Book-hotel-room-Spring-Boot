package com.benkitou.hotel.dtos.bookingdtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoomBookingRequestDto {
    private Long roomId;
    private int numberOfDays;
    private double price;
    private LocalDate startDate;
    private LocalDate endDate;

}
