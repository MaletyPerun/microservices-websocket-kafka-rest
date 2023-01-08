package com.example.microservice3.util;

import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;

@UtilityClass
public class TimeUtil {

    public static LocalDateTime getDateTime() {
        return LocalDateTime.now();
    }
}
