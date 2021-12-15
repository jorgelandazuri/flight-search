package com.jalch.flightsearch.common.util;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DateUtils {

    public static DateTime getNexMonday(){
        DateTime today = DateTime.now();
        int dayOfWeek = today.getDayOfWeek();
        return today.plusDays(8 - dayOfWeek);
    }

    public static String  formatDate(DateTime date, String pattern){
        DateTimeFormatter formatter = DateTimeFormat.forPattern(pattern);
        return formatter.print(date);
    }
}
