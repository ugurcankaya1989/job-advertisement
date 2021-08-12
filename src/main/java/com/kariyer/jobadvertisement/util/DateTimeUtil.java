package com.kariyer.jobadvertisement.util;

import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@UtilityClass
public class DateTimeUtil {

    public static LocalDateTime stringToLocalDateTime(String prettierDate) {
        return LocalDateTime.parse(prettierDate, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS"));
    }

}
