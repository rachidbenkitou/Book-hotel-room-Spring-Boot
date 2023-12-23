package com.benkitou.hotel.daos;

import com.benkitou.hotel.dtos.HotelDto;
import com.benkitou.hotel.entities.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {
    @Query("SELECT NEW com.benkitou.hotel.dtos.HotelDto(hotel.id, hotel.name" +
            ", hotel.address,hotel.description" +
            ", city.id, city.name) " +
            "FROM Hotel hotel LEFT JOIN City city ON hotel.cityId=city.id " +
            "WHERE (:id IS NULL OR hotel.id= :id)" +
            "AND (:name IS NULL OR LOWER(hotel.name) LIKE LOWER(CONCAT('%', :name, '%')))" +
            "AND (:address IS NULL OR LOWER(hotel.address) LIKE LOWER(CONCAT('%', :address, '%')))" +
            "AND (:cityId IS NULL OR (hotel.cityId= :cityId))")
    List<HotelDto> findHotelsByQuery (
            @Param("id") Long id,
            @Param("name") String name,
            @Param("address") String address,
            @Param("cityId") Long cityId
    );

    boolean existsHotelsByName(String name);

    boolean existsHotelById(Long id);
}
