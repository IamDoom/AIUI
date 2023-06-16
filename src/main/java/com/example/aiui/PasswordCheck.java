package com.example.aiui;

public class PasswordCheck {
    public void WachtWoordStengthVerwerker(String ww){
        int Strenght = this.WachtWoordCheck(ww);
        if(0 <= Strenght && Strenght < 3){
            System.out.println("Low Password Strength");
        }
        if(2 < Strenght && Strenght < 5){
            System.out.println("Decent Password Strength");
        }
        if(Strenght == 5){
            System.out.println("Strong Password Strength");

        }
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