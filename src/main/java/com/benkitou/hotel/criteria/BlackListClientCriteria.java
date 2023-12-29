package com.benkitou.hotel.criteria;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BlackListClientCriteria {
    private long id;
    private long hotelId;
    private String cin;
    private String phone;
}
