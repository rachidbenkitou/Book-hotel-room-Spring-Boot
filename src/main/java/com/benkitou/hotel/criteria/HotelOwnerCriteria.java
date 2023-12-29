package com.benkitou.hotel.criteria;

import lombok.Builder;

@Builder
public class HotelOwnerCriteria extends PersonCriteria {
    public HotelOwnerCriteria(Long id) {
        super(id);
    }

    public HotelOwnerCriteria() {
    }

    public HotelOwnerCriteria(Long id, String phone, String email, String cin) {
        super(id, phone, email, cin);
    }
}
