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
public class DiscountDto implements Serializable {
    private Long id;
    private String type;
    private Double price;
    private int percent;
    private Boolean isActive;
}
