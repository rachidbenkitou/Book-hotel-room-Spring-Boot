package com.benkitou.hotel.services.inter;



import com.benkitou.hotel.dtos.ImageDto;
import com.benkitou.hotel.dtos.ResponseDto;
import com.benkitou.hotel.exceptions.EntityNotFoundException;

import java.util.List;

public interface ImageService {
    List<ImageDto> getImagesByHotelId(Long hotelId);
    List<ImageDto> getImagesByRoomId(Long roomId);

    ImageDto getImageById(Long id) throws EntityNotFoundException;

    ImageDto addImage(ImageDto imageDto);

    ImageDto updateImage(Long id, ImageDto imageDto) throws EntityNotFoundException;

    ResponseDto deleteImageById(Long id) throws EntityNotFoundException;

}
