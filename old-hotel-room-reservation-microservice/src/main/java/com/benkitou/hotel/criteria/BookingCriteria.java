package com.benkitou.hotel.criteria;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class BookingCriteria {
    private Long id;
    private Long clientId;
    private Long statusId;
    private Double price;
    private LocalDate dateCreated;
}
