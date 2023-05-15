package com.example.aiui;

class User{
    private String password;
    private String Username;
    private String Email;

    public User(String password, String username, String email) {
        this.password = password;
        Username = username;
        Email = email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

}
public class data {

}
