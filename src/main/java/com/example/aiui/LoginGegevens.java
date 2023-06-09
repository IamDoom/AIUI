package com.example.aiui;

public class LoginGegevens {
    private String usernameKey;
    private String passwordKey;

    public LoginGegevens(String usernameKey, String passwordKey){
        this.passwordKey = passwordKey;
        this.usernameKey = usernameKey;
    }

    public String getPasswordKey() {
        return passwordKey;
    }

    public String getUsernameKey() {
        return usernameKey;
    }

    public void setPasswordKey(String passwordKey) {
        this.passwordKey = passwordKey;
    }

    public void setUsernameKey(String usernameKey) {
        this.usernameKey = usernameKey;
    }
}
