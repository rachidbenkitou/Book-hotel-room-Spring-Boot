package com.benkitou.hotel.criteria;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class RoomCriteria {
    private Long id;
    private Integer number;
    private Double price;
    private Long hotelId;
    private String hotelName;
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


//    Watch the video to implement criteria
//    When adding room make a test if the room number already exists for a hotel.
//    https://www.youtube.com/watch?v=_-TzGBwYf8c&ab_channel=NourShaheen
}
