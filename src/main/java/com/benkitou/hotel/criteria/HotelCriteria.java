package com.benkitou.hotel.criteria;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HotelCriteria {
    private Long id;
    private String name;
    private String address;
    private Long cityId;
}
