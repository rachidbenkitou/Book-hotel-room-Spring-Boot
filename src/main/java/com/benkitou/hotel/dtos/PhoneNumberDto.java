package com.benkitou.hotel.dtos;

import com.benkitou.hotel.entities.Hotel;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PhoneNumberDto {

    private Long id;
    private String phone;
    private Long hotelId;
    private String hotelName;
    private String hotelCity;
    private Hotel hotel;


    public PhoneNumberDto(Long id, String phone, Long hotelId, String hotelName, String hotelCity) {
        this.id = id;
        this.phone = phone;
        this.hotelId = hotelId;
        this.hotelName = hotelName;
        this.hotelCity = hotelCity;
    }
}
