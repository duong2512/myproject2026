package com.myproject.global.util;

import com.myproject.global.exception.BadRequestException;
import lombok.Generated;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateEx {
    @Generated
    private static final Logger log = LoggerFactory.getLogger(DateEx.class);

    private DateEx() {
    }

    public static Date getMinDate(int year) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, 0, 2, 0, 0, 0);
        return getTimeEx(cal);
    }

    public static Date getMinDate() {
        return getMinDate(1);
    }

    public static Date getMinDate1753() {
        return getMinDate(1753);
    }

    public static Date getMinDate1970() {
        return getMinDate(1970);
    }

    public static Date getMaxDate() {
        Calendar cal = Calendar.getInstance();
        cal.set(9999, 11, 31, 23, 59, 59);
        return getTimeEx(cal);
    }

    public static Date setMinDateIfNull(Date tInput) {
        return tInput != null && getTimeEx(tInput) >= getTimeEx(getMinDate()) ? tInput : getMinDate();
    }

    public static Date setMinDate1753IfNull(Date tInput) {
        return tInput != null && getTimeEx(tInput) >= getTimeEx(getMinDate1753()) ? tInput : getMinDate1753();
    }

    public static Date setMinDate1970IfNull(Date tInput) {
        return tInput != null && getTimeEx(tInput) >= getTimeEx(getMinDate1970()) ? tInput : getMinDate1970();
    }

    public static Date setMaxDateIfNull(Date tInput) {
        return tInput != null && getTimeEx(tInput) > getTimeEx(getMinDate1970()) ? tInput : getMaxDate();
    }

    public static long getTimeEx(Date tInput) {
        return tInput.getTime();
    }

    public static long getTimeEx(java.sql.Date tInput) {
        return tInput.getTime();
    }

    public static Date getTimeEx(Calendar tInput) {
        return tInput.getTime();
    }

    public static Date getFirstDayOfYearCurrent() {
        return getFirstDayOfYear(Calendar.getInstance().get(1));
    }

    public static Date getFirstDayOfYear(Date tInput) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(tInput);
        return getFirstDayOfYear(cal.get(1));
    }

    public static Date getFirstDayOfYear(int year) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, 0, 1, 0, 0, 0);
        return getTimeEx(cal);
    }

    public static Date string2Date(String dateString, String pattern) {
        return string2Date(dateString, pattern, (Date) null);
    }

    public static Date string2Date(String dateString, String patternInput, Date dateDefaultValue) {
        try {
            if (!StringUtils.isBlank(dateString) && !dateString.equalsIgnoreCase("NULL")) {
                dateString = StringUtils.trimToEmpty(dateString);
                patternInput = StringUtils.trimToEmpty(patternInput);
                if (!StringUtils.isEmpty(dateString) && !StringUtils.isEmpty(patternInput)) {
                    return (new SimpleDateFormat(patternInput)).parse(dateString);
                } else if (dateDefaultValue == null) {
                    throw new BadRequestException("Dữ liệu đầu vào không hợp lệ [%s] / [%s].", new Object[]{dateString, patternInput});
                } else {
                    return dateDefaultValue;
                }
            } else {
                return dateDefaultValue;
            }
        } catch (ParseException var4) {
            throw new RuntimeException(var4);
        }
    }
}
