package com.benkitou.hotel.utils;

import java.time.LocalDate;

public class DateManipulator {
    public static LocalDate addDaysToDate(LocalDate startDate, int numberOfDays) {
        if (startDate == null || numberOfDays < 0) {
            throw new IllegalArgumentException("Invalid input parameters (calculateEndDate function in DateManipulator class)");
        }
        return startDate.plusDays(numberOfDays);
    }
}
