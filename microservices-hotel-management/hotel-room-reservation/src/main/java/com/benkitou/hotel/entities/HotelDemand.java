package com.benkitou.hotel.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class HotelDemand implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
