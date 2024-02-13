package com.benkitou.hotel.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import java.io.Serializable;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Image implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String type;

    private String filePath;

    @Column(name = "HOTEL_ID")
    private  Long hotelId;

    @Column(name = "ROOM_ID")
    private Long roomId;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "HOTEL_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    private Hotel hotel;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "ROOM_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    private Room room;


}
