package com.benkitou.hotel.services.strategy.inter;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ImagesStrategy {
    void uploadImages(List<MultipartFile> files, Long entityId) throws IOException;

}
