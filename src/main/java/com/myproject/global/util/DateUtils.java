package com.myproject.global.util;

import lombok.extern.slf4j.Slf4j;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

@Slf4j
public class DateUtils {
    public static final String DATE_FORMAT_YYYY_MM_DD = "yyyy-MM-dd";
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_TIME_FORMAT_DD_MM_YYYY_HH_MM = "yyyy-MM-dd HH:mm";
    public static final String YY = "yy";
    public static final String DATE_FORMAT_DD_MM_YYYY = "dd/MM/yyyy";
    public static final String DATE_FORMAT_DD_MM_YYYY_HH_MM_SS = "dd/MM/yyyy HH:mm:ss";
    public static final String DATE_TIME_ISO = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    public static final String YYYY_MM_DD = "yyyyMMdd";
    public static final String DDMMYYYY = "ddMMyyyy";
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy_MM_dd_HH_mm_ss";
    public static final Integer ONE_YEAR_DAY = 365;

    private DateUtils() {
        throw new UnsupportedOperationException();
    }

    public static String dateToString(Date date, String... format) {
        if (date == null) {
            return null;
        }
        String dateFormat = format.length > 0 ? format[0] : YY;
        if (dateFormat.equals(DATE_TIME_ISO)) {
            TimeZone tz = TimeZone.getTimeZone("UTC");
            DateFormat df = new SimpleDateFormat(dateFormat);
            df.setTimeZone(tz);
            return df.format(date);
        }
        return new SimpleDateFormat(format.length > 0 ? format[0] : YY).format(date);
    }

    public static Date stringToDate(String date, String... format) {
        if (StringUtils.isNullOrBlank(date)) return null;
        try {
            String fm = format.length > 0 ? format[0] : DATE_FORMAT_YYYY_MM_DD;
            SimpleDateFormat sdf = new SimpleDateFormat(fm);
            if(DATE_TIME_ISO.equals(fm)) {
                sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
            }
            return sdf.parse(date);
        } catch (ParseException e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public static Timestamp stringToTimestamp(String dateStr, String... format) {
        Date date = stringToDate(dateStr, format);
        if (date == null) {
            return null;
        }
        return new Timestamp(date.getTime());
    }

    public static Date max(Date date1, Date date2) {
        if (date1 == null && date2 == null) {
            return null;
        }

        if (date1 != null && date2 == null) {
            return date1;
        }

        if (date1 == null) {
            return date2;
        }

        return new Date(Math.max(date1.getTime(), date2.getTime()));
    }

    public static Date max(Date date1, Date date2, Date date3) {
        if (date1 == null && date2 == null && date3 == null) {
            return null;
        }

        if (date1 == null) {
            return max(date2, date3);
        }

        if (date2 == null) {
            return max(date1, date3);
        }

        if (date3 == null) {
            return max(date1, date2);
        }

        return max(max(date1, date2), date3);
    }

    public static String toExcelDateFormat(Date date, String format) {
        if (date == null) {
            return "";
        }
        DateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }
}