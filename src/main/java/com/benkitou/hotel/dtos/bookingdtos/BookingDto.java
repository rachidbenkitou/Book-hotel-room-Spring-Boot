package com.benkitou.hotel.dtos.bookingdtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookingDto {
    private long id;
    private Long clientId;
    private String clientPhone;
    private String clientEmail;
    private Long statusId;
    private String statusName;
    private Long hotelId;

    private Double price;

    private LocalDate dateCreated;

    public BookingDto() {

    }

    public BookingDto(Long id, Long clientId, String clientPhone, String clientEmail, Long statusId, String statusName, Double price, LocalDate dateCreated) {
        this.id = id;
        this.clientId = clientId;
        this.clientPhone = clientPhone;
        this.clientEmail = clientEmail;
        this.statusName = statusName;
        this.statusId = statusId;
        this.price = price;
        this.dateCreated = dateCreated;
    }

}
