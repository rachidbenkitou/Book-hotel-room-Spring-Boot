package com.benkitou.hotel.daos;

import com.benkitou.hotel.entities.RoomBooking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomBookingRepository extends JpaRepository<RoomBooking, Long> {
}
