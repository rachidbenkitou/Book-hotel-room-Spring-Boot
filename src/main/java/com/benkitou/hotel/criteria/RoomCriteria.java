package com.benkitou.hotel.criteria;

import lombok.Builder;
import lombok.Data;

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

}
