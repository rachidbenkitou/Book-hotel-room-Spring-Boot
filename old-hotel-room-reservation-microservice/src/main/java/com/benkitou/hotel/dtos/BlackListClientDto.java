package com.benkitou.hotel.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class BlackListClientDto {
    private Long id;
    private Long hotelId;
    private String cin;
    private Long clientId;
    private String phone;
    private Boolean isAllowed;
    // How many times the client made noise
    private Integer numberOfTries;
}
