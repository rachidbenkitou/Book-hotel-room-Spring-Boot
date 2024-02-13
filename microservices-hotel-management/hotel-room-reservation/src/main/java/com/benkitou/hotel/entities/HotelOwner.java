package com.benkitou.hotel.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("hotelOwner")
public  class HotelOwner extends Person{

}
