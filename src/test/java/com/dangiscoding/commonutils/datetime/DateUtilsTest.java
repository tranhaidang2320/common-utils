package com.dangiscoding.commonutils.datetime;

import com.dangiscoding.commonutils.datetime.exception.ConvertPatternException;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DateUtilsTest {
    @Test
    void givenSomeValidDateAndPattern_whenWriteDateAsString_thenReturnValidDateString() {
        String expected = "1970-01-01";
        String actual = DateUtils.writeDateAsString(Date.from(Instant.EPOCH), "yyyy-MM-dd");
        assertEquals(expected, actual);
    }

    @Test
    void givenSomeValidDateAndPattern_whenConvertPattern_thenReturnValidDateString() {
        String expected = "01/01/1970";
        String actual = DateUtils.convertPattern("1970-01-01", "yyyy-MM-dd", "dd/MM/yyyy");
        assertEquals(expected, actual);
    }

    @Test
    void givenSomeDateAndWrongFromPattern_whenConvertPattern_thenThrowConvertPatternException() {
        assertThrows(ConvertPatternException.class, () -> DateUtils.convertPattern("1970/01/01", "yyyy-MM-dd", "dd/MM/yyyy"));
    }
}