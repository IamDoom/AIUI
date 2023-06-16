package com.example.aiui;
import java.util.ArrayList;


interface UserFactory{
    User createUser(String firstName, String lastName, String email, String username, String password);
}

class administratorFactory implements UserFactory{
    @Override
    public User createUser(String firstName, String lastName, String email, String username, String password) {
        return new Administrator(firstName, lastName, email, username, password);
    }
}
class employeeFactory implements UserFactory{
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
    private String Username;
    private final int employeeID;
    private String Email;
    protected String type;

    public User(String firstName, String lastName, String email, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.Username = username;
        this.employeeID = IDcounter;
        IDcounter += 1;
        this.Email = email;
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

class chatHistory{
    private final ArrayList<String> chat = new ArrayList<>();

}

class Data {
    private final UserDB UserDB;
    public Data(){
        UserDB = new UserDB();
        this.registerUser("john", "doe", "johndoe@emailadress.com","abc", "123", true);
    }

    public void registerUser(String firstname, String lastname, String emailaddress, String username, String password,  boolean administrator){
        //check password strenght
        PasswordCheck passwordCheck = new PasswordCheck();
        passwordCheck.WachtWoordStengthVerwerker(password);
        UserFactory userFactory;
        if (administrator) {
            userFactory = new administratorFactory();
        }else{
            userFactory = new employeeFactory();
        }

        User newUser = userFactory.createUser(firstname,lastname,emailaddress,username,password);
        UserDB.addUser(newUser);
    }

    public String DeterminePasswordStrength(String password){
        return null;

    }


    public UserDB getUserDB() {
        return UserDB;
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
