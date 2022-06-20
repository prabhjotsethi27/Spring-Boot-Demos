package com.hcl.demo.fundTransferApp.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {

    DateTimeUtil(){}

    public static String convertDateTimeToISOString(LocalDateTime dateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        return dateTime.format(formatter);
    }
}
