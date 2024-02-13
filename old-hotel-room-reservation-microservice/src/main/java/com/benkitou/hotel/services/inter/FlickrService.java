package com.benkitou.hotel.services.inter;

import java.io.InputStream;

public interface FlickrService {
    String savePhoto(InputStream photo, String title);
}
