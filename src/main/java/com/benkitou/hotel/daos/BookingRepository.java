package com.benkitou.hotel.daos;

import com.benkitou.hotel.dtos.bookingDtos.BookingDto;
import com.benkitou.hotel.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    @Query("SELECT NEW com.benkitou.hotel.dtos.bookingDtos.BookingDto(" +
            "booking.id,booking.client.id, booking.client.phone, booking.client.email, " +
            "booking.bookingStatus.id, booking.bookingStatus.statusName, " +
            "booking.price, booking.dateCreated) " +
            "FROM Booking booking " +
            "LEFT JOIN booking.client " +
            "LEFT JOIN booking.bookingStatus " +
            "WHERE (:id IS NULL OR booking.id = :id) " +
            "AND (:clientId IS NULL OR booking.client.id = :clientId) " +
            "AND (:statusId IS NULL OR booking.bookingStatus.id = :statusId) " +
            "AND (:price IS NULL OR booking.price = :price) " +
            "AND (:dateCreated IS NULL OR booking.dateCreated = :dateCreated)")
    List<BookingDto> findBookingsByQuery(
            @Param("id") Long id,
            @Param("clientId") Long clientId,
            @Param("statusId") Long statusId,
            @Param("price") Integer price,
            @Param("dateCreated") LocalDate dateCreated
    );


}
