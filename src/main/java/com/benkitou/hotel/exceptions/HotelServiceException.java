package com.benkitou.hotel.exceptions;

public class HotelServiceException extends RuntimeException {

    public HotelServiceException(String message) {
        super(message);
    }

    public HotelServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
