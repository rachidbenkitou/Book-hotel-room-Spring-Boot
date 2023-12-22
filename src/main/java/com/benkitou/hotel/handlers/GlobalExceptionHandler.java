package com.benkitou.hotel.handlers;

import com.benkitou.hotel.dtos.ResponseDto;
import com.benkitou.hotel.exceptions.EntityNotFoundException;
import com.benkitou.hotel.utils.FormatDateTime;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
    private final HttpServletRequest request;

    public GlobalExceptionHandler(HttpServletRequest request) {
        this.request = request;
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ResponseDto> handleEntityNotFoundException(EntityNotFoundException ex) {
        ResponseDto errorResponse = ResponseDto.builder()
                .message(ex.getMessage())
                .timestamp(FormatDateTime.formatDateTime(LocalDateTime.now()))
                .path(getRequestPath())
                .status(HttpStatus.NOT_FOUND.value())
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    private String getRequestPath() {
        return request.getRequestURI();
    }


}
