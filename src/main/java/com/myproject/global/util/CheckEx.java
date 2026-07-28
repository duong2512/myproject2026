package com.myproject.global.util;

public class CheckEx {
    private static CheckEx instance;

    private CheckEx() {
    }

    public static CheckEx getInstance() {
        if (instance == null) {
            instance = new CheckEx();
        }

        return instance;
    }

    public <T> boolean checkArrayIsEmpty(T[] arrInput) {
        return arrInput == null || arrInput.length < 1;
    }
}
