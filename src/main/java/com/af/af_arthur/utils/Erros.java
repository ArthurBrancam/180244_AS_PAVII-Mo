package com.af.af_arthur.utils;

public class Erros {
    private static boolean onError = false;

    private static String msg = "";

    public static boolean isOnError() {
        return onError;
    }

    public static void setOnError(boolean onError) {
        Erros.onError = onError;
    }

    public static String getMsg() {
        return msg;
    }

    public static void setMsg(String msg) {
        Erros.setOnError(true);
        Erros.msg = msg;
    }

}