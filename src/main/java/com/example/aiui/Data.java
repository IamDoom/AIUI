package com.example.aiui;
import java.util.ArrayList;


interface UserFactory{
    User createUser(String firstName, String lastName, String email, String username, String password);
}

class AdministratorFactory implements UserFactory{
    @Override
    public User createUser(String firstName, String lastName, String email, String username, String password) {
        return new Administrator(firstName, lastName, email, username, password);
    }
}

class EmployeeFactory implements UserFactory{
    @Override
    public User createUser(String firstName, String lastName, String email, String username, String password) {
        return new Employee(firstName, lastName, email, username, password);
    }
}


abstract class User{
    private GespreksManager gespreksManager;
    private static int IDcounter = 0;
    private String firstName;
    private String lastName;
    private String password;
    private String username;
    private final int employeeID;
    private String email;
    protected String type;

    public User(String firstName, String lastName, String email, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.username = username;
        this.employeeID = IDcounter;
        IDcounter += 1;
        this.email = email;
        this.gespreksManager = new GespreksManager();
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
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
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
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public GespreksManager getGespreksManager() {
        return gespreksManager;
    }

    public void setGespreksManager(GespreksManager gespreksManager) {
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
    private final ArrayList<User> users;
    public UserDB(){
        users = new ArrayList<>();
    }
    public void addUser(User user){
        users.add(user);
    }
    public ArrayList<User> getUsers(){
        return users;
    }
}

class ChatHistory{
    private final ArrayList<String> chat = new ArrayList<>();
}

class Data {
    private final UserDB userDB;
    public Data(){
        userDB = new UserDB();
        this.registerUser("john", "doe", "johndoe@emailadress.com","abc", "123", true);
    }

    public void registerUser(String firstname, String lastname, String emailaddress, String username, String password,  boolean administrator){
        //check password strength
        PasswordCheck passwordCheck = new PasswordCheck();
        passwordCheck.wachtwoordSterkteVerwerker(password);
        UserFactory userFactory;
        if (administrator) {
            userFactory = new AdministratorFactory();
        }else{
            userFactory = new EmployeeFactory();
        }

        User newUser = userFactory.createUser(firstname,lastname,emailaddress,username,password);
        userDB.addUser(newUser);
    }

    public UserDB getUserDB() {
        return userDB;
    }

    public User login(String username, String password) {
        for(User user: userDB.getUsers()){
            if(username.equals(user.getUsername()) && password.equals(user.getPassword())){
                return user;
            }
        }
        System.out.println("No user matches the given credentials");
        return null; //login credentials match no user
    }
}
