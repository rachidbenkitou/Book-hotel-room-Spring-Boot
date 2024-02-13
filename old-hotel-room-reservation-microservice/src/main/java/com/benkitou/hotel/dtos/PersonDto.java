package com.benkitou.hotel.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersonDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private String phone;
    private String email;
    private String password;
    private String cin;

    public PersonDto(Long id, String firstName, String lastName, String address, String phone, String email, String cin) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.cin=cin;
    }
}
