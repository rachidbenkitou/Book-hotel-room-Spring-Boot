package com.benkitou.hotel.dtos;

import com.benkitou.hotel.entities.Image;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HotelDto implements Serializable {
    private Long id;
    private String name;
    private String address;
    private String description;
    private String status;
    private long cityId;
    private String cityName;
    private String defaultImage;
    private String defaultPhoneNumber;
    private String defaultEmail;

    HotelDto(){}
    public HotelDto(long id, String name, String address, String description, long cityId, String cityName) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.description = description;
        this.cityId = cityId;
        this.cityName = cityName;
    }
}
