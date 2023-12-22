package com.benkitou.hotel.exceptions;

public class EntityAlreadyExistsException extends Exception{
    public EntityAlreadyExistsException(String message){
        super(message);
    }
    public EntityAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
