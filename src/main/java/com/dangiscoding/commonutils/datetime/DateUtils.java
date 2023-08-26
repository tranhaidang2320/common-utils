package com.dangiscoding.commonutils.datetime;

import java.text.SimpleDateFormat;
import java.util.Date;

public final class DateUtils {
    private DateUtils() {

    }

    public static String toString(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }
}
