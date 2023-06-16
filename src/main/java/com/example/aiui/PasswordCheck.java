package com.example.aiui;

public class PasswordCheck {
    public boolean wachtwoordSterkteVerwerker(String ww){
        int strength = this.WachtWoordCheck(ww);
        String strengthMessage = (0 <= strength && strength < 3) ? "Low Password Strength"
                : (2 < strength && strength < 5) ? "Decent Password Strength"
                : (strength == 5) ? "Strong Password Strength"
                : "";
        System.out.println(strengthMessage);
        return (3<= strength && strength <=5);

    }
    public int WachtWoordCheck(String password){
        int strength = 0;
        if (password.length() >= 8) {
            strength++;
        }

        // Check for uppercase letters
        if (password.matches(".*[A-Z].*")) {
            strength++;
        }

        // Check for lowercase letters
        if (password.matches(".*[a-z].*")) {
            strength++;
        }

        // Check for digits
        if (password.matches(".*\\d.*")) {
            strength++;
        }

        // Check for special characters
        if (password.matches(".*[^a-zA-Z0-9].*")) {
            strength++;
        }

        return strength;
    }
}
