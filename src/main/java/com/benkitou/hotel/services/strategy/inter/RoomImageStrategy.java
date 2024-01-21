package com.benkitou.hotel.services.strategy.inter;

import com.benkitou.hotel.services.strategy.inter.images.ImageDeletionStrategy;
import com.benkitou.hotel.services.strategy.inter.images.ImageStrategy;
import com.benkitou.hotel.services.strategy.inter.images.ImagesStrategy;

public interface RoomImageStrategy extends ImageStrategy, ImagesStrategy, ImageDeletionStrategy {
}
