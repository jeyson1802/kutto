package com.kutto.plataforma.util;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static Date stringToDate(String fechaString, String formatString) {

        if (fechaString == null) return null;
        SimpleDateFormat format = new SimpleDateFormat(formatString);
        try {
            return format.parse(fechaString);
        } catch (Exception e) {
            return null;
        }
    }

    public static Time stringToTime(String timeString, String formatString) {

        if (timeString == null) return null;
        SimpleDateFormat format = new SimpleDateFormat(formatString);
        try {
            return new Time(format.parse(timeString).getTime());
        } catch (Exception e) {
            return null;
        }
    }

    public static Date stringToDateYYYYMMDD(String fechaString) {
        return stringToDate(fechaString, "yyyy-MM-dd");
    }

    public static Time stringToTimeHHMMSS(String timeString) {
        return stringToTime(timeString, "HH:mm:ss");
    }
}
