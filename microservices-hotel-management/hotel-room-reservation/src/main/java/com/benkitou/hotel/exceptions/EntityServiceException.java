package com.benkitou.hotel.exceptions;

public class EntityServiceException extends RuntimeException {

    public EntityServiceException(String message) {
        super(message);
    }

    public EntityServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
