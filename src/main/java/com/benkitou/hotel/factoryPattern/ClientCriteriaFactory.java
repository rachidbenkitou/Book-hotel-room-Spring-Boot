package com.benkitou.hotel.factoryPattern;

import com.benkitou.hotel.criteria.ClientCriteria;

public class ClientCriteriaFactory {
    public static ClientCriteria createById(Long id) {
        ClientCriteria clientCriteria = new ClientCriteria();
        clientCriteria.setId(id);
        return clientCriteria;
    }

}
