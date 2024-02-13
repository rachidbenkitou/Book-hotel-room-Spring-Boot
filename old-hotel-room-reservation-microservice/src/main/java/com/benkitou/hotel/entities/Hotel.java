package com.benkitou.hotel.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import java.io.Serializable;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Hotel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private String description;
    private String defaultImage;
    private String defaultPhoneNumber;
    private String defaultEmail;
    private String status;
    @Column(name = "CITY_ID")
    private Long cityId;
    @ManyToOne
    @JoinColumn(name = "CITY_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    private City city;

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Image> images;

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Email> emails;

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PhoneNumber> phoneNumbers;
}
