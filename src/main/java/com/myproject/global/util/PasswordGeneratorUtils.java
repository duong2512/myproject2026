package com.myproject.global.util;

import org.apache.commons.lang3.RandomStringUtils;

public class PasswordGeneratorUtils {
    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL = "!@#$%^&*";

    public static String generateTempPassword(int length) {
        String upper = RandomStringUtils.random(1, UPPER);
        String lower = RandomStringUtils.random(1, LOWER);
        String digit = RandomStringUtils.random(1, DIGITS);
        String special = RandomStringUtils.random(1, SPECIAL);

        String all = UPPER + LOWER + DIGITS + SPECIAL;
        String remaining = RandomStringUtils.random(length - 4, all);

        String combined = upper + lower + digit + special + remaining;

        char[] chars = combined.toCharArray();
        for (int i = chars.length - 1; i > 0; i--) {
            int j = (int) (Math.random() * (i + 1));
            char temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;
        }
        return new String(chars);
    }
}
