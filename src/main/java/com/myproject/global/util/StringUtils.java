package com.myproject.global.util;

import lombok.extern.slf4j.Slf4j;

import java.text.Normalizer;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

@Slf4j
public class StringUtils {
    private StringUtils() {
        throw new UnsupportedOperationException();
    }

    public static final String FORWARD_SLASH = "/";
    public static final String HYPHEN = "-";
    public static final String COMMA = ",";
    public static final String DOT = ".";
    public static final String EMPTY = "";
    public static final String STRING_FORMAT = "%s.%s.%s.%s";
    public static final String THREE_DOT_FORMAT = "%s.%s.%s.";
    public static final String TAIL_PDF = ".pdf";
    public static final String TAIL_XLSX = ".xlsx";
    public static final String TAIL_DOCX = ".docx";
    public static final String TAIL_ZIP = ".zip";
    public static final String DD_MM_YYYY_HH_MM_SS = "dd/MM/yyyy HH:mm:ss";
    public static final String DD_MM_YYYY_HH_MM_SS_1 = "dd_MM_yyyy_HH_mm_ss";
    public static final String DD_MM_YYYY_2 = "dd/MM/yyyy";
    public static final String YYYY_MM_DD = "yyyy/MM/dd";
    public static final String SEMICOLON = ";";
    public static final String SPACE = " ";
    public static final String HYPHEN_OR_DASH = " - ";
    public static final String COLON = ":";
    public static final Locale LOCALE = Locale.ITALY;
    public static final NumberFormat NUMBER_FORMAT = NumberFormat.getInstance(LOCALE);

    public static boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }

    public static boolean isNullOrBlank(String str) {
        if (str == null) {
            return true;
        }
        return str.trim().isEmpty();
    }

    public static String trim(Object e) {
        if (e != null) {
            return e.toString().trim();
        }
        return null;
    }

    public static String dateToString(Date date, String... format) {
        if (date == null) {
            return null;
        }
        return new SimpleDateFormat(format.length > 0 ? format[0] : DD_MM_YYYY_HH_MM_SS).format(date);
    }

    public static String removeAccent(String text) {
        String temp = Normalizer.normalize(text, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        temp = pattern.matcher(temp).replaceAll("");
        temp = temp.replace("đ", "d");
        temp = temp.replace("Đ", "D");
        return temp;
    }

    public static String nullIfEmpty(String str) {
        return isNullOrBlank(str) ? null : str;
    }

    public static String joinStrings(List<String> strings, String delimiter) {
        if (strings == null || strings.isEmpty()) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strings.size(); i++) {
            sb.append(strings.get(i));
            if (i < strings.size() - 1) {
                sb.append(delimiter);
            }
        }
        return sb.toString();
    }
}
