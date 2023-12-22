package com.benkitou.hotel.dtos;

import java.io.Serializable;

public class ClientDto extends PersonDto implements Serializable {
    public ClientDto(){
    }
    public ClientDto(Long id, String firstName, String lastName, String address, String phone, String email) {
        super(id, firstName, lastName, address, phone, email);
    }
}
