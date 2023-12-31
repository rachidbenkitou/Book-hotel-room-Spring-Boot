package com.benkitou.hotel.criteria;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DiscountCriteria {
    private Long id;
    private String type;
    private Boolean isActive;
}
