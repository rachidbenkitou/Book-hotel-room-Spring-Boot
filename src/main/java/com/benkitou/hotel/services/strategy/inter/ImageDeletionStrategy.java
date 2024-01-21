package com.benkitou.hotel.services.strategy.inter;

import com.benkitou.hotel.dtos.ResponseDto;

public interface ImageDeletionStrategy {
    ResponseDto deleteImages(Long elementId);
}
