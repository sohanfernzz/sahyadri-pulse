package com.example.project1.User;

import android.util.Patterns;

import java.util.regex.Pattern;

public class RegistrationUtils {

    private static final String USERNAME_PATTERN = "[a-zA-Z0-9]{3,20}";
    private static final String PASSWORD_PATTERN = "^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$";

    public static boolean isValidEmail(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }





    public static boolean isValidUsername(String username) {
        return Pattern.matches(USERNAME_PATTERN, username);
    }

    public static boolean isValidPassword(String password) {
        return Pattern.matches(PASSWORD_PATTERN, password);
    }

    public static boolean doPasswordsMatch(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }
}
