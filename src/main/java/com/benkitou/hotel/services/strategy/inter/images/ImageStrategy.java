package com.benkitou.hotel.services.strategy.inter.images;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageStrategy {
    void uploadImage(MultipartFile file, Long entityId) throws IOException;
}
