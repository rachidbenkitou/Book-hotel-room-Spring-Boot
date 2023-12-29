package com.benkitou.hotel.dtos;

import com.benkitou.hotel.entities.Hotel;
import com.benkitou.hotel.entities.RoomType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoomDto {
    private Long id;
    private Integer number;
    private int description;
    private Double price;
    private Long hotelId;
    private Hotel hotel;
    private String hotelName;
    private Integer capacity;
    private Integer capacityAdults;
    private Integer capacityChildren;
    private Boolean isAvailable;
    private Boolean hasWifi;
    private Boolean hasTv;
    private Long roomTypeId;
    private RoomType roomType;
    private LocalDate dateAvailable;


    public RoomDto(long id, Integer number, int description, Double price, Long hotelId, String hotelName) {
        this.id = id;
        this.number = number;
        this.description = description;
        this.price = price;
        this.hotelId = hotelId;
        this.hotelName = hotelName;
    }


}
