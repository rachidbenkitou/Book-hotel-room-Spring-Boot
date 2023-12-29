package com.benkitou.hotel.daos;

import com.benkitou.hotel.entities.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoomTypeRepository extends JpaRepository<RoomType, Long> {
    Optional<RoomType> findRoomTypeByName(String name);
    boolean existsRoomTypeByName(String name);
}
