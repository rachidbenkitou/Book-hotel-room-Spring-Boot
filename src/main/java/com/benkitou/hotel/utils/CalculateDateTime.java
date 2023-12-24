package com.benkitou.hotel.utils;

import java.time.LocalDate;

public class CalculateDateTime {
    public static LocalDate calculateEndDate(LocalDate startDate, int numberOfDays) {
        if (startDate == null || numberOfDays < 0) {
            throw new IllegalArgumentException("Invalid input parameters (calculateEndDate function in CalculateDateTime class)");
        }

        return startDate.plusDays(numberOfDays);
    }
}
