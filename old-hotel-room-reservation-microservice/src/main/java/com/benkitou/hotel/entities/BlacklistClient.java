package com.benkitou.hotel.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class BlacklistClient implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long hotelId;
    private Long clientId;
    private String cin;
    private String phone;
    private Boolean isAllowed;
    // How many times the client made noise
    private Integer numberOfTries;
}
