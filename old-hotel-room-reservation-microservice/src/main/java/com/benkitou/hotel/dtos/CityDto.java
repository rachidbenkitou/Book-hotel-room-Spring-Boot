package com.benkitou.hotel.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CityDto {
    private Long id;
    private String name;
}
