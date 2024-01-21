package com.benkitou.hotel.services.strategy;


import com.benkitou.hotel.daos.ImageDao;
import com.benkitou.hotel.dtos.ImageDto;
import com.benkitou.hotel.dtos.ResponseDto;
import com.benkitou.hotel.entities.Image;
import com.benkitou.hotel.exceptions.EntityAlreadyExistsException;
import com.benkitou.hotel.exceptions.EntityServiceException;
import com.benkitou.hotel.mappers.ImageMapper;
import com.benkitou.hotel.services.strategy.inter.HotelImageStrategy;
import com.benkitou.hotel.services.strategy.inter.images.ImageDeletionStrategy;
import com.benkitou.hotel.services.strategy.inter.images.ImageStrategy;
import com.benkitou.hotel.services.strategy.inter.images.ImagesStrategy;
import com.benkitou.hotel.utils.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import static com.benkitou.hotel.utils.FilesOperations.deleteFolderAndContents;
import static com.benkitou.hotel.utils.FilesOperations.removeLastSegmentFromPath;

@Service
@Transactional
@RequiredArgsConstructor
public class HotelImageStrategyImpl implements HotelImageStrategy {

    private final ImageDao imageDao;
    private final ImageMapper imageMapper;

    @Override
    public void uploadImage(MultipartFile file, Long hotelId) throws IOException {

        try {
            String folderPath = String.format("%s hotels/hotel_ %d /", Constants.IMAGE_FOLDER_PATH, hotelId);
            String filePath = folderPath + file.getOriginalFilename();

            Optional<Image> existingImage = imageDao.findByFilePath(filePath);
            existingImage.ifPresent(image -> {
                try {
                    throw new EntityAlreadyExistsException(String.format("This image with name %s already exists", file.getOriginalFilename()));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });

            imageDao.save(
                    Image.builder()
                            .name(file.getOriginalFilename())
                            .room(null)
                            .hotelId(hotelId)
                            .type(file.getContentType())
                            .filePath(filePath).build());

            // Create the product's folder if it doesn't exist
            Path path = Paths.get(folderPath);
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }

            file.transferTo(new File(filePath));

        } catch (Exception exception) {
            throw new EntityServiceException("An error occurred while saving hotel image.", exception);
        }
    }

    @Override
    public void uploadImages(List<MultipartFile> images, Long hotelId) throws IOException {
        for (MultipartFile image : images) {
            uploadImage(image, hotelId);
        }
    }

    @Override
    public ResponseDto deleteImages(Long hotelId) {
        List<ImageDto> imageList = imageMapper.modelsToDtos(imageDao.findImagesByHotelId(hotelId));
        return deleteImagesFromSystem(imageList, hotelId);
    }

    private ResponseDto deleteImagesFromSystem(List<ImageDto> imageList, Long elementId) {
        if (!imageList.isEmpty()) {
            String newPath = removeLastSegmentFromPath(imageList.get(0).getFilePath());
            deleteFolderAndContents(newPath);
            imageDao.deleteAllByHotelId(elementId);
        }
        return ResponseDto.builder()
                .message("Hotel images successfully deleted.")
                .build();
    }
}
