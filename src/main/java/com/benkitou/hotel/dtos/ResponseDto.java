package com.benkitou.hotel.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseDto {
    private String message;
}
