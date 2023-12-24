package com.benkitou.hotel.dtos;

import com.benkitou.hotel.entities.Hotel;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmailDto {
    private Long id;
    private String email;
    private Long hotelId;
    private Hotel hotel;
}
