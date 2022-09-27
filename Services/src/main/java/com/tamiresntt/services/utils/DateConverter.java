package com.tamiresntt.services.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateConverter {

    public static LocalDateTime toLocalDateTime(String date) {

        var formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        return LocalDateTime.parse(date, formatter);
    }

    public static LocalDate toLocalDate(String date) {

        var formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        return LocalDate.parse(date, formatter);
    }
}
