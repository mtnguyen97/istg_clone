package com.example.istg.commons;

import java.util.Random;

public final class Utils {
    private static final String LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";

    public static String randomString(int length) {
        Random random = new Random();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(LETTERS.length());
            builder.append(LETTERS.charAt(index));
        }
        return builder.toString();
    }
}
