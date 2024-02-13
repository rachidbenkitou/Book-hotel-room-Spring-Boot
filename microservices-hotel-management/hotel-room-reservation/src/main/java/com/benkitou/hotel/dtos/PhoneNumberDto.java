package com.benkitou.hotel.dtos;

import com.benkitou.hotel.entities.Hotel;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@AllArgsConstructor
public class PhoneNumberDto {

    private Long id;
    private String phone;
    private Long hotelId;
    private String hotelName;
    private String hotelCity;
    private Hotel hotel;

    public PhoneNumberDto(){}

    public PhoneNumberDto(Long id, String phone, Long hotelId, String hotelName, String hotelCity) {
        this.id = id;
        this.phone = phone;
        this.hotelId = hotelId;
        this.hotelName = hotelName;
        this.hotelCity = hotelCity;
    }
}
