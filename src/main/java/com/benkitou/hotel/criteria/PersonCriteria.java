package com.benkitou.hotel.criteria;

import lombok.Data;

@Data
public class PersonCriteria {
    private Long id;
    private String phone;
    private String email;

    public PersonCriteria() {
    }

    public PersonCriteria(Long id) {
        this.id = id;
    }
    public PersonCriteria(Long id, String phone, String email) {
        this.id = id;
        this.email = email;
        this.phone = phone;
    }
}