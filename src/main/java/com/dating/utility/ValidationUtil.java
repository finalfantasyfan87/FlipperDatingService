package com.dating.utility;


import org.springframework.util.StringUtils;

public interface ValidationUtil {
    public static boolean isNullOrEmpty(String value) {
        return !StringUtils.hasLength(value);
    }

    public static boolean isEmailValid(String email) {
        return email != null && email.matches("^[\\w-.]+@([\\\\w-]+\\\\.)+[\\\\w-]{2,4}$");
    }

    public static boolean isValidUserId(String userId) {
        return userId != null && userId.matches("^[a-fA-F0-9]{24}$");
    }

    public static void check(boolean condition){}

}