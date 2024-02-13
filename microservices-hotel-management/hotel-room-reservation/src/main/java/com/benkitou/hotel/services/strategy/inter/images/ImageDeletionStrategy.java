package com.benkitou.hotel.services.strategy.inter.images;

import com.benkitou.hotel.dtos.ResponseDto;

public interface ImageDeletionStrategy {
    ResponseDto deleteImages(Long elementId);
}
