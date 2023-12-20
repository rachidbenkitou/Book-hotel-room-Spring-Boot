package com.benkitou.hotel.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
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
public class RoomBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "ROOM_ID")
    private long roomId;

    @Column(name = "BOOKING_ID")
    private long bookingId;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDate bookingDate;

    private int numberOfDays;

    private double price;

    @ManyToOne
    @JoinColumn(name = "ROOM_ID",referencedColumnName = "ID", insertable = false, updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    private Room room;

    @ManyToOne
    @JoinColumn(name = "BOOKING_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    private Booking booking;
}
