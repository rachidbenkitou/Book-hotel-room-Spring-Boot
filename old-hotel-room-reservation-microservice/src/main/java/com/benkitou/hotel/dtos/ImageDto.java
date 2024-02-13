package com.benkitou.hotel.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ImageDto implements Serializable {
    private Long id;

    private String name;

    private String type;

    private String filePath;

    private Long hotelId;

    private Long roomId;
}
