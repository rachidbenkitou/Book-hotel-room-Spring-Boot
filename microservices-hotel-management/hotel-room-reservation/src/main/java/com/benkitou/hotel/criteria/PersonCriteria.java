package com.benkitou.hotel.criteria;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonCriteria {
    protected Long id;
    private String phone;
    private String email;
    private String cin;

    public PersonCriteria(Long id) {
        this.id = id;
    }

}
