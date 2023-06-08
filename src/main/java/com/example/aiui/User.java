package com.example.aiui;

class User {
    private static int IDcounter = 1;
    private String firstName;
    private String lastName;
    private String password;
    private String username;
    private int employeeID;
    private String email;
    private boolean administrator;

    public User(String firstName, String lastName, String password, String username, int employeeID, String email, boolean administrator) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.username = username;
        this.employeeID = employeeID;
        this.email = email;
        this.administrator = administrator;
    }

    // Overloaded constructor to generate employeeID automatically
    public User(String firstName, String lastName, String password, String username, String email, boolean administrator) {
        this(firstName, lastName, password, username, IDcounter++, email, administrator);
    }

    // Getter and setter methods
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public boolean isAdministrator() {
        return administrator;
    }

    public void setAdministrator(boolean administrator) {
        this.administrator = administrator;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
