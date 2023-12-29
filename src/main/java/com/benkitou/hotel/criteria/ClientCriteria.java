package com.benkitou.hotel.criteria;

import lombok.Builder;

@Builder
public class ClientCriteria extends PersonCriteria {

    public ClientCriteria() {
    }

    public ClientCriteria(Long id) {
        super(id);
    }

    public ClientCriteria(Long id, String phone, String email, String cin) {
        super(id, phone, email, cin);
    }
}
