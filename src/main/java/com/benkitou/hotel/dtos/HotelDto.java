package com.benkitou.hotel.dtos;

import lombok.Data;

@Data
public class HotelDto {
    private long id;
    private String name;
    private String address;
    private String description;
    private long cityId;
    private String cityName;

    public HotelDto(long id, String name, String address, String description, long cityId, String cityName){
        this.id=id;
        this.name=name;
        this.address=address;
        this.description=description;
        this.cityId=cityId;
        this.cityName=cityName;
    }
}
