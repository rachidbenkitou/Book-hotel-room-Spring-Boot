package com.benkitou.hotel.dtos;

import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
public class ClientDto extends PersonDto implements Serializable {

    public ClientDto(Long id, String firstName, String lastName, String address, String phone, String email, String cin) {
        super(id, firstName, lastName, address, phone, email, cin);
    }
}
