package com.benkitou.hotel.factoryPattern;

import com.benkitou.hotel.criteria.HotelOwnerCriteria;

public class HotelOwnerCriteriaFactory {
    public static HotelOwnerCriteria createById(Long id) {
        HotelOwnerCriteria hotelOwnerCriteria = new HotelOwnerCriteria();
        hotelOwnerCriteria.setId(id);
        return hotelOwnerCriteria;
    }
}
