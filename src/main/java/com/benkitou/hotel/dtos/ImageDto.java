package com.benkitou.hotel.dtos;

import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
public class ImageDto implements Serializable {
    private Long id;
    private String name;
    private String url;
    private String hotelUrl;
    private String roomUrl;
}
