package com.benkitou.hotel.factory;

import com.benkitou.hotel.criteria.HotelOwnerCriteria;

public class HotelOwnerCriteriaFactory {
    private HotelOwnerCriteriaFactory() {
        throw new IllegalStateException("ClientCriteriaFactory class");
    }

    public static HotelOwnerCriteria createById(Long id) {
        HotelOwnerCriteria hotelOwnerCriteria = new HotelOwnerCriteria();
        hotelOwnerCriteria.setId(id);
        return hotelOwnerCriteria;
    }

    public static HotelOwnerCriteria createHotelOwnerObject(Long id, String phone, String email, String cin) {
        HotelOwnerCriteria hotelOwnerCriteria = new HotelOwnerCriteria();
        hotelOwnerCriteria.setId(id);
        hotelOwnerCriteria.setPhone(phone);
        hotelOwnerCriteria.setEmail(email);
        hotelOwnerCriteria.setCin(cin);
        return hotelOwnerCriteria;
    }
}
