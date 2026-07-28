package com.myproject.global.constants;

public class Regex {
    private Regex() {
    }

    public static final String REGEX_NUMBER = "^\\d+$";
    public static final String REGEX_ISO_DATE = "^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d{3}Z$";
    public static final String REGEX_ISO_OFFSET_DATE_TIME =
            "^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d{3}[+-]\\d{2}:\\d{2}$";
}
