package com.benkitou.hotel.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RoomBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ROOM_ID")
    private Long roomId;

    @Column(name = "BOOKING_ID")
    private Long bookingId;

//    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDate startDate;

    @Column(nullable = false, updatable = false)
    private LocalDate endDate;

    private Integer numberOfDays;

    @ManyToOne
    @JoinColumn(name = "ROOM_ID",referencedColumnName = "ID", insertable = false, updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    private Room room;

    @ManyToOne
    @JoinColumn(name = "BOOKING_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    private Booking booking;
}
