package com.benkitou.hotel.services;


import com.benkitou.hotel.daos.ImageDao;
import com.benkitou.hotel.dtos.ImageDto;
import com.benkitou.hotel.dtos.ResponseDto;
import com.benkitou.hotel.entities.Image;
import com.benkitou.hotel.exceptions.EntityNotFoundException;
import com.benkitou.hotel.mappers.ImageMapper;
import com.benkitou.hotel.services.inter.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {
    private final ImageDao imageDao;
    private final ImageMapper imageMapper;

    @Override
    public List<ImageDto> getImagesByHotelId(Long hotelId) {
        return imageMapper.modelsToDtos(imageDao.findImagesByHotelId(hotelId));
    }

    @Override
    public List<ImageDto> getImagesByRoomId(Long roomId) {
        return imageMapper.modelsToDtos(imageDao.findImagesByRoomId(roomId));
    }

    @Override
    public ImageDto getImageById(Long id) throws EntityNotFoundException {

        Optional<Image> image = imageDao.findById(id);

        if (image.isPresent()) {
            return imageMapper.modelToDto(image.get());

        } else {
            throw new EntityNotFoundException(String.format("The image with the id %d is not found.", id));
        }
    }

    @Override
    public ImageDto addImage(ImageDto imageDto) {
        imageDto.setId(null);
        Image savedImage = imageDao.save(imageMapper.dtoToModel(imageDto));
        return imageMapper.modelToDto(savedImage);
    }

    @Override
    public ImageDto updateImage(Long id, ImageDto imageDto) throws EntityNotFoundException {
        ImageDto oldImageDto = getImageById(id);
        imageDto.setId(oldImageDto.getId());
        Image updatedImage = imageDao.save(imageMapper.dtoToModel(imageDto));
        return imageMapper.modelToDto(updatedImage);
    }

    @Override
    public ResponseDto deleteImageById(Long id) throws EntityNotFoundException {
        ImageDto image = getImageById(id); // Make sure this method retrieves the image entity
        // Delete the image file from the file system
        String filePath = image.getFilePath();
        File imageFile = new File(filePath);

        if (imageFile.exists()) {
            if (imageFile.delete()) {
                imageDao.deleteById(id);
            } else {
                throw new RuntimeException("Failed to delete image file");
            }
        } else {
            // If the file doesn't exist, only delete the entity from the database
            imageDao.deleteById(id);
        }

        return ResponseDto.builder()
                .message("Image successfully deleted.")
                .build();
    }

}
