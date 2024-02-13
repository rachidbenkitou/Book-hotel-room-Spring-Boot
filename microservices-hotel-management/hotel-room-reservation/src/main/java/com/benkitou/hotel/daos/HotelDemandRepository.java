package com.benkitou.hotel.daos;

import com.benkitou.hotel.dtos.HotelDemandDto;
import com.benkitou.hotel.entities.HotelDemand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HotelDemandRepository extends JpaRepository<HotelDemand, Long> {
    @Query("SELECT NEW com.benkitou.hotel.dtos.HotelDemandDto(" +
            "hotelDemand.id, " +
            "hotelDemand.hotelName, " +
            "hotelDemand.hotelAddress, " +
            "hotelDemand.hotelDescription, " +
            "hotelDemand.hotelDefaultPhoneNumber, " +
            "hotelDemand.hotelDefaultEmail, " +
            "hotelDemand.hotelStatus, " +
            "hotelDemand.hotelCityId, " +
            "hotelDemand.firstName, " +
            "hotelDemand.lastName, " +
            "hotelDemand.address, " +
            "hotelDemand.phone, " +
            "hotelDemand.email, " +
            "hotelDemand.password, " +
            "hotelDemand.cin) " +
            "FROM HotelDemand hotelDemand " +
            "WHERE (:id IS NULL OR hotelDemand.id = :id) " +
            "AND (:status IS NULL OR hotelDemand.hotelStatus = :status)")
    List<HotelDemandDto> findHotelDemandsByQuery(
            @Param("id") Long id,
            @Param("status") String status
    );


}
