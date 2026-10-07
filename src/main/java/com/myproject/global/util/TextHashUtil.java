package com.myproject.global.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.text.Normalizer;
import java.util.Locale;

public class TextHashUtil {
    private TextHashUtil() {
        // Utility class
    }

    /**
     * Chuẩn hóa nội dung text:
     * - Viết hoa
     * - Bỏ dấu tiếng Việt
     * - Bỏ khoảng trắng
     */
    public static String normalize(String text) {
        if (text == null || text.isEmpty()) {
            return null;
        }

        String result = text.toUpperCase(Locale.ROOT);

        // Bỏ dấu tiếng Việt
        result = Normalizer.normalize(result, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "");

        // Xử lý riêng ký tự Đ
        result = result.replace("Đ", "D");

        // Viết liền
        result = result.replaceAll("\\s+", "");

        return result;
    }

    /**
     * Sinh SHA-256 hash từ nội dung text đã chuẩn hóa.
     */
    public static String generateHash(String text) {
        String normalizedText = normalize(text);

        if (normalizedText == null) {
            return null;
        }

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(
                    normalizedText.getBytes(StandardCharsets.UTF_8)
            );

            StringBuilder result = new StringBuilder(hash.length * 2);

            for (byte b : hash) {
                result.append(String.format("%02x", b));
            }

            return result.toString();

        } catch (Exception e) {
            throw new IllegalStateException(
                    "Không thể tạo hash cho nội dung text", e
            );
        }
    }
}
