package com.benkitou.hotel.daos;

import com.benkitou.hotel.entities.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingStatusRepository  extends JpaRepository<BookingStatus, Long> {
}
