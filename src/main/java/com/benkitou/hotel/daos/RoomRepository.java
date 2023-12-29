package com.benkitou.hotel.daos;

import com.benkitou.hotel.dtos.RoomDto;
import com.benkitou.hotel.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long>, JpaSpecificationExecutor<Room> {
    @Query("SELECT NEW com.benkitou.hotel.dtos.RoomDto(room.id, room.number, room.description, room.price, room.hotel.id, room.hotel.name) " +
            "FROM Room room " +
            "LEFT JOIN room.hotel " +  // Assuming there is a 'hotel' association in the Room entity
            "WHERE (:id IS NULL OR room.id = :id) " +
            "AND (:number IS NULL OR room.number = :number) " +
            "AND (:price IS NULL OR room.price = :price) " +
            "AND (:hotelId IS NULL OR room.hotel.id = :hotelId) " +
            "AND (:hotelName IS NULL OR LOWER(room.hotel.name) LIKE LOWER(CONCAT('%', :hotelName, '%')))")
    List<RoomDto> findRoomsByQuery(
            @Param("id") Long id,
            @Param("number") Integer number,
            @Param("price") Double price,
            @Param("hotelId") Long hotelId,
            @Param("hotelName") String hotelName
    );

    boolean existsRoomById(Long id);

    boolean existsByNumberAndHotelId(Integer number, Long hotelId);
}
