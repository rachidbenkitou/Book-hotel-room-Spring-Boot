package com.benkitou.hotel.services.strategy.inter;

import com.benkitou.hotel.services.strategy.inter.images.ImageDeletionStrategy;
import com.benkitou.hotel.services.strategy.inter.images.ImageUploadingStrategy;
import com.benkitou.hotel.services.strategy.inter.images.ImagesUploadingStrategy;

public interface RoomImageStrategy extends ImageUploadingStrategy, ImagesUploadingStrategy, ImageDeletionStrategy {
}
