package com.tamiresntt.services.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

public class DateConverter {

    public static String toLocalDateTime(Long dateLong) {
        var localDate = LocalDateTime.ofInstant(Instant.ofEpochMilli(dateLong), TimeZone.getDefault().toZoneId());
        var formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        return localDate.format(formatter);
    }
}
