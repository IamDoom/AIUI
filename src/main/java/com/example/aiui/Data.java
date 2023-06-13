package com.example.aiui;
import java.sql.*;
import java.util.ArrayList;

interface messageReceiver{
    void receiveMessage(String message);
}
class Bot implements messageReceiver{

    private String name;

    Bot(String name){
        this.name = name;
    }
    @Override
    public void receiveMessage(String message) {
        System.out.println("Bot received message: " + message);
        String reply = generateReply(message);
        System.out.println(this.name+": " + reply);
    }
    private String generateReply(String message) {

        return "This is the bot's reply to: " + message;
    }

}

abstract class User{
    private gespreksManager gespreksManager;
    private static int IDcounter = 1;
    private String firstName;
    private String lastName;
    private String password;
    private String Username;
    private int employeeID;
    private String Email;
    protected String type;


    public User(String firstName, String lastName, String email, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.Username = username;
        this.IDcounter += 1;
        this.employeeID = IDcounter;
        this.Email = email;
        this.gespreksManager = new gespreksManager();
    }

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
        return Username;
    }
    public void setUsername(String username) {
        Username = username;
    }
    public int getEmployeeID() {
        return employeeID;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return Email;
    }
    public void setEmail(String email) {
        Email = email;
    }

    public com.example.aiui.gespreksManager getGespreksManager() {
        return gespreksManager;
    }

    public void setGespreksManager(com.example.aiui.gespreksManager gespreksManager) {
        this.gespreksManager = gespreksManager;
    }
}
class Employee extends User{
    Employee(String firstName, String lastName, String email, String username, String password){
        super(firstName,lastName,email,username,password);
        super.type = "employee";
    }
}

class Administrator extends User{
    Administrator(String firstName, String lastName, String email, String username, String password){
        super(firstName,lastName,email,username,password);
        super.type = "admin";
    }
}

class UserDB{
    private ArrayList<User> users = new ArrayList<>();

    public void addUser(User user){
        users.add(user);
    }
    public ArrayList<User> getUsers(){
        return users;
    }

}

class chatHistory{
    private ArrayList<String> chat = new ArrayList<>();

}

class data {
    private UserDB UserDB = new UserDB();
    public void registerUser(String firstname, String lastname, String emailaddress, String username, String password,boolean administrator){
        if (administrator) {
            Administrator NewAdmin = new Administrator(firstname,lastname,emailaddress,username,password);
            UserDB.addUser(NewAdmin);
        }else{
            Employee newEmployee = new Employee(firstname,lastname,emailaddress,username,password);
            UserDB.addUser(newEmployee);

        }
    }

    public User login(String username, String password) {
        for(User user: UserDB.getUsers()){
            if(username.equals(user.getUsername()) && password.equals(user.getPassword())){
                return user;
            }
        }
        System.out.println("no user matches the given credentials");
        return null; //login credentials match no user
    }
}
