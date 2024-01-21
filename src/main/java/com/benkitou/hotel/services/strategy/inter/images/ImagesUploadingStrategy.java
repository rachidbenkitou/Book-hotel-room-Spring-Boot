package com.benkitou.hotel.services.strategy.inter.images;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ImagesUploadingStrategy {
    void uploadImages(List<MultipartFile> files, Long entityId) throws IOException;

}
