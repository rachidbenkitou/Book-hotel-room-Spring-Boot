package com.benkitou.hotel.daos;

import com.benkitou.hotel.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImageDao extends JpaRepository<Image, Long> {
    List<Image> findImagesByHotelId(Long hotelId);
    List<Image> findImagesByRoomId(Long roomId);

    Optional<Image> findByFilePath(String filePath);

    void deleteAllByHotelId(Long hotelId);

    void deleteAllByRoomId(Long roomId);
}
