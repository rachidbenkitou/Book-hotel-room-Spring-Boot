package com.benkitou.hotel.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import java.io.Serializable;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PhoneNumber implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String phone;
    @Column(name = "HOTEL_ID")
    private Long hotelId;
    @ManyToOne
    @JoinColumn(name = "HOTEL_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    private Hotel hotel;
}
