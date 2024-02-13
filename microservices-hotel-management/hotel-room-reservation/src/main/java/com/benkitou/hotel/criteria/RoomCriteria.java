package com.benkitou.hotel.criteria;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Builder
public class RoomCriteria implements Serializable {
    private Long id;
    private Integer number;
    private Double price;
    private Long hotelId;
    private String hotelName;
    private Long hotelCityId;
    private Integer capacity;
    private Integer capacityAdults;
    private Integer capacityChildren;
    private Boolean isAvailable;
    private Boolean hasWifi;
    private Boolean hasTv;
    private Long roomTypeId;
    private String roomTypeName;
    // Allowing the user to search for a range of room prices.
    private Double startPrice;
    private Double endPrice;
    // // Allowing the user to search for a range of room availability dates
    private LocalDate startDate;
    private LocalDate endDate;
}
