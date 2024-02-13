package com.benkitou.hotel.mappers;

import com.benkitou.hotel.dtos.ImageDto;
import com.benkitou.hotel.entities.Image;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface ImageMapper {
    ImageDto modelToDto(Image image);

    Image dtoToModel(ImageDto imageDto);

    List<ImageDto> modelsToDtos(List<Image> images);
}
