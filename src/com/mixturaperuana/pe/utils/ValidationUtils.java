package com.mixturaperuana.pe.utils;

public class ValidationUtils {
    public static boolean isValidEmail(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("^\\d{9}$");
    }

    public static boolean isValidName(String name) {
        return name.matches("^[A-Za-zÁÉÍÓÚáéíóúÑñ\\s]{2,}$");
    }
}
