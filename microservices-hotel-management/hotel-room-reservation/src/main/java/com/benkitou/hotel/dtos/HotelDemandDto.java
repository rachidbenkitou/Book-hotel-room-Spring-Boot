package com.benkitou.hotel.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@AllArgsConstructor
public class HotelDemandDto implements Serializable {
    private Long id;

    // Hotel Infos Start
    private String hotelName;
    private String hotelAddress;
    private String hotelDescription;
    private String hotelDefaultPhoneNumber;
    private String hotelDefaultEmail;
    private String hotelStatus;
    private Long hotelCityId;
    // Hotel Infos End

    // HotelOwner Infos Start

    private String firstName;
    private String lastName;
    private String address;
    private String phone;
    private String email;
    private String password;
    private String cin;
    // HotelOwner Infos End
}
