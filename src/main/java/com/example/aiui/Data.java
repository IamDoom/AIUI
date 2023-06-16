package com.example.aiui;
import java.util.ArrayList;


abstract class UserFactory{

    abstract User createUser(String firstName, String lastName, String email, String username, String password);

    public void AddUser(User user, UserDB userDB) {
        userDB.addUser(user);
    }

    final void CreateAndAddUser(String firstName, String lastName, String email, String username, String password,UserDB userDB){
        User user = createUser(firstName, lastName, email, username, password);
        AddUser(user,userDB);
    }
}

class administratorFactory extends UserFactory {
    @Override
    public User createUser(String firstName, String lastName, String email, String username, String password) {
        return new Administrator(firstName, lastName, email, username, password);
    }

}
class employeeFactory extends UserFactory {
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
    String tempfirstName;
    String templastName;
    String tempemail;
    String tempusername;
    String temppassword;
    private final UserDB UserDB;
    public Data(){
        UserDB = new UserDB();
        tempfirstName = "john";
        templastName = "doe";
        tempemail = "johndoe@emailadress.com";
        temppassword = "Lol123";
        tempusername = "abc";
        this.registerUser(true);
    }

    public void setTempfirstName(String tempfirstName) {
        this.tempfirstName = tempfirstName;
    }

    public void setTemplastName(String templastName) {
        this.templastName = templastName;
    }

    public void setTempemail(String tempemail) {
        this.tempemail = tempemail;
    }

    public void setTempusername(String tempusername) {
        this.tempusername = tempusername;
    }

    public void settempPassword(String password) {
        this.temppassword = password;
    }

    public Boolean registerUser(boolean administrator) {
        //check password strenght
        PasswordCheck passwordCheck = new PasswordCheck();
        if (passwordCheck.wachtwoordSterkteVerwerker(temppassword)) {
            UserFactory userFactory;
            if (administrator) {
                userFactory = new administratorFactory();
            } else {
                userFactory = new employeeFactory();
            }

            userFactory.CreateAndAddUser(tempfirstName,templastName,tempemail,tempusername,temppassword, this.UserDB);
            tempfirstName = null;
            templastName = null;
            tempemail = null;
            temppassword = null;
            tempusername = null;
            return true;
        }
        else {
            return false;
        }
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
