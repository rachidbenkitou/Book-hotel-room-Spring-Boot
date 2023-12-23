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
public class RoomDto {
    private Long id;
    private Integer number;
    private int description;
    private Double price;

    private Long hotelId;

    private String hotelName;
    private Hotel hotel;


    public RoomDto(long id, Integer number, int description, Double price, Long hotelId, String hotelName) {
        this.id = id;
        this.number = number;
        this.description = description;
        this.price = price;
        this.hotelId = hotelId;
        this.hotelName = hotelName;
    }
}
