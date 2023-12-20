package com.benkitou.hotel.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PhoneNumber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String phone;
    @Column(name = "HOTEL_ID")
    private long hotelId;
    @ManyToOne
    @JoinColumn(name = "HOTEL_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    private Hotel hotel;
}
