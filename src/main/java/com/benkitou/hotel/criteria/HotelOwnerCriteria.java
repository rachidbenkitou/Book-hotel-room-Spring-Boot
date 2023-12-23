package com.benkitou.hotel.criteria;

import lombok.Builder;

@Builder
public class HotelOwnerCriteria extends PersonCriteria {
    public HotelOwnerCriteria() {
    }

    public HotelOwnerCriteria(Long id) {
        super(id);
    }
    public HotelOwnerCriteria(Long id, String phone, String email) {
        super(id, phone, email);
    }
}
