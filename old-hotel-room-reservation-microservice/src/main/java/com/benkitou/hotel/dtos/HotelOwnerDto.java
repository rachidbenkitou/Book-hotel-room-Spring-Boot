package com.benkitou.hotel.dtos;

public class HotelOwnerDto extends PersonDto {
    public HotelOwnerDto() {
    }

    public HotelOwnerDto(Long id, String firstName, String lastName, String address, String phone, String email, String cin) {
        super(id, firstName, lastName, address, phone, email, cin);
    }
}
