package com.benkitou.hotel.dtos;

import lombok.Data;

public class HotelOwnerDto extends PersonDto{
    public  HotelOwnerDto(){}
    public HotelOwnerDto(Long id, String firstName, String lastName, String address, String phone, String email) {
        super(id, firstName, lastName, address, phone, email);
    }
}
