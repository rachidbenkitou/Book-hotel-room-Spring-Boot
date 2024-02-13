package com.benkitou.hotel.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FormatDateTime {
    public static String formatDateTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return dateTime.format(formatter);
    }
}
