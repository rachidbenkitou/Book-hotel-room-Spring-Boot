package com.benkitou.hotel.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("client")
public class Client  extends Person{

}
