package com.benkitou.hotel.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDto {
    private String message;
    private int status;
    private String timestamp;
    private String path;

    public ResponseDto(String message) {
        this.message = message;
    }

    public ResponseDto(String message, int status, String timestamp, String path) {
        this.message = message;
        this.status = status;
        this.timestamp = timestamp;
        this.path = path;
    }


}
