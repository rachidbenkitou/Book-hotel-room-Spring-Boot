package com.benkitou.hotel.services;

import com.benkitou.hotel.services.inter.FlickrService;
import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.uploader.UploadMetaData;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
@Slf4j
public class FlickrServiceImpl implements FlickrService {

    @Value("${flickr.apiKey}")
    private String apiKey;

    @Value("${flickr.apiSecret}")
    private String apiSecret;

    @Value("${flickr.appKey}")
    private String appKey;

    @Value("${flickr.appSecret}")
    private String appSecret;
    private Flickr flickr;

    @Autowired
    FlickrServiceImpl(Flickr flickr) {
        this.flickr = flickr;
    }

    @Override
    @SneakyThrows
    public String savePhoto(InputStream photo, String title) {
        UploadMetaData uploadMetaData = new UploadMetaData();
        uploadMetaData.setTitle(title);

        String photoId = flickr.getUploader().upload(photo, uploadMetaData);
        return flickr.getPhotosInterface().getPhoto(photoId).getMedium640Url();
    }


}
