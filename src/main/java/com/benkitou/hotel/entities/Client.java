package com.benkitou.hotel.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@DiscriminatorValue("client")
public class Client  extends Person{

}
