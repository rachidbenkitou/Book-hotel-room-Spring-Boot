package com.benkitou.hotel.criteria;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BlackListClientCriteria {
    private Long id;
    private Long hotelId;
    private String cin;
    private Long clientId;
    private String phone;
    private Boolean isAllowed;
    // How many times the client made noise
    private Integer numberOfTries;
}
