package com.dangiscoding.commonutils.datetime;

import com.dangiscoding.commonutils.datetime.exception.ConvertPatternException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class DateUtils {
    private DateUtils() {

    }

    public static String writeDateAsString(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    public static String convertPattern(String date, String fromPattern, String toPattern) {
        try {
            return new SimpleDateFormat(toPattern)
                    .format(new SimpleDateFormat(fromPattern).parse(date));
        } catch (ParseException ex) {
            String msg = String.format("Exception when convert [%s] from pattern [%s] to pattern [%s]", date, fromPattern, toPattern);
            throw new ConvertPatternException(msg, ex);
        }
    }
}
