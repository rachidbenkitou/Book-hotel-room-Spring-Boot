package com.benkitou.hotel.patterns.factory;

import com.benkitou.hotel.criteria.ClientCriteria;

public class ClientCriteriaFactory {

    private ClientCriteriaFactory() {
        throw new IllegalStateException("ClientCriteriaFactory class");
    }

    public static ClientCriteria createById(Long id) {
        ClientCriteria clientCriteria = new ClientCriteria();
        clientCriteria.setId(id);
        return clientCriteria;
    }

    public static ClientCriteria createByClientObject(Long id, String phone, String email, String cin) {
        ClientCriteria clientCriteria = new ClientCriteria();
        clientCriteria.setId(id);
        clientCriteria.setPhone(phone);
        clientCriteria.setEmail(email);
        clientCriteria.setCin(cin);
        return clientCriteria;
    }

}
