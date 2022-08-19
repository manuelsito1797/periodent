package com.project.app.util;

import javafx.util.StringConverter;
import javafx.util.converter.LocalDateStringConverter;

import java.sql.Date;
import java.time.LocalDate;

/**
 * @author dhelarius 23/7/2022
 * periodent
 */
public class DateUtil {

    public static StringConverter<LocalDate> getDateStringConverter(Date date) {
        StringConverter<LocalDate> converter = new LocalDateStringConverter();
        converter.fromString(date.toString());
        return converter;
    }

    public static Date getDateFromStr(String str) {
        return Date.valueOf(str);
    }
}
