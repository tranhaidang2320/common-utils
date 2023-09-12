package com.dangiscoding.commonutils.datetime;

import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DateUtilsTest {
    @Test
    void givenSomeValidDateAndPattern_whenWriteDateAsString_thenReturnValidDateString() {
        String expected = "1970-01-01";
        String actual = DateUtils.writeDateAsString(Date.from(Instant.EPOCH), "yyyy-MM-dd");
        assertEquals(expected, actual);
    }
}